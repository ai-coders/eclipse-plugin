package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.action.tree.EpiDeleteNodeAction;
import net.aicoder.epi.base.view.action.tree.EpiFilterAction;
import net.aicoder.epi.base.view.action.tree.EpiRefreshAction;
import net.aicoder.epi.base.view.action.tree.EpiSaveAction;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.control.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.control.tree.EpiTree;
import net.aicoder.epi.base.view.control.tree.EpiTreeDefiner;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;
import net.aicoder.epi.devp.prddev.doper.ops.SysDpyResInstDoper;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDpyResourcesVo;

/**
 * 部署模型-资源实例(树表)
 * @author WANGQINGPING
 *
 */
public class SysDpyResInstanceTreeTable extends BaseTitleArea{
	public static final String ID = SysDpyResInstanceTreeTable.class.getName();		
	private EpiTree tree;
	private EpiTreeDefiner definer;
	private IViewContext context;
	private SysDpyResInstDoper doper;
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"名称", "name", -20, null, null, null, IColumnDefiner.EDITABLE },
		{"代码", "code", 20, null, null, null, IColumnDefiner.EDITABLE }
	};
	
	
	
	public SysDpyResInstanceTreeTable() {
		super("资源实例",null);
		doper = new SysDpyResInstDoper();
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		IEpiAction[] epiAction = new IEpiAction[4];
		epiAction[0] = new EpiDeleteNodeAction(tree);
		epiAction[1] = new EpiRefreshAction(tree);
		epiAction[2] = new EpiFilterAction(tree);
		epiAction[3] = new EpiSaveAction(tree);
		
		toolBarManager.add(epiAction[0]);
		toolBarManager.add(epiAction[1]);
		toolBarManager.add(epiAction[2]);
		toolBarManager.add(epiAction[3]);
		toolBarManager.update(false);
	}

	@Override
	//protected Control createAreaControl(Composite parent) {
	public void assembleControl(Composite parent) {
		definer = new EpiTreeDefiner(null, columnsDefine);
		context = new ViewContext();
		tree = new EpiTree(parent, definer, context);
		//return tree;
	}

	public EpiSelectionProvider getSelectionProvider() {
		return tree.getSelectionProvider();
	}
	
	//刷新树表数据
	public void setSelection(ISelection selection) {	
		//点选“关联资源”的记录时，维护当前关联资源的“资源实例”；
		//添加“资源实例”时，可多选所属的部署方案；
		//依据所选择的部署方案，显示缺省的资源实例；
		//选择“过滤”按钮进行其它部署方案的

		Object firstElement = ((IStructuredSelection)selection).getFirstElement();
		SysDpyResourcesVo sysDpyResourcesVo = null;
		if(firstElement instanceof SysDpyResourcesVo) {
			sysDpyResourcesVo = (SysDpyResourcesVo)firstElement;
		}
		
		IEpiInput input = doper.loadSysDypResInstList(sysDpyResourcesVo);
		context.setInput(input);
		tree.getViewer().setInput(context.getInput());
		tree.getViewer().refresh();
	}

}
