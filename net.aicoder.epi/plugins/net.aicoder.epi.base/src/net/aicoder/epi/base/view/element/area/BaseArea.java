package net.aicoder.epi.base.view.element.area;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

public abstract class BaseArea extends AbstractBaseArea {
	private Composite m_container;
	
	//// Constructor
	public BaseArea() {
		super();
	}

	public BaseArea(IWorkbenchPart workbenchPart) {
		this();
		setWorkbenchPart(workbenchPart);
	}

	public BaseArea(String titleText, Image titleImage) {
		super();
		setTitleText(titleText);
		setTitleImage(titleImage);
	}
	
	public BaseArea(IWorkbenchPart workbenchPart, String titleText, Image titleImage) {
		this(titleText, titleImage);
		setWorkbenchPart(workbenchPart);
	}

	//// IEpiArea
	@Override
	public final void createControl(Composite parent) {
		m_container = new Composite(parent, SWT.NONE);
		m_container.setLayout(new FillLayout());
		createAreaControl(m_container);
	}
	
	//// abstract method
	protected abstract Control createAreaControl(Composite parent);

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
