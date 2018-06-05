package net.aicoder.epi.base.view.element.area;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public final class EpiSashArea implements IEpiArea {
	private EpiSashForm m_sashForm;
	private IEpiArea[] epiAreas;
	private int[] areaWeights;
	private int fixedOrientation = 0;

	private String titleText;
	private Image titleImage;

	//// Constructor
	public EpiSashArea() {
		super();
		this.fixedOrientation = 0;
	}

	public EpiSashArea(int fixedOrientation) {
		super();
		this.fixedOrientation = fixedOrientation;
	}

	@Override
	public final void createControl(Composite parent) {
		{
			m_sashForm = new EpiSashForm(parent, SWT.NONE);
			setFixedOrientation(fixedOrientation);
		}
		for (IEpiArea epiArea : epiAreas) {
			createAreaComposite(parent, epiArea);
		}
		if(areaWeights != null) {
			m_sashForm.setWeights(areaWeights);
		}
	}
	
	private void createAreaComposite(Composite parent, IEpiArea epiArea) {
		if (epiArea instanceof EpiSashArea) {
			epiArea.createControl(m_sashForm);
		}else {
			EpiWithTitleArea areaComposite = new EpiWithTitleArea(m_sashForm, SWT.BORDER);
			areaComposite.setTitleText(epiArea.getTitleText());
			areaComposite.setTitleImage(epiArea.getTitleImage());
			areaComposite.setArea(epiArea);
		}
	}

	@Override
	public Control getControl() {
		return m_sashForm;
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
	}

	@Override
	public String getTitleText() {
		return titleText;
	}

	@Override
	public Image getTitleImage() {
		return titleImage;
	}

	@Override
	public boolean setFocus() {
		return getControl().setFocus();
	}

	@Override
	public void dispose() {
	}

	//// getter/setter
	public int getFixedOrientation() {
		return fixedOrientation;
	}

	public void setFixedOrientation(int fixedOrientation) {
		this.fixedOrientation = fixedOrientation;
		m_sashForm.setFixedOrientation(fixedOrientation);
	}

	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	public void setTitleImage(Image titleImage) {
		this.titleImage = titleImage;
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
