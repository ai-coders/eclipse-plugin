package net.aicoder.devp.model.system;

import net.aicoder.devp.model.DevpBaseEo;

public class SysSystemEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private String type; // ϵͳ����
	private String stereotype; // ������
	private String scope; // ��Χ
	private String version; // �汾
	private String phase; // �׶�
	private String status; // ״̬

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

}
