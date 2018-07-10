package net.aicoder.epi.base.view.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TreeEditor;
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

public class PropertyTable2 extends Composite {
	private static int VALUE_COLUMN_NO = 1;

	private Tree tree;
	private TreeViewer viewer;
	private PropsInput propsInput;
	//private TreeEditor editor;

	private boolean m_showAdvancedProperties;
	private Map<TreeItem, TreeEditor> editorMap = new HashMap<TreeItem, TreeEditor>(0);
	private Set<TreeItem> dirtyBackgroundSet = new HashSet<TreeItem>(0);

	// private TreeEditor editor;

	public PropertyTable2(Composite parent) {
		super(parent, SWT.NONE);
		setLayout(new FillLayout());

		tree = new Tree(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		viewer = new TreeViewer(tree);

		{
			TreeColumn column = new TreeColumn(tree, SWT.LEFT);
			column.setText("属性名称");
			column.setWidth(200);
		}
		/**
		 * { TreeColumn column = new TreeColumn(tree, SWT.LEFT); column.setText("属性代码");
		 * column.setWidth(100); }
		 **/
		{
			TreeColumn column = new TreeColumn(tree, SWT.LEFT);
			column.setText("属性值");
			column.setWidth(400);
		}

		IContentProvider contentProvider = new PropsContentProvider();
		viewer.setContentProvider(contentProvider);
		IBaseLabelProvider labelProvider = new PropsColLabelProvider();
		viewer.setLabelProvider(labelProvider);
		propsInput = new PropsInput();
		viewer.setInput(propsInput);

		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
	}

	public PropsInput getPropsInput() {
		return propsInput;
	}

	public void setPropsInput(PropsInput propsInput) {
		this.propsInput = propsInput;
		viewer.setInput(propsInput);
	}

	public void refresh() {
		disposeTree();

		resetTreeItems();
		viewer.expandAll();
		viewer.refresh();
	}

	// 依据是否可以修改，显示不同底色
	private void resetTreeItems() {
		TreeItem[] items = tree.getItems();
		if (items == null || items.length == 0) {
			return;
		}
		for (TreeItem treeItem : items) {
			resetOneTreeItem(treeItem);
		}
	}

	private void resetOneTreeItem(TreeItem treeItem) {
		Object itemData = treeItem.getData();
		if (itemData == null) {
			return;
		}
		PropertyInfo propertyInfo = (PropertyInfo) itemData;
		if (propertyCanModify(propertyInfo)) {
			attachTreeItemEditor(treeItem, propertyInfo);
		} else {
			treeItem.setBackground(VALUE_COLUMN_NO, Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
		}
	}

	public PropertyInfo getFirstSelectedItem() {
		PropertyInfo item = null;
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if (selection.size() == 0) {
			return null;
		}
		item = (PropertyInfo) selection.getFirstElement();
		return item;
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

		newEditor.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent me) {
				//Text text = (Text) editor.getEditor();
				editor.getItem().setText(VALUE_COLUMN_NO, newEditor.getText());
				String newValue = newEditor.getText();
				propertyInfo.setValue(newValue);
			}
		});
		newEditor.selectAll();
		newEditor.setFocus();

		IBaseVo currentData = propertyInfo.getCurrentData();
		currentData.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (BeanUtil.isEquals(event.getOldValue(), event.getNewValue())) {
					return;
				}
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

	private void disposeTree(){
		disposeEditors();
	}
	
	private void disposeEditors() {
		TreeEditor editor = null;
		Iterator<Entry<TreeItem, TreeEditor>> map1it = editorMap.entrySet().iterator();
		while (map1it.hasNext()) {
			Entry<TreeItem, TreeEditor> entry = map1it.next();
			editor = entry.getValue();
			if (editor.getEditor() != null) {
				Control editorControl = editor.getEditor();
				editorControl.dispose();
			}
			editor.dispose();
			editorMap.remove(entry.getKey());
		}
	}
	
	protected void revertToOriginalBackground()
	{
		Display display = Display.getCurrent();
		int columns = tree.getColumnCount();
		Iterator<TreeItem> iter = dirtyBackgroundSet.iterator();
		while(iter.hasNext())
		{
			TreeItem item = iter.next();
			for (int i=0;i<columns;i++)
			{
				item.setBackground(i, display.getSystemColor(SWT.COLOR_WHITE));
			}			
		}		
		dirtyBackgroundSet.clear();
	}

}
