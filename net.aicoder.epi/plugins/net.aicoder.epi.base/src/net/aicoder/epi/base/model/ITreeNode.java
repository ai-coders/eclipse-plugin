package net.aicoder.epi.base.model;

import java.util.List;

public interface ITreeNode{
	public void setRootNode(ITreeNode rootNode);
	
	public <T extends ITreeNode> T getRootNode();

	public void setParentNode(ITreeNode parentNode);
	
	public <T extends ITreeNode> T getParentNode();
	
	public boolean hasChildren();
	
	public <T extends IBaseVo> List<T> getChildrenList();
}