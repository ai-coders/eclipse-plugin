package net.aicoder.devp.model.product;

import net.aicoder.devp.model.BaseDevpEo;
import net.aicoder.devp.model.EtypeEnum;

public class PrdPrdlineEo extends BaseDevpEo {
	private static final long serialVersionUID = 1L;

	private String type; // 产品线类型
	private String domain; // 领域
	private String stereotype; // 构造型
	private String scope; // 访问控制范围
	private String version; // 版本
	private String phase; // 阶段
	private String status; // 状态
	private long parentRid; // 父产品线编号
	private int seq; // 顺序号

	static {
		etype = EtypeEnum.PRD_LINE;
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
}
