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

import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.part.area.SashArea;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.devp.prddev.doper.dev.DropdownOptionsDoper;
import net.aicoder.epi.devp.prddev.view.property.PrddevWithPropArea;

/**
 * 部署模型-内容(左/右区域)
 * @author WANGQINGPING
 *
 */
public class SysDpyModelArea extends PrddevWithPropArea {
	public final static String ID = SysDpyModelArea.class.getName();
	private DropdownOptionsDoper dropdownOptionsDoper;

	private SysDpyCmpTreeTable sysDpyCmpTreeTable; // 左-上
	private SysDpyResAndInstArea sysDpyResAndInstArea; // 左-下

	private SysDpySchemeArea sysDpySchemeArea; // 右-上
	private SysDpyCmpRefTable sysDpyCmpRefTable; // 右-下

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
		weights[0] = 1;
		weights[1] = 1;

		sysDpyCmpTreeTable = new SysDpyCmpTreeTable();
		sysDpyResAndInstArea = new SysDpyResAndInstArea();

		IArea[] areas = new IArea[2];
		areas[0] = sysDpyCmpTreeTable;
		areas[1] = sysDpyResAndInstArea;

		SashArea sashArea = new SashArea(getWorkbenchPart());
		sashArea.setAreas(areas);
		sashArea.setWeights(weights);
		sashArea.setFixedOrientation(SWT.VERTICAL);

		return sashArea;
	}

	private IArea newRightWorkArea() {
		int[] weights = new int[2];
		weights[0] = 1;
		weights[1] = 15;

		sysDpySchemeArea = new SysDpySchemeArea();
		sysDpyCmpRefTable = new SysDpyCmpRefTable();

		IArea[] areas = new IArea[2];
		areas[0] = sysDpySchemeArea;
		areas[1] = sysDpyCmpRefTable;

		SashArea sashArea = new SashArea(getWorkbenchPart());
		sashArea.setAreas(areas);
		sashArea.setWeights(weights);
		sashArea.setFixedOrientation(SWT.VERTICAL);

		return sashArea;
	}
	
	@Override
	public void attachEvent() {
		sysDpyCmpTreeTable.getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				//sysDpyResAndInstArea.setSelection(selection);
				sysDpyCmpTreeTable.bindSelectionDataEvent(selection);
				setElementSelection(selection);
			}
		});
		
		//绑定监听：[资源应用场景]点击选择组件-->响应到右边区域[xxx组件列表]处理
		//getModelLeftArea().getSysDpyResAndInstArea().getSysDpyResInstanceTreeTable().getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {			
		sysDpyResAndInstArea.getSysDpyResInstanceTreeTable().getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if(((StructuredSelection)selection).getFirstElement() == null) return;
				
				//getModelRightArea().getSysDpyCmpRefTable().setSelectionBySysDpyResInstVo(selection);
				sysDpyCmpRefTable.setSelectionBySysDpyResInstVo(selection);
			}
		});
		
		//绑定监听：[部署方案]下拉框选择组件-->响应到[资源应用场景]区域处理
		//getModelRightArea().getSysDpySchemeArea().getSelectionCombo().addSelectionListener(new SelectionListener() {		
		sysDpySchemeArea.getSelectionCombo().addSelectionListener(new SelectionListener() {		
			@Override
			public void widgetSelected(SelectionEvent e) {
				IBaseVo baseVo = null;
				String source = e.getSource().toString();
				String name = source.substring(source.indexOf("{")+1, source.indexOf("}"));
				//List<IBaseVo> currentSysDpySchemas = getModelRightArea().getSysDpySchemeArea().getSelectionComboData();
				List<IBaseVo> currentSysDpySchemas = sysDpySchemeArea.getSelectionComboData();
				for (IBaseVo iBaseVo : currentSysDpySchemas) {
					if(iBaseVo.getName().equals(name)) {
						baseVo = iBaseVo;
						break;
					}
				}
				if(baseVo == null) return;
				
				// getModelLeftArea().getSysDpyResAndInstArea().getSysDpyResInstanceTreeTable().setSelectionBySysDpySchema(baseVo);
				sysDpyResAndInstArea.getSysDpyResInstanceTreeTable().setSelectionBySysDpySchema(baseVo);
				System.out.println("widgetSelected:"+name);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println("widgetDefaultSelected:"+e.getSource().toString());
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
