package net.aicoder.epi.devp.prddev.model.dev.system;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.model.BaseVo;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.devp.prddev.model.dev.funa.FunaModuleVo;

public class SysElementVo extends BaseVo implements ITreeNode {
	private static final long serialVersionUID = 1L;

	private String elmFlag; // 系统元素所属类型标识: CMP-组件,IDE-开发,DPY-部署
	
	private ITreeNode rootNode;
	private ITreeNode parentNode;
	private List<IBaseVo> childrenList = new ArrayList<IBaseVo>(0);

	@Override
	public String getEtype() {
		return this.elmFlag;
	}

	@Override
	public void setRootNode(ITreeNode rootNode) {
		this.rootNode = (FunaModuleVo) rootNode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ITreeNode getRootNode() {
		return this.rootNode;
	};

	@Override
	public void setParentNode(ITreeNode parentNode) {
		this.parentNode = (FunaModuleVo) parentNode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ITreeNode getParentNode() {
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
	
	//// getter/setter
	public String getElmFlag() {
		return elmFlag;
	}

	public void setElmFlag(String elmFlag) {
		this.elmFlag = elmFlag;
	}
}
