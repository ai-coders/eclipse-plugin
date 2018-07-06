package net.aicoder.epi.base.view.part;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.TreeItem;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.definer.IViewDefiner;

public interface IViewElement {
	public <T extends IViewElement> T getViewElement();

	public <T extends IViewDefiner> T getDefiner();
	
	public <T extends StructuredViewer> T getViewer();
	
	public boolean isEditable();
	
	public void setEditable(boolean editable);
	
	public boolean isDirty();
	
	public void setDirty(boolean dirty);
	
	//public TreeItem getSelectedTreeItem();
	
	public IBaseVo getFirstSelectedItem();
	
	public IBaseVo[] getSelectedItems();
	
	public void setDirtyBackground(String property);
	
	public void setDirtyBackground(int columnIndex);
	
	public void putInsertedData(IBaseVo item);
	
	public void putDeletedData(IBaseVo item);
	
	public void putUpdatedData(IBaseVo item);
	
	

}