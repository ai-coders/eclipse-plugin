package net.aicoder.devp.model.uml;

import net.aicoder.devp.model.DevpBaseEo;

public class UmlDiagramObjectEo extends DevpBaseEo {
	private static final long serialVersionUID = 1L;

	private long diagramRid;
	private long objectRid;

	private String diagramType;
	private String objectType;

	public long getDiagramRid() {
		return diagramRid;
	}

	public void setDiagramRid(long diagramRid) {
		this.diagramRid = diagramRid;
	}

	public long getObjectRid() {
		return objectRid;
	}

	public void setObjectRid(long objectRid) {
		this.objectRid = objectRid;
	}

	public String getDiagramType() {
		return diagramType;
	}

	public void setDiagramType(String diagramType) {
		this.diagramType = diagramType;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

}
