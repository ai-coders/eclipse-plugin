package net.aicoder.devp.model.dev;

import net.aicoder.devp.model.DevpBaseEo;

public class DevaModuleEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private int mduFlag; // ģ�ͱ�־, 0:��Ʒ;1:ģ��;2:����
	private String type; // ����
	private String stereotype; // ������
	private String scope; // ��Χ
	private String version; // �汾
	private String phase; // �׶�
	private String status; // ״̬
	private long prdRid; // ��Ʒ���
	private String prdCode; // ��Ʒ����
	private String prdName; // ��Ʒ����
	private long parentRid; // ��ģ����
	private int seq; // ˳���

	public int getMduFlag() {
		return mduFlag;
	}

	public void setMduFlag(int mduFlag) {
		this.mduFlag = mduFlag;
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

	public long getPrdRid() {
		return prdRid;
	}

	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
	}

	public String getPrdCode() {
		return prdCode;
	}

	public void setPrdCode(String prdCode) {
		this.prdCode = prdCode;
	}

	public String getPrdName() {
		return prdName;
	}

	public void setPrdName(String prdName) {
		this.prdName = prdName;
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
