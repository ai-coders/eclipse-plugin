package net.aicoder.epi.devp.prddev.view.opsexploer;

import java.util.ArrayList;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiEditorInput;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;
import net.aicoder.epi.base.view.element.table.EpiTable;
import net.aicoder.epi.base.view.element.table.EpiTableDefiner;
import net.aicoder.epi.devp.prddev.PrddevImageConstant;
import net.aicoder.epi.devp.prddev.doper.ops.ProductOpsDoper;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElementVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

/**
 * 产品发布导航-设计图-表区域
 * @author WANGQINGPING
 *
 */
public class ProductOpsFolderDesDgmArea extends BaseWithTitleArea{
	public static final String ID = ProductOpsFolderDesDgmArea.class.getName();
	private ProductOpsDoper productOpsDoper;
	private EpiTableDefiner tableDefiner;//Table定义
	private ProductDesDgmTableAreaContext productDesDgmTableAreaContext;//Input,EditorInput定义
	private EpiTable epiTable;
	
	// 0-数据类型, 1-图片, 2-Text对应变量名(缺省为name), 3-Description对应变量名(缺省为description), 4-双击对应的动作
	private Object[][] viewDefine = {
			{EtypeEnum.PRODUCT.etype(),PrddevImageConstant.getImage(PrddevImageConstant.PRODUCT),}
	};
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
			{"名称", "name", -20, },
			{"目录", "fullPath", },
	};
	
	
	public ProductOpsFolderDesDgmArea() {
		super();
		this.productOpsDoper = new ProductOpsDoper();
		setTitleText("设计图");
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		tableDefiner = new EpiTableDefiner(viewDefine,columnsDefine);
		productDesDgmTableAreaContext = new ProductDesDgmTableAreaContext();
		epiTable = new EpiTable(parent, tableDefiner, productDesDgmTableAreaContext);
		return epiTable;
	}
	
	/**
	 * 在浏览视图里点击产品之后回调方法
	 * @param selection
	 */
	public void setElementSelection(ISelection selection) {
		Object item = ((IStructuredSelection)selection).getFirstElement();
		if(item instanceof PrdProductVo) {
			//依据条件做查询操作
			PrdProductVo currentData = (PrdProductVo)item;
			
			SysElementVo currElm = new SysElementVo();
			currElm.setRid(1001);
			currElm.setCode(currentData.getCode());
			currElm.setName(currentData.getName());
			
			EpiInput epiInput = productOpsDoper.loadProductOpsFolderDesDgmList(currElm);
			productDesDgmTableAreaContext.setInput(epiInput);
			epiTable.getViewer().setInput(productDesDgmTableAreaContext.getInput());
			
			EpiEditorInput editorInput = new EpiEditorInput(currentData);
			productDesDgmTableAreaContext.setEditorInput(editorInput);
			
			epiTable.getViewer().refresh();
			System.out.println(currentData.toString());
		}else {
			
		}
		
	}
	
	private class ProductDesDgmTableAreaContext extends ViewContext{

		public ProductDesDgmTableAreaContext() {
			super();
			setInput(createInput(null));
			IBaseVo currentData = getInput().getCurrentData();
			setEditorInput(createEditorInput(currentData));
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public IEpiInput createInput(IBaseVo selectionElement) {
//			SysElementVo currElm = new SysElementVo();
//			currElm.setRid(1001);
//			currElm.setCode("ELM_C1");
//			currElm.setName("ELM_N1");
			
//			EpiInput epiInput = productOpsDoper.loadProductOpsFolderDesDgmList(currElm);
			EpiInput epiInput = new EpiInput();
			epiInput.setDataList(new ArrayList<>());
			return epiInput;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public IEpiEditorInput createEditorInput(IBaseVo selectionElement) {
			EpiEditorInput editorInput = new EpiEditorInput(selectionElement);
			return editorInput;
		}
		
	}

}
