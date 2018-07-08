package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.view.element.area.BaseArea;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;

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
	protected Control createAreaControl(Composite parent) {
		int[] areaWeights = new int[2];
		areaWeights[0] = 1;
		areaWeights[1] = 1;
		
		sysDpyResourcesTable = new SysDpyResourcesTable();
		sysDpyResInstanceTreeTable = new SysDpyResInstanceTreeTable();
		IEpiArea[] epiAreas = new IEpiArea[2];
		epiAreas[0] = sysDpyResourcesTable;
		epiAreas[1] = sysDpyResInstanceTreeTable;
		
		EpiSashArea sashArea = new EpiSashArea(getWorkbenchPart(), SWT.HORIZONTAL);
		sashArea.setEpiAreas(epiAreas);
		sashArea.setAreaWeights(areaWeights);
		sashArea.createControl(parent);

		//点选“关联资源”的一条记录时，传递到关联资源的“资源应用场景区域展示该关联资源内的资源实例数据”
		getSysDpyResourcesTable().getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				//事件传递到资源应用场景区域处理
				ISelection selection = event.getSelection();
				getSysDpyResInstanceTreeTable().setSelection(selection);
			}
		});
		return sashArea.getControl();
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
