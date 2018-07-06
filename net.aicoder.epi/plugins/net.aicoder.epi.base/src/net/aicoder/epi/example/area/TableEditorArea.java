package net.aicoder.epi.example.area;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.view.part.area.BaseArea;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;

public class TableEditorArea extends BaseTitleArea {//BaseArea {
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
	//protected Control createAreaControl(Composite parent) {
	public void assembleControl(Composite parent) {
		new TableEditorComposite(parent);
	}
	
	@Override
	public void attachEvent() {
	}

}
