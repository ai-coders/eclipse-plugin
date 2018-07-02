package net.aicoder.epi.devp.prddev.doper.ops;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.dao.ops.SysDpySchemaDao;
import net.aicoder.epi.devp.prddev.model.ops.SysDpySchemaVo;

/**
 * 部署模型-部署方案
 * @author WANGQINGPING
 *
 */
public class SysDpySchemaDoper extends BaseDoper{
	private SysDpySchemaDao sysDpySchemaDao;
	
	public SysDpySchemaDoper() {
		super();
		this.sysDpySchemaDao = new SysDpySchemaDao();
	}
	
	public List<SysDpySchemaVo> loadSysDpySchemaList(IBaseVo baseVo){
		return sysDpySchemaDao.loadSysDpySchemaList(baseVo);
	}
}
