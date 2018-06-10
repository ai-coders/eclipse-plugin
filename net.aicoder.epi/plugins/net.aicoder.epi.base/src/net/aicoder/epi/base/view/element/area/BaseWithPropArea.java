package net.aicoder.epi.base.view.element.area;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.property.PropsArea;

public abstract class BaseWithPropArea extends BaseArea {
	private EpiSashArea sashArea;
	protected PropsArea propsArea;
	
	//// Constructor
	public BaseWithPropArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
		initBaseWithPropArea();
	}
	
	private void initBaseWithPropArea() {
		propsArea = new PropsArea(this.getWorkbenchPart());
		IEpiArea[] areas = new IEpiArea[2];
		areas[0] = newWorkArea();
		areas[1] = propsArea;
		
		sashArea = new EpiSashArea(this.getWorkbenchPart());
		sashArea.setEpiAreas(areas);
	}
	
	protected abstract IEpiArea newWorkArea();
	
	@Override
	protected Control createAreaControl(Composite parent) {
		int[] areaWeights = new int[2];
		areaWeights[0] = 2;
		areaWeights[1] = 1;
		
		sashArea.setAreaWeights(areaWeights);
		sashArea.createControl(parent);
		return sashArea.getControl();
	}
	
	public PropsArea getPropsArea() {
		return propsArea;
	}
}
