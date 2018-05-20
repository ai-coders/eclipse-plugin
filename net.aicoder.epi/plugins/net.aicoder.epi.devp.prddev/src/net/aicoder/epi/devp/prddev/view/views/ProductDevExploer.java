package net.aicoder.epi.devp.prddev.view.views;

import org.eclipse.ui.ISharedImages;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.view.explorer.EpiExplorer;
import net.aicoder.epi.devp.DevpConstant;
import net.aicoder.epi.devp.prddev.PrddevImageConstant;
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
			{EtypeEnum.PRODUCT.getEtype(), PrddevImageConstant.getImage(PrddevImageConstant.PRODUCT) , null, null, ProductEditor.ID},
			
			// 功能元素
			{DevpConstant.CATEGORY_FUN, ImageUtil.getImage(ISharedImages.IMG_OBJ_FOLDER) , null, null, ModuleEditor.ID},
			{EtypeEnum.MODULE.getEtype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_DEPLOY)},
			{EtypeEnum.FUNCTION.getEtype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_CPNT)},
			
			// 系统组件元素
			{DevpConstant.CATEGORY_CMP, ImageUtil.getImage(ISharedImages.IMG_OBJ_FOLDER) , null, null, SysCmpEditor.ID},
			{EtypeEnum.SYSTEM.getEtype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_DEPLOY)},
			{EtypeEnum.SYSCMP.getEtype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_CPNT)},
			
			// 系统开发元素
			{DevpConstant.CATEGORY_IDE, ImageUtil.getImage(ISharedImages.IMG_OBJ_FOLDER) , null, null, SysIdeEditor.ID},
			{EtypeEnum.SYSIDEFLD.getEtype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_IDEPRJ)},
			{EtypeEnum.SYSIDEPRJ.getEtype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_IDEPRJ)},
			
			// 系统开发元素
			{DevpConstant.CATEGORY_DPY, ImageUtil.getImage(ISharedImages.IMG_OBJ_FOLDER) , null, null, SysDpyEditor.ID},
			{EtypeEnum.SYSDPYENV.getEtype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_IDEPRJ)},
	};
	
	public ProductDevExploer() {
		super();
		
	}
}
