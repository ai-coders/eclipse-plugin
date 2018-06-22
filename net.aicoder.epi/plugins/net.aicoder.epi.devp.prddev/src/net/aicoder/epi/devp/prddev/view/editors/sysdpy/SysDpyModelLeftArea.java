package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.view.element.area.BaseArea;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;

/**
 * 部署模型-左边区域
 * @author WANGQINGPING
 *
 */
public class SysDpyModelLeftArea extends BaseArea{
	public static final String ID = SysDpyModelLeftArea.class.getName();
	private SysDpyCmpTreeTable sysDpyCmpTreeTable;
	private SysDpyResAndInstArea sysDpyResAndInstArea;
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	@Override
	protected Control createAreaControl(Composite parent) {
	
//		int[] areaWeights = new int[2];
//		areaWeights[0] = 1;
//		areaWeights[1] = 1;
		
		
		sysDpyCmpTreeTable = new SysDpyCmpTreeTable();
		sysDpyResAndInstArea = new SysDpyResAndInstArea();
		IEpiArea[] epiAreas = new IEpiArea[2];
		epiAreas[0] = sysDpyCmpTreeTable;
		epiAreas[1] = sysDpyResAndInstArea;
		
		EpiSashArea sashArea = new EpiSashArea(getWorkbenchPart(), SWT.VERTICAL);
		sashArea.setEpiAreas(epiAreas);
//		sashArea.setAreaWeights(areaWeights);
		sashArea.createControl(parent);
		
		return sashArea.getControl();
	}

	
	public SysDpyCmpTreeTable getSysDpyCmpTreeTable() {
		return sysDpyCmpTreeTable;
	}

	public void setSysDpyCmpTreeTable(SysDpyCmpTreeTable sysDpyCmpTreeTable) {
		this.sysDpyCmpTreeTable = sysDpyCmpTreeTable;
	}

	public SysDpyResAndInstArea getSysDpyResAndInstArea() {
		return sysDpyResAndInstArea;
	}

	public void setSysDpyResAndInstArea(SysDpyResAndInstArea sysDpyResAndInstArea) {
		this.sysDpyResAndInstArea = sysDpyResAndInstArea;
	}

}
