package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.part.area.SashArea;
import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.base.view.property.IPropsManager;
import net.aicoder.epi.devp.prddev.doper.dev.DropdownOptionsDoper;
import net.aicoder.epi.devp.prddev.view.property.PrddevPropsManager;
import net.aicoder.epi.devp.prddev.view.property.PrddevWithPropArea;
import net.aicoder.epi.example.area.TableEditorArea;

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
