package net.aicoder.devp.model;

public enum EtypeEnum {
	_NONE(0), 
	
	// 产品
	PRDLINE(100100), //产品线
	PRODUCT(100200), //产品
	MODULE(100300), //模块
	FUNCTION(100400), //功能
	
	//// 用例
	USECASE(110100), //用例
	
	//// 模型
	MODEL(120100), //模型
	
	//// UI
	PAGE(130100), //UI
	UIELEMENT(130200), //UI Element
	
	
	// 系统
	SYSTEM(200100), //
	COMPONENT(200200), //
	
	// 工程
	PROJECT(300100), //
	SOURCECODE(300200), //source
	;
	private final int etype;

	private EtypeEnum(int etype) {
		this.etype = etype;
	}
	
    public static EtypeEnum forInt(int etype) {
        for (EtypeEnum type : values()) {
            if (type.etype == etype) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Etype: " + etype);
    }
	
	public int getEtype() {
		return etype;
	}
}
