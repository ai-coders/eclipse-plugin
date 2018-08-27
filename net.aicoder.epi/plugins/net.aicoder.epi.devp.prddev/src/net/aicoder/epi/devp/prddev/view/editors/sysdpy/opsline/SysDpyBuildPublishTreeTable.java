package net.aicoder.epi.devp.prddev.view.editors.sysdpy.opsline;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.control.tree.EpiTree;
import net.aicoder.epi.base.view.control.tree.EpiTreeDefiner;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;
import net.aicoder.epi.devp.prddev.doper.ops.SysDpyBuildPublishDoper;

/**
 * 运维流水线-构建及发布(树表)
 * @author WANGQINGPING
 *
 */
public class SysDpyBuildPublishTreeTable extends BaseTitleArea {
	public final String ID = SysDpyBuildPublishTreeTable.class.getName();
	private EpiTree tree;
	private EpiTreeDefiner definer;
	private IViewContext context;
	private SysDpyBuildPublishDoper doper;
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"*步骤名称", "name", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"代码", "code", -20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"执行方式", "alias", 0,IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE},
		{"执行状态", "type", 0,IColumnDefiner.CE_TEXT,null,null,IColumnDefiner.EDITABLE},
		{"操作", "version", 0,IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE}
	};
		
	
	//Constructor
	public SysDpyBuildPublishTreeTable() {
		super();
		setTitleText("构建及发布 ");
		this.doper = new SysDpyBuildPublishDoper();
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		IEpiAction[] epiAction = new IEpiAction[4];
		epiAction[0] = new SysDpyBuildPublishSetupAction(tree);//设定
		epiAction[1] = new SysDpyBuildPublishExecuteAction(tree);//执行
		epiAction[2] = new SysDpyBuildPublishEditorAction(tree);//编辑
		epiAction[3] = new SysDpyBuildPublishPlanAction(tree);//计划
		toolBarManager.add(epiAction[0]);
		toolBarManager.add(epiAction[1]);
		toolBarManager.add(epiAction[2]);
		toolBarManager.add(epiAction[3]);
		toolBarManager.update(true);
	}
	
	
	@Override
	public void assembleControl(Composite parent) {
		definer = new EpiTreeDefiner(null, columnsDefine);
		context = new ViewContext();
		tree = new EpiTree(parent, definer, context);
	}
	
	
	/**
	 *运维流水线->选择部署方案->构建及发布响应处理
	 * @param selection
	 */
	public void setSelection(ISelection selection) {
		
		
		
		IEpiInput input = doper.loadSysDpyBuildPublishList(null);
		if(input != null && input.getDataList().size() > 0) {
			input.setCurrentData(input.getDataList().get(0));
			context.setInput(input);
			TreeViewer viewer = tree.getViewer();
			viewer.setInput(input);
			viewer.refresh();
		}
	}
	
	
	/**
	 * 运维流水线->构建及发布-设定动作
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyBuildPublishSetupAction extends BaseAction{
		public SysDpyBuildPublishSetupAction(EpiTree tree) {
			super("设定", null);
		}
		public SysDpyBuildPublishSetupAction(String actionText, ImageDescriptor actionImageDescriptor) {
			super(actionText, actionImageDescriptor);
		}
		
		@Override
		public void run() {
			// 执行 设定处理
			
			
		}
	}
	
	/**
	 * 运维流水线->构建及发布-执行动作
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyBuildPublishExecuteAction extends BaseAction{
		public SysDpyBuildPublishExecuteAction(EpiTree tree) {
			super("执行", null);
		}
		public SysDpyBuildPublishExecuteAction(String actionText, ImageDescriptor actionImageDescriptor) {
			super(actionText, actionImageDescriptor);
		}
		
		@Override
		public void run() {
			// 执行 执行处理
			
			
		}
	}
	
	/**
	 * 运维流水线->构建及发布-编辑动作
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyBuildPublishEditorAction extends BaseAction{
		public SysDpyBuildPublishEditorAction(EpiTree tree) {
			super("编辑", null);
		}
		public SysDpyBuildPublishEditorAction(String actionText, ImageDescriptor actionImageDescriptor) {
			super(actionText, actionImageDescriptor);
		}
		
		@Override
		public void run() {
			// 执行 编辑处理
			
			
		}
	}
	
	/**
	 * 运维流水线->构建及发布-计划动作
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyBuildPublishPlanAction extends BaseAction{
		public SysDpyBuildPublishPlanAction(EpiTree tree) {
			super("计划", null);
		}
		public SysDpyBuildPublishPlanAction(String actionText, ImageDescriptor actionImageDescriptor) {
			super(actionText, actionImageDescriptor);
		}
		
		@Override
		public void run() {
			// 执行 计划处理
			
			
		}
	}

}
