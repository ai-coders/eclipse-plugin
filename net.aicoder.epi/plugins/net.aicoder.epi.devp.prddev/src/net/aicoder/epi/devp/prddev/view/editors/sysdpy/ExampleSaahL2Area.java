package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.part.area.SashArea;
import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.example.area.TableEditorArea;

public class ExampleSaahL2Area extends SashArea { //BaseArea{
	public ExampleSaahL2Area(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}
	
	@Override
	public void initArea() {
		int[] weights = new int[5];
		weights[0] = 1;
		weights[1] = 2;
		weights[2] = 3;
		weights[3] = 4;
		weights[4] = 5;

		IArea[] areas = new IArea[5];
		areas[0] = new TableEditorArea();
		areas[1] = new TableEditorArea();
		areas[2] = new TableEditorArea();
		areas[3] = new TableEditorArea();
		areas[4] = new TableEditorArea();
		
		this.areas = areas;
		this.weights = weights;
		this.fixedOrientation = SWT.HORIZONTAL;
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
	}

}
