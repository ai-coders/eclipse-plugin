package net.aicoder.epi.base.view.action.table;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;

import net.aicoder.epi.base.BaseImageConstant;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.control.table.EpiTable;

/**
 * 列表-复制动作
 * @author WANGQINGPING
 *
 */
public class EpiCopyRowAction extends BaseAction{
	private static String actionText = "复制";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_COPY);
	private static ImageDescriptor actionImageDescriptorDis = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_COPY);
	private static String toolTipText = "复制!";
	private static String visibleWhenCount = "*";
	private EpiTable epiTable;
	
	
	private EpiCopyRowAction() {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}
	
	public EpiCopyRowAction(String actionText, ImageDescriptor actionImageDescriptor) {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}
	
	public EpiCopyRowAction(EpiTable epiTable) {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
		this.setTable(epiTable);
	}

	
	@Override
	public void run() {
		TableViewer viewer = epiTable.getViewer();
		//IViewDefiner definer = epiTree.getDefiner();

		IBaseVo currData = epiTable.getFirstSelectedItem();
		if (currData == null) {
			return;
		}
		doCopyAction(currData);
		viewer.refresh();
	}
	
	
	protected void doCopyAction(IBaseVo currData) {
		// TODO Auto-generated method stub
		
	}

	public EpiTable getTable() {
		return epiTable;
	}

	public void setTable(EpiTable epiTable) {
		this.epiTable = epiTable;
		TableViewer viewer = epiTable.getViewer();
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
