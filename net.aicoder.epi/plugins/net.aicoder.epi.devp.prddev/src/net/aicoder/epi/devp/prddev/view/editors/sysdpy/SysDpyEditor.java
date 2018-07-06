package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;

import net.aicoder.epi.base.view.part.editor.BaseFormEditor;

public class SysDpyEditor extends BaseFormEditor{
	public static String ID = SysDpyEditor.class.getName();
	
	//private IViewContext context;
	private SysDpyModelPage sysDpyMainPage;

	@Override
	protected void addPages() {
		try {
			sysDpyMainPage = new SysDpyModelPage(this);
			addPage(sysDpyMainPage);
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
}
