package net.aicoder.epi.devp.prddev.dao.dev;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.OptionItem;

public class DropdownOptionsDao  extends BaseDao {
	
	public DropdownOptionsDao() {
		super();
	}
	
	public List<OptionItem> listDropdownOption(String configType){
		List<OptionItem> list = null;
		list = _listDropdownOption(configType);
		return list;
	}
	
	private List<OptionItem> _listDropdownOption(String configType){
		List<OptionItem> list = null;
		switch(configType) {
		case "cmpType":
			list = _listDropdownOption_cmpType(configType);
			break;
		}
		return list;
	}
	
	private List<OptionItem> _listDropdownOption_cmpType(String configType){
		List<OptionItem> list = new ArrayList<OptionItem>(0);
		{
			OptionItem item = new OptionItem();
			item.setConfigType(configType);
			item.setDisplayName("组件类型");
			item.setCode("cmp_web");
			item.setValue("Web组件");
			item.setDescription("War包");
			list.add(item);
		}
		{
			OptionItem item = new OptionItem();
			item.setConfigType(configType);
			item.setDisplayName("组件类型");
			item.setCode("cmp_backend_svr");
			item.setValue("后端服务组件");
			item.setDescription("");
			list.add(item);
		}
		{
			OptionItem item = new OptionItem();
			item.setConfigType(configType);
			item.setDisplayName("组件类型");
			item.setCode("cmp_frontend");
			item.setValue("前端组件");
			item.setDescription("");
			list.add(item);
		}
		{
			OptionItem item = new OptionItem();
			item.setConfigType(configType);
			item.setDisplayName("组件类型");
			item.setCode("cmp_frontend_c#");
			item.setValue("前端C#组件");
			item.setDescription("");
			list.add(item);
		}
		{
			OptionItem item = new OptionItem();
			item.setConfigType(configType);
			item.setDisplayName("组件类型");
			item.setCode("cmp_ios");
			item.setValue("iOS安装包");
			item.setDescription("");
			list.add(item);
		}
		{
			OptionItem item = new OptionItem();
			item.setConfigType(configType);
			item.setDisplayName("组件类型");
			item.setCode("cmp_apk");
			item.setValue("安卓安装包");
			item.setDescription("");
			list.add(item);
		}
		return list;
	}


}
