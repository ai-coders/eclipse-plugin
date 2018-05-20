package net.aicoder.epi.devp.model.alike;

import net.aicoder.epi.base.model.BaseVo;

public class IssueVo extends BaseVo {
	private static final long serialVersionUID = 1L;

	private String type; //问题类型
	private String issue; //问题说明
	private String reply; //问题回复
	private String status; //处理状态
	private int hasAttachment; //是否有附件
	private String nexusType; //关联记录类型
	private long nexusRid; //关联记录编号
	private String nexusVersion; //关联记录版本
	private String nexusPhase; //关联记录阶段
	private int seq; //顺序号

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
