package net.aicoder.epi.base.view.property;

import net.aicoder.epi.base.model.BaseVo;
import net.aicoder.epi.base.model.ExtInfosDefine;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.property.PropsDefine;

public interface IPropsManager {
	public String getPluginId();
	
	public ExtInfosDefine getExtInfosDefine(IBaseVo element);
	public void loadEelementInfos(BaseVo element);
	
	public PropsDefine getPropsDefine(IBaseVo element);
	
	public void clearRefObjects();
	public void putRefObjects(String refObjectsCode, Object refObjects);
	public Object getRefObjects(String refObjectsCode);
}
