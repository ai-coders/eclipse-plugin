package net.aicoder.epi.base.view.element.tree;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.definer.IViewDefiner;
import net.aicoder.epi.base.view.definer.IViewItemDefiner;
import net.aicoder.tcom.tools.util.AiStringUtil;
import net.aicoder.tcom.tools.util.BeanUtil;

public class EpiTreeLabelProvider extends LabelProvider{
	IViewDefiner viewDefiner;
	
	public EpiTreeLabelProvider() {
		super();
	}
	
	public EpiTreeLabelProvider(IViewDefiner viewDefiner) {
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
	
	public IViewDefiner getViewDefiner() {
		return viewDefiner;
	}

	public void setViewDefiner(IViewDefiner viewDefiner) {
		this.viewDefiner = viewDefiner;
	}


}
