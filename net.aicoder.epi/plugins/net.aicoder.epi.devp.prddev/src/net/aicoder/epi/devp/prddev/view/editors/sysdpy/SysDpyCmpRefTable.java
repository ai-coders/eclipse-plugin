package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.control.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.control.table.EpiTable;
import net.aicoder.epi.base.view.control.table.EpiTableDefiner;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;
import net.aicoder.epi.devp.prddev.doper.dev.system.SysElementDoper;
import net.aicoder.epi.devp.prddev.model.dev.ProductDevVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysCmpVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElmCatgVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

/**
 * 部署模型-XXX组件(表)
 * @author WANGQINGPING
 *
 */
public class SysDpyCmpRefTable extends BaseTitleArea {
	public static final String ID = SysDpyCmpRefTable.class.getName();
	private EpiTable table;
	private EpiTableDefiner definer;
	private IViewContext context;
	private SysElementDoper doper;
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"关联类型", "code", -20, null, null, null, IColumnDefiner.EDITABLE },
		{"关联元素", "name", 20, null, null, null, IColumnDefiner.EDITABLE },
		{"别名", "alias", },
		{"类型", "type", },
		{"描述", "description", 0, null, null, null, IColumnDefiner.EDITABLE }
	};

	public SysDpyCmpRefTable() {
		super("XXX组件",null);
		doper = new SysElementDoper();
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
	//protected Control createAreaControl(Composite parent) {
	public void assembleControl(Composite parent) {
		definer = new EpiTableDefiner(null, columnsDefine);
		IEpiEditorInput editorInput = (IEpiEditorInput)this.getEditorInput();
		IBaseVo currentData = editorInput.getCurrentData();
		PrdProductVo product = null;
		if(currentData instanceof SysElmCatgVo) {
			ProductDevVo productDev = (ProductDevVo) ((SysElmCatgVo)currentData).getParentNode();
			product = productDev.getProduct();
		}else if(currentData instanceof PrdProductVo){
			product = (PrdProductVo)currentData;
		}
		List<IBaseVo> dataList = doper.listSysDpyElement(product);
		IEpiInput input = new EpiInput();
		input.setDataList(dataList);
		context = new ViewContext();
		context.setInput(input);

		table = new EpiTable(parent, definer, context);
		//return table;
	}
	
	public EpiSelectionProvider getSelectionProvider() {
		return table.getSelectionProvider();
	}
	
	/**
	 * 刷新列表数据
	 * @param selection
	 */
	public void setSelection(ISelection selection) {
		Object firstElement = ((StructuredSelection)selection).getFirstElement();
		SysCmpVo sysCmpVo = null;
		if(firstElement instanceof SysCmpVo) {
			sysCmpVo = (SysCmpVo)firstElement;
		}
		
		
		
		 
		
	}
	
}
