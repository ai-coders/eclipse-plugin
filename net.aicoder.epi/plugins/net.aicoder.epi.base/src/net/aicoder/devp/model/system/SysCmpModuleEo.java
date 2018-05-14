package net.aicoder.devp.model.system;

import net.aicoder.devp.model.DevpBaseEo;

public class SysCmpModuleEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private String type; // ��Ӧ��ϵ����
	private String stereotype; // ������
	private String scope; // ��Χ
	private String version; // �汾
	private String phase; // �׶�
	private String status; // ״̬
	private long sysRid; // ϵͳ���
	private long prdRid; // ��Ʒ���
	private long mduRid; // ģ����
	private int seq; // ˳���

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

	public long getSysRid() {
		return sysRid;
	}

	public void setSysRid(long sysRid) {
		this.sysRid = sysRid;
	}

	public long getPrdRid() {
		return prdRid;
	}

	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
	}

	public long getMduRid() {
		return mduRid;
	}

	public void setMduRid(long mduRid) {
		this.mduRid = mduRid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

}
