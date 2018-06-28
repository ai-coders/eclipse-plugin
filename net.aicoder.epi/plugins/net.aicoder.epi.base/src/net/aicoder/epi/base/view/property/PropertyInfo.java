package net.aicoder.epi.base.view.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.aicoder.epi.base.model.BooleanItem;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.OptionItem;
import net.aicoder.epi.base.model.StateFlagEnum;
import net.aicoder.epi.base.model.TreeNodeVo;
import net.aicoder.epi.base.model.property.PitemDefine;
import net.aicoder.epi.base.model.property.PropCatgEnum;
import net.aicoder.epi.base.model.property.PropControlEnum;
import net.aicoder.tcom.tools.util.AiStringUtil;

public class PropertyInfo extends TreeNodeVo {
	private static final long serialVersionUID = 1L;

	private static Object[] trueBooleanItemObject = { true, 1, "是" };
	private static BooleanItem trueBooleanItem = new BooleanItem(trueBooleanItemObject);
	private static Object[] falseBooleanItemObject = { false, 0, "否" };
	private static BooleanItem falseBooleanItem = new BooleanItem(falseBooleanItemObject);

	private IBaseVo currentData;
	private IPropsManager propsManager;
	private Object refObjects = null;
	private List<OptionItem> comboBoxItemList;
	private Map<Boolean, BooleanItem> checkBoxItemMap;

	private PitemDefine itemDefine;
	private PropCatgEnum propCtgy;

	private boolean m_showAdvancedProperties = false;

	private List<IBaseVo> fullChildrenList = new ArrayList<IBaseVo>(0);

	public void setShowAdvancedProperties(boolean showAdvancedProperties) {
		m_showAdvancedProperties = showAdvancedProperties;
		List<IBaseVo> childrenList = getChildrenList();
		childrenList.clear();
		if (m_showAdvancedProperties) {
			for (IBaseVo child : fullChildrenList) {
				PropertyInfo propertyInfo = (PropertyInfo) child;
				propertyInfo.setShowAdvancedProperties(m_showAdvancedProperties);
				childrenList.add(propertyInfo);
			}
		}
	}

	public boolean isModified() {
		boolean isModified = false;
		if (getOrigValue() == null) {
			if (getValue() == null) {
				isModified = false;
			} else {
				isModified = true;
			}
		} else {
			isModified = getOrigValue().equals(getValue()) ? false : true;
		}
		return isModified;
	}

	//// getter/setter
	public PitemDefine getItemDefine() {
		return itemDefine;
	}

	public IBaseVo getCurrentData() {
		return currentData;
	}

	public void setCurrentData(IBaseVo currentData) {
		this.currentData = currentData;
	}

	public IPropsManager getPropsManager() {
		return propsManager;
	}

	public void setPropsManager(IPropsManager propsManager) {
		this.propsManager = propsManager;
		initRefObjects();
	}

	public void setItemDefine(PitemDefine itemDefine) {
		this.itemDefine = itemDefine;
		propCtgy = PropCatgEnum.forStr(itemDefine.getCategory());

		initRefObjects();
	}

	public PropCatgEnum getPropCtgy() {
		return propCtgy;
	}

	public List<IBaseVo> getFullChildrenList() {
		return fullChildrenList;
	}

	public Object getDefaultValue() {
		Object value = null;
		if (itemDefine.getData() != null) {
			value = itemDefine.getData().getDafaultValue();
		}
		return value; // 是否需要做数据类型的转换，将String转为相应的类型？
	}

	public Object getOrigValue() {
		Object value = null;
		String propertyCode = itemDefine.getCode();
		if (StateFlagEnum.INSERTED == currentData.getDataState(propertyCode)) {
			value = getDefaultValue();
			if(value != null) {
				return value; 
			}
		}
		if (currentData != null) {
			currentData.getPropertyOrigValue(propertyCode);
		}
		return value;
	}

	public Object getValue() {
		Object value = null;
		String propertyCode = itemDefine.getCode();
		if (currentData != null) {
			currentData.getPropertyValue(propertyCode);
		}
		return value;
	}

	public void setValue(Object value) {
		String propertyCode = itemDefine.getCode();
		if (currentData != null) {
			currentData.putPropertyValue(propertyCode, value);
		}
	}

