package net.aicoder.devp.model.product;

import net.aicoder.devp.model.DevpBaseEo;

public class PrdPersonEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private int nexusType; // ����Ԫ������
	private long nexusRid; // ����Ԫ�ر��
	private int seq; // ˳���
	private long uid; // �û����

	private String type; // �û�����
	private String role; // �û�����
	private String status; // ״̬
	private long orgRid; // ��֯���
	private String orgName; // ��֯����

	public int getNexusType() {
		return nexusType;
	}

	public void setNexusType(int nexusType) {
		this.nexusType = nexusType;
	}

	public long getNexusRid() {
		return nexusRid;
	}

	public void setNexusRid(long nexusRid) {
		this.nexusRid = nexusRid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getOrgRid() {
		return orgRid;
	}

	public void setOrgRid(long orgRid) {
		this.orgRid = orgRid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
