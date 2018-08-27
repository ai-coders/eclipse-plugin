package net.aicoder.epi.devp.prddev.dao.ops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDiagramVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElementVo;
import net.aicoder.epi.devp.prddev.model.ops.ProductOpsVo;
import net.aicoder.epi.devp.prddev.model.ops.ResponseContent;
import net.aicoder.epi.devp.prddev.model.ops.ResponseResult;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.util.network.NetworkConstant;
import net.aicoder.epi.util.network.NetworkHelper;

/**
 * 产品发布导航 Dao
 * @author WANGQINGPING
 *
 */
public class ProductOpsDao extends BaseDao {

	public ProductOpsDao() {
		super();
	}
	
	
	//加载产品分组数据
	public List<IBaseVo> loadProductGroupList(IBaseVo baseVo){
		List<IBaseVo> produtOpsList = new ArrayList<>();
		
		Map<String, Object> request = new HashMap<String,Object>();
		request.put("searchCondition", new Object());
		request.put("start", 0);
		request.put("limit", 10);
		String resultJson = NetworkHelper.postForObject(NetworkConstant.SYS_DPY_OPS, request, String.class);
		System.out.println(resultJson);
		ResponseResult<ResponseContent<List<ProductOpsVo>>> resultObj = JSON.parseObject(resultJson,new TypeReference<ResponseResult<ResponseContent<List<ProductOpsVo>>>>(){});
		if(resultObj == null || resultObj.getData() == null || resultObj.getData().getContent() == null || resultObj.getData().getContent().size() == 0) return produtOpsList;
		List<ProductOpsVo> prdGroups = resultObj.getData().getContent();
		for (ProductOpsVo pov : prdGroups) {
			pov.setEtype(EtypeEnum.PRD_GROUP.etype());
			List<PrdProductVo> productList = pov.getProductList();
			for (PrdProductVo ppv : productList) { ppv.setEtype(EtypeEnum.PRODUCT.etype());}
			pov.getChildrenList().addAll(productList);
			produtOpsList.add(pov);
		}
		return produtOpsList;
	}
	
	
	//获取设计图
	public List<IBaseVo> loadProductOpsFolderDesDgmList(IBaseVo baseVo){
		List<IBaseVo> list = new  ArrayList<IBaseVo>(0);
		long rid = 100000;
		{
			SysDiagramVo vo = new SysDiagramVo();
			vo.setRid(++rid);
			vo.setCode("ELM_DGM_C" + rid);
			vo.setName("【DPY】部署规划图");
			vo.setDgmFlag(EtypeEnum.SYS_CMP_DGM.etype());
			vo.setSysElement((SysElementVo)baseVo);
			list.add(vo);
		}
		{
			SysDiagramVo vo = new SysDiagramVo();
			vo.setRid(++rid);
			vo.setCode("ELM_DGM_C" + rid);
			vo.setName("【DPY】部署图-开发");
			vo.setDgmFlag(EtypeEnum.SYS_CMP_DGM.etype());
			vo.setSysElement((SysElementVo)baseVo);
			list.add(vo);
		}
		{
			SysDiagramVo vo = new SysDiagramVo();
			vo.setRid(++rid);
			vo.setCode("ELM_DGM_C" + rid);
			vo.setName("【DPY】部署图-测试1");
			vo.setDgmFlag(EtypeEnum.SYS_CMP_DGM.etype());
			vo.setSysElement((SysElementVo)baseVo);
			list.add(vo);
		}
		{
			SysDiagramVo vo = new SysDiagramVo();
			vo.setRid(++rid);
			vo.setCode("ELM_DGM_C" + rid);
			vo.setName("【DPY】部署图-验证");
			vo.setDgmFlag(EtypeEnum.SYS_CMP_DGM.etype());
			vo.setSysElement((SysElementVo)baseVo);
			list.add(vo);
		}
		{
			SysDiagramVo vo = new SysDiagramVo();
			vo.setRid(++rid);
			vo.setCode("ELM_DGM_C" + rid);
			vo.setName("【DPY】部署图-生产1");
			vo.setDgmFlag(EtypeEnum.SYS_CMP_DGM.etype());
			vo.setSysElement((SysElementVo)baseVo);
			list.add(vo);
		}
		return list;
	}
	
	//获取子元素
	public List<IBaseVo> loadProductOpsFolderSubElmList(IBaseVo baseVo){
		List<IBaseVo> list = new  ArrayList<IBaseVo>(0);
		long rid = 100000;
		{
			SysDiagramVo vo = new SysDiagramVo();
			vo.setRid(++rid);
			vo.setCode("ELM_DSE_C" + rid);
			vo.setName("XXX环境");
			vo.setDgmFlag(EtypeEnum.SYS_CMP_DGM.etype());
			vo.setSysElement((SysElementVo)baseVo);
			list.add(vo);
		}
		{
			SysDiagramVo vo = new SysDiagramVo();
			vo.setRid(++rid);
			vo.setCode("ELM_DSE_C" + rid);
			vo.setName("XXX节点");
			vo.setDgmFlag(EtypeEnum.SYS_CMP_DGM.etype());
			vo.setSysElement((SysElementVo)baseVo);
			list.add(vo);
		}
		{
			SysDiagramVo vo = new SysDiagramVo();
			vo.setRid(++rid);
			vo.setCode("ELM_DSE_C" + rid);
			vo.setName("XXX组件");
			vo.setDgmFlag(EtypeEnum.SYS_CMP_DGM.etype());
			vo.setSysElement((SysElementVo)baseVo);
			list.add(vo);
		}
		{
			SysDiagramVo vo = new SysDiagramVo();
			vo.setRid(++rid);
			vo.setCode("ELM_DSE_C" + rid);
			vo.setName("XXX资源");
			vo.setDgmFlag(EtypeEnum.SYS_CMP_DGM.etype());
			vo.setSysElement((SysElementVo)baseVo);
			list.add(vo);
		}
		return list;
	}
	
	
	
}
