package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.editor.FormEditor;

import net.aicoder.epi.base.view.editor.BaseFormPage;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;

public class SysDpyModelPage extends BaseFormPage {
	public final static String ID = SysDpyModelPage.class.getName();
	
	//private EpiSashArea sashArea;
	private SysDpyElmListArea viewArea;
	
	public SysDpyModelPage(FormEditor editor) {
		super(editor,ID, "部署模型");
	}
	
	@Override
	public IEpiArea newPageArea() {
		viewArea = new SysDpyElmListArea(this.getEditor());
		return viewArea;
	}
	
	@Override
	public void createControl(Composite parent) {
		viewArea.createControl(parent);	
	}
}
