package net.aicoder.epi.base.view.property;

import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import net.aicoder.epi.base.model.property.PitemDefine;
import net.aicoder.epi.base.model.property.PropIoFlagEnum;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.definer.IViewDefiner;
import net.aicoder.tcom.tools.util.AiStringUtil;
import net.aicoder.tcom.tools.util.BeanUtil;

public class PropsColLabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String text = "";
		if (element != null) {
			if (element instanceof PropertyInfo) {
				PropertyInfo propertyInfo = (PropertyInfo) element;
				switch (columnIndex) {
				case 0:
					text = getPropertyName(propertyInfo);
					break;
				case 1:
					text = propertyInfo.getShowValue();
					break;
				default:
					break;
				}
			}
		}
		return text;
	}

	private String getPropertyName(PropertyInfo propertyInfo) {
		String text = "";
		PitemDefine itemDefine = propertyInfo.getItemDefine();
		String ioFlag = "";
		if (itemDefine != null && itemDefine.getControl() != null) {
			ioFlag = itemDefine.getControl().getIoFlag();
		}
		if (AiStringUtil.isEmpty(propertyInfo.getCode())) {
			text = propertyInfo.getName();
		} else {
			text = propertyInfo.getName() + "(" + propertyInfo.getCode() + ")";
		}
		if (PropIoFlagEnum.NotNull.ioFlag().equalsIgnoreCase(ioFlag)) {
			text = "*" + text;
		} else {
			text = " " + text;
		}
		return text;
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub

	}
}
