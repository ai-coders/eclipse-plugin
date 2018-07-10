package net.aicoder.epi.devp.prddev.dao.ops;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDpyResInstVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDpyResourcesVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDpySchemaVo;
import net.aicoder.epi.util.network.NetworkConstant;
import net.aicoder.epi.util.network.NetworkHelper;

public class SysDpyResInstDao extends BaseDao{

	public SysDpyResInstDao() {
		super();
	}
	
	public List<IBaseVo> loadSysDypResInstList(IBaseVo baseVo){
		List<IBaseVo> sysDpyResInstList = new ArrayList<>();
		
		MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
		if(baseVo instanceof SysDpyResourcesVo) {
			SysDpyResourcesVo sdrv = (SysDpyResourcesVo) baseVo;
			request.add("sessionId", "123456789");
			request.add("res_id", String.valueOf(sdrv.getRid()));
			request.add("prd_rid", String.valueOf(sdrv.getPrdRid()));
		}else if(baseVo instanceof SysDpySchemaVo) {
			SysDpySchemaVo sdsv = (SysDpySchemaVo) baseVo;
			request.add("sessionId", "123456789");
			request.add("scheme_rid", String.valueOf(sdsv.getRid()));
			request.add("prd_rid", String.valueOf(sdsv.getPrdRid()));
		}

		
		String resultJson = NetworkHelper.postForObject(NetworkConstant.PRODUCTOPS_SYS_DPY_RES_INST, request, String.class);
		System.out.println(resultJson);
		
//		ResponseResult<List<SysDpyResInstVo>> resultObj = JSON.parseObject(resultJson,new TypeReference<ResponseResult<List<SysDpyResInstVo>>>(){});
//		if(resultObj == null || resultObj.getData() == null || resultObj.getData().size() == 0) return sysDpyResInstList;													
		{
			SysDpyResInstVo sdriv = new SysDpyResInstVo();
			sdriv.setName("主机1");
			sdriv.setCode("SysDpyResInstVo_Code_1");
			sysDpyResInstList.add(sdriv);
			
			SysDpyResInstVo sdrivSub = new SysDpyResInstVo();
			sdrivSub.setName("Tomcat1");
			sdrivSub.setCode("Sub_Code_1");
			sdriv.getChildrenList().add(sdrivSub);
		}
		
		{
			SysDpyResInstVo sdriv = new SysDpyResInstVo();
			sdriv.setName("主机2");
			sdriv.setCode("SysDpyResInstVo_Code_2");
			sysDpyResInstList.add(sdriv);
			
			SysDpyResInstVo sdrivSub = new SysDpyResInstVo();
			sdrivSub.setName("Tomcat2");
			sdrivSub.setCode("Sub_Code_2");
			sdriv.getChildrenList().add(sdrivSub);
		}
		
		{
			SysDpyResInstVo sdriv = new SysDpyResInstVo();
			sdriv.setName("主机3");
			sdriv.setCode("SysDpyResInstVo_Code_3");
			sysDpyResInstList.add(sdriv);
			
			SysDpyResInstVo sdrivSub = new SysDpyResInstVo();
			sdrivSub.setName("Tomcat3");
			sdrivSub.setCode("Sub_Code_3");
			sdriv.getChildrenList().add(sdrivSub);
		}
		
		return sysDpyResInstList;
	}
	
	
	public List<IBaseVo> loadSysDypResInstFilterList(IBaseVo baseVo) {
		List<IBaseVo> sysDpyResInstFilterList = new ArrayList<>();
		
		
		
		
		return sysDpyResInstFilterList;
	}
	
}
