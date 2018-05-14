package net.aicoder.epi.base.view.explorer;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.IEditorInput;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.adapter.IEpiInput;
import net.aicoder.epi.base.view.adapter.IEpiEditorInput;
import net.aicoder.epi.base.view.definer.ViewDefiner;

public class EpiExplorerDefiner extends ViewDefiner {
	public EpiExplorerDefiner() {
		super();
	}

	public EpiExplorerDefiner(Object[][] viewDefine) {
		super();
		setViewDefine(viewDefine);
		newContentProvider();
		newLabelProvider();
	}

	@Override
	public IEpiInput createAdapter(IBaseVo selectionElement) {
		return getAdapter();
	}

	@Override
	public IEpiEditorInput createEditorInput(IBaseVo selectionElement) {
		return getEditorInput();
	}

	private void newContentProvider() {
		IContentProvider contentProvider = new EpiExplorerContentProvider(this);
		setContentProvider(contentProvider);
	}

	private void newLabelProvider() {
		ILabelProvider lablerProvider = new EpiExplorerLabelProvider(this);
		setLabelProvider(lablerProvider);
	}

}
