package net.aicoder.epi.devp.prddev.model.ops;

import net.aicoder.epi.base.model.BaseVo;

public class OpsAssetGroupVo extends BaseVo{
	private static final long serialVersionUID = 1L;

	private String typeCode;
	private String typeName;
	private String stereotype;
	private String scope;
	private String version;
	private String phase;
	private String status;
	private long parentRid;
	private long seq;
	private String parasCode;
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	public String getParasCode() {
		return parasCode;
	}
	public void setParasCode(String parasCode) {
		this.parasCode = parasCode;
	}
  
}
