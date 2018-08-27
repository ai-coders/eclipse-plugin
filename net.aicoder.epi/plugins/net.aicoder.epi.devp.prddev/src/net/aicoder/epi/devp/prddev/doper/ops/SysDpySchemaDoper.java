package net.aicoder.epi.devp.prddev.doper.ops;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.devp.prddev.dao.ops.SysDpySchemaDao;

/**
 * 运维流水线-部署方案Doper
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
	
	public IEpiInput loadSysDpySchemaList(IBaseVo baseVo){
		if(baseVo == null) return null;
//		if(baseVo.isLoadedElement(SYS_DPY_SCHEMA)) return null;
		
		List<IBaseVo> sysDpySchemaList = sysDpySchemaDao.loadSysDpySchemaList(baseVo);
		IEpiInput epiInput = new EpiInput();
		epiInput.setDataList(sysDpySchemaList);
		
//		LoadElementState loadElementState = new LoadElementState(SYS_DPY_SCHEMA);
//		loadElementState.setStartRecNo(0);
//		baseVo.putLoadElementState(loadElementState);
		return epiInput;
	}
}
