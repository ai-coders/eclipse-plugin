package net.aicoder.epi.base.view.definer;

import org.eclipse.swt.graphics.Image;

public class ViewItemDefiner implements IViewItemDefiner {

	private String etype; // 数据类型
	private Image image; // 图片
	private String textVarName; // Text对应变量名, getText()
	private String descVarName; // Description对应变量名, getDescription()

	//private Action doubleClickAction; // 双击对应的动作
	private String editorId; // 双击时打开编辑器的ID

	public ViewItemDefiner() {
		super();
	}

	public ViewItemDefiner(Object[] viewItemDefine) {
		super();
		
		if (viewItemDefine == null) {
			return;
		}
		
		int seq = 0;
		if (viewItemDefine.length > seq) {
			this.setEtype((String) viewItemDefine[seq++]);
		}
		if (viewItemDefine.length > seq) {
			this.setImage((Image) viewItemDefine[seq++]);
		}
		if (viewItemDefine.length > seq) {
			this.setTextVarName((String) viewItemDefine[seq++]);
		}
		if (viewItemDefine.length > seq) {
			this.setDescVarName((String) viewItemDefine[seq++]);
		}
		if (viewItemDefine.length > seq) {
			this.setEditorId((String) viewItemDefine[seq++]);
		}
		if (viewItemDefine.length > seq) {
		}
	}

	@Override
	public String getEtype() {
		return etype;
	}

	@Override
	public void setEtype(String etype) {
		this.etype = etype;
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public String getTextVarName() {
		return textVarName;
	}

	@Override
	public void setTextVarName(String textVarName) {
		this.textVarName = textVarName;
	}

	@Override
	public String getDescVarName() {
		return descVarName;
	}

	@Override
	public void setDescVarName(String descVarName) {
		this.descVarName = descVarName;
	}

	@Override
	public String getEditorId() {
		return editorId;
	}

	@Override
	public void setEditorId(String editorId) {
		this.editorId = editorId;
	}
}
