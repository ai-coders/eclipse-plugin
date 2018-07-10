package net.aicoder.epi.base.view.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import net.aicoder.tcom.tools.util.AiStringUtil;

public abstract class BaseAction extends Action implements IEpiAction {
	/**
	 * 元素选择的含义： * = 任意数量的元素; ? = 0个或1个元素; + = 一个或多个元素; ! = 没有元素;
	 */
	protected String visibleWhenCount = "";

	public BaseAction(String actionText, ImageDescriptor actionImageDescriptor) {
		super(actionText, actionImageDescriptor);
	}
	
	public void setEnabledByCount(IStructuredSelection selection) {
		boolean enabled = checkEnabledByCount(selection);
		super.setEnabled(enabled);
	}
	
	private boolean checkEnabledByCount(IStructuredSelection selection) {
		boolean enabled = false;
		int selectionNum = 0;
		if (selection == null) {
			selectionNum = 0;
		} else {
			selectionNum = selection.size();
		}
		switch (visibleWhenCount) {
		case "*":
			enabled = true;
			break;
		case "?":
			enabled = (selectionNum == 0 || selectionNum == 1) ? true : false;
			break;
		case "+":
			enabled = (selectionNum > 0 ) ? true : false;
			break;
		case "!":
			enabled = (selectionNum == 0 ) ? true : false;
			break;
		default:
			try {
				int whenCount = Integer.parseInt(visibleWhenCount);
				enabled = (selectionNum == whenCount ) ? true : false;
			}catch (NumberFormatException e) {  
				enabled = true;
			}
			break;
		}
		return enabled;
	}
	
	@Override
	public boolean isCheckVisibleWhenCount() {
		boolean isVisibleWhenCount = false;
		if(AiStringUtil.isEmpty(visibleWhenCount)) {
			isVisibleWhenCount = false;
		}else {
			switch (visibleWhenCount){
				case "*":
					isVisibleWhenCount = false;
					break;
				default:
					isVisibleWhenCount = true;
					break;
			}
		}
		return isVisibleWhenCount;
	}

	//// getter/setter
	@Override
	public String getVisibleWhenCount() {
		return visibleWhenCount;
	}

	@Override
	public void setVisibleWhenCount(String visibleWhenCount) {
		this.visibleWhenCount = visibleWhenCount;
	}
}
