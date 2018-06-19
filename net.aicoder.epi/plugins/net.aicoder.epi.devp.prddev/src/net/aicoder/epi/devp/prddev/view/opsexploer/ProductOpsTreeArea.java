package net.aicoder.epi.devp.prddev.view.opsexploer;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISharedImages;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiEditorInput;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;
import net.aicoder.epi.base.view.element.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.element.tree.EpiTree;
import net.aicoder.epi.base.view.element.tree.EpiTreeDefiner;
import net.aicoder.epi.devp.prddev.PrddevImageConstant;
import net.aicoder.epi.devp.prddev.doper.ops.ProductOpsDoper;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.devp.prddev.view.editors.sysdpy.SysDpyEditor;

/**
 * 产品发布导航-产品分组-浏览区域
 * @author WANGQINGPING
 *
 */
public class ProductOpsTreeArea extends BaseWithTitleArea{
	public static final String ID = ProductOpsTreeArea.class.getName();
	private ProductOpsDoper productOpsDoper;
	private EpiTree tree;
	
	//Tree定义
	// 0-数据类型, 1-图片, 2-Text对应变量名(缺省为name), 3-Description对应变量名(缺省为description), 4-双击对应的动作
	private Object[][] viewDefine = {
			//应用发布-XXX产品-部署模型
			{EtypeEnum.PRD_GROUP.etype(),PrddevImageConstant.getImage(PrddevImageConstant.PRODUCT),},
			{EtypeEnum.PRODUCT.etype(),PrddevImageConstant.getImage(ISharedImages.IMG_OBJ_FOLDER),null,null,SysDpyEditor.ID},
	};
	
	
	public ProductOpsTreeArea() {
		super();
		this.productOpsDoper = new ProductOpsDoper();
		//设置标题
		setTitleText("产品发布导航");
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {

	}

	@Override
	protected Control createAreaControl(Composite parent) {
		EpiTreeDefiner treeDefiner = new EpiTreeDefiner(viewDefine);
		ProductOpsTreeAreaContext productOpsTreeContext = new ProductOpsTreeAreaContext();
		tree = new EpiTree(parent, treeDefiner, productOpsTreeContext);
		tree.getTree().setHeaderVisible(false);
		tree.getTree().setLinesVisible(false);
		return tree;
	}
	
	public EpiSelectionProvider getSelectionProvider() {
		return tree.getSelectionProvider();
	}
	
	/**
	 * 获取数据
	 * @author WANGQINGPING
	 *
	 */
	private class ProductOpsTreeAreaContext extends ViewContext{

		public ProductOpsTreeAreaContext() {
			super();
//			setInput(createInput(null));
			IBaseVo currentData = getInput().getCurrentData();
			setEditorInput(createEditorInput(currentData));
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public IEpiInput createInput(IBaseVo selectionElement) {
			//请求数据
			PrdProductVo currProduct = new PrdProductVo();
			currProduct.setRid(1001);
			currProduct.setCode("PD_C1");
			currProduct.setName("PD_N1");
			
			EpiInput epiInput = productOpsDoper.loadProductGroupList(currProduct);
			return epiInput;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public IEpiEditorInput createEditorInput(IBaseVo selectionElement) {
			EpiEditorInput epiEditorInput = new EpiEditorInput(selectionElement);
			return epiEditorInput;
		}
		
	}

}