	public String getShowValue() {
		String showValue = "";
		if (currentData == null) {
			return showValue;
		}

		String propertyCode = itemDefine.getCode();
		String controlType = "";
		if (itemDefine.getControl() != null) {
			controlType = itemDefine.getControl().getType();
		}
		if (controlType == null) {
			controlType = "";
		} else {
			controlType = controlType.toUpperCase();
		}
		PropControlEnum controlTypeEnum = PropControlEnum.forStr(controlType);
		switch (controlTypeEnum) {
		case NONE:
		case Text:
			showValue = currentData.getPropertyShowValue(propertyCode);
			break;
		case Date:
			showValue = currentData.getPropertyShowValue(propertyCode);
			break;
		case DateTime:
			showValue = currentData.getPropertyShowValue(propertyCode);
			break;
		case ComboBox:
			showValue = getComboBoxShowValue();
			break;
		case CheckBox:
			showValue = getCheckBoxShowValue();
			break;
		default:
			break;
		}

		return showValue;
	}

	public List<OptionItem> getComboBoxItemList() {
		return this.comboBoxItemList;
	}

	public OptionItem getComboBoxOptionItem(int index) {
		OptionItem optionItem = null;
		optionItem = comboBoxItemList.get(index);
		return optionItem;
	}

	public BooleanItem getCheckBoxBooleanItem() {
		BooleanItem booleanItem = null;
		String propertyCode = itemDefine.getCode();
		Object value = currentData.getPropertyValue(propertyCode);

		if (checkBoxItemMap != null) {
			if (value != null) {
				if (value.equals(checkBoxItemMap.get(true).getDataValue())) {
					booleanItem = checkBoxItemMap.get(true);
				} else if (value.equals(checkBoxItemMap.get(false).getDataValue())) {
					booleanItem = checkBoxItemMap.get(false);
				}
			}
		}
		return booleanItem;
	}

	public BooleanItem getCheckBoxBooleanItem(boolean selected) {
		BooleanItem booleanItem = null;
		booleanItem = checkBoxItemMap.get(selected);
		return booleanItem;
	}

	//// private
	@SuppressWarnings("unchecked")
	private void initRefObjects() {
		if (itemDefine == null || propsManager == null) {
			return;
		}

		String refObjectsCode = "";
		String controlType = "";
		if (itemDefine.getControl() != null) {
			refObjectsCode = itemDefine.getControl().getRefObjects();
			controlType = itemDefine.getControl().getType();
		}
		if (!AiStringUtil.isEmpty(refObjectsCode)) {
			refObjects = propsManager.getRefObjects(refObjectsCode);
		}

		if (PropControlEnum.ComboBox.controlType().equalsIgnoreCase(controlType)) {
			if (refObjects != null) {
				comboBoxItemList = (List<OptionItem>) refObjects;
			}
		} else if (PropControlEnum.CheckBox.controlType().equalsIgnoreCase(controlType)) {
			checkBoxItemMap = new HashMap<Boolean, BooleanItem>(2);
			checkBoxItemMap.put(true, trueBooleanItem);
			checkBoxItemMap.put(false, falseBooleanItem);

			if (refObjects != null) {
				for (Object optionItem : (List<?>) refObjects) {
					BooleanItem booleanItem = (BooleanItem) optionItem;
					checkBoxItemMap.put(booleanItem.isBooleanValue(), booleanItem);
				}
			}
		}
	}

	private String getComboBoxShowValue() {
		String showValue = "";
		String propertyCode = itemDefine.getCode();
		Object value = currentData.getPropertyValue(propertyCode);
		showValue = currentData.getPropertyShowValue(propertyCode);

		if (comboBoxItemList != null) {
			for (OptionItem optionItem : comboBoxItemList) {
				if (optionItem.getCode().equals(value)) {
					showValue = optionItem.getValue();
					break;
				}
			}
		}
		return showValue;
	}

	private String getCheckBoxShowValue() {
		String showValue = "";
		String propertyCode = itemDefine.getCode();
		Object value = currentData.getPropertyValue(propertyCode);
		showValue = currentData.getPropertyShowValue(propertyCode);

		if (checkBoxItemMap != null) {
			if (value != null) {
				if (value.equals(checkBoxItemMap.get(true).getDataValue())) {
					showValue = checkBoxItemMap.get(true).getShowValue();
				} else if (value.equals(checkBoxItemMap.get(false).getDataValue())) {
					showValue = checkBoxItemMap.get(false).getShowValue();
				}
			}
		}
		return showValue;
	}

}
