package net.aicoder.epi.base.view.definer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;

import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.tcom.tools.util.AiStringUtil;

public abstract class ViewDefiner implements IViewDefiner {
	private Object[][] viewDefine;
	private Object[][] columnsDefine;
	
	private Map<String, ViewItemDefiner> viewDefineMap = new HashMap<String, ViewItemDefiner>(0);
	private List<IColumnDefiner> columnDefinerList = new ArrayList<IColumnDefiner>(0);
	private Map<String, IColumnDefiner> columnDefineMap = new HashMap<String, IColumnDefiner>(0);
	private Map<String, String[]> cellEditorItemsMap = new HashMap<String, String[]>(0);

	private boolean hasOpenEditAction = false;

	private IContentProvider contentProvider;
	private ILabelProvider labelProvider;
	private ITableLabelProvider columnLabelProvider;

	//private IEpiInput input;
	//private IEpiEditorInput editorInput;

	public ViewDefiner() {
		super();
	}

	public ViewDefiner(Object[][] viewDefine) {
		super();
		setViewDefine(viewDefine);
	}
	
	public ViewDefiner(Object[][] viewDefine,Object[][] columnsDefine) {
		super();
		setViewDefine(viewDefine);
		setColumnsDefine(columnsDefine);
	}

	@Override
	public IViewItemDefiner getViewItemDefiner(String etype) {
		return viewDefineMap.get(etype);
	}

	@Override
	public Object[][] getViewDefine() {
		return viewDefine;
	}

	public void setViewDefine(Object[][] viewDefine) {
		this.viewDefine = viewDefine;
		parserViewDefine(viewDefine);
	}
	
	@Override
	public Object[][] getColumnsDefine() {
		return columnsDefine;
	}

	@Override
	public void setColumnsDefine(Object[][] columnsDefine) {
		this.columnsDefine = columnsDefine;
		parserColumnsDefine(columnsDefine);
	}
	
	@Override
	public IColumnDefiner getColumnDefinerByName(String columnName) {
		IColumnDefiner columnDefiner = null;
		columnDefiner = this.columnDefineMap.get(columnName);
		return columnDefiner;
	}
	
	@Override
	public IColumnDefiner getColumnDefinerByCode(String columnCode) {
		IColumnDefiner columnDefiner = null;
		if(columnCode == null) {
			return columnDefiner;
		}
		for(IColumnDefiner oneDefiner:columnDefinerList) {
			if(columnCode.equals(oneDefiner.getDataName())) {
				columnDefiner = oneDefiner;
				break;
			}
		}
		return columnDefiner;
	}


	@Override
	public boolean hasOpenEditAction() {
		return hasOpenEditAction;
	}

	@Override
	public IContentProvider getContentProvider() {
		return contentProvider;
	}

	@Override
	public void setContentProvider(IContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}

	@Override
	public ILabelProvider getLabelProvider() {
		return labelProvider;
	}

	@Override
	public void setLabelProvider(ILabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	@Override
	public ITableLabelProvider getColumnLabelProvider() {
		return columnLabelProvider;
	}

	@Override
	public void setColumnLabelProvider(ITableLabelProvider columnLabelProvider) {
		this.columnLabelProvider = columnLabelProvider;
	}

	@Override
	public List<IColumnDefiner> getColumnDefinerList() {
		return columnDefinerList;
	}
/**
	@Override
	public IEpiInput getInput() {
		return input;
	}

	@Override
	public void setInput(IEpiInput adapter) {
		this.input = adapter;
	}

	@Override
	public IEpiEditorInput getEditorInput() {
		return editorInput;
	}

	@Override
	public void setEditorInput(IEpiEditorInput editorInput) {
		this.editorInput = editorInput;
	}
**/	
	@Override
	public void putCellEditorItems(String columnName, String[] cellEditorItems) {
		cellEditorItemsMap.put(columnName, cellEditorItems);
	}
	
	@Override
	public String[] getCellEditorItems(String columnName) {
		return cellEditorItemsMap.get(columnName);
	}

	private void parserViewDefine(Object[][] viewDefine) {
		if (viewDefine == null) {
			return;
		}
		for (Object[] viewItemDefine : viewDefine) {
			ViewItemDefiner viewItemDefiner = new ViewItemDefiner(viewItemDefine);
			if (viewItemDefiner != null) {
				String etype = viewItemDefiner.getEtype();
				viewDefineMap.put(etype, viewItemDefiner);
				String editorId = viewItemDefiner.getEditorId();
				if (!AiStringUtil.isEmpty(editorId)) {
					hasOpenEditAction = true;
				}
			}
		}
	}
	
	private void parserColumnsDefine(Object[][] columnsDefine) {
		if (columnsDefine == null) {
			return;
		}
		int columnIndex = 0;
		for (Object[] columnDefine : columnsDefine) {
			IColumnDefiner columnDefiner = new ColumnDefiner(columnDefine);
			if (columnDefiner != null) {
				columnDefiner.setColumnIndex(columnIndex++);
				columnDefinerList.add(columnDefiner);
				String columnName = columnDefiner.getColumnName();
				columnDefineMap.put(columnName, columnDefiner);
			}
		}
	}

}
