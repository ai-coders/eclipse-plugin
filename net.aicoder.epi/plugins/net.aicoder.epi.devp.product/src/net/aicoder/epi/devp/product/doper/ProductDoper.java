package net.aicoder.epi.devp.product.doper;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiEditorInput;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.devp.product.dao.ProductDao;

public class ProductDoper extends BaseDoper {
	private ProductDao productDao;
	
	public ProductDoper() {
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
