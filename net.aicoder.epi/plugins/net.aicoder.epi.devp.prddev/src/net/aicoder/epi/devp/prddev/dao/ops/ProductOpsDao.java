package net.aicoder.epi.devp.prddev.dao.ops;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDiagramVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElementVo;
import net.aicoder.epi.devp.prddev.model.ops.ProductOpsVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

/**
 * 产品发布导航 Dao
 * @author WANGQINGPING
 *
 */
public class ProductOpsDao extends BaseDao {

	public ProductOpsDao() {
		super();
	}
	
	
	//获取产品分组内产品
	public List<IBaseVo> loadProductGroupList(IBaseVo baseVo){
		List<IBaseVo> produtOpsList = new ArrayList<>();
		{
			//产品分组1
			ProductOpsVo prdGroupVo1 = new ProductOpsVo();
			prdGroupVo1.setRid(100);
			prdGroupVo1.setTid(1);
			prdGroupVo1.setCode("Prd_Group_Code_1");
			prdGroupVo1.setName("信息部产品分组");
			prdGroupVo1.setEtype(EtypeEnum.PRD_GROUP.etype());
			produtOpsList.add(prdGroupVo1);
			{
				//产品分组1-产品一
				PrdProductVo prd = new PrdProductVo();
				prd.setRid(10);
				prd.setTid(1);
				prd.setCode("Product_Code_11");
				prd.setName("仪器解析产品");
				prd.setEtype("ASSET_BIZ_SW");
				prdGroupVo1.getChildrenList().add(prd);
			}
			{
				//产品分组1-产品二			
				PrdProductVo prd = new PrdProductVo();
				prd.setRid(11);
				prd.setTid(1);
				prd.setCode("Product_Code_12");
				prd.setName("好医生产品");
				prd.setEtype("ASSET_BIZ_SW");
				prdGroupVo1.getChildrenList().add(prd);
			}
			{
				//产品分组1-产品三
				PrdProductVo prd = new PrdProductVo();
				prd.setRid(12);
				prd.setTid(1);
				prd.setCode("Product_Code_13");
				prd.setName("记事本产品");
				prd.setEtype("ASSET_BIZ_SW");
				prdGroupVo1.getChildrenList().add(prd);
			}
		}
		
		{
			//产品分组2
			ProductOpsVo prdGroupVo2 = new ProductOpsVo();
			prdGroupVo2.setRid(101);
			prdGroupVo2.setTid(1);
			prdGroupVo2.setCode("Prd_Group_Code_2");
			prdGroupVo2.setName("实验室产品分组");
			prdGroupVo2.setEtype(EtypeEnum.PRD_GROUP.etype());
			produtOpsList.add(prdGroupVo2);
			{
				//产品分组2-产品一
				PrdProductVo prd = new PrdProductVo();
				prd.setRid(13);
				prd.setTid(1);
				prd.setCode("Product_Code_21");
				prd.setName("微生物产品");
				prd.setEtype("ASSET_BIZ_SW");
				prdGroupVo2.getChildrenList().add(prd);
			}
			{
				//产品分组2-产品二
				PrdProductVo prd = new PrdProductVo();
				prd.setRid(14);
				prd.setTid(1);
				prd.setCode("Product_Code_22");
				prd.setName("高血压产品");
				prd.setEtype("ASSET_BIZ_SW");
				prdGroupVo2.getChildrenList().add(prd);
			}
			{
				//产品分组2-产品三
				PrdProductVo prd = new PrdProductVo();
				prd.setRid(15);
				prd.setTid(1);
				prd.setCode("Product_Code_23");
				prd.setName("血常规产品");
				prd.setEtype("ASSET_BIZ_SW");
				prdGroupVo2.getChildrenList().add(prd);
			}
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
