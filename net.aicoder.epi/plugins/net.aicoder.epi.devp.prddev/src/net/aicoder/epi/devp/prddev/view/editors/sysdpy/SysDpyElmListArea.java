package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.element.area.BaseArea;
import net.aicoder.epi.base.view.element.area.BaseWithPropArea;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.EpiTabArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;
import net.aicoder.epi.base.view.element.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.element.tree.EpiTree;
import net.aicoder.epi.base.view.element.tree.EpiTreeDefiner;
import net.aicoder.epi.base.view.property.PropsArea;
import net.aicoder.epi.devp.DevpConstant;
import net.aicoder.epi.devp.prddev.PrddevImageConstant;
import net.aicoder.epi.devp.prddev.PrddevPlugin;
import net.aicoder.epi.devp.prddev.doper.ProductDevDoper;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.devp.prddev.view.editors.function.ModuleEditor;
import net.aicoder.epi.devp.prddev.view.editors.product.ProductEditor;
import net.aicoder.epi.devp.prddev.view.editors.syscmp.SysCmpEditor;
import net.aicoder.epi.devp.prddev.view.editors.sysdpy.SysDpyEditor;
import net.aicoder.epi.devp.prddev.view.editors.syside.SysIdeEditor;
import net.aicoder.epi.example.area.TableEditorArea;
import net.aicoder.epi.example.area.TableEditorWithTitleArea;
import net.aicoder.epi.util.ImageUtil;

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
		areaWeights[0] = 2;
		areaWeights[1] = 1;

		elmTreeTable = new SysDpyElmTreeTable();
		instTreeTable = new SysDpyElmTreeTable();
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