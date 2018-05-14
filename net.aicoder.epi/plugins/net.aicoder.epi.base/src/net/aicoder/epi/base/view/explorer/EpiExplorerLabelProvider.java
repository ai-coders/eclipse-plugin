package net.aicoder.epi.base.view.explorer;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.definer.IViewDefiner;
import net.aicoder.epi.base.view.definer.IViewItemDefiner;
import net.aicoder.tcom.tools.util.AiStringUtil;
import net.aicoder.tcom.tools.util.BeanUtil;

public class EpiExplorerLabelProvider extends LabelProvider implements ICommonLabelProvider {
	IViewDefiner viewDefiner;
	
	public EpiExplorerLabelProvider() {
		super();
	}
	
	public EpiExplorerLabelProvider(IViewDefiner viewDefiner) {
		super();
		setViewDefiner(viewDefiner);
	}

	@Override
	public Image getImage(Object element) {
		Image image = null;

		if (element != null && element instanceof IBaseVo) {
			String etype = ((IBaseVo) element).getEtype();
			if (!AiStringUtil.isEmpty(etype) && viewDefiner != null) {
				IViewItemDefiner itemDefiner = viewDefiner.getViewItemDefiner(etype);
				if (itemDefiner != null) {
					image = itemDefiner.getImage();
				}
			}
		}

		return image;
	}

	@Override
	public String getText(Object element) {
		String text = "";
		
		if (element != null && element instanceof IBaseVo) {
			String etype = ((IBaseVo) element).getEtype();
			if (!AiStringUtil.isEmpty(etype) && viewDefiner != null) {
				IViewItemDefiner itemDefiner = viewDefiner.getViewItemDefiner(etype);
				if (itemDefiner == null || AiStringUtil.isEmpty(itemDefiner.getTextVarName())
						|| "name".equalsIgnoreCase(itemDefiner.getTextVarName())) {
					text = ((IBaseVo) element).getName();
				} else {
					try {
						text = BeanUtil.getPropertyValue(element, itemDefiner.getTextVarName()).toString();
					} catch (Exception e) {
						text = ((IBaseVo) element).getName();
						e.printStackTrace();
					}
				}
			}
		}

		return text;
	}

	@Override
	public String getDescription(Object element) {
		String text = "";
		
		if (element != null && element instanceof IBaseVo) {
			String etype = ((IBaseVo) element).getEtype();
			if (!AiStringUtil.isEmpty(etype) && viewDefiner != null) {
				IViewItemDefiner itemDefiner = viewDefiner.getViewItemDefiner(etype);
				if (itemDefiner == null || AiStringUtil.isEmpty(itemDefiner.getDescVarName())
						|| "description".equalsIgnoreCase(itemDefiner.getDescVarName())) {
					text = ((IBaseVo) element).getDescription();
				} else {
					try {
						text = BeanUtil.getPropertyValue(element, itemDefiner.getDescVarName()).toString();
					} catch (Exception e) {
						text = ((IBaseVo) element).getDescription();
						e.printStackTrace();
					}
				}
			}
		}

		return text;
	}

	@Override
	public void restoreState(IMemento arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveState(IMemento arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(ICommonContentExtensionSite arg0) {
		// TODO Auto-generated method stub

	}
	
	public IViewDefiner getViewDefiner() {
		return viewDefiner;
	}

	public void setViewDefiner(IViewDefiner viewDefiner) {
		this.viewDefiner = viewDefiner;
	}

}
