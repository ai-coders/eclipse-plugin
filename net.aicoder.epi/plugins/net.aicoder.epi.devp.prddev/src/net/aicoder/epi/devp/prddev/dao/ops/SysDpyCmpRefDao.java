package net.aicoder.epi.devp.prddev.dao.ops;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.model.ops.SysCmpVo;
import net.aicoder.epi.devp.prddev.model.ops.SysDpyCmpRefVo;
import net.aicoder.epi.devp.prddev.model.ops.SysDpyResInstVo;
import net.aicoder.epi.devp.prddev.model.ops.SysDpySchemaVo;

/**
 * 部署模块-xxx组件
 * @author WANGQINGPING
 *
 */
public class SysDpyCmpRefDao extends BaseDao {
	
	public SysDpyCmpRefDao() {
		super();
	}
	
	/**
	 * 依据条件加载xxx组件数据
	 * @param baseVo
	 * @return
	 */
	public List<IBaseVo> loadSysDpyCmpRefList(IBaseVo baseVo){
		List<IBaseVo> sysDpyCmpRefList = new ArrayList<>();
		if(baseVo instanceof SysCmpVo) {//系统、子系统、组件
			
			
		}else if (baseVo instanceof SysDpyResInstVo) {//资源应用场景
			
			
		}else if (baseVo instanceof SysDpySchemaVo) {//部署方案
			
			
		}else {
			return sysDpyCmpRefList;
		}
		
		{
			SysDpyCmpRefVo sdcrv = new SysDpyCmpRefVo();
			sdcrv.setCode("Code-1");
			sdcrv.setName("tomcat");
			sdcrv.setAlias("tomcat");
			sdcrv.setDescription("服务器中间件");
			sdcrv.setEtype("安装到tomat");
			sysDpyCmpRefList.add(sdcrv);
		}
		{
			SysDpyCmpRefVo sdcrv = new SysDpyCmpRefVo();
			sdcrv.setCode("Code-2");
			sdcrv.setName("weblogic");
			sdcrv.setAlias("weblogic");
			sdcrv.setDescription("服务器中间件");
			sdcrv.setEtype("安装到weblogic");
			sysDpyCmpRefList.add(sdcrv);
		}
		
		return sysDpyCmpRefList;
	}
}
