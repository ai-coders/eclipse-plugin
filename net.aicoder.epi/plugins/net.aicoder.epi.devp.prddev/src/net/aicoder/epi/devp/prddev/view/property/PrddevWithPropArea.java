package net.aicoder.epi.devp.prddev.view.property;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.property.BaseWithPropArea;
import net.aicoder.epi.devp.prddev.PrddevPlugin;

public abstract class PrddevWithPropArea extends BaseWithPropArea {
	public static String ID = PrddevWithPropArea.class.getName();
	
	public PrddevWithPropArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart, new PrddevPropsManager());
	}
	
	@Override
	public void setElementSelection(ISelection selection) {
		propsArea.setElementSelection(selection);
	}
}
