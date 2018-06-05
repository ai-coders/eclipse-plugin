package net.aicoder.epi.base.view.element.area;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class BaseWithTitleArea implements IEpiArea {
	private EpiWithTitleForm m_container;
	private String titleText;
	private Image titleImage;
	private Control bodyControl;

	//// Constructor
	public BaseWithTitleArea() {
		super();
	}

	public BaseWithTitleArea(String titleText, Image titleImage) {
		super();
		setTitleText(titleText);
		setTitleImage(titleImage);
	}

	//// IEpiArea
	@Override
	public final void createControl(Composite parent) {
		m_container = new EpiWithTitleForm(parent, SWT.BORDER);
		//m_container.setLayout(new FillLayout());
		m_container.setTitleText(titleText);
		m_container.setTitleImage(titleImage);
		
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

	//// getter/setter
	@Override
	public String getTitleText() {
		return titleText;
	}

	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	@Override
	public Image getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(Image titleImage) {
		this.titleImage = titleImage;
	}
}
