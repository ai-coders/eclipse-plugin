package net.aicoder.devp.model.dev.system;

import net.aicoder.devp.model.DevpBaseEo;

public class SysConnectorInfoEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private long prdRid; // 产品编号
	private long contRid; // 关联连接编号
	private long sprdRid; // 来源产品编号
	private long selmRid; // 来源系统元素编号
	private long dprdRid; // 目标产品编号
	private long delmRid; // 目标系统元素编号
	private long sinstRid; // 来源系统元素实例编号
	private long dinstRid; // 目标系统元素实例编号
	private int seq; // 顺序号
	private String type; // 设值方式
	private String infoValue1; // 信息值1
	private String infoValue2; // 信息值2
	private String infoValue3; // 信息值3
	private String infoValue4; // 信息值4
	private String infoValue5; // 信息值5
	private String notes; // 备注

	public long getPrdRid() {
		return prdRid;
	}

	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
	}

	public long getContRid() {
		return contRid;
	}

	public void setContRid(long contRid) {
		this.contRid = contRid;
	}

	public long getSprdRid() {
		return sprdRid;
	}

	public void setSprdRid(long sprdRid) {
		this.sprdRid = sprdRid;
	}

	public long getSelmRid() {
		return selmRid;
	}

	public void setSelmRid(long selmRid) {
		this.selmRid = selmRid;
	}

	public long getDprdRid() {
		return dprdRid;
	}

	public void setDprdRid(long dprdRid) {
		this.dprdRid = dprdRid;
	}

	public long getDelmRid() {
		return delmRid;
	}

	public void setDelmRid(long delmRid) {
		this.delmRid = delmRid;
	}

	public long getSinstRid() {
		return sinstRid;
	}

	public void setSinstRid(long sinstRid) {
		this.sinstRid = sinstRid;
	}

	public long getDinstRid() {
		return dinstRid;
	}

	public void setDinstRid(long dinstRid) {
		this.dinstRid = dinstRid;
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

	public String getInfoValue1() {
		return infoValue1;
	}

	public void setInfoValue1(String infoValue1) {
		this.infoValue1 = infoValue1;
	}

	public String getInfoValue2() {
		return infoValue2;
	}

	public void setInfoValue2(String infoValue2) {
		this.infoValue2 = infoValue2;
	}

	public String getInfoValue3() {
		return infoValue3;
	}

	public void setInfoValue3(String infoValue3) {
		this.infoValue3 = infoValue3;
	}

	public String getInfoValue4() {
		return infoValue4;
	}

	public void setInfoValue4(String infoValue4) {
		this.infoValue4 = infoValue4;
	}

	public String getInfoValue5() {
		return infoValue5;
	}

	public void setInfoValue5(String infoValue5) {
		this.infoValue5 = infoValue5;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
