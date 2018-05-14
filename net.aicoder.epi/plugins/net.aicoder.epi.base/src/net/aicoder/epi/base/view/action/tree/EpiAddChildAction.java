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
import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.adapter.EpiInput;
import net.aicoder.epi.base.view.definer.IViewDefiner;
import net.aicoder.epi.base.view.element.tree.EpiTree;
import net.aicoder.epi.base.view.element.tree.EpiTreeContentProvider;

public class EpiAddChildAction extends BaseAction {
	private static String actionText = "添加子记录";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant
			.getImageDescriptor(BaseImageConstant.A_ADD_CHILD);
	private static String toolTipText = "新增子记录!";
	
	private EpiTree epiTree;

	public EpiAddChildAction() {
		super(actionText, actionImageDescriptor);
		setToolTipText(toolTipText);
	}
	
	public EpiAddChildAction(String text, ImageDescriptor imageDescriptor) {
		super(text, imageDescriptor);
		setToolTipText(toolTipText);
	}

	public EpiAddChildAction(EpiTree tree) {
		super(actionText, actionImageDescriptor);
		setToolTipText(toolTipText);
		setTree(tree);
	}

	public void run() {
		TreeViewer viewer = epiTree.getViewer();
		IViewDefiner definer = epiTree.getDefiner();
		
		IBaseVo currData = epiTree.getFirstSelectedItem();
		if (currData == null) {
			Object input = viewer.getInput();
			Object[] rootNodes = ((EpiTreeContentProvider) definer.getContentProvider()).getElements(input);
			if (rootNodes != null) {
				currData = (IBaseVo) rootNodes[0];
			}
		}
		
		IBaseVo newData = doAddChildNode(currData);
		if(newData == null) {
			return;
		}
		
		StructuredSelection newSelection;
		
		if(currData == null) {
			EpiInput adpapter = new EpiInput(newData);
			List<IBaseVo> dataList = new ArrayList<IBaseVo>(0);
			dataList.add(newData);
			adpapter.setDataList(dataList);
			viewer.setInput(adpapter);
			newSelection = new StructuredSelection(newData);
			epiTree.setDirtyBackground(0);
			viewer.refresh();
		}else {
			viewer.setExpandedState(currData, true);
			newSelection = new StructuredSelection(newData);
			viewer.setSelection(newSelection);
			epiTree.setDirtyBackground(0);
			viewer.refresh(currData);
		}
		epiTree.putInsertedData(newData);
	}
	
	protected IBaseVo doAddChildNode(IBaseVo parentData) {
		IBaseVo newData = null;
		return newData;
	}

	//// getter/setter
	public EpiTree getTree() {
		return epiTree;
	}

	public void setTree(EpiTree epiTree) {
		this.epiTree = epiTree;
		
		TreeViewer viewer = epiTree.getViewer();
		
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
}
