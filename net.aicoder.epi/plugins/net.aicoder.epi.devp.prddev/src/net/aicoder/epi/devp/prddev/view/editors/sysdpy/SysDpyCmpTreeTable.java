package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.EpiAddAction;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.action.tree.EpiAddChildAction;
import net.aicoder.epi.base.view.action.tree.EpiDeleteNodeAction;
import net.aicoder.epi.base.view.action.tree.EpiDownRowAction;
import net.aicoder.epi.base.view.action.tree.EpiFilterAction;
import net.aicoder.epi.base.view.action.tree.EpiRefreshAction;
import net.aicoder.epi.base.view.action.tree.EpiUpRowAction;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.control.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.control.tree.EpiTree;
import net.aicoder.epi.base.view.control.tree.EpiTreeDefiner;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;
import net.aicoder.epi.devp.prddev.doper.ops.SysCmpDoper;
import net.aicoder.epi.devp.prddev.model.dev.ProductDevVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElmCatgVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

/**
 * 部署模型-组件(树表)
 * @author WANGQINGPING
 *
 */
public class SysDpyCmpTreeTable extends BaseTitleArea{
	public static String ID = SysDpyCmpTreeTable.class.getName();
	private EpiTree tree;
	private EpiTreeDefiner definer;
	private IViewContext context;
	private SysCmpDoper doper;
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"名称*", "name", 20, null, null, null, IColumnDefiner.EDITABLE },
		{"代码", "code", -20, null, null, null, IColumnDefiner.EDITABLE },
		{"别名", "alias", },
		{"类型", "type", },
		{"版本", "version", },
		{"描述", "description", 0, null, null, null, IColumnDefiner.EDITABLE }
	};
	
	
	//构造
	public SysDpyCmpTreeTable() {
		super();
		doper = new SysCmpDoper();
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		IEpiAction[] epiAction = new IEpiAction[7];
		epiAction[0] = new EpiAddAction();
		epiAction[1] = new EpiAddChildAction(tree);
		epiAction[2] = new EpiUpRowAction(tree);
		epiAction[3] = new EpiDownRowAction(tree);
		epiAction[4] = new EpiDeleteNodeAction(tree);
		epiAction[5] = new EpiRefreshAction(tree);
		epiAction[6] = new EpiFilterAction(tree);
		
		toolBarManager.add(epiAction[0]);
		toolBarManager.add(epiAction[1]);
		toolBarManager.add(epiAction[2]);
		toolBarManager.add(epiAction[3]);
		toolBarManager.add(epiAction[4]);
		toolBarManager.add(epiAction[5]);
		toolBarManager.add(epiAction[6]);
		toolBarManager.update(false);
		
	}

	@Override
	//protected Control createAreaControl(Composite parent) {
	public void assembleControl(Composite parent) {
		IEpiEditorInput editorInput = (IEpiEditorInput)this.getEditorInput();
		IBaseVo currentData = editorInput.getCurrentData();
		PrdProductVo product = null;
		if(currentData instanceof SysElmCatgVo) {
			ProductDevVo productDev = (ProductDevVo) ((SysElmCatgVo)currentData).getParentNode();
			product = productDev.getProduct();
		}else if(currentData instanceof PrdProductVo){
			product = (PrdProductVo)currentData;
		}
		
		
		//点选XXX产品时，获取当前产品的系统、子系统、组件；
		//可新增/删除系统、子系统、组件等，及维护系统、子系统、组件结构
		IEpiInput input = doper.loadSysCmpList(product);
		
		definer = new EpiTreeDefiner(null, columnsDefine);
		context = new ViewContext();
		context.setInput(input);
		tree = new EpiTree(parent, definer, context);

		//return tree;
	}
	
	public EpiSelectionProvider getSelectionProvider() {
		return tree.getSelectionProvider();
	}
	
	public void setSelection(ISelection selection) {
		//刷新树表数据
	}

}
