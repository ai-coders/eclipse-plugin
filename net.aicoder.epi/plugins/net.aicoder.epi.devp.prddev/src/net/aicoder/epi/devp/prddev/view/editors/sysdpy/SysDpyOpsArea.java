package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.base.view.part.area.SashArea;

/**
 * 运维流水线-内容区域
 * @author WANGQINGPING
 *
 */
public class SysDpyOpsArea extends SashArea{
	public final static String ID = SysDpyOpsArea.class.getName();
	private SysDpyOpsSchemaTable sysDpyOpsSchemaTable;//上
	private SysDpyOpsSchemaFolder sysDpyOpsSchemaFolder;//下
	
	
	
	public SysDpyOpsArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}
	
	@Override
	public void initArea() {
		int[] weights = new int[2];
		weights[0] = 1;
		weights[1] = 1;
		
		sysDpyOpsSchemaTable = new SysDpyOpsSchemaTable();
		sysDpyOpsSchemaFolder = new SysDpyOpsSchemaFolder();
		IArea[] areas = new IArea[2];
		areas[0] = sysDpyOpsSchemaTable;
		areas[1] = sysDpyOpsSchemaFolder;
		
		this.areas = areas;
		this.weights = weights;
		this.fixedOrientation = SWT.VERTICAL;
	}
	

}
