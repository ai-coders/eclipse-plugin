package net.aicoder.epi.devp.prddev.doper.dev;

import java.util.List;

import net.aicoder.epi.base.doper.BaseDoper;
import net.aicoder.epi.base.model.OptionItem;
import net.aicoder.epi.devp.prddev.dao.dev.DropdownOptionsDao;

public class DropdownOptionsDoper extends BaseDoper {
	DropdownOptionsDao dao;
	
	public DropdownOptionsDoper() {
		super();
		dao = new DropdownOptionsDao();
	}
	
	public List<OptionItem> listDropdownOption(String configType){
		List<OptionItem> list = null;
		list = dao.listDropdownOption(configType);
		return list;
	}
}
