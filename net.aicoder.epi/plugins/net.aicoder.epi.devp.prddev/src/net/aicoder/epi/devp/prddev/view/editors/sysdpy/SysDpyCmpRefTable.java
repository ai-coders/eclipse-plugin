package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.action.tree.EpiDeleteNodeAction;
import net.aicoder.epi.base.view.action.tree.EpiFilterAction;
import net.aicoder.epi.base.view.action.tree.EpiRefreshAction;
import net.aicoder.epi.base.view.action.tree.EpiSaveAction;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;
import net.aicoder.epi.base.view.element.tree.EpiTree;
import net.aicoder.epi.base.view.element.tree.EpiTreeDefiner;
import net.aicoder.epi.devp.prddev.doper.dev.system.SysElementDoper;
import net.aicoder.epi.devp.prddev.model.dev.ProductDevVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElmCatgVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

/**
 * 部署模型-子区域-右边区域-下边区域
 * @author WANGQINGPING
 *
 */
public class SysDpyCmpRefTable extends BaseWithTitleArea {
	public static final String ID = SysDpyCmpRefTable.class.getName();
	private EpiTree tree;
	private EpiTreeDefiner definer;
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
	protected Control createAreaControl(Composite parent) {
		definer = new EpiTreeDefiner(null, columnsDefine);
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

		tree = new EpiTree(parent, definer, context);
		return tree;
	}
	
}
