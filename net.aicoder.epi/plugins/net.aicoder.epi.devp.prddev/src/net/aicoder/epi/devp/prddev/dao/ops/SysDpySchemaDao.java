package net.aicoder.epi.devp.prddev.dao.ops;

import java.util.ArrayList;
import java.util.List;

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
 * 部署方案
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
		
		
		MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
		request.add("sessionId", "123456789");
		request.add("prd_rid", String.valueOf(pVo.getRid()));
		String resultJson = NetworkHelper.postForObject(NetworkConstant.PRODUCTOPS_SYS_DPY_SCHEMA, request, String.class);
		System.out.println(resultJson);
		ResponseResult<List<SysDpySchemaVo>> resultObj = JSON.parseObject(resultJson,new TypeReference<ResponseResult<List<SysDpySchemaVo>>>(){});
		if(resultObj == null || resultObj.getData() == null || resultObj.getData().size() == 0) return sysDpySchemaVos;
		
		List<SysDpySchemaVo> data = resultObj.getData();
		for (SysDpySchemaVo sysDpySchemaVo : data) {
			sysDpySchemaVos.add(sysDpySchemaVo);
		}
		
		return sysDpySchemaVos;
	}
}
