package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.part.area.SashArea;
import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.example.area.TableEditorArea;

public class ExampleSaahL1Area extends SashArea { //BaseArea{
	public ExampleSaahL1Area(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}
	
	@Override
	public void initArea() {
		int[] weights = new int[2];
		weights[0] = 1;
		weights[1] = 5;

		IArea[] areas = new IArea[2];
		areas[0] = new TableEditorArea();
		areas[1] = new ExampleSaahL2Area(getWorkbenchPart());
		
		this.areas = areas;
		this.weights = weights;
		this.fixedOrientation = SWT.VERTICAL;
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
	}

}
