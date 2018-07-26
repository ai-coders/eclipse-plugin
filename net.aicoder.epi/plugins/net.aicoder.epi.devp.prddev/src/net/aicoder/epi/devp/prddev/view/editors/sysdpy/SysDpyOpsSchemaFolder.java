package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;

import net.aicoder.epi.base.view.part.area.BaseArea;
import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.base.view.part.area.TabArea;

/**
 * 运维流水线-Tab切换
 * @author WANGQINGPING
 *
 */
public class SysDpyOpsSchemaFolder extends BaseArea {
	private SysDpyOpsSchemaFolderEnvPrepare sysDpyOpsSchemaFolderEnvPrepare; // 环境准备			
	private SysDpyOpsSchemaFolderBuildAndPublish sysDpyOpsSchemaFolderBuildAndPublish; // 构建及发布	
	private SysDpyOpsSchemaFolderSysMonitor sysDpyOpsSchemaFolderSysMonitor; // 系统监控
	private IArea[] epiAreas;

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}
	
	@Override
	public void assembleControl(Composite parent) {
		sysDpyOpsSchemaFolderEnvPrepare = new SysDpyOpsSchemaFolderEnvPrepare();
		sysDpyOpsSchemaFolderBuildAndPublish = new SysDpyOpsSchemaFolderBuildAndPublish();
		sysDpyOpsSchemaFolderSysMonitor = new SysDpyOpsSchemaFolderSysMonitor();
		epiAreas = new IArea[3];
		epiAreas[0] = sysDpyOpsSchemaFolderEnvPrepare;
		epiAreas[1] = sysDpyOpsSchemaFolderBuildAndPublish;
		epiAreas[2] = sysDpyOpsSchemaFolderSysMonitor;
		
		TabArea tabArea = new TabArea(getWorkbenchPart());
		tabArea.setEpiAreas(epiAreas);
		tabArea.createControl(parent);
		tabArea.getTabFolder().setSelection(0);
	}

	
	
	public SysDpyOpsSchemaFolderEnvPrepare getSysDpyOpsSchemaFolderEnvPrepare() {
		return sysDpyOpsSchemaFolderEnvPrepare;
	}

	public SysDpyOpsSchemaFolderBuildAndPublish getSysDpyOpsSchemaFolderBuildAndPublish() {
		return sysDpyOpsSchemaFolderBuildAndPublish;
	}

	public SysDpyOpsSchemaFolderSysMonitor getSysDpyOpsSchemaFolderSysMonitor() {
		return sysDpyOpsSchemaFolderSysMonitor;
	}
	
}
