package net.aicoder.epi.base.view.element.area;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;

public abstract class BaseArea implements IEpiArea {
	private Composite m_container;
	private String titleText;
	private Image titleImage;

	//// Constructor
	public BaseArea() {
		super();
	}

	public BaseArea(String titleText, Image titleImage) {
		super();
		setTitleText(titleText);
		setTitleImage(titleImage);
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

	private IEditorPart editor;
	public IEditorPart getEditor() {
		return editor;
	}

	public void setEditor(IEditorPart editor) {
		this.editor = editor;
	}
}
