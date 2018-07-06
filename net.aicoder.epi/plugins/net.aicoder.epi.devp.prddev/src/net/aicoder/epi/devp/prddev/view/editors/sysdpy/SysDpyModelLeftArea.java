package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.part.area.BaseArea;
import net.aicoder.epi.base.view.part.area.SashArea;
import net.aicoder.epi.base.view.part.area.IArea;

/**
 * 部署模型-左边区域
 * @author WANGQINGPING
 *
 */
public class SysDpyModelLeftArea extends SashArea { //BaseArea{
	public static final String ID = SysDpyModelLeftArea.class.getName();
	private SysDpyCmpTreeTable sysDpyCmpTreeTable;
	private SysDpyResAndInstArea sysDpyResAndInstArea;
	
	public SysDpyModelLeftArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}

	@Override
	public void initArea() {
		int[] weights = new int[2];
		weights[0] = 2;
		weights[1] = 1;

		sysDpyCmpTreeTable = new SysDpyCmpTreeTable(); //左边区域
		sysDpyResAndInstArea = new SysDpyResAndInstArea(); //右边区域
		IArea[] areas = new IArea[2];
		areas[0] = sysDpyCmpTreeTable;
		areas[1] = sysDpyResAndInstArea;
		
		this.areas = areas;
		this.weights = weights;
		this.fixedOrientation = SWT.VERTICAL;
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
	}

/**	
	@Override
	protected Control createAreaControl(Composite parent) {
		sysDpyCmpTreeTable = new SysDpyCmpTreeTable();
		sysDpyResAndInstArea = new SysDpyResAndInstArea();
		IArea[] epiAreas = new IArea[2];
		epiAreas[0] = sysDpyCmpTreeTable;
		epiAreas[1] = sysDpyResAndInstArea;
		
		//EpiSashArea sashArea = new EpiSashArea(getWorkbenchPart(), SWT.VERTICAL);
		SashArea sashArea = new SashArea(getWorkbenchPart(), SWT.VERTICAL);
		sashArea.setAreas(epiAreas);
//		sashArea.setAreaWeights(areaWeights);
		sashArea.createControl(parent);
		
		return sashArea.getControl();
	}
**/
	
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
