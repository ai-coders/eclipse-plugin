package net.aicoder.epi.devp.prddev.view.editors.sysdpy.opsline;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.editor.FormEditor;

import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.base.view.part.editor.BaseFormPage;

/**
 * 运维流水线页面
 * @author WANGQINGPING
 *
 */
public class SysDpyOpsPage extends BaseFormPage{
	public final static String ID = SysDpyOpsPage.class.getName();
	private SysDpyOpsArea area;
	

	public SysDpyOpsPage(FormEditor formEditor) {
		super(formEditor, ID, "运维流水线");
	}

	@Override
	public IArea newPageArea() {
		area = new SysDpyOpsArea(getEditor());
		return area;
	}

	@Override
	public void createControl(Composite parent) {
		area.createControl(parent);
	}
	
	@Override
	public boolean isActive() {
		boolean active = super.isActive();
		System.out.println("SysDpyOpsPage:isActive()->"+active);
		return active;
	}
	
	@Override
	public boolean isDirty() {
		boolean dirty = super.isDirty();
		System.out.println("SysDpyOpsPage:isDirty()->"+dirty);
		return dirty;
	}
	
	@Override
	public boolean isEditor() {
		boolean editor2 = super.isEditor();
		System.out.println("SysDpyOpsPage:isEditor()->"+editor2);
		return true;
	}
	
	@Override
	public boolean isSaveAsAllowed() {
		boolean saveAsAllowed = super.isSaveAsAllowed();
		System.out.println("SysDpyOpsPage:isSaveAsAllowed()->"+saveAsAllowed);
		return saveAsAllowed;
	}
	
	@Override
	public boolean isSaveOnCloseNeeded() {
		boolean saveOnCloseNeeded = super.isSaveOnCloseNeeded();
		System.out.println("SysDpyOpsPage:isSaveOnCloseNeeded()->"+saveOnCloseNeeded);            
		return saveOnCloseNeeded;
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		super.doSave(monitor);
		System.out.println("SysDpyOpsPage:doSave(IProgressMonitor monitor)");
	}
	
	@Override
	public void doSaveAs() {
		super.doSaveAs();
		System.out.println("SysDpyOpsPage:doSaveAs()");
	}
	
	@Override
	public void dispose() {
		super.dispose();
		System.out.println("SysDpyOpsPage:dispose()");
	}

}
