package net.aicoder.epi.devp.prddev.model.dev.system;


import java.util.Date;

import net.aicoder.epi.base.model.TreeNodeVo;

/**
 * 系统组件实体
 * @author WANGQINGPING
 *
 */
public class SysCmpVo extends TreeNodeVo{
	private static final long serialVersionUID = 1L;
	
	private String type; // 类型
	private String subType; // 子类型
	private String stereotype; // 构造型
	private String scope; // 范围
	private String version; // 版本
	private String phase; // 阶段
	private String status; // 状态
	private String notes; // 备注
	private int installable; //可安装组件
	private int sharedComponent; //共享组件
	private int sharedService; //共享服务
	private long prdRid; // 产品编号
	private long parentRid; // 父包编号
	private int seq; // 顺序号
	
	
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
	public int getInstallable() {
		return installable;
	}
	public void setInstallable(int installable) {
		this.installable = installable;
	}
	public int getSharedComponent() {
		return sharedComponent;
	}
	public void setSharedComponent(int sharedComponent) {
		this.sharedComponent = sharedComponent;
	}
	public int getSharedService() {
		return sharedService;
	}
	public void setSharedService(int sharedService) {
		this.sharedService = sharedService;
	}
	public long getPrdRid() {
		return prdRid;
	}
	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
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
	
}
