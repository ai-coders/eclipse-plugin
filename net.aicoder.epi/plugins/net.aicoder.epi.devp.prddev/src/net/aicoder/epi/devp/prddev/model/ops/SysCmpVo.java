package net.aicoder.epi.devp.prddev.model.ops;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.base.model.TreeNodeVo;

/**
 * 部署模型-系统、子系统、组件
 * @author WANGQINGPING
 *
 */
public class SysCmpVo extends TreeNodeVo{
	private static final long serialVersionUID = 1L;
	private String type;
  	private String subType;
  	private String stereotype;
  	private String scope;
  	private String version;
  	private String phase;
  	private String status;
  	private String notes;
  	private long installable;
  	private long sharedComponent;
  	private long sharedService;
  	private long prdRid;
  	private long parentRid;
  	private long seq;
  	
  	private SysCmpVo parentSysCmpVo;
  	private List<IBaseVo> childrenSysCmpVoList = new ArrayList<IBaseVo>(0);
  	
  	
	public SysCmpVo getParentSysCmpVo() {
		return parentSysCmpVo;
	}
	public void setParentSysCmpVo(SysCmpVo parentSysCmpVo) {
		this.parentSysCmpVo = parentSysCmpVo;
	}
	@SuppressWarnings("unchecked")
	@Override
	public TreeNodeVo getParentNode() {
		return super.getParentNode();
//		return this.getParentSysCmpVo();
	}
	@Override
	public void setParentNode(ITreeNode parentNode) {
		super.setParentNode(parentNode);
		this.setParentSysCmpVo((SysCmpVo) parentNode);
	}
	
	public List<IBaseVo> getChildrenSysCmpVoList() {
		return childrenSysCmpVoList;
	}
	public void setChildrenSysCmpVoList(List<IBaseVo> childrenSysCmpVoList) {
		this.childrenSysCmpVoList = childrenSysCmpVoList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
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
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public long getInstallable() {
		return installable;
	}
	public void setInstallable(long installable) {
		this.installable = installable;
	}
	public long getSharedComponent() {
		return sharedComponent;
	}
	public void setSharedComponent(long sharedComponent) {
		this.sharedComponent = sharedComponent;
	}
	public long getSharedService() {
		return sharedService;
	}
	public void setSharedService(long sharedService) {
		this.sharedService = sharedService;
	}
	public long getPrdRid() {
		return prdRid;
	}
	public void setPrdRid(long prdRid) {
		this.prdRid = prdRid;
	}
	public long getParentRid() {
		return parentRid;
	}
	public void setParentRid(long parentRid) {
		this.parentRid = parentRid;
	}
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}

}
