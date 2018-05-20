package net.aicoder.epi.devp.model.alike;

import net.aicoder.epi.base.model.BaseVo;

public class AttachmentVo extends BaseVo {
	private static final long serialVersionUID = 1L;

	private String type; //附件类型
	private String fileType; //文件类型
	private String accessType; //访问方式
	private String domain; //访问域
	private String address; //访问地址
	private String fileVersion; //附件版本
	private String nexusType; //关联记录类型
	private long nexusRid; //关联记录编号
	private int seq; //顺序号

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
