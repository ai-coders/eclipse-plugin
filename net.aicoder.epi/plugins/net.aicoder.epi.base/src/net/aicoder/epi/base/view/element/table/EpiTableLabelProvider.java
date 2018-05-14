package net.aicoder.epi.base.view.element.table;

import java.util.List;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.definer.IViewDefiner;
import net.aicoder.tcom.tools.util.BeanUtil;

public class EpiTableLabelProvider extends LabelProvider implements ITableLabelProvider {
	IViewDefiner viewDefiner;

	public EpiTableLabelProvider(EpiTableDefiner tableDefiner) {
		super();
		setViewDefiner(viewDefiner);
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String text = "";
		if(element != null) {
			List<IColumnDefiner> columnDefinerList = viewDefiner.getColumnDefinerList();
			if(columnIndex>columnDefinerList.size()) {
				return text;
			}else {
				IColumnDefiner columnDefiner = columnDefinerList.get(columnIndex);
				Object value = null;
				try {
					value = BeanUtil.getPropertyValue(element, columnDefiner.getDataName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(value != null) {
					text = value.toString();
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
