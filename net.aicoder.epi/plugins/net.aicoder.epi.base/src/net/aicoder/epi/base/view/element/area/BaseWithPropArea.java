package net.aicoder.epi.base.view.element.area;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.property.PropsArea;

public abstract class BaseWithPropArea extends BaseArea {
	protected PropsArea propsArea;
	
	@Override
	protected final Control createAreaControl(Composite parent) {
		int[] weights = new int[2];
		weights[0] = 2;
		weights[1] = 1;
		
		propsArea = new PropsArea();
		IEpiArea[] areas = new IEpiArea[2];
		areas[0] = createWorkArea();
		areas[1] = propsArea;
		
		EpiSashArea sashArea = new EpiSashArea();
		sashArea.setEditor(this.getEditor());
		sashArea.setEpiAreas(areas);
		sashArea.setAreaWeights(weights);
		sashArea.createControl(parent);
		return sashArea.getControl();
	}
	
	protected abstract IEpiArea createWorkArea();
	
	public PropsArea getPropsArea() {
		return propsArea;
	}

}
