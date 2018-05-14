package net.aicoder.devp.model.product;

import net.aicoder.devp.model.DevpBaseEo;
import net.aicoder.devp.model.EtypeEnum;

public class PrdPrdlineEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private String type; // ��Ʒ������
	private String domain; // ����
	private String stereotype; // ������
	private String scope; // ��Χ
	private String version; // �汾
	private String phase; // �׶�
	private String status; // ״̬
	private long parentRid; // ����Ʒ�߱��
	private int seq; // ˳���

	static {
		etype = EtypeEnum.PRDLINE;
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
