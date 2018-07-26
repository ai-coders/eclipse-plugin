package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.part.area.SashArea;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.part.area.IArea;
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

	private SysDpyCmpTreeTable sysDpyCmpTreeTable; // 左-上 系统、子系统、组件
	private SysDpyResourcesTable sysDpyResourcesTable; // 左-下 关联资源
	private SysDpySchemeArea sysDpySchemeArea; // 右-上 部署方案
	private SysDpyCmpRefTable sysDpyCmpRefTable; // 右-中 xxx组件维护
	private SysDpyResInstanceTreeTable sysDpyResInstanceTreeTable; // 右-下 资源实例		

	public SysDpyModelArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
		initDropdownOptions();
	}

	@Override
	protected IArea newWorkArea() {
		int[] weights = new int[2];
		weights[0] = 2;
		weights[1] = 1;

		IArea[] areas = new IArea[2];
		areas[0] = newLeftWorkArea(); //左边区域
		areas[1] = newRightWorkArea(); //右边区域

		SashArea sashArea = new SashArea(getWorkbenchPart());
		sashArea.setAreas(areas);
		sashArea.setWeights(weights);
		sashArea.setFixedOrientation(SWT.HORIZONTAL);

		return sashArea;
	}

	private IArea newLeftWorkArea() {
		int[] weights = new int[2];
		weights[0] = 2;
		weights[1] = 1;

		sysDpyCmpTreeTable = new SysDpyCmpTreeTable();
		sysDpyResourcesTable = new SysDpyResourcesTable();

		IArea[] areas = new IArea[2];
		areas[0] = sysDpyCmpTreeTable;
		areas[1] = sysDpyResourcesTable;

		SashArea sashArea = new SashArea(getWorkbenchPart());
		sashArea.setAreas(areas);
		sashArea.setWeights(weights);
		sashArea.setFixedOrientation(SWT.VERTICAL);

		return sashArea;
	}

	private IArea newRightWorkArea() {
		int[] weights = new int[3];
		weights[0] = 1;
		weights[1] = 15;
		weights[2] = 8;

		sysDpySchemeArea = new SysDpySchemeArea();
		sysDpyCmpRefTable = new SysDpyCmpRefTable();
		sysDpyResInstanceTreeTable = new SysDpyResInstanceTreeTable();		

		IArea[] areas = new IArea[3];
		areas[0] = sysDpySchemeArea;
		areas[1] = sysDpyCmpRefTable;
		areas[2] = sysDpyResInstanceTreeTable;

		SashArea sashArea = new SashArea(getWorkbenchPart());
		sashArea.setAreas(areas);
		sashArea.setWeights(weights);
		sashArea.setFixedOrientation(SWT.VERTICAL);

		return sashArea;
	}
	
	@Override
	public void attachEvent() {
		//绑定监听：[系统、子系统、组件树表]点击选择组件-->响应到右边区域[xxx组件列表]处理
		sysDpyCmpTreeTable.getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				SysCmpVo sysCmpVo = (SysCmpVo) ((IStructuredSelection)selection).getFirstElement();
				sysDpyCmpTreeTable.setCurrentData(sysCmpVo);
				
//				sysDpyCmpRefTable.setSelection(selection);
				setElementSelection(selection);//进入属性区域处理
				sysDpyCmpTreeTable.bindSelectionDataEvent(selection);//进入属性区域逆向处理
			}
		});
		
		//绑定监听：[关联资源列表]点击选择组件-->响应到右边区域[资源使用场景]区域处理
		sysDpyResourcesTable.getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				
				sysDpyResInstanceTreeTable.setSelection(selection);
				setElementSelection(selection);//进入属性区域处理
				sysDpyResourcesTable.bindSelectionDataEvent(selection);//进入属性区域逆向处理
			}
		});
		
		//绑定监听：[资源应用场景]点击选择组件-->响应到[xxx组件列表]处理
		sysDpyResInstanceTreeTable.getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if(((StructuredSelection)selection).getFirstElement() == null) return;
				
//				sysDpyCmpRefTable.setSelectionBySysDpyResInstVo(selection);
				setElementSelection(selection);//进入属性区域处理
				sysDpyResInstanceTreeTable.bindSelectionDataEvent(selection);//进入属性区域逆向处理
			}
		});
		
		//绑定监听：[部署方案]下拉框选择组件-->响应到[资源应用场景]区域处理
		sysDpySchemeArea.getSelectionCombo().addSelectionListener(new SelectionListener() {		
			@Override
			public void widgetSelected(SelectionEvent e) {
				IBaseVo baseVo = null;
				String source = e.getSource().toString();
				String name = source.substring(source.indexOf("{")+1, source.indexOf("}"));
				List<IBaseVo> currentSysDpySchemas = sysDpySchemeArea.getSelectionComboData();
				for (IBaseVo iBaseVo : currentSysDpySchemas) {
					if(iBaseVo.getName().equals(name)) {
						baseVo = iBaseVo;
						break;
					}
				}
				if(baseVo == null) return;
				
				sysDpyResInstanceTreeTable.setSelectionBySysDpySchema(baseVo);			
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println("widgetDefaultSelected:[部署方案]下拉框选择"+e.getSource().toString());
			}
		});
		
		//绑定监听：[xxx组件列表]点击选择组件-->响应右侧[属性]区域处理
		sysDpyCmpRefTable.getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if(((StructuredSelection)selection).getFirstElement() == null) return;
				
				setElementSelection(selection);//进入属性区域处理
				sysDpyCmpRefTable.bindSelectionDataEvent(selection);//进入属性区域逆向处理
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
	 * 获取关联资源对象
	 * @return 
	 */
	public SysDpyResourcesTable getSysDpyResourcesTable() {
		return sysDpyResourcesTable;
	}

	/**
	 * 获取部署方案对象
	 * @return
	 */
	public SysDpySchemeArea getSysDpySchemeArea() {
		return sysDpySchemeArea;
	}

	/**
	 * 获取引用组件对象
	 * @return
	 */
	public SysDpyCmpRefTable getSysDpyCmpRefTable() {
		return sysDpyCmpRefTable;
	}

	/**
	 * 获取资源实例对象
	 * @return
	 */
	public SysDpyResInstanceTreeTable getSysDpyResInstanceTreeTable() {
		return sysDpyResInstanceTreeTable;
	}
	
}
