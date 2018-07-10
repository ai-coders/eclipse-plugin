package net.aicoder.epi.devp.prddev.dao.ops;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDpyResourcesVo;
import net.aicoder.epi.devp.prddev.model.ops.ResponseResult;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.util.network.NetworkConstant;
import net.aicoder.epi.util.network.NetworkHelper;
import net.aicoder.tcom.tools.util.BeanUtil;

public class SysDpyResourcesDao extends BaseDao{

	public SysDpyResourcesDao() {
		super();
	}
	
	public List<IBaseVo> loadSysDpyResourcesList(IBaseVo baseVo){
		List<IBaseVo> sysDpyResourcesList = new ArrayList<>();
		if(!(baseVo instanceof PrdProductVo)) return sysDpyResourcesList;
		PrdProductVo pVo = (PrdProductVo)baseVo;
		
		//前提条件：只查询installable为1的组件
		MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
		request.add("sessionId", "123456789");
		request.add("prd_rid", String.valueOf(pVo.getRid()));
		String resultJson = NetworkHelper.postForObject(NetworkConstant.PRODUCTOPS_SYS_DPY_RESOURCES, request, String.class);
		System.out.println(resultJson);
		ResponseResult<List<SysDpyResourcesVo>> resultObj = JSON.parseObject(resultJson,new TypeReference<ResponseResult<List<SysDpyResourcesVo>>>(){});
		if(resultObj == null || resultObj.getData() == null || resultObj.getData().size() == 0) return sysDpyResourcesList;
		
		
		
		//关联类型：部署到、连接、调用
		//连接时(子类型)：双向[-o)-]，请求[-)],提供[-o]
		{
			SysDpyResourcesVo sdrv = new SysDpyResourcesVo();
			BeanUtil.copyBeanToBean(sdrv, resultObj.getData().get(0));
			sysDpyResourcesList.add(sdrv);
		}
		{
			SysDpyResourcesVo sdrv = new SysDpyResourcesVo();
			BeanUtil.copyBeanToBean(sdrv, resultObj.getData().get(1));
			sysDpyResourcesList.add(sdrv);
		}
		
		return sysDpyResourcesList;
	}
	
}
