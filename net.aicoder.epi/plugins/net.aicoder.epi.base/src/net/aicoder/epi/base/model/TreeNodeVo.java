package net.aicoder.epi.base.model;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeVo extends BaseVo implements ITreeNode {
	private static final long serialVersionUID = 1L;
	
	private TreeNodeVo rootNode;
	private TreeNodeVo parentNode;
	private List<IBaseVo> childrenList = new ArrayList<IBaseVo>(0);
	
	public TreeNodeVo() {
		super();
	}

	@Override
	public void setRootNode(ITreeNode rootNode) {
		this.rootNode = (TreeNodeVo) rootNode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TreeNodeVo getRootNode() {
		return this.rootNode;
	};

	@Override
	public void setParentNode(ITreeNode parentNode) {
		this.parentNode = (TreeNodeVo) parentNode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TreeNodeVo getParentNode() {
		return this.parentNode;
	}

	@Override
	public boolean hasChildren() {
		if (childrenList == null) {
			return false;
		}
		return childrenList.size() > 0 ? true : false;
	};

	@Override
	public List<IBaseVo> getChildrenList() {
		return this.childrenList;
	}
}
