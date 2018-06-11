package net.aicoder.epi.devp.prddev.view.devexploer;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISharedImages;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.EpiAddAction;
import net.aicoder.epi.base.view.action.EpiDeleteAction;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.context.EpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;
import net.aicoder.epi.base.view.element.tree.EpiTree;
import net.aicoder.epi.base.view.element.tree.EpiTreeDefiner;
import net.aicoder.epi.devp.DevpConstant;
import net.aicoder.epi.devp.prddev.PrddevImageConstant;
import net.aicoder.epi.devp.prddev.doper.dev.ProductDevDoper;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.devp.prddev.view.editors.function.ModuleEditor;
import net.aicoder.epi.devp.prddev.view.editors.product.ProductEditor;
import net.aicoder.epi.devp.prddev.view.editors.syscmp.SysCmpEditor;
import net.aicoder.epi.devp.prddev.view.editors.sysdpy.SysDpyEditor;
import net.aicoder.epi.devp.prddev.view.editors.syside.SysIdeEditor;
import net.aicoder.epi.util.ImageUtil;

public class ProductDevTree extends BaseWithTitleArea {
	public static String ID = ProductDevTree.class.getName();

	// 0-数据类型, 1-图片, 2-Text对应变量名(缺省为name), 3-Description对应变量名(缺省为description),
	// 4-双击对应的动作
	private static Object[][] viewDefine = {
			{ EtypeEnum.PRODUCT.etype(), PrddevImageConstant.getImage(PrddevImageConstant.PRODUCT), null, null,
					ProductEditor.ID },
			{ DevpConstant.EXT_PRODUCT, PrddevImageConstant.getImage(PrddevImageConstant.PRODUCT_DIS), },

			// 功能元素
			{ DevpConstant.CATEGORY_FUN, ImageUtil.getImage(ISharedImages.IMG_OBJ_FOLDER), null, null,
					ModuleEditor.ID },
			{ EtypeEnum.MODULE.etype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_DEPLOY) },
			{ EtypeEnum.FUNCTION.etype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_CPNT) },

			// 系统组件元素
			{ DevpConstant.CATEGORY_CMP, ImageUtil.getImage(ISharedImages.IMG_OBJ_FOLDER), null, null,
					SysCmpEditor.ID },
			{ EtypeEnum.SYSTEM.etype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_DEPLOY) },
			{ EtypeEnum.SYS_CMP.etype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_CPNT) },

			// 系统开发元素
			{ DevpConstant.CATEGORY_IDE, ImageUtil.getImage(ISharedImages.IMG_OBJ_FOLDER), null, null,
					SysIdeEditor.ID },
			{ EtypeEnum.SYS_IDE_FLD.etype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_IDEPRJ) },
			{ EtypeEnum.SYS_IDE_PRJ.etype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_IDEPRJ) },

			// 系统部署元素
			{ DevpConstant.CATEGORY_DPY, ImageUtil.getImage(ISharedImages.IMG_OBJ_FOLDER), null, null,
					SysDpyEditor.ID },
			{ EtypeEnum.SYS_DPY_ENV.etype(), PrddevImageConstant.getImage(PrddevImageConstant.E_PRD_IDEPRJ) }, };

	private EpiTree tree;
	private EpiTreeDefiner definer;
	private IViewContext context;
	private ProductDevDoper doper;
	private IEpiAction[] toolBarAactons;

	public ProductDevTree() {
		super();
		doper = new ProductDevDoper();
		// this.setTitleText("产品构建导航");
		// this.setTitleImage(PrddevImageConstant.getImage(PrddevImageConstant.PRODUCT));
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		toolBarAactons = new IEpiAction[2];
		toolBarAactons[0] = new EpiAddAction();
		toolBarAactons[1] = new EpiDeleteAction();
		toolBarManager.add(toolBarAactons[0]);
		toolBarManager.add(toolBarAactons[1]);
		toolBarManager.update(false);
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		definer = new EpiTreeDefiner(viewDefine);
		
		context = new ProductDevTreeContext();
		//IEpiInput input = createInput(null);
		// definer.setInput(input);
		//IEpiEditorInput editorInput = createEditorInput(null);
		// definer.setEditorInput(editorInput);

		tree = new EpiTree(parent, definer, context);
		tree.getTree().setHeaderVisible(false);
		tree.getTree().setLinesVisible(false);
		return tree;
	}

	@SuppressWarnings("unchecked")
	class ProductDevTreeContext extends ViewContext{
		public ProductDevTreeContext() {
			super();
			setInput(createInput(null));
			IBaseVo currData = getInput().getCurrentData();
			setEditorInput(createEditorInput(currData));
		}
		
		@Override
		public IEpiInput createInput(IBaseVo selectionElement) {
			PrdProductVo currProduct = new PrdProductVo();
			currProduct.setRid(1001);
			currProduct.setCode("PD_C1");
			currProduct.setName("PD_N1");

			IEpiInput input = doper.loadProductList(currProduct);
			return input;
		}

		@Override
		public IEpiEditorInput createEditorInput(IBaseVo selectionElement) {
			EpiEditorInput editorInput = new EpiEditorInput(selectionElement);
			
			return editorInput;
		}
	}

}
