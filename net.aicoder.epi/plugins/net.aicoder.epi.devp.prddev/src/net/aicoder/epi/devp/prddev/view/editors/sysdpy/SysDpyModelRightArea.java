package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.view.element.area.BaseArea;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;

/**
 * 部署模型-子区域-右边区域
 * @author WANGQINGPING
 *
 */
public class SysDpyModelRightArea extends BaseArea{
	public static final String ID = SysDpyModelRightArea.class.getName();
	private SysDpySchemeArea subRightUpArea; //上半区域
	private SysDpyCmpRefTable subRightDownArea; //下半区域

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		int[] areaWeights = new int[2];
		areaWeights[0] = 1;
		areaWeights[1] = 10;
		
		subRightUpArea = new SysDpySchemeArea();
		subRightDownArea = new SysDpyCmpRefTable();
		IEpiArea[] epiAreas = new IEpiArea[2];
		epiAreas[0] = subRightUpArea;
		epiAreas[1] = subRightDownArea;
		
		EpiSashArea sashArea = new EpiSashArea(getWorkbenchPart(), SWT.VERTICAL);
		sashArea.setEpiAreas(epiAreas);
		sashArea.setAreaWeights(areaWeights);
		
		sashArea.createControl(parent);
		
		return sashArea.getControl();
	}
	
}
