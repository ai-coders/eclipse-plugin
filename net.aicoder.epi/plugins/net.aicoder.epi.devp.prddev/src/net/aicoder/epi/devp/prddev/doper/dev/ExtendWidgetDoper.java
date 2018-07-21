package net.aicoder.epi.devp.prddev.doper.dev;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.OptionItem;
import net.aicoder.epi.devp.prddev.dao.dev.ExtendWidgetDao;

/**
 * 扩展
 * @author WQP
 *
 */
public class ExtendWidgetDoper extends BaseDoper {
	private ExtendWidgetDao extendWidgetDao;
	
	public ExtendWidgetDoper() {
		super();
		this.extendWidgetDao = new ExtendWidgetDao();
	}
	
	
	public List<OptionItem> loadCmpTypeList(String type){
		return extendWidgetDao.loadCmpTypeList(type);
	}
	
	
}
