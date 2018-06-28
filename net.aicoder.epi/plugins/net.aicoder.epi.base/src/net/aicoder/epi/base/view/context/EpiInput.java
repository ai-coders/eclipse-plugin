package net.aicoder.epi.base.view.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.aicoder.epi.base.model.IBaseVo;

public class EpiInput implements IEpiInput {
	private Map<String, Object> parameterMap = new HashMap<String, Object>(0);

	private IBaseVo currentData;
	private List<IBaseVo> dataList;

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
		IBaseVo oldData = this.currentData;
		if (oldData != null) {
			oldData.removeAllPropertyChangeListener();
		}
		this.currentData = currentData;
	}

	@Override
	// public <T extends IBaseVo> List<T> getDataList() {
	public List<IBaseVo> getDataList() {
		return dataList;
	}

	@Override
	public void setDataList(List<IBaseVo> dataList) {
		this.dataList = dataList;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) { // org.eclipse.core.resources.IResource.class
		return null;
	}
}
