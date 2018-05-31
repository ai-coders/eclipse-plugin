package net.aicoder.epi.devp.prddev.model.dev;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.model.TreeNodeVo;
import net.aicoder.epi.devp.DevpConstant;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.tcom.tools.util.BeanUtil;

public class ProductDevVo extends TreeNodeVo {
	private static final long serialVersionUID = 1L;
	protected static String ETYPE = EtypeEnum.PRODUCT.etype();
	
	private PrdProductVo product;
	private boolean currPrd = false;
	
	public ProductDevVo() {
		super();
	}
	
	public ProductDevVo(PrdProductVo product) {
		super();
		this.setProduct(product);
	}

	@Override
	public String getEtype() {
		if(currPrd) {
			return ETYPE;
		}else {
			return DevpConstant.EXT_PRODUCT;
		}
	}

/**	
	//// Override
	@Override
	public long getRid() {
		return this.product.getRid();
	}

	@Override
	public long getTid() {
		return this.product.getTid();
	}

	@Override
	public String getName() {
		return this.product.getName();
	}
	
	@Override
	public String getCode() {
		return this.product.getCode();
	}
	
	@Override
	public String getAlias() {
		return this.product.getAlias();
	}

	@Override
	public String getDescription() {
		return this.product.getDescription();
	}
**/
	
	//// getter/setter
	public void setProduct(PrdProductVo product) {
		this.product = product;
		BeanUtil.copyBeanToBean(this, product);
	}

	public void setCurrPrd(boolean currPrd) {
		this.currPrd = currPrd;
	}
	
	public boolean isCurrPrd() {
		return currPrd;
	}

	public PrdProductVo getProduct() {
		return product;
	}
}
