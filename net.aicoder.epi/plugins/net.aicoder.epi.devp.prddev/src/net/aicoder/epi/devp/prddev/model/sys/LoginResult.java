package net.aicoder.epi.devp.prddev.model.sys;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 登陆结果
 * Created by gonghongrui on 2017/1/5.
 */
public class LoginResult {

	private boolean success;

	private String sessionId;

	private String message;

	public LoginResult(Boolean success){
		this.success = success;
	}

	public LoginResult(Boolean success, String id){
		this.success = success;
		this.sessionId = id;
	}


	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
