package net.aicoder.epi.base.view.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class PropertyTableBak extends Composite {
	private static int VALUE_COLUMN_NO = 1;

	private Tree tree;
	private TreeViewer viewer;
	private PropsInput propsInput;
	private TreeEditor editor;

	private boolean m_showAdvancedProperties;
	private Map<TreeItem, TreeEditor> editorMap = new HashMap<TreeItem, TreeEditor>(0);

	// private TreeEditor editor;

	public PropertyTableBak(Composite parent) {
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

		attachModifyEditor();
	}

	public PropsInput getPropsInput() {
		return propsInput;
	}

	public void setPropsInput(PropsInput propsInput) {
		this.propsInput = propsInput;
		viewer.setInput(propsInput);
	}

	public void refresh() {
		//
		resetTreeItems();
		viewer.expandAll();
		viewer.refresh();
	}

	// 依据是否可以修改，显示不同底色
	private void resetTreeItems() {
		for (TreeItem treeItem : tree.getItems()) {

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

	//// Modify
	private void attachModifyEditor() {
		editor = new TreeEditor(tree);
		// The editor must have the same size as the cell and must not be any smaller
		// than 50 pixels.
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;

		tree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// Clean up any previous editor control
				// Identify the selected row
				final TreeItem treeItem = (TreeItem) e.item;
				Control oldEditor = editor.getEditor();
				if (oldEditor != null) {
					oldEditor.dispose();
				}
				if (treeItem == null) {
					return;
				}

				PropertyInfo selectedProperty = getFirstSelectedItem();
				resetPropertyEditor(treeItem, selectedProperty);
			}
		});
	}

	private void resetPropertyEditor(TreeItem treeItem, PropertyInfo selectedProperty) {
		PitemDefine itemDefine = selectedProperty.getItemDefine();
		if (AiStringUtil.isEmpty(itemDefine.getCode())) {
			return;
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
			resetPropertyControl(treeItem, selectedProperty);
			break;
		default:
			break;
		}
	}

	private void resetPropertyControl(TreeItem treeItem, PropertyInfo selectedProperty) {
/**		
		IBaseVo currentData = propsInput.getCurrentData();
		currentData.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(BeanUtil.isEquals(evt.getOldValue(), evt.getNewValue())) {
					return;
				}
				String showValue = selectedProperty.getShowValue();
				editor.getItem().setText(VALUE_COLUMN_NO, showValue);
			}
		});
**/
		PitemDefine itemDefine = selectedProperty.getItemDefine();
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
			resetTextEditor(treeItem, selectedProperty);
			break;
		case Date:
			break;
		case DateTime:
			break;
		case ComboBox:
			resetComboBoxEditor(treeItem, selectedProperty);
			break;
		case CheckBox:
			resetCheckBoxEditor(treeItem, selectedProperty);
			break;
		default:
			break;
		}
	}

	private void resetTextEditor(TreeItem treeItem, PropertyInfo selectedProperty) {
		Text newEditor = new Text(tree, SWT.NONE);
		newEditor.setText(treeItem.getText(VALUE_COLUMN_NO));
		newEditor.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent me) {
				Text text = (Text) editor.getEditor();
				String value = text.getText();
				selectedProperty.setValue(value);

				//editor.getItem().setText(VALUE_COLUMN_NO, value);
			}
		});
		newEditor.selectAll();
		newEditor.setFocus();
		editor.setEditor(newEditor, treeItem, VALUE_COLUMN_NO);
	}

	private void resetComboBoxEditor(TreeItem treeItem, PropertyInfo selectedProperty) {
		List<OptionItem> comboBoxItemList = selectedProperty.getComboBoxItemList();
		if (comboBoxItemList == null) {
			return;
		}

		final CCombo newEditor = new CCombo(tree, SWT.READ_ONLY);
		for (OptionItem optionItem : comboBoxItemList) {
			newEditor.add(optionItem.getValue());
		}
		// Select the previously selected item from the cell
		newEditor.select(newEditor.indexOf(treeItem.getText(VALUE_COLUMN_NO)));

		// Compute the width for the editor Also, compute the column width, so that the
		// dropdown fits
		// editor.minimumWidth = newEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
		// tree.getColumn(VALUE_COLUMN_NO).setWidth(editor.minimumWidth);

		// Set the focus on the dropdown and set into the editor
		newEditor.setFocus();
		editor.setEditor(newEditor, treeItem, VALUE_COLUMN_NO);

		// Add a listener to set the selected item back into the cell
		final int col = VALUE_COLUMN_NO;
		newEditor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//treeItem.setText(col, newEditor.getText());
				int selectionIndex = newEditor.getSelectionIndex();

				OptionItem optionItem = selectedProperty.getComboBoxOptionItem(selectionIndex);
				Object value = optionItem.getCode();
				selectedProperty.setValue(value);

				// They selected an item; end the editing session
				newEditor.dispose();
			}
		});
	}

	// 这个有Bug
	private void resetCheckBoxEditor(TreeItem treeItem, PropertyInfo selectedProperty) {
		final Button newEditor = new Button(tree, SWT.CHECK);
		BooleanItem booleanItem = selectedProperty.getCheckBoxBooleanItem();
		newEditor.setSelection(booleanItem.isBooleanValue());
		newEditor.setText(booleanItem.getShowValue());

		newEditor.setFocus();
		editor.setEditor(newEditor, treeItem, VALUE_COLUMN_NO);

		// Add a listener to set the selected item back into the cell
		final int col = VALUE_COLUMN_NO;
		newEditor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				treeItem.setText(col, "");
				boolean selected = newEditor.getSelection();

				BooleanItem optionItem = selectedProperty.getCheckBoxBooleanItem(selected);
				Object value = optionItem.getDataValue();
				String showVlaue = optionItem.getShowValue();
				newEditor.setText(showVlaue);
				//treeItem.setText(col, newEditor.getText());

				selectedProperty.setValue(value);
				//selectedProperty.setShowValue(showVlaue);

				// They selected an item; end the editing session
				newEditor.dispose();
			}
		});
	}
}
