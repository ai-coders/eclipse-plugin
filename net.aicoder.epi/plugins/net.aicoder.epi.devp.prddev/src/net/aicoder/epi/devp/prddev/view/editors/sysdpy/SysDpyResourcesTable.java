package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;
import net.aicoder.epi.base.view.element.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.element.table.EpiTable;
import net.aicoder.epi.base.view.element.table.EpiTableDefiner;
import net.aicoder.epi.devp.prddev.doper.ops.SysDpyResourcesDoper;
import net.aicoder.epi.devp.prddev.model.dev.ProductDevVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElmCatgVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

/**
 * 部署模型-关联资源(表)
 * @author WANGQINGPING
 *
 */
public class SysDpyResourcesTable extends BaseWithTitleArea{
	public static final String ID = SysDpyResourcesTable.class.getName();		
	private EpiTable table;
	private EpiTableDefiner definer;
	private IViewContext context;
	private SysDpyResourcesDoper doper;
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"关联类型", "type", -20, null, null, null, IColumnDefiner.CE_TEXT},
		{"关联名称", "name", 20, null, null, null, IColumnDefiner.CE_TEXT }
	};
	
	
	
	//Constructor
	public SysDpyResourcesTable() {
		super("关联资源",null);
		doper = new SysDpyResourcesDoper();
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
//		IEpiAction[] epiAction = new IEpiAction[4];
//		epiAction[0] = new EpiDeleteNodeAction(tree);
//		epiAction[1] = new EpiRefreshAction(tree);
//		epiAction[2] = new EpiFilterAction(tree);
//		epiAction[3] = new EpiSaveAction(tree);
//		
//		toolBarManager.add(epiAction[0]);
//		toolBarManager.add(epiAction[1]);
//		toolBarManager.add(epiAction[2]);
//		toolBarManager.add(epiAction[3]);
//		toolBarManager.update(false);
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		IEpiEditorInput editorInput = (IEpiEditorInput)this.getEditorInput();
		IBaseVo currentData = editorInput.getCurrentData();
		PrdProductVo product = null;
		if(currentData instanceof SysElmCatgVo) {
			ProductDevVo productDev = (ProductDevVo) ((SysElmCatgVo)currentData).getParentNode();
			product = productDev.getProduct();
		}else if(currentData instanceof PrdProductVo){
			product = (PrdProductVo)currentData;
		}
		
		//点选XXX产品时，获取当前产品的所关联的资源；关联资源为该产品所涉及的外部产品、及其它系统资源；关联资源类型有：部署到、连接（双向/请求）、调用	
		IEpiInput input = doper.loadSysDpyResourcesList(product);
		
		definer = new EpiTableDefiner(null, columnsDefine);
		context = new ViewContext();
		context.setInput(input);
		table = new EpiTable(parent, definer, context);
		
		return table;
	}
	
	public EpiSelectionProvider getSelectionProvider() {
		return table.getSelectionProvider();
	}
	
	public void setSelection(ISelection selection) {
		//刷新列表数据
		
		
	}


}
