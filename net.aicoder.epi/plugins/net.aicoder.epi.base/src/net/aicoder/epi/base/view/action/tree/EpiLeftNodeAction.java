package net.aicoder.epi.base.view.action.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import net.aicoder.epi.base.BaseImageConstant;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.control.tree.EpiTree;
import net.aicoder.epi.base.view.control.tree.EpiTreeContentProvider;
import net.aicoder.epi.base.view.definer.IViewDefiner;

public class EpiLeftNodeAction extends BaseAction {
	private static String actionText = "节点左移";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_LEFT);
	private static ImageDescriptor actionImageDescriptorDis = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_LEFT_DIS);
	private static String toolTipText = "节点左移!";

	private static String visibleWhenCount = "1";
	private EpiTree epiTree;

	public EpiLeftNodeAction() {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}

	public EpiLeftNodeAction(String text, ImageDescriptor imageDescriptor) {
		super(text, imageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}

	public EpiLeftNodeAction(EpiTree tree) {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
		this.setTree(tree);
	}

	public void run() {
		TreeViewer viewer = epiTree.getViewer();
		// IViewDefiner definer = epiTree.getDefiner();

		IBaseVo currData = epiTree.getFirstSelectedItem();
		if (currData == null) {
			return;
		}

		IBaseVo newData = doLeftNode(currData);
		if (newData == null) {
			return;
		}
		viewer.refresh();

		StructuredSelection newSelection;
		newSelection = new StructuredSelection(newData);
		viewer.setSelection(newSelection);
		// epiTree.setDirtyBackground(0);

		// newData.setPreItemData(currData);
		// epiTree.putInsertedData(newData);
	}

	protected IBaseVo doLeftNode(IBaseVo currData) {
		IBaseVo newData = currData;

		if (currData instanceof ITreeNode) {
			ITreeNode parentData = ((ITreeNode) currData).getParentNode();
			if (parentData != null) {
				ITreeNode ppNode = parentData.getParentNode();
				if(ppNode != null && ppNode instanceof ITreeNode) {
					List<IBaseVo> childrenList = parentData.getChildrenList();
					childrenList.remove(currData);
					List<IBaseVo> ppChildrenList = ppNode.getChildrenList();
					((ITreeNode) currData).setParentNode(ppNode);
					ppChildrenList.add(currData);
				}
			} else {
				newData = null;
			}
		} else {
			newData = null;
		}
		return newData;
	}

	//// getter/setter
	public EpiTree getTree() {
		return epiTree;
	}

	public void setTree(EpiTree tree) {
		this.epiTree = tree;

		TreeViewer viewer = tree.getViewer();

		if (isCheckVisibleWhenCount() && viewer != null) {
			setEnabledByCount(null);
			viewer.addSelectionChangedListener(new ISelectionChangedListener() {
				public void selectionChanged(SelectionChangedEvent event) {
					IStructuredSelection selection = (IStructuredSelection) event.getSelection();
					setEnabledByCount(selection);
				}
			});
		}
	}
}
