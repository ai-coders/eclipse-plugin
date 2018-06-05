package net.aicoder.epi.devp.prddev.view.views;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.adapter.IEpiInput;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;
import net.aicoder.epi.base.view.element.table.EpiTable;
import net.aicoder.epi.base.view.element.table.EpiTableDefiner;
import net.aicoder.epi.devp.DevpConstant;
import net.aicoder.epi.devp.prddev.PrddevImageConstant;
import net.aicoder.epi.devp.prddev.doper.ProductDevDoper;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElementVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.devp.prddev.view.editors.product.ProductEditor;

public class ProductDevDgmTable extends BaseWithTitleArea{
	public static String ID = ProductDevDgmTable.class.getName();
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

	private EpiTable table;
	private EpiTableDefiner definer;
	private ProductDevDoper doper;
	
	public ProductDevDgmTable() {
		super();
		doper = new ProductDevDoper();
		this.setTitleText("设计图");
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		definer = new EpiTableDefiner(viewDefine, columnsDefine);
		IEpiInput input = createInput(null);
		definer.setInput(input);
		table = new EpiTable(parent, definer);
		return table;
	}
	
	private IEpiInput createInput(IBaseVo selectionElement) {
		SysElementVo currElm = new SysElementVo();
		currElm.setRid(1001);
		currElm.setCode("ELM_C1");
		currElm.setName("ELM_N1");

		IEpiInput input = doper.loadDevDgmList(currElm);
		return input;
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
	}
}
