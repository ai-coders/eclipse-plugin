package net.aicoder.epi.base.view.explorer;

import java.util.List;

import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.base.view.adapter.IEpiInput;
import net.aicoder.epi.base.view.definer.IViewDefiner;

public class EpiExplorerContentProvider implements ICommonContentProvider{
	IViewDefiner viewDefiner;
	
	public EpiExplorerContentProvider() {
		super();
	}

	public EpiExplorerContentProvider(IViewDefiner viewDefiner) {
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

	@Override
	public Object getParent(Object element) {
		Object parent = null;
		if (element instanceof ITreeNode) {
			parent = ((ITreeNode) element).getParentData();
		}
		return parent;
	}

	@Override
	public boolean hasChildren(Object element) {
		boolean hasChildren = false;
		if (element instanceof ITreeNode) {
			hasChildren = ((ITreeNode) element).hasChildren();
		}
		return hasChildren;
	}

	@Override
	public Object[] getChildren(Object element) {
		Object[] children = new Object[0];
		if (element instanceof ITreeNode) {
			List<IBaseVo> list = ((ITreeNode) element).getChildrenList();
			if(list != null) {
				children = list.toArray();
			}
		}
		return children;
	}

	@Override
	public void restoreState(IMemento arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveState(IMemento arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(ICommonContentExtensionSite arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public IViewDefiner getViewDefiner() {
		return viewDefiner;
	}

	public void setViewDefiner(IViewDefiner viewDefiner) {
		this.viewDefiner = viewDefiner;
	}

}
