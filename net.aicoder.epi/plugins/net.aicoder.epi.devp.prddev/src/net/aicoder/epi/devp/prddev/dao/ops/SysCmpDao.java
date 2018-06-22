package net.aicoder.epi.devp.prddev.dao.ops;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.model.ops.SysCmpVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

public class SysCmpDao extends BaseDao{

	public SysCmpDao() {
		super();
	}

	public List<IBaseVo> loadSysCmpList(IBaseVo baseVo){
		List<IBaseVo> sysCmpList = new ArrayList<>();
		if(!(baseVo instanceof PrdProductVo)) return sysCmpList;
		
		PrdProductVo pVo = (PrdProductVo)baseVo;
		
		//前提条件：只查询installable为1的组件
		{
			//LIS系统
			SysCmpVo sysCmpVo = new SysCmpVo();
			sysCmpVo.setName(pVo.getName()+"LIS系统");
			sysCmpVo.setCode("LIS_Sys_Code_1");
			sysCmpVo.setAlias("LIS系统");
			sysCmpVo.setType("系统");
			sysCmpVo.setVersion("V1.0.0");
			sysCmpVo.setDescription("描述部分略");
			sysCmpList.add(sysCmpVo);
			
			{
				//LIS前端
				SysCmpVo sysCmpVoFore = new SysCmpVo();
				sysCmpVoFore.setName("LIS前端");
				sysCmpVoFore.setCode("LIS_Sys_Code_11");
				sysCmpVoFore.setAlias("LIS前端");
				sysCmpVoFore.setType("子系统");
				sysCmpVoFore.setVersion("V1.0.0");
				sysCmpVoFore.setDescription("描述部分略");
				sysCmpVo.getChildrenList().add(sysCmpVoFore);
				
				//组件
				SysCmpVo sysCmpVoSub = new SysCmpVo();
				sysCmpVoSub.setName("LIS组件");
				sysCmpVoSub.setCode("LIS_Sys_Code_111");
				sysCmpVoSub.setAlias("LIS组件");
				sysCmpVoSub.setType("组件");
				sysCmpVoSub.setVersion("V1.0.0");
				sysCmpVoSub.setDescription("描述部分略");
				sysCmpVoFore.getChildrenList().add(sysCmpVoSub);
			}
			{
				//LIS后端
				SysCmpVo sysCmpVoBack = new SysCmpVo();
				sysCmpVoBack.setName("LIS后端");
				sysCmpVoBack.setCode("LIS_Sys_Code_12");
				sysCmpVoBack.setAlias("LIS后端");
				sysCmpVoBack.setType("子系统");
				sysCmpVoBack.setVersion("V1.0.0");
				sysCmpVoBack.setDescription("描述部分略");
				sysCmpVo.getChildrenList().add(sysCmpVoBack);
				
				//组件
				SysCmpVo sysCmpVoSub = new SysCmpVo();
				sysCmpVoSub.setName("LIS后端组件");
				sysCmpVoSub.setCode("LIS_Sys_Code_122");
				sysCmpVoSub.setAlias("LIS后端组件");
				sysCmpVoSub.setType("组件");
				sysCmpVoSub.setVersion("V1.0.0");
				sysCmpVoSub.setDescription("描述部分略");
				sysCmpVoBack.getChildrenList().add(sysCmpVoSub);
				
				//组件
				SysCmpVo sysCmpVoSub2 = new SysCmpVo();
				sysCmpVoSub2.setName("LIS测试组件");
				sysCmpVoSub2.setCode("LIS_Sys_Code_123");
				sysCmpVoSub2.setAlias("LIS测试组件");
				sysCmpVoSub2.setType("组件");
				sysCmpVoSub2.setVersion("V1.0.0");
				sysCmpVoSub2.setDescription("描述部分略");
				sysCmpVoBack.getChildrenList().add(sysCmpVoSub2);
			}
		}
		{
			//NotePad系统
			SysCmpVo sysCmpVo = new SysCmpVo();
			sysCmpVo.setName(pVo.getName()+"NotePad系统");
			sysCmpVo.setCode("LIS_Sys_Code_2");
			sysCmpVo.setAlias("NotePad系统");
			sysCmpVo.setType("系统");
			sysCmpVo.setVersion("V1.0.0");
			sysCmpVo.setDescription("描述部分略");
			sysCmpList.add(sysCmpVo);
			
			{
				//NotePad前端
				SysCmpVo sysCmpVoFore = new SysCmpVo();
				sysCmpVoFore.setName("NotePad前端");
				sysCmpVoFore.setCode("LIS_Sys_Code_21");
				sysCmpVoFore.setAlias("NotePad前端");
				sysCmpVoFore.setType("子系统");
				sysCmpVoFore.setVersion("V1.0.0");
				sysCmpVoFore.setDescription("描述部分略");
				sysCmpVo.getChildrenList().add(sysCmpVoFore);
				
				//组件
				SysCmpVo sysCmpVoSub = new SysCmpVo();
				sysCmpVoSub.setName("NotePad组件");
				sysCmpVoSub.setCode("LIS_Sys_Code_211");
				sysCmpVoSub.setAlias("NotePad组件");
				sysCmpVoSub.setType("组件");
				sysCmpVoSub.setVersion("V1.0.0");
				sysCmpVoSub.setDescription("描述部分略");
				sysCmpVoFore.getChildrenList().add(sysCmpVoSub);
			}
			{
				//NotePad后端
				SysCmpVo sysCmpVoBack = new SysCmpVo();
				sysCmpVoBack.setName("NotePad后端");
				sysCmpVoBack.setCode("LIS_Sys_Code_22");
				sysCmpVoBack.setAlias("NotePad后端");
				sysCmpVoBack.setType("子系统");
				sysCmpVoBack.setVersion("V1.0.0");
				sysCmpVoBack.setDescription("描述部分略");
				sysCmpVo.getChildrenList().add(sysCmpVoBack);
				
				//组件
				SysCmpVo sysCmpVoSub = new SysCmpVo();
				sysCmpVoSub.setName("NotePad后端组件");
				sysCmpVoSub.setCode("LIS_Sys_Code_222");
				sysCmpVoSub.setAlias("NotePad后端组件");
				sysCmpVoSub.setType("组件");
				sysCmpVoSub.setVersion("V1.0.0");
				sysCmpVoSub.setDescription("描述部分略");
				sysCmpVoBack.getChildrenList().add(sysCmpVoSub);
				
				//组件
				SysCmpVo sysCmpVoSub2 = new SysCmpVo();
				sysCmpVoSub2.setName("NotePad测试组件");
				sysCmpVoSub2.setCode("LIS_Sys_Code_223");
				sysCmpVoSub2.setAlias("NotePad测试组件");
				sysCmpVoSub2.setType("组件");
				sysCmpVoSub2.setVersion("V1.0.0");
				sysCmpVoSub2.setDescription("描述部分略");
				sysCmpVoBack.getChildrenList().add(sysCmpVoSub2);
			}
		}
		{
			//Message系统
			SysCmpVo sysCmpVo = new SysCmpVo();
			sysCmpVo.setName(pVo.getName()+"Message系统");
			sysCmpVo.setCode("LIS_Sys_Code_3");
			sysCmpVo.setAlias("Message系统");
			sysCmpVo.setType("系统");
			sysCmpVo.setVersion("V1.0.0");
			sysCmpVo.setDescription("描述部分略");
			sysCmpList.add(sysCmpVo);
			
			{
				//Message前端
				SysCmpVo sysCmpVoFore = new SysCmpVo();
				sysCmpVoFore.setName("Message前端");
				sysCmpVoFore.setCode("LIS_Sys_Code_31");
				sysCmpVoFore.setAlias("Message前端");
				sysCmpVoFore.setType("子系统");
				sysCmpVoFore.setVersion("V1.0.0");
				sysCmpVoFore.setDescription("描述部分略");
				sysCmpVo.getChildrenList().add(sysCmpVoFore);
				
				//组件
				SysCmpVo sysCmpVoSub = new SysCmpVo();
				sysCmpVoSub.setName("Message组件");
				sysCmpVoSub.setCode("LIS_Sys_Code_311");
				sysCmpVoSub.setAlias("Message组件");
				sysCmpVoSub.setType("组件");
				sysCmpVoSub.setVersion("V1.0.0");
				sysCmpVoSub.setDescription("描述部分略");
				sysCmpVoFore.getChildrenList().add(sysCmpVoSub);
			}
			{
				//Message后端
				SysCmpVo sysCmpVoBack = new SysCmpVo();
				sysCmpVoBack.setName("Message后端");
				sysCmpVoBack.setCode("LIS_Sys_Code_32");
				sysCmpVoBack.setAlias("Message后端");
				sysCmpVoBack.setType("子系统");
				sysCmpVoBack.setVersion("V1.0.0");
				sysCmpVoBack.setDescription("描述部分略");
				sysCmpVo.getChildrenList().add(sysCmpVoBack);
				
				//组件
				SysCmpVo sysCmpVoSub = new SysCmpVo();
				sysCmpVoSub.setName("Message后端组件");
				sysCmpVoSub.setCode("LIS_Sys_Code_322");
				sysCmpVoSub.setAlias("Message后端组件");
				sysCmpVoSub.setType("组件");
				sysCmpVoSub.setVersion("V1.0.0");
				sysCmpVoSub.setDescription("描述部分略");
				sysCmpVoBack.getChildrenList().add(sysCmpVoSub);
				
				//组件
				SysCmpVo sysCmpVoSub2 = new SysCmpVo();
				sysCmpVoSub2.setName("Message测试组件");
				sysCmpVoSub2.setCode("LIS_Sys_Code_323");
				sysCmpVoSub2.setAlias("NotePad测试组件");
				sysCmpVoSub2.setType("组件");
				sysCmpVoSub2.setVersion("V1.0.0");
				sysCmpVoSub2.setDescription("描述部分略");
				sysCmpVoBack.getChildrenList().add(sysCmpVoSub2);
			}
		}
		
		return sysCmpList;
	}
}
