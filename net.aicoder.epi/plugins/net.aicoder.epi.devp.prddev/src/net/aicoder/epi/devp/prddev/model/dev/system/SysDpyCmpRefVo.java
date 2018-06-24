package net.aicoder.epi.devp.prddev.model.dev.system;

import net.aicoder.epi.base.model.BaseVo;

/**
 * 
 * @author WANGQINGPING
 *
 */
public class SysDpyCmpRefVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private long prdRid; //产品编号
	private long schemeRid; // 部署方案编号
	private long cmpRid; //组件编号
	private long refEtype; //关联元素类型
	private long refPrdRid; //关联产品编号
	private long refElmRid; //关联元素编号
	private long refInstRid; //关联元素实例编号
	private int seq; //顺序号
	private String type; // 类型
	private String subType; // 子类型
	private String stereotype; //构造型
	private String scope; //范围
	private String direction; //方向
	private String srcMulti; //来源对应数量
	private String srcRole; //来源角色
	private String srcRoleType; //来源角色类型
	private String destMulti; //目标对应数量
	private String destRole; //目标角色
	private String destRoleType; //目标角色类型
	private String attrRelation; //属性对应关系
	
	
	public long getPrdRid() {
		return prdRid;
	}
	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
	}
	public long getSchemeRid() {
		return schemeRid;
	}
	public void setSchemeRid(long schemeRid) {
		this.schemeRid = schemeRid;
	}
	public long getCmpRid() {
		return cmpRid;
	}
	public void setCmpRid(long cmpRid) {
		this.cmpRid = cmpRid;
	}
	public long getRefEtype() {
		return refEtype;
	}
	public void setRefEtype(long refEtype) {
		this.refEtype = refEtype;
	}
	public long getRefPrdRid() {
		return refPrdRid;
	}
	public void setRefPrdRid(long refPrdRid) {
		this.refPrdRid = refPrdRid;
	}
	public long getRefElmRid() {
		return refElmRid;
	}
	public void setRefElmRid(long refElmRid) {
		this.refElmRid = refElmRid;
	}
	public long getRefInstRid() {
		return refInstRid;
	}
	public void setRefInstRid(long refInstRid) {
		this.refInstRid = refInstRid;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
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
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getSrcMulti() {
		return srcMulti;
	}
	public void setSrcMulti(String srcMulti) {
		this.srcMulti = srcMulti;
	}
	public String getSrcRole() {
		return srcRole;
	}
	public void setSrcRole(String srcRole) {
		this.srcRole = srcRole;
	}
	public String getSrcRoleType() {
		return srcRoleType;
	}
	public void setSrcRoleType(String srcRoleType) {
		this.srcRoleType = srcRoleType;
	}
	public String getDestMulti() {
		return destMulti;
	}
	public void setDestMulti(String destMulti) {
		this.destMulti = destMulti;
	}
	public String getDestRole() {
		return destRole;
	}
	public void setDestRole(String destRole) {
		this.destRole = destRole;
	}
	public String getDestRoleType() {
		return destRoleType;
	}
	public void setDestRoleType(String destRoleType) {
		this.destRoleType = destRoleType;
	}
	public String getAttrRelation() {
		return attrRelation;
	}
	public void setAttrRelation(String attrRelation) {
		this.attrRelation = attrRelation;
	}

}
