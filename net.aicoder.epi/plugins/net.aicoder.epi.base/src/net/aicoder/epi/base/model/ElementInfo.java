package net.aicoder.epi.base.model;

import net.aicoder.tcom.tools.util.ConvDataType;

public class ElementInfo extends AbstractBaseVo {
	private static final long serialVersionUID = 1L;

	private long objRid; // 元素编号
	// private int seq; // 顺序号
	private String dataType; // 数据类型
	private String infoValue; // 扩展信息值
	private String notes; // 备注

	public long getObjRid() {
		return objRid;
	}

	public void setObjRid(long objRid) {
		this.objRid = objRid;
	}

	public DataTypeEnum getDataTypeEnum() {
		return DataTypeEnum.forStr(dataType);
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Object getInfoValue() {
		Object value = null;
		String strValue = getInfoStrValue();
		try {
			switch (getDataTypeEnum()) {
			case Boolean:
				value = ConvDataType.toBoolean(strValue);
				break;
			case Int:
			case Integer:
				value = ConvDataType.toInteger(strValue);
				break;
			case Long:
				value = ConvDataType.toLong(strValue);
				break;
			case Float:
				value = ConvDataType.toFloat(strValue);
				break;
			case Double:
				value = ConvDataType.toDouble(strValue);
				break;
			case BigDecimal:
				value = ConvDataType.toBigDecimal(strValue);
				break;
			case Date:
				value = ConvDataType.toDate(strValue);
				break;
			case Timestamp:
				value = ConvDataType.toTimestamp(strValue);
				break;
			default:
				value = strValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public String getInfoStrValue() {
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
