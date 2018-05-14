package net.aicoder.epi.base.view.editor;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import net.aicoder.epi.base.view.action.IEpiAction;

public class EpiFormPage extends FormPage {
	public static String ID = EpiFormPage.class.getName();

	private IEpiAction[] toolBarAactons;
	private ScrolledForm form;
	
	public EpiFormPage(String id, String title) {
		super(id, title);
	}

	public EpiFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	protected void createFormContent(final IManagedForm managedForm) {
		// FormToolkit toolkit = managedForm.getToolkit();
		form = managedForm.getForm();
		Composite parent = form.getBody();
		parent.setLayout(new FillLayout());

		createControl(parent);

		toolBarAactons = makeToolBarAction();
		if (toolBarAactons != null) {
			IToolBarManager manager = form.getToolBarManager();
			hookToolBars(manager);
			form.updateToolBar();
		}

		hookContextMenu();
		hookKeybordActions();
	}

	protected void createControl(Composite parent) {

	}

	protected IEpiAction[] makeToolBarAction() {
		return null;
	}

	protected void hookToolBars(IToolBarManager manager) {
		if(toolBarAactons == null) {
			return;
		}
		for (IEpiAction action : toolBarAactons) {
			manager.add(action);
		}
	}

	protected void hookContextMenu() {

	}

	protected void hookKeybordActions() {

	}

	//// getter/setter
	public IEpiAction[] getToolBarAactons() {
		return toolBarAactons;
	}

	public void setToolBarAactons(IEpiAction[] toolBarAactons) {
		this.toolBarAactons = toolBarAactons;
	}
}
