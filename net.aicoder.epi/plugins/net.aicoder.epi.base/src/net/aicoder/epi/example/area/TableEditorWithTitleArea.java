package net.aicoder.epi.example.area;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.view.element.area.BaseArea;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;

public class TableEditorWithTitleArea extends BaseWithTitleArea {
	public TableEditorWithTitleArea() {
		super("TableEditorComposite",null);
	}
	
	public TableEditorWithTitleArea(String titleText, Image titleImage){
		super(titleText,titleImage);
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		return (new TableEditorComposite(parent));
	}
}
