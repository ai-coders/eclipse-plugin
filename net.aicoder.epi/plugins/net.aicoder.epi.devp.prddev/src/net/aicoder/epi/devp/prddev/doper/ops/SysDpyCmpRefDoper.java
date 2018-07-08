package net.aicoder.epi.devp.prddev.doper.ops;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.devp.prddev.dao.ops.SysDpyCmpRefDao;

/**
 * 部署模型-xxx组件
 * @author WANGQINGPING
 *
 */
public class SysDpyCmpRefDoper extends BaseDoper{
	private SysDpyCmpRefDao sysDpyCmpRefDao;
	
	public SysDpyCmpRefDoper() {
		super();
		this.sysDpyCmpRefDao = new SysDpyCmpRefDao();
	}
	
	
	public EpiInput loadSysDpyCmpRefList(IBaseVo baseVo){
		List<IBaseVo> loadSysDpyCmpRefList = sysDpyCmpRefDao.loadSysDpyCmpRefList(baseVo);
		EpiInput epiInput = new EpiInput();
		epiInput.setDataList(loadSysDpyCmpRefList);
		return epiInput;
	}
	
}
