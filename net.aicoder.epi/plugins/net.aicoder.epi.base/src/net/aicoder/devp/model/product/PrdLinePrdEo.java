package net.aicoder.devp.model.product;

import net.aicoder.devp.model.DevpBaseEo;

public class PrdLinePrdEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private long lineRid; // 产品线编号
	private long prdRid; // 产品编号
	private int seq; // 顺序号
	private String type; //  类型
	private String stereotype; //  构造型
	private String scope; //  范围

	public long getLineRid() {
		return lineRid;
	}

	public void setLineRid(long lineRid) {
		this.lineRid = lineRid;
	}

	public long getPrdRid() {
		return prdRid;
	}

	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
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
