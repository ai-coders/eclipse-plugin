package net.aicoder.devp.model;

public enum EtypeEnum {
	_NONE(""), 
	
	// ��Ʒ
	PRDLINE("PrdLine"), //��Ʒ��
	PRODUCT("Product"), //��Ʒ
	MODULE("Module"), //ģ��
	FUNCTION("Function"), //����
	
	//// ����
	USECASE("UseCase"), //����
	
	//// ģ��
	MODEL("Model"), //ģ��
	
	//// UI
	PAGE("Page"), //UI
	UIELEMENT("UiElement"), //UI Element
	
	
	// ϵͳ
	SYSTEM("System"), //
	COMPONENT("Component"), //
	
	// ����
	PROJECT("Project"), //
	SOURCECODE("SourceCode"), //source
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
	
	public String getEtype() {
		return etype;
	}
}
