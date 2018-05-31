package net.aicoder.epi.devp.product.view.editors;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;

import net.aicoder.epi.base.view.editor.BaseFormEditor;

public class ProductEditor extends BaseFormEditor {
	public static String ID = ProductEditor.class.getName();
	
	private PrdOverviewPage overviewPage;
	private PrdFunctionListPage functionListPage;
	private PrdFunctionTreePage functionTreePage;
	
	@Override
	protected void addPages() {
		try {
			overviewPage = new PrdOverviewPage(this);
			functionListPage = new PrdFunctionListPage(this);
			functionTreePage = new PrdFunctionTreePage(this);
			addPage(overviewPage);
			addPage(functionListPage);
			addPage(functionTreePage);
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
