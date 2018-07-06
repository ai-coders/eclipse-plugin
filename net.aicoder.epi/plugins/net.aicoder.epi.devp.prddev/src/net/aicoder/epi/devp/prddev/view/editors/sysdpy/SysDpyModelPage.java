package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.editor.FormEditor;

import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.base.view.part.editor.BaseFormPage;

public class SysDpyModelPage extends BaseFormPage {
	public final static String ID = SysDpyModelPage.class.getName();
	
	private SysDpyModelArea area;
	//private ExampleSaahL1Area area;
	
	public SysDpyModelPage(FormEditor editor) {
		super(editor, ID, "部署模型");
	}

	@Override
	public IArea newPageArea() {
		area = new SysDpyModelArea(getEditor());
		//area = new ExampleSaahL1Area(getEditor());
		return area;
	}

	@Override
	public void createControl(Composite parent) {
		area.createControl(parent);
	}

}
