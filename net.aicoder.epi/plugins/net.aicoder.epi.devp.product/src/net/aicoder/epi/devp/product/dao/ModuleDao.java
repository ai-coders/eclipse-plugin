package net.aicoder.epi.devp.product.dao;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.product.model.product.PrdModuleVo;

public class ModuleDao  extends BaseDao{
	
	public ModuleDao() {
		super();
	}
	
	public List<IBaseVo> listModule(long prdRid){
		List<IBaseVo> list = null;
		list = _listProductByUid(prdRid);
		return list;
	}

	private List<IBaseVo> _listProductByUid(long prdRid){
		List<IBaseVo> list = new ArrayList<IBaseVo>(0);
		PrdModuleVo prd1 = new PrdModuleVo();
		prd1.setRid(100000);
		prd1.setCode("P_C100000");
		prd1.setName("P_N100000");
		list.add(prd1);
		
		PrdModuleVo module11 = new PrdModuleVo();
		module11.setRid(110000);
		module11.setCode("M_C110000");
		module11.setName("M_N110000");
		module11.setParentModule(prd1);
		prd1.getChildrenModuleList().add(module11);

		PrdModuleVo module111 = new PrdModuleVo();
		module111.setRid(111000);
		module111.setCode("M_C111000");
		module111.setName("M_N111000");
		module111.setParentModule(module11);
		module11.getChildrenModuleList().add(module111);

		PrdModuleVo module12 = new PrdModuleVo();
		module12.setRid(120000);
		module12.setCode("M_C120000");
		module12.setName("M_N120000");
		module12.setParentModule(prd1);
		prd1.getChildrenModuleList().add(module12);

		return list;
	}
}
