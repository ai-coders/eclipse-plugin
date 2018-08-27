package net.aicoder.epi.devp.prddev.model.sys;

/**
 * 登陆结果实体
 *
 */
public class LoginResult {
	private int code;
	private String message;
	private LoginResultData data;

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LoginResultData getData() {
		return data;
	}
	public void setData(LoginResultData data) {
		this.data = data;
	}

	public class LoginResultData{
		private boolean success;
		private String sessionId;
		private String message;
		
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}

}
