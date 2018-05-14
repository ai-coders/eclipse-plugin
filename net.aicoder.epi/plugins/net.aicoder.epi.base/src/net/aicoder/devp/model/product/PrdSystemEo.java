package net.aicoder.devp.model.product;

import net.aicoder.devp.model.DevpBaseEo;

public class PrdSystemEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private long prdRid; // ��Ʒ���
	private long sysRid; // ϵͳ���
	private int seq; // ˳���

	private String type; // ����
	private String stereotype; // ������
	private String scope; // ��Χ
	private int relateFlag; // ������Ʒ���, 0���ڲ�ϵͳ��1���ⲿϵͳ
	private long relatePrdRid; // ������Ʒ���

	public long getPrdRid() {
		return prdRid;
	}

	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
	}

	public long getSysRid() {
		return sysRid;
	}

	public void setSysRid(long sysRid) {
		this.sysRid = sysRid;
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

	public int getRelateFlag() {
		return relateFlag;
	}

	public void setRelateFlag(int relateFlag) {
		this.relateFlag = relateFlag;
	}

	public long getRelatePrdRid() {
		return relatePrdRid;
	}

	public void setRelatePrdRid(long relatePrdRid) {
		this.relatePrdRid = relatePrdRid;
	}

}
