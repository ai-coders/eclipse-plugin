package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.element.area.BaseWithPropArea;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;

/**
 * 部署模型-子区域
 * @author WANGQINGPING
 *
 */
public class SysDpySubArea extends BaseWithPropArea{
	public final static String ID = SysDpySubArea.class.getName();
	private SysDpySubLeftArea subLeftArea; //左边区域
	private SysDpySubRightArea subRightArea; //右边区域

	public SysDpySubArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	@Override
	protected IEpiArea newWorkArea() {
		int[] areaWeights = new int[2];
		areaWeights[0] = 1;
		areaWeights[1] = 1;
		
		subLeftArea = new SysDpySubLeftArea(); //左边区域
		subRightArea = new SysDpySubRightArea(); //右边区域
		IEpiArea[] epiAreas = new IEpiArea[2];
		epiAreas[0] = subLeftArea;
		epiAreas[1] = subRightArea;
		
		EpiSashArea sashArea = new EpiSashArea(getWorkbenchPart(), SWT.HORIZONTAL);
		sashArea.setEpiAreas(epiAreas);
		sashArea.setAreaWeights(areaWeights);
		return sashArea;
	}
	
	@Override
	protected Control createAreaControl(Composite parent) {
		// TODO Auto-generated method stub
		return super.createAreaControl(parent);
	}

}
