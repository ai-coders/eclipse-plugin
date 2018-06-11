package net.aicoder.epi.base.view.property;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.TreeNodeVo;
import net.aicoder.epi.base.view.definer.property.PitemDefine;
import net.aicoder.epi.base.view.definer.property.PropCatgEnum;

public class PropertyInfo extends TreeNodeVo {
	private static final long serialVersionUID = 1L;

	private PitemDefine itemDefine;
	private PropCatgEnum propCtgy;

	private boolean m_showAdvancedProperties = false;
	// private boolean isModified = false;

	private Object value;
	private String showValue;

	private Object defValue;

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
		if (defValue == null) {
			if (value == null) {
				isModified = false;
			} else {
				isModified = true;
			}
		} else {
			isModified = defValue.equals(value) ? false : true;
		}
		return isModified;
	}

	public PitemDefine getItemDefine() {
		return itemDefine;
	}

	public void setItemDefine(PitemDefine itemDefine) {
		this.itemDefine = itemDefine;
		propCtgy = PropCatgEnum.forStr(itemDefine.getCategory());
	}

	public PropCatgEnum getPropCtgy() {
		return propCtgy;
	}

	public List<IBaseVo> getFullChildrenList() {
		return fullChildrenList;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getShowValue() {
		return showValue;
	}

	public void setShowValue(String showValue) {
		this.showValue = showValue;
	}

	public Object getDefValue() {
		return defValue;
	}

	public void setDefValue(Object defValue) {
		this.defValue = defValue;
	}
}
