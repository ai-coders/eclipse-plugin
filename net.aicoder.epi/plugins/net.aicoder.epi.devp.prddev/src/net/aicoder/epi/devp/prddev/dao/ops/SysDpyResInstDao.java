package net.aicoder.epi.devp.prddev.dao.ops;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDpyResInstVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDpyResourcesVo;

public class SysDpyResInstDao extends BaseDao{

	public SysDpyResInstDao() {
		super();
	}
	
	public List<IBaseVo> loadSysDypResInstList(IBaseVo baseVo){
		List<IBaseVo> sysDpyResInstList = new ArrayList<>();
		if(!(baseVo instanceof SysDpyResourcesVo)) return sysDpyResInstList;
		
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
	
}
