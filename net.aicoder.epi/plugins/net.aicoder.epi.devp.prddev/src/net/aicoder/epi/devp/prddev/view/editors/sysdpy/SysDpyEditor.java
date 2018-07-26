package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;

import net.aicoder.epi.base.view.part.editor.BaseFormEditor;

public class SysDpyEditor extends BaseFormEditor{
	public static String ID = SysDpyEditor.class.getName();
	
	//private IViewContext context;
	private SysDpyModelPage sysDpyModelPage;
	private SysDpyOpsPage sysDpyOpsPage;

	@Override
	protected void addPages() {
		try {
			sysDpyModelPage = new SysDpyModelPage(this);
			sysDpyOpsPage = new SysDpyOpsPage(this);
			addPage(sysDpyModelPage);
			addPage(sysDpyOpsPage);
			
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		updateTitle();
	}

	private void updateTitle() {
		IEditorInput input = getEditorInput();
		setPartName(input.getName());
		setTitleToolTip(input.getToolTipText());
	}
	
	@Override
	public boolean isDirty() {
		boolean isDirty = super.isDirty();
		System.out.println("SysDpyEditor:isDirty()->"+isDirty);
		return isDirty;
	}
	
	@Override
	public boolean isSaveAsAllowed() {
		boolean saveAsAllowed = super.isSaveAsAllowed();
		System.out.println("SysDpyEditor:isSaveAsAllowed()->"+saveAsAllowed);
		return saveAsAllowed;
	}
	
	@Override
	public boolean isSaveOnCloseNeeded() {
		boolean saveOnCloseNeeded = super.isSaveOnCloseNeeded();
		System.out.println("SysDpyEditor:isSaveOnCloseNeeded()->"+saveOnCloseNeeded);
		return saveOnCloseNeeded;
	}
	
	@Override
	public void doSave(IProgressMonitor arg0) {
		super.doSave(arg0);
		MessageDialog.openInformation(getContainer().getShell(), "保存", "进行保存数据操作");
		System.out.println("SysDpyEditor:doSave(IProgressMonitor arg0)");
	}
	
	@Override
	public void doSaveAs() {
		super.doSaveAs();
		System.out.println("SysDpyEditor:doSaveAs()");
	}
	
	@Override
	public void dispose() {
		super.dispose();
		System.out.println("SysDpyEditor:dispose()");
	}
	
	@Override
	protected void commitPages(boolean onSave) {
		super.commitPages(onSave);
		System.out.println("SysDpyEditor:commitPages(boolean onSave)->"+onSave);
	}
	
	@Override
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		System.out.println("SysDpyEditor:pageChange(int newPageIndex)->"+newPageIndex);
	}
	
	@Override
	public void close(boolean save) {
		super.close(save);
		System.out.println("SysDpyEditor:close(boolean save)->"+save);
	}
	
	@Override
	public void editorDirtyStateChanged() {
		super.editorDirtyStateChanged();
		System.out.println("SysDpyEditor:editorDirtyStateChanged()");
	}
	
}
