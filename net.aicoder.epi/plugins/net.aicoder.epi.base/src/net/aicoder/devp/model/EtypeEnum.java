package net.aicoder.devp.model;

public enum EtypeEnum {
	NONE(""), 
	
	// 产品
	PRD_LINE("PRD_LINE"), //产品线
	PRODUCT("PRODUCT"), //产品
	MODULE("MODULE"), //模块
	FUNCTION("FUNCTION"), //功能
	PRD_GROUP("PRD_GROUP"), //产品组
	
	//// 模型
	FUN_MODEL("FUN_MODEL"), //功能模型
	CMP_MODEL("CMP_MODEL"), //组件模型
	IDE_MODEL("CMP_MODEL"), //开发模型
	DPY_MODEL("DPY_MODEL"), //部署模型
	
	// 系统
	SYSTEM("SYSTEM"), // 系统
	SUB_SYS("SUB_SYS"), // 子系统
	SYS_CMP("SYS_CMP"), // 组件
	SYS_IDE_FLD("SYS_IDE_FLD"), // 工程目录
	SYS_IDE_PRJ("SYS_IDE_PRJ"), // 工程项目
	SYS_DPY_ENV("SYS_DPY_ENV"), // 部署环境
	SYS_RESOURCES("SYS_RESOURCES"), // 系统资源
	
	// 系统设计图
	SYS_CMP_DGM("SYS_CMP_DGM"), // 组件图
	SYS_DPY_DGM("SYS_DPY_DGM"), // 部署图
	
	//// 用例
	USE_CASE("USE_CASE"), //用例
	
	//// UI
	PAGE("Page"), //UI
	UI_ELEMENT("UiElement"), //UI Element
	
	
	;
	private final String etype;

	private EtypeEnum(String etype) {
		this.etype = etype;
	}
	
    public static EtypeEnum forStr(String etype) {
        for (EtypeEnum type : values()) {
            if (type.etype == etype) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Etype: " + etype);
    }
	
	public String etype() {
		return etype;
	}
}
