package net.aicoder.epi.devp.product.view.editors;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.base.view.part.editor.BaseFormPage;
import net.aicoder.epi.sample.edittable.Ch9TableEditorComposite;

public class PrdOverviewPage extends BaseFormPage {
	//private IEpiAction[] toolBarAactons;
	ScrolledForm form;

	public PrdOverviewPage(FormEditor editor) {
		super(editor, PrdOverviewPage.class.getName(), "产品总览");	
		creatToolBar();
	}
	
	@Override
	public IArea newPageArea() {
		return null;
	}
	

/**		
	public void createFormContent(final IManagedForm managedForm) {
		form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		form.setText("产品总览");
		TableWrapLayout layout = new TableWrapLayout();
		layout.leftMargin = 10;
		layout.rightMargin = 10;
		form.getBody().setLayout(layout);
		
		createContent(form, toolkit);
	}
**/
	private void creatToolBar() {
	}
	
	@Override
	public void createControl(Composite parent) {
		IEpiEditorInput editorInput = (IEpiEditorInput) this.getEditor().getEditorInput();
		IBaseVo currentData = editorInput.getCurrentData();
		//((ScrolledForm)parent).setText(currentData.getName());
		
		new Ch9TableEditorComposite(parent);
	}

	@Override
	protected IEpiAction[] makeToolBarAction() {
		return null;
	}
	
}
