package net.aicoder.epi.devp.product.model.product;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.model.BaseVo;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.base.model.TreeNodeVo;
import net.aicoder.epi.devp.DevpConstant;

public class PrdDiagramCatgVo extends TreeNodeVo {
	private static final long serialVersionUID = 1L;
	private static final String ETYPE = DevpConstant.CATEGORY;

	private String type; // 产品类型
	private String stereotype; // 构造型
	
	private PrdProductVo parentProduct;
	private List<IBaseVo> diagramList = new ArrayList<IBaseVo>(0);

	public PrdDiagramCatgVo() {
		super();
	}
	
	@Override
	public void setParentNode(ITreeNode parentProduct) {
		this.setParentProduct((PrdProductVo)parentProduct);
	}

	@Override
	public TreeNodeVo getParentNode() {
		return this.getParentProduct();
	}

	@Override
	public boolean hasChildren() {
		boolean hasChildren = false;
		if (getDiagramList().size() == 0) {
			hasChildren = false;
		} else {
			hasChildren = true;
		}
		return hasChildren;
	}

	@Override
	public List<IBaseVo> getChildrenList() {
		return getDiagramList();
	}
	
	@Override
	public String getEtype() {
		return ETYPE;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStereotype() {
		return stereotype;
	}

	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
	}

	public PrdProductVo getParentProduct() {
		return parentProduct;
	}

	public void setParentProduct(PrdProductVo parentProduct) {
		this.parentProduct = parentProduct;
	}

	public List<IBaseVo> getDiagramList() {
		return diagramList;
	}

	public void setDiagramList(List<IBaseVo> diagramList) {
		this.diagramList = diagramList;
	}
}
