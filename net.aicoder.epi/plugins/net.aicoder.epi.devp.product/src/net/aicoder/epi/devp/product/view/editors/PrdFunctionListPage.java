package net.aicoder.epi.devp.product.view.editors;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.editor.FormEditor;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.adapter.IEpiEditorInput;
import net.aicoder.epi.base.view.adapter.IEpiInput;
import net.aicoder.epi.base.view.adapter.IViewContext;
import net.aicoder.epi.base.view.adapter.ViewContext;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.editor.BaseFormPage;
import net.aicoder.epi.base.view.element.table.EpiTable;
import net.aicoder.epi.base.view.element.table.EpiTableDefiner;
import net.aicoder.epi.devp.product.doper.ModuleDoper;

public class PrdFunctionListPage extends BaseFormPage {
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
			{"Code", "code", -20, null, null, null, IColumnDefiner.EDITABLE },
			{"Name", "name", 20, null, null, null, IColumnDefiner.EDITABLE },
			{"Alias", "alias", },
			{"Description", "description", 0, null, null, null, IColumnDefiner.EDITABLE },
			{"Type", "type", },
			{"Stereotype", "stereotype", 0, null, null, IColumnDefiner.HIDDEN},
			{"Version", "version", },
			{"Phase", "phase", },
			{"Status", "status", },
	};
	
	
	private FormEditor editor;
	private EpiTable table;
	private EpiTableDefiner definer;
	
	private ModuleDoper dataoper;

	public PrdFunctionListPage(FormEditor editor) {
		super(editor, PrdFunctionListPage.class.getName(), "功能清单");
		this.editor = editor;
		
		dataoper = new ModuleDoper();
	}
	
	@Override
	public void createControl(Composite parent) {
		definer = new EpiTableDefiner(null, columnsDefine);
		IEpiEditorInput editInput = (IEpiEditorInput)editor.getEditorInput();
		IBaseVo product = editInput.getCurrentData();
		IEpiInput input = dataoper.loadModuleList(product);
		IViewContext context = new ViewContext();
		context.setInput(input);
		//definer.setInput(input);
		table = new EpiTable(parent, definer, context);
	}
}
