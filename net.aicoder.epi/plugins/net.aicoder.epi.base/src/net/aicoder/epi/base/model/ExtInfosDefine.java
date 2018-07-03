package net.aicoder.epi.base.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtInfosDefine {
	private String etype;

	private Map<String, ElementInfoDefine> elementInfoDefineMap = new HashMap<String, ElementInfoDefine>(0);

	public void initElementInfosDefine(List<ElementInfoDefine> commonInfoDefineList) {
		if (commonInfoDefineList == null) {
			return;
		}
		elementInfoDefineMap.clear();
		for (ElementInfoDefine infoDefine : commonInfoDefineList) {
			if (infoDefine != null) {
				elementInfoDefineMap.put(infoDefine.getCode(), infoDefine);
			}
		}
	}

	public void initElementInfosDefine(List<ElementInfoDefine> commonInfoDefineList,
			List<ElementInfoDefine> tenantInfoDefineList) {
		if (commonInfoDefineList == null && tenantInfoDefineList == null) {
			return;
		}
		elementInfoDefineMap.clear();
		if (commonInfoDefineList != null) {
			for (ElementInfoDefine infoDefine : commonInfoDefineList) {
				if (infoDefine != null) {
					elementInfoDefineMap.put(infoDefine.getCode(), infoDefine);
				}
			}
		}
		if (tenantInfoDefineList != null) {
			for (ElementInfoDefine infoDefine : tenantInfoDefineList) {
				if (infoDefine != null) {
					elementInfoDefineMap.put(infoDefine.getCode(), infoDefine);
				}
			}
		}
	}

	public ElementInfoDefine getElementInfoDefine(String infoCode) {
		ElementInfoDefine elementInfoDefine = null;
		if (elementInfoDefineMap.containsKey(infoCode)) {
			elementInfoDefine = elementInfoDefineMap.get(infoCode);
		}
		return elementInfoDefine;
	}

	public ElementInfo newElementInfo(String infoCode) {
		ElementInfo elementInfo = new ElementInfo();
		elementInfo.setEtype(etype);
		elementInfo.setCode(infoCode);

		ElementInfoDefine infoDefine = getElementInfoDefine(infoCode);
		if (infoDefine != null) {
			// elementInfo.setEtype(infoDefine.getEtype());
			// elementInfo.setCode(infoDefine.getCode());
			elementInfo.setName(infoDefine.getName());
			elementInfo.setAlias(infoDefine.getAlias());
			// elementInfo.setDescription(infoDefine.getDescription()); // 节省存储就不复制了

			elementInfo.setDataType(infoDefine.getDataType());
			elementInfo.setInfoValue(infoDefine.getInfoValue());
			// elementInfo.setNotes(infoDefine.getNotes()); // 节省存储就不复制了
		}
		return elementInfo;
	}
	
	public Object getElementInfoDefaultValue(String infoCode) {
		Object defaultValue = null;
		
		ElementInfoDefine elementInfoDefine = getElementInfoDefine(infoCode);
		if(elementInfoDefine != null) {
			defaultValue = elementInfoDefine.getInfoValue();
		}
		
		return defaultValue;
	}

	public String getEtype() {
		return etype;
	}

	public void setEtype(String etype) {
		this.etype = etype;
	}
/**
	public Map<String, ElementInfoDefine> getElementInfoDefineMap() {
		return elementInfoDefineMap;
	}

	public void setElementInfoDefineMap(Map<String, ElementInfoDefine> elementInfoDefineMap) {
		this.elementInfoDefineMap = elementInfoDefineMap;
	}
**/	
}
