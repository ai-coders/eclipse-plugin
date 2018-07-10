package net.aicoder.epi.devp.prddev.model.dev.system;

import net.aicoder.epi.base.model.BaseVo;

public class SysDpyInstSchemeVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String type;
  	private String subType;
  	private String stereotype;
  	private long prdRid;
  	private long resRid;
  	private long instRid;
  	private long schemeRid;
  	private long seq;
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
	public long getResRid() {
		return resRid;
	}
	public void setResRid(long resRid) {
		this.resRid = resRid;
	}
	public long getInstRid() {
		return instRid;
	}
	public void setInstRid(long instRid) {
		this.instRid = instRid;
	}
	public long getSchemeRid() {
		return schemeRid;
	}
	public void setSchemeRid(long schemeRid) {
		this.schemeRid = schemeRid;
	}
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}

}
