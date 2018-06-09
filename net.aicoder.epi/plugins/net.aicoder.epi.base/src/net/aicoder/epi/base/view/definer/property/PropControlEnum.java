package net.aicoder.epi.base.view.definer.property;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.tcom.tools.util.AiStringUtil;

public enum PropControlEnum {
	NONE(""),

	NORMAL("NORMAL"),
	PREFERRED("PREFERRED"),
	ADVANCED("ADVANCED"),
	ADVANCED_REALLY("ADVANCED_REALLY"),
	HIDDEN("HIDDEN"),
	;
	
	private final String propCtgy;

	private PropControlEnum(String propCtgy) {
		this.propCtgy = propCtgy;
	}

	public static PropControlEnum forStr(String propCtgy) {
		if(AiStringUtil.isEmpty(propCtgy)) {
			return PropControlEnum.NORMAL;
		}
		for (PropControlEnum type : values()) {
			if (type.propCtgy.equalsIgnoreCase(propCtgy)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid Etype: " + propCtgy);
	}

	public String propCtgy() {
		return propCtgy;
	}
}
