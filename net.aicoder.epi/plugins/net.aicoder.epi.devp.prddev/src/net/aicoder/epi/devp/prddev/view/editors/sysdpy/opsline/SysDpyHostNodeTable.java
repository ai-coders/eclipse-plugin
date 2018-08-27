package net.aicoder.epi.devp.prddev.view.editors.sysdpy.opsline;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;

import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.action.table.EpiAddRowAction;
import net.aicoder.epi.base.view.action.table.EpiDeleteRowAction;
import net.aicoder.epi.base.view.action.table.EpiDownRowAction;
import net.aicoder.epi.base.view.action.table.EpiUpRowAction;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.control.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.control.table.EpiTable;
import net.aicoder.epi.base.view.control.table.EpiTableDefiner;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;
import net.aicoder.epi.devp.prddev.doper.ops.SysDpyEnvPrepareDoper;

/**
 * 运维流水线-环境准备-主机节点（列表，属性区域）
 * @author WANGQINGPING
 *
 */
public class SysDpyHostNodeTable extends BaseTitleArea{
	private EpiTable table;
	private EpiTableDefiner definer;
	private IViewContext context;
	private SysDpyEnvPrepareDoper doper;
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"标识", "tag", -20, null, null, null, IColumnDefiner.CE_TEXT},
		{"名称", "name", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"代码", "code", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"别名", "alias", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"类别", "type", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"描述", "description", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"所属项目", "program", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"部署位置", "location", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"访问地址", "address", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"备注", "remark", 20, null, null, null, IColumnDefiner.CE_TEXT }
	};
		
	
	//Constructor
	public SysDpyHostNodeTable() {
		super("主机节点",null);
		this.doper = new SysDpyEnvPrepareDoper();
	}
	
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		IEpiAction[] epiAction = new IEpiAction[5];
		epiAction[0] = new SysDpyHostNodeAddRowAction(table);//新增
		epiAction[1] = new SysDpyHostNodeEditRowAction(table);//编辑
		epiAction[2] = new EpiUpRowAction(table);//上移
		epiAction[3] = new EpiDownRowAction(table);//下移
		epiAction[4] = new EpiDeleteRowAction(table);//删除
		toolBarManager.add(epiAction[0]);
		toolBarManager.add(epiAction[1]);
		toolBarManager.add(epiAction[2]);
		toolBarManager.add(epiAction[3]);
		toolBarManager.add(epiAction[4]);
		toolBarManager.update(true);
	}
	
	@Override
	public void assembleControl(Composite parent) {
		definer = new EpiTableDefiner(null, columnsDefine);
		context = new ViewContext();
		table = new EpiTable(parent, definer, context);
	}
	
	
	public EpiSelectionProvider getSelectionProvider() {
		return table.getSelectionProvider();
	}

	public void bindSelectionDataEvent(ISelection selection) {
		table.bindSelectionDataEvent();
	}

	
	/**
	 *运维流水线->选择部署方案->主机节点响应处理
	 * @param selection
	 */
	public void setSelection(ISelection selection) {
		
		IEpiInput input = doper.loadSysDpyHostNodeList(null);
		if(input != null && input.getDataList().size() > 0) {
			input.setCurrentData(input.getDataList().get(0));
			context.setInput(input);
			TableViewer viewer = table.getViewer();
			viewer.setInput(input);
			viewer.refresh();
		}
	}
	
	/**
	 * 主机节点-新增动作
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyHostNodeAddRowAction extends EpiAddRowAction{

		public SysDpyHostNodeAddRowAction(EpiTable epiTable) {
			super(epiTable);
		}
		
		@Override
		protected void doAddAction() {
			//执行添加1行处理
		}
		
	}
	
	/**
	 * 主机节点-编辑动作
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyHostNodeEditRowAction extends BaseAction{

		public SysDpyHostNodeEditRowAction(EpiTable table) {
			super("编辑", null);
		}
		
		public SysDpyHostNodeEditRowAction(String actionText, ImageDescriptor actionImageDescriptor) {
			super(actionText, actionImageDescriptor);
		}
		
		@Override
		public void run() {
			//做编辑处理
			
			
		}
		
	}
	
	
	

}
