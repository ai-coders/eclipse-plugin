package net.aicoder.epi.base.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.tcom.tools.util.BeanUtil;

public abstract class BaseVo implements IBaseVo {
	private static final long serialVersionUID = 1L;

	private StateFlagEnum dataState; // 只在客户端使用，不持久化

	private long rid;
	private long tid;
	private String etype;
	private String code;
	private String name;
	private String alias;
	private String description;
	private int recordState = 1; // 0-失效;1-生效;缺省为1

	private String parasId;
	private String createUid;
	private String createUcode;
	private String createUname;
	private Date createAt;
	private String modifyUid;
	private String modifyUcode;
	private String modifyUname;
	private Date modifyAt;

	private IBaseVo preItemData;
	private Map<String, Object> propertyOriginalValueMap = new HashMap<String, Object>(0);
	private Map<String, LoadElementState> loadElementStateMap = new HashMap<String, LoadElementState>(0);

	public BaseVo() {
		super();
	}

	@Override
	public StateFlagEnum getDataState() {
		return dataState;
	}

	@Override
	public void setDataState(StateFlagEnum dataState) {
		this.dataState = dataState;
	}

	@Override
	public String getPropsId() { // 属性定义对应的ID
		return getEtype();
	}

	//// 属性定义
	@Override
	public long getRid() {
		return rid;
	}

	@Override
	public void setRid(long rid) {
		this.rid = rid;
	}

	@Override
	public long getTid() {
		return tid;
	}

	@Override
	public void setTid(long tid) {
		this.tid = tid;
	}

	@Override
	public String getEtype() {
		return etype;
	}

	@Override
	public void setEtype(String etype) {
		this.etype = etype;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAlias() {
		return alias;
	}

	@Override
	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int getRecordState() {
		return recordState;
	}

	@Override
	public void setRecordState(int recordState) {
		this.recordState = recordState;
	}

	@Override
	public String getParasId() {
		return parasId;
	}

	@Override
	public void setParasId(String parasId) {
		this.parasId = parasId;
	}

	@Override
	public String getCreateUid() {
		return createUid;
	}

	@Override
	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	@Override
	public String getCreateUcode() {
		return createUcode;
	}

	@Override
	public void setCreateUcode(String createUcode) {
		this.createUcode = createUcode;
	}

	@Override
	public String getCreateUname() {
		return createUname;
	}

	@Override
	public void setCreateUname(String createUname) {
		this.createUname = createUname;
	}

	@Override
	public Date getCreateAt() {
		return createAt;
	}

	@Override
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Override
	public String getModifyUid() {
		return modifyUid;
	}

	@Override
	public void setModifyUid(String modifyUid) {
		this.modifyUid = modifyUid;
	}

	@Override
	public String getModifyUcode() {
		return modifyUcode;
	}

	@Override
	public void setModifyUcode(String modifyUcode) {
		this.modifyUcode = modifyUcode;
	}

	@Override
	public String getModifyUname() {
		return modifyUname;
	}

	@Override
	public void setModifyUname(String modifyUname) {
		this.modifyUname = modifyUname;
	}

	@Override
	public Date getModifyAt() {
		return modifyAt;
	}

	@Override
	public void setModifyAt(Date modifyAt) {
		this.modifyAt = modifyAt;
	}

	@Override
	public Map<String, Object> getOriginalPropertyValue() {
		return propertyOriginalValueMap;
	}

	@Override
	public void setOriginalPropertyValue(Map<String, Object> originalPropertyValue) {
		this.propertyOriginalValueMap = originalPropertyValue;
	}

	//// 前置的元素引用，控制元素排列顺序时使用
	@Override
	public IBaseVo getPreItemData() {
		return preItemData;
	}

	@Override
	public void setPreItemData(IBaseVo preItemData) {
		this.preItemData = preItemData;
	}

	//// property
	@Override
	public boolean putPropertyValue(String propertyName, Object value) {
		boolean isModfiy = false;
		Object origVlaue;
		try {
			if (StateFlagEnum.INSERTED == dataState) {
				BeanUtil.setPropertyValue(this, propertyName, value);
				isModfiy = true;
			} else {
				origVlaue = getPropertyOrigValue(propertyName);
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
					propertyOriginalValueMap.put(propertyName, origVlaue);
					BeanUtil.setPropertyValue(this, propertyName, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isModfiy;
	}

	@Override
	public Object getPropertyOrigValue(String propertyName) {
		Object origVlaue = null;
		try {
			if (StateFlagEnum.INSERTED == dataState) {
				// BeanUtil.setPropertyValue(this, propertyName, value);
			} else {
				if (propertyOriginalValueMap.containsKey(propertyName)) {
					origVlaue = propertyOriginalValueMap.get(propertyName);
				} else {
					origVlaue = BeanUtil.getPropertyValue(this, propertyName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return origVlaue;
	}

	@Override
	public Object getPropertyValue(String propertyName) {
		Object value = null;
		try {
			value = BeanUtil.getPropertyValue(this, propertyName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public String getPropertyShowValue(String propertyName) {
		String showValue = "";
		Object value = getPropertyValue(propertyName);
		if (value != null) {
			showValue = value.toString();
		}
		return showValue;
	}

	public void putLoadElementState(LoadElementState loadElementState) {
		if (loadElementState == null) {
			return;
		}
		String elementName = loadElementState.getElementName();
		loadElementStateMap.put(elementName, loadElementState);
	}

	public boolean isLoadedElement(String elementName) {
		boolean isLoaded = false;
		if (loadElementStateMap.containsKey(elementName)) {
			LoadElementState loadElementState = loadElementStateMap.get(elementName);
			isLoaded = loadElementState.isLoadedElement();
		}
		return isLoaded;
	}

	public boolean isLoadedElement(String elementName, int pageNo) {
		boolean isLoaded = false;
		if (loadElementStateMap.containsKey(elementName)) {
			LoadElementState loadElementState = loadElementStateMap.get(elementName);
			isLoaded = loadElementState.isLoadedElement(pageNo);
		}
		return isLoaded;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
