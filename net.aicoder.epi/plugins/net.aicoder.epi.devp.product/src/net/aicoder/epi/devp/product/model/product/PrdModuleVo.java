package net.aicoder.epi.devp.product.model.product;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.model.BaseVo;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.devp.DevpConstant;

public class PrdModuleVo extends BaseVo implements ITreeNode{
	private static final long serialVersionUID = 1L;
	private static final String ETYPE = DevpConstant.E_MODULE;
	
	private int mduFlag; // 模型标志, 0:产品;1:模块;2:功能
	private String type; // 类型
	private String stereotype; // 构造型
	private String scope; // 范围
	private String version; // 版本
	private String phase; // 阶段
	private String status; // 状态
	private long prdRid; // 产品编号
	private String prdCode; // 产品代码
	private String prdName; // 产品名称
	private long parentRid; // 父模块编号
	private int seq; // 顺序号

	private PrdModuleVo parentModule;
	private List<IBaseVo> childrenModuleList = new ArrayList<IBaseVo>(0);
	
	@Override
	public String getEtype() {
		return ETYPE;
	}

	@Override
	public void setParentData(ITreeNode parent) {
		this.setParentModule((PrdModuleVo)parent);
	}
	
	@Override
	public ITreeNode getParentData() {
		return this.getParentModule();
	}

	@Override
	public boolean hasChildren() {
		boolean hasChildren = false;
		int childrenNum = 0;
		childrenNum = childrenModuleList.size();
		if (childrenNum == 0) {
			hasChildren = false;
		} else {
			hasChildren = true;
		}
		return hasChildren;	}

	@Override
	public List<IBaseVo> getChildrenList() {
		return this.childrenModuleList;
	}

	public int getMduFlag() {
		return mduFlag;
	}

	public void setMduFlag(int mduFlag) {
		this.mduFlag = mduFlag;
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

	public long getPrdRid() {
		return prdRid;
	}

	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
	}

	public String getPrdCode() {
		return prdCode;
	}

	public void setPrdCode(String prdCode) {
		this.prdCode = prdCode;
	}

	public String getPrdName() {
		return prdName;
	}

	public void setPrdName(String prdName) {
		this.prdName = prdName;
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

	public PrdModuleVo getParentModule() {
		return parentModule;
	}

	public void setParentModule(PrdModuleVo parentModule) {
		this.parentModule = parentModule;
	}

	public List<IBaseVo> getChildrenModuleList() {
		return childrenModuleList;
	}

	public void setChildrenModuleList(List<IBaseVo> childrenModuleList) {
		this.childrenModuleList = childrenModuleList;
	}

}
