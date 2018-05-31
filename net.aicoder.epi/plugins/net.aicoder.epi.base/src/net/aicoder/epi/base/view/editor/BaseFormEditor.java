package net.aicoder.epi.base.view.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

public abstract class BaseFormEditor extends FormEditor {

	//// Constructor
	public BaseFormEditor() {
		super();
	}

	//// Life cycle
	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		initializeTitle(input);
	}

	private void initializeTitle(IEditorInput input) {
		if (input != null) {
			String title = input.getName();
			setPartName(title);
		}
	}

	@Override
	protected void addPages() {
	}

	@Override
	public void doSave(IProgressMonitor arg0) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void dispose() {
		// continue
		super.dispose();
	}

	//// EditorPart
	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		super.init(site, editorInput);
	}

}
