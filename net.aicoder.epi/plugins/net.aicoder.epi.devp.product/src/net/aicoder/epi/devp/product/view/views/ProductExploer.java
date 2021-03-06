package net.aicoder.epi.devp.product.view.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.EpiAddAction;
import net.aicoder.epi.base.view.action.EpiDeleteAction;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.context.EpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.explorer.EpiExplorer;
import net.aicoder.epi.base.view.explorer.EpiExplorerDefiner;
import net.aicoder.epi.devp.DevpConstant;
import net.aicoder.epi.devp.product.ProductImageConstant;
import net.aicoder.epi.devp.product.doper.ProductDoper;
import net.aicoder.epi.devp.product.view.editors.ProductEditor;
import net.aicoder.epi.util.ImageUtil;

public class ProductExploer extends EpiExplorer {
	public static String ID = ProductExploer.class.getName();
	
	// 0-数据类型, 1-图片, 2-Text对应变量名(缺省为name), 3-Description对应变量名(缺省为description), 4-双击对应的动作
	private static Object[][] viewDefine = {
			{DevpConstant.E_PRDLINE, ProductImageConstant.getImage(ProductImageConstant.E_PRDLINE) , },
			{DevpConstant.E_PRODUCT, ProductImageConstant.getImage(ProductImageConstant.E_PRODUCT) , null, null, ProductEditor.ID},
			{DevpConstant.CATEGORY, ImageUtil.getImage(ISharedImages.IMG_OBJ_FOLDER) , },
			{DevpConstant.E_PRD_DEPLOY, ProductImageConstant.getImage(ProductImageConstant.E_PRD_DEPLOY) , null, null, ProductEditor.ID},
			{DevpConstant.E_PRD_CPNT, ProductImageConstant.getImage(ProductImageConstant.E_PRD_CPNT) , null, null, ProductEditor.ID},
			{DevpConstant.E_PRD_IDEPRJ, ProductImageConstant.getImage(ProductImageConstant.E_PRD_IDEPRJ) , null, null, ProductEditor.ID },
	};
	
	private IEpiAction[] toolBarAactons;
	private ProductDoper dataoper;

	
	public ProductExploer() {
		super();
		dataoper = new ProductDoper();
		EpiExplorerDefiner definer = new EpiExplorerDefiner(viewDefine);
		
		IViewContext context = new ProductExploerContext();
		//definer.createInput(null);
		//this.setDefiner(context.getEditorInput(null));
		this.setDefiner(definer);
		this.setViewContext(context);
	}
	
	@Override
	protected void hookToolBars(){
		toolBarAactons = new IEpiAction[2];
		toolBarAactons[0] = new EpiAddAction();
		toolBarAactons[1] = new EpiDeleteAction();
		
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}
	
	private void fillLocalPullDown(IMenuManager manager) {
/**
		//为持久化UI状态提供支持
		if(memento != null)
			filterAction.init(memento);
		//增加操作
		manager.add(filterAction);
**/
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		for(IEpiAction action:toolBarAactons) {
			manager.add(action);
			
			if(action.isCheckVisibleWhenCount()) {
				action.setEnabledByCount(null);
				this.getCommonViewer().addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection)event.getSelection();
						action.setEnabledByCount(selection);
					}
				});
			}
		}
		
	}
	

	@Override
	protected void hookContextMenu() {
		
	}
	
	@Override
	protected void hookKeybordActions(){
		
	}
	
	@SuppressWarnings("unchecked")
	class ProductExploerContext extends ViewContext{
		public ProductExploerContext() {
			super();
			setInput(createInput(null));
			IBaseVo currData = getInput().getCurrentData();
			setEditorInput(createEditorInput(currData));
		}
		
		@Override
		public IEpiInput createInput(IBaseVo selectionElement) {
			IEpiInput input = dataoper.loadProductList(selectionElement);
			setInput(input);
			return input;
		}

		@Override
		public IEpiEditorInput createEditorInput(IBaseVo selectionElement) {
			EpiEditorInput editorInput = dataoper.getSelectProductAsEditorInput(selectionElement);
			setEditorInput(editorInput);
			return editorInput;
		}
	}
}

