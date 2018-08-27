package net.aicoder.epi.devp.prddev.view.editors.sysdpy.opsline;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;

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
 * 运维流水线-环境准备-资源配置（列表，属性区域）
 * @author WANGQINGPING
 *
 */
public class SysDpyResConfTable extends BaseTitleArea{
	private EpiTable table;
	private EpiTableDefiner definer;
	private IViewContext context;
	private SysDpyEnvPrepareDoper doper;
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"资源代码", "code", -20, null, null, null, IColumnDefiner.CE_TEXT},
		{"资源名称", "name", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"资源别名", "alias", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"资源类型", "type", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"需求描述", "description", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"实例标识", "name", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"部署模式", "name", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"部署说明", "name", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"实例名称", "name", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"实例代码", "name", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"实例别名", "name", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"实例类别", "name", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"实例规格", "name", 20, null, null, null, IColumnDefiner.CE_TEXT },
		{"访问地址", "name", 20, null, null, null, IColumnDefiner.CE_TEXT }
	};

	//Constructor
	public SysDpyResConfTable() {
		super("资源配置",null);
		this.doper = new SysDpyEnvPrepareDoper();
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
		
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
	 *运维流水线->选择部署方案->资源配置响应处理
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




}
