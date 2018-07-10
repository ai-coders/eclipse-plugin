package net.aicoder.epi.devp.prddev.dao.ops;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.model.ops.OpsAssetCmdbVo;
import net.aicoder.epi.devp.prddev.model.ops.ResponseResult;
import net.aicoder.epi.util.network.NetworkConstant;
import net.aicoder.epi.util.network.NetworkHelper;

/**
 * IT资产Dao
 * @author WANGQINGPING
 *
 */
public class OpsAssetCmdbDao extends BaseDao {
	
	public OpsAssetCmdbDao() {
		super();
	}
	
	/**
	 * 加载IT资产数据
	 * @param baseVo
	 * @return
	 */
	public List<IBaseVo> loadOpsAssetCmdbList(IBaseVo baseVo){
		List<IBaseVo> results = new ArrayList<>();
		MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
		request.add("sessionId", "123456789");
		request.add("etype", "ASSET_BIZ_SW");
		String resultJson = NetworkHelper.postForObject(NetworkConstant.PRODUCTOPS_OPS_ASSET_CMDB, request, String.class);
		System.out.println(resultJson);
		ResponseResult<List<OpsAssetCmdbVo>> resultObj = JSON.parseObject(resultJson,new TypeReference<ResponseResult<List<OpsAssetCmdbVo>>>(){});
		if(resultObj == null || resultObj.getData() == null || resultObj.getData().size() == 0) return results;
		
		for (IBaseVo iBaseVo : resultObj.getData()) {
			results.add(iBaseVo);
		}
		
		return results;
	}
}
