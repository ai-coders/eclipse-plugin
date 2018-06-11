package net.aicoder.epi.base.view.definer.property;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.tcom.tools.util.AiStringUtil;

//CanbeNull,NotNull,Display,Hidden,Others
public enum PropIoFlagEnum {
	NONE(""),

	CanbeNull("CanbeNull"), // 可填：可以为空的输入项
	NotNull("NotNull"), // 必填：必须输入项
	Display("Display"), // 显示：仅显示内容
	Hidden("Hidden"), // 隐藏：界面上不显示的隐藏数据
	Others("Others"), // 其它：依据不同条件再确定控制属性
	;
	
	private final String ioFlag;

	private PropIoFlagEnum(String ioFlag) {
		this.ioFlag = ioFlag;
	}

	public static PropIoFlagEnum forStr(String ioFlag) {
		if(AiStringUtil.isEmpty(ioFlag)) {
			return PropIoFlagEnum.CanbeNull;
		}
		for (PropIoFlagEnum type : values()) {
			if (type.ioFlag.equalsIgnoreCase(ioFlag)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid Etype: " + ioFlag);
	}

	public String ioFlag() {
		return ioFlag;
	}
}
