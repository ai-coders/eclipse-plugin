package net.aicoder.epi.base.model.property;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.tcom.tools.util.AiStringUtil;

public enum PropControlEnum {
	NONE(""),

	Text("Text"),
	Date("Date"),
	DateTime("DateTime"),
	ComboBox("ComboBox"),
	CheckBox("CheckBox"),
	;
	
	private final String controlType;

	private PropControlEnum(String controlType) {
		this.controlType = controlType;
	}

	public static PropControlEnum forStr(String controlType) {
		if(AiStringUtil.isEmpty(controlType)) {
			return PropControlEnum.Text;
		}
		for (PropControlEnum type : values()) {
			if (type.controlType.equalsIgnoreCase(controlType)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid Etype: " + controlType);
	}

	public String controlType() {
		return controlType;
	}
}
