package net.aicoder.epi.base.view.property;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.TreeNodeVo;
import net.aicoder.epi.base.view.definer.property.PitemDefine;
import net.aicoder.epi.base.view.definer.property.PropCtgyEnum;

public class PropertyInfo extends TreeNodeVo {
	private static final long serialVersionUID = 1L;
	
	private PitemDefine itemDefine;
	private PropCtgyEnum propCtgy;

	
	private Object value;
	private String showValue;
	
	private Object defValue;
	
	private List<IBaseVo> fullChildrenList = new ArrayList<IBaseVo>(0);

	public PitemDefine getItemDefine() {
		return itemDefine;
	}
	
	public void setItemDefine(PitemDefine itemDefine) {
		this.itemDefine = itemDefine;
		propCtgy = PropCtgyEnum.forStr(itemDefine.getCategory());
	}
	
	public PropCtgyEnum getPropCtgy() {
		return propCtgy;
	}

	public List<IBaseVo> getFullChildrenList(){
		return fullChildrenList;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getShowValue() {
		return showValue;
	}

	public void setShowValue(String showValue) {
		this.showValue = showValue;
	}

	public Object getDefValue() {
		return defValue;
	}

	public void setDefValue(Object defValue) {
		this.defValue = defValue;
	}
}
