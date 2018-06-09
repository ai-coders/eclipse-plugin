package net.aicoder.epi.devp.prddev.view.devexploer;

import org.eclipse.swt.widgets.Composite;

import net.aicoder.epi.base.view.BaseViewPart;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;

public class ProductDevExploer extends BaseViewPart{
	public static String ID = ProductDevExploer.class.getName();
	
	EpiSashArea sashArea;

	@Override
	public IEpiArea newPageArea() {
		sashArea = new EpiSashArea(this);
		return sashArea;
	}

	@Override
	public void createControl(Composite parent) {
		int[] weights = new int[2];
		weights[0] = 2;
		weights[1] = 1;
		
		IEpiArea[] areas = new IEpiArea[2];
		areas[0] = new ProductDevTree();
		areas[1] = new ProductDevDownArea();
		
		sashArea.setEpiAreas(areas);
		sashArea.setAreaWeights(weights);
		sashArea.createControl(parent);
	}

	@Override
	public void setFocus() {
		sashArea.setFocus();
	}

}
