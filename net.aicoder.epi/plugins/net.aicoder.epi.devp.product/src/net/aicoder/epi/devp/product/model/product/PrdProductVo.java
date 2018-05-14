package net.aicoder.epi.devp.product.model.product;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.model.BaseVo;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.devp.DevpConstant;

public class PrdProductVo extends BaseVo implements ITreeNode{
	private static final long serialVersionUID = 1L;
	private static final String ETYPE = DevpConstant.E_PRODUCT;

	private String type; // 产品类型
	private String stereotype; // 构造型
	private String scope; // 范围
	private String version; // 版本
	private String phase; // 阶段
	private String status; // 状态
	
	private PrdPrdlineVo parentPrdline;
	private List<IBaseVo> diagramCatgList = new ArrayList<IBaseVo>(0);

	public PrdProductVo() {
		super();
	}
	
	@Override
	public void setParentData(ITreeNode parent) {
		this.setParentPrdline((PrdPrdlineVo)parent);
	}

	@Override
	public ITreeNode getParentData() {
		return this.getParentPrdline();
	}

	@Override
	public boolean hasChildren() {
		boolean hasChildren = false;
		if (getDiagramCatgList().size() == 0) {
			hasChildren = false;
		} else {
			hasChildren = true;
		}
		return hasChildren;
	}

	@Override
	public List<IBaseVo> getChildrenList() {
		return this.getDiagramCatgList();
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

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public PrdPrdlineVo getParentPrdline() {
		return parentPrdline;
	}

	public void setParentPrdline(PrdPrdlineVo parentPrdline) {
		this.parentPrdline = parentPrdline;
	}

	public List<IBaseVo> getDiagramCatgList() {
		return diagramCatgList;
	}

	public void setDiagramCatgList(List<IBaseVo> diagramCatgList) {
		this.diagramCatgList = diagramCatgList;
	}
}
