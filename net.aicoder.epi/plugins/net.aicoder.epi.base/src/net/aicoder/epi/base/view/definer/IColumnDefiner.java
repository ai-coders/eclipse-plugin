package net.aicoder.epi.base.view.definer;

public interface IColumnDefiner {
	public final static String EDITABLE = "Editable";
	public final static String HIDDEN = "Hidden";
	
	public final static String CE_COMBOBOX = "ComboBoxCellEditor";
	public final static String CE_TEXT = "TextCellEditor";
	public final static String CE_DATE = "DateCellEditor";

	String getColumnName();

	void setColumnName(String columnName);

	int getColumnLength();

	void setColumnLength(int columnLength);

	String getDataName();

	void setDataName(String dataName);

	String getDataType();

	void setDataType(String dataType);

	String getDataFormat();

	void setDataFormat(String dataFormat);

	String getEditFlag();

	void setEditFlag(String editFlag);

	String getHiddenFlag();

	void setHiddenFlag(String hiddenFlag);

	boolean isEditable();

	void setEditable(boolean editable);

	boolean ishHidden();

	void sethHidden(boolean hHidden);
	
	public int getColumnIndex();
	
	public void setColumnIndex(int columnIndex);

}