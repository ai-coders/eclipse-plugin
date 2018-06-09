package net.aicoder.epi.base.view.element.area;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.property.PropsArea;

public abstract class BaseWithPropArea extends BaseArea {
	protected PropsArea propsArea;
	
	//// Constructor
	public BaseWithPropArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}
	
	@Override
	protected final Control createAreaControl(Composite parent) {
		int[] weights = new int[2];
		weights[0] = 2;
		weights[1] = 1;
		
		propsArea = new PropsArea(this.getWorkbenchPart());
		IEpiArea[] areas = new IEpiArea[2];
		areas[0] = createWorkArea(parent);
		areas[1] = propsArea;
		
		EpiSashArea sashArea = new EpiSashArea(this.getWorkbenchPart());
		//sashArea.setEditor(this.getEditor());
		//sashArea.setWorkbenchPart(this.getWorkbenchPart());

		sashArea.setEpiAreas(areas);
		sashArea.setAreaWeights(weights);
		sashArea.createControl(parent);
		return sashArea.getControl();
	}
	
	protected abstract IEpiArea createWorkArea(Composite parent);
	
	public PropsArea getPropsArea() {
		return propsArea;
	}

}
