package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import net.aicoder.epi.base.view.part.area.BaseArea;
import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.base.view.part.area.SashArea;

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
