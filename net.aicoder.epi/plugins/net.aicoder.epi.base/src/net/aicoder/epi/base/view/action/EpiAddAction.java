package net.aicoder.epi.base.view.action;

import org.eclipse.jface.resource.ImageDescriptor;

import net.aicoder.epi.base.BaseImageConstant;

public class EpiAddAction extends BaseAction implements IEpiAction {
	private static String actionText = "添加";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant
			.getImageDescriptor(BaseImageConstant.A_ADD);
	private static String toolTipText = "新增记录!";

	public EpiAddAction() {
		super(actionText, actionImageDescriptor);
		setToolTipText(toolTipText);
	}

	public EpiAddAction(String text, ImageDescriptor imageDescriptor) {
		super(text, imageDescriptor);
		setToolTipText(toolTipText);
	}

	public void run() {

	}
	
	

}
