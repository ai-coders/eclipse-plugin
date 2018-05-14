package net.aicoder.epi.base.view;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class EpiPerspective implements IPerspectiveFactory {
	public static String ID = EpiPerspective.class.getName();
	
	public EpiPerspective() {
		super();
	}

	@Override
	public void createInitialLayout(IPageLayout arg0) {
		//this.layout = layout;
	}
	
}
