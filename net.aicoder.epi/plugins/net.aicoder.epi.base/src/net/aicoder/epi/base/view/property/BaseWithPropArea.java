package net.aicoder.epi.base.view.property;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.element.area.BaseArea;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;

public abstract class BaseWithPropArea extends BaseArea {
	protected IPropsManager propsManager;

	protected PropsArea propsArea;
	private EpiSashArea sashArea;

	//// Constructor
	// public BaseWithPropArea(IWorkbenchPart workbenchPart) {
	public BaseWithPropArea(IWorkbenchPart workbenchPart, IPropsManager propsManager) {
		super(workbenchPart);
		this.propsManager = propsManager;
		initBaseWithPropArea();
	}

	private void initBaseWithPropArea() {
		propsArea = new PropsArea(this.getWorkbenchPart());
		propsArea.setPropsManager(propsManager);

		IEpiArea[] areas = new IEpiArea[2];
		areas[0] = newWorkArea();
		areas[1] = propsArea;

		sashArea = new EpiSashArea(this.getWorkbenchPart());
		sashArea.setEpiAreas(areas);
	}

	protected abstract IEpiArea newWorkArea();

	public abstract void setElementSelection(ISelection selection);

	@Override
	protected Control createAreaControl(Composite parent) {
		int[] areaWeights = new int[2];
		areaWeights[0] = 3;
		areaWeights[1] = 1;

		sashArea.setAreaWeights(areaWeights);
		sashArea.createControl(parent);
		return sashArea.getControl();
	}

	public PropsArea getPropsArea() {
		return propsArea;
	}
}
