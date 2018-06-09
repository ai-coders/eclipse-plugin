package net.aicoder.epi.base.model;

import java.io.Serializable;
import java.util.Date;

public interface IBaseVo extends Serializable {
	public boolean putPropertyValue(String propertyName, Object value);
	
	public Object getPropertyOrigValue(String propertyName);
	
	public Object getPropertyValue(String propertyName);
	
	public String getPropertyShowValue(String propertyName);
	
	public StateFlagEnum getDataState();
	
	public void setDataState(StateFlagEnum dataState);
	
	public String getEtype();
	
	public long getRid();

	public void setRid(long rid);
	
	public long getTid();
	
	public void setTid(long tid);

	public String getCode();

	public void setCode(String code);

	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);

	public String getCreateUid();

	public void setCreateUid(String createUid);
	
	public String getCreateUcode();
	
	public void setCreateUcode(String createUcode);

	public String getCreateUname();

	public void setCreateUname(String createUname);

	public Date getCreateAt();

	public void setCreateAt(Date createAt);

	public String getModifyUid();

	public void setModifyUid(String modifyUid);
	
	public String getModifyUcode();
	
	public void setModifyUcode(String modifyUcode);

	public String getModifyUname();

	public void setModifyUname(String modifyUname);

	public Date getModifyAt();

	public void setModifyAt(Date modifyAt);
	
	public IBaseVo getPreItemData();
	
	public void setPreItemData(IBaseVo preItemData);
	
	public String getPropsId();
	
	public String getParasId();
}
