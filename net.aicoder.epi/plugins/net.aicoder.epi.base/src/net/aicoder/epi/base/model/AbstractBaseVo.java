package net.aicoder.epi.base.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.tcom.tools.util.BeanUtil;

public abstract class AbstractBaseVo implements IBaseVo {
	private static final long serialVersionUID = 1L;
	
	//private boolean initFlag = false; // 是否为对象初始化，如从服务端获取数据后设值时需要将标识设为true，后续客户端修改的信息才可以体现出属性是否修改

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

	//private IBaseVo preItemData;
	protected Map<String, Object> propertyOriginalValueMap = new HashMap<String, Object>(0);
	//private Map<String, LoadElementState> loadElementStateMap = new HashMap<String, LoadElementState>(0);

	public AbstractBaseVo() {
		super();
	}
/**
	@Override
	public boolean isInitFlag() {
		return initFlag;
	}

	@Override
	public void setInitFlag(boolean initFlag) {
		this.initFlag = initFlag;
	}
**/
	@Override
	public StateFlagEnum getDataState(String propertyCode) {
		return dataState;
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

	//// 前置的元素引用，控制元素排列顺序时使用
	@Override
	public IBaseVo getPreItemData() {
		return null;
	}

	@Override
	public void setPreItemData(IBaseVo preItemData) {
	}

	//// property
	@Override
	public void setPropertyValue(String propertyCode, Object value) { // 只能给跟踪属性变更的情景下使用
		try {
			BeanUtil.setPropertyValue(this, propertyCode, value);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public boolean putPropertyValue(String propertyCode, Object value) { // 只能给跟踪属性变更的情景下使用
		boolean isModfiy = false;
		Object origVlaue;
		try {
			origVlaue = getPropertyOrigValue(propertyCode);
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
				propertyOriginalValueMap.put(propertyCode, origVlaue);
				//putPropertyOrigValue(propertyCode, origVlaue);
				BeanUtil.setPropertyValue(this, propertyCode, value);
				PropertyChangeEvent event = new PropertyChangeEvent(this, propertyCode, origVlaue, value);
				firePropertyChange(event);
			}
/**			
			if (StateFlagEnum.INSERTED == dataState) {
				BeanUtil.setPropertyValue(this, propertyCode, value);
				isModfiy = true;
			} else {
				origVlaue = getPropertyOrigValue(propertyCode);
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
					propertyOriginalValueMap.put(propertyCode, origVlaue);
					BeanUtil.setPropertyValue(this, propertyCode, value);
				}
			}
**/			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isModfiy;
	}

	@Override
	public Object getPropertyOrigValue(String propertyCode) {
		Object origVlaue = null;
		try {
			if (propertyOriginalValueMap.containsKey(propertyCode)) {
				origVlaue = propertyOriginalValueMap.get(propertyCode);
			} else {
				origVlaue = BeanUtil.getPropertyValue(this, propertyCode);
			}
/**			
			if (StateFlagEnum.INSERTED == dataState) {
				// BeanUtil.setPropertyValue(this, propertyCode, value);
			} else {
				if (propertyOriginalValueMap.containsKey(propertyCode)) {
					origVlaue = propertyOriginalValueMap.get(propertyCode);
				} else {
					origVlaue = BeanUtil.getPropertyValue(this, propertyCode);
				}
			}
**/			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return origVlaue;
	}
	
	@Override
	public Object getPropertyValue(String propertyCode) {
		Object value = null;
		try {
			value = BeanUtil.getPropertyValue(this, propertyCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public String getPropertyShowValue(String propertyCode) {
		String showValue = "";
		Object value = getPropertyValue(propertyCode);
		if (value != null) {
			showValue = value.toString();
		}
		return showValue;
	}
	
	@Override
	public boolean isLoadedElement(String elementName) {
		return true;
	}
	
	@Override
	public boolean isLoadedElement(String elementName, int pageNo) {
		return true;
	}
	
/**
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
**/
	
	@Override
	public String toString() {
		return this.getName();
	}
	
	/////////////////////////////////////////////////////////////////////////////
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	@Override
	public void firePropertyChange(PropertyChangeEvent event) {
		changeSupport.firePropertyChange(event);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	@Override
	public void removeAllPropertyChangeListener() {
		PropertyChangeListener[] listeners = changeSupport.getPropertyChangeListeners();
		if(listeners != null) {
			for(PropertyChangeListener listener:listeners) {
				changeSupport.removePropertyChangeListener(listener);
			}
		}
	}
}
