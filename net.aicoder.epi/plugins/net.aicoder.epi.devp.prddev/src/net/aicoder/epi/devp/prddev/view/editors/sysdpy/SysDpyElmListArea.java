package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.element.area.BaseWithPropArea;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;
import net.aicoder.epi.devp.prddev.PrddevPlugin;

public class SysDpyElmListArea extends BaseWithPropArea {
	public static String ID = SysDpyElmListArea.class.getName();
	public static String PLUGIN_ID = PrddevPlugin.PLUGIN_ID;

	private SysDpyElmTreeTable elmTreeTable;
	private SysDpyElmTreeTable instTreeTable;

	//// Constructor
	public SysDpyElmListArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}

	@Override
	protected IEpiArea newWorkArea() {
		int[] areaWeights = new int[2];
		areaWeights[0] = 1;
		areaWeights[1] = 1;

		elmTreeTable = new SysDpyElmTreeTable();//上半部分：XXX产品
		instTreeTable = new SysDpyElmTreeTable();//下半部分：XXX部署实例
		IEpiArea[] epiAreas = new IEpiArea[2];
		epiAreas[0] = elmTreeTable;
		epiAreas[1] = instTreeTable;

		EpiSashArea sashArea = new EpiSashArea(this.getWorkbenchPart(), SWT.VERTICAL);
		sashArea.setEpiAreas(epiAreas);
		sashArea.setAreaWeights(areaWeights);

		return sashArea;
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		Control control = super.createAreaControl(parent);
		
		elmTreeTable.getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				propsArea.setElementSelection(PLUGIN_ID, selection);
			}
		});
		
		instTreeTable.getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				propsArea.setElementSelection(PLUGIN_ID, selection);
			}
		});
		
		return control;
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

}
