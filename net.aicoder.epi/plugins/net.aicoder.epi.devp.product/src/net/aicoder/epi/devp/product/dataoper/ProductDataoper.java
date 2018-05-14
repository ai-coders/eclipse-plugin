package net.aicoder.epi.devp.product.dataoper;

import java.util.List;

import net.aicoder.epi.base.dataoper.BaseDataoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.adapter.EpiEditorInput;
import net.aicoder.epi.base.view.adapter.EpiInput;
import net.aicoder.epi.devp.product.dao.ProductDao;

public class ProductDataoper extends BaseDataoper {
	private ProductDao productDao;
	
	public ProductDataoper() {
		super();
		productDao = new ProductDao();
	}

	public EpiInput loadProductList(IBaseVo currentData) {
		EpiInput input = new EpiInput();
		List<IBaseVo> elementList = productDao.listProduct();
		input.setDataList(elementList);
		return input;
	}
	
	public EpiEditorInput getSelectProductAsEditorInput(IBaseVo selectProduct) {
		EpiEditorInput editorInput = new EpiEditorInput(selectProduct);
		return editorInput;
	}

	//// getter/setter
	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
}
