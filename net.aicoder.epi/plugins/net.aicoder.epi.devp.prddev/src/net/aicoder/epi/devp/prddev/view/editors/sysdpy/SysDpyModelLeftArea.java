package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.view.element.area.BaseArea;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;

/**
 * 部署模型-子区域-左边区域
 * @author WANGQINGPING
 *
 */
public class SysDpyModelLeftArea extends BaseArea{
	public static final String ID = SysDpyModelLeftArea.class.getName();
	private SysDpyCmpTreeTable subLeftUpArea; //上半区域
	private SysDpyResAndInstArea subLeftDownArea; //下半区域
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	@Override
	protected Control createAreaControl(Composite parent) {
//		int[] areaWeights = new int[2];
//		areaWeights[0] = 1;
//		areaWeights[1] = 1;
		
		subLeftUpArea = new SysDpyCmpTreeTable();
		subLeftDownArea = new SysDpyResAndInstArea();
		IEpiArea[] epiAreas = new IEpiArea[2];
		epiAreas[0] = subLeftUpArea;
		epiAreas[1] = subLeftDownArea;
		
		EpiSashArea sashArea = new EpiSashArea(getWorkbenchPart(), SWT.VERTICAL);
		sashArea.setEpiAreas(epiAreas);
//		sashArea.setAreaWeights(areaWeights);
		
		sashArea.createControl(parent);
		
		return sashArea.getControl();
	}

}
