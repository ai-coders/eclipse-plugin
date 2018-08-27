package net.aicoder.epi.base.view.action.table;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;

import net.aicoder.epi.base.BaseImageConstant;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.control.table.EpiTable;

public class EpiDownRowAction extends BaseAction {
	private static String actionText = "记录下移";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_DOWN);
	private static ImageDescriptor actionImageDescriptorDis = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_DOWN_DIS);
	private static String toolTipText = "记录下移!";
	
	private static String visibleWhenCount = "1";
	private EpiTable epiTable;

	public EpiDownRowAction() {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}
	
	public EpiDownRowAction(String text, ImageDescriptor imageDescriptor) {
		super(text, imageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}

	public EpiDownRowAction(EpiTable table) {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
		this.setTable(table);
	}

	public void run() {
		TableViewer viewer = epiTable.getViewer();
		//IViewDefiner definer = epiTree.getDefiner();

		IBaseVo currData = epiTable.getFirstSelectedItem();
		if (currData == null) {
			return;
		}
		
		doDownRow(currData);
		viewer.refresh();
		
//		StructuredSelection newSelection;
//		newSelection = new StructuredSelection(newData);
//		viewer.setSelection(newSelection);
		//epiTree.setDirtyBackground(0);

		//newData.setPreItemData(currData);
		//epiTree.putInsertedData(newData);
	}
	
	protected void doDownRow(IBaseVo currData) {
		IEpiInput input = (IEpiInput) epiTable.getViewer().getInput();
		List<IBaseVo> dataList = input.getDataList();
		int indexOf = dataList.indexOf(currData);
		if(indexOf < dataList.size()-1) {
			dataList.remove(currData);
			dataList.add(indexOf+1, currData);
		}
	}

	//// getter/setter
	public EpiTable getTable() {
		return epiTable;
	}

	public void setTable(EpiTable table) {
		this.epiTable = table;
		
		TableViewer viewer = table.getViewer();
		
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
