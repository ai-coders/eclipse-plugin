package net.aicoder.epi.example.area;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.EpiTabArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;

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
		IEpiArea[] epiChildrenAreas = new TableEditorArea[2];
		epiChildrenAreas[0] = new TableEditorArea();
		epiChildrenAreas[1] = new TableEditorArea();
		EpiTabArea epiTabArea = new EpiTabArea(null);
		epiTabArea.setEpiAreas(epiChildrenAreas);
		
		//epiAreas[0].createControl(parent);
		
		IEpiArea[] epiAreas = new IEpiArea[2];
		epiAreas[0] = new TableEditorArea();
		epiAreas[1] = epiTabArea;
		
		//EpiSashArea sashArea = new EpiSashArea(SWT.VERTICAL);
		//EpiSashArea sashArea = new EpiSashArea(SWT.HORIZONTAL);
		EpiSashArea sashArea = new EpiSashArea(null);
		sashArea.setEpiAreas(epiAreas);
		sashArea.setAreaWeights(areaWeights);
		
		sashArea.createControl(parent);
	}
	
}
