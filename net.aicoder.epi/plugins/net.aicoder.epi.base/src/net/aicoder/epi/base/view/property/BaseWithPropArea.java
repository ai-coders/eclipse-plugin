package net.aicoder.epi.base.view.property;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.part.area.SashArea;
import net.aicoder.epi.example.area.TableEditorArea;
import net.aicoder.epi.base.view.part.area.IArea;

public abstract class BaseWithPropArea extends SashArea { //BaseArea {
	protected IPropsManager propsManager;
	protected PropsArea propsArea;

	//// Constructor
	public BaseWithPropArea(IWorkbenchPart workbenchPart) {
	//public BaseWithPropArea(IWorkbenchPart workbenchPart, IPropsManager propsManager) {
		super(workbenchPart);
		// this.propsManager = propsManager;
		// initArea();
	}

	@Override
	public void initArea() {
		int[] weights = new int[2];
		weights[0] = 4;
		weights[1] = 1;

		propsArea = new PropsArea(this.getWorkbenchPart());
		//propsArea.setPropsManager(propsManager);

		IArea[] areas = new IArea[2];
		areas[0] = newWorkArea();
		areas[1] = propsArea;

		this.areas = areas;
		this.weights = weights;
		//this.fixedOrientation = SWT.VERTICAL;
		this.fixedOrientation = SWT.HORIZONTAL;
	}

	protected abstract IArea newWorkArea();

	public abstract void setElementSelection(ISelection selection);

/**
	//@Override
	public Control createAreaControl(Composite parent) {
		propsArea.setPropsManager(propsManager);
		
		sashArea.createControl(parent);
		return sashArea.getControl();
	}
**/
	public PropsArea getPropsArea() {
		return propsArea;
	}
	
	public IPropsManager getPropsManager() {
		return propsManager;
	}
}
