package net.aicoder.epi.devp.prddev.view.views;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.BaseViewPart;
import net.aicoder.epi.base.view.IViewElement;
import net.aicoder.epi.base.view.definer.IViewDefiner;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;
import net.aicoder.epi.devp.DevpConstant;
import net.aicoder.epi.devp.prddev.PrddevImageConstant;
import net.aicoder.epi.devp.prddev.view.editors.function.ModuleEditor;
import net.aicoder.epi.devp.prddev.view.editors.product.ProductEditor;
import net.aicoder.epi.devp.prddev.view.editors.syscmp.SysCmpEditor;
import net.aicoder.epi.devp.prddev.view.editors.sysdpy.SysDpyEditor;
import net.aicoder.epi.devp.prddev.view.editors.syside.SysIdeEditor;
import net.aicoder.epi.util.ImageUtil;

public class ProductDevExploer extends BaseViewPart{
	public static String ID = ProductDevExploer.class.getName();
	
	EpiSashArea sashArea;

	@Override
	public void createPartControl(Composite parent) {
		int[] weights = new int[2];
		weights[0] = 2;
		weights[1] = 1;
		
		IEpiArea[] areas = new IEpiArea[2];
		areas[0] = new ProductDevTree();
		areas[1] = new ProductDevDownArea();
		
		sashArea = new EpiSashArea();
		sashArea.setEpiAreas(areas);
		sashArea.setAreaWeights(weights);
		sashArea.createControl(parent);
	}

	@Override
	public void setFocus() {
		sashArea.setFocus();
	}
}
