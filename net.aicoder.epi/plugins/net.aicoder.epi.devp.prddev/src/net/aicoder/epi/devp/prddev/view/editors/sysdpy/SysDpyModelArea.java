package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;
import net.aicoder.epi.base.view.property.IPropsManager;
import net.aicoder.epi.devp.prddev.doper.dev.DropdownOptionsDoper;
import net.aicoder.epi.devp.prddev.view.property.PrddevWithPropArea;

/**
 * 部署模型-内容(左/右区域)
 * @author WANGQINGPING
 * 事件传输机制重点：
 * 子类有需要传递事件到其它同级或父/子级时，需要在父级获取子类对象在父类绑定监听并传于对应对象处理
 *
 */
public class SysDpyModelArea extends PrddevWithPropArea {
	public final static String ID = SysDpyModelArea.class.getName();
	private IPropsManager propsManager;
	private DropdownOptionsDoper dropdownOptionsDoper;

	private SysDpyModelLeftArea modelLeftArea; //左边区域
	private SysDpyModelRightArea modelRightArea; //右边区域
	
	public SysDpyModelArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
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
		
		propsManager = this.getPropsManager();
		propsManager.clearRefObjects();
		
		initDropdownOptions();
		
		return sashArea;
	}
	
	@Override
	protected Control createAreaControl(Composite parent) {
		Control control = super.createAreaControl(parent);

		//绑定监听：[系统、子系统、组件树表]点击选择组件-->响应到右边区域[xxx组件列表]处理
		getModelLeftArea().getSysDpyCmpTreeTable().getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if(((StructuredSelection)selection).getFirstElement() == null) return;
				
				getModelRightArea().getSysDpyCmpRefTable().setSelection(selection);
				setElementSelection(selection);
			}
		});
		
		//绑定监听：[资源应用场景]点击选择组件-->响应到右边区域[xxx组件列表]处理
		getModelLeftArea().getSysDpyResAndInstArea().getSysDpyResInstanceTreeTable().getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if(((StructuredSelection)selection).getFirstElement() == null) return;
				
				getModelRightArea().getSysDpyCmpRefTable().setSelectionBySysDpyResInstVo(selection);
			}
		});
		
		//绑定监听：[部署方案]下拉框选择组件-->响应到[资源应用场景]区域处理
		getModelRightArea().getSysDpySchemeArea().getSelectionCombo().addSelectionListener(new SelectionListener() {		
			@Override
			public void widgetSelected(SelectionEvent e) {
				IBaseVo baseVo = null;
				String source = e.getSource().toString();
				String name = source.substring(source.indexOf("{")+1, source.indexOf("}"));
				List<IBaseVo> currentSysDpySchemas = getModelRightArea().getSysDpySchemeArea().getSelectionComboData();
				for (IBaseVo iBaseVo : currentSysDpySchemas) {
					if(iBaseVo.getName().equals(name)) {
						baseVo = iBaseVo;
						break;
					}
				}
				if(baseVo == null) return;
				
				getModelLeftArea().getSysDpyResAndInstArea().getSysDpyResInstanceTreeTable().setSelectionBySysDpySchema(baseVo);
				System.out.println("widgetSelected:"+name);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println("widgetDefaultSelected:"+e.getSource().toString());
			}
		});
		
		
		return control;
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	private void initDropdownOptions() {
		dropdownOptionsDoper = new DropdownOptionsDoper();
		{
			String refObjectsCode = "cmpType";
			Object refObjects = dropdownOptionsDoper.listDropdownOption(refObjectsCode);
			propsManager.putRefObjects(refObjectsCode, refObjects);
		}
	}

	//// getter/setter
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
