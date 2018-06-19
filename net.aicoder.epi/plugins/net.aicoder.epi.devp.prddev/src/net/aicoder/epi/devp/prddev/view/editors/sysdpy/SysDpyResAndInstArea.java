package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.view.element.area.BaseArea;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;

/**
 * 部署模型-子区域-左边-下边区域
 * @author WANGQINGPING
 *
 */
public class SysDpyResAndInstArea extends BaseArea{
	public static final String ID = SysDpyResAndInstArea.class.getName();
	private SysDpyResourcesTable subLeftDownAssociateArea;
	private SysDpyResInstanceTreeTable subLeftDownInstanceArea;

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		int[] areaWeights = new int[2];
		areaWeights[0] = 1;
		areaWeights[1] = 1;
		
		subLeftDownAssociateArea = new SysDpyResourcesTable();
		subLeftDownInstanceArea = new SysDpyResInstanceTreeTable();
		IEpiArea[] epiAreas = new IEpiArea[2];
		epiAreas[0] = subLeftDownAssociateArea;
		epiAreas[1] = subLeftDownInstanceArea;
		
		EpiSashArea sashArea = new EpiSashArea(getWorkbenchPart(), SWT.HORIZONTAL);
		sashArea.setEpiAreas(epiAreas);
		sashArea.setAreaWeights(areaWeights);
		
		sashArea.createControl(parent);

		return sashArea.getControl();
	}

}
