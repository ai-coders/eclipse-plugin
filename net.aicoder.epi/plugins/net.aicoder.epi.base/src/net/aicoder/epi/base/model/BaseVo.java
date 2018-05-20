package net.aicoder.epi.base.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.tcom.tools.util.BeanUtil;

public abstract class BaseVo implements IBaseVo {
	private static final long serialVersionUID = 1L;
	private static final String ETYPE = EtypeEnum.NONE.getEtype();

	private StateFlagEnum dataState;

	private long rid;
	private long tid;
	private String code;
	private String name;
	private String alias;
	private String description;

	private String createUid;
	private String createUcode;
	private String createUname;
	private Date createAt;
	private String modifyUid;
	private String modifyUcode;
	private String modifyUname;
	private Date modifyAt;
	
	private IBaseVo preItemData;
	private Map<String, Object> originalPropertyValue = new HashMap<String, Object>(0);

	public BaseVo() {
		super();
	}

	@Override
	public String getEtype() {
		return ETYPE;
	}

	public boolean putPropertyValue(String propertyName, Object value) {
		boolean isModfiy = false;
		Object origVlaue;
		try {
			if (StateFlagEnum.INSERTED == dataState) {
				BeanUtil.setPropertyValue(this, propertyName, value);
				isModfiy = true;
			} else {
				if (originalPropertyValue.containsKey(propertyName)) {
					origVlaue = originalPropertyValue.get(propertyName);
				} else {
					origVlaue = BeanUtil.getPropertyValue(this, propertyName);
				}
				if (origVlaue == null) {
					if (value != null) {
						isModfiy = true;
					}
				} else {
					if (!origVlaue.equals(value)) {
						isModfiy = true;
					}
				}
				if (isModfiy) {
					originalPropertyValue.put(propertyName, origVlaue);
					BeanUtil.setPropertyValue(this, propertyName, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isModfiy;
	}
	
	
	public StateFlagEnum getDataState() {
		return dataState;
	}

	public void setDataState(StateFlagEnum dataState) {
		this.dataState = dataState;
	}

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateUid() {
		return createUid;
	}

	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	public String getCreateUcode() {
		return createUcode;
	}

	public void setCreateUcode(String createUcode) {
		this.createUcode = createUcode;
	}

	public String getCreateUname() {
		return createUname;
	}

	public void setCreateUname(String createUname) {
		this.createUname = createUname;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getModifyUid() {
		return modifyUid;
	}

	public void setModifyUid(String modifyUid) {
		this.modifyUid = modifyUid;
	}

	public String getModifyUcode() {
		return modifyUcode;
	}

	public void setModifyUcode(String modifyUcode) {
		this.modifyUcode = modifyUcode;
	}

	public String getModifyUname() {
		return modifyUname;
	}

	public void setModifyUname(String modifyUname) {
		this.modifyUname = modifyUname;
	}

	public Date getModifyAt() {
		return modifyAt;
	}

	public void setModifyAt(Date modifyAt) {
		this.modifyAt = modifyAt;
	}
	
	public IBaseVo getPreItemData() {
		return preItemData;
	}

	public void setPreItemData(IBaseVo preItemData) {
		this.preItemData = preItemData;
	}

	@Override
	public String toString() {
		//return this.getCode() + "-" + this.getName();
		return this.getName();
	}
}
