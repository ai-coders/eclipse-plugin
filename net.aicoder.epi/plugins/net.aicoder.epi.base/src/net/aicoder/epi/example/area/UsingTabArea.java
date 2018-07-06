package net.aicoder.epi.example.area;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import net.aicoder.epi.base.view.part.area.SashArea;
import net.aicoder.epi.base.view.part.area.TabArea;
import net.aicoder.epi.base.view.part.area.IArea;

public class UsingTabArea {
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(600, 800);
		shell.setLayout(new FillLayout());
		
		//new TableEditorComposite(shell);
		createControl(shell);

		shell.open();
		shell.layout();
		
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}
	
	private static void createControl(Composite parent) {
		int[] areaWeights = new int[2];
		areaWeights[0] = 1;
		areaWeights[1] = 2;
		//areaWeights[2] = 3;

		//new TableEditorComposite(parent);
		IArea[] epiChildrenAreas = new TableEditorArea[2];
		epiChildrenAreas[0] = new TableEditorArea();
		epiChildrenAreas[1] = new TableEditorArea();
		TabArea epiTabArea = new TabArea(null);
		epiTabArea.setEpiAreas(epiChildrenAreas);
		
		//epiAreas[0].createControl(parent);
		
		IArea[] epiAreas = new IArea[2];
		epiAreas[0] = new TableEditorArea();
		epiAreas[1] = epiTabArea;
		
		//EpiSashArea sashArea = new EpiSashArea(SWT.VERTICAL);
		//EpiSashArea sashArea = new EpiSashArea(SWT.HORIZONTAL);
		SashArea sashArea = new SashArea(null);
		sashArea.setAreas(epiAreas);
		sashArea.setWeights(areaWeights);
		
		sashArea.createControl(parent);
	}
	
}
