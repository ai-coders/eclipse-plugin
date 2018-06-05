package net.aicoder.devp.model.alike;

import net.aicoder.devp.model.BaseDevpEo;

public class RequirementEo extends BaseDevpEo {
	private static final long serialVersionUID = 1L;

	private String nexusType; //关联记录类型
	private long nexusRid; //关联记录编号
	private int seq; //顺序号
	private String type; //需求类型
	private String content; //内容
	private int hasAttachment; //是否有附件

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
