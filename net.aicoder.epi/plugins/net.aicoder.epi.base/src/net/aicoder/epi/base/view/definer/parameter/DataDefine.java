package net.aicoder.epi.base.view.definer.parameter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {})
public class DataDefine {
	// <Data type="" format="" length="" precision="" scale="" >dafault_value</Data>

	private String type;
	private String format;
	private String length;
	private String precision;
	private String scale;
	private String dafaultValue;

	public String getType() {
		return type;
	}

	@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	@XmlAttribute
	public void setFormat(String format) {
		this.format = format;
	}

	public String getLength() {
		return length;
	}

	@XmlAttribute
	public void setLength(String length) {
		this.length = length;
	}

	public String getPrecision() {
		return precision;
	}

	@XmlAttribute
	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getScale() {
		return scale;
	}

	@XmlAttribute
	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getDafaultValue() {
		return dafaultValue;
	}

	@XmlElement(name = "Data")
	public void setDafaultValue(String dafaultValue) {
		this.dafaultValue = dafaultValue;
	}

}
