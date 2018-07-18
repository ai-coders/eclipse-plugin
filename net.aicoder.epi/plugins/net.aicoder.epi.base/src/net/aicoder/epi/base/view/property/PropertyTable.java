package net.aicoder.epi.base.view.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import net.aicoder.epi.base.model.BooleanItem;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.OptionItem;
import net.aicoder.epi.base.model.property.PitemDefine;
import net.aicoder.epi.base.model.property.PropControlEnum;
import net.aicoder.epi.base.model.property.PropIoFlagEnum;
import net.aicoder.tcom.tools.util.AiStringUtil;
import net.aicoder.tcom.tools.util.BeanUtil;

public class PropertyTable extends Composite {
	private static int NAME_COLUMN_NO = 0;
	private static int VALUE_COLUMN_NO = 1;

	private Tree tree;
	private PropsInput propsInput;

	private boolean m_showAdvancedProperties;
	private Map<TreeItem, TreeEditor> editorMap = new HashMap<TreeItem, TreeEditor>(0);
	private Set<TreeItem> dirtyBackgroundSet = new HashSet<TreeItem>(0);

	public PropertyTable(Composite parent) {
		super(parent, SWT.NONE);
		setLayout(new FillLayout());
		tree = new Tree(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		{
			TreeColumn column = new TreeColumn(tree, SWT.LEFT);
			column.setText("属性名称");
			column.setWidth(200);
		}
		{
			TreeColumn column = new TreeColumn(tree, SWT.LEFT);
			column.setText("属性值");
			column.setWidth(400);
		}

		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		
		//监听对table的双击事件，执行可编辑操作
		//tree.addListener(SWT.MouseDoubleClick, editorListener);
	}

	public void refresh() {
		disposeTree();

		//resetTreeTable();
		resetTreeItems();
	}

	private void resetTreeItems() {
		PropertyInfo[] treeItemElements = propsInput.getPropertyInfos();
		if (treeItemElements == null || treeItemElements.length == 0) {
			return;
		}
		for (PropertyInfo propertyInfo : treeItemElements) {
			renderPropertyInfo(tree, propertyInfo);
		}
	}

	private void renderPropertyInfo(Tree parent, PropertyInfo propertyInfo) {
		if (propertyInfo == null) {
			return;
		}
		TreeItem treeItem = new TreeItem(parent, SWT.NONE);
		resetOneTreeItem(treeItem, propertyInfo);

		List<IBaseVo> childrenList = propertyInfo.getChildrenList();
		if (childrenList == null) {
			return;
		}
		for (IBaseVo child : childrenList) {
			if (child != null) {
				renderPropertyInfo(treeItem, (PropertyInfo) child);
			}
		}
		treeItem.setExpanded(true);
	}

	private void renderPropertyInfo(TreeItem parent, PropertyInfo propertyInfo) {
		if (propertyInfo == null) {
			return;
		}
		TreeItem treeItem = new TreeItem(parent, SWT.NONE);
		resetOneTreeItem(treeItem, propertyInfo);

		List<IBaseVo> childrenList = propertyInfo.getChildrenList();
		if (childrenList == null) {
			return;
		}
		for (IBaseVo child : childrenList) {
			if (child != null) {
				renderPropertyInfo(treeItem, (PropertyInfo) child);
			}
		}
	}

	private void resetOneTreeItem(TreeItem treeItem, PropertyInfo propertyInfo) {
		treeItem.setText(NAME_COLUMN_NO, getPropertyName(propertyInfo));
		//treeItem.setText(VALUE_COLUMN_NO, propertyInfo.getShowValue());
		attachTreeItemEditor(treeItem, propertyInfo);
	}

	private void attachTreeItemEditor(TreeItem treeItem, PropertyInfo propertyInfo) {
		PitemDefine itemDefine = propertyInfo.getItemDefine();
		String controlType = "";
		if (itemDefine != null && itemDefine.getControl() != null) {
			controlType = itemDefine.getControl().getType();
		}
		if (controlType == null) {
			controlType = "";
		} else {
			controlType = controlType.toUpperCase();
		}
		PropControlEnum controlTypeEnum = PropControlEnum.forStr(controlType);
		switch (controlTypeEnum) {
		case NONE:
		case Text:
			resetTextEditor(treeItem, propertyInfo);
			break;
		case Date:
			resetTextEditor(treeItem, propertyInfo);
			break;
		case DateTime:
			resetTextEditor(treeItem, propertyInfo);
			break;
		case ComboBox:
			resetComboBoxEditor(treeItem, propertyInfo);
			break;
		case CheckBox:
			resetCheckBoxEditor(treeItem, propertyInfo);
			break;
		default:
			break;
		}
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

	private void resetTextEditor(TreeItem treeItem, PropertyInfo propertyInfo) {
		TreeEditor editor = new TreeEditor(tree);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;

		final Text newEditor = new Text(tree, SWT.NONE);
		editor.setEditor(newEditor, treeItem, VALUE_COLUMN_NO);
		editorMap.put(treeItem, editor);

		newEditor.setText(propertyInfo.getShowValue());
		//newEditor.selectAll();
		//newEditor.setFocus();

		if (!propertyCanModify(propertyInfo)) {
			newEditor.setEditable(false);
			return;
		}

		// 开始编辑的事件
		newEditor.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent me) {
				//Text text = (Text) editor.getEditor();
				//newEditor.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
				String newValue = newEditor.getText();
				editor.getItem().setText(VALUE_COLUMN_NO, newValue);
				//isEditorModify = true;
				//propertyInfo.setValue(newValue);
			}
		});
		//编辑后光标丢失事件
		newEditor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String newValue = newEditor.getText();

