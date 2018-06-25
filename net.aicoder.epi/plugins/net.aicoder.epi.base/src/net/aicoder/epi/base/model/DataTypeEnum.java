package net.aicoder.epi.base.model;

public enum DataTypeEnum {
	NONE(""), 
	
	Boolean("Boolean"),
	String("String"),
	Int("int"),
	Integer("Integer"),
	Long("Long"),
	Float("float"),
	Double("Double"),
	BigDecimal("BigDecimal"),
	Date("Date"),
	//Time("Time"),
	Timestamp("Timestamp"),
	Blob("Blob"),
	Clob("Clob"),
	
	;
	
	private final String dtype;

	private DataTypeEnum(String dtype) {
		this.dtype = dtype;
	}
	
    public static DataTypeEnum forStr(String dtype) {
        for (DataTypeEnum type : values()) {
            if (type.dtype == dtype) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Etype: " + dtype);
    }
	
	public String dtype() {
		return dtype;
	}
}
