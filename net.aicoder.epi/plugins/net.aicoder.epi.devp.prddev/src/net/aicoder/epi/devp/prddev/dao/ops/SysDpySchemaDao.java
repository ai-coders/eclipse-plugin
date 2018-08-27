package net.aicoder.epi.devp.prddev.dao.ops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDpySchemaVo;
import net.aicoder.epi.devp.prddev.model.ops.ResponseResult;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.util.network.NetworkConstant;
import net.aicoder.epi.util.network.NetworkHelper;

/**
 * 运维流水线-部署方案Dao
 * @author WANGQINGPING
 *
 */
public class SysDpySchemaDao extends BaseDao {
	
	public SysDpySchemaDao() {
		super();
	}
	
	public List<IBaseVo> loadSysDpySchemaList(IBaseVo baseVo){
		List<IBaseVo> sysDpySchemaVos = new ArrayList<>();
		if(!(baseVo instanceof PrdProductVo)) return sysDpySchemaVos;
		PrdProductVo pVo = (PrdProductVo)baseVo;
		
		
		Map<String, Object> request = new HashMap<String,Object>();
		request.put("searchCondition", new Object());
		request.put("start", 0);
		request.put("limit", 10);
		
//		String resultJson = NetworkHelper.postForObject(NetworkConstant.SYS_DPY_PARAM_CONF, request, String.class);
//		System.out.println(resultJson);
//		ResponseResult<List<SysDpySchemaVo>> resultObj = JSON.parseObject(resultJson,new TypeReference<ResponseResult<List<SysDpySchemaVo>>>(){});
//		if(resultObj == null || resultObj.getData() == null || resultObj.getData().size() == 0) return sysDpySchemaVos;
		
		
		return sysDpySchemaVos;
	}
}
