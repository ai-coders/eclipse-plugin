package net.aicoder.epi.devp.prddev.dao.dev;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDiagramVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElementVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

public class ProductDevDao extends BaseDao {
	
	public ProductDevDao() {
		super();
	}
	
	//// 依据当前的产品rid获取关联的外部产品
	public List<PrdProductVo> loadExtProductList(long prdRid){ 
		List<PrdProductVo> productList = new ArrayList<PrdProductVo>(0);
		
		PrdProductVo prd1 = new PrdProductVo();
		prd1.setRid(90001);
		prd1.setCode("Ext_PD_C1");
		prd1.setName("Ext_PD_N1");
		productList.add(prd1);
		
		PrdProductVo prd2 = new PrdProductVo();
		prd2.setRid(90001);
		prd2.setCode("Ext_PD_C2");
		prd2.setName("Ext_PD_N2");
		productList.add(prd2);
		
		return productList;
	}
	
	//// 获取当前系统元素的设计图
	public List<IBaseVo> loadDevDgmList(IBaseVo sysElement){
		List<IBaseVo> list = new  ArrayList<IBaseVo>(0);
		long rid = 100000;
		{
			SysDiagramVo vo = new SysDiagramVo();
			vo.setRid(++rid);
			vo.setCode("ELM_DGM_C" + rid);
			vo.setName("ELM_DGM_N" + rid);
			vo.setDgmFlag(EtypeEnum.SYS_CMP_DGM.etype());
			vo.setSysElement((SysElementVo)sysElement);
			
			list.add(vo);
		}
		{
			SysDiagramVo vo = new SysDiagramVo();
			vo.setRid(++rid);
			vo.setCode("ELM_DGM_C" + rid);
			vo.setName("ELM_DGM_N" + rid);
			vo.setDgmFlag(EtypeEnum.SYS_DPY_DGM.etype());
			vo.setSysElement((SysElementVo)sysElement);
			list.add(vo);
		}
		
		return list;
	}

}