				//校验当前值跟原始值/上次编辑值对比
				String propertyCode = propertyInfo.getItemDefine().getCode();
				Object oldValue = propertyInfo.getCurrentData().getPropertyValue(propertyCode);
				Object origValue = propertyInfo.getCurrentData().getPropertyOrigValue(propertyCode);
				
				if(BeanUtil.isEquals(origValue, newValue)) {
					//恢复默认颜色
					newEditor.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
				}else {
					if(BeanUtil.isEquals(oldValue, newValue)) {
						//与上一次值一致则不做处理
					}else {
						newEditor.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW));
					}
				}
				propertyInfo.setValue(newValue);
				
				
//				if(propertyInfo.isModified()) {
//					newEditor.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW));
//					//dirtyBackgroundSet.add(treeItem);
//					
//					//设置颜色和验证
//					
//					
//					
//				}
			}
		});

		IBaseVo currentData = propertyInfo.getCurrentData();
		currentData.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (BeanUtil.isEquals(event.getOldValue(), event.getNewValue())) {
					return;
				}
				//if()
				newEditor.setText(propertyInfo.getShowValue());
				//String showValue = propertyInfo.getShowValue();
				//treeItem.setText(VALUE_COLUMN_NO, showValue);
			}
		});
	}

	private void resetComboBoxEditor(TreeItem treeItem, PropertyInfo propertyInfo) {
		List<OptionItem> comboBoxItemList = propertyInfo.getComboBoxItemList();
		if (comboBoxItemList == null) {
			return;
		}

		TreeEditor editor = new TreeEditor(tree);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;

		final CCombo newEditor = new CCombo(tree, SWT.READ_ONLY);
		for (OptionItem optionItem : comboBoxItemList) {
			newEditor.add(optionItem.getValue());
		}
		editor.setEditor(newEditor, treeItem, VALUE_COLUMN_NO);
		editorMap.put(treeItem, editor);

		newEditor.select(newEditor.indexOf(propertyInfo.getShowValue()));
		//newEditor.setFocus();

		if (!propertyCanModify(propertyInfo)) {
			newEditor.setEditable(false);
			return;
		}

		newEditor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//treeItem.setText(col, newEditor.getText());
				int selectionIndex = newEditor.getSelectionIndex();
				OptionItem optionItem = propertyInfo.getComboBoxOptionItem(selectionIndex);
				Object value = optionItem.getCode();
				propertyInfo.setValue(value);
			}
		});

		IBaseVo currentData = propertyInfo.getCurrentData();
		currentData.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (BeanUtil.isEquals(event.getOldValue(), event.getNewValue())) {
					return;
				}
				newEditor.select(newEditor.indexOf(propertyInfo.getShowValue()));
			}
		});
	}

	private void resetCheckBoxEditor(TreeItem treeItem, PropertyInfo propertyInfo) {
		TreeEditor editor = new TreeEditor(tree);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;

		final Button newEditor = new Button(tree, SWT.CHECK);
		editor.setEditor(newEditor, treeItem, VALUE_COLUMN_NO);
		editorMap.put(treeItem, editor);

		{
			BooleanItem booleanItem = propertyInfo.getCheckBoxBooleanItem();
			newEditor.setSelection(booleanItem.isBooleanValue());
			newEditor.setText(booleanItem.getShowValue());
		}

		if (!propertyCanModify(propertyInfo)) {
			//newEditor.setEditable(false);
			newEditor.setEnabled(false);
			return;
		}

		//newEditor.setFocus();

		newEditor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				boolean selected = newEditor.getSelection();
				BooleanItem optionItem = propertyInfo.getCheckBoxBooleanItem(selected);
				Object value = optionItem.getDataValue();
				String showVlaue = optionItem.getShowValue();
				newEditor.setText(showVlaue);

				propertyInfo.setValue(value);
			}
		});

		IBaseVo currentData = propertyInfo.getCurrentData();
		currentData.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (BeanUtil.isEquals(event.getOldValue(), event.getNewValue())) {
					return;
				}

				BooleanItem booleanItem = propertyInfo.getCheckBoxBooleanItem();
				newEditor.setSelection(booleanItem.isBooleanValue());
				newEditor.setText(booleanItem.getShowValue());
			}
		});
	}
	
