package net.aicoder.epi.devp.prddev.model.dev.system;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.model.BaseVo;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.devp.prddev.model.dev.funa.FunaModuleVo;

public class SysElmCatgVo extends BaseVo implements ITreeNode {
	private static final long serialVersionUID = 1L;

	private String elmFlag; // 系统元素所属类型标识: CMP-组件,IDE-开发,DPY-部署
	private String type; // 类型
	private String subType; // 子类型
	private String stereotype; // 构造型
	private String scope; // 范围
	private String version; // 版本
	private String phase; // 阶段
	private String status; // 状态
	private long prdRid; // 产品编号
	private long parentRid; // 父包编号
	private int seq; // 顺序号
	private int sharedComponent; //是否为共享组件
	private int sharedService; //是否提供共享服务
	private String notes; // 备注
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getStereotype() {
		return stereotype;
	}

	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getPrdRid() {
		return prdRid;
	}

	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
	}

	public long getParentRid() {
		return parentRid;
	}

	public void setParentRid(long parentRid) {
		this.parentRid = parentRid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getSharedComponent() {
		return sharedComponent;
	}

	public void setSharedComponent(int sharedComponent) {
		this.sharedComponent = sharedComponent;
	}

	public int getSharedService() {
		return sharedService;
	}

	public void setSharedService(int sharedService) {
		this.sharedService = sharedService;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
