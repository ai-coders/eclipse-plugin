package net.aicoder.epi.devp.prddev.view.editors.sysdpy.opsline;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;

import net.aicoder.epi.base.view.part.area.BaseArea;
import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.base.view.part.area.TabArea;

/**
 * 运维流水线-环境准备(区域)
 * @author WANGQINGPING
 *
 */
public class SysDpyEnvPrepArea extends BaseArea{
	public final String ID = SysDpyEnvPrepArea.class.getName();
	
	private SysDpyParamConfTable sysdpyParamConfTable;//参数配置（列表）
	private SysDpyHostNodeArea sysDpyHostNodeArea;//主机节点（区域）
	private SysDpyResConfArea sysDpyResConfArea;//资源配置（区域）
	private IArea[] epiAreas;
	
	
	public SysDpyEnvPrepArea() {
		super();
		setTitleText("环境准备");
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}
	
	@Override
	public void assembleControl(Composite parent) {
		epiAreas = new IArea[3];
		sysdpyParamConfTable = new SysDpyParamConfTable();
		sysDpyHostNodeArea = new SysDpyHostNodeArea(getWorkbenchPart());				
		sysDpyResConfArea = new SysDpyResConfArea(getWorkbenchPart());	
		
		epiAreas[0] = sysdpyParamConfTable;
		epiAreas[1] = sysDpyHostNodeArea;
		epiAreas[2] = sysDpyResConfArea;
		
		TabArea tabArea = new TabArea(getWorkbenchPart());
		tabArea.setEpiAreas(epiAreas);
		tabArea.createControl(parent);
		tabArea.getTabFolder().setSelection(0);
	}

	
	public SysDpyParamConfTable getSysdpyParamConfTable() {
		return sysdpyParamConfTable;
	}

	public SysDpyHostNodeTable getSysDpyHostNodeTable() {
		return sysDpyHostNodeArea.getSysDpyHostNodeTable();
	}

	public SysDpyResConfTable getSysDpyResConfTable() {
		return sysDpyResConfArea.getSysDpyResConfTable();
	}

}
