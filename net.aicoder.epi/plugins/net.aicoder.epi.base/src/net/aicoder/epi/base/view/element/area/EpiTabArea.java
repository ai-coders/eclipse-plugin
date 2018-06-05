package net.aicoder.epi.base.view.element.area;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class EpiTabArea implements IEpiArea {
	private CTabFolder tabFolder;
	private CTabItem[] tabItems;
	private IEpiArea[] epiAreas;
	
	private String titleText;
	private Image titleImage;

	@Override
	public final void createControl(Composite parent) {
		tabFolder = new CTabFolder(parent,SWT.NONE);
		if(epiAreas!=null && epiAreas.length >0) {
			int epiAreasLength = epiAreas.length;
			tabItems = new CTabItem[epiAreasLength];
			for(int tabIdx = 0; tabIdx<epiAreasLength; tabIdx++) {
				IEpiArea epiArea = epiAreas[tabIdx];
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
	}
	
	private Control createAreaComposite(Composite parent, IEpiArea epiArea) {
		epiArea.createControl(parent);
		return epiArea.getControl();
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

	@Override
	public String getTitleText() {
		return titleText;
	}

	@Override
	public Image getTitleImage() {
		return titleImage;
	}

	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	public void setTitleImage(Image titleImage) {
		this.titleImage = titleImage;
	}

	public IEpiArea[] getEpiAreas() {
		return epiAreas;
	}

	public void setEpiAreas(IEpiArea[] epiAreas) {
		this.epiAreas = epiAreas;
	}
}
