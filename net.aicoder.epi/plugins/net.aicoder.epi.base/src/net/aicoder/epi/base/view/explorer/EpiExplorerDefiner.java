package net.aicoder.epi.base.view.explorer;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ViewerSorter;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.adapter.IEpiInput;
import net.aicoder.epi.base.view.adapter.IEpiEditorInput;
import net.aicoder.epi.base.view.definer.ViewDefiner;

@SuppressWarnings("deprecation")
public class EpiExplorerDefiner extends ViewDefiner {
	private ViewerSorter sorter;
	
	public EpiExplorerDefiner() {
		super();
	}

	public EpiExplorerDefiner(Object[][] viewDefine) {
		super();
		setViewDefine(viewDefine);
		newContentProvider();
		newLabelProvider();
		newViewerSorter();
	}

	@Override
	public IEpiInput createInput(IBaseVo selectionElement) {
		return getInput();
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
	
	private void newViewerSorter() {
		sorter = new EpiExplorerViewerSorter(this);
		setSorter(sorter);
	}

	//// getter/setter
	public ViewerSorter getSorter() {
		return sorter;
	}

	public void setSorter(ViewerSorter sorter) {
		this.sorter = sorter;
	}
}