/**	
	public void bindPropertyDataEvent() {
		IBaseVo currentData = propsInput.getCurrentData();
		currentData.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (BeanUtil.isEquals(event.getOldValue(), event.getNewValue())) {
					return;
				}
				String propertyCode = event.getPropertyName();
				setPropertyValue(propertyCode);
			}
		});
	}
	
	private void setPropertyValue(String propertyCode) {
		
	}
**/
	
	private boolean propertyCanModify(PropertyInfo propertyInfo) {
		boolean canPropertyModify = false;
		PitemDefine itemDefine = propertyInfo.getItemDefine();
		if (AiStringUtil.isEmpty(itemDefine.getCode())) {
			return canPropertyModify;
		}
		String ioFlag = "";
		if (itemDefine != null && itemDefine.getControl() != null) {
			ioFlag = itemDefine.getControl().getIoFlag();
		}
		if (ioFlag == null) {
			ioFlag = "";
		} else {
			ioFlag = ioFlag.toUpperCase();
		}
		PropIoFlagEnum ioFlagEnum = PropIoFlagEnum.forStr(ioFlag);
		switch (ioFlagEnum) {
		case NONE:
		case CanbeNull:
		case NotNull:
			canPropertyModify = true;
			break;
		default:
			canPropertyModify = false;
			break;
		}
		return canPropertyModify;
	}

	private void disposeTree() {
		TreeItem[] items = tree.getItems();
		if (items == null || items.length == 0) {
			return;
		}
		for (TreeItem treeItem : items) {
			disposeTreeItem(treeItem);
		}
		tree.removeAll();
		editorMap.clear();
	}

	private void disposeTreeItem(TreeItem treeItem) {
		TreeEditor editor = null;
		if (editorMap.containsKey(treeItem)) {
			editor = editorMap.get(treeItem);
			if (editor.getEditor() != null) {
				Control editorControl = editor.getEditor();
				editorControl.dispose();
			}
			editorMap.remove(treeItem);
		}
		TreeItem[] items = treeItem.getItems();
		if (items == null || items.length == 0) {
			return;
		}
		for (TreeItem subTreeItem : items) {
			disposeTreeItem(subTreeItem);
		}
	}

	//// Access
	/**
	 * Shows or hides {@link Property}-s with {@link PropertyCategory#ADVANCED}.
	 */
	public void setShowAdvancedProperties(boolean showAdvancedProperties) {
		m_showAdvancedProperties = showAdvancedProperties;
		propsInput.setShowAdvancedProperties(m_showAdvancedProperties);
		refresh();
	}

	//// getter/setter
	public PropsInput getPropsInput() {
		return propsInput;
	}

	public void setPropsInput(PropsInput propsInput) {
		this.propsInput = propsInput;
	}
}
