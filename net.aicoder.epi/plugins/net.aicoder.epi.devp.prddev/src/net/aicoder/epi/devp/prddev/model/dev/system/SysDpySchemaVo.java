package net.aicoder.epi.devp.prddev.model.dev.system;

import net.aicoder.epi.base.model.BaseVo;

/**
 * 部署模型-部署方案实体
 * @author WANGQINGPING
 *
 */
public class SysDpySchemaVo extends BaseVo{
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
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}

}
