package net.aicoder.epi.base.dao;

public abstract class BaseDao{
	private long uid = 1;
	private String userCode = "AI0000001";
	private String userName = "Stone.Shi";
	
	public BaseDao() {
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
