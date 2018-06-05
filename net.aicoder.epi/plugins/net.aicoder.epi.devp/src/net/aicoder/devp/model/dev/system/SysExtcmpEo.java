package net.aicoder.devp.model.dev.system;

import net.aicoder.devp.model.BaseDevpEo;

public class SysExtcmpEo extends BaseDevpEo {
	private static final long serialVersionUID = 1L;

	private long prdRid; //产品编号
	private long extPrdRid; //外部产品编号
	private String extElmFlag; // 系统元素所属类型标识: CMP-组件,IDE-开发,DPY-部署
	private long extElmRid; //外部系统元素编号
	private int seq; //顺序号
	private String type; // 类型
	private String stereotype; // 构造型

	public long getPrdRid() {
		return prdRid;
	}

	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
	}

	public long getExtPrdRid() {
		return extPrdRid;
	}

	public void setExtPrdRid(long extPrdRid) {
		this.extPrdRid = extPrdRid;
	}

	public long getExtElmRid() {
		return extElmRid;
	}

	public void setExtElmRid(long extElmRid) {
		this.extElmRid = extElmRid;
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

}
