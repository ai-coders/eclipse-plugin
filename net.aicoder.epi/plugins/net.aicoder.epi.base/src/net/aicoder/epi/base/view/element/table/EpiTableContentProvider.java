package net.aicoder.epi.base.view.element.table;

import org.eclipse.jface.viewers.IStructuredContentProvider;

import net.aicoder.epi.base.view.adapter.IEpiInput;
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
			if(adapter != null) {
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
