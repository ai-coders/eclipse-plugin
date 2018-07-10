package net.aicoder.epi.base.view.action.table;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;

import net.aicoder.epi.base.BaseImageConstant;
import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.control.table.EpiTable;

/**
 * 列表-添加动作
 * @author WANGQINGPING
 *
 */
public class EpiAddAction extends BaseAction{
	private static String actionText = "添加";
	private static ImageDescriptor actionImageDescriptor = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_ADD);
	private static ImageDescriptor actionImageDescriptorDis = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_ADD);
	private static String toolTipText = "添加!";
	private static String visibleWhenCount = "*";
	private EpiTable epiTable;
	
	
	private EpiAddAction() {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}
	
	public EpiAddAction(String actionText, ImageDescriptor actionImageDescriptor) {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
	}
	
	public EpiAddAction(EpiTable epiTable) {
		super(actionText, actionImageDescriptor);
		this.setVisibleWhenCount(visibleWhenCount);
		this.setDisabledImageDescriptor(actionImageDescriptorDis);
		this.setToolTipText(toolTipText);
		this.setTable(epiTable);
	}

	
	@Override
	public void run() {
		//process
//		ISelection selection = epiTable.getViewer().getSelection();
//		Object firstElement = ((StructuredSelection)selection).getFirstElement();

		doAddAction();
		epiTable.getViewer().refresh();
	}
	
	
	protected void doAddAction() {
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
