package net.aicoder.epi.base.view.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.definer.property.PitemDefine;
import net.aicoder.epi.base.view.definer.property.PropsDefine;

public class PropsInput implements IEpiInput {
	private String pluginId;
	private IBaseVo currentData;
	private List<IBaseVo> dataList;

	private PropsDefine define;
	private List<PropertyInfo> fullPropsInfoList = new ArrayList<PropertyInfo>(0);
	private List<PropertyInfo> propsInfoList = new ArrayList<PropertyInfo>(0);

	public PropsInput() {
		super();
	}

	public PropsInput(String pluginId, IBaseVo currentData) {
		super();
		setPluginId(pluginId);
		setCurrentData(currentData);
	}
/**
	public PropsInput(IBaseVo currentData) {
		super();
		setCurrentData(currentData);
	}
*/
	
	//// Override
	@Override
	public void setParameter(String parameterName, Object parameterValue) {
	}

	@Override
	public Object getParameter(String parameterName) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends IBaseVo> T getCurrentData() {
		return (T) this.currentData;
	}

	@Override
	public void setCurrentData(IBaseVo currentData) {
		this.currentData = currentData;
		fullPropsInfoList.clear();
		propsInfoList.clear();

		define = PropsManager.getPropsDefine(pluginId, currentData);
		if (define == null) {
			return;
		}
		for (PitemDefine itemDefine : define.getPitemDefineList()) {
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
		propInfo.setItemDefine(itemDefine);
		propInfo.setCode(itemDefine.getCode());
		propInfo.setName(itemDefine.getName());
		propInfo.setAlias(itemDefine.getAlias());
		setPropInfoValue(element, propInfo);
		
		if(itemDefine.getSubItemsList() == null) {
			return propInfo;
		}
		for (PitemDefine subItemDefine : itemDefine.getSubItemsList()) {
			PropertyInfo subPropInfo = createPropertyInfo(element, subItemDefine);
			if (subPropInfo != null) {
				propInfo.getFullChildrenList().add(subPropInfo);
				if (subPropInfo.getPropCtgy().priority() <= 0) {
					propInfo.getChildrenList().add(propInfo);
				}
			}
		}
		return propInfo;
	}

	private void setPropInfoValue(IBaseVo element, PropertyInfo propInfo) {
		PitemDefine itemDefine = propInfo.getItemDefine();
		String propertyName = itemDefine.getCode();
		
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

	@SuppressWarnings("unchecked")
	@Override
	public <T extends IBaseVo> List<T> getDataList() {
		return (List<T>) this.dataList;
	}

	@Override
	public void setDataList(List<IBaseVo> dataList) {
		this.dataList = dataList;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void firePropertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	//// getter/setter
	public PropsDefine getDefine() {
		return define;
	}

	public String getPluginId() {
		return pluginId;
	}

	public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}

	public void setDefine(PropsDefine define) {
		this.define = define;
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

}
