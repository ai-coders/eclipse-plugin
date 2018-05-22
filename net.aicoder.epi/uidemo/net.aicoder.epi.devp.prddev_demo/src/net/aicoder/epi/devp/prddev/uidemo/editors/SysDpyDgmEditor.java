package net.aicoder.epi.devp.prddev.uidemo.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TabFolder;

public class SysDpyDgmEditor extends EditorPart {

	public static final String ID = SysDpyDgmEditor.class.getName(); //$NON-NLS-1$

	public SysDpyDgmEditor() {
		super();
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FormLayout());
		
		ToolBar toolBar = new ToolBar(container, SWT.FLAT | SWT.RIGHT);
		FormData fd_toolBar = new FormData();
		fd_toolBar.right = new FormAttachment(0, 584);
		fd_toolBar.top = new FormAttachment(0, 10);
		fd_toolBar.left = new FormAttachment(0, 10);
		toolBar.setLayoutData(fd_toolBar);
		
		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.NONE);
		toolItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		toolItem_1.setText("新增(子)");
		
		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.setText("新增");
		
		ToolItem toolItem_2 = new ToolItem(toolBar, SWT.NONE);
		toolItem_2.setText("上移");
		
		ToolItem toolItem_3 = new ToolItem(toolBar, SWT.NONE);
		toolItem_3.setText("下移");
		
		ToolItem toolItem_4 = new ToolItem(toolBar, SWT.NONE);
		toolItem_4.setText("升级");
		
		ToolItem toolItem_5 = new ToolItem(toolBar, SWT.NONE);
		toolItem_5.setText("降级");
		
		ToolItem toolItem_6 = new ToolItem(toolBar, SWT.NONE);
		toolItem_6.setText("删除");
		
		ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
		toolItem.setText("刷新");
		
		Tree tree = new Tree(container, SWT.BORDER);
		FormData fd_tree = new FormData();
		fd_tree.right = new FormAttachment(toolBar, 246, SWT.RIGHT);
		fd_tree.bottom = new FormAttachment(toolBar, 263, SWT.BOTTOM);
		fd_tree.top = new FormAttachment(toolBar, 6);
		fd_tree.left = new FormAttachment(toolBar, 0, SWT.LEFT);
		
		ToolItem toolItem_7 = new ToolItem(toolBar, SWT.NONE);
		toolItem_7.setText("保存");
		tree.setLayoutData(fd_tree);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		
		TreeColumn treeColumn = new TreeColumn(tree, SWT.NONE);
		treeColumn.setWidth(159);
		treeColumn.setText("名称*");
		
		TreeColumn trclmnNewColumn = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn.setWidth(95);
		trclmnNewColumn.setText("代码");
		
		TreeColumn trclmnNewColumn_1 = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn_1.setWidth(99);
		trclmnNewColumn_1.setText("别名");
		
		TreeColumn treeColumn_2 = new TreeColumn(tree, SWT.NONE);
		treeColumn_2.setWidth(44);
		treeColumn_2.setText("类型");
		
		TreeColumn treeColumn_5 = new TreeColumn(tree, SWT.NONE);
		treeColumn_5.setWidth(70);
		treeColumn_5.setText("子类型");
		
		TreeColumn treeColumn_3 = new TreeColumn(tree, SWT.NONE);
		treeColumn_3.setWidth(54);
		treeColumn_3.setText("版本");
		
		TreeColumn treeColumn_1 = new TreeColumn(tree, SWT.NONE);
		treeColumn_1.setWidth(156);
		treeColumn_1.setText("描述");
		
		TreeColumn trclmnNewColumn_2 = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn_2.setWidth(139);
		trclmnNewColumn_2.setText("备注");
		
		TreeItem trtmXxx = new TreeItem(tree, SWT.NONE);
		trtmXxx.setText("XXX环境");
		
		TreeItem trtmNewTreeitem = new TreeItem(trtmXxx, SWT.NONE);
		trtmNewTreeitem.setText("XXX节点/设备");
		
		TreeItem trtmNewTreeitem_1 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmNewTreeitem_1.setText("XXX组件");
		
		TreeItem trtmXxx_2 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmXxx_2.setText("XXX组件(外部)");
		trtmNewTreeitem.setExpanded(true);
		
		TreeItem trtmXxx_3 = new TreeItem(trtmXxx, SWT.NONE);
		trtmXxx_3.setText("XXX资源");
		trtmXxx.setExpanded(true);
		
		Label label = new Label(container, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 50);
		fd_label.left = new FormAttachment(tree, 37);
		label.setLayoutData(fd_label);
		label.setText("属性值");
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("当前元素");
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(tree, 6);
		fd_lblNewLabel.left = new FormAttachment(tree, 10, SWT.LEFT);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		
		ToolBar toolBar_1 = new ToolBar(container, SWT.FLAT | SWT.RIGHT);
		FormData fd_toolBar_1 = new FormData();
		fd_toolBar_1.top = new FormAttachment(tree, 6);
		fd_toolBar_1.left = new FormAttachment(lblNewLabel, 23);
		toolBar_1.setLayoutData(fd_toolBar_1);
		
		ToolItem toolItem_8 = new ToolItem(toolBar_1, SWT.NONE);
		toolItem_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		toolItem_8.setText("新增实例/删除/上移/下移");
		
		Label label_1 = new Label(container, SWT.NONE);
		FormData fd_label_1 = new FormData();
		fd_label_1.top = new FormAttachment(tree, 17);
		fd_label_1.left = new FormAttachment(toolBar_1, 132);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("关联元素");
		
		ToolBar toolBar_2 = new ToolBar(container, SWT.FLAT | SWT.RIGHT);
		FormData fd_toolBar_2 = new FormData();
		fd_toolBar_2.top = new FormAttachment(tree, 6);
		fd_toolBar_2.left = new FormAttachment(label_1, 12);
		toolBar_2.setLayoutData(fd_toolBar_2);
		
		ToolItem toolItem_9 = new ToolItem(toolBar_2, SWT.NONE);
		toolItem_9.setText("引用元素/新增实例/删除/上移/下移");
		
		Composite composite_1 = new Composite(container, SWT.NONE);
		TreeColumnLayout tcl_composite_1 = new TreeColumnLayout();
		composite_1.setLayout(tcl_composite_1);
		FormData fd_composite_1 = new FormData();
		fd_composite_1.bottom = new FormAttachment(toolBar_1, 275, SWT.BOTTOM);
		fd_composite_1.right = new FormAttachment(0, 346);
		fd_composite_1.top = new FormAttachment(toolBar_1, 18);
		fd_composite_1.left = new FormAttachment(0, 10);
		composite_1.setLayoutData(fd_composite_1);
		
		Tree tree_2 = new Tree(composite_1, SWT.BORDER);
		tree_2.setHeaderVisible(true);
		tree_2.setLinesVisible(true);
		
		TreeColumn treeColumn_4 = new TreeColumn(tree_2, SWT.NONE);
		tcl_composite_1.setColumnData(treeColumn_4, new ColumnPixelData(71, true, true));
		treeColumn_4.setText("名称*");
		
		TreeColumn treeColumn_6 = new TreeColumn(tree_2, SWT.NONE);
		tcl_composite_1.setColumnData(treeColumn_6, new ColumnPixelData(63, true, true));
		treeColumn_6.setText("代码");
		
		TreeColumn trclmnNewColumn_3 = new TreeColumn(tree_2, SWT.NONE);
		tcl_composite_1.setColumnData(trclmnNewColumn_3, new ColumnPixelData(61, true, true));
		trclmnNewColumn_3.setText("别名");
		
		TreeColumn treeColumn_7 = new TreeColumn(tree_2, SWT.NONE);
		tcl_composite_1.setColumnData(treeColumn_7, new ColumnPixelData(57, true, true));
		treeColumn_7.setText("类型");
		
		TreeColumn trclmnNewColumn_4 = new TreeColumn(tree_2, SWT.NONE);
		tcl_composite_1.setColumnData(trclmnNewColumn_4, new ColumnPixelData(67, true, true));
		trclmnNewColumn_4.setText("描述");
		
		TreeItem trtmXxx_1 = new TreeItem(tree_2, SWT.NONE);
		trtmXxx_1.setText("XXX组件");
		
		TreeItem treeItem = new TreeItem(trtmXxx_1, SWT.NONE);
		treeItem.setText("实例1");
		trtmXxx_1.setExpanded(true);
		
		Composite composite_2 = new Composite(container, SWT.NONE);
		TreeColumnLayout tcl_composite_2 = new TreeColumnLayout();
		composite_2.setLayout(tcl_composite_2);
		FormData fd_composite_2 = new FormData();
		fd_composite_2.bottom = new FormAttachment(label_1, 270, SWT.BOTTOM);
		fd_composite_2.right = new FormAttachment(composite_1, 471, SWT.RIGHT);
		fd_composite_2.top = new FormAttachment(label_1, 25);
		fd_composite_2.left = new FormAttachment(composite_1, 38);
		composite_2.setLayoutData(fd_composite_2);
		
		Tree tree_3 = new Tree(composite_2, SWT.BORDER);
		tree_3.setHeaderVisible(true);
		tree_3.setLinesVisible(true);
		
		TreeColumn trclmnNewColumn_5 = new TreeColumn(tree_3, SWT.NONE);
		tcl_composite_2.setColumnData(trclmnNewColumn_5, new ColumnPixelData(71, true, true));
		trclmnNewColumn_5.setText("关联方向");
		
		TreeColumn treeColumn_16 = new TreeColumn(tree_3, SWT.NONE);
		tcl_composite_2.setColumnData(treeColumn_16, new ColumnPixelData(74, true, true));
		treeColumn_16.setText("关联类型");
		
		TreeColumn treeColumn_12 = new TreeColumn(tree_3, SWT.NONE);
		tcl_composite_2.setColumnData(treeColumn_12, new ColumnPixelData(79, true, true));
		treeColumn_12.setText("关联元素");
		
		TreeColumn treeColumn_13 = new TreeColumn(tree_3, SWT.NONE);
		tcl_composite_2.setColumnData(treeColumn_13, new ColumnPixelData(62, true, true));
		treeColumn_13.setText("代码");
		
		TreeColumn treeColumn_14 = new TreeColumn(tree_3, SWT.NONE);
		tcl_composite_2.setColumnData(treeColumn_14, new ColumnPixelData(64, true, true));
		treeColumn_14.setText("别名");
		
		TreeColumn treeColumn_15 = new TreeColumn(tree_3, SWT.NONE);
		tcl_composite_2.setColumnData(treeColumn_15, new ColumnPixelData(126, true, true));
		treeColumn_15.setText("关联描述");
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.top = new FormAttachment(composite_1, 0, SWT.TOP);
		fd_lblNewLabel_1.left = new FormAttachment(composite_2, 62);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("关联属性值");
		
		Composite composite = new Composite(container, SWT.NONE);
		TreeColumnLayout tcl_composite = new TreeColumnLayout();
		composite.setLayout(tcl_composite);
		FormData fd_composite = new FormData();
		fd_composite.right = new FormAttachment(tree, 356, SWT.RIGHT);
		fd_composite.top = new FormAttachment(label, 24);
		fd_composite.left = new FormAttachment(tree, 37);
		composite.setLayoutData(fd_composite);
		
		Tree tree_1 = new Tree(composite, SWT.BORDER);
		tree_1.setHeaderVisible(true);
		tree_1.setLinesVisible(true);
		
		TreeColumn treeColumn_8 = new TreeColumn(tree_1, SWT.NONE);
		tcl_composite.setColumnData(treeColumn_8, new ColumnPixelData(150, true, true));
		treeColumn_8.setText("属性");
		
		TreeColumn treeColumn_9 = new TreeColumn(tree_1, SWT.NONE);
		tcl_composite.setColumnData(treeColumn_9, new ColumnPixelData(150, true, true));
		treeColumn_9.setText("值");
		
		TreeItem treeItem_1 = new TreeItem(tree_1, SWT.NONE);
		treeItem_1.setText("属性1");
		
		TreeItem treeItem_2 = new TreeItem(treeItem_1, SWT.NONE);
		treeItem_2.setText("属性11");
		treeItem_1.setExpanded(true);
		
		Composite composite_3 = new Composite(container, SWT.NONE);
		TreeColumnLayout tcl_composite_3 = new TreeColumnLayout();
		composite_3.setLayout(tcl_composite_3);
		FormData fd_composite_3 = new FormData();
		fd_composite_3.top = new FormAttachment(lblNewLabel_1, 16);
		fd_composite_3.right = new FormAttachment(composite_2, 355, SWT.RIGHT);
		fd_composite_3.left = new FormAttachment(composite_2, 61);
		composite_3.setLayoutData(fd_composite_3);
		
		Tree tree_4 = new Tree(composite_3, SWT.BORDER);
		tree_4.setHeaderVisible(true);
		tree_4.setLinesVisible(true);
		
		TreeColumn treeColumn_10 = new TreeColumn(tree_4, SWT.NONE);
		tcl_composite_3.setColumnData(treeColumn_10, new ColumnPixelData(150, true, true));
		treeColumn_10.setText("属性");
		
		TreeColumn treeColumn_11 = new TreeColumn(tree_4, SWT.NONE);
		tcl_composite_3.setColumnData(treeColumn_11, new ColumnPixelData(150, true, true));
		treeColumn_11.setText("值");
		
		TreeItem treeItem_3 = new TreeItem(tree_4, SWT.NONE);
		treeItem_3.setText("属性1");
		
		TreeItem treeItem_4 = new TreeItem(treeItem_3, SWT.NONE);
		treeItem_4.setText("属性11");
		treeItem_3.setExpanded(true);
		
		Label label_2 = new Label(container, SWT.NONE);
		FormData fd_label_2 = new FormData();
		fd_label_2.top = new FormAttachment(composite_1, 0, SWT.TOP);
		fd_label_2.left = new FormAttachment(lblNewLabel_1, 35);
		label_2.setLayoutData(fd_label_2);
		label_2.setText("被关联元素值");

	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		// Initialize the editor part
		this.setSite(site);
		this.setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
}
