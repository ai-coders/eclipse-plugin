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
import net.aicoder.epi.devp.prddev.model.dev.system.SysCmpVo;
import net.aicoder.epi.devp.prddev.model.ops.ResponseContent;
import net.aicoder.epi.devp.prddev.model.ops.ResponseResult;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.util.network.NetworkConstant;
import net.aicoder.epi.util.network.NetworkHelper;
import net.aicoder.tcom.tools.util.BeanUtil;

/**
 *产品-系统组件 Dao
 * @author WANGQINGPING
 *
 */
public class SysCmpDao extends BaseDao{

	public SysCmpDao() {
		super();
	}

	public List<IBaseVo> loadSysCmpList(IBaseVo baseVo){
		List<IBaseVo> sysCmpList = new ArrayList<>();
		if(!(baseVo instanceof PrdProductVo)) return sysCmpList;
		PrdProductVo pVo = (PrdProductVo)baseVo;
		SysCmpVo scvo = new SysCmpVo();
		BeanUtil.copyBeanToBean(scvo, pVo);
		scvo.setInstallable(1);

		//前提条件：只查询installable为1的组件
		Map<String, Object> request = new HashMap<String,Object>();
		request.put("searchCondition", scvo);
		request.put("start", 0);
		request.put("limit", 10);
		String resultJson = NetworkHelper.postForObject(NetworkConstant.SYS_DPY_CMP, request, String.class);
		System.out.println(resultJson);
		ResponseResult<ResponseContent<List<SysCmpVo>>> resultObj = JSON.parseObject(resultJson,new TypeReference<ResponseResult<ResponseContent<List<SysCmpVo>>>>(){});
		if(resultObj == null || resultObj.getData() == null || resultObj.getData().getContent() == null || resultObj.getData().getContent().size() == 0) return sysCmpList;
		List<SysCmpVo> sysCmps = resultObj.getData().getContent();
		for (SysCmpVo scv : sysCmps) {
			List<SysCmpVo> devpSysCmpList = scv.getDevpSysCmpList();
			scv.getChildrenList().addAll(devpSysCmpList);
			sysCmpList.add(scv);
		}
		
		return sysCmpList;
	}
	
	
	/**
	 * 加载当前产品下可用[系统、子系统、组件]
	 * @param baseVo
	 * @return
	 */
	public List<IBaseVo> loadSysCmpFilterList(IBaseVo baseVo) {
		List<IBaseVo> sysCmpFilterList = new ArrayList<>();
		if(baseVo == null || !(baseVo instanceof SysCmpVo)) return sysCmpFilterList;
		SysCmpVo filter = (SysCmpVo) baseVo;
		
		//前提条件：只查询installable为1的组件
		
		List<IBaseVo> sysCmpList = new ArrayList<>();
		MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
		request.add("sessionId", "123456789");
		request.add("prd_rid", String.valueOf(filter.getPrdRid()));
		String resultJson = NetworkHelper.postForObject(NetworkConstant.SYS_DPY_CMP, request, String.class);
		System.out.println(resultJson);
		ResponseResult<List<SysCmpVo>> resultObj = JSON.parseObject(resultJson,new TypeReference<ResponseResult<List<SysCmpVo>>>(){});
		if(resultObj == null || resultObj.getData() == null || resultObj.getData().size() == 0) return sysCmpFilterList;
		{
			SysCmpVo scv = resultObj.getData().get(0);
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
		
		for (IBaseVo ibo : sysCmpList) {
			if(ibo.getName().equals(baseVo.getName())) {
				sysCmpFilterList.add(ibo);
			}
		}
		
		return sysCmpFilterList;
	}
}
