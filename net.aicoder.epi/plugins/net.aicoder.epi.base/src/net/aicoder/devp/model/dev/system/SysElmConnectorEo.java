package net.aicoder.devp.model.dev.system;

import net.aicoder.devp.model.DevpBaseEo;

public class SysElmConnectorEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private long sprdRid; // 来源产品编号
	private long selmRid; // 来源系统元素编号
	private long dprdRid; // 目标产品编号
	private long delmRid; // 目标系统元素编号
	private long selmInstRid; // 来源系统元素实例编号
	private long delmInstRid; // 目标系统元素实例编号
	private int seq; // 顺序号
	private String type; // 对应关系类型
	private String subType; // 对应关系子类型
	private String stereotype; // 构造型
	private String scope; // 范围
	private String direction; // 方向
	private String srcMulti; // 来源对应数量
	private String srcRole; // 来源角色
	private String srcRoleType; // 来源角色类型
	private String destMulti; // 目标对应数量
	private String destRole; // 目标角色
	private String destRoleType; // 目标角色类型
	private String attrRelation; // 属性对应关系

	public long getSprdRid() {
		return sprdRid;
	}

	public void setSprdRid(long sprdRid) {
		this.sprdRid = sprdRid;
	}

	public long getSelmRid() {
		return selmRid;
	}

	public void setSelmRid(long selmRid) {
		this.selmRid = selmRid;
	}

	public long getDprdRid() {
		return dprdRid;
	}

	public void setDprdRid(long dprdRid) {
		this.dprdRid = dprdRid;
	}

	public long getDelmRid() {
		return delmRid;
	}

	public void setDelmRid(long delmRid) {
		this.delmRid = delmRid;
	}

	public long getSelmInstRid() {
		return selmInstRid;
	}

	public void setSelmInstRid(long selmInstRid) {
		this.selmInstRid = selmInstRid;
	}

	public long getDelmInstRid() {
		return delmInstRid;
	}

	public void setDelmInstRid(long delmInstRid) {
		this.delmInstRid = delmInstRid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getSrcMulti() {
		return srcMulti;
	}

	public void setSrcMulti(String srcMulti) {
		this.srcMulti = srcMulti;
	}

	public String getSrcRole() {
		return srcRole;
	}

	public void setSrcRole(String srcRole) {
		this.srcRole = srcRole;
	}

	public String getSrcRoleType() {
		return srcRoleType;
	}

	public void setSrcRoleType(String srcRoleType) {
		this.srcRoleType = srcRoleType;
	}

	public String getDestMulti() {
		return destMulti;
	}

	public void setDestMulti(String destMulti) {
		this.destMulti = destMulti;
	}

	public String getDestRole() {
		return destRole;
	}

	public void setDestRole(String destRole) {
		this.destRole = destRole;
	}

	public String getDestRoleType() {
		return destRoleType;
	}

	public void setDestRoleType(String destRoleType) {
		this.destRoleType = destRoleType;
	}

	public String getAttrRelation() {
		return attrRelation;
	}

	public void setAttrRelation(String attrRelation) {
		this.attrRelation = attrRelation;
	}

}
