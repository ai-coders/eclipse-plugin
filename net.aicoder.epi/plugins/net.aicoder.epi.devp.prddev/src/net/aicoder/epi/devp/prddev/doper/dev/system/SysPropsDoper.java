package net.aicoder.epi.devp.prddev.doper.dev.system;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.BaseVo;
import net.aicoder.epi.base.model.ElementInfo;
import net.aicoder.epi.base.model.ElementInfoDefine;
import net.aicoder.epi.base.model.ExtInfosDefine;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.LoadElementState;
import net.aicoder.epi.devp.prddev.dao.dev.system.SysPropsDao;

public class SysPropsDoper  extends BaseDoper {
	
	private SysPropsDao sysPropsDao;
	
	public SysPropsDoper() {
		super();
		sysPropsDao = new SysPropsDao();
	}
	
	public ExtInfosDefine loadExtInfosDefine(IBaseVo element) {
		if(element == null) {
			return null;
		}
		ExtInfosDefine extInfosDefine = new ExtInfosDefine();
		extInfosDefine.setEtype(element.getEtype());
		
		List<ElementInfoDefine> commonInfoDefineList = sysPropsDao.listCommonExtInfoDefine(element);
		List<ElementInfoDefine> tenantInfoDefineList = sysPropsDao.listTenantExtInfoDefine(element);
		extInfosDefine.initElementInfosDefine(commonInfoDefineList, tenantInfoDefineList);
		
		return extInfosDefine;		
	}
	
	public List<ElementInfo> listElementInfo(IBaseVo element){
		if(element == null) {
			return null;
		}
		if(element.isLoadedElement(BaseVo.PROP_INFO_PREFIX)) {
			return null;
		}
		List<ElementInfo> list = sysPropsDao.listElementInfo(element);
		
		LoadElementState loadElementState = new LoadElementState(BaseVo.PROP_INFO_PREFIX);
		loadElementState.setStartRecNo(0);
		element.putLoadElementState(loadElementState);

		return list;
	}

}
