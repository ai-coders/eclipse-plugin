package net.aicoder.epi.base.view.context;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import net.aicoder.epi.base.model.IBaseVo;

public class EpiEditorInput extends EpiInput implements IEpiEditorInput {
	private String name;
	private String toolTipText;
	private ImageDescriptor imageDescriptor;
	private IPersistableElement persistableElement;

	public EpiEditorInput() {
		super();
	}

	public EpiEditorInput(IBaseVo currentData) {
		super(currentData);
		if(currentData!=null) {
			this.name = currentData.getName();
		}
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return this.imageDescriptor;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IPersistableElement getPersistable() {
		return this.persistableElement;
	}

	@Override
	public String getToolTipText() {
		return this.toolTipText;
	}

	public boolean equals(Object obj) {
		if(this.name == null || obj == null) {
			return false;
		}
		if (obj instanceof IEditorInput) {
			return this.name.equals(((IEditorInput) obj).getName());
		}
		return false;
	}

	//// getter/setter
	public IPersistableElement getPersistableElement() {
		return persistableElement;
	}

	public void setPersistableElement(IPersistableElement persistableElement) {
		this.persistableElement = persistableElement;
	}

	public void setToolTipText(String toolTipText) {
		this.toolTipText = toolTipText;
	}

	public void setImageDescriptor(ImageDescriptor imageDescriptor) {
		this.imageDescriptor = imageDescriptor;
	}
	
}
