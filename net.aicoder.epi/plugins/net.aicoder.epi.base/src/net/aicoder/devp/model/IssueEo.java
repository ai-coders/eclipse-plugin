package net.aicoder.devp.model;

public class IssueEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private String type; // ��������
	private String issue; // ����˵��
	private String reply; // ����ظ�
	private String status; // ����״̬
	private int hasAttachment; // �Ƿ��и���
	private String nexusType; // ������¼����
	private long nexusRid; // ������¼���
	private String nexusVersion; // ������¼�汾
	private String nexusPhase; // ������¼�׶�
	private int seq; // ˳���

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getHasAttachment() {
		return hasAttachment;
	}

	public void setHasAttachment(int hasAttachment) {
		this.hasAttachment = hasAttachment;
	}

	public String getNexusType() {
		return nexusType;
	}

	public void setNexusType(String nexusType) {
		this.nexusType = nexusType;
	}

	public long getNexusRid() {
		return nexusRid;
	}

	public void setNexusRid(long nexusRid) {
		this.nexusRid = nexusRid;
	}

	public String getNexusVersion() {
		return nexusVersion;
	}

	public void setNexusVersion(String nexusVersion) {
		this.nexusVersion = nexusVersion;
	}

	public String getNexusPhase() {
		return nexusPhase;
	}

	public void setNexusPhase(String nexusPhase) {
		this.nexusPhase = nexusPhase;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

}
