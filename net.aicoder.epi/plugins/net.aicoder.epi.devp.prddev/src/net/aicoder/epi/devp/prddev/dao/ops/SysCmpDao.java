package net.aicoder.epi.devp.prddev.dao.ops;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.model.ops.ResponseResult;
import net.aicoder.epi.devp.prddev.model.ops.SysCmpVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.util.network.NetworkConstant;
import net.aicoder.epi.util.network.NetworkHelper;
import net.aicoder.tcom.tools.util.BeanUtil;

public class SysCmpDao extends BaseDao{

	public SysCmpDao() {
		super();
	}

	public List<IBaseVo> loadSysCmpList(IBaseVo baseVo){
		List<IBaseVo> sysCmpList = new ArrayList<>();
		if(!(baseVo instanceof PrdProductVo)) return sysCmpList;
		PrdProductVo pVo = (PrdProductVo)baseVo;
		
		
		//前提条件：只查询installable为1的组件
		MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
		request.add("sessionId", "123456789");
		request.add("prd_rid", String.valueOf(pVo.getRid()));
		String resultJson = NetworkHelper.postForObject(NetworkConstant.PRODUCTOPS_SYS_CMP, request, String.class);
		System.out.println(resultJson);
		ResponseResult<List<SysCmpVo>> resultObj = JSON.parseObject(resultJson,new TypeReference<ResponseResult<List<SysCmpVo>>>(){});
		if(resultObj == null || resultObj.getData() == null || resultObj.getData().size() == 0) return sysCmpList;
		
		
		{
			SysCmpVo scv = new SysCmpVo();
			BeanUtil.copyBeanToBean(scv, resultObj.getData().get(0));
			sysCmpList.add(scv);
			{
				SysCmpVo scv_sub = new SysCmpVo();
				BeanUtil.copyBeanToBean(scv_sub, resultObj.getData().get(1));
				scv_sub.setParentNode(scv);
				scv.getChildrenList().add(scv_sub);
				{
					SysCmpVo scv_cmp = new SysCmpVo();
					BeanUtil.copyBeanToBean(scv_cmp, resultObj.getData().get(2));
					scv_cmp.setParentNode(scv_sub);
					scv_sub.getChildrenList().add(scv_cmp);
				}
			}
		}

		
		return sysCmpList;
	}
}
