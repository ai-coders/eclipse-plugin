package net.aicoder.epi.base.view.definer.property;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.tcom.tools.util.AiStringUtil;

public enum PropCatgEnum {
	NONE(""),

	/**
	 * "Normal" category, used for properties that should be displayed without any
	 * effect.
	 */
	NORMAL("NORMAL"),
	/**
	 * "Preferred" category, for properties that are most useful for component.
	 */
	PREFERRED("PREFERRED"),
	/**
	 * "Advanced" category, for properties that are rarely used, visible if
	 * modified, even if not enabled.
	 */
	ADVANCED("ADVANCED"),
	/**
	 * "Advanced" category, for properties that are rarely used, visible only if
	 * enabled.
	 */
	ADVANCED_REALLY("ADVANCED_REALLY"),
	/**
	 * "Hidden" category, for properties that should not be displayed.
	 */
	HIDDEN("HIDDEN"),
	;
	
	private final String propCtgy;

	private PropCatgEnum(String propCtgy) {
		this.propCtgy = propCtgy;
	}

	public static PropCatgEnum forStr(String propCtgy) {
		if(AiStringUtil.isEmpty(propCtgy)) {
			return PropCatgEnum.NORMAL;
		}
		for (PropCatgEnum type : values()) {
			if (type.propCtgy.equalsIgnoreCase(propCtgy)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid Etype: " + propCtgy);
	}

	public String propCtgy() {
		return propCtgy;
	}
	
	public int priority() {
		int priority = 0;
		switch(this) {
		case NORMAL:
			priority = 0;
			break;
		case PREFERRED:
			priority = -1;
			break;
		case ADVANCED:
			priority = 1;
			break;
		case ADVANCED_REALLY:
			priority = 2;
			break;
		case HIDDEN:
			priority = 3;
			break;
		default:
			priority = 0;
			break;
		}
		return priority;
	}
}
