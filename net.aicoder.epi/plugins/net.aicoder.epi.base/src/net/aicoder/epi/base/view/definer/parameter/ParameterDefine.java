package net.aicoder.epi.base.view.definer.parameter;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@XmlAccessorType(XmlAccessType.PROPERTY)  
@XmlRootElement(name = "Parameter")  
@XmlType(propOrder = {})  
public class ParameterDefine {
	private static final Log log = LogFactory.getLog(ParameterDefine.class);
	
	private String code;
	private String name;
	private String alias;
	private String type;
	private String image;
	private String imageDis;
	private String tooltip;
	
	//private List<ActionDefine> actionDefineList;
	//private List<ToolbarDefine> toolbarDefineList;
	
	private List<PitemDefine> pitemDefineList;
	
	public ParameterDefine() {
		
	}

	//// getter/setter
	public String getCode() {
		return code;
	}

	@XmlAttribute
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	@XmlAttribute
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getType() {
		return type;
	}

	@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	@XmlAttribute
	public void setImage(String image) {
		this.image = image;
	}

	public String getImageDis() {
		return imageDis;
	}

	@XmlAttribute
	public void setImageDis(String imageDis) {
		this.imageDis = imageDis;
	}

	public String getTooltip() {
		return tooltip;
	}

	@XmlAttribute
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public List<PitemDefine> getPitemDefineList() {
		return pitemDefineList;
	}

	@XmlElement(name = "Pitem")
	public void setPitemDefineList(List<PitemDefine> pitemDefineList) {
		this.pitemDefineList = pitemDefineList;
	}
}
