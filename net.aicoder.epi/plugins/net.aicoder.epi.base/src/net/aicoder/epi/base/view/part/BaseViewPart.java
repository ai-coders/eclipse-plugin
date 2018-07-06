package net.aicoder.epi.base.view.part;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.part.ViewPart;

import net.aicoder.epi.base.view.part.area.IArea;

public abstract class BaseViewPart extends ViewPart {
	protected IArea pageArea;
	private Composite parent;

	//// Constructor
	public BaseViewPart() {
		super();
		initialize();
	}

	private void initialize() {
		pageArea = newPageArea();
		if(pageArea != null) {
			pageArea.setWorkbenchPart(this);
		}
	}
	
	protected abstract IArea newPageArea();

	@Override
	public final void createPartControl(Composite parent) {
		this.parent = parent;
		createControl(parent);
	}

	public abstract void createControl(Composite parent);
	
	public Control getControl() {
		return parent;
	}
}
