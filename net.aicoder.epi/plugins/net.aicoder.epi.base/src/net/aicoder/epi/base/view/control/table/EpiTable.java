package net.aicoder.epi.base.view.control.table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.StateFlagEnum;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.part.IViewElement;
import net.aicoder.tcom.tools.util.AiStringUtil;
import net.aicoder.tcom.tools.util.BeanUtil;

public class EpiTable extends Composite implements IViewElement {
	public final int EACH_CHAR_WIDTH = 10;

	protected Table table;
	protected TableViewer viewer;
	private EpiTableDefiner definer;
	private IViewContext context;
	private EpiSelectionProvider selectionProvider;

	private boolean editable = true;
	private boolean dirty = false;
	private List<IBaseVo> insertedDataList = new ArrayList<IBaseVo>(0);
	private List<IBaseVo> deletedDataList = new ArrayList<IBaseVo>(0);
	private List<IBaseVo> updatedDataList = new ArrayList<IBaseVo>(0);
	
    private Set<TableItem> dirtyBackgroundSet = new HashSet<TableItem>(0);

    private TableItem currentSelectionTableItem = null;
    private IBaseVo currentSelectionData = null;
    
 /**   
	public EpiTable(Composite parent) {
		super(parent, SWT.NULL);
		initEpiTable();
	}
**/
	public EpiTable(Composite parent, EpiTableDefiner definer, IViewContext context) {
		super(parent, SWT.NULL);
		initEpiTable();

		setDefiner(definer);
		attachViewDefiner(definer);
		seViewContext(context);
		viewer.setInput(context.getInput());
		
		selectionProvider = new EpiSelectionProvider(viewer);
	}
	
