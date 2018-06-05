package net.aicoder.devp.model.product;

import java.util.Date;

import net.aicoder.devp.model.BaseDevpEo;
import net.aicoder.devp.model.EtypeEnum;

public class PrdProductEo extends BaseDevpEo {
	private static final long serialVersionUID = 1L;

	private String type; // 产品类型
	private String stereotype; // 构造型
	private String scope; // 范围
	private String prdDept; // 所属部门
	private String prdOwner; // 产品负责人
	private String devManager; // 开发负责人
	private String opsManager; // 维护负责人
	private String bizLine; // 业务线
	private String bizManager; // 业务代表
	private Date golive; // 启用时间
	private String majorCust; // 主要客户
	private String custManager; // 客户代表
	private String custUsage; // 客户使用情况
	private String acquisitionMode; // 获取方式
	private String acquisitionDesc; // 获取方式说明
	private String version; // 版本
	private String phase; // 阶段
	private String status; // 状态
	private String notes; // 备注

	static {
		etype = EtypeEnum.PRODUCT;
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

	public String getPrdDept() {
		return prdDept;
	}

	public void setPrdDept(String prdDept) {
		this.prdDept = prdDept;
	}

	public String getPrdOwner() {
		return prdOwner;
	}

	public void setPrdOwner(String prdOwner) {
		this.prdOwner = prdOwner;
	}

	public String getDevManager() {
		return devManager;
	}

	public void setDevManager(String devManager) {
		this.devManager = devManager;
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

	public Date getGolive() {
		return golive;
	}

	public void setGolive(Date golive) {
		this.golive = golive;
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
}
