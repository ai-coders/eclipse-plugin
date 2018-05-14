package net.aicoder.epi.base.view.adapter;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import net.aicoder.epi.base.model.IBaseVo;

public class EpiEditorInput extends EpiInput implements IEpiEditorInput {
	private String name;

	public EpiEditorInput() {
		super();
	}

	public EpiEditorInput(IBaseVo currentData) {
		super(currentData);
		this.name = currentData.getName();
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean equals(Object obj) {
		if (obj instanceof IEditorInput) {
			return this.name.equals(((IEditorInput) obj).getName());
		}
		return false;
	}

}
