package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.element.area.BaseWithPropArea;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;

/**
 * 部署模型-内容(左/右区域)
 * @author WANGQINGPING
 *
 */
public class SysDpyModelArea extends BaseWithPropArea{
	public final static String ID = SysDpyModelArea.class.getName();
	private SysDpyModelLeftArea modelLeftArea; //左边区域
	private SysDpyModelRightArea modelRightArea; //右边区域

	public SysDpyModelArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	@Override
	protected IEpiArea newWorkArea() {
//		int[] areaWeights = new int[2];
//		areaWeights[0] = 1;
//		areaWeights[1] = 1;
		
		modelLeftArea = new SysDpyModelLeftArea(); //左边区域
		modelRightArea = new SysDpyModelRightArea(); //右边区域
		IEpiArea[] epiAreas = new IEpiArea[2];
		epiAreas[0] = modelLeftArea;
		epiAreas[1] = modelRightArea;
		
		EpiSashArea sashArea = new EpiSashArea(getWorkbenchPart(), SWT.HORIZONTAL);
		sashArea.setEpiAreas(epiAreas);
//		sashArea.setAreaWeights(areaWeights);		

		return sashArea;
	}
	
	@Override
	protected Control createAreaControl(Composite parent) {
		Control control = super.createAreaControl(parent);
		//此监听点击系统树表[组件]->刷新右边区域[xxx组件列表]数据
		getModelLeftArea().getSysDpyCmpTreeTable().getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				getModelRightArea().getSysDpyCmpRefTable().setSelection(selection);
			}
		});
		return control;
	}

	public SysDpyModelLeftArea getModelLeftArea() {
		return modelLeftArea;
	}

	public void setModelLeftArea(SysDpyModelLeftArea modelLeftArea) {
		this.modelLeftArea = modelLeftArea;
	}

	public SysDpyModelRightArea getModelRightArea() {
		return modelRightArea;
	}

	public void setModelRightArea(SysDpyModelRightArea modelRightArea) {
		this.modelRightArea = modelRightArea;
	}

}
