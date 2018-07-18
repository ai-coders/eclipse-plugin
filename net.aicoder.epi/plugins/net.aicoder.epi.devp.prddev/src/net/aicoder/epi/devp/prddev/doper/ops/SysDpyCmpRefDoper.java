package net.aicoder.epi.devp.prddev.doper.ops;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.LoadElementState;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.devp.prddev.dao.ops.SysDpyCmpRefDao;

/**
 * 部署模型-xxx组件
 * @author WANGQINGPING
 *
 */
public class SysDpyCmpRefDoper extends BaseDoper{
	private SysDpyCmpRefDao sysDpyCmpRefDao;
	public static final String SYS_DPY_CMP_REF = "SysDpyCmpRef";
	
	public SysDpyCmpRefDoper() {
		super();
		this.sysDpyCmpRefDao = new SysDpyCmpRefDao();
	}
	
	
	public EpiInput loadSysDpyCmpRefList(IBaseVo baseVo){
		if(baseVo == null) return null;
		if(baseVo.isLoadedElement(SYS_DPY_CMP_REF)) return null;
		
		List<IBaseVo> loadSysDpyCmpRefList = sysDpyCmpRefDao.loadSysDpyCmpRefList(baseVo);
		EpiInput epiInput = new EpiInput();
		epiInput.setDataList(loadSysDpyCmpRefList);
		
		LoadElementState loadElementState = new LoadElementState(SYS_DPY_CMP_REF);
		loadElementState.setStartRecNo(0);
		baseVo.putLoadElementState(loadElementState);
		return epiInput;
	}
	
}
