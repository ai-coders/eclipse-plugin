package net.aicoder.epi.base.view.explorer;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import net.aicoder.epi.base.view.definer.IViewDefiner;

@SuppressWarnings("deprecation")
public class EpiExplorerViewerSorter extends ViewerSorter{
	IViewDefiner viewDefiner;
	
	public EpiExplorerViewerSorter() {
		super();
	}
	
	public EpiExplorerViewerSorter(IViewDefiner viewDefiner) {
		super();
		setViewDefiner(viewDefiner);
	}

	
	public int compare(Viewer viewer, Object e1, Object e2) {
		return 1; // 按原来的顺序输出
	}

	
/**	
	public int compare(Viewer viewer, Object e1, Object e2) {
		// Make sure the local repository node is the first item in the tree
		if (e1 instanceof LocalRepositoryNode) {
			return -1;
		}
		else if (e2 instanceof LocalRepositoryNode) {
			return 1;
		}
		if (e1 instanceof IConnectionProfile && e2 instanceof IConnectionProfile) {
			IConnectionProfile icp1 = (IConnectionProfile) e1;
			IConnectionProfile icp2 = (IConnectionProfile) e2;
			return icp1.getName().compareToIgnoreCase(icp2.getName());
		}
		if (e1 instanceof ICategory && e2 instanceof ICategory) {
			ICategory icat1 = (ICategory) e1;
			ICategory icat2 = (ICategory) e2;
			return icat1.getName().compareToIgnoreCase(icat2.getName());
		}
		return super.compare(viewer, e1, e2);
	}
**/
	
	//// getter/setter
	public IViewDefiner getViewDefiner() {
		return viewDefiner;
	}

	public void setViewDefiner(IViewDefiner viewDefiner) {
		this.viewDefiner = viewDefiner;
	}
}
