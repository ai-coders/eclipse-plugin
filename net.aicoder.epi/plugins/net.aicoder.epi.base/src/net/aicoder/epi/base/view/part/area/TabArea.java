package net.aicoder.epi.base.view.part.area;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

public class TabArea extends AbstractBaseArea {
	protected IArea[] areas;

	private CTabFolder tabFolder;
	private CTabItem[] tabItems;
	
	//// Constructor
	public TabArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
		initArea();
	}
	
	@Override
	public void initArea() {
	}

	@Override
	public final void createControl(Composite parent) {
		tabFolder = new CTabFolder(parent,SWT.NONE);
		if(areas!=null && areas.length >0) {
			int epiAreasLength = areas.length;
			tabItems = new CTabItem[epiAreasLength];
			for(int tabIdx = 0; tabIdx<epiAreasLength; tabIdx++) {
				IArea epiArea = areas[tabIdx];
				//epiArea.setEditor(this.getEditor());
				epiArea.setWorkbenchPart(this.getWorkbenchPart());
				tabItems[tabIdx] = new CTabItem(tabFolder,SWT.NONE);
				if(epiArea.getTitleText()!=null) {
					tabItems[tabIdx].setText(epiArea.getTitleText());
				}
				if(epiArea.getTitleImage()!=null) {
					tabItems[tabIdx].setImage(epiArea.getTitleImage());
				}
				
				Control control = createAreaComposite(tabFolder,epiArea);
				tabItems[tabIdx].setControl(control);
			}
		}
		
		attachEvent();
	}
	
	private Control createAreaComposite(Composite parent, IArea epiArea) {
		epiArea.createControl(parent);
		return epiArea.getControl();
	}
	
	@Override
	public final void assembleControl(Composite parent) {
	}

	@Override
	public void attachEvent() {
	}


	public CTabFolder getTabFolder() {
		return this.tabFolder;
	}
	
	@Override
	public Control getControl() {
		return tabFolder;
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
	}

	@Override
	public boolean setFocus() {
		return tabFolder.setFocus();
	}

	@Override
	public void dispose() {
	}

	public IArea[] getEpiAreas() {
		return areas;
	}

	public void setEpiAreas(IArea[] epiAreas) {
		this.areas = epiAreas;
	}
}
