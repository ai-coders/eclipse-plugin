package net.aicoder.epi.devp.prddev.doper.ops;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.devp.prddev.dao.ops.ProductOpsDao;

/**
 * 产品发布导航 Doper
 * @author WANGQINGPING
 *
 */
public class ProductOpsDoper extends BaseDoper{
	private ProductOpsDao productOpsDao;

	public ProductOpsDoper() {
		super();
		this.productOpsDao = new ProductOpsDao();
	}
	
	/**
	 * 获取产品分组浏览数据
	 * @param baseVo
	 * @return
	 */
	public EpiInput loadProductGroupList(IBaseVo baseVo) {
		List<IBaseVo> loadProductGroupList = productOpsDao.loadProductGroupList(baseVo);
		EpiInput epiInput = new EpiInput();
		epiInput.setDataList(loadProductGroupList);
		return epiInput;
	}
	
	/**
	 * 获取组件图数据
	 * @param baseVo
	 * @return
	 */
	public EpiInput loadProductOpsFolderDesDgmList(IBaseVo baseVo){
		List<IBaseVo> loadProductDesDgmTableList = productOpsDao.loadProductOpsFolderDesDgmList(baseVo);
		EpiInput epiInput = new EpiInput();
		epiInput.setDataList(loadProductDesDgmTableList);
		return epiInput;
	}
	
	/**
	 * 获取子元素数据
	 * @param baseVo
	 * @return
	 */
	public EpiInput loadProductOpsFolderSubElmList(IBaseVo baseVo) {
		List<IBaseVo> loadProductDesDgmTableList = productOpsDao.loadProductOpsFolderSubElmList(baseVo);
		EpiInput epiInput = new EpiInput();
		epiInput.setDataList(loadProductDesDgmTableList);
		return epiInput;
	}
	
	
	
	
	
}
