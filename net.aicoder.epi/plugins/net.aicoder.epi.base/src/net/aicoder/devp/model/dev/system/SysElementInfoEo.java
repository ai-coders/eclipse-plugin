package net.aicoder.devp.model.dev.system;

import net.aicoder.devp.model.DevpBaseEo;

public class SysElementInfoEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private long prdRid; // 产品编号
	private long elmRid; // 系统元素编号
	private int seq; // 顺序号
	private String infoValue1; // 信息值1
	private String infoValue2; // 信息值2
	private String infoValue3; // 信息值3
	private String infoValue4; // 信息值4
	private String infoValue5; // 信息值5

	public long getPrdRid() {
		return prdRid;
	}

	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
	}

	public long getElmRid() {
		return elmRid;
	}

	public void setElmRid(long elmRid) {
		this.elmRid = elmRid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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

}
