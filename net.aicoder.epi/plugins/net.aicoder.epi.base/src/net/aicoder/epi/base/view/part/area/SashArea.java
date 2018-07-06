package net.aicoder.epi.base.view.part.area;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.control.form.EpiSashForm;

public class SashArea extends AbstractBaseArea {
	protected IArea[] areas;
	protected int[] weights;
	protected int fixedOrientation = 0;

	private EpiSashForm m_sashForm;

	//// Constructor
	/**
	 * 创建组合分区
	 * @param workbenchPart，被调用的EditorPart或ViewPart
	 * 区域的排列会依据长宽比例自动调整
	 */
	public SashArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
		this.fixedOrientation = 0;
		initArea();
	}

	/**
	 * 创建组合分区
	 * @param workbenchPart，被调用的EditorPart或ViewPart
	 * @param fixedOrientation，取值为：
	 * fixedOrientation = SWT.VERTICAL，则区域为固定的纵向排列
	 * fixedOrientation = SWT.HORIZONTAL，则区域为固定的横向排列
	 */
	public SashArea(IWorkbenchPart workbenchPart, int fixedOrientation) {
		this(workbenchPart);
		this.fixedOrientation = fixedOrientation;
		initArea();
	}
	
	@Override
	public void initArea() {
	}

	@Override
	public final void createControl(Composite parent) {
		m_sashForm = new EpiSashForm(parent, SWT.NONE);
		setFixedOrientation(fixedOrientation);
		for (IArea epiArea : areas) {
			if (this.getWorkbenchPart() != null) {
				epiArea.setWorkbenchPart(this.getWorkbenchPart());
			}
			createAreaComposite(epiArea);
		}
		if (weights != null) {
			m_sashForm.setWeights(weights);
		}
		
		attachEvent();
	}

	private void createAreaComposite(IArea epiArea) {
		if (epiArea == null) {
			return;
		}
		epiArea.createControl(m_sashForm);
	}
	
	@Override
	public final void assembleControl(Composite parent) {
	}

	@Override
	public void attachEvent() {
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
	}

	//// getter/setter
	public int getFixedOrientation() {
		return fixedOrientation;
	}

	public void setFixedOrientation(int fixedOrientation) {
		this.fixedOrientation = fixedOrientation;
		if(m_sashForm != null) {
			m_sashForm.setFixedOrientation(fixedOrientation);
		}
	}

	public IArea[] getAreas() {
		return areas;
	}

	public void setAreas(IArea[] areas) {
		this.areas = areas;
	}

	public int[] getWeights() {
		return weights;
	}

	public void setWeights(int[] weights) {
		this.weights = weights;
	}
}
