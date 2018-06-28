package net.aicoder.epi.base.model;

import java.io.Serializable;

public class BooleanItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean booleanValue;
	private Object dataValue;
	private String showValue;
	
	private String description;
	
	public BooleanItem() {
		super();
	}
	
	public BooleanItem(Object[] itemArgs) {
		super();
		
		if (itemArgs == null) {
			return;
		}
		
		int seq = 0;
		if (itemArgs.length > seq) {
			this.setBooleanValue((boolean) itemArgs[seq++]);
		}
		if (itemArgs.length > seq) {
			this.setDataValue(itemArgs[seq++]);
		}
		if (itemArgs.length > seq) {
			this.setShowValue((String) itemArgs[seq++]);
		}
		if (itemArgs.length > seq) {
			this.setDescription((String) itemArgs[seq++]);
		}
	}

	public boolean isBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public Object getDataValue() {
		return dataValue;
	}

	public void setDataValue(Object dataValue) {
		this.dataValue = dataValue;
	}

	public String getShowValue() {
		return showValue;
	}

	public void setShowValue(String showValue) {
		this.showValue = showValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
