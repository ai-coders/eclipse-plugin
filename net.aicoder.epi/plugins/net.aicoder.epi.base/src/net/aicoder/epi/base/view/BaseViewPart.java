package net.aicoder.epi.base.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.part.ViewPart;

import net.aicoder.epi.base.view.element.area.IEpiArea;

public abstract class BaseViewPart extends ViewPart {
	protected IEpiArea pageArea;
	private Composite parent;

	//// Constructor
	public BaseViewPart() {
		super();
		initialize(this);
	}

	public final void initialize(IViewPart viewPart) {
		pageArea = newPageArea();
		if(pageArea != null) {
			pageArea.setWorkbenchPart(viewPart);
		}
	}
	
	public abstract IEpiArea newPageArea();

	@Override
	public final void createPartControl(Composite parent) {
		this.parent = parent;
		createControl(parent);
	}

	//@Override
	public abstract void createControl(Composite parent);
	
	public Control getControl() {
		return parent;
	}
}
