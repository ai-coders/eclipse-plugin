package net.aicoder.devp.model.dev.system;

import net.aicoder.devp.model.DevpBaseEo;

public class SysElmInstanceEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private String elmFlag; // 系统元素所属类型标识
	private String type; // 类型
	private String subType; // 子类型
	private String stereotype; // 构造型
	private long prdRid; // 产品编号
	private long elmRid; // 系统元素编号
	private int seq; // 顺序号

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
