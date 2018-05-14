package net.aicoder.epi.base.view.definer;

public class ColumnDefiner implements IColumnDefiner {
	private String columnName = ""; // 列名
	private String dataName = ""; // 数据属性名称
	private int columnLength = 0; // 列显示的宽度，0: 缺省长度, -n: 固定的长度（不会依据Layout来调整)， n: 长度可依据Layout来调整  
	private String dataType = ""; // 数据类型
	private String dataFormat = ""; // 数据格式
	private String hiddenFlag = ""; // 是否隐藏的标志：Hidden，否则该栏位为显示
	private String editFlag = ""; // 是否可编辑的标志：Editable，否则不可编辑

	private boolean hidden = false;
	private boolean editable = false;
	
	private int columnIndex = 0;
	
	public ColumnDefiner() {
		super();
	}
	
	public ColumnDefiner(Object[] columnDefine) {
		super();
		if (columnDefine == null) {
			return;
		}
		
		int seq = 0;
		if (columnDefine.length > seq) {
			this.setColumnName((String) columnDefine[seq++]);
		}
		if (columnDefine.length > seq) {
			this.setDataName((String) columnDefine[seq++]);
		}
		if (columnDefine.length > seq) {
			this.setColumnLength((int) columnDefine[seq++]);
		}
		if (columnDefine.length > seq) {
			this.setDataType((String) columnDefine[seq++]);
		}
		if (columnDefine.length > seq) {
			this.setDataFormat((String) columnDefine[seq++]);
		}
		if (columnDefine.length > seq) {
			this.setHiddenFlag((String) columnDefine[seq++]);
		}		
		if (columnDefine.length > seq) {
			this.setEditFlag((String) columnDefine[seq++]);
		}		
	}

	@Override
	public String getColumnName() {
		return columnName;
	}

	@Override
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Override
	public int getColumnLength() {
		return columnLength;
	}

	@Override
	public void setColumnLength(int columnLength) {
		this.columnLength = columnLength;
	}

	@Override
	public String getDataName() {
		return dataName;
	}

	@Override
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	@Override
	public String getDataType() {
		return dataType;
	}

	@Override
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Override
	public String getDataFormat() {
		return dataFormat;
	}

	@Override
	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	@Override
	public String getEditFlag() {
		return editFlag;
	}

	@Override
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
		if(EDITABLE.equalsIgnoreCase(editFlag)) {
			editable = true;
		}
	}

	@Override
	public String getHiddenFlag() {
		return hiddenFlag;
	}

	@Override
	public void setHiddenFlag(String hiddenFlag) {
		this.hiddenFlag = hiddenFlag;
		if(HIDDEN.equalsIgnoreCase(hiddenFlag)) {
			hidden = true;
		}
	}

	@Override
	public boolean isEditable() {
		return editable;
	}

	@Override
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public boolean ishHidden() {
		return hidden;
	}

	@Override
	public void sethHidden(boolean hHidden) {
		this.hidden = hHidden;
	}

	@Override
	public int getColumnIndex() {
		return columnIndex;
	}

	@Override
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
}
