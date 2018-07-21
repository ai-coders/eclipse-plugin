package net.aicoder.epi.base.view.control.tree;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.custom.CCombo;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.definer.IViewDefiner;
import net.aicoder.epi.base.view.extend.DatePickerCombo;
import net.aicoder.epi.base.view.extend.DatePickerCombo.DatePickerComboCellEditor;
import net.aicoder.epi.base.view.extend.PullDownComboBoxCellEditor;
import net.aicoder.tcom.tools.util.BeanUtil;

public class EpiTreeContentProvider implements ITreeContentProvider{
	protected IViewDefiner viewDefiner;
	protected TreeViewer viewer;
	
	public EpiTreeContentProvider(IViewDefiner viewDefiner) {
		super();
		setViewDefiner(viewDefiner);
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (TreeViewer)viewer;
//		if(newInput != null) {
//			//((EpiAdapter)oldInput).addPropertyChangeListener(listener); //未作处理
//		}
//		if(oldInput != null) {
//			//((EpiAdapter)oldInput).removePropertyChangeListener(listener); //未作处理
//			oldInput = null;
//		}
		
		if(oldInput != null && newInput != null) {
			EpiInput epiInput = (EpiInput)newInput;
//			List<IBaseVo> dataList = epiInput.getDataList();
			IBaseVo currentData = epiInput.getCurrentData();
			
			CellEditor[] cellEditors = this.viewer.getCellEditors();
			if(cellEditors != null) {
				for (CellEditor cellEditor : cellEditors) {
					if(cellEditor instanceof PullDownComboBoxCellEditor) {//下拉选择控件
						PullDownComboBoxCellEditor baseComboBoxCellEditor = (PullDownComboBoxCellEditor)cellEditor;
						CCombo cCombo = (CCombo) baseComboBoxCellEditor.getControl();
						String[] items = cCombo.getItems();
						for (int i = 0; i < items.length; i++) {
							try {
								Object propertyValue = BeanUtil.getPropertyValue(currentData, baseComboBoxCellEditor.getCurrentType());
								if(items[i].equals(propertyValue)) {
									cCombo.select(i);
									cCombo.setText(items[i]);
									break;
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}else if(cellEditor instanceof DatePickerComboCellEditor) {//日期选择控件
						DatePickerComboCellEditor dpcce = (DatePickerComboCellEditor) cellEditor;
						DatePickerCombo datePickerCombo = (DatePickerCombo) dpcce.getControl();
						Date createAt = currentData.getCreateAt();
						String format = new SimpleDateFormat("yyyy-MM-dd").format(createAt);
						
						datePickerCombo.setText(format);
						
					}
				}
			}
		}
		
	}

	//// getter/setter
	@Override
	public Object[] getElements(Object input) {
		Object[] elements = new Object[0];
		if (input instanceof IEpiInput) {
			IEpiInput adapter = (IEpiInput) input;
			if(adapter != null && adapter.getDataList() != null) {
				elements = adapter.getDataList().toArray();
			}
		}
		return elements;
	}

	@Override
	public Object getParent(Object element) {
		Object parent = null;
		if (element instanceof ITreeNode) {
			parent = ((ITreeNode) element).getParentNode();
		}
		return parent;
	}

	@Override
	public boolean hasChildren(Object element) {
		boolean hasChildren = false;
		if (element instanceof ITreeNode) {
			hasChildren = ((ITreeNode) element).hasChildren();
		}
		return hasChildren;
	}

	@Override
	public Object[] getChildren(Object element) {
		Object[] children = new Object[0];
		if (element instanceof ITreeNode) {
			List<IBaseVo> list = ((ITreeNode) element).getChildrenList();
			if(list != null) {
				children = list.toArray();
			}
		}
		return children;
	}

	public IViewDefiner getViewDefiner() {
		return viewDefiner;
	}

	public void setViewDefiner(IViewDefiner viewDefiner) {
		this.viewDefiner = viewDefiner;
	}
}
