package net.aicoder.epi.devp.prddev.model.dev.system;

import net.aicoder.epi.base.model.TreeNodeVo;

/**
 * 资源实例实体
 * @author WANGQINGPING
 *
 */
public class SysDpyResInstVo extends TreeNodeVo{
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
	private long resRid; // 关联资源编号
	private long parentRid; // 父包编号
	private int seq; // 顺序号
	private long assetRid; // 关联IT资产编号
	private String assetEtype; // 关联IT资产元素类型
	private String assetTypeCode; // 关联IT资产类型代码
	
	
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
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
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