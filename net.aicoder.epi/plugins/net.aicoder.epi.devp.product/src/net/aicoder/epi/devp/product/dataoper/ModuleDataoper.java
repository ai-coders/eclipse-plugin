package net.aicoder.epi.devp.product.dataoper;

import java.util.List;

import net.aicoder.epi.base.dataoper.BaseDataoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.adapter.EpiInput;
import net.aicoder.epi.devp.product.dao.ModuleDao;

public class ModuleDataoper extends BaseDataoper {
	private ModuleDao moduleDao;

	public ModuleDataoper() {
		super();
		moduleDao = new ModuleDao();
	}
	
	public EpiInput loadModuleList(IBaseVo currentData) {
		EpiInput input = new EpiInput();
		long prdRid = currentData.getRid();
		List<IBaseVo> elementList = moduleDao.listModule(prdRid);
		input.setDataList(elementList);
		return input;
	}
	
	public void saveModuleList() {
		
	}

}
