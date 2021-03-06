package net.aicoder.epi.base.view.property;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.base.view.context.IEpiInput;

public class PropsContentProvider implements ITreeContentProvider{
	protected TreeViewer viewer;

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (TreeViewer)viewer;
		if(newInput != null) {
			//((EpiAdapter)oldInput).addPropertyChangeListener(listener); //未作处理
		}
		if(oldInput != null) {
			//((EpiAdapter)oldInput).removePropertyChangeListener(listener); //未作处理
			oldInput = null;
		}
	}

	//// getter/setter
	@Override
	public Object[] getElements(Object input) {
		Object[] elements = new Object[0];
		if (input instanceof PropsInput) {
			PropsInput propsInput = (PropsInput) input;
			if(propsInput != null) {
				elements = propsInput.getPropertyInfos();
			}
		}
		return elements;
	}

	@Override
	public Object getParent(Object element) {
		Object parent = null;
		if (element instanceof ITreeNode) {
			parent = ((ITreeNode) element).getParentNode();
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
}
