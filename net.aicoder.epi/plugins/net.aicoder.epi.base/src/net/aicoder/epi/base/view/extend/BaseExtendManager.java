package net.aicoder.epi.base.view.extend;

import java.util.HashMap;
import java.util.Map;

import net.aicoder.epi.base.model.BaseVo;
import net.aicoder.epi.base.model.ExtInfosDefine;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.property.PropsDefine;
import net.aicoder.epi.base.view.property.IPropsManager;

/**
 * 下拉选项扩展管理器
 * @author WQP
 *
 */
public abstract interface BaseExtendManager extends IPropsManager {
	static Map<String, Object> refObjectsMap = new HashMap<String, Object>(0);

	@Override
	public default String getPluginId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public default ExtInfosDefine getExtInfosDefine(IBaseVo element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public default void loadEelementInfos(BaseVo element) {
		// TODO Auto-generated method stub

	}

	@Override
	public default PropsDefine getPropsDefine(IBaseVo element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public abstract void clearRefObjects();

	@Override
	public abstract void putRefObjects(String refObjectsCode, Object refObjects);

	@Override
	public abstract Object getRefObjects(String refObjectsCode);

}
