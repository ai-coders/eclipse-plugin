package net.aicoder.epi.devp.prddev.view.devexploer;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISharedImages;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.control.tree.EpiTree;
import net.aicoder.epi.base.view.control.tree.EpiTreeDefiner;
import net.aicoder.epi.base.view.part.area.BaseArea;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;
import net.aicoder.epi.base.view.part.area.TabArea;
import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.devp.DevpConstant;
import net.aicoder.epi.devp.prddev.PrddevImageConstant;
import net.aicoder.epi.devp.prddev.doper.dev.ProductDevDoper;
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
	private TabArea tabArea;
	
	@Override
	public void initArea() {
		IArea[] areas = new IArea[2];
		areas[0] = new ProductDevDgmTable();
		areas[1] = new ProductDevSubElmTable();
		
		tabArea = new TabArea(this.getWorkbenchPart());
		tabArea.setEpiAreas(areas);		
	}

	@Override
	//protected Control createAreaControl(Composite parent) {
	public void assembleControl(Composite parent) {
		tabArea.createControl(parent);
		tabArea.getTabFolder().setSelection(0);
		//return tabArea.getControl();
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
	}

	private IEpiInput createInput(IBaseVo selectionElement) {
		return null;
	}
}
