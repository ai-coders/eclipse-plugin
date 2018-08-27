package net.aicoder.epi.devp.prddev.model.ops;

public class ResponseContent<D> {
	private String total;
	private String totalElements;
	private D content;
	
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(String totalElements) {
		this.totalElements = totalElements;
	}
	public D getContent() {
		return content;
	}
	public void setContent(D content) {
		this.content = content;
	}
	
}
