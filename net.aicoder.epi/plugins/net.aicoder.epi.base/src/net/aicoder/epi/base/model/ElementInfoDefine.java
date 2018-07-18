package net.aicoder.epi.base.model;

public class ElementInfoDefine extends AbstractBaseVo {
	private static final long serialVersionUID = 1L;

	//private long objRid; // 元素编号
	//private int seq; // 顺序号
	private String dataType; //数据类型
	private String infoValue; // 扩展信息值
	private String notes; // 备注

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
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

	@Override
	public void putLoadElementState(LoadElementState loadElementState) {
		// TODO Auto-generated method stub
		
	}

}
