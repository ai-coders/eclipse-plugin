package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.control.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.part.area.BaseArea;
import net.aicoder.epi.base.view.part.area.SashArea;
import net.aicoder.epi.base.view.part.area.IArea;

/**
 * 部署模型-右边区域
 * @author WANGQINGPING
 *
 */
public class SysDpyModelRightArea extends SashArea { //BaseArea{
	public static final String ID = SysDpyModelRightArea.class.getName();
	private SysDpySchemeArea sysDpySchemeArea; //上半区域
	private SysDpyCmpRefTable sysDpyCmpRefTable; //下半区域
	
	public SysDpyModelRightArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}

	
	@Override
	public void initArea() {
		int[] weights = new int[2];
		weights[0] = 2;
		weights[1] = 1;

		sysDpySchemeArea = new SysDpySchemeArea(); //左边区域
		sysDpyCmpRefTable = new SysDpyCmpRefTable(); //右边区域
		IArea[] areas = new IArea[2];
		areas[0] = sysDpySchemeArea;
		areas[1] = sysDpyCmpRefTable;
		
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
		int[] areaWeights = new int[2];
		areaWeights[0] = 1;
		areaWeights[1] = 15;
		
		sysDpySchemeArea = new SysDpySchemeArea();
		sysDpyCmpRefTable = new SysDpyCmpRefTable();
		IArea[] epiAreas = new IArea[2];
		epiAreas[0] = sysDpySchemeArea;
		epiAreas[1] = sysDpyCmpRefTable;
		
		SashArea sashArea = new SashArea(getWorkbenchPart(), SWT.VERTICAL);
		sashArea.setAreas(epiAreas);
		sashArea.setWeights(areaWeights);
		
		sashArea.createControl(parent);
		
		return sashArea.getControl();
	}
**/
	
	
	public SysDpySchemeArea getSysDpySchemeArea() {
		return sysDpySchemeArea;
	}

	public void setSysDpySchemeArea(SysDpySchemeArea sysDpySchemeArea) {
		this.sysDpySchemeArea = sysDpySchemeArea;
	}

	public SysDpyCmpRefTable getSysDpyCmpRefTable() {
		return sysDpyCmpRefTable;
	}

	public void setSysDpyCmpRefTable(SysDpyCmpRefTable sysDpyCmpRefTable) {
		this.sysDpyCmpRefTable = sysDpyCmpRefTable;
	}

	
}
