package net.aicoder.epi.base.view.part.area;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

public abstract class AbstractBaseArea implements IArea {
	private IWorkbenchPart workbenchPart;
	private String titleText;
	private Image titleImage;

	//private IEditorPart editor;
	
	//// Constructor
	public AbstractBaseArea() {
		super();
	}

	public AbstractBaseArea(IWorkbenchPart workbenchPart) {
		this();
		setWorkbenchPart(workbenchPart);
	}

	public AbstractBaseArea(String titleText, Image titleImage) {
		super();
		setTitleText(titleText);
		setTitleImage(titleImage);
	}
	
	public AbstractBaseArea(IWorkbenchPart workbenchPart, String titleText, Image titleImage) {
		this(titleText, titleImage);
		setWorkbenchPart(workbenchPart);
	}

	@Override
	public IWorkbenchPart getWorkbenchPart() {
		return workbenchPart;
	}

	@Override
	public void setWorkbenchPart(IWorkbenchPart workbenchPart) {
		this.workbenchPart = workbenchPart;
	}
	
	@Override
	public IEditorInput getEditorInput() {
		IEditorInput editorInput = null;
		if(workbenchPart instanceof IEditorPart) {
			editorInput = ((IEditorPart)workbenchPart).getEditorInput();
		}
		return editorInput;
	}
	
	@Override
	public String getTitleText() {
		return titleText;
	}

	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	@Override
	public Image getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(Image titleImage) {
		this.titleImage = titleImage;
	}

}
