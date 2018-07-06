package net.aicoder.epi.base.view.action.tree;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;

import net.aicoder.epi.base.BaseImageConstant;
import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.control.tree.EpiTree;

public class EpiSaveAction extends BaseAction {
	private static String actionText = "保存";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant.getImageDescriptor(ISharedImages.IMG_ETOOL_SAVEALL_EDIT);
	private static ImageDescriptor actionImageDescriptorDis = BaseImageConstant.getImageDescriptor(ISharedImages.IMG_ETOOL_SAVEALL_EDIT_DISABLED);
	private static String toolTipText = "保存";
	private static String visibleWhenCount = "1";
	private EpiTree tree;
	
	public EpiSaveAction() {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}
	
	public EpiSaveAction(String actionText, ImageDescriptor actionImageDescriptor) {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}
	
	public EpiSaveAction(EpiTree epiTree) {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
		this.setTree(epiTree);
	}
	
	@Override
	public void run() {
		
		
		
		System.out.println("发起保存动作");
	}
	

	public EpiTree getTree() {
		return tree;
	}

	public void setTree(EpiTree tree) {
		this.tree = tree;
	}

	
}
