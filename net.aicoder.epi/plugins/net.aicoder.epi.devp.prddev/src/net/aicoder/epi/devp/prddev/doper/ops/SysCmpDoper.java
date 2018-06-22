package net.aicoder.epi.devp.prddev.doper.ops;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.devp.prddev.dao.ops.SysCmpDao;

public class SysCmpDoper extends BaseDoper{
	private SysCmpDao sysCmpDao;

	public SysCmpDoper() {
		super();
		this.sysCmpDao = new SysCmpDao();
	}
	
	/**
	 * 加载当前产品下可用[系统、子系统、组件]
	 * @param baseVo
	 * @return
	 */
	public EpiInput loadSysCmpList(IBaseVo baseVo) {
		List<IBaseVo> loadSysCmpList = sysCmpDao.loadSysCmpList(baseVo);
		EpiInput epiInput = new EpiInput();
		epiInput.setDataList(loadSysCmpList);
		return epiInput;
	}
	

}
