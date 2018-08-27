package net.aicoder.epi.devp.prddev.doper.ops;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.devp.prddev.dao.ops.SysDpyBuildPublishDao;

/**
 *  运维流水线-构建及发布Doper
 * @author WANGQINGPING
 *
 */
public class SysDpyBuildPublishDoper extends BaseDoper {
	private SysDpyBuildPublishDao dao;
	
	public SysDpyBuildPublishDoper() {
		super();
		this.dao = new SysDpyBuildPublishDao();
	}
	
	public IEpiInput loadSysDpyBuildPublishList(IBaseVo basevo) {
		List<IBaseVo> sysDpyBuildPublishList = dao.loadSysDpyBuildPublishList(basevo);
		IEpiInput input = new EpiInput();
		input.setDataList(sysDpyBuildPublishList);
		return input;
	}
	
	
}
