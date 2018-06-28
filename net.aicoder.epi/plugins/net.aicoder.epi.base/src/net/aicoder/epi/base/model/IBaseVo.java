package net.aicoder.epi.base.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Date;

public interface IBaseVo extends Serializable {

	//boolean isInitFlag();
	
	//void setInitFlag(boolean initFlag);
	
	StateFlagEnum getDataState(String propertyCode);
	
	StateFlagEnum getDataState();

	void setDataState(StateFlagEnum dataState);

	String getPropsId();

	//// 属性定义
	long getRid();

	void setRid(long rid);

	long getTid();

	void setTid(long tid);

	String getEtype();

	void setEtype(String etype);

	String getCode();

	void setCode(String code);

	String getName();

	void setName(String name);

	String getAlias();

	void setAlias(String alias);

	String getDescription();

	void setDescription(String description);

	int getRecordState();

	void setRecordState(int recordState);

	String getParasId();

	void setParasId(String parasId);

	String getCreateUid();

	void setCreateUid(String createUid);

	String getCreateUcode();

	void setCreateUcode(String createUcode);

	String getCreateUname();

	void setCreateUname(String createUname);

	Date getCreateAt();

	void setCreateAt(Date createAt);

	String getModifyUid();

	void setModifyUid(String modifyUid);

	String getModifyUcode();

	void setModifyUcode(String modifyUcode);

	String getModifyUname();

	void setModifyUname(String modifyUname);

	Date getModifyAt();

	void setModifyAt(Date modifyAt);

	//Map<String, Object> getOriginalPropertyValue();

	//void setOriginalPropertyValue(Map<String, Object> originalPropertyValue);

	//// 前置的元素引用，控制元素排列顺序时使用
	IBaseVo getPreItemData();

	void setPreItemData(IBaseVo preItemData);

	//// property
	/**
	 * 单纯向属性设，而不需要跟踪属性是否变化的情景下使用
	 * @param propertyCode
	 * @param value
	 */
	void setPropertyValue(String propertyCode, Object value);
	
	/**
	 * 需要跟踪属性是否变化的情景下使用，设置属性值时会同步记录原始的数值，并且触发属性变易事件
	 * @param propertyCode
	 * @param value
	 * @return 属性是否被修改，即新值是否与属性的原始值是否一致
	 */
	boolean putPropertyValue(String propertyCode, Object value);

	Object getPropertyOrigValue(String propertyCode);

	Object getPropertyValue(String propertyCode);

	String getPropertyShowValue(String propertyCode);
	
	//// loaded state
	public boolean isLoadedElement(String elementName);
	
	public boolean isLoadedElement(String elementName, int pageNo);
	
	///////////////////////
	public void addPropertyChangeListener(PropertyChangeListener listener);
	
	public void firePropertyChange(PropertyChangeEvent event);
	
	public void removePropertyChangeListener(PropertyChangeListener listener);
	
	public void removeAllPropertyChangeListener();
}
