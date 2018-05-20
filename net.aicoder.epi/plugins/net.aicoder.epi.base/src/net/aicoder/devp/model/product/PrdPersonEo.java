package net.aicoder.devp.model.product;

import net.aicoder.devp.model.DevpBaseEo;

public class PrdPersonEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private String nexusType; // 关联元素类型
	private long nexusRid; // 关联元素编号
	private int seq; // 顺序号
	private long uid; // 用户编号
	private String type; // 用户类型
	private String role; // 用户类型
	private String status; // 状态
	private long userTid; // 用户租户编号
	private long orgRid; // 组织编号
	private String orgName; // 组织名称

	public String getNexusType() {
		return nexusType;
	}

	public void setNexusType(String nexusType) {
		this.nexusType = nexusType;
	}

	public long getNexusRid() {
		return nexusRid;
	}

	public void setNexusRid(long nexusRid) {
		this.nexusRid = nexusRid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getUserTid() {
		return userTid;
	}

	public void setUserTid(long userTid) {
		this.userTid = userTid;
	}

	public long getOrgRid() {
		return orgRid;
	}

	public void setOrgRid(long orgRid) {
		this.orgRid = orgRid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
