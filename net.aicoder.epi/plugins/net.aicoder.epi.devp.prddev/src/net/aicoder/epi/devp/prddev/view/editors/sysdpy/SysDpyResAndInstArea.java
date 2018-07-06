package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.view.part.area.BaseArea;
import net.aicoder.epi.base.view.part.area.SashArea;
import net.aicoder.epi.base.view.part.area.IArea;

/**
 * 部署模型-关联资源和资源实例(区域)
 * @author WANGQINGPING
 *
 */
public class SysDpyResAndInstArea extends BaseArea{
	public static final String ID = SysDpyResAndInstArea.class.getName();
	private SysDpyResourcesTable sysDpyResourcesTable;
	private SysDpyResInstanceTreeTable sysDpyResInstanceTreeTable;

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	@Override
	//protected Control createAreaControl(Composite parent) {
	public void assembleControl(Composite parent) {
		int[] areaWeights = new int[2];
		areaWeights[0] = 1;
		areaWeights[1] = 1;
		
		sysDpyResourcesTable = new SysDpyResourcesTable();
		sysDpyResInstanceTreeTable = new SysDpyResInstanceTreeTable();
		IArea[] epiAreas = new IArea[2];
		epiAreas[0] = sysDpyResourcesTable;
		epiAreas[1] = sysDpyResInstanceTreeTable;
		
		SashArea sashArea = new SashArea(getWorkbenchPart(), SWT.HORIZONTAL);
		sashArea.setAreas(epiAreas);
		sashArea.setWeights(areaWeights);
		sashArea.createControl(parent);

		//点选“关联资源”的记录时，维护当前关联资源的“资源实例”；
		//添加“资源实例”时，可多选所属的部署方案；
		//依据所选择的部署方案，显示缺省的资源实例；
		//选择“过滤”按钮进行其它部署方案的
		sysDpyResourcesTable.getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				//跳转到资源实例区域处理
				ISelection selection = event.getSelection();
				sysDpyResInstanceTreeTable.setSelection(selection);
			}
		});
		//return sashArea.getControl();
	}

	
	public SysDpyResourcesTable getSysDpyResourcesTable() {
		return sysDpyResourcesTable;
	}

	public void setSysDpyResourcesTable(SysDpyResourcesTable sysDpyResourcesTable) {
		this.sysDpyResourcesTable = sysDpyResourcesTable;
	}

	public SysDpyResInstanceTreeTable getSysDpyResInstanceTreeTable() {
		return sysDpyResInstanceTreeTable;
	}

	public void setSysDpyResInstanceTreeTable(SysDpyResInstanceTreeTable sysDpyResInstanceTreeTable) {
		this.sysDpyResInstanceTreeTable = sysDpyResInstanceTreeTable;
	}

}
