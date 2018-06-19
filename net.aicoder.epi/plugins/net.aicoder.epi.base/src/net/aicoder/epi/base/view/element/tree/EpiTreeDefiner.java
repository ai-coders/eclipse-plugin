package net.aicoder.epi.base.view.element.tree;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;

import net.aicoder.epi.base.view.definer.ViewDefiner;
import net.aicoder.epi.base.view.element.table.EpiColumnLabelProvider;

public class EpiTreeDefiner extends ViewDefiner {
	public EpiTreeDefiner() {
		super();
		newContentProvider();
		newLabelProvider();
	}

	public EpiTreeDefiner(Object[][] viewDefine) {
		super(viewDefine);
		newContentProvider();
		newLabelProvider();
	}

	public EpiTreeDefiner(Object[][] viewDefine, Object[][] columnsDefine) {
		super(viewDefine, columnsDefine);
		newContentProvider();
		newLabelProvider();
	}
	
	private void newContentProvider() {
		IContentProvider contentProvider = new EpiTreeContentProvider(this);
		setContentProvider(contentProvider);
	}

	private void newLabelProvider() {
		if (this.getColumnDefinerList().size() != 0) {
			ITableLabelProvider columnLablerProvider = new EpiColumnLabelProvider(this);
			setColumnLabelProvider(columnLablerProvider);
		}
		ILabelProvider lablerProvider = new EpiTreeLabelProvider(this);
		setLabelProvider(lablerProvider);
	}
}
