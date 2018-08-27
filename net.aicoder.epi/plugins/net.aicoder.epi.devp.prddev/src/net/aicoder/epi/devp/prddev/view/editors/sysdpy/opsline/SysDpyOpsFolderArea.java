package net.aicoder.epi.devp.prddev.view.editors.sysdpy.opsline;

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
public class SysDpyOpsFolderArea extends BaseArea {
	private SysDpyEnvPrepArea sysDpyOpsEnvPrepArea; // 环境准备区域			
	private SysDpyBuildPublishTreeTable sysDpyOpsBuildPublishTreeTable; // 构建及发布树表	
	private SysDpySysMonitorTable sysDpyOpsSysMonitorTable; // 系统监控列表
	private IArea[] epiAreas;
	

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}
	
	@Override
	public void assembleControl(Composite parent) {
		sysDpyOpsEnvPrepArea = new SysDpyEnvPrepArea();
		sysDpyOpsBuildPublishTreeTable = new SysDpyBuildPublishTreeTable();
		sysDpyOpsSysMonitorTable = new SysDpySysMonitorTable();
		epiAreas = new IArea[3];
		epiAreas[0] = sysDpyOpsEnvPrepArea;
		epiAreas[1] = sysDpyOpsBuildPublishTreeTable;
		epiAreas[2] = sysDpyOpsSysMonitorTable;
		
		TabArea tabArea = new TabArea(getWorkbenchPart());
		tabArea.setEpiAreas(epiAreas);
		tabArea.createControl(parent);
		tabArea.getTabFolder().setSelection(0);
	}

	
	
	public SysDpyEnvPrepArea getSysDpyOpsEnvPrepArea() {
		return sysDpyOpsEnvPrepArea;
	}

	public SysDpyBuildPublishTreeTable getSysDpyOpsBuildPublishTreeTable() {
		return sysDpyOpsBuildPublishTreeTable;
	}

	public SysDpySysMonitorTable getSysDpyOpsSysMonitorTable() {
		return sysDpyOpsSysMonitorTable;
	}
	
}
