package net.aicoder.epi.base.view.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;

public interface IEpiAction extends IAction {
	/**
	 * 元素选择的含义：
	 * * = 任意数量的元素;
	 * ? = 0个或1个元素;
	 * + = 一个或多个元素;
	 * ! = 没有元素;
	 * @return VisibleWhenCount Value
	 */
	public String getVisibleWhenCount();
	
	public void setVisibleWhenCount(String visibleWhenCount);
	
	public boolean isCheckVisibleWhenCount();
	
	public void setEnabledByCount(IStructuredSelection selection);
}
