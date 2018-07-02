package net.aicoder.epi.devp.prddev.model.ops;

import net.aicoder.epi.base.model.BaseVo;

public class SysDpyCmpRefVo extends BaseVo{
	private static final long serialVersionUID = 1L;
	private long prdRid;
  	private long schemeRid;
  	private long cmpRid;
  	private long refEtype;
  	private long refPrdRid;
  	private long refElmRid;
  	private long refInstRid;
  	private long seq;
  	private String type;
  	private String subType;
  	private String stereotype;
  	private String scope;
  	private String direction;
  	private String srcMulti;
  	private String srcRole;
  	private String srcRoleType;
  	private String destMulti;
  	private String destRole;
  	private String destRoleType;
  	private String attrRelation;
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
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
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
