package net.aicoder.epi.devp.prddev.dao.dev;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.OptionItem;

/**
 * 扩展
 * @author WQP
 *
 */
public class ExtendWidgetDao extends BaseDao {

	public List<OptionItem> loadCmpTypeList(String type){
		List<OptionItem> results = new ArrayList<>();
		{
			OptionItem oi = new OptionItem();
			oi.setRid(139099933L);
			oi.setTid(1L);
			oi.setCode("Code_1");
			oi.setConfigType("cmpType");
			oi.setDescription("描述小米组件");
			oi.setValue("小米组件");
			oi.setvIndex(1);
			oi.setDisplayName("小米组件");
			results.add(oi);
		}
		{
			OptionItem oi = new OptionItem();
			oi.setRid(139099934L);
			oi.setTid(1L);
			oi.setCode("Code_2");
			oi.setConfigType("cmpType");
			oi.setDescription("描述三星组件");
			oi.setValue("三星组件");
			oi.setvIndex(3);
			oi.setDisplayName("三星组件");
			results.add(oi);
		}
		{
			OptionItem oi = new OptionItem();
			oi.setRid(139099935L);
			oi.setTid(1L);
			oi.setCode("Code_3");
			oi.setConfigType("cmpType");
			oi.setDescription("描述华为组件");
			oi.setValue("华为组件");
			oi.setvIndex(2);
			oi.setDisplayName("华为组件");
			results.add(oi);
		}

		return results;
	}
}
