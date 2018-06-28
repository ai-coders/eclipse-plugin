package net.aicoder.epi.base.view.property;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.model.BaseVo;
import net.aicoder.epi.base.model.ExtInfosDefine;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.property.PitemDefine;
import net.aicoder.epi.base.model.property.PropsDefine;
import net.aicoder.epi.base.view.context.EpiInput;

public class PropsInput extends EpiInput{ //implements IEpiInput {
	//private IBaseVo currentData;
	//private List<IBaseVo> dataList;

	private IPropsManager propsManager;
	private ExtInfosDefine extInfosDefine;
	private PropsDefine propsDefine;
	
	private List<PropertyInfo> fullPropsInfoList = new ArrayList<PropertyInfo>(0);
	private List<PropertyInfo> propsInfoList = new ArrayList<PropertyInfo>(0);
	
	private boolean m_showAdvancedProperties = false;

	public PropsInput() {
		super();
	}
	
	public PropsInput(IPropsManager propsManager) {
		super();
		this.propsManager = propsManager;
	}

	public PropsInput(IPropsManager propsManager, IBaseVo currentData) {
		this(propsManager);
		setCurrentData(currentData);
	}

	//// Override
	@Override
	public void setParameter(String parameterName, Object parameterValue) {
	}

	@Override
	public Object getParameter(String parameterName) {
		return null;
	}

	@Override
	public void setCurrentData(IBaseVo currentData) {
		super.setCurrentData(currentData);
		
		fullPropsInfoList.clear();
		propsInfoList.clear();
		
		if(propsManager != null) {
			extInfosDefine = propsManager.getExtInfosDefine(currentData);
			((BaseVo)currentData).setExtInfosDefine(extInfosDefine);
			propsManager.loadEelementInfos((BaseVo)currentData);
			propsDefine = propsManager.getPropsDefine(currentData);
		}
		if (propsDefine == null) {
			return;
		}
		for (PitemDefine itemDefine : propsDefine.getPitemDefineList()) {
			PropertyInfo propInfo = createPropertyInfo(currentData, itemDefine);
			if (propInfo != null) {
				fullPropsInfoList.add(propInfo);
				if (propInfo.getPropCtgy().priority() <= 0) {
					propsInfoList.add(propInfo);
				}
			}
		}
	}

	private PropertyInfo createPropertyInfo(IBaseVo element, PitemDefine itemDefine) {
		if (itemDefine == null) {
			return null;
		}
		PropertyInfo propInfo = new PropertyInfo();
		propInfo.setCurrentData(element);
		propInfo.setPropsManager(propsManager);
		
		propInfo.setItemDefine(itemDefine);
		propInfo.setCode(itemDefine.getCode());
		propInfo.setName(itemDefine.getName());
		propInfo.setAlias(itemDefine.getAlias());
		//setPropInfoValue(element, propInfo);
		
		if(itemDefine.getSubItemsList() == null) {
			return propInfo;
		}
		for (PitemDefine subItemDefine : itemDefine.getSubItemsList()) {
			PropertyInfo subPropInfo = createPropertyInfo(element, subItemDefine);
			if (subPropInfo != null) {
				propInfo.getFullChildrenList().add(subPropInfo);
				if (subPropInfo.getPropCtgy().priority() <= 0) {
					propInfo.getChildrenList().add(subPropInfo);
				}
			}
		}
		return propInfo;
	}

/**	
	private void setPropInfoValue(IBaseVo element, PropertyInfo propInfo) {
		PitemDefine itemDefine = propInfo.getItemDefine();
		String propertyName = itemDefine.getCode();
		if(AiStringUtil.isEmpty(propertyName)) {
			return;
		}

		Object origValue = null;
		origValue = element.getPropertyOrigValue(propertyName);
		if (origValue != null) {
			propInfo.setDefValue(origValue);
		}
		
		Object value = element.getPropertyValue(propertyName);
		propInfo.setValue(value);
		
		String showValue = element.getPropertyShowValue(propertyName);
		propInfo.setShowValue(showValue);
	}
**/
	
	//// getter/setter
	public PropsDefine getPropsDefine() {
		return propsDefine;
	}

	public ExtInfosDefine getExtInfosDefine() {
		return extInfosDefine;
	}

	public void setExtInfosDefine(ExtInfosDefine extInfosDefine) {
		this.extInfosDefine = extInfosDefine;
	}

	public void setDefine(PropsDefine define) {
		this.propsDefine = define;
	}

	public List<PropertyInfo> getFullPropsInfoList() {
		return this.fullPropsInfoList;
	}

	public PropertyInfo[] getPropertyInfos() {
		int size = propsInfoList.size();
		PropertyInfo[] propertyInfos = new PropertyInfo[0];
		if(size == 0) {
			return propertyInfos;
		}else {
			propertyInfos = new PropertyInfo[size];
			int idx = 0;
			for(PropertyInfo propertyInfo:propsInfoList) {
				propertyInfos[idx++] = propertyInfo;
			}
			return propertyInfos;
		}
	}
	
	public void setShowAdvancedProperties(boolean showAdvancedProperties) {
		m_showAdvancedProperties = showAdvancedProperties;
		propsInfoList.clear();
		for(PropertyInfo propertyInfo:fullPropsInfoList) {
			if(m_showAdvancedProperties) {
				propertyInfo.setShowAdvancedProperties(m_showAdvancedProperties);
				propsInfoList.add(propertyInfo);
			}else {
				if (propertyInfo.getPropCtgy().priority() <= 0) {
					propsInfoList.add(propertyInfo);
				}
			}
		}
	}
	
	public IPropsManager getPropsManager() {
		return this.propsManager;
	}

}
