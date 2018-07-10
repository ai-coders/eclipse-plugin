package net.aicoder.epi.base.model;

import java.io.Serializable;

public class LoadElementState implements Serializable{
	private final static int DEFAULT_PAGE_SIZE = 100;

	private String elementName;
	private int totalRecNum;

	private boolean loadElementByPage = false;
	private int pageSize = DEFAULT_PAGE_SIZE;
	private int currPageNo = -1;
	private int startRecNo = -1;
	private int finishRecNo;

	// private boolean loadElement = false;

	////
	public LoadElementState() {
		super();
	}

	public LoadElementState(String elementName) {
		super();
		setElementName(elementName);
	}

	public boolean isLoadedElement() {
		boolean isLoaded = false;
		if (startRecNo < 0) {
			isLoaded = false;
		} else {
			isLoaded = true;
		}
		return isLoaded;
	}

	public boolean isLoadedElement(int pageNo) {
		boolean isLoaded = false;
		if (pageNo == currPageNo && currPageNo >= 0) {
			isLoaded = true;
		}
		return isLoaded;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public int getTotalRecNum() {
		return totalRecNum;
	}

	public void setTotalRecNum(int totalRecNum) {
		this.totalRecNum = totalRecNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrPageNo() {
		return currPageNo;
	}

	public void setCurrPageNo(int currPageNo) {
		this.currPageNo = currPageNo;
	}

	public int getStartRecNo() {
		return startRecNo;
	}

	public void setStartRecNo(int startRecNo) {
		this.startRecNo = startRecNo;
	}

	public int getFinishRecNo() {
		return finishRecNo;
	}

	public void setFinishRecNo(int finishRecNo) {
		this.finishRecNo = finishRecNo;
	}

	public boolean isLoadElementByPage() {
		return loadElementByPage;
	}

	public void setLoadElementByPage(boolean loadElementByPage) {
		this.loadElementByPage = loadElementByPage;
	}
}
