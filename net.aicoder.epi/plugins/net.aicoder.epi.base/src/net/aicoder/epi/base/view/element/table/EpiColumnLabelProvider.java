package net.aicoder.epi.base.view.element.table;

import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.definer.IViewDefiner;
import net.aicoder.tcom.tools.util.BeanUtil;

public class EpiColumnLabelProvider implements  ITableLabelProvider {
	//private String dataName;
	IViewDefiner viewDefiner;

	public EpiColumnLabelProvider(IViewDefiner viewDefiner){
		this.viewDefiner = viewDefiner;
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
