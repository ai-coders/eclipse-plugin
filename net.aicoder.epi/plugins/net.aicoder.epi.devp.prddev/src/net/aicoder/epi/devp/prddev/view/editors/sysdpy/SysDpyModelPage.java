package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.editor.FormEditor;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.control.table.EpiTable;
import net.aicoder.epi.base.view.control.tree.EpiTree;
import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.base.view.part.editor.BaseFormPage;

/**
 * 部署模型页面
 * @author WANGQINGPING
 *
 */
public class SysDpyModelPage extends BaseFormPage {
	public final static String ID = SysDpyModelPage.class.getName();
	private SysDpyModelArea area;
	
	private boolean isDirty = false;
	
	public SysDpyModelPage(FormEditor editor) {
		super(editor, ID, "部署模型");
	}

	@Override
	public IArea newPageArea() {
		area = new SysDpyModelArea(getEditor());
		return area;
	}

	@Override
	public void createControl(Composite parent) {
		area.createControl(parent);
	}
	
	@Override
	public boolean isActive() {
		boolean active = super.isActive();
		System.out.println("SysDpyModelPage:isActive()->"+active);
		return active;
	}
	
	@Override
	public boolean isDirty() {
//		boolean dirty = super.isDirty();
//		System.out.println("SysDpyModelPage:isDirty()->"+dirty);
		return isDirty;
	}
	
	@Override
	public boolean isEditor() {
		boolean editor2 = super.isEditor();
		System.out.println("SysDpyModelPage:isEditor()->"+editor2);
		return true;
	}
	
	@Override
	public boolean isSaveAsAllowed() {
		boolean saveAsAllowed = super.isSaveAsAllowed();
		System.out.println("SysDpyModelPage:isSaveAsAllowed()->"+saveAsAllowed);
		return saveAsAllowed;
	}
	
	@Override
	public boolean isSaveOnCloseNeeded() {
		boolean saveOnCloseNeeded = super.isSaveOnCloseNeeded();
		System.out.println("SysDpyModelPage:isSaveOnCloseNeeded()->"+saveOnCloseNeeded);            
		return saveOnCloseNeeded;
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		super.doSave(monitor);
		System.out.println("SysDpyModelPage:doSave(IProgressMonitor monitor)");
		
		EpiTree sysDpyCmpTree = area.getSysDpyCmpTreeTable().getTree();
		EpiTable sysDpyResourcesTable = area.getSysDpyResourcesTable().getTable();
		EpiTable sysDpySchemaTable = area.getSysDpySchemeArea().getTable();
		EpiTable sysDpyReTree = area.getSysDpyCmpRefTable().getTable();
		EpiTree sysDpyResInstTree = area.getSysDpyResInstanceTreeTable().getTree();			
		
		//在此处进行做editor 新增、修改、删除的保存处理
		
		System.out.println("在此处进行做editor 新增、修改、删除的保存处理");
		
		isDirty = false;
	}
	
	@Override
	public void doSaveAs() {
		super.doSaveAs();
		System.out.println("SysDpyModelPage:doSaveAs()");
	}
	
	@Override
	public void dispose() {
		super.dispose();
		System.out.println("SysDpyModelPage:dispose()");
	}

}
