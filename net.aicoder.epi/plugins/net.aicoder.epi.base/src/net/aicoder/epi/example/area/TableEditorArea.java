package net.aicoder.epi.example.area;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import net.aicoder.epi.base.view.element.area.BaseArea;

public class TableEditorArea extends BaseArea {
	public TableEditorArea() {
		super("TableEditorComposite",null);
	}
	
	public TableEditorArea(String titleText, Image titleImage){
		super(titleText,titleImage);
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
	}

	@Override
	protected void createAreaControl(Composite parent) {
		new TableEditorComposite(parent);
	}
}
