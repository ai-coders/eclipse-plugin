package net.aicoder.epi.base.view.element.tree;

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
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.StateFlagEnum;
import net.aicoder.epi.base.view.IViewElement;
import net.aicoder.epi.base.view.adapter.IViewContext;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.element.table.EpiCellModifier;
import net.aicoder.tcom.tools.util.AiStringUtil;

public class EpiTree extends Composite implements IViewElement {
	public final int EACH_CHAR_WIDTH = 10;
	
	private EpiTreeDefiner definer;
	private Tree tree;
	private TreeViewer viewer;
	private IViewContext context;
	
	private boolean editable = true;
	private boolean dirty = false;
	private List<IBaseVo> insertedDataList = new ArrayList<IBaseVo>(0);
	private List<IBaseVo> deletedDataList = new ArrayList<IBaseVo>(0);
	private List<IBaseVo> updatedDataList = new ArrayList<IBaseVo>(0);
	
    private Set<TreeItem> dirtyBackgroundSet = new HashSet<TreeItem>(0);
/**	
	public EpiTree(Composite parent) {
		super(parent, SWT.NULL);
		initEpiTree();
	}
	
	public EpiTree(Composite parent, EpiTreeDefiner definer) {
		super(parent, SWT.NULL);
		initEpiTree();
		
		this.definer = definer;
		attachViewDefiner(definer);
	}
**/	
	public EpiTree(Composite parent, EpiTreeDefiner definer, IViewContext context) {
		super(parent, SWT.NULL);
		initEpiTree();
		
		setDefiner(definer);
		attachViewDefiner(definer);
		seViewContext(context);
		viewer.setInput(context.getInput());
	}

	private void initEpiTree() {
		setLayout(new FillLayout());
		
		tree = new Tree(this,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		//tree.setLayout(new TableLayout());
		//tree.setLayoutData(new GridData(GridData.FILL_BOTH)); //excption
		viewer = new TreeViewer(tree);
	}
	

	// 设置: Provider(双击事件), 列配置, CellEditors, 菜单
	protected void attachViewDefiner(EpiTreeDefiner definer) {
		this.definer = definer;
		
		attachTreeViewer();
		attachTreeColumns();
		if(isEditable()) {
			attachCellEditors();
		}
		attachMenus();
		
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
	}
	
	//// private methods
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
	
	private void attachTreeViewer() {
		viewer.setContentProvider(definer.getContentProvider());
		if(definer.getColumnLabelProvider()!=null) {
			viewer.setLabelProvider(definer.getColumnLabelProvider());
		}else {
			viewer.setLabelProvider(definer.getLabelProvider());
		}
		//viewer.setInput(definer.getInput());

		if (definer.hasOpenEditAction()) {
			hookDoubleClickAction();
		}
	}
	
	private void attachTreeColumns() {
		if (definer.getColumnDefinerList().size() == 0) {
			return;
		}
		// TableLayout layout = new TableLayout();
		for (IColumnDefiner columnDefiner : definer.getColumnDefinerList()) {
			TreeColumn column = new TreeColumn(tree, SWT.LEFT);
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
	
	private void attachCellEditors() {
		int columnDefinerNum = definer.getColumnDefinerList().size();
		if (columnDefinerNum == 0) {
			return;
		}
		//ICellModifier cellModifier = new EpiCellModifier(definer, viewer);
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
					cellEditors[colIdx] = new TextCellEditor(tree);
				} else {
					switch (dataType) {
					case IColumnDefiner.CE_COMBOBOX:
						String[] items = definer.getCellEditorItems(columnName);
						cellEditors[colIdx] = new ComboBoxCellEditor(tree, items);
						break;
					default:
						cellEditors[colIdx] = new TextCellEditor(tree);
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
	
	private void attachMenus(){
		
	}
	
	@Override
	public void setDirtyBackground(String property) {
		IColumnDefiner columnDefiner = definer.getColumnDefiner(property);
		if (columnDefiner != null) {
			setDirtyBackground(columnDefiner.getColumnIndex());
		}
	}
	
	@Override
	public void setDirtyBackground(int columnIndex) {
		TreeItem treeItem0 = this.getSelectedTreeItem();
		if (treeItem0 != null) {
			Display display = Display.getCurrent();
			treeItem0.setBackground(columnIndex, display.getSystemColor(SWT.COLOR_YELLOW));
			dirtyBackgroundSet.add(treeItem0);
		}
	}
	
/**
	public void setDirtyBackground(TreeItem item, int columnIndex)
	{
		if (item != null) 
		{
			Display display = Display.getCurrent();
			item.setBackground(columnIndex, display.getSystemColor(SWT.COLOR_YELLOW));			
			dirtyBackgroundSet.add(item);
		}
	}

	public void removeDirtyBackground(TreeItem item, int columnIndex) {
		if (item != null) {
			Display display = Display.getCurrent();
			item.setBackground(columnIndex, display.getSystemColor(SWT.COLOR_WHITE));
			
			//dirtyBackgroundSet.remove(item);
		}
	}
**/	

	protected void revertToOriginalBackground()
	{
		Display display = Display.getCurrent();
		int columns = tree.getColumnCount();
		Iterator<TreeItem> iter = dirtyBackgroundSet.iterator();
		while(iter.hasNext())
		{
			TreeItem item = iter.next();
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
 	    
	//// Getter/Setter
	public Tree getTree() {
		return tree;
	}
	
	public TreeItem getSelectedTreeItem() {
		TreeItem[] items;
		TreeItem treeItem0 = null;
		items = tree.getSelection();
		if(items != null && items.length > 0) {
			treeItem0 = items[0];
		}
		return treeItem0;
	}

	@Override
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
		while(iter.hasNext()) {
			items[index++] = (IBaseVo)iter.next();
		}
		return items;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EpiTreeDefiner getDefiner() {
		return definer;
	}

	public void setDefiner(EpiTreeDefiner treeDefiner) {
		this.definer = treeDefiner;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TreeViewer getViewer() {
		return viewer;
	}

	public void setViewer(TreeViewer viewer) {
		this.viewer = viewer;
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

	public void refresh() {
		insertedDataList.clear();
		deletedDataList.clear();
		updatedDataList.clear();
        revertToOriginalBackground();
		setDirty(false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public EpiTree getViewElement() {
		return this;
	}

	public IViewContext getViewContext() {
		return context;
	}

	public void seViewContext(IViewContext context) {
		this.context = context;
		viewer.setInput(context.getEditorInput());
	}
}
