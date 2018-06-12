package net.aicoder.epi.base.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public interface IBaseVo extends Serializable {

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

	Map<String, Object> getOriginalPropertyValue();

	void setOriginalPropertyValue(Map<String, Object> originalPropertyValue);

	//// 前置的元素引用，控制元素排列顺序时使用
	IBaseVo getPreItemData();

	void setPreItemData(IBaseVo preItemData);

	//// property
	boolean putPropertyValue(String propertyName, Object value);

	Object getPropertyOrigValue(String propertyName);

	Object getPropertyValue(String propertyName);

	String getPropertyShowValue(String propertyName);
	
	//// loaded state
	public boolean isLoadedElement(String elementName);
	
	public boolean isLoadedElement(String elementName, int pageNo);
}
