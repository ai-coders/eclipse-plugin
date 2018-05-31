package net.aicoder.epi.devp.prddev.model.dev.funa;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.model.BaseVo;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;

public class FunaModuleVo extends BaseVo implements ITreeNode {
	private static final long serialVersionUID = 1L;
	
	private String mduFlag; // 模块标志
	private String type; // 类型
	private String stereotype; // 构造型
	private String scope; // 访问控制范围
	private String actor; // 使用者
	private String priority; // 优先级
	private String version; // 版本
	private String phase; // 阶段
	private String status; // 状态
	private long prdRid; // 产品编号
	private long parentRid; // 父模块编号
	private int seq; // 顺序号
	private String notes; // 备注

	private FunaModuleVo rootNode;
	private FunaModuleVo parentNode;
	private List<IBaseVo> childrenList = new ArrayList<IBaseVo>(0);

	@Override
	public String getEtype() {
		return this.mduFlag;
	}

	@Override
	public void setRootNode(ITreeNode rootNode) {
		this.rootNode = (FunaModuleVo) rootNode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FunaModuleVo getRootNode() {
		return this.rootNode;
	};

	@Override
	public void setParentNode(ITreeNode parentNode) {
		this.parentNode = (FunaModuleVo) parentNode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FunaModuleVo getParentNode() {
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
	public String getMduFlag() {
		return mduFlag;
	}

	public void setMduFlag(String mduFlag) {
		this.mduFlag = mduFlag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
