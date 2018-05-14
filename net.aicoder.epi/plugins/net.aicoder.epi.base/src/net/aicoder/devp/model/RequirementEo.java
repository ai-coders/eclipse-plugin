package net.aicoder.devp.model;

public class RequirementEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private String nexusType; // ������¼����
	private long nexusRid; // ������¼���
	private int seq; // ˳���
	private String type; // ��������
	private String content; // ����
	private int hasAttachment; // �Ƿ��и���

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHasAttachment() {
		return hasAttachment;
	}

	public void setHasAttachment(int hasAttachment) {
		this.hasAttachment = hasAttachment;
	}

}
