package net.aicoder.epi.devp.prddev.doper.ops;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.prddev.dao.ops.OpsAssetCmdbDao;

/**
 * IT资产Doper
 * @author WANGQINGPING
 *
 */
public class OpsAssetCmdbDoper extends BaseDoper{
	private OpsAssetCmdbDao opsAssetCmdbDao;
	
	public OpsAssetCmdbDoper() {
		super();
		this.opsAssetCmdbDao = new OpsAssetCmdbDao();
	}
	
	public List<IBaseVo> loadOpsAssetCmdbList(IBaseVo baseVo){
		List<IBaseVo> loadOpsAssetCmdbList = opsAssetCmdbDao.loadOpsAssetCmdbList(baseVo);
		return loadOpsAssetCmdbList;
	}
	
	
}
