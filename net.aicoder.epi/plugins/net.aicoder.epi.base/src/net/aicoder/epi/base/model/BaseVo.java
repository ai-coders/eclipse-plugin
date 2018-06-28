package net.aicoder.epi.base.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.aicoder.tcom.tools.util.AiStringUtil;
import net.aicoder.tcom.tools.util.BeanUtil;

public class BaseVo extends AbstractBaseVo {
	private static final long serialVersionUID = 1L;

	// BaseVo有2类属性：一类为BaseVo对象自身的属性;另一类为扩展属性，即保存的ElementInfo扩展表中，属性名称为Info:xxx，必须带有前缀"Info:"
	public static final String PROP_INFO_PREFIX = "Info:";

	private ExtInfosDefine extInfosDefine;
	private Map<String, ElementInfo> elementInfoMap = new HashMap<String, ElementInfo>(0);

	private Map<String, Object> propertyOriginalValueMap = new HashMap<String, Object>(0);
	private Map<String, LoadElementState> loadElementStateMap = new HashMap<String, LoadElementState>(0);

	private IBaseVo preItemData;

	public BaseVo() {
		super();
	}
	
	@Override
	public StateFlagEnum getDataState(String propertyCode) {
		StateFlagEnum dataState = super.getDataState();
		if(this.isExtInfo(propertyCode)) {
			ElementInfo elementInfo = getElementInfo(propertyCode);
			if(elementInfo != null) {
				dataState = elementInfo.getDataState();
			}
		}
		return dataState;
	}

	public void settElementInfos(List<ElementInfo> elementInfoList) {
		if(elementInfoList == null) {
			return;
		}
		elementInfoMap.clear();
		LoadElementState loadElementState = new LoadElementState(PROP_INFO_PREFIX);
		loadElementState.setStartRecNo(0);
		putLoadElementState(loadElementState);

		for (ElementInfo info : elementInfoList) {
			if (info != null) {
				elementInfoMap.put(info.getCode(), info);
			}
		}
	}

	public ElementInfo getElementInfo(String propertyCode) {
		String infoCode = propertyCode2InfoCode(propertyCode);
		
		ElementInfo info = null;
		if (elementInfoMap.containsKey(infoCode)) {
			info = elementInfoMap.get(infoCode);
		}
		return info;
	}

	public Object getElementInfoValue(String propertyCode) {
		//String infoCode = propertyCode2InfoCode(propertyCode);
		Object value = null;
		ElementInfo info = getElementInfo(propertyCode);

		if (info != null) {
			value = info.getInfoValue();
		}
		return value;
	}

	public void setElementInfoValue(String propertyCode, Object value) {
		String strValue = null;
		if (value != null) {
			strValue = value.toString();
		}

		ElementInfo info = getElementInfo(propertyCode);
		if (info == null && extInfosDefine != null) {
			String infoCode = propertyCode2InfoCode(propertyCode);
			info = extInfosDefine.newElementInfo(infoCode);
			elementInfoMap.put(info.getCode(), info);
		}
		if(info != null) {
			info.setInfoValue(strValue);
		}
	}
	
	public static boolean isExtInfo(String propertyCode) {
		boolean isExtElementInfo = false;
		
		String prefix = "";
		int prefixLen = PROP_INFO_PREFIX.length();
		if (!AiStringUtil.isEmpty(propertyCode) && propertyCode.length() > prefixLen) {
			prefix = propertyCode.substring(0, prefixLen);
			if (PROP_INFO_PREFIX.equalsIgnoreCase(prefix)) {
				isExtElementInfo = true;
			}
		}
		
		return isExtElementInfo;
	}

	public static String propertyCode2InfoCode(String propertyCode) {
		String infoCode = "";
		int prefixLen = PROP_INFO_PREFIX.length();
		if(isExtInfo(propertyCode)) {
			infoCode = propertyCode.substring(prefixLen).trim();
		}else {
			infoCode = propertyCode;
		}
		return infoCode;
	}
	
	public static String infoCode2propertyCode(String infoCode) {
		String propertyCode = "";
		if(isExtInfo(infoCode)) {
			propertyCode = infoCode;
		}else {
			propertyCode = PROP_INFO_PREFIX + infoCode;
		}
		return propertyCode;
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
	public boolean putPropertyValue(String propertyCode, Object value) {
		boolean isModfiy = false;
		Object origVlaue;
		try {
			if (StateFlagEnum.INSERTED == getDataState()) {
				if(isExtInfo(propertyCode)) {
					setElementInfoValue(propertyCode, value);
				}else {
					BeanUtil.setPropertyValue(this, propertyCode, value);
				}
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
					if(isExtInfo(propertyCode)) {
						setElementInfoValue(propertyCode, value);
					}else {
						BeanUtil.setPropertyValue(this, propertyCode, value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isModfiy;
	}

	@Override
	public Object getPropertyOrigValue(String propertyCode) {
		Object origVlaue = null;
		try {
			if (StateFlagEnum.INSERTED == getDataState()) {
				// BeanUtil.setPropertyValue(this, propertyCode, value);
			} else {
				if (propertyOriginalValueMap.containsKey(propertyCode)) {
					origVlaue = propertyOriginalValueMap.get(propertyCode);
				} else {
					//origVlaue = BeanUtil.getPropertyValue(this, propertyCode);
					if(isExtInfo(propertyCode)) {
						origVlaue = getElementInfoValue(propertyCode);
					}else {
						origVlaue = BeanUtil.getPropertyValue(this, propertyCode);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return origVlaue;
	}

	@Override
	public Object getPropertyValue(String propertyCode) {
		Object value = null;
		try {
			//value = BeanUtil.getPropertyValue(this, propertyCode);
			if(isExtInfo(propertyCode)) {
				value = getElementInfoValue(propertyCode);
			}else {
				value = BeanUtil.getPropertyValue(this, propertyCode);
			}
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
	
	//// getter/setter
	public ExtInfosDefine getExtInfosDefine() {
		return extInfosDefine;
	}

	public void setExtInfosDefine(ExtInfosDefine extInfosDefine) {
		this.extInfosDefine = extInfosDefine;
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
}
