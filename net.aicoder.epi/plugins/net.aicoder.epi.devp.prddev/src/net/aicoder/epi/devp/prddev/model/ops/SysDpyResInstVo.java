package net.aicoder.epi.devp.prddev.model.ops;

import net.aicoder.epi.base.model.BaseVo;

public class SysDpyResInstVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String type;
  	private String subType;
  	private String stereotype;
  	private String scope;
  	private String version;
  	private String phase;
  	private String status;
  	private String notes;
  	private long prdRid;
  	private long resRid;
  	private long parentRid;
  	private long seq;
  	private long assetRid;
  	private String assetEtype;
  	private String assetTypeCode;
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
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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
	public long getParentRid() {
		return parentRid;
	}
	public void setParentRid(long parentRid) {
		this.parentRid = parentRid;
	}
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
	public long getAssetRid() {
		return assetRid;
	}
	public void setAssetRid(long assetRid) {
		this.assetRid = assetRid;
	}
	public String getAssetEtype() {
		return assetEtype;
	}
	public void setAssetEtype(String assetEtype) {
		this.assetEtype = assetEtype;
	}
	public String getAssetTypeCode() {
		return assetTypeCode;
	}
	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
	}

}
