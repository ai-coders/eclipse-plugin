package net.aicoder.epi.devp.prddev.view.property;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.property.BaseWithPropArea;

public abstract class PrddevWithPropArea extends BaseWithPropArea {
	public static String ID = PrddevWithPropArea.class.getName();
	
	public PrddevWithPropArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}
	
	@Override
	public void initArea() {
		super.initArea();
		propsManager = new PrddevPropsManager();
		propsArea.setPropsManager(propsManager);
	}

	@Override
	public void setElementSelection(ISelection selection) {
		propsArea.setElementSelection(selection);
	}
}
