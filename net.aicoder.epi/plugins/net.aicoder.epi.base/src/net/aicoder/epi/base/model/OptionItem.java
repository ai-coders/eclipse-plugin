package net.aicoder.epi.base.model;

import java.io.Serializable;

public class OptionItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long rid;

	/**
	 * 租户ID
	 * 
	 */
	private Long tid;

	/**
	 * 配置类型
	 * 
	 */
	private String configType;

	/**
	 * 展现名称
	 * 
	 */
	private String displayName;

	/**
	 * 参数代码
	 * 
	 */
	private String code;

	/**
	 * 参数值
	 * 
	 */
	private String value;

	/**
	 * 参数说明
	 * 
	 */
	private String description;

	/**
	 * 展现顺序
	 * 
	 */
	private Integer vIndex;

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getvIndex() {
		return vIndex;
	}

	public void setvIndex(Integer vIndex) {
		this.vIndex = vIndex;
	}

}
