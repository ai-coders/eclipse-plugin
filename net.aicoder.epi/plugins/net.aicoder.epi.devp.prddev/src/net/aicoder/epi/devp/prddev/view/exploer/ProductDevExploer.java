package net.aicoder.epi.devp.prddev.view.exploer;

import org.eclipse.ui.ISharedImages;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.adapter.EpiEditorInput;
import net.aicoder.epi.base.view.adapter.IEpiEditorInput;
import net.aicoder.epi.base.view.adapter.IEpiInput;
import net.aicoder.epi.base.view.explorer.EpiExplorer;
import net.aicoder.epi.base.view.explorer.EpiExplorerDefiner;
import net.aicoder.epi.devp.DevpConstant;
import net.aicoder.epi.devp.prddev.PrddevImageConstant;
import net.aicoder.epi.devp.prddev.doper.ProductDevDoper;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.devp.prddev.view.editors.function.ModuleEditor;
import net.aicoder.epi.devp.prddev.view.editors.product.ProductEditor;
import net.aicoder.epi.devp.prddev.view.editors.syscmp.SysCmpEditor;
import net.aicoder.epi.devp.prddev.view.editors.sysdpy.SysDpyEditor;
import net.aicoder.epi.devp.prddev.view.editors.syside.SysIdeEditor;
import net.aicoder.epi.util.ImageUtil;

public class ProductDevExploer extends EpiExplorer{
	public static String ID = ProductDevExploer.class.getName();

	// 0-数据类型, 1-图片, 2-Text对应变量名(缺省为name), 3-Description对应变量名(缺省为description), 4-双击对应的动作
	private static Object[][] viewDefine = {
			{EtypeEnum.PRODUCT.etype(), PrddevImageConstant.getImage(PrddevImageConstant.PRODUCT) , null, null, ProductEditor.ID},
			{DevpConstant.EXT_PRODUCT, PrddevImageConstant.getImage(PrddevImageConstant.PRODUCT_DIS) ,},
			
			// 功能元素
			{DevpConstant.CATEGORY_FUN, ImageUtil.getImage(ISharedImages.IMG_OBJ_FOLDER) , null, null, ModuleEditor.ID},
			{EtypeEnum.MODULE.etype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_DEPLOY)},
			{EtypeEnum.FUNCTION.etype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_CPNT)},
			
			// 系统组件元素
			{DevpConstant.CATEGORY_CMP, ImageUtil.getImage(ISharedImages.IMG_OBJ_FOLDER) , null, null, SysCmpEditor.ID},
			{EtypeEnum.SYSTEM.etype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_DEPLOY)},
			{EtypeEnum.SYSCMP.etype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_CPNT)},
			
			// 系统开发元素
			{DevpConstant.CATEGORY_IDE, ImageUtil.getImage(ISharedImages.IMG_OBJ_FOLDER) , null, null, SysIdeEditor.ID},
			{EtypeEnum.SYSIDEFLD.etype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_IDEPRJ)},
			{EtypeEnum.SYSIDEPRJ.etype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_IDEPRJ)},
			
			// 系统部署元素
			{DevpConstant.CATEGORY_DPY, ImageUtil.getImage(ISharedImages.IMG_OBJ_FOLDER) , null, null, SysDpyEditor.ID},
			{EtypeEnum.SYSDPYENV.etype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_IDEPRJ)},
	};
	
	private ProductDevDoper doper;
	
	public ProductDevExploer() {
		super();
		doper = new ProductDevDoper();
		ProductDevExploerDefiner definer = new ProductDevExploerDefiner(viewDefine);
		definer.createAdapter(null);
		this.setDefiner(definer);
	}
	
	class ProductDevExploerDefiner extends EpiExplorerDefiner{
		public ProductDevExploerDefiner(Object[][] viewDefine) {
			super(viewDefine);
		}
		
		@Override
		public IEpiInput createAdapter(IBaseVo selectionElement) {
			PrdProductVo currProduct = new PrdProductVo();
			currProduct.setRid(1001);
			currProduct.setCode("PD_C1");
			currProduct.setName("PD_N1");

			IEpiInput input = doper.loadProductList(currProduct);
			setInput(input);
			return input;
		}

		@Override
		public IEpiEditorInput createEditorInput(IBaseVo selectionElement) {
			EpiEditorInput editorInput = new EpiEditorInput();
			//doper.getSelectProductAsEditorInput(selectionElement);
			setEditorInput(editorInput);
			return editorInput;
		}
	}
	
}
