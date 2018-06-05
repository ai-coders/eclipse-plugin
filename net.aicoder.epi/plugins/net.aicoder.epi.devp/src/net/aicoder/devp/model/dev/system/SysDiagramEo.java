package net.aicoder.devp.model.dev.system;

import net.aicoder.devp.model.BaseDevpEo;

public class SysDiagramEo extends BaseDevpEo {
	private static final long serialVersionUID = 1L;

	private String dgmFlag; // 系统元素所属类型标识
	private String type; // 类型
	private String subType; // 子类型
	private String stereotype; // 构造型
	private String scope; // 范围
	private String version; // 版本
	private String phase; // 阶段
	private String status; // 状态
	private long prdRid; // 产品编号
	private long elmRid; // 系统元素编号
	private int seq; // 顺序号

	public String getDgmFlag() {
		return dgmFlag;
	}

	public void setDgmFlag(String dgmFlag) {
		this.dgmFlag = dgmFlag;
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

	public long getElmRid() {
		return elmRid;
	}

	public void setElmRid(long elmRid) {
		this.elmRid = elmRid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

}
