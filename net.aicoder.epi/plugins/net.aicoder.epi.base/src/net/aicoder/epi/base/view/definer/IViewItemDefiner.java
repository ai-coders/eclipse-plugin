package net.aicoder.epi.base.view.definer;

import org.eclipse.swt.graphics.Image;

public interface IViewItemDefiner {

	String getEtype();

	void setEtype(String etype);

	Image getImage();

	void setImage(Image image);

	String getTextVarName();

	void setTextVarName(String textVarName);

	String getDescVarName();

	void setDescVarName(String descVarName);

	String getEditorId();

	void setEditorId(String editorId);

}