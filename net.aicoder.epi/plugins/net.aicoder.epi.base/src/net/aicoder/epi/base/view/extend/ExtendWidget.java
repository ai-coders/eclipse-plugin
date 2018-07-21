package net.aicoder.epi.base.view.extend;
//package net.aicoder.epi.base.view.external;
//
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import java.util.List;
//
//import javax.activity.InvalidActivityException;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.custom.CCombo;
//import org.eclipse.swt.custom.TreeEditor;
//import org.eclipse.swt.events.FocusAdapter;
//import org.eclipse.swt.events.FocusEvent;
//import org.eclipse.swt.events.ModifyEvent;
//import org.eclipse.swt.events.ModifyListener;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Table;
//import org.eclipse.swt.widgets.Text;
//import org.eclipse.swt.widgets.Tree;
//import org.eclipse.swt.widgets.TreeItem;
//
//import net.aicoder.epi.base.model.AbstractBaseVo;
//import net.aicoder.epi.base.model.BaseVo;
//import net.aicoder.epi.base.model.BooleanItem;
//import net.aicoder.epi.base.model.IBaseVo;
//import net.aicoder.epi.base.model.OptionItem;
//import net.aicoder.epi.base.model.TreeNodeVo;
//import net.aicoder.epi.base.model.property.PitemDefine;
//import net.aicoder.epi.base.model.property.PropControlEnum;
//import net.aicoder.epi.base.model.property.PropIoFlagEnum;
//import net.aicoder.epi.base.view.definer.IColumnDefiner;
//import net.aicoder.epi.base.view.definer.ViewDefiner;
//import net.aicoder.epi.base.view.property.PropertyInfo;
//import net.aicoder.tcom.tools.util.AiStringUtil;
//import net.aicoder.tcom.tools.util.BeanUtil;
//
//public class ExtendWidget<T> {
//	private static int NAME_COLUMN_NO = 0;
//	private static int VALUE_COLUMN_NO = 1;
//	
//	/**
//	 * 对tree/table绑定扩展控件
//	 * @param component tree/table组件对象
//	 * @param viewDefiner tree/table组件列的定义
//	 * @param baseVos tree/table组件数据集
//	 */
//	public void renderWidget(T component,ViewDefiner viewDefiner,IBaseVo... baseVos) {
//		if(component instanceof Tree) {
//			if(baseVos == null || baseVos.length == 0) return;
//			Tree tree = (Tree) component;
//			
//			for (IBaseVo ibv : baseVos) {
//				TreeNodeVo treeNodeVo = (TreeNodeVo) ibv;
//				IColumnDefiner columnDefinerByCode = viewDefiner.getColumnDefinerByCode(treeNodeVo.getCode());
//				String editFlag = columnDefinerByCode.getEditFlag();
//				
//				renderTreeWidget(tree,editFlag, treeNodeVo);
//			}
//		}else if(component instanceof Table){
//			
//			
//			
//		}else {
//			try {
//				throw new InvalidActivityException("暂不支持");
//			} catch (InvalidActivityException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	/**
//	 * 
//	 * @param parent tree组件对象
//	 * @param editFlag 编辑的标志,有Text,Date,DateTime,ComboBox,CheckBox
//	 * @param treeNodeVo 当前组件数据
//	 */
//	private void renderTreeWidget(Tree parent,String editFlag, TreeNodeVo treeNodeVo) {
//		TreeItem treeItem = new TreeItem(parent, SWT.NONE);
//		resetOneTreeItem(treeItem, treeNodeVo);
//
//		List<IBaseVo> childrenList = treeNodeVo.getChildrenList();
//		if (childrenList == null) {
//			return;
//		}
//		for (IBaseVo child : childrenList) {
//			if (child != null) {
//				renderTreeItemWidget(treeItem, (TreeNodeVo) child);
//			}
//		}
//		treeItem.setExpanded(true);
//	}
//	
//	private void renderTreeItemWidget(TreeItem parent, TreeNodeVo treeNodeVo) {
//		if (treeNodeVo == null) {
//			return;
//		}
//		TreeItem treeItem = new TreeItem(parent, SWT.NONE);
//		resetOneTreeItem(treeItem, treeNodeVo);
//
//		List<IBaseVo> childrenList = treeNodeVo.getChildrenList();
//		if (childrenList == null) {
//			return;
//		}
//		for (IBaseVo child : childrenList) {
//			if (child != null) {
//				renderTreeItemWidget(treeItem, (TreeNodeVo) child);
//			}
//		}
//	}
//	
//	private void resetOneTreeItem(TreeItem treeItem, TreeNodeVo propertyInfo) {
//		treeItem.setText(NAME_COLUMN_NO, getPropertyName(propertyInfo));
//		//treeItem.setText(VALUE_COLUMN_NO, propertyInfo.getShowValue());
//		attachTreeItemEditor(treeItem, propertyInfo);
//	}
//	
//	private String getPropertyName(TreeNodeVo propertyInfo) {
//		String text = "";
//		
//		return text;
//	}
//	
//	private void attachTreeItemEditor(TreeItem treeItem, TreeNodeVo propertyInfo) {
//		PitemDefine itemDefine = propertyInfo.getItemDefine();
//		String controlType = "";
//		propertyInfo.getD
//		if (itemDefine != null && itemDefine.getControl() != null) {
//			controlType = itemDefine.getControl().getType();
//		}
//		if (controlType == null) {
//			controlType = "";
//		} else {
//			controlType = controlType.toUpperCase();
//		}
//		PropControlEnum controlTypeEnum = PropControlEnum.forStr(controlType);
//		switch (controlTypeEnum) {
//		case NONE:
//		case Text:
//			resetTextEditor(treeItem, propertyInfo);
//			break;
//		case Date:
//			resetTextEditor(treeItem, propertyInfo);
//			break;
//		case DateTime:
//			resetTextEditor(treeItem, propertyInfo);
//			break;
//		case ComboBox:
//			resetComboBoxEditor(treeItem, propertyInfo);
//			break;
//		case CheckBox:
//			resetCheckBoxEditor(treeItem, propertyInfo);
//			break;
//		default:
//			break;
//		}
//	}
//	
//	
//	private void resetTextEditor(TreeItem treeItem, TreeNodeVo propertyInfo) {
//		TreeEditor editor = new TreeEditor(tree);
//		editor.horizontalAlignment = SWT.LEFT;
//		editor.grabHorizontal = true;
//		editor.minimumWidth = 50;
//
//		final Text newEditor = new Text(tree, SWT.NONE);
//		editor.setEditor(newEditor, treeItem, VALUE_COLUMN_NO);
//		editorMap.put(treeItem, editor);
//
//		newEditor.setText(propertyInfo.getShowValue());
//		//newEditor.selectAll();
//		//newEditor.setFocus();
//
//		if (!propertyCanModify(propertyInfo)) {
//			newEditor.setEditable(false);
//			return;
//		}
//
//		// 开始编辑的事件
//		newEditor.addModifyListener(new ModifyListener() {
//			@Override
//			public void modifyText(ModifyEvent me) {
//				//Text text = (Text) editor.getEditor();
//				//newEditor.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
//				String newValue = newEditor.getText();
//				editor.getItem().setText(VALUE_COLUMN_NO, newValue);
//				//isEditorModify = true;
//				//propertyInfo.setValue(newValue);
//			}
//		});
//		//编辑后光标丢失事件
//		newEditor.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				String newValue = newEditor.getText();
//
//				//校验当前值跟原始值/上次编辑值对比
//				String propertyCode = propertyInfo.getItemDefine().getCode();
//				Object oldValue = propertyInfo.getPropertyValue(propertyCode);
//				Object origValue = propertyInfo.getPropertyOrigValue(propertyCode);
//				
//				if(BeanUtil.isEquals(origValue, newValue)) {
//					//恢复默认颜色
//					newEditor.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
//				}else {
//					if(BeanUtil.isEquals(oldValue, newValue)) {
//						//与上一次值一致则不做处理
//					}else {
//						newEditor.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW));
//					}
//				}
//				propertyInfo.setValue(newValue);
//				
//				
////				if(propertyInfo.isModified()) {
////					newEditor.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW));
////					//dirtyBackgroundSet.add(treeItem);
////					
////					//设置颜色和验证
////					
////					
////					
////				}
//			}
//		});
//
//		IBaseVo currentData = propertyInfo.getCurrentData();
//		currentData.addPropertyChangeListener(new PropertyChangeListener() {
//			@Override
//			public void propertyChange(PropertyChangeEvent event) {
//				if (BeanUtil.isEquals(event.getOldValue(), event.getNewValue())) {
//					return;
//				}
//				//if()
//				newEditor.setText(propertyInfo.getShowValue());
//				//String showValue = propertyInfo.getShowValue();
//				//treeItem.setText(VALUE_COLUMN_NO, showValue);
//			}
//		});
//	}
//
//	private void resetComboBoxEditor(Tree tree,TreeItem treeItem,TreeNodeVo treeNodeVo, List<Object> treeNodeVos) {
//		if (treeNodeVos == null) {
//			return;
//		}
//
//		TreeEditor editor = new TreeEditor(tree);
//		editor.horizontalAlignment = SWT.LEFT;
//		editor.grabHorizontal = true;
//		editor.minimumWidth = 50;
//
//		final CCombo newEditor = new CCombo(tree, SWT.READ_ONLY);
//		for (TreeNodeVo optionItem : treeNodeVos) {
//			newEditor.add(optionItem.getName());
//		}
//		editor.setEditor(newEditor, treeItem, VALUE_COLUMN_NO);
//		newEditor.select(newEditor.indexOf(treeNodeVos.get(0).getName()));
//
//		newEditor.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent event) {
//				//treeItem.setText(col, newEditor.getText());
//				int selectionIndex = newEditor.getSelectionIndex();
//				TreeNodeVo treeNodeVo = treeNodeVos.get(selectionIndex);
//				OptionItem optionItem = propertyInfo.getComboBoxOptionItem(selectionIndex);
//				Object value = optionItem.getCode();
//				propertyInfo.setValue(value);
//			}
//		});
//
//		IBaseVo currentData = propertyInfo.getCurrentData();
//		currentData.addPropertyChangeListener(new PropertyChangeListener() {
//			@Override
//			public void propertyChange(PropertyChangeEvent event) {
//				if (BeanUtil.isEquals(event.getOldValue(), event.getNewValue())) {
//					return;
//				}
//				newEditor.select(newEditor.indexOf(propertyInfo.getShowValue()));
//			}
//		});
//	}
//
//	private void resetCheckBoxEditor(TreeItem treeItem, TreeNodeVo propertyInfo) {
//		TreeEditor editor = new TreeEditor(tree);
//		editor.horizontalAlignment = SWT.LEFT;
//		editor.grabHorizontal = true;
//		editor.minimumWidth = 50;
//
//		final Button newEditor = new Button(tree, SWT.CHECK);
//		editor.setEditor(newEditor, treeItem, VALUE_COLUMN_NO);
//		editorMap.put(treeItem, editor);
//
//		{
//			BooleanItem booleanItem = propertyInfo.getCheckBoxBooleanItem();
//			newEditor.setSelection(booleanItem.isBooleanValue());
//			newEditor.setText(booleanItem.getShowValue());
//		}
//
//		if (!propertyCanModify(propertyInfo)) {
//			//newEditor.setEditable(false);
//			newEditor.setEnabled(false);
//			return;
//		}
//
//		//newEditor.setFocus();
//
//		newEditor.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent event) {
//				boolean selected = newEditor.getSelection();
//				BooleanItem optionItem = propertyInfo.getCheckBoxBooleanItem(selected);
//				Object value = optionItem.getDataValue();
//				String showVlaue = optionItem.getShowValue();
//				newEditor.setText(showVlaue);
//
//				propertyInfo.setValue(value);
//			}
//		});
//
//		IBaseVo currentData = propertyInfo.getCurrentData();
//		currentData.addPropertyChangeListener(new PropertyChangeListener() {
//			@Override
//			public void propertyChange(PropertyChangeEvent event) {
//				if (BeanUtil.isEquals(event.getOldValue(), event.getNewValue())) {
//					return;
//				}
//
//				BooleanItem booleanItem = propertyInfo.getCheckBoxBooleanItem();
//				newEditor.setSelection(booleanItem.isBooleanValue());
//				newEditor.setText(booleanItem.getShowValue());
//			}
//		});
//	}
//	
//	
//	
//	
//}
