package net.aicoder.epi.base.view.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import net.aicoder.epi.base.BaseImageConstant;

public class EpiDeleteAction extends BaseAction {
	private static String actionText = "删除";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_DELETE);
	private static ImageDescriptor actionImageDescriptorDis = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_DELETE_DIS);
	private static String toolTipText = "删除记录!";
	
	private static String visibleWhenCount = "+";
	
	private boolean askConfirmation;

	public EpiDeleteAction() {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		setToolTipText(toolTipText);
	}
	
	public EpiDeleteAction(String text, ImageDescriptor imageDescriptor) {
		super(text, imageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		setToolTipText(toolTipText);
	}
	
	public void run() {
		
	}

}
