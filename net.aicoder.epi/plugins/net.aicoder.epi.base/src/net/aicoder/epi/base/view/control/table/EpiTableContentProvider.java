package net.aicoder.epi.base.view.control.table;

import org.eclipse.jface.viewers.IStructuredContentProvider;

import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.definer.IViewDefiner;

public class EpiTableContentProvider implements IStructuredContentProvider {
	IViewDefiner viewDefiner;
	
	public EpiTableContentProvider(EpiTableDefiner tableDefiner) {
		super();
		setViewDefiner(viewDefiner);
	}

	@Override
	public Object[] getElements(Object input) {
		Object[] elements = new Object[0];
		if (input instanceof IEpiInput) {
			IEpiInput adapter = (IEpiInput) input;
			if(adapter != null && adapter.getDataList() != null) {
				elements = adapter.getDataList().toArray();
			}
		}
		return elements;
	}

	public IViewDefiner getViewDefiner() {
		return viewDefiner;
	}

	public void setViewDefiner(IViewDefiner viewDefiner) {
		this.viewDefiner = viewDefiner;
	}

}
