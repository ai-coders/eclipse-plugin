package net.aicoder.epi.devp.prddev.doper.ops;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.devp.prddev.dao.ops.SysDpyEnvPrepareDao;

/**
 * 运维流水线-环境准备Doper
 * @author WANGQINGPING
 *
 */
public class SysDpyEnvPrepareDoper extends BaseDoper {
	private SysDpyEnvPrepareDao dao;
	
	public SysDpyEnvPrepareDoper() {
		super();
		this.dao = new SysDpyEnvPrepareDao();
	}
	
	
	/**
	 * 加载部署方案-参数配置数据
	 * @param basevo
	 * @return
	 */
	public IEpiInput loadSysDpyParamConfList(IBaseVo basevo){
		List<IBaseVo> sysDpyEnvPrepareList = dao.loadSysDpyParamConfList(basevo);
		IEpiInput epiInput = new EpiInput();
		epiInput.setDataList(sysDpyEnvPrepareList);
		return epiInput;
	}
	
	/**
	 * 加载部署方案-主机节点数据
	 * @param basevo
	 * @return
	 */
	public IEpiInput loadSysDpyHostNodeList(IBaseVo basevo){
		List<IBaseVo> sysDpyHostNodeList = dao.loadSysDpyHostNodeList(basevo);
		IEpiInput epiInput = new EpiInput();
		epiInput.setDataList(sysDpyHostNodeList);
		return epiInput;
	}
	
	/**
	 * 加载部署方案-资源配置数据
	 * @param basevo
	 * @return
	 */
	public IEpiInput loadSysDpyResConfList(IBaseVo basevo){
		List<IBaseVo> sysDpyResConf = dao.loadSysDpyResConfList(basevo);			
		IEpiInput epiInput = new EpiInput();
		epiInput.setDataList(sysDpyResConf);
		return epiInput;
	}

}
