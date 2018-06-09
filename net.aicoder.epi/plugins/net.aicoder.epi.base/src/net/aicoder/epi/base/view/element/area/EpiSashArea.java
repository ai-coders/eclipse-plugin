package net.aicoder.epi.base.view.element.area;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

public final class EpiSashArea extends AbstractBaseArea {
	private Composite m_container;
	private EpiSashForm m_sashForm;
	private IEpiArea[] epiAreas;
	private int[] areaWeights;
	private int fixedOrientation = 0;

	//// Constructor
	/**
	 * 创建组合分区
	 * @param workbenchPart，被调用的EditorPart或ViewPart
	 * 区域的排列会依据长宽比例自动调整
	 */
	public EpiSashArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
		this.fixedOrientation = 0;
	}

	/**
	 * 创建组合分区
	 * @param workbenchPart，被调用的EditorPart或ViewPart
	 * @param fixedOrientation，取值为：
	 * fixedOrientation = SWT.VERTICAL，则区域为固定的纵向排列
	 * fixedOrientation = SWT.HORIZONTAL，则区域为固定的横向排列
	 */
	public EpiSashArea(IWorkbenchPart workbenchPart, int fixedOrientation) {
		super(workbenchPart);
		this.fixedOrientation = fixedOrientation;
	}

	@Override
	public final void createControl(Composite parent) {
		m_container = new Composite(parent, SWT.NONE);
		m_container.setLayout(new FillLayout());
		{
			m_sashForm = new EpiSashForm(m_container, SWT.NONE);
			setFixedOrientation(fixedOrientation);
		}
		for (IEpiArea epiArea : epiAreas) {
			if(this.getWorkbenchPart()!=null) {
				epiArea.setWorkbenchPart(this.getWorkbenchPart());
			}
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
