package net.aicoder.epi.base.view.action.tree;

import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;

import net.aicoder.epi.base.BaseImageConstant;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.control.tree.EpiTree;

public class EpiDeleteNodeAction extends BaseAction {
	private static String actionText = "删除";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_DELETE);
	private static ImageDescriptor actionImageDescriptorDis = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_DELETE_DIS);
	private static String toolTipText = "删除记录!";
	
	private static String visibleWhenCount = "+";
	private boolean askConfirmation = true;
	
	private EpiTree tree;

	public EpiDeleteNodeAction() {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}
	
	public EpiDeleteNodeAction(String text, ImageDescriptor imageDescriptor) {
		super(text, imageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}
	
	public EpiDeleteNodeAction(EpiTree tree) {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
		this.setTree(tree);
	}
	
	public void run() {
		TreeViewer viewer = tree.getViewer();
		//IViewDefiner definer = tree.getTreeDefiner();
		IBaseVo[] items = tree.getSelectedItems();
		if (items == null) {
			return;
		}

		int deleteNum = doDdeleteNode(items);
		if(deleteNum == 0) {
			return;
		}else {
			viewer.refresh();
			for(IBaseVo item:items) {
				tree.putDeletedData(item);
			}
		}
	}
	
	protected int doDdeleteNode(IBaseVo[] items) {
		int deleteNum = 0;
		if (items == null || items.length == 0) {
			return deleteNum;
		}

		TreeViewer viewer = tree.getViewer();
		if (askConfirmation) {
			String title = "记录删除";
			StringBuffer strBuffer = new StringBuffer();
			strBuffer.append("是否确认删除以下【" + items.length + "】条记录：\n");
			for (IBaseVo item : items) {
				strBuffer.append("[" + item.toString() + "] ");
			}

			boolean okay = MessageDialog.openQuestion(viewer.getControl().getShell(), title, strBuffer.toString());
			if (!okay) {
				return 0;
			}
		}
		for (IBaseVo item : items) {
			if (item instanceof ITreeNode) {
				ITreeNode node = (ITreeNode) item;
				ITreeNode parent = node.getParentNode();
				if (parent != null) {
					List<IBaseVo> childrenList = parent.getChildrenList();
					for (IBaseVo child : childrenList) {
						if (item.equals(child)) {
							childrenList.remove(child);
							deleteNum++;
							break;
						}
					}
				}
			}
		}
		return deleteNum;
	}
	
	//// getter/setter
	public EpiTree getTree() {
		return tree;
	}

	public void setTree(EpiTree tree) {
		this.tree = tree;
		TreeViewer viewer = tree.getViewer();
		
		if(isCheckVisibleWhenCount() && viewer != null) {
			setEnabledByCount(null);
			viewer.addSelectionChangedListener(new ISelectionChangedListener() {
				public void selectionChanged(SelectionChangedEvent event) {
					IStructuredSelection selection = (IStructuredSelection)event.getSelection();
					setEnabledByCount(selection);
				}
			});
		}
	}

	public boolean isAskConfirmation() {
		return askConfirmation;
	}

	public void setAskConfirmation(boolean askConfirmation) {
		this.askConfirmation = askConfirmation;
	}
}
