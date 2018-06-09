package net.aicoder.epi.base.view.element.area;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

public abstract class BaseWithTitleArea extends AbstractBaseArea {
	private EpiWithTitleForm m_container;
	private Control bodyControl;

	//// Constructor
	public BaseWithTitleArea() {
		super();
	}

	public BaseWithTitleArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}

	public BaseWithTitleArea(String titleText, Image titleImage) {
		super(titleText,titleImage);
	}

	public BaseWithTitleArea(IWorkbenchPart workbenchPart, String titleText, Image titleImage) {
		super(workbenchPart, titleText,titleImage);
	}

	//// IEpiArea
	@Override
	public final void createControl(Composite parent) {
		m_container = new EpiWithTitleForm(parent, SWT.BORDER);
		//m_container.setLayout(new FillLayout());
		m_container.setTitleText(this.getTitleText());
		m_container.setTitleImage(this.getTitleImage());
		
		bodyControl = createAreaControl(m_container);
		bodyControl.setLayoutData(new FillLayout());
		m_container.setArea(this);
	}
	
	//// abstract method
	protected abstract Control createAreaControl(Composite parent);
	
	public Control getBodyControl() {
		return bodyControl;
	}

	@Override
	public Control getControl() {
		return m_container;
	}

	@Override
	public boolean setFocus() {
		return getControl().setFocus();
	}
	
	@Override
	public void dispose() {
		Control control = getControl();
		if (control != null && !control.isDisposed()) {
			control.dispose();
		}
	}
}
