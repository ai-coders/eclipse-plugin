package net.aicoder.epi.devp.prddev.uidemo.editors.b180612;

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

public class SysIdeEditor extends EditorPart {

	public static final String ID = SysIdeEditor.class.getName(); //$NON-NLS-1$
	private Table table;

	public SysIdeEditor() {
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
		treeColumn.setWidth(139);
		treeColumn.setText("名称*");
		
		TreeColumn trclmnNewColumn = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn.setWidth(82);
		trclmnNewColumn.setText("代码");
		
		TreeColumn trclmnNewColumn_1 = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn_1.setWidth(79);
		trclmnNewColumn_1.setText("别名");
		
		TreeColumn treeColumn_2 = new TreeColumn(tree, SWT.NONE);
		treeColumn_2.setWidth(44);
		treeColumn_2.setText("类型");
		
		TreeColumn treeColumn_5 = new TreeColumn(tree, SWT.NONE);
		treeColumn_5.setWidth(70);
		treeColumn_5.setText("子类型");
		
		TreeColumn treeColumn_1 = new TreeColumn(tree, SWT.NONE);
		treeColumn_1.setWidth(107);
		treeColumn_1.setText("描述");
		
		TreeColumn trclmnNewColumn_2 = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn_2.setWidth(139);
		trclmnNewColumn_2.setText("备注");
		
		TreeItem trtmXxx = new TreeItem(tree, SWT.NONE);
		trtmXxx.setText("XXX工程目录");
		
		TreeItem trtmNewTreeitem = new TreeItem(trtmXxx, SWT.NONE);
		trtmNewTreeitem.setText("XXX子目录");
		
		TreeItem trtmNewTreeitem_1 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmNewTreeitem_1.setText("XXX工程");
		trtmNewTreeitem.setExpanded(true);
		trtmXxx.setExpanded(true);
		
		Label label = new Label(container, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(tree, 0, SWT.TOP);
		label.setLayoutData(fd_label);
		label.setText("属性值");
		
		Composite composite = new Composite(container, SWT.NONE);
		fd_label.left = new FormAttachment(composite, 0, SWT.LEFT);
		TreeColumnLayout tcl_composite = new TreeColumnLayout();
		composite.setLayout(tcl_composite);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(100, -163);
		fd_composite.top = new FormAttachment(label);
		fd_composite.left = new FormAttachment(tree, 38);
		fd_composite.right = new FormAttachment(100, 11);
		composite.setLayoutData(fd_composite);
		
		Tree tree_1 = new Tree(composite, SWT.BORDER);
		tree_1.setHeaderVisible(true);
		tree_1.setLinesVisible(true);
		
		TreeColumn treeColumn_6 = new TreeColumn(tree_1, SWT.NONE);
		tcl_composite.setColumnData(treeColumn_6, new ColumnPixelData(104, true, true));
		treeColumn_6.setText("属性");
		
		TreeColumn treeColumn_7 = new TreeColumn(tree_1, SWT.NONE);
		tcl_composite.setColumnData(treeColumn_7, new ColumnPixelData(103, true, true));
		treeColumn_7.setText("值");
		
		TreeItem trtmXxx_1 = new TreeItem(tree_1, SWT.NONE);
		trtmXxx_1.setText("属性1");
		
		TreeItem treeItem = new TreeItem(trtmXxx_1, SWT.NONE);
		treeItem.setText("属性11");
		trtmXxx_1.setExpanded(true);
		
		Composite composite_1 = new Composite(container, SWT.NONE);
		TableColumnLayout tcl_composite_1 = new TableColumnLayout();
		composite_1.setLayout(tcl_composite_1);
		FormData fd_composite_1 = new FormData();
		fd_composite_1.right = new FormAttachment(0, 854);
		fd_composite_1.top = new FormAttachment(tree, 33);
		fd_composite_1.left = new FormAttachment(0, 10);
		composite_1.setLayoutData(fd_composite_1);
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_5, new ColumnPixelData(117, true, true));
		tableColumn_5.setText("工程名");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_4, new ColumnPixelData(150, true, true));
		tableColumn_4.setText("组件名(全路径)");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_1, new ColumnPixelData(84, true, true));
		tableColumn_1.setText("版本");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn_3, new ColumnPixelData(150, true, true));
		tableColumn_3.setText("类型");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tcl_composite_1.setColumnData(tableColumn, new ColumnPixelData(150, true, true));
		tableColumn.setText("关联关系描述");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText("XXX工程");
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText("XXX工程");
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("工程输出组件");
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(tree, 6);
		fd_lblNewLabel.left = new FormAttachment(tree, 10, SWT.LEFT);
		lblNewLabel.setLayoutData(fd_lblNewLabel);

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
