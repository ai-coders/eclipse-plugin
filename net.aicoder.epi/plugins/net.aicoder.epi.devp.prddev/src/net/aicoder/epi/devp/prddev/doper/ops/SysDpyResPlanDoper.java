package net.aicoder.epi.devp.prddev.doper.ops;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.devp.prddev.dao.ops.SysDpyResPlanDao;

/**
 * 部署模型-资源规划 Doper
 * @author WANGQINGPING
 *
 */
public class SysDpyResPlanDoper extends BaseDoper{
	private SysDpyResPlanDao dao;
	
	public SysDpyResPlanDoper() {
		super();
		this.dao = new SysDpyResPlanDao();
	}
	
	/**
	 * 加载资源规划数据
	 * @param baseVo
	 * @return
	 */
	public IEpiInput loadSysDpyResPlanList(IBaseVo baseVo){
		List<IBaseVo> sysDpyResPlanList = null;
		sysDpyResPlanList = dao.loadSysDpyResPlanList(baseVo);
		
		IEpiInput epiInput = new EpiInput();
		epiInput.setDataList(sysDpyResPlanList);
		return epiInput;
	}
	
	
	
}
