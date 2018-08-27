package net.aicoder.epi.devp.prddev.view.editors.sysdpy.opsline;


import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.base.view.part.area.SashArea;

/**
 * 运维流水线-编辑区域
 * @author WANGQINGPING
 *
 */
public class SysDpyOpsArea extends SashArea{
	public final static String ID = SysDpyOpsArea.class.getName();
	private SysDpySchemaTable sysDpySchemaTable;//上，部署方案
	private SysDpyOpsFolderArea sysDpyOpsFolderArea;//下，[环境准备，构建及发布，系统监控]
	
	
	
	public SysDpyOpsArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}
	
	@Override
	public void initArea() {
		int[] weights = new int[2];
		weights[0] = 1;
		weights[1] = 1;
		
		sysDpySchemaTable = new SysDpySchemaTable();
		sysDpyOpsFolderArea = new SysDpyOpsFolderArea();
		IArea[] areas = new IArea[2];
		areas[0] = sysDpySchemaTable;
		areas[1] = sysDpyOpsFolderArea;
		
		this.areas = areas;
		this.weights = weights;
		this.fixedOrientation = SWT.VERTICAL;
	}
	
	
	@Override
	public void attachEvent() {
		//部署方案列表点击响应监听
		sysDpySchemaTable.getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();

				//环境准备->参数配置
				sysDpyOpsFolderArea.getSysDpyOpsEnvPrepArea().getSysdpyParamConfTable().setSelection(selection);				
				//环境准备->主机节点
				sysDpyOpsFolderArea.getSysDpyOpsEnvPrepArea().getSysDpyHostNodeTable().setSelection(selection);
				//环境准备->资源配置
				sysDpyOpsFolderArea.getSysDpyOpsEnvPrepArea().getSysDpyResConfTable().setSelection(selection);
				//构建及发布
				sysDpyOpsFolderArea.getSysDpyOpsBuildPublishTreeTable().setSelection(selection);
				//系统监控
				sysDpyOpsFolderArea.getSysDpyOpsSysMonitorTable().setSelection(selection);
			}
		});
	}

}
