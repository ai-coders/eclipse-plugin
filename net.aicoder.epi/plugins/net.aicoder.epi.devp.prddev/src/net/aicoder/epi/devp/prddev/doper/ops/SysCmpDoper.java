package net.aicoder.epi.devp.prddev.doper.ops;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.devp.prddev.dao.ops.SysCmpDao;

/**
 * 部署模型-系统组件
 * @author WANGQINGPING
 *
 */
public class SysCmpDoper extends BaseDoper{
	private SysCmpDao sysCmpDao;
	public static final String SYS_CMP = "SysCmp";

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
		if(baseVo == null) return null;
//		if(baseVo.isLoadedElement(SYS_CMP)) return null;
		
		List<IBaseVo> loadSysCmpList = sysCmpDao.loadSysCmpList(baseVo);
		EpiInput epiInput = new EpiInput();
		epiInput.setDataList(loadSysCmpList);
		
//		LoadElementState loadElementState = new LoadElementState(SYS_CMP);
//		loadElementState.setStartRecNo(0);
//		baseVo.putLoadElementState(loadElementState);
		return epiInput;
	}
	
	/**
	 * 加载当前产品下过滤可用[系统、子系统、组件]
	 * @param baseVo
	 * @return
	 */
	public EpiInput loadSysCmpFilterList(IBaseVo baseVo) {
		List<IBaseVo> loadSysCmpFilterList = sysCmpDao.loadSysCmpFilterList(baseVo);
		EpiInput epiInput = new EpiInput();
		epiInput.setDataList(loadSysCmpFilterList);
		return epiInput;
	}
	

}
