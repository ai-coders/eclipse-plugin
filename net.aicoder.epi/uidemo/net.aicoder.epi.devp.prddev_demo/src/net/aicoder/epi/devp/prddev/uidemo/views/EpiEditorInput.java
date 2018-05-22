package net.aicoder.epi.devp.prddev.uidemo.views;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class EpiEditorInput implements IEditorInput {
	private String name;

	public EpiEditorInput() {
		super();
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
		if (obj == null) {
			return false;
		} else {
			if (obj instanceof IEditorInput) {
				IEditorInput objIn = (IEditorInput) obj;
				if (this.name == null) {
					return objIn.getName() == null ? true : false;
				}
				return this.name.equals(objIn.getName());
			}
		}
		return false;
	}

	@Override
	public <T> T getAdapter(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
