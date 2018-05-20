package net.aicoder.devp.model.system;

import net.aicoder.devp.model.DevpBaseEo;

public class SysCmpRelationEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private String type; // ��Ӧ��ϵ����
	private String stereotype; // ������
	private long ssysRid; // ��Դϵͳ���
	private long scmpRid; // ��Դ������
	private long dsysRid; // Ŀ��ϵͳ���
	private long dcmpRid; // Ŀ��������
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

	public long getSsysRid() {
		return ssysRid;
	}

	public void setSsysRid(long ssysRid) {
		this.ssysRid = ssysRid;
	}

	public long getScmpRid() {
		return scmpRid;
	}

	public void setScmpRid(long scmpRid) {
		this.scmpRid = scmpRid;
	}

	public long getDsysRid() {
		return dsysRid;
	}

	public void setDsysRid(long dsysRid) {
		this.dsysRid = dsysRid;
	}

	public long getDcmpRid() {
		return dcmpRid;
	}

	public void setDcmpRid(long dcmpRid) {
		this.dcmpRid = dcmpRid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

}
