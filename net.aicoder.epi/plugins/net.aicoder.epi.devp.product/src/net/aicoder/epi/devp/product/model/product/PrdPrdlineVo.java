package net.aicoder.epi.devp.product.model.product;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.model.BaseVo;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.base.model.TreeNodeVo;
import net.aicoder.epi.devp.DevpConstant;

public class PrdPrdlineVo extends TreeNodeVo{
	private static final long serialVersionUID = 1L;
	private static final String ETYPE = DevpConstant.E_PRDLINE;

	private String type; // 产品线类型
	private String domain; // 领域
	private String stereotype; // 构造型
	private String scope; // 范围
	private String version; // 版本
	private String phase; // 阶段
	private String status; // 状态
	private long parentRid; // 父产品线编号
	private int seq; // 顺序号

	private PrdPrdlineVo parentPrdline;
	private List<PrdPrdlineVo> childrenPrdlineList = new ArrayList<PrdPrdlineVo>(0);
	private List<PrdProductVo> productList = new ArrayList<PrdProductVo>(0);
	
	public PrdPrdlineVo() {
		super();
	}
	
	@Override
	public void setParentNode(ITreeNode parent) {
		this.setParentPrdline((PrdPrdlineVo)parent);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TreeNodeVo getParentNode() {
		return this.getParentPrdline();
	}

	@Override
	public boolean hasChildren() {
		boolean hasChildren = false;
		int childrenNum = 0;
		childrenNum = childrenNum + childrenPrdlineList.size();
		childrenNum = childrenNum + productList.size();
		if (childrenNum == 0) {
			hasChildren = false;
		} else {
			hasChildren = true;
		}
		return hasChildren;
	}

	@Override
	public List<IBaseVo> getChildrenList() {
		List<IBaseVo> list = new ArrayList<IBaseVo>(0);
		list.addAll(this.childrenPrdlineList);
		list.addAll(this.productList);
		return list;
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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
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

	public long getParentRid() {
		return parentRid;
	}

	public void setParentRid(long parentRid) {
		this.parentRid = parentRid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public PrdPrdlineVo getParentPrdline() {
		return parentPrdline;
	}

	public void setParentPrdline(PrdPrdlineVo parentPrdLine) {
		this.parentPrdline = parentPrdLine;
	}


	public List<PrdPrdlineVo> getChildrenPrdlineList() {
		return childrenPrdlineList;
	}

	public void setChildrenPrdlineList(List<PrdPrdlineVo> childrenPrdlineList) {
		this.childrenPrdlineList = childrenPrdlineList;
	}

	public List<PrdProductVo> getProductList() {
		return productList;
	}

	public void setProductList(List<PrdProductVo> productList) {
		this.productList = productList;
	}
}
