package net.aicoder.epi.devp.prddev.view.editors.sysdpy.opsline;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.LoadElementState;
import net.aicoder.epi.base.model.StateFlagEnum;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.action.table.EpiAddRowAction;
import net.aicoder.epi.base.view.action.table.EpiDeleteRowAction;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.control.table.EpiTable;
import net.aicoder.epi.base.view.control.table.EpiTableDefiner;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;
import net.aicoder.epi.devp.prddev.doper.ops.SysDpyEnvPrepareDoper;

/**
 * 运维流水线-环境准备-参数配置（列表）
 * @author WANGQINGPING
 *
 */
public class SysDpyParamConfTable extends BaseTitleArea {
	private EpiTable table;
	private EpiTableDefiner definer;
	private IViewContext context;
	private SysDpyEnvPrepareDoper doper; // 环境准备doper
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"参数名称", "name", -20, null, null, null, IColumnDefiner.CE_TEXT},
		{"参数值", "value", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"备注", "remark", 20, null, null, null, IColumnDefiner.CE_TEXT }
	};
	
	
	//Constructor
	public SysDpyParamConfTable() {
		super("参数配置", null);
		this.doper = new SysDpyEnvPrepareDoper();
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		IEpiAction[] epiAction = new IEpiAction[2];
		epiAction[0] = new SysDpyParamsConfAddRowAction(table);//新增
		epiAction[1] = new EpiDeleteRowAction(table);//删除
		toolBarManager.add(epiAction[0]);
		toolBarManager.add(epiAction[1]);
		toolBarManager.update(true);
	}
	
	@Override
	public void assembleControl(Composite parent) {
		definer = new EpiTableDefiner(null, columnsDefine);
		context = new ViewContext();
		table = new EpiTable(parent, definer, context);
	}
	
	/**
	 *运维流水线->选择部署方案->参数配置响应处理
	 * @param selection
	 */
	public void setSelection(ISelection selection) {
		
		
		
		IEpiInput input = doper.loadSysDpyParamConfList(null);
		if(input != null && input.getDataList().size() > 0) {
			input.setCurrentData(input.getDataList().get(0));
			context.setInput(input);
			TableViewer viewer = table.getViewer();
			viewer.setInput(input);
			viewer.refresh();
		}
	}
	
	
	/**
	 * 参数配置-新增动作
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyParamsConfAddRowAction extends EpiAddRowAction{
		public SysDpyParamsConfAddRowAction(EpiTable epiTable) {
			super(epiTable);
		}
		
		public SysDpyParamsConfAddRowAction(String actionText, ImageDescriptor actionImageDescriptor) {
			super(actionText, actionImageDescriptor);
		}
		
		@Override
		protected void doAddAction() {
			//执行新增1行处理
			TableViewer tableViewer = getTable().getViewer();
			IEpiInput input = (IEpiInput) tableViewer.getInput();
			List<IBaseVo> dataList = input.getDataList();
			dataList.add(null);
			tableViewer.refresh();
		}
	}

}
