package net.aicoder.epi.devp.product.model.product;

import java.util.List;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.base.model.TreeNodeVo;

public class PrdDiagramVo extends TreeNodeVo{
	private static final long serialVersionUID = 1L;
	//private static final String ETYPE = DevpConstant.E_UML_DIAGRAM;

	private String type; // UML图类型
	private String stereotype; // 构造型
	private String scope; // 范围
	private String version; // 版本
	private String phase; // 阶段
	private String status; // 状态
	
	private PrdDiagramCatgVo parentDiagramCatg;
	
	public PrdDiagramVo() {
		super();
	}

	@Override
	public void setParentNode(ITreeNode parent) {
		this.setParentDiagramCatg((PrdDiagramCatgVo)parent);
	}

		
	@SuppressWarnings("unchecked")
	@Override
	public TreeNodeVo getParentNode() {
		return this.getParentDiagramCatg();
	}

	@Override
	public boolean hasChildren() {
		return false;
	}

	@Override
	public List<IBaseVo> getChildrenList() {
		return null;
	}
	
	@Override
	public String getEtype() {
		return getType();
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
	
	public PrdDiagramCatgVo getParentDiagramCatg() {
		return parentDiagramCatg;
	}

	public void setParentDiagramCatg(PrdDiagramCatgVo parentDiagramCatg) {
		this.parentDiagramCatg = parentDiagramCatg;
	}

}
