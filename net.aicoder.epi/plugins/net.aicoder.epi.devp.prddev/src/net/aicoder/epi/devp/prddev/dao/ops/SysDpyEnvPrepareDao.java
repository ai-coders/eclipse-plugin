package net.aicoder.epi.devp.prddev.dao.ops;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;

/**
 * 运维流水线-环境准备Dao
 * @author WANGQINGPING
 *
 */
public class SysDpyEnvPrepareDao extends BaseDao {
	
	public SysDpyEnvPrepareDao() {
		super();
	}
	
	/**
	 * 加载部署方案-参数配置数据
	 * @param basevo
	 * @return
	 */
	public List<IBaseVo> loadSysDpyParamConfList(IBaseVo basevo){
		List<IBaseVo> sysDpyEnvPrepareList = new ArrayList<IBaseVo>();			
		
		
		
		return sysDpyEnvPrepareList;
	}
	
	/**
	 * 加载部署方案-主机节点数据
	 * @param basevo
	 * @return
	 */
	public List<IBaseVo> loadSysDpyHostNodeList(IBaseVo basevo){
		List<IBaseVo> sysDpyHostNodeList = new ArrayList<IBaseVo>();			
		
		
		
		return sysDpyHostNodeList;
	}
	
	/**
	 * 加载部署方案-资源配置数据
	 * @param basevo
	 * @return
	 */
	public List<IBaseVo> loadSysDpyResConfList(IBaseVo basevo){
		List<IBaseVo> sysDpyResConf = new ArrayList<IBaseVo>();			
		
		
		
		return sysDpyResConf;
	}
	
	

}
