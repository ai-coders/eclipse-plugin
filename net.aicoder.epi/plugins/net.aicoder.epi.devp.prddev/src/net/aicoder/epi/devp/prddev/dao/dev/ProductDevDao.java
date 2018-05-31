package net.aicoder.epi.devp.prddev.dao.dev;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

public class ProductDevDao extends BaseDao {
	
	public ProductDevDao() {
		super();
	}
	
	//依据当前的产品rid获取关联的外部产品
	public List<PrdProductVo> loadExtProductList(long prdRid){ 
		List<PrdProductVo> productList = new ArrayList<PrdProductVo>(0);
		
		PrdProductVo prd1 = new PrdProductVo();
		prd1.setRid(90001);
		prd1.setCode("Ext_PD_C1");
		prd1.setName("Ext_PD_N1");
		productList.add(prd1);
		
		PrdProductVo prd2 = new PrdProductVo();
		prd2.setRid(90001);
		prd2.setCode("Ext_PD_C1");
		prd2.setName("Ext_PD_N1");
		productList.add(prd2);
		
		return productList;
	}

}
