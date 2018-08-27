package net.aicoder.epi.devp.prddev.view.editors.sysdpy.model;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.base.view.part.area.SashArea;
import net.aicoder.epi.devp.prddev.doper.dev.DropdownOptionsDoper;
import net.aicoder.epi.devp.prddev.model.dev.system.SysCmpVo;
import net.aicoder.epi.devp.prddev.view.property.PrddevWithPropArea;

/**
 * 部署模型-内容区域(左/右区域)
 * @author WANGQINGPING
 *
 */
public class SysDpyModelArea extends PrddevWithPropArea {
	public final static String ID = SysDpyModelArea.class.getName();
	private DropdownOptionsDoper dropdownOptionsDoper;

	private SysDpyCmpTreeTable sysDpyCmpTreeTable; // 上- 系统、子系统、组件-树表
	private SysDpyResPlanTable sysDpyResPlanTable; // 下- 资源规划-列表

	public SysDpyModelArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
		initDropdownOptions();
	}

	@Override
	protected IArea newWorkArea() {
		int[] weights = new int[1];
		weights[0] = 1;

		IArea[] areas = new IArea[1];
		areas[0] = newLeftWorkArea(); //左边区域
//		areas[1] = newRightWorkArea(); //右边区域

		SashArea sashArea = new SashArea(getWorkbenchPart());
		sashArea.setAreas(areas);
		sashArea.setWeights(weights);
		sashArea.setFixedOrientation(SWT.VERTICAL);

		return sashArea;
	}

	private IArea newLeftWorkArea() {
		int[] weights = new int[2];
		weights[0] = 2;
		weights[1] = 1;

		sysDpyCmpTreeTable = new SysDpyCmpTreeTable();
		sysDpyResPlanTable = new SysDpyResPlanTable();

		IArea[] areas = new IArea[2];
		areas[0] = sysDpyCmpTreeTable;
		areas[1] = sysDpyResPlanTable;

		SashArea sashArea = new SashArea(getWorkbenchPart());
		sashArea.setAreas(areas);
		sashArea.setWeights(weights);
		sashArea.setFixedOrientation(SWT.VERTICAL);

		return sashArea;
	}

//	private IArea newRightWorkArea() {
//		int[] weights = new int[3];
//		weights[0] = 1;
//		weights[1] = 15;
//		weights[2] = 8;
//
//		sysDpySchemeArea = new SysDpySchemeArea();
//		sysDpyCmpRefTable = new SysDpyCmpRefTable();
//		sysDpyResInstanceTreeTable = new SysDpyResInstanceTreeTable();		
//
//		IArea[] areas = new IArea[3];
//		areas[0] = sysDpySchemeArea;
//		areas[1] = sysDpyCmpRefTable;
//		areas[2] = sysDpyResInstanceTreeTable;
//
//		SashArea sashArea = new SashArea(getWorkbenchPart());
//		sashArea.setAreas(areas);
//		sashArea.setWeights(weights);
//		sashArea.setFixedOrientation(SWT.VERTICAL);

//		return null;
//	}
	
	@Override
	public void attachEvent() {
		//绑定监听：[系统、子系统、组件树表]点击选择组件-->响应到属性区域
		sysDpyCmpTreeTable.getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				SysCmpVo sysCmpVo = (SysCmpVo) ((IStructuredSelection)selection).getFirstElement();
				sysDpyCmpTreeTable.setCurrentData(sysCmpVo);
				
				setElementSelection(selection);//进入属性区域处理
				sysDpyCmpTreeTable.bindSelectionDataEvent(selection);//绑定属性区域逆向处理
			}
		});
		
		//绑定监听：[资源规划列表]点击选择资源规划-->响应到属性区域
		sysDpyResPlanTable.getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();

				setElementSelection(selection);//进入属性区域处理
				sysDpyResPlanTable.bindSelectionDataEvent(selection);//绑定属性区域逆向处理
			}
		});
		
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	private void initDropdownOptions() {
		propsManager = this.getPropsManager();
		propsManager.clearRefObjects();

		dropdownOptionsDoper = new DropdownOptionsDoper();
		{
			String refObjectsCode = "cmpType";
			Object refObjects = dropdownOptionsDoper.listDropdownOption(refObjectsCode);
			propsManager.putRefObjects(refObjectsCode, refObjects);
		}
	}

	
	/**
	 * 获取系统、子系统、组件对象
	 * @return tree
	 */
	public SysDpyCmpTreeTable getSysDpyCmpTreeTable() {
		return sysDpyCmpTreeTable;
	}

	/**
	 * 获取资源规划对象
	 * @return 
	 */
	public SysDpyResPlanTable getSysDpyResPlanTable() {
		return sysDpyResPlanTable;
	}
	
}
