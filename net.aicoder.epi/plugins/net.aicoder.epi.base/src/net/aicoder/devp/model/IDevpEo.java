package net.aicoder.devp.model;

import java.io.Serializable;
import java.util.Date;

public interface IDevpEo extends Serializable {
	public EtypeEnum getEtype(); //元素类型，产品线、产品、组件....

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
	
	public int getRecordState();
	
	public void setRecordState(int recordState);

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
}
