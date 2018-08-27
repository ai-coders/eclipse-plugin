package net.aicoder.epi.devp.prddev.model.ops;

import java.util.Date;
import java.util.List;

import net.aicoder.epi.base.model.TreeNodeVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.tcom.tools.util.BeanUtil;

/**
 * 产品发布导航-产品分组Vo
 * @author WANGQINGPING
 *
 */
public class ProductOpsVo extends TreeNodeVo{
	private static final long serialVersionUID = 1L;
	private String ETYPE; // 数据类型
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
	private PrdProductVo prdProductVo; //产品
	private List<PrdProductVo> productList; //产品组内产品集合


	public ProductOpsVo() {
		super();
	}

	public ProductOpsVo(PrdProductVo prdProductVo) {
		super();
		this.prdProductVo = prdProductVo;
		BeanUtil.copyBeanToBean(this, prdProductVo);
	}

	public PrdProductVo getPrdProductVo() {
		return prdProductVo;
	}

	public void setPrdProductVo(PrdProductVo prdProductVo) {
		this.prdProductVo = prdProductVo;
	}

	public String getEtype() {
		return ETYPE;
	}

	public void setEtype(String eTYPE) {
		this.ETYPE = eTYPE;
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
	
	public List<PrdProductVo> getProductList() {
		return productList;
	}

	public void setProductList(List<PrdProductVo> productList) {
		this.productList = productList;
	}
	
}
