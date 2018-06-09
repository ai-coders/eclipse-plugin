package net.aicoder.epi.base.view.element.area;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

public final class EpiSashArea extends AbstractBaseArea {
	private EpiSashForm m_sashForm;
	private IEpiArea[] epiAreas;
	private int[] areaWeights;
	private int fixedOrientation = 0;

	//// Constructor
	public EpiSashArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
		this.fixedOrientation = 0;
	}

	public EpiSashArea(IWorkbenchPart workbenchPart, int fixedOrientation) {
		super(workbenchPart);
		this.fixedOrientation = fixedOrientation;
	}

	@Override
	public final void createControl(Composite parent) {
		{
			m_sashForm = new EpiSashForm(parent, SWT.NONE);
			setFixedOrientation(fixedOrientation);
		}
		for (IEpiArea epiArea : epiAreas) {
			//epiArea.setEditor(this.getEditor());
			epiArea.setWorkbenchPart(this.getWorkbenchPart());
			createAreaComposite(parent, epiArea);
		}
		if(areaWeights != null) {
			m_sashForm.setWeights(areaWeights);
		}
	}
	
	private void createAreaComposite(Composite parent, IEpiArea epiArea) {
		if(epiArea == null) {
			return;
		}
		epiArea.createControl(m_sashForm);
	}

	@Override
	public Control getControl() {
		return m_sashForm;
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
	}
	
	@Override
	public boolean setFocus() {
		return getControl().setFocus();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	//// getter/setter
	public int getFixedOrientation() {
		return fixedOrientation;
	}

	public void setFixedOrientation(int fixedOrientation) {
		this.fixedOrientation = fixedOrientation;
		m_sashForm.setFixedOrientation(fixedOrientation);
	}

	public IEpiArea[] getEpiAreas() {
		return epiAreas;
	}

	public void setEpiAreas(IEpiArea[] epiAreas) {
		this.epiAreas = epiAreas;
	}
	
	public int[] getAreaWeights() {
		return areaWeights;
	}

	public void setAreaWeights(int[] areaWeights) {
		this.areaWeights = areaWeights;
	}
}