	private void initEpiTable() {
		setLayout(new FillLayout());

		// table = new Table(this,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL |
		// SWT.FULL_SELECTION |SWT.HIDE_SELECTION);
		table = new Table(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		// table.setLayoutData(new GridData(GridData.FILL_BOTH));
		// table.setLayout(new TableLayout());
		viewer = new TableViewer(table);
	}

	protected void attachViewDefiner(EpiTableDefiner definer) {
		this.definer = definer;

		attachTableColumns();
		attachTableViewer();
		if (isEditable()) {
			attachCellEditors();
		}

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		// GridLayoutFactory.fillDefaults().generateLayout(parent); //
		// 这句话非常害人，每级的Layout自己处理，不要在下一级处理
	}

	private void attachTableColumns() {
		if (definer.getColumnDefinerList().size() == 0) {
			return;
		}
		// TableLayout layout = new TableLayout();
		for (IColumnDefiner columnDefiner : definer.getColumnDefinerList()) {
			TableColumn column = new TableColumn(table, SWT.LEFT);
			String columnName = columnDefiner.getColumnName();
			column.setText(columnName);

			int columnLenath = columnDefiner.getColumnLength();
			if (columnLenath == 0) {
				column.setWidth(EACH_CHAR_WIDTH * 10);
			} else if (columnLenath < 0) {
				column.setWidth(EACH_CHAR_WIDTH * columnLenath * -1);
				column.setResizable(false);
			} else {
				column.setWidth(EACH_CHAR_WIDTH * columnLenath);
			}
			// layout.addColumnData(new ColumnWeightData(50, 75, true));

			boolean isHidden = columnDefiner.ishHidden();
			if (isHidden) {
				column.setWidth(0);
			}
		}
		// tree.setLayout(layout);
	}

	private void attachTableViewer() {
		viewer.setContentProvider(definer.getContentProvider());
		if (definer.getColumnLabelProvider() != null) {
			viewer.setLabelProvider(definer.getColumnLabelProvider());
		} else {
			viewer.setLabelProvider(definer.getLabelProvider());
		}
		//viewer.setInput(definer.getInput());

		if (definer.hasOpenEditAction()) {
			hookDoubleClickAction();
		}
	}

	private void hookDoubleClickAction() {
		Action openEditorAction = new Action() {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				ISelection selection = viewer.getSelection();
				IBaseVo vo = (IBaseVo) ((IStructuredSelection) selection).getFirstElement();
				String editorId = definer.getViewItemDefiner(vo.getEtype()).getEditorId();
				if (!AiStringUtil.isEmpty(editorId)) {
					try {
						IEditorInput input = context.getEditorInput(vo);
						page.openEditor(input, editorId);
					} catch (PartInitException e) {
						System.out.println(e);
					}
				}
			}
		};

		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				openEditorAction.run();
			}
		});
	}

	private void attachCellEditors() {
		int columnDefinerNum = definer.getColumnDefinerList().size();
		if (columnDefinerNum == 0) {
			return;
		}
		ICellModifier cellModifier = new EpiCellModifier(this);
		viewer.setCellModifier(cellModifier);

		CellEditor[] cellEditors = new CellEditor[columnDefinerNum];
		String[] columnProperties = new String[columnDefinerNum];
		for (int colIdx = 0; colIdx < definer.getColumnDefinerList().size(); colIdx++) {
			IColumnDefiner columnDefiner = definer.getColumnDefinerList().get(colIdx);
			String dataType = columnDefiner.getDataType();
			String columnName = columnDefiner.getColumnName();
			boolean isColumnEditable = columnDefiner.isEditable();
			if (isColumnEditable) {
				if (dataType == null) {
					cellEditors[colIdx] = new TextCellEditor(table);
				} else {
					switch (dataType) {
					case IColumnDefiner.CE_COMBOBOX:
						String[] items = definer.getCellEditorItems(columnName);
						cellEditors[colIdx] = new ComboBoxCellEditor(table, items);
						break;
					default:
						cellEditors[colIdx] = new TextCellEditor(table);
						break;
					}
				}
			} else {
				cellEditors[colIdx] = null;
			}
			columnProperties[colIdx] = columnName;
		}

		viewer.setCellEditors(cellEditors);
		viewer.setColumnProperties(columnProperties);
	}

	@Override
	public void setDirtyBackground(String property) {
		IColumnDefiner columnDefiner = definer.getColumnDefinerByName(property);
		if (columnDefiner != null) {
			setDirtyBackground(columnDefiner.getColumnIndex());
		}
	}
	
	@Override
	public void setDirtyBackground(int columnIndex) {
		TableItem treeItem0 = this.getSelectedTableItem();
		if (treeItem0 != null) {
			Display display = Display.getCurrent();
			treeItem0.setBackground(columnIndex, display.getSystemColor(SWT.COLOR_YELLOW));
			dirtyBackgroundSet.add(treeItem0);
		}
	}

	protected void revertToOriginalBackground()
	{
		Display display = Display.getCurrent();
		int columns = table.getColumnCount();
		Iterator<TableItem> iter = dirtyBackgroundSet.iterator();
		while(iter.hasNext())
		{
			TableItem item = iter.next();
			for (int i=0;i<columns;i++)
			{
				item.setBackground(i, display.getSystemColor(SWT.COLOR_WHITE));
			}			
		}		
		dirtyBackgroundSet.clear();
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
 	    
	//// getter/setter
	public Table getTable() {
		return table;
	}
	
	public TableItem getSelectedTableItem() {
		TableItem[] items;
		TableItem tableItem0 = null;
		items = table.getSelection();
		if(items != null) {
			tableItem0 = items[0];
		}
		return tableItem0;
	}
	
	public IBaseVo getFirstSelectedItem() {
		IBaseVo item = null;
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if (selection.size() == 0) {
			return null;
		}
		item = (IBaseVo) selection.getFirstElement();
		return item;
	}

	@SuppressWarnings("rawtypes")
	public IBaseVo[] getSelectedItems() {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if (selection.size() == 0) {
			return null;
		}
		IBaseVo[] items = new IBaseVo[selection.size()];
		Iterator iter = selection.iterator();
		int index = 0;
		while (iter.hasNext()) {
			items[index++] = (IBaseVo) iter.next();
		}
		return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TableViewer getViewer() {
		return viewer;
	}

	public void setViewer(TableViewer viewer) {
		this.viewer = viewer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EpiTableDefiner getDefiner() {
		return definer;
	}

	public void setDefiner(EpiTableDefiner definer) {
		this.definer = definer;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public List<IBaseVo> getInsertedDataList() {
		return insertedDataList;
	}

	public void putInsertedData(IBaseVo item) {
		item.setDataState(StateFlagEnum.INSERTED);
		insertedDataList.add(item);
		setDirty(true);
	}

	public List<IBaseVo> getDeletedDataList() {
		return deletedDataList;
	}

	public void putDeletedData(IBaseVo item) {
		if(item.getDataState()==StateFlagEnum.INSERTED) {
			insertedDataList.remove(item);
		}else {
			item.setDataState(StateFlagEnum.DELETED);
			deletedDataList.add(item);
			setDirty(true);
		}
	}

	public List<IBaseVo> getUpdatedDataList() {
		return updatedDataList;
	}

	public void putUpdatedData(IBaseVo item) {
		if (item.getDataState() == StateFlagEnum.INSERTED) {
			// insertedDataList.remove(item);
		} else {
			item.setDataState(StateFlagEnum.UPDATED);
			//updatedDataList.remove(item);
			if (updatedDataList.indexOf(item) < 0) {
				updatedDataList.add(item);
				setDirty(true);
			}
		}
	}

	public void revert() {
		insertedDataList.clear();
		deletedDataList.clear();
		updatedDataList.clear();
		setDirty(false);
		revertToOriginalBackground();
	}

	@SuppressWarnings("unchecked")
	@Override
	public EpiTable getViewElement() {
		return this;
	}
	
	public IViewContext getViewContext() {
		return context;
	}

	public void seViewContext(IViewContext context) {
		this.context = context;
		viewer.setInput(context.getEditorInput());
	}

	public EpiSelectionProvider getSelectionProvider() {
		return selectionProvider;
	}
	
	/**
	 * 绑定属性区域处理，改变值逆向更新数据
	 */
	public void bindSelectionDataEvent() {
		TableItem selectionTableItem = getSelectedTableItem();
		if(selectionTableItem == null) {
			return;
		}
		if(selectionTableItem.equals(currentSelectionTableItem)) {
			return;
		}
		IBaseVo selectionData = this.getFirstSelectedItem();
		if(selectionData == null || selectionData.equals(currentSelectionData)) {
		}else{
			if(currentSelectionData != null) {
				currentSelectionData.removeAllPropertyChangeListener();
			}
			selectionData.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent event) {
					if (BeanUtil.isEquals(event.getOldValue(), event.getNewValue())) {
						return;
					}
					
					String property = event.getPropertyName();
					setCellValue(selectionTableItem,selectionData, property);
				}
			});
		}
		
		currentSelectionTableItem = selectionTableItem;
		currentSelectionData = selectionData;
	}
	
	private void setCellValue(TableItem selectionTableItem, IBaseVo selectionData , String property) {
		if(selectionTableItem == null || selectionData == null || property == null) {
			return;
		}
		IColumnDefiner columnDefiner = definer.getColumnDefinerByCode(property);
		int colIdx = columnDefiner.getColumnIndex();
		boolean isColumnEditable = columnDefiner.isEditable();
		String showValue = selectionData.getPropertyShowValue(property);
		if (isColumnEditable) {
			selectionTableItem.setText(colIdx, showValue);
		}else {
			selectionTableItem.setText(colIdx, showValue);
		}
	}
	
}
