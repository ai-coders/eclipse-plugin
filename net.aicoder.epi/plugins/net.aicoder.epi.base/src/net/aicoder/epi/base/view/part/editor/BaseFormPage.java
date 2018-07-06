package net.aicoder.epi.base.view.part.editor;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.part.area.IArea;

public abstract class BaseFormPage extends FormPage implements IFormEditorPage{
	public static String ID = BaseFormPage.class.getName();
	
	protected IArea pageArea;
	protected int pageIndex;
	protected String name;
	protected Image image;

	private IEpiAction[] toolBarAactons;
	private ScrolledForm form;
	
	//// Constructor
	public BaseFormPage(String id, String title) {
		super(id, title);
	}

	public BaseFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
		pageArea = newPageArea();
		if(pageArea != null) {
			//pageArea.setEditor(editor);
			pageArea.setWorkbenchPart(editor);
		}
		//initialize(editor);
	}

	//// Life cycle
	@Override
	protected final void createFormContent(final IManagedForm managedForm) {
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

	//// IFormEditorPage
	public abstract IArea newPageArea();

	@Override
	public int getPageIndex() {
		return this.pageIndex;
	}

	@Override
	public void setPageIndex(int index) {
		this.pageIndex = index;
	}

	@Override
	public abstract void createControl(Composite parent);

	@Override
	public Control getControl() {
		return this.form;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Image getImage() {
		return this.image;
	}
}
