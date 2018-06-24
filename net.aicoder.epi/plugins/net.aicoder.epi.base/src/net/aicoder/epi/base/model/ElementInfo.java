package net.aicoder.epi.base.model;

public class ElementInfo extends AbstractBaseVo {
	private static final long serialVersionUID = 1L;

	private long objRid; // 元素编号
	//private int seq; // 顺序号
	private String infoValue; // 扩展信息值
	private String notes; // 备注

	public long getObjRid() {
		return objRid;
	}

	public void setObjRid(long objRid) {
		this.objRid = objRid;
	}

	public String getInfoValue() {
		return infoValue;
	}

	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
