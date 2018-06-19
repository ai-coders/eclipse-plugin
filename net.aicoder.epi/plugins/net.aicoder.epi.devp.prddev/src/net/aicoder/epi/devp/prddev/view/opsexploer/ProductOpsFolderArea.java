package net.aicoder.epi.devp.prddev.view.opsexploer;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.view.element.area.BaseArea;
import net.aicoder.epi.base.view.element.area.EpiTabArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;

public class ProductOpsFolderArea extends BaseArea{
	public static final String ID = ProductOpsFolderArea.class.getName();
	private IEpiArea[] epiAreas;
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		epiAreas = new IEpiArea[1];
		epiAreas[0] = new ProductOpsFolderDesDgmArea();
//		epiAreas[1] = new ProductOpsFolderSubElmArea(); //目前没有子元素,后期会扩充
		
		EpiTabArea tabArea = new EpiTabArea(this.getWorkbenchPart());
		tabArea.setEpiAreas(epiAreas);
		tabArea.createControl(parent);
		tabArea.getTabFolder().setSelection(0);
	
		return tabArea.getControl();
	}
	
	public void setElementSelection(ISelection selection) {
		((ProductOpsFolderDesDgmArea)epiAreas[0]).setElementSelection(selection);
	}

}
