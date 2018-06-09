package net.aicoder.epi.devp.prddev.doper.dev.system;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.dao.dev.system.SysElementDao;
import net.aicoder.epi.devp.prddev.model.dev.ProductDevVo;

public class SysElementDoper extends BaseDoper {
	private SysElementDao sysElementDao;
	
	public SysElementDoper() {
		super();
		sysElementDao = new SysElementDao();
	}
	
	public List<IBaseVo> listSysDpyElement(ProductDevVo product){
		List<IBaseVo> list = null;
		long prdRid = product.getRid();
		list = sysElementDao.listSysDpyElement(prdRid);
		return list;
	}
}
