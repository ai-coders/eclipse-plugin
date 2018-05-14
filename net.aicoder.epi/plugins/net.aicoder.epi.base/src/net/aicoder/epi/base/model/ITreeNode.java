package net.aicoder.epi.base.model;

import java.util.List;

public interface ITreeNode{
	public void setParentData(ITreeNode parent);
	
	public ITreeNode getParentData();
	
	public boolean hasChildren();
	
	public List<IBaseVo> getChildrenList();

}