package net.aicoder.epi.devp.prddev.model.ops;

import net.aicoder.epi.base.model.BaseVo;

/**
 * IT资产配置数据库表实体
 * @author WANGQINGPING
 *
 */
public class OpsAssetCmdbVo extends BaseVo{
	private static final long serialVersionUID = 1L;
	private String typeCode;
	private String typeName;
	private String stereotype;
	private String scope;
	private String hardwareModel;
	private String softwareModel;
	private String version;
	private String status;
	private java.sql.Date createDate;
	private java.sql.Date expireDate;
	private String assetProject;
	private String assetArea;
	private String assetLocation;
	private String intAccessAddr;
	private String extAccessAddr;
	private String acquisitionMode;
	private String acquisitionDesc;
	private String assetDept;
	private String assetManager;
	private String useDept;
	private String useManager;
	private String opsDept;
	private String opsManager;
	private String bizLine;
	private String bizManager;
	private java.sql.Date goliveDate;
	private String majorCust;
	private String custManager;
	private String custUsage;
	private String notes;
	private long prdTid;
	private long prdRid;
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
	public String getHardwareModel() {
		return hardwareModel;
	}
	public void setHardwareModel(String hardwareModel) {
		this.hardwareModel = hardwareModel;
	}
	public String getSoftwareModel() {
		return softwareModel;
	}
	public void setSoftwareModel(String softwareModel) {
		this.softwareModel = softwareModel;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public java.sql.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.sql.Date createDate) {
		this.createDate = createDate;
	}
	public java.sql.Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(java.sql.Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getAssetProject() {
		return assetProject;
	}
	public void setAssetProject(String assetProject) {
		this.assetProject = assetProject;
	}
	public String getAssetArea() {
		return assetArea;
	}
	public void setAssetArea(String assetArea) {
		this.assetArea = assetArea;
	}
	public String getAssetLocation() {
		return assetLocation;
	}
	public void setAssetLocation(String assetLocation) {
		this.assetLocation = assetLocation;
	}
	public String getIntAccessAddr() {
		return intAccessAddr;
	}
	public void setIntAccessAddr(String intAccessAddr) {
		this.intAccessAddr = intAccessAddr;
	}
	public String getExtAccessAddr() {
		return extAccessAddr;
	}
	public void setExtAccessAddr(String extAccessAddr) {
		this.extAccessAddr = extAccessAddr;
	}
	public String getAcquisitionMode() {
		return acquisitionMode;
	}
	public void setAcquisitionMode(String acquisitionMode) {
		this.acquisitionMode = acquisitionMode;
	}
	public String getAcquisitionDesc() {
		return acquisitionDesc;
	}
	public void setAcquisitionDesc(String acquisitionDesc) {
		this.acquisitionDesc = acquisitionDesc;
	}
	public String getAssetDept() {
		return assetDept;
	}
	public void setAssetDept(String assetDept) {
		this.assetDept = assetDept;
	}
	public String getAssetManager() {
		return assetManager;
	}
	public void setAssetManager(String assetManager) {
		this.assetManager = assetManager;
	}
	public String getUseDept() {
		return useDept;
	}
	public void setUseDept(String useDept) {
		this.useDept = useDept;
	}
	public String getUseManager() {
		return useManager;
	}
	public void setUseManager(String useManager) {
		this.useManager = useManager;
	}
	public String getOpsDept() {
		return opsDept;
	}
	public void setOpsDept(String opsDept) {
		this.opsDept = opsDept;
	}
	public String getOpsManager() {
		return opsManager;
	}
	public void setOpsManager(String opsManager) {
		this.opsManager = opsManager;
	}
	public String getBizLine() {
		return bizLine;
	}
	public void setBizLine(String bizLine) {
		this.bizLine = bizLine;
	}
	public String getBizManager() {
		return bizManager;
	}
	public void setBizManager(String bizManager) {
		this.bizManager = bizManager;
	}
	public java.sql.Date getGoliveDate() {
		return goliveDate;
	}
	public void setGoliveDate(java.sql.Date goliveDate) {
		this.goliveDate = goliveDate;
	}
	public String getMajorCust() {
		return majorCust;
	}
	public void setMajorCust(String majorCust) {
		this.majorCust = majorCust;
	}
	public String getCustManager() {
		return custManager;
	}
	public void setCustManager(String custManager) {
		this.custManager = custManager;
	}
	public String getCustUsage() {
		return custUsage;
	}
	public void setCustUsage(String custUsage) {
		this.custUsage = custUsage;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public long getPrdTid() {
		return prdTid;
	}
	public void setPrdTid(long prdTid) {
		this.prdTid = prdTid;
	}
	public long getPrdRid() {
		return prdRid;
	}
	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
	}
	public String getParasCode() {
		return parasCode;
	}
	public void setParasCode(String parasCode) {
		this.parasCode = parasCode;
	}


}
