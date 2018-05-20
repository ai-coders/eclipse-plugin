package net.aicoder.devp.model;

import java.util.Date;

public abstract class DevpBaseEo implements IDevpEo {
	private static final long serialVersionUID = 1L;

	protected static EtypeEnum etype;

	private long rid;
	private long tid;
	private String code;
	private String name;
	private String alias;
	private String description;
	
	private int recordState;

	private String createUid;
	private String createUcode;
	private String createUname;
	private Date createAt;
	private String modifyUid;
	private String modifyUcode;
	private String modifyUname;
	private Date modifyAt;

	public DevpBaseEo() {
		super();
	}

	public EtypeEnum getEtype() {
		return etype;
	}

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getRecordState() {
		return recordState;
	}

	public void setRecordState(int recordState) {
		this.recordState = recordState;
	}

	public String getCreateUid() {
		return createUid;
	}

	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	public String getCreateUcode() {
		return createUcode;
	}

	public void setCreateUcode(String createUcode) {
		this.createUcode = createUcode;
	}

	public String getCreateUname() {
		return createUname;
	}

	public void setCreateUname(String createUname) {
		this.createUname = createUname;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getModifyUid() {
		return modifyUid;
	}

	public void setModifyUid(String modifyUid) {
		this.modifyUid = modifyUid;
	}

	public String getModifyUcode() {
		return modifyUcode;
	}

	public void setModifyUcode(String modifyUcode) {
		this.modifyUcode = modifyUcode;
	}

	public String getModifyUname() {
		return modifyUname;
	}

	public void setModifyUname(String modifyUname) {
		this.modifyUname = modifyUname;
	}

	public Date getModifyAt() {
		return modifyAt;
	}

	public void setModifyAt(Date modifyAt) {
		this.modifyAt = modifyAt;
	}
}
