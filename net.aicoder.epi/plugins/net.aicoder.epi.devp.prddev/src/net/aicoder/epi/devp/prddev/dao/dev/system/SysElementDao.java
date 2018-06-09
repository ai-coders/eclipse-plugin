package net.aicoder.epi.devp.prddev.dao.dev.system;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.TreeNodeVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElementVo;

public class SysElementDao extends BaseDao {
	public SysElementDao() {
		super();
	}
	
	public List<IBaseVo> listSysDpyElement(long prdRid){
		List<IBaseVo> list = null;
		list = _listSysDpyElement(prdRid);
		return list;
	}

	private List<IBaseVo> _listSysDpyElement(long prdRid){
		List<IBaseVo> list = new ArrayList<IBaseVo>(0);
		TreeNodeVo rootNode = null;
		TreeNodeVo parentNode = null;
		//String elmFlag = EtypeEnum.SYS_CMP.etype();
		long rid = 100100;
		{
			SysElementVo vo = new SysElementVo();
			vo.setRid(++rid);
			vo.setElmFlag(EtypeEnum.SYS_DPY_ENV.etype());
			vo.setCode("ELM_DPY_C" + rid);
			vo.setName("ELM_DPY_N" + rid);
			vo.setRootNode(rootNode);
			vo.setParentNode(parentNode);
			list.add(vo);
			parentNode = vo;
		}
		rid = 100110;
		{
			SysElementVo vo = new SysElementVo();
			vo.setRid(++rid);
			vo.setElmFlag(EtypeEnum.SYS_CMP.etype());
			vo.setCode("ELM_DPY_C" + rid);
			vo.setName("ELM_DPY_N" + rid);
			vo.setRootNode(rootNode);
			vo.setParentNode(parentNode);
			parentNode.getChildrenList().add(vo);
			//list.add(vo);
		}
		rid = 100200;
		parentNode = null;
		{
			SysElementVo vo = new SysElementVo();
			vo.setRid(++rid);
			vo.setElmFlag(EtypeEnum.SYS_RESOURCES.etype());
			vo.setCode("ELM_DPY_C" + rid);
			vo.setName("ELM_DPY_N" + rid);
			vo.setRootNode(rootNode);
			vo.setParentNode(parentNode);
			list.add(vo);
		}
		return list;
	}
}
