package net.aicoder.epi.devp.prddev.view.editors.sysdpy.opsline;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;

import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;
import net.aicoder.epi.devp.prddev.dao.ops.SysDpySysMonitorDao;
import net.aicoder.epi.devp.prddev.doper.ops.SysDpySysMonitorDoper;

/**
 * 运维流水线-系统监控
 * @author WANGQINGPING
 *
 */
public class SysDpySysMonitorTable extends BaseTitleArea{
	public final String ID = SysDpySysMonitorTable.class.getName();
	private SysDpySysMonitorDoper doper;
	
	
	public SysDpySysMonitorTable() {
		super();
		setTitleText("系统监控");
		this.doper = new SysDpySysMonitorDoper();
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}
	
	@Override
	public void assembleControl(Composite parent) {
		
	}
	
	
	/**
	 *运维流水线->选择部署方案->系统监控响应处理
	 * @param selection
	 */
	public void setSelection(ISelection selection) {
		
		
	}

}
