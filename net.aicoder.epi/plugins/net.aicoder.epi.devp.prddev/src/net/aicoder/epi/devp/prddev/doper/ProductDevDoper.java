package net.aicoder.epi.devp.prddev.doper;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.devp.DevpConstant;
import net.aicoder.epi.devp.prddev.dao.dev.ProductDevDao;
import net.aicoder.epi.devp.prddev.model.dev.ProductDevVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElmCatgVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

public class ProductDevDoper extends BaseDoper {
	private ProductDevDao productDevDao;
	
	public ProductDevDoper() {
		super();
		productDevDao = new ProductDevDao();
	}
	
	//// loadProductList
	public EpiInput loadProductList(IBaseVo currentData) {
		EpiInput input = new EpiInput();
		List<IBaseVo> elementList = new ArrayList<IBaseVo>(0);
		
		ProductDevVo currProductDev = _loadCurrProduct(currentData);
		currProductDev.setCurrPrd(true);
		appendElmCatg(currProductDev);
		elementList.add(currProductDev);
		
		List<PrdProductVo> extProductList = _loadExtProductList(currentData);
		if(extProductList != null) {
			for(PrdProductVo extProduct:extProductList) {
				ProductDevVo extProductDev = new ProductDevVo(extProduct);
				extProductDev.setCurrPrd(false);
				appendElmCatg(extProductDev);
				elementList.add(extProductDev);
			}
		}
		
		input.setDataList(elementList);
		return input;
	}
	
	private ProductDevVo _loadCurrProduct(IBaseVo currentData) {
		ProductDevVo currProduct = null;
		currProduct = new ProductDevVo((PrdProductVo)currentData);
		return currProduct;
	}
	
	private List<PrdProductVo> _loadExtProductList(IBaseVo currentData){
		long prdRid = currentData.getRid();
		return productDevDao.loadExtProductList(prdRid);
	}
	
	private void appendElmCatg(ProductDevVo productDev) {
		// 功能模型
		{ 
			SysElmCatgVo elmCatg = new SysElmCatgVo();
			elmCatg.setPrdRid(productDev.getRid());
			elmCatg.setElmFlag(DevpConstant.CATEGORY_FUN);
			elmCatg.setName("功能模型");
			elmCatg.setCode("FunctionModel");
			elmCatg.setParentNode(productDev);
			productDev.getChildrenList().add(elmCatg);
		}
		
		// 组件模型
		{ 
			SysElmCatgVo elmCatg = new SysElmCatgVo();
			elmCatg.setPrdRid(productDev.getRid());
			elmCatg.setElmFlag(DevpConstant.CATEGORY_CMP);
			elmCatg.setName("组件模型");
			elmCatg.setCode("ComponentModel");
			elmCatg.setParentNode(productDev);
			productDev.getChildrenList().add(elmCatg);
		}
		
		// 开发模型
		if(productDev.isCurrPrd()) {
			SysElmCatgVo elmCatg = new SysElmCatgVo();
			elmCatg.setPrdRid(productDev.getRid());
			elmCatg.setElmFlag(DevpConstant.CATEGORY_IDE);
			elmCatg.setName("开发模型");
			elmCatg.setCode("DevelopmentModel");
			elmCatg.setParentNode(productDev);
			productDev.getChildrenList().add(elmCatg);
		}
		
		// 部署模型
		if(productDev.isCurrPrd()) {
			SysElmCatgVo elmCatg = new SysElmCatgVo();
			elmCatg.setPrdRid(productDev.getRid());
			elmCatg.setElmFlag(DevpConstant.CATEGORY_DPY);
			elmCatg.setName("部署模型");
			elmCatg.setCode("DeploymentModel");
			elmCatg.setParentNode(productDev);
			productDev.getChildrenList().add(elmCatg);
		}
	}
	
	//// loadDevDgmList
	public EpiInput loadDevDgmList(IBaseVo currSysElement) {
		EpiInput input = new EpiInput();
		List<IBaseVo> elementList = _loadDevDgmList(currSysElement);
		input.setDataList(elementList);
		return input;
	}
	
	private List<IBaseVo> _loadDevDgmList(IBaseVo currSysElement){
		return productDevDao.loadDevDgmList(currSysElement);
	}
	

}
