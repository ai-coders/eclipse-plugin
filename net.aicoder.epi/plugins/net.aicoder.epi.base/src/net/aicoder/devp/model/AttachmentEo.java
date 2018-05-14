package net.aicoder.devp.model;

public class AttachmentEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private String type; // ��������
	private String fileType; // �ļ�����
	private String accessType; // ���ʷ�ʽ
	private String domain; // ������
	private String address; // ���ʵ�ַ
	private String fileVersion; // �����汾
	private String nexusType; // ������¼����
	private long nexusRid; // ������¼���
	private int seq; // ˳���

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFileVersion() {
		return fileVersion;
	}

	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
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

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
}
