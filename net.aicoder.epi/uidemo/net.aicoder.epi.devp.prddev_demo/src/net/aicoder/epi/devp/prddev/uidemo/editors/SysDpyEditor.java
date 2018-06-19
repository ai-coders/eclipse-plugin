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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.jface.viewers.TableTreeViewer;

public class SysDpyEditor extends EditorPart {

	public static final String ID = SysDpyEditor.class.getName(); //$NON-NLS-1$
	private Table table;

	public SysDpyEditor() {
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
		fd_toolBar.left = new FormAttachment(0, 10);
		fd_toolBar.top = new FormAttachment(0, 10);
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
		
		ToolItem toolItem_7 = new ToolItem(toolBar, SWT.NONE);
		toolItem_7.setText("保存");
		
		Composite composite_cmp = new Composite(container, SWT.NONE);
		//composite_cmp.setLayoutData(new FormData());
		FormData fd_composite_cmp = new FormData();
		fd_composite_cmp.top = new FormAttachment(toolBar, 10);
		fd_composite_cmp.right = new FormAttachment(0, 515);
		fd_composite_cmp.left = new FormAttachment(0, 10);
		composite_cmp.setLayoutData(fd_composite_cmp);
	
		Tree tree = new Tree(composite_cmp, SWT.BORDER);
		tree.setLocation(0, 0);
		tree.setSize(505, 187);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		
		TreeColumn treeColumn = new TreeColumn(tree, SWT.NONE);
		treeColumn.setWidth(159);
		treeColumn.setText("名称*");
		
		TreeColumn trclmnNewColumn = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn.setWidth(58);
		trclmnNewColumn.setText("代码");
		
		TreeColumn trclmnNewColumn_1 = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn_1.setWidth(59);
		trclmnNewColumn_1.setText("别名");
		
		TreeColumn treeColumn_2 = new TreeColumn(tree, SWT.NONE);
		treeColumn_2.setWidth(69);
		treeColumn_2.setText("类型");
		
		TreeColumn treeColumn_3 = new TreeColumn(tree, SWT.NONE);
		treeColumn_3.setWidth(70);
		treeColumn_3.setText("版本");
		
		TreeColumn treeColumn_1 = new TreeColumn(tree, SWT.NONE);
		treeColumn_1.setWidth(81);
		treeColumn_1.setText("描述");
		
		TreeItem trtmXxx = new TreeItem(tree, SWT.NONE);
		trtmXxx.setText("XXX系统");
		
		TreeItem trtmNewTreeitem = new TreeItem(trtmXxx, SWT.NONE);
		trtmNewTreeitem.setText("XXX前端");
		
		TreeItem trtmNewTreeitem_1 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmNewTreeitem_1.setText("XXX组件");
		trtmNewTreeitem.setExpanded(true);
		
		TreeItem trtmXxx_3 = new TreeItem(trtmXxx, SWT.NONE);
		trtmXxx_3.setText("XXX后端");
		
		TreeItem trtmXxx_2 = new TreeItem(trtmXxx_3, SWT.NONE);
		trtmXxx_2.setText("XXX组件");
		
		TreeItem trtmNewTreeitem_2 = new TreeItem(trtmXxx_3, SWT.NONE);
		trtmNewTreeitem_2.setText("XXX组件");
		trtmXxx_3.setExpanded(true);
		trtmXxx.setExpanded(true);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("关联资源");
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(composite_cmp, 14);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		
		Button button_1 = new Button(container, SWT.NONE);
		FormData fd_button_1 = new FormData();
		fd_button_1.left = new FormAttachment(lblNewLabel, 110);
		button_1.setLayoutData(fd_button_1);
		button_1.setText("+|-|...");
		
		Composite composite_ref = new Composite(container, SWT.NONE);
		TableColumnLayout tcl_composite_ref = new TableColumnLayout();
		composite_ref.setLayout(tcl_composite_ref);
		FormData fd_composite_ref = new FormData();
		fd_composite_ref.right = new FormAttachment(toolBar, -280, SWT.RIGHT);
		fd_composite_ref.top = new FormAttachment(lblNewLabel, 6);
		fd_composite_ref.left = new FormAttachment(0, 10);
		fd_composite_ref.bottom = new FormAttachment(100, -46);
		composite_ref.setLayoutData(fd_composite_ref);
		
		table = new Table(composite_ref, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tcl_composite_ref.setColumnData(tableColumn_5, new ColumnPixelData(117, true, true));
		tableColumn_5.setText("关联类型");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tcl_composite_ref.setColumnData(tableColumn_4, new ColumnPixelData(91, true, true));
		tableColumn_4.setText("关联名称");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tcl_composite_ref.setColumnData(tableColumn_3, new ColumnPixelData(103, true, true));
		tableColumn_3.setText("产品/资源类型");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tcl_composite_ref.setColumnData(tableColumn_1, new ColumnPixelData(73, true, true));
		tableColumn_1.setText("代码");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tcl_composite_ref.setColumnData(tableColumn, new ColumnPixelData(108, true, true));
		tableColumn.setText("关联描述");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText("安装到");
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText("连接[-o)-]");
		
		TableItem tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setText("连接[-o]");
		
		TableItem tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_3.setText("连接[)-]");
		
		Label label_1 = new Label(container, SWT.NONE);
		fd_toolBar.right = new FormAttachment(label_1, -6);
		FormData fd_label_1 = new FormData();
		fd_label_1.left = new FormAttachment(0, 521);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("部署方案");
		
		Button btnNewButton = new Button(container, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("+");
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		FormData fd_button = new FormData();
		button.setLayoutData(fd_button);
		button.setText("+|-|...");
		
		Label label_2 = new Label(container, SWT.NONE);
		FormData fd_label_2 = new FormData();
		fd_label_2.left = new FormAttachment(button_1, 15);
		fd_label_2.bottom = new FormAttachment(lblNewLabel, 0, SWT.BOTTOM);
		label_2.setLayoutData(fd_label_2);
		label_2.setText("资源实例");
		//fd_composite_inst.top = new FormAttachment(button_2, 1);
		
		Button btny = new Button(container, SWT.NONE);
		fd_button_1.top = new FormAttachment(0, 244);
		FormData fd_btny = new FormData();
		fd_btny.right = new FormAttachment(composite_cmp, 0, SWT.RIGHT);
		fd_btny.bottom = new FormAttachment(button_1, 0, SWT.BOTTOM);
		btny.setLayoutData(fd_btny);
		btny.setText("+|-|Y");
		
		Composite composite_inst = new Composite(container, SWT.NONE);
		TreeColumnLayout tcl_composite_inst = new TreeColumnLayout();
		composite_inst.setLayout(tcl_composite_inst);
		FormData fd_composite_inst = new FormData();
		fd_composite_inst.bottom = new FormAttachment(composite_ref, 0, SWT.BOTTOM);
		fd_composite_inst.top = new FormAttachment(composite_ref, 0, SWT.TOP);
		fd_composite_inst.right = new FormAttachment(toolBar, 0, SWT.RIGHT);
		fd_composite_inst.left = new FormAttachment(composite_ref, 6);
		composite_inst.setLayoutData(fd_composite_inst);
		
		Tree tree_3 = new Tree(composite_inst, SWT.BORDER);
		tree_3.setHeaderVisible(true);
		tree_3.setLinesVisible(true);
		
		TreeColumn treeColumn_9 = new TreeColumn(tree_3, SWT.NONE);
		tcl_composite_inst.setColumnData(treeColumn_9, new ColumnPixelData(135, true, true));
		treeColumn_9.setText("名称");
		
		TreeColumn treeColumn_10 = new TreeColumn(tree_3, SWT.NONE);
		tcl_composite_inst.setColumnData(treeColumn_10, new ColumnPixelData(150, true, true));
		treeColumn_10.setText("代码");
		
		TreeItem treeItem_1 = new TreeItem(tree_3, SWT.NONE);
		treeItem_1.setText("主机1");
		
		TreeItem trtmTomcat = new TreeItem(treeItem_1, SWT.NONE);
		trtmTomcat.setText("Tomcat");
		treeItem_1.setExpanded(true);
		
		TreeItem trtmNewTreeitem_8 = new TreeItem(tree_3, SWT.NONE);
		trtmNewTreeitem_8.setText("主机2");
		
		TreeItem trtmTomcat_1 = new TreeItem(trtmNewTreeitem_8, SWT.NONE);
		trtmTomcat_1.setText("Tomcat");
		trtmNewTreeitem_8.setExpanded(true);
		
		TreeItem trtmNewTreeitem_9 = new TreeItem(tree_3, SWT.NONE);
		trtmNewTreeitem_9.setText("主机3");
		
		TreeItem trtmNewTreeitem_10 = new TreeItem(trtmNewTreeitem_9, SWT.NONE);
		trtmNewTreeitem_10.setText("Tomcat");
		trtmNewTreeitem_9.setExpanded(true);
		
		Composite composite_dpy = new Composite(container, SWT.NONE);
		fd_button.bottom = new FormAttachment(composite_dpy, -6);
		fd_btnNewButton.right = new FormAttachment(composite_dpy, 0, SWT.RIGHT);
		TreeColumnLayout tcl_composite_dpy = new TreeColumnLayout();
		composite_dpy.setLayout(tcl_composite_dpy);
		FormData fd_composite_dpy = new FormData();
		fd_composite_dpy.left = new FormAttachment(composite_cmp, 6);
		fd_composite_dpy.bottom = new FormAttachment(100, -46);
		fd_composite_dpy.top = new FormAttachment(0, 84);
		composite_dpy.setLayoutData(fd_composite_dpy);
		
		Tree tree_2 = new Tree(composite_dpy, SWT.BORDER);
		tree_2.setHeaderVisible(true);
		tree_2.setLinesVisible(true);
		
		TreeColumn trclmnNewColumn_2 = new TreeColumn(tree_2, SWT.NONE);
		tcl_composite_dpy.setColumnData(trclmnNewColumn_2, new ColumnPixelData(115, true, true));
		trclmnNewColumn_2.setText("关联类型");
		
		TreeColumn treeColumn_4 = new TreeColumn(tree_2, SWT.NONE);
		tcl_composite_dpy.setColumnData(treeColumn_4, new ColumnPixelData(86, true, true));
		treeColumn_4.setText("关联元素");
		
		TreeColumn trclmnNewColumn_3 = new TreeColumn(tree_2, SWT.NONE);
		tcl_composite_dpy.setColumnData(trclmnNewColumn_3, new ColumnPixelData(49, true, true));
		trclmnNewColumn_3.setText("别名");
		
		TreeColumn treeColumn_5 = new TreeColumn(tree_2, SWT.NONE);
		tcl_composite_dpy.setColumnData(treeColumn_5, new ColumnPixelData(47, true, true));
		treeColumn_5.setText("类型");
		
		TreeColumn treeColumn_8 = new TreeColumn(tree_2, SWT.NONE);
		tcl_composite_dpy.setColumnData(treeColumn_8, new ColumnPixelData(116, true, true));
		treeColumn_8.setText("描述");
		
		TreeItem trtmXxx_4 = new TreeItem(tree_2, SWT.NONE);
		trtmXxx_4.setText("安装到|Tomcat");
		trtmXxx_4.setExpanded(true);
		
		TreeItem trtmNewTreeitem_4 = new TreeItem(tree_2, SWT.NONE);
		trtmNewTreeitem_4.setText("连接|第三方接口");
		
		TreeItem trtmNewTreeitem_5 = new TreeItem(tree_2, SWT.NONE);
		trtmNewTreeitem_5.setText("连接|XXX组件");
		
		TreeItem trtmNewTreeitem_6 = new TreeItem(tree_2, SWT.NONE);
		trtmNewTreeitem_6.setText("连接|DB");
		
		TreeItem trtmNewTreeitem_7 = new TreeItem(tree_2, SWT.NONE);
		trtmNewTreeitem_7.setText("连接|消息对列");
		
		Label label = new Label(container, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 18);
		label.setLayoutData(fd_label);
		label.setText("属性值");
		
		Composite composite_props = new Composite(container, SWT.NONE);
		fd_button.right = new FormAttachment(composite_props, -10);
		fd_label.left = new FormAttachment(composite_props, 0, SWT.LEFT);
		//fd_composite_dpy.right = new FormAttachment(100, -304);
		TreeColumnLayout tcl_composite_props = new TreeColumnLayout();
		composite_props.setLayout(tcl_composite_props);
		FormData fd_composite_props = new FormData();
		fd_composite_props.bottom = new FormAttachment(100, -46);
		fd_composite_props.top = new FormAttachment(label, 10);
		fd_composite_props.left = new FormAttachment(composite_dpy, 10, SWT.RIGHT);
		//fd_composite_props.bottom = new FormAttachment(100, -135);
		composite_props.setLayoutData(fd_composite_props);
		
		Tree tree_1 = new Tree(composite_props, SWT.BORDER);
		tree_1.setHeaderVisible(true);
		tree_1.setLinesVisible(true);
		
		TreeColumn treeColumn_6 = new TreeColumn(tree_1, SWT.NONE);
		tcl_composite_props.setColumnData(treeColumn_6, new ColumnPixelData(104, true, true));
		treeColumn_6.setText("属性");
		
		TreeColumn treeColumn_7 = new TreeColumn(tree_1, SWT.NONE);
		tcl_composite_props.setColumnData(treeColumn_7, new ColumnPixelData(130, true, true));
		treeColumn_7.setText("值");
		
		TreeItem trtmXxx_1 = new TreeItem(tree_1, SWT.NONE);
		trtmXxx_1.setText("属性1");
		
		TreeItem treeItem = new TreeItem(trtmXxx_1, SWT.NONE);
		treeItem.setText("属性11");
		trtmXxx_1.setExpanded(true);
		
		Combo combo = new Combo(container, SWT.NONE);
		fd_btnNewButton.top = new FormAttachment(combo, 0, SWT.TOP);
		fd_label_1.top = new FormAttachment(combo, 0, SWT.TOP);
		FormData fd_combo = new FormData();
		fd_combo.right = new FormAttachment(btnNewButton, -6);
		fd_combo.bottom = new FormAttachment(button, -2);
		fd_combo.left = new FormAttachment(label_1, 6);
		combo.setLayoutData(fd_combo);
		
		Label lblXxx = new Label(container, SWT.NONE);
		FormData fd_lblXxx = new FormData();
		fd_lblXxx.top = new FormAttachment(button, 5, SWT.TOP);
		fd_lblXxx.right = new FormAttachment(label_1, 0, SWT.RIGHT);
		lblXxx.setLayoutData(fd_lblXxx);
		lblXxx.setText("XXX组件");

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
