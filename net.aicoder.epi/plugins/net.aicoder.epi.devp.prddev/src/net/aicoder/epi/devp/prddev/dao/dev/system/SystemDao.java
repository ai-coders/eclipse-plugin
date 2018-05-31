package net.aicoder.epi.devp.prddev.dao.dev.system;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.devp.prddev.model.dev.funa.FunaModuleVo;

public class SystemDao extends BaseDao {
	public SystemDao() {
		super();
	}
	
	public List<IBaseVo> listModule(long prdRid){
		List<IBaseVo> list = null;
		list = _listModuleByPrdRid(prdRid);
		return list;
	}

	private List<IBaseVo> _listModuleByPrdRid(long prdRid){
		List<IBaseVo> list = new ArrayList<IBaseVo>(0);
		ITreeNode rootNode = null;
		
		FunaModuleVo prd1 = new FunaModuleVo();
		prd1.setRid(100000);
		prd1.setCode("P_C100000");
		prd1.setName("P_N100000");
		prd1.setRootNode(prd1);
		rootNode = prd1;
		prd1.setRootNode(rootNode);
		list.add(prd1);
		
		
		FunaModuleVo module11 = new FunaModuleVo();
		module11.setRid(110000);
		module11.setCode("M_C110000");
		module11.setName("M_N110000");
		module11.setParentNode(prd1);
		module11.setRootNode(rootNode);
		prd1.getChildrenList().add(module11);

		FunaModuleVo module111 = new FunaModuleVo();
		module111.setRid(111000);
		module111.setCode("M_C111000");
		module111.setName("M_N111000");
		module111.setParentNode(module11);
		module111.setRootNode(rootNode);
		module11.getChildrenList().add(module111);

		FunaModuleVo module12 = new FunaModuleVo();
		module12.setRid(120000);
		module12.setCode("M_C120000");
		module12.setName("M_N120000");
		module12.setParentNode(prd1);
		module12.setRootNode(rootNode);
		prd1.getChildrenList().add(module12);

		return list;
	}
}
