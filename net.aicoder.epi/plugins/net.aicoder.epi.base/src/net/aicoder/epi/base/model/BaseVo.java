package net.aicoder.epi.base.model;

import java.beans.PropertyChangeEvent;
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

	//private Map<String, Object> propertyOriginalValueMap = new HashMap<String, Object>(0);
	private Map<String, LoadElementState> loadElementStateMap = new HashMap<String, LoadElementState>(0);

	public BaseVo() {
		super();
	}

	@Override
	public StateFlagEnum getDataState(String propertyCode) {
		StateFlagEnum dataState = super.getDataState();
		if (isExtInfo(propertyCode)) {
			ElementInfo elementInfo = getElementInfo(propertyCode);
			if (elementInfo != null) {
				dataState = elementInfo.getDataState();
			}
		}
		return dataState;
	}
	
	//// property
	@Override
	public void setPropertyValue(String propertyCode, Object value) {
		if (isExtInfo(propertyCode)) {
			setElementInfoValue(propertyCode, value);
		} else {
			super.setPropertyValue(propertyCode, value);
		}
	}
	
	@Override
	public boolean putPropertyValue(String propertyCode, Object value) {
		boolean isModfiy = false;
		
		if (isExtInfo(propertyCode)) {
			isModfiy = putElementInfoValue(propertyCode, value);
		} else {
			isModfiy = super.putPropertyValue(propertyCode, value);
		}

		return isModfiy;
	}

	@Override
	public Object getPropertyOrigValue(String propertyCode) {
		Object origVlaue = null;
		if (isExtInfo(propertyCode)) {
			origVlaue = getElementInfoOrigValue(propertyCode);
		} else {
			origVlaue = super.getPropertyOrigValue(propertyCode);
		}
		return origVlaue;
	}

	@Override
	public Object getPropertyValue(String propertyCode) {
		Object value = null;
		try {
			if (isExtInfo(propertyCode)) {
				value = getElementInfoValue(propertyCode);
			} else {
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
	
	@Override
	public boolean isLoadedElement(String elementName) {
		boolean isLoaded = false;
		if (loadElementStateMap.containsKey(elementName)) {
			LoadElementState loadElementState = loadElementStateMap.get(elementName);
			isLoaded = loadElementState.isLoadedElement();
		}
		return isLoaded;
	}

	@Override
	public boolean isLoadedElement(String elementName, int pageNo) {
		boolean isLoaded = false;
		if (loadElementStateMap.containsKey(elementName)) {
			LoadElementState loadElementState = loadElementStateMap.get(elementName);
			isLoaded = loadElementState.isLoadedElement(pageNo);
		}
		return isLoaded;
	}
	
	////
	public void putLoadElementState(LoadElementState loadElementState) {
		if (loadElementState == null) {
			return;
		}
		String elementName = loadElementState.getElementName();
		loadElementStateMap.put(elementName, loadElementState);
	}

	public void initElementInfos(List<ElementInfo> elementInfoList) {
		if (elementInfoList == null) {
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
		Object value = null;
		ElementInfo info = getElementInfo(propertyCode);

		if (info != null) {
			value = info.getInfoValue();
		}
		return value;
	}
	
	public Object getElementInfoOrigValue(String propertyCode) {
		Object origVlaue = null;
		String infoCode = propertyCode2InfoCode(propertyCode);
		if (propertyOriginalValueMap.containsKey(propertyCode)) {
			origVlaue = propertyOriginalValueMap.get(propertyCode);
		} else {
			if(elementInfoMap.containsKey(infoCode)) {
				origVlaue = elementInfoMap.get(infoCode).getInfoValue();
			}else {
				origVlaue = extInfosDefine.getElementInfoDefine(infoCode);
			}
		}
		return origVlaue;
	}
	
	public void setElementInfoValue(String propertyCode, Object value) {
		String infoCode = propertyCode2InfoCode(propertyCode);
		String strValue = null;
		if (value != null) {
			strValue = value.toString();
		}

		ElementInfo info = getElementInfo(propertyCode);
		if (info == null && extInfosDefine != null) {
			info = extInfosDefine.newElementInfo(infoCode);
			info.setDataState(StateFlagEnum.INSERTED);
			elementInfoMap.put(info.getCode(), info);
		}
		if (info != null) {
			info.setInfoValue(strValue);
		}
	}
	
	public boolean putElementInfoValue(String propertyCode, Object value) {
		boolean isModfiy = false;
		Object origVlaue;
		origVlaue = getElementInfoOrigValue(propertyCode);
		
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
			setElementInfoValue(propertyCode, value);
			if(StateFlagEnum.ORIGINAL.equals(getDataState(propertyCode))) {
				ElementInfo info = getElementInfo(propertyCode);
				info.setDataState(StateFlagEnum.UPDATED);
			}
			PropertyChangeEvent event = new PropertyChangeEvent(this, propertyCode, origVlaue, value);
			firePropertyChange(event);
		}
		return isModfiy;
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
		if (isExtInfo(propertyCode)) {
			infoCode = propertyCode.substring(prefixLen).trim();
		} else {
			infoCode = propertyCode;
		}
		return infoCode;
	}

	public static String infoCode2propertyCode(String infoCode) {
		String propertyCode = "";
		if (isExtInfo(infoCode)) {
			propertyCode = infoCode;
		} else {
			propertyCode = PROP_INFO_PREFIX + infoCode;
		}
		return propertyCode;
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
