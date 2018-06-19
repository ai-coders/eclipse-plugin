package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.editor.FormEditor;
import net.aicoder.epi.base.view.editor.BaseFormPage;
import net.aicoder.epi.base.view.element.area.IEpiArea;

public class SysDpyModelPage extends BaseFormPage {
	public final static String ID = SysDpyModelPage.class.getName();
	private SysDpySubArea sysDpySubArea;
	
	public SysDpyModelPage(FormEditor editor) {
		super(editor, ID, "部署模型");
	}

	@Override
	public IEpiArea newPageArea() {
		sysDpySubArea = new SysDpySubArea(getEditor());
		return sysDpySubArea;
	}

	@Override
	public void createControl(Composite parent) {
		sysDpySubArea.createControl(parent);
	}

}
