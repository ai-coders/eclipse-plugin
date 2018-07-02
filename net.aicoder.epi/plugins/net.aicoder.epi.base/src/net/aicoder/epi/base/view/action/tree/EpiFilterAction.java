package net.aicoder.epi.base.view.action.tree;

import org.eclipse.jface.resource.ImageDescriptor;

import net.aicoder.epi.base.BaseImageConstant;
import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.element.tree.EpiTree;

/**
 * Tree 过滤 action
 * @author WANGQINGPING
 *
 */
public class EpiFilterAction extends BaseAction{
	private static String actionText = "过滤";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_FILTER);
	private static ImageDescriptor actionImageDescriptorDis = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_FILTER);
	private static String toolTipText = "过滤";
	private static String visibleWhenCount = "1";
	private EpiTree tree;

	
	public EpiFilterAction() {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}

	public EpiFilterAction(String text, ImageDescriptor imageDescriptor) {
		super(actionText, actionImageDescriptor);
		setToolTipText(toolTipText);
	}
	

	public EpiFilterAction(EpiTree epiTree) {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
		this.setTree(epiTree);
	}
	
	@Override
	public void run() {
		//filter action process
//		MessageDialog messageDialog = new MessageDialog(parentShell, dialogTitle, dialogTitleImage, dialogMessage, dialogImageType, dialogButtonLabels, defaultIndex)
		
		doFilterAction();
		tree.getViewer().refresh();
	}


	protected void doFilterAction() {
		// TODO Auto-generated method stub
		
	}

	public EpiTree getTree() {
		return tree;
	}


	public void setTree(EpiTree tree) {
		this.tree = tree;
		tree.getViewer();
	}
	

	
	
}
