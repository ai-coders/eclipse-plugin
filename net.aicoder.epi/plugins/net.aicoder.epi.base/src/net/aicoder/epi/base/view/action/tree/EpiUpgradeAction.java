package net.aicoder.epi.base.view.action.tree;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;

import net.aicoder.epi.base.BaseImageConstant;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.TreeNodeVo;
import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.control.tree.EpiTree;
import net.aicoder.tcom.tools.util.BeanUtil;

/**
 * 节点升级
 * @author WANGQINGPING
 *
 */
public class EpiUpgradeAction extends BaseAction{
	private static String actionText = "节点升级";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_UPGRADE);
	private static ImageDescriptor actionImageDescriptorDis = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_UPGRADE_DIS);
	private static String toolTipText = "节点升级!";

	private static String visibleWhenCount = "1";
	private EpiTree epiTree;
	
	public EpiUpgradeAction() {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}
	
	public EpiUpgradeAction(String text, ImageDescriptor imageDescriptor) {
		super(text, imageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}

	public EpiUpgradeAction(EpiTree tree) {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
		this.setTree(tree);
	}
	
	@Override
	public void run() {
		//升级
		TreeViewer viewer = epiTree.getViewer();
		IBaseVo[] currDatas = epiTree.getSelectedItems();
		if (currDatas == null) {
			return;
		}
		
		doUpgradeAction(currDatas);
		viewer.refresh();
		
		for (IBaseVo iBaseVo : currDatas) {
			epiTree.putUpdatedData(iBaseVo);
		}
		
//		StructuredSelection newSelection;
//		newSelection = new StructuredSelection(newData);
//		viewer.setSelection(newSelection);
	}
	
	protected void doUpgradeAction(IBaseVo[] currDatas) {
		for (IBaseVo iBaseVo : currDatas) {
			TreeNodeVo currNode = (TreeNodeVo)iBaseVo;
			TreeNodeVo parentNode = currNode.getParentNode();
			if(iBaseVo instanceof TreeNodeVo && parentNode.getParentNode() != null) {//第三级
				TreeNodeVo dest = new TreeNodeVo();
				BeanUtil.copyBeanToBean(dest, currNode);
				dest.setEtype(parentNode.getEtype());
				dest.setParentNode(parentNode.getParentNode());
				parentNode.getParentNode().getChildrenList().add(dest);
				parentNode.getChildrenList().remove(currNode);
			}else if(iBaseVo instanceof TreeNodeVo && parentNode.getParentNode() == null) {//第二级
				TreeNodeVo dest = new TreeNodeVo();
				BeanUtil.copyBeanToBean(dest, currNode);
				dest.setEtype(parentNode.getEtype());
				dest.setParentNode(null);
				IEpiInput input = (IEpiInput) epiTree.getViewer().getInput();
				List<IBaseVo> dataList = input.getDataList();
				dataList.add(dest);
				currNode.getParentNode().getChildrenList().remove(currDatas);
			}
		}
	}

	public EpiTree getTree() {
		return epiTree;
	}

	public void setTree(EpiTree epiTree) {
		this.epiTree = epiTree;
		TreeViewer viewer = epiTree.getViewer();

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
