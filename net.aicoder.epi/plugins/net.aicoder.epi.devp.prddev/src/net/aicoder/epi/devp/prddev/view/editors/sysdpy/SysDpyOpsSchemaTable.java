package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.action.table.EpiAddAction;
import net.aicoder.epi.base.view.action.table.EpiDeleteAction;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.control.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.control.table.EpiTable;
import net.aicoder.epi.base.view.control.table.EpiTableDefiner;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;
import net.aicoder.epi.devp.prddev.doper.ops.SysDpyResourcesDoper;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

/**
 * 运维流水线-产品部署列表
 * @author WANGQINGPING
 *
 */
public class SysDpyOpsSchemaTable extends BaseTitleArea{
	private EpiTable table;
	private EpiTableDefiner definer;
	private IViewContext context;
	private PrdProductVo currentSelectProduct;//当前选中的产品
	private SysDpyResourcesDoper doper;
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"部署方案名称", "name", -20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE},
		{"代码", "code", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"类型", "type", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"版本", "version", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"环境准备", "name", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"构建及发布", "name", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"系统监控", "name", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"备注", "remark", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE }
	};
	
	
	public SysDpyOpsSchemaTable() {
		super("", null);
		doper = new SysDpyResourcesDoper();
	}
		
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		IEpiAction[] epiAction = new IEpiAction[2];
		epiAction[0] = new EpiAddAction(table);
		epiAction[1] = new EpiDeleteAction(table);
		toolBarManager.add(epiAction[0]);
		toolBarManager.add(epiAction[1]);
		toolBarManager.update(true);
	}
	
	
	@Override
	public void assembleControl(Composite parent) {
		IEpiEditorInput editorInput = (IEpiEditorInput)this.getEditorInput();
		IBaseVo currentData = editorInput.getCurrentData();
		if(currentData instanceof PrdProductVo){
			currentSelectProduct = (PrdProductVo)currentData;
		}
		
		//点选XXX产品时，获取当前产品的所关联的资源；关联资源为该产品所涉及的外部产品、及其它系统资源；关联资源类型有：部署到、连接（双向/请求）、调用	
		IEpiInput input = doper.loadSysDpyResourcesList(currentSelectProduct);
		
		definer = new EpiTableDefiner(null, columnsDefine);
		context = new ViewContext();
		context.setInput(input);
		table = new EpiTable(parent, definer, context);

	}
	
	public void bindSelectionDataEvent(ISelection selection) {
		table.bindSelectionDataEvent();
	}
	
	public EpiSelectionProvider getSelectionProvider() {
		return table.getSelectionProvider();
	}

}
