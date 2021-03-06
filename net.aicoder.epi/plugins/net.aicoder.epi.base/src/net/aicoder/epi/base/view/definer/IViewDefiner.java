package net.aicoder.epi.base.view.definer;

import java.util.List;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;

public interface IViewDefiner {
	
	public IViewItemDefiner getViewItemDefiner(String etype);

	public Object[][] getViewDefine();

	public void setViewDefine(Object[][] viewDefine);
	
	public Object[][] getColumnsDefine();
	
	public void setColumnsDefine(Object[][] columnsDefine);
	
	public IColumnDefiner getColumnDefinerByName(String columnName);
	
	public IColumnDefiner getColumnDefinerByCode(String columnCode);

	public IContentProvider getContentProvider();

	public void setContentProvider(IContentProvider contentProvider);

	public ILabelProvider getLabelProvider();

	public void setLabelProvider(ILabelProvider labelProvider);
	
	public ITableLabelProvider getColumnLabelProvider();
	
	public void setColumnLabelProvider(ITableLabelProvider columnLabelProvider);
	
	public List<IColumnDefiner> getColumnDefinerList();

	public boolean hasOpenEditAction();
	
	public void putCellEditorItems(String columnName, String[] cellEditorItems);
	
	public String[] getCellEditorItems(String columnName);
	
	//public IEpiInput getInput();

	//public void setInput(IEpiInput input);
	
	//public IEpiEditorInput getEditorInput();
	
	//public void setEditorInput(IEpiEditorInput editorInput);
	
	//public IEpiInput createInput(IBaseVo selectionElement);
	
	//public IEpiEditorInput createEditorInput(IBaseVo selectionElement);

}