package net.aicoder.epi.devp.prddev.doper.ops;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.devp.prddev.dao.ops.SysDpySysMonitorDao;

public class SysDpySysMonitorDoper extends BaseDoper {
	private SysDpySysMonitorDao dao;
	
	public SysDpySysMonitorDoper() {
		super();
		this.dao = new SysDpySysMonitorDao();
	}
	
	public IEpiInput loadSysDpySysMonitorList(IBaseVo basevo) {
		
		IEpiInput input = new EpiInput();
		return input;
	}
}
