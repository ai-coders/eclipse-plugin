package net.aicoder.epi.devp.prddev.view.views;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISharedImages;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.adapter.IEpiInput;
import net.aicoder.epi.base.view.element.area.BaseArea;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;
import net.aicoder.epi.base.view.element.area.EpiTabArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;
import net.aicoder.epi.base.view.element.tree.EpiTree;
import net.aicoder.epi.base.view.element.tree.EpiTreeDefiner;
import net.aicoder.epi.devp.DevpConstant;
import net.aicoder.epi.devp.prddev.PrddevImageConstant;
import net.aicoder.epi.devp.prddev.doper.ProductDevDoper;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.devp.prddev.view.editors.function.ModuleEditor;
import net.aicoder.epi.devp.prddev.view.editors.product.ProductEditor;
import net.aicoder.epi.devp.prddev.view.editors.syscmp.SysCmpEditor;
import net.aicoder.epi.devp.prddev.view.editors.sysdpy.SysDpyEditor;
import net.aicoder.epi.devp.prddev.view.editors.syside.SysIdeEditor;
import net.aicoder.epi.example.area.TableEditorArea;
import net.aicoder.epi.example.area.TableEditorWithTitleArea;
import net.aicoder.epi.util.ImageUtil;

public class ProductDevDownArea extends BaseArea {
	public static String ID = ProductDevDownArea.class.getName();

	@Override
	protected Control createAreaControl(Composite parent) {
		EpiTabArea tabArea = new EpiTabArea();
		IEpiArea[] areas = new IEpiArea[2];
		//areas[0] = new TableEditorArea();
		//areas[1] = new TableEditorWithTitleArea();
		areas[0] = new ProductDevDgmTable();
		areas[1] = new ProductDevSubElmTable();
		
		tabArea.setEpiAreas(areas);
		tabArea.createControl(parent);
		tabArea.getTabFolder().setSelection(0);
		
		return tabArea.getControl();
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
	}

	private IEpiInput createInput(IBaseVo selectionElement) {
		return null;
	}
}