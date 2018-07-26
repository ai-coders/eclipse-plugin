package net.aicoder.epi.devp.prddev.view.opsexploer;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;

import net.aicoder.epi.base.view.part.BaseViewPart;
import net.aicoder.epi.base.view.part.area.SashArea;
import net.aicoder.epi.base.view.part.area.IArea;

/**
 * 产品发布导航-视图页面
 * @author WANGQINGPING
 *
 */
public class ProductOpsExploer extends BaseViewPart{
	public static final String ID = ProductOpsExploer.class.getName();	
	private SashArea epiSashArea;

	
	@Override
	public IArea newPageArea() {
		epiSashArea = new SashArea(this);
		return epiSashArea;
	}

	@Override
	public void createControl(Composite parent) {
		int[] weights = new int[2];
		weights[0] = 2;
		weights[1] = 1;
		
		IArea[] epiAreas = new IArea[2];
		epiAreas[0] = new ProductOpsTreeArea();
		epiAreas[1] = new ProductOpsFolderArea();
		epiSashArea.setAreas(epiAreas);
		epiSashArea.setWeights(weights);
		epiSashArea.createControl(parent);
		
		((ProductOpsTreeArea)epiAreas[0]).getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				((ProductOpsFolderArea)epiAreas[1]).setElementSelection(selection);
			}
		});
	}

	@Override
	public void setFocus() {
		epiSashArea.setFocus();
	}
	

}
