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
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.definer.IViewDefiner;
import net.aicoder.epi.base.view.element.tree.EpiTree;
import net.aicoder.epi.base.view.element.tree.EpiTreeContentProvider;

public class EpiAddBrotherAction extends BaseAction {
	private static String actionText = "添加记录";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant
			.getImageDescriptor(BaseImageConstant.A_ADD_ROW);
	private static String toolTipText = "新增兄弟记录!";
	
	private EpiTree epiTree;

	public EpiAddBrotherAction() {
		super(actionText, actionImageDescriptor);
		setToolTipText(toolTipText);
	}
	
	public EpiAddBrotherAction(String text, ImageDescriptor imageDescriptor) {
		super(text, imageDescriptor);
		setToolTipText(toolTipText);
	}

	public EpiAddBrotherAction(EpiTree tree) {
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
			if (rootNodes != null && rootNodes.length > 0) {
				currData = (IBaseVo) rootNodes[0];
			}
		}
		
		IBaseVo newData = doAddBrotherRow(currData);
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
			viewer.refresh();
		}else {
			viewer.refresh();
		}
		newSelection = new StructuredSelection(newData);
		viewer.setSelection(newSelection);
		epiTree.setDirtyBackground(0);

		newData.setPreItemData(currData);
		epiTree.putInsertedData(newData);
	}
	
	protected IBaseVo doAddBrotherRow(IBaseVo currData) {
		IBaseVo newData = null;
		return newData;
	}

	//// getter/setter
	public EpiTree getTree() {
		return epiTree;
	}

	public void setTree(EpiTree tree) {
		this.epiTree = tree;
		
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
}
