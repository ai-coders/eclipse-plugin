package net.aicoder.devp.model.dev.system;

import net.aicoder.devp.model.DevpBaseEo;

public class SysDgmElementEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private long prdRid; // 产品编号
	private long dgmRid; // UML图编号
	private long elmRid; // 系统元素编号
	private long elmInstRid; // 系统元素实例编号
	private int seq; // 顺序号
	private String type; // 类型
	private String subType; // 子类型
	private String stereotype; // 构造型
	private String scope; // 范围

	public long getPrdRid() {
		return prdRid;
	}

	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
	}

	public long getDgmRid() {
		return dgmRid;
	}

	public void setDgmRid(long dgmRid) {
		this.dgmRid = dgmRid;
	}

	public long getElmRid() {
		return elmRid;
	}

	public void setElmRid(long elmRid) {
		this.elmRid = elmRid;
	}

	public long getElmInstRid() {
		return elmInstRid;
	}

	public void setElmInstRid(long elmInstRid) {
		this.elmInstRid = elmInstRid;
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

}
