package net.aicoder.epi.devp.prddev.view.devexploer;

import org.eclipse.swt.widgets.Composite;

import net.aicoder.epi.base.view.part.BaseViewPart;
import net.aicoder.epi.base.view.part.area.SashArea;
import net.aicoder.epi.base.view.part.area.IArea;

public class ProductDevExploer extends BaseViewPart{
	public static String ID = ProductDevExploer.class.getName();
	
	SashArea sashArea;

	@Override
	public IArea newPageArea() {
		int[] weights = new int[2];
		weights[0] = 2;
		weights[1] = 1;
		
		IArea[] areas = new IArea[2];
		areas[0] = new ProductDevTree();
		areas[1] = new ProductDevDownArea();
		
		sashArea = new SashArea(this);
		sashArea.setAreas(areas);
		sashArea.setWeights(weights);

		return sashArea;
	}

	@Override
	public void createControl(Composite parent) {
		sashArea.createControl(parent);
	}

	@Override
	public void setFocus() {
		sashArea.setFocus();
	}

}
