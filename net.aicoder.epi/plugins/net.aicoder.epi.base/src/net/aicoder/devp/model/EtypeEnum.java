package net.aicoder.devp.model;

public enum EtypeEnum {
	_NONE(0), 
	
	// ��Ʒ
	PRDLINE(100100), //��Ʒ��
	PRODUCT(100200), //��Ʒ
	MODULE(100300), //ģ��
	FUNCTION(100400), //����
	
	//// ����
	USECASE(110100), //����
	
	//// ģ��
	MODEL(120100), //ģ��
	
	//// UI
	PAGE(130100), //UI
	UIELEMENT(130200), //UI Element
	
	
	// ϵͳ
	SYSTEM(200100), //
	COMPONENT(200200), //
	
	// ����
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
