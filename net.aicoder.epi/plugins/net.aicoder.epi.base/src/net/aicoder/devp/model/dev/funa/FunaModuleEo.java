package net.aicoder.devp.model.dev.funa;

import net.aicoder.devp.model.DevpBaseEo;

public class FunaModuleEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private int mduFlag; // 模块标志,0:产品;1:模块;2:功能
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

	public int getMduFlag() {
		return mduFlag;
	}

	public void setMduFlag(int mduFlag) {
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
