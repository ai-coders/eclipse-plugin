package net.aicoder.epi.base.model.property;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {})
public class PitemDefine {
	//private static final Log log = LogFactory.getLog(PropertiesDefine.class);
	
	private String code;
	private String name;
	private String alias;
	private String type;
	private String image;
	private String imageDis;
	private String category;
	
	private String description;
	private String checkRule;
	
	private DataDefine data;
	private ControlDefine control;
	//private CheckRuleDefine checkRule;
	
	private List<PitemDefine> subItemsList;

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

	public String getCategory() {
		return category;
	}

	@XmlAttribute
	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement(name = "Description")
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement(name = "CheckRule")
	public void setCheckRule(String checkRule) {
		this.checkRule = checkRule;
	}

	public DataDefine getData() {
		return data;
	}

	@XmlElement(name = "Data")
	public void setData(DataDefine data) {
		this.data = data;
	}

	public ControlDefine getControl() {
		return control;
	}

	@XmlElement(name = "Control")
	public void setControl(ControlDefine control) {
		this.control = control;
	}

	public String getCheckRule() {
		return checkRule;
	}

	public List<PitemDefine> getSubItemsList() {
		return subItemsList;
	}

	@XmlElement(name = "SubItems")
	public void setSubItemsList(List<PitemDefine> subItemsList) {
		this.subItemsList = subItemsList;
	}
}
