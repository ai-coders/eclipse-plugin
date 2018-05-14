package net.aicoder.epi.base.view.adapter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;

import net.aicoder.epi.base.model.IBaseVo;

public interface IEpiInput extends IAdaptable {
	public void setParameter(String parameterName, Object parameterValue);
	
	public Object getParameter(String parameterName);
	
	public IBaseVo getCurrentData();
	
	public void setCurrentData(IBaseVo currentData);
	
	public List<IBaseVo> getDataList();
	
	public void setDataList(List<IBaseVo> dataList);

	public void addPropertyChangeListener(PropertyChangeListener listener);
	
	public void firePropertyChange(PropertyChangeEvent event);
	
	public void removePropertyChangeListener(PropertyChangeListener listener);
}
