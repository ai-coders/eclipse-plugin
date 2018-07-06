package net.aicoder.epi.base.view.part.area;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.control.form.EpiWithTitleForm;

public abstract class BaseTitleArea extends AbstractBaseArea {
	private EpiWithTitleForm m_container;
	private Composite bodyComposite;

	//// Constructor
	public BaseTitleArea() {
		super();
		initArea();
	}

	public BaseTitleArea(IWorkbenchPart workbenchPart) {
		this();
		setWorkbenchPart(workbenchPart);
	}

	public BaseTitleArea(String titleText, Image titleImage) {
		this();
		setTitleText(titleText);
		setTitleImage(titleImage);
	}

	public BaseTitleArea(IWorkbenchPart workbenchPart, String titleText, Image titleImage) {
		this(titleText, titleImage);
		setWorkbenchPart(workbenchPart);
	}

	@Override
	public void initArea() {
	}

	//// IEpiArea
	@Override
	public final void createControl(Composite parent) {
		m_container = new EpiWithTitleForm(parent, SWT.BORDER);
		//m_container.setLayout(new FillLayout());
		m_container.setTitleText(this.getTitleText());
		m_container.setTitleImage(this.getTitleImage());
		
		bodyComposite = new Composite(m_container,SWT.NONE);
		bodyComposite.setLayout(new FillLayout());
		assembleControl(bodyComposite);
		attachEvent();
		
		//bodyControl = createAreaControl(m_container);
		//bodyControl.setLayoutData(new FillLayout());
		m_container.setArea(this);
		
		attachEvent();
	}
	
	@Override
	public void assembleControl(Composite parent) {
	}

	@Override
	public void attachEvent() {
	}

	//// abstract method
	// protected abstract Control createAreaControl(Composite parent);

	public Composite getBodyComposite() {
		return bodyComposite;
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
