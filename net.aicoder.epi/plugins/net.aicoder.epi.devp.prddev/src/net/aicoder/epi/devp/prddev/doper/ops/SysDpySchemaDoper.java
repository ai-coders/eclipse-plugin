package net.aicoder.epi.devp.prddev.doper.ops;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.LoadElementState;
import net.aicoder.epi.devp.prddev.dao.ops.SysDpySchemaDao;

/**
 * 部署模型-部署方案
 * @author WANGQINGPING
 *
 */
public class SysDpySchemaDoper extends BaseDoper{
	private SysDpySchemaDao sysDpySchemaDao;
	public static final String SYS_DPY_SCHEMA = "SysDpySchema";
	
	public SysDpySchemaDoper() {
		super();
		this.sysDpySchemaDao = new SysDpySchemaDao();
	}
	
	public List<IBaseVo> loadSysDpySchemaList(IBaseVo baseVo){
		if(baseVo == null) return null;
		if(baseVo.isLoadedElement(SYS_DPY_SCHEMA)) return null;
		
		List<IBaseVo> loadSysDpySchemaList = sysDpySchemaDao.loadSysDpySchemaList(baseVo);
		
		LoadElementState loadElementState = new LoadElementState(SYS_DPY_SCHEMA);
		loadElementState.setStartRecNo(0);
		baseVo.putLoadElementState(loadElementState);
		return loadSysDpySchemaList;
	}
}
