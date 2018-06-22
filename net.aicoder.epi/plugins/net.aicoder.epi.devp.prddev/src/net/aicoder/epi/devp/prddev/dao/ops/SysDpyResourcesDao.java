package net.aicoder.epi.devp.prddev.dao.ops;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.model.ops.SysDpyResourcesVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

public class SysDpyResourcesDao extends BaseDao{

	public SysDpyResourcesDao() {
		super();
	}
	
	public List<IBaseVo> loadSysDpyResourcesList(IBaseVo baseVo){
		List<IBaseVo> sysDpyResourcesList = new ArrayList<>();
		if(!(baseVo instanceof PrdProductVo)) return sysDpyResourcesList;
		
		//关联类型：部署到、连接、调用
		//连接时(子类型)：双向[-o)-]，请求[-)],提供[-o]
		{
			SysDpyResourcesVo sdrv = new SysDpyResourcesVo();
			sdrv.setName("部署名称");
			sdrv.setCode("SysDpyResources_Code_2");
			sdrv.setType("部署到");
			sysDpyResourcesList.add(sdrv);
		}
		{
			SysDpyResourcesVo sdrv = new SysDpyResourcesVo();
			sdrv.setName("连接双向名称");
			sdrv.setCode("SysDpyResources_Code_3");
			sdrv.setType("连接[-o)-]");
			sysDpyResourcesList.add(sdrv);
		}
		{
			SysDpyResourcesVo sdrv = new SysDpyResourcesVo();
			sdrv.setName("连接提供名称");
			sdrv.setCode("SysDpyResources_Code_3");
			sdrv.setType("连接[-o]");
			sysDpyResourcesList.add(sdrv);
		}
		{
			SysDpyResourcesVo sdrv = new SysDpyResourcesVo();
			sdrv.setName("连接请求名称");
			sdrv.setCode("SysDpyResources_Code_3");
			sdrv.setType("连接[-)]");
			sysDpyResourcesList.add(sdrv);
		}
		{
			SysDpyResourcesVo sdrv = new SysDpyResourcesVo();
			sdrv.setName("调用名称");
			sdrv.setCode("SysDpyResources_Code_4");
			sdrv.setType("调用");
			sysDpyResourcesList.add(sdrv);
		}
		
		return sysDpyResourcesList;
	}
	
}
