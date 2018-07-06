package net.aicoder.epi.devp.prddev.view.opsexploer;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiEditorInput;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.control.table.EpiTable;
import net.aicoder.epi.base.view.control.table.EpiTableDefiner;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;
import net.aicoder.epi.devp.DevpConstant;
import net.aicoder.epi.devp.prddev.PrddevImageConstant;
import net.aicoder.epi.devp.prddev.doper.ops.ProductOpsDoper;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElementVo;
import net.aicoder.epi.devp.prddev.view.editors.product.ProductEditor;

/**
 * 产品发布导航-子元素-表区域
 * @author WANGQINGPING
 *
 */
public class ProductOpsFolderSubElmArea extends BaseTitleArea {
	public static final String ID = ProductOpsFolderSubElmArea.class.getName();
	private ProductOpsDoper productOpsDoper;
	
	// 0-数据类型, 1-图片, 2-Text对应变量名(缺省为name), 3-Description对应变量名(缺省为description), 4-双击对应的动作
	private static Object[][] viewDefine = {
			{EtypeEnum.PRODUCT.etype(), PrddevImageConstant.getImage(PrddevImageConstant.PRODUCT) , null, null, ProductEditor.ID},
			{DevpConstant.EXT_PRODUCT, PrddevImageConstant.getImage(PrddevImageConstant.PRODUCT_DIS) ,},
	};

	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
			{"Name", "name", -20, },
			{"Path", "fullPath", },
	};
	
	
		
	public ProductOpsFolderSubElmArea() {
		super();
		this.productOpsDoper = new ProductOpsDoper();
		setTitleText("子元素");
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	@Override
	//protected Control createAreaControl(Composite parent) {
	public void assembleControl(Composite parent) {
		EpiTableDefiner tableDefiner = new EpiTableDefiner(viewDefine, columnsDefine);
		ProductSubElmTableAreaContext productSubElmTableAreaContext = new ProductSubElmTableAreaContext();
		
		EpiTable epiTable = new EpiTable(parent, tableDefiner, productSubElmTableAreaContext);
		//return epiTable;
	}
	
	
	private class ProductSubElmTableAreaContext extends ViewContext{

		public ProductSubElmTableAreaContext() {
			super();
			setInput(createInput(null));
			IBaseVo currentData = getInput().getCurrentData();
			setEditorInput(createEditorInput(currentData));
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public IEpiInput createInput(IBaseVo selectionElement) {
			SysElementVo currElm = new SysElementVo();
			currElm.setRid(1001);
			currElm.setCode("ELM_C1");
			currElm.setName("ELM_N1");
			
			EpiInput epiInput = productOpsDoper.loadProductOpsFolderSubElmList(currElm);
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
