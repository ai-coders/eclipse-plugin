package net.aicoder.epi.base.view.element.table;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Item;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.IViewElement;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.definer.IViewDefiner;
import net.aicoder.tcom.tools.util.BeanUtil;

public class EpiCellModifier implements ICellModifier {
	IViewElement viewElement;
	
    protected boolean canModify = false;
    protected boolean isModifying = false;
	
    public EpiCellModifier(IViewElement viewElement){
    	this.viewElement = viewElement;
    }
    
/**
    public EpiCellModifier(IViewDefiner viewDefiner,StructuredViewer viewer){
		this.viewDefiner = viewDefiner;
		this.viewer = viewer;
	}
**/
    
    public void setCanModify(boolean canModify)
    {
        this.canModify = canModify;
    }

	@Override
	public boolean canModify(Object element, String property) {
		if (!canModify) { // 从datatools抄来的
			//return false;
		}

		boolean isCanModify = false;
		if (element != null) {
			IViewDefiner viewDefiner = viewElement.getDefiner();
			IColumnDefiner columnDefiner = viewDefiner.getColumnDefiner(property);
			if (columnDefiner != null) {
				isCanModify = columnDefiner.isEditable();
			}
		}
		return isCanModify;
	}

	@Override
	public Object getValue(Object element, String property) {
		Object value = "";
		IViewDefiner viewDefiner = viewElement.getDefiner();
		if(element != null) {
			IColumnDefiner columnDefiner = viewDefiner.getColumnDefiner(property);
			if(columnDefiner!=null) {
				try {
					value = BeanUtil.getPropertyValue(element, columnDefiner.getDataName());
					if(value == null) {
						value = "";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	@Override
	public void modify(Object element, String property, Object value) {
		if (isModifying) {
			// return; // Protect against infinite recursion, bug
		}
		IViewDefiner viewDefiner = viewElement.getDefiner();
		StructuredViewer viewer = viewElement.getViewer();
		boolean isDirty = false;
		
		try {
			isModifying = true;

			if (element != null) {
				Item tableItem = (Item) element;
				Object data = tableItem.getData();
				IColumnDefiner columnDefiner = viewDefiner.getColumnDefiner(property);
				if (columnDefiner != null) {
					String propertyName = columnDefiner.getDataName();
					if (data instanceof IBaseVo) {
						isDirty = ((IBaseVo)data).putPropertyValue(propertyName, value);
						if(isDirty) {
							viewElement.setDirtyBackground(property);
							viewElement.putUpdatedData((IBaseVo)data);
							viewer.refresh(data);
						}
					} else {
						try {
							Object oldValue = BeanUtil.getPropertyValue(data, propertyName);
							if (oldValue == null) {
								if (value != null) {
									isDirty = true;
								}
							} else {
								if (!oldValue.equals(value)) {
									isDirty = true;
								}
							}
							if (isDirty) {
								BeanUtil.setPropertyValue(data, propertyName, value);
								viewer.refresh(data);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} finally {
			isModifying = false;
		}
	}
	
	public IViewElement getViewElement() {
		return viewElement;
	}

	public void setViewElement(IViewElement viewElement) {
		this.viewElement = viewElement;
	}
}
