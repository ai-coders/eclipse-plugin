package net.aicoder.epi.base.view.element.table;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.definer.ViewDefiner;

public class EpiTableDefiner extends ViewDefiner {
	public EpiTableDefiner() {
		super();
		newContentProvider();
		newLabelProvider();
	}

	public EpiTableDefiner(Object[][] viewDefine) {
		super();
		setViewDefine(viewDefine);
		newContentProvider();
		newLabelProvider();
	}

	public EpiTableDefiner(Object[][] viewDefine, Object[][] columnsDefine) {
		super(viewDefine, columnsDefine);
		newContentProvider();
		newLabelProvider();
	}

	private void newContentProvider() {
		IContentProvider contentProvider = new EpiTableContentProvider(this);
		setContentProvider(contentProvider);
	}

	private void newLabelProvider() {
/**		
		if (this.getColumnDefinerList().size() != 0) {
			ITableLabelProvider columnLablerProvider = new EpiColumnLabelProvider(this);
			setColumnLabelProvider(columnLablerProvider);
		}
		ILabelProvider lablerProvider = new EpiTableLabelProvider(this);
		setLabelProvider(lablerProvider);
**/
		ITableLabelProvider columnLablerProvider = new EpiColumnLabelProvider(this);
		setColumnLabelProvider(columnLablerProvider);
	}
}
