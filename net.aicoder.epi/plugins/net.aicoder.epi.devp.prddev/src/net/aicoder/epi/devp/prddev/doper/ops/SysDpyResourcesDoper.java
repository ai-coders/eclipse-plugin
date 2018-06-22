package net.aicoder.epi.devp.prddev.doper.ops;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.devp.prddev.dao.ops.SysDpyResourcesDao;

public class SysDpyResourcesDoper extends BaseDoper {
	private SysDpyResourcesDao sysDpyResourcesDao;

	public SysDpyResourcesDoper() {
		super();
		this.sysDpyResourcesDao = new SysDpyResourcesDao();
	}
	
	/**
	 * 加载当前产品下[关联资源];(关联资源为该产品所涉及的外部产品、及其它系统资源；关联资源类型有：部署到、连接（双向/请求）、调用)
	 * @param baseVo
	 * @return
	 */
	public EpiInput loadSysDpyResourcesList(IBaseVo baseVo) {
		List<IBaseVo> loadSysDpyResourcesList = sysDpyResourcesDao.loadSysDpyResourcesList(baseVo);
		EpiInput epiInput = new EpiInput();
		epiInput.setDataList(loadSysDpyResourcesList);
		return epiInput;
	}
	
	
}
