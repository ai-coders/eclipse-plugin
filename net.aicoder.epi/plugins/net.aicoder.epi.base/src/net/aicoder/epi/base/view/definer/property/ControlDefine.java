package net.aicoder.epi.base.view.definer.property;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {})
public class ControlDefine {

	private String type;
	private String ioFlag;
	private String refObjects;
	private String extControl;

	public String getType() {
		return type;
	}

	@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}

	public String getIoFlag() {
		return ioFlag;
	}

	@XmlAttribute
	public void setIoFlag(String ioFlag) {
		this.ioFlag = ioFlag;
	}

	public String getRefObjects() {
		return refObjects;
	}

	@XmlAttribute
	public void setRefObjects(String refObjects) {
		this.refObjects = refObjects;
	}

	public String getExtControl() {
		return extControl;
	}

	@XmlAttribute
	public void setExtControl(String extControl) {
		this.extControl = extControl;
	}

}
