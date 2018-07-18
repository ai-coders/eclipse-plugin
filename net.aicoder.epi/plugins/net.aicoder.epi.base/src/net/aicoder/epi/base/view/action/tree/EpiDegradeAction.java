package net.aicoder.epi.base.view.action.tree;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import net.aicoder.epi.base.BaseImageConstant;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.control.tree.EpiTree;

/**
 * 节点降级
 * @author WANGQINGPING
 *
 */
public class EpiDegradeAction extends BaseAction{
	private static String actionText = "节点降级";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_DEGRADE);
	private static ImageDescriptor actionImageDescriptorDis = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_DEGRADE_DIS);
	private static String toolTipText = "节点降级!";

	private static String visibleWhenCount = "1";
	private EpiTree epiTree;
	
	public EpiDegradeAction() {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}
	
	public EpiDegradeAction(String text, ImageDescriptor imageDescriptor) {
		super(text, imageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}

	public EpiDegradeAction(EpiTree tree) {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
		this.setTree(tree);
	}
	
	@Override
	public void run() {
		//降级
		TreeViewer viewer = epiTree.getViewer();
		IBaseVo currData = epiTree.getFirstSelectedItem();
		if (currData == null) {
			return;
		}

		IBaseVo newData = doDegradeAction(currData);
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
	
	protected IBaseVo doDegradeAction(IBaseVo currData) {
		return null;
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
