package net.aicoder.devp.model;

public enum EtypeEnum {
	NONE(""), 
	
	// 产品
	PRDLINE("PrdLine"), //产品线
	PRODUCT("Product"), //产品
	MODULE("Module"), //功能模块
	FUNCTION("Function"), //功能
	
	// 系统
	SYSTEM("System"), // 系统、子系统
	SYSCMP("SysCmp"), // 组件
	SYSIDEFLD("SysIdeFld"), // 工程目录
	SYSIDEPRJ("SysIdePrj"), // 工程项目
	SYSDPYENV("SysDpyEnv"), // 部署环境
	
	//// 用例
	USECASE("UseCase"), //用例
	
	//// 模型
	MODEL("Model"), //模型
	
	//// UI
	PAGE("Page"), //UI
	UIELEMENT("UiElement"), //UI Element
	
	
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
