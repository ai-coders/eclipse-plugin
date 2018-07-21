package net.aicoder.epi.base.view.extend;

import java.util.List;

import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.model.OptionItem;

/**
 * 自定义下拉控件ComboBoxCellEditor
 * @author WQP
 *
 */
public class PullDownComboBoxCellEditor extends ComboBoxCellEditor {
	private List<OptionItem> refObjects;
	private String currentType;//当前编辑数据类型


	public PullDownComboBoxCellEditor() {
		super();
	}
	
	public PullDownComboBoxCellEditor(Composite parent, String[] items) {
		super(parent, items);
	}
	
	@SuppressWarnings("unchecked")
	public PullDownComboBoxCellEditor(Composite parent, String[] items, int style, String currentType, Object refObjects) {
		super(parent, items, style);
		this.refObjects = (List<OptionItem>) refObjects;
		this.currentType = currentType;
	}
	
	@Override
	protected Control createControl(Composite parent) {
		Control createControl = super.createControl(parent);
		return createControl;
	}
	
	@Override
	public void create(Composite parent) {
		super.create(parent);
		doGetValue();
		
	}
	
	@Override
	protected Object doGetValue() {
		Object doGetValue = super.doGetValue();
		int selection = 0;
		CCombo comboBox = (CCombo) getControl();

		if(doGetValue instanceof Integer) {
			selection = ((Integer) doGetValue).intValue();
		}else if(doGetValue instanceof String && ((String) doGetValue).length() > 0) {
			selection = Integer.parseInt(doGetValue.toString());
		}else {
			selection = 0;
		}
		if(selection < 0) selection = 0;
		
		comboBox.select(selection);
		return refObjects == null ? "":refObjects.get(selection).getValue();
	}
	
	@Override
	protected void doSetValue(Object value) {
		//此方法是下拉选择的值做相应设定处理
		
		CCombo comboBox = (CCombo) getControl();
		System.out.println(comboBox.getSelectionIndex());
	}
	
	@Override
	protected int getDoubleClickTimeout() {
		return super.getDoubleClickTimeout();
	}
	
	public String getCurrentType() {
		return currentType;
	}
}
