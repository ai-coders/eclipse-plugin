package net.aicoder.epi.base.view.action.table;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;

import net.aicoder.epi.base.BaseImageConstant;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.control.table.EpiTable;

/**
 * 列表-删除动作
 * @author WANGQINGPING
 *
 */
public class EpiDeleteAction extends BaseAction{
	private static String actionText = "删除";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_DELETE);
	private static ImageDescriptor actionImageDescriptorDis = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_DELETE_DIS);
	private static String toolTipText = "删除!";
	private static String visibleWhenCount = "1";
	private EpiTable epiTable;
	
	
	private EpiDeleteAction() {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}
	
	public EpiDeleteAction(String actionText, ImageDescriptor actionImageDescriptor) {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}
	
	public EpiDeleteAction(EpiTable epiTable) {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
		this.setTable(epiTable);
	}

	
	@Override
	public void run() {
		//process
		ISelection selection = epiTable.getViewer().getSelection();
		IBaseVo firstElement = (IBaseVo) ((StructuredSelection)selection).getFirstElement();

		doDeleteAction(firstElement);
		epiTable.getViewer().refresh();

	}
	
	
	protected void doDeleteAction(IBaseVo baseVo) {
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
