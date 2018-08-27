package net.aicoder.epi.devp.prddev.model.ops;

import net.aicoder.epi.base.model.BaseVo;

/**
 * 部署模型-资源规划实体
 * @author WANGQINGPING
 *
 */
public class OpsResPlanVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	
	private String type; // 类型
	private String subType; // 子类型
	private String stereotype; // 构造型
	private String scope; // 范围
	private String version; // 版本
	private String phase; // 阶段
	private String status; // 状态
	private String notes; // 备注
	private long prdRid; // 产品编号
	private int seq; // 顺序号
	
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the subType
	 */
	public String getSubType() {
		return subType;
	}
	/**
	 * @param subType the subType to set
	 */
	public void setSubType(String subType) {
		this.subType = subType;
	}
	/**
	 * @return the stereotype
	 */
	public String getStereotype() {
		return stereotype;
	}
	/**
	 * @param stereotype the stereotype to set
	 */
	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
	}
	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}
	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the phase
	 */
	public String getPhase() {
		return phase;
	}
	/**
	 * @param phase the phase to set
	 */
	public void setPhase(String phase) {
		this.phase = phase;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return the prdRid
	 */
	public long getPrdRid() {
		return prdRid;
	}
	/**
	 * @param prdRid the prdRid to set
	 */
	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
	}
	/**
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	
}
