package net.aicoder.epi.devp.prddev.doper.ops;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.LoadElementState;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.devp.prddev.dao.ops.SysDpyResInstDao;

/**
 * 部署模型-资源应用场景
 * @author WANGQINGPING
 *
 */
public class SysDpyResInstDoper extends BaseDoper{
	private SysDpyResInstDao sysDpyResInstDao;
	public static final String SYS_DPY_RES_INST = "SysDpyResInst";
	
	public SysDpyResInstDoper() {
		super();
		this.sysDpyResInstDao = new SysDpyResInstDao();
	}
	
	/**
	 * 加载资源实例数据
	 * @param baseVo
	 * @return
	 */
	public EpiInput loadSysDypResInstList(IBaseVo baseVo) {
		if(baseVo == null) return null;
		if(baseVo.isLoadedElement(SYS_DPY_RES_INST)) return null;
		
		
		List<IBaseVo> loadSysDypResInstList = sysDpyResInstDao.loadSysDypResInstList(baseVo);
		EpiInput epiInput = new EpiInput();
		epiInput.setDataList(loadSysDypResInstList);
		
		LoadElementState loadElementState = new LoadElementState(SYS_DPY_RES_INST);
		loadElementState.setStartRecNo(0);
		baseVo.putLoadElementState(loadElementState);
		return epiInput;
	}
	
	/**
	 * 过滤资源实例数据
	 * @param baseVo
	 * @return
	 */
	public EpiInput loadSysDypResInstFilterList(IBaseVo baseVo) {
		List<IBaseVo> loadSysDypResInstFilterList = sysDpyResInstDao.loadSysDypResInstList(baseVo);
		EpiInput epiInput = new EpiInput();
		epiInput.setDataList(loadSysDypResInstFilterList);
		return epiInput;
	}
	
}
