package net.aicoder.epi.base.view.adapter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.aicoder.epi.base.model.IBaseVo;

public class EpiInput implements IEpiInput {
	private Map<String, Object> parameterMap = new HashMap<String, Object>(0);
	
	protected IBaseVo currentData;
	protected List<IBaseVo> dataList;

	public EpiInput() {
		super();
	}
	
	public EpiInput(IBaseVo currentData) {
		super();
		setCurrentData(currentData);
	}
	
	@Override
	public void setParameter(String parameterName, Object parameterValue) {
		this.parameterMap.put(parameterName, parameterValue);
	}
	
	@Override
	public Object getParameter(String parameterName) {
		return this.parameterMap.get(parameterName);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public IBaseVo getCurrentData() {
		return currentData;
	}

	@Override
	public void setCurrentData(IBaseVo currentData) {
		this.currentData = currentData;
	}

	@Override
	//public <T extends IBaseVo> List<T> getDataList() {
	public List<IBaseVo> getDataList() {
		return dataList;
	}

	@Override
	public void setDataList(List<IBaseVo> dataList) {
		this.dataList = dataList;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) { //org.eclipse.core.resources.IResource.class
		return null;
	}
	
	private PropertyChangeSupport delegate;

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		delegate.addPropertyChangeListener(listener);
	}

	@Override
	public void firePropertyChange(PropertyChangeEvent event) {
		delegate.firePropertyChange(event);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		delegate.removePropertyChangeListener(listener);
	}
}
