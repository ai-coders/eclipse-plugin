package net.aicoder.epi.base.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.aicoder.epi.base.model.property.DataDefine;
import net.aicoder.epi.base.model.property.PitemDefine;
import net.aicoder.epi.base.model.property.PropsDefine;
import net.aicoder.tcom.tools.util.AiStringUtil;
//import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.tcom.tools.util.BeanUtil;

public class BaseVo extends AbstractBaseVo {
	// BaseVo有2类属性：一类为BaseVo对象自身的属性;另一类为扩展属性，即保存的ElementInfo扩展表中，属性名称为Info:xxx，必须带有前缀"Info:"
	public static final String PROP_INFO_PREFIX = "Info:";

	private static final long serialVersionUID = 1L;

	private PropsDefine propsDefine;

	private Map<String, ElementInfo> elementInfoMap = new HashMap<String, ElementInfo>(0);

	private Map<String, Object> propertyOriginalValueMap = new HashMap<String, Object>(0);
	private Map<String, LoadElementState> loadElementStateMap = new HashMap<String, LoadElementState>(0);

	private IBaseVo preItemData;

	public BaseVo() {
		super();
	}

	public void putElementInfos(List<ElementInfo> elementInfoList) {
		if (elementInfoList == null) {
			return;
		}
		elementInfoMap.clear();
		for (ElementInfo info : elementInfoList) {
			if (info != null) {
				elementInfoMap.put(info.getCode(), info);
			}
		}
	}

	public ElementInfo getElementInfo(String propertyName) {
		String infoCode = propertyName2InfoCode(propertyName);
		
		ElementInfo info = null;
		if (elementInfoMap.containsKey(infoCode)) {
			info = elementInfoMap.get(infoCode);
		}
		return info;
	}

	public Object getElementInfoValue(String propertyName) {
		//String infoCode = propertyName2InfoCode(propertyName);
		Object value = null;
		ElementInfo info = getElementInfo(propertyName);

		if (info != null) {
			value = info.getInfoValue();
		}
		return value;
	}

	public void setElementInfoValue(String propertyName, Object value) {
		String strValue = null;
		if (value != null) {
			strValue = value.toString();
		}

		ElementInfo info = getElementInfo(propertyName);
		if (info == null) {
			info = newElementInfo(propertyName);
		}
		info.setInfoValue(strValue);
	}
	
	public boolean isExtInfo(String propertyName) {
		boolean isExtElementInfo = false;
		
		String prefix = "";
		int prefixLen = PROP_INFO_PREFIX.length();
		if (!AiStringUtil.isEmpty(propertyName) && propertyName.length() > prefixLen) {
			prefix = propertyName.substring(0, prefixLen - 1);
			if (PROP_INFO_PREFIX.equalsIgnoreCase(prefix)) {
				isExtElementInfo = true;
			}
		}
		
		return isExtElementInfo;
	}

	public String propertyName2InfoCode(String propertyName) {
		String infoCode = "";
		int prefixLen = PROP_INFO_PREFIX.length();
		if(isExtInfo(propertyName)) {
			infoCode = propertyName.substring(prefixLen).trim();
		}else {
			infoCode = propertyName;
		}
		return infoCode;
	}
	
	public String infoCode2PropertyName(String infoCode) {
		String propertyName = "";
		if(isExtInfo(infoCode)) {
			propertyName = infoCode;
		}else {
			propertyName = PROP_INFO_PREFIX + infoCode;
		}
		return propertyName;
	}

	private ElementInfo newElementInfo(String propertyName) {
		String infoCode = propertyName2InfoCode(propertyName);
		
		ElementInfo info = new ElementInfo();
		info.setTid(this.getTid());
		info.setEtype(this.getEtype());
		info.setObjRid(this.getRid());
		info.setCode(infoCode);

		PitemDefine pitemDefine;
		if (propsDefine != null) {
			pitemDefine = propsDefine.getPitemDefine(propertyName);
			if (pitemDefine != null) {
				info.setName(pitemDefine.getName());
				info.setAlias(pitemDefine.getAlias());
				DataDefine data = pitemDefine.getData();
				if (data != null) {
					info.setInfoValue(data.getDafaultValue());
				}
			}
		}
		info.setCode(propertyName);
		elementInfoMap.put(info.getCode(), info);
		return info;
	}

/**
	//@Override
	public Map<String, Object> getOriginalPropertyValue() {
		return propertyOriginalValueMap;
	}

	//@Override
	public void setOriginalPropertyValue(Map<String, Object> originalPropertyValue) {
		this.propertyOriginalValueMap = originalPropertyValue;
	}
**/
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
			if (StateFlagEnum.INSERTED == getDataState()) {
				if(isExtInfo(propertyName)) {
					setElementInfoValue(propertyName, value);
				}else {
					BeanUtil.setPropertyValue(this, propertyName, value);
				}
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
					if(isExtInfo(propertyName)) {
						setElementInfoValue(propertyName, value);
					}else {
						BeanUtil.setPropertyValue(this, propertyName, value);
					}
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
			if (StateFlagEnum.INSERTED == getDataState()) {
				// BeanUtil.setPropertyValue(this, propertyName, value);
			} else {
				if (propertyOriginalValueMap.containsKey(propertyName)) {
					origVlaue = propertyOriginalValueMap.get(propertyName);
				} else {
					//origVlaue = BeanUtil.getPropertyValue(this, propertyName);
					if(isExtInfo(propertyName)) {
						origVlaue = getElementInfoValue(propertyName);
					}else {
						origVlaue = BeanUtil.getPropertyValue(this, propertyName);
					}
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
			//value = BeanUtil.getPropertyValue(this, propertyName);
			if(isExtInfo(propertyName)) {
				value = getElementInfoValue(propertyName);
			}else {
				value = BeanUtil.getPropertyValue(this, propertyName);
			}
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

	public PropsDefine getPropsDefine() {
		return propsDefine;
	}

	public void setPropsDefine(PropsDefine propsDefine) {
		this.propsDefine = propsDefine;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
