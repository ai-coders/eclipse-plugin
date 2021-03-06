package net.aicoder.epi.base.view.action.tree;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;

import net.aicoder.epi.base.BaseImageConstant;
import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.control.tree.EpiTree;

public class EpiRefreshAction extends BaseAction {
	private static String actionText = "刷新记录";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_REFRESH);
	private static String toolTipText = "刷新所有记录!";

	private EpiTree epiTree;

	public EpiRefreshAction() {
		super(actionText, actionImageDescriptor);
		setToolTipText(toolTipText);
	}

	public EpiRefreshAction(String text, ImageDescriptor imageDescriptor) {
		super(text, imageDescriptor);
		setToolTipText(toolTipText);
	}

	public EpiRefreshAction(EpiTree tree) {
		super(actionText, actionImageDescriptor);
		setToolTipText(toolTipText);
		setTree(tree);
	}

	public void run() {
		TreeViewer viewer = epiTree.getViewer();
		//IViewDefiner definer = epiTree.getDefiner();
		IViewContext context = epiTree.getViewContext();
		IEpiInput input = context.getInput();

		IEpiInput newInput = doRefresh(input);
		if(newInput == null) {
			return;
		}
		epiTree.refresh();
		viewer.setInput(newInput);
		viewer.refresh();
	}

	protected IEpiInput doRefresh(IEpiInput adapter) {
		IEpiInput newAdapter = adapter;
		return newAdapter;
	}

	//// getter/setter
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
