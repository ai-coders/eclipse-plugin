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

public class ModuleEditor extends EditorPart {

	//public static final String ID = "net.aicoder.epi.devp.prddev.uidemo.editors.ModuleEditor"; //$NON-NLS-1$
	public static final String ID = ModuleEditor.class.getName(); //$NON-NLS-1$

	public ModuleEditor() {
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
		fd_tree.right = new FormAttachment(toolBar, 153, SWT.RIGHT);
		fd_tree.bottom = new FormAttachment(toolBar, 263, SWT.BOTTOM);
		fd_tree.top = new FormAttachment(toolBar, 6);
		fd_tree.left = new FormAttachment(toolBar, 0, SWT.LEFT);
		
		ToolItem toolItem_7 = new ToolItem(toolBar, SWT.NONE);
		toolItem_7.setText("保存");
		tree.setLayoutData(fd_tree);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		
		TreeColumn treeColumn = new TreeColumn(tree, SWT.NONE);
		treeColumn.setWidth(100);
		treeColumn.setText("名称*");
		
		TreeColumn trclmnNewColumn = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn.setWidth(82);
		trclmnNewColumn.setText("代码");
		
		TreeColumn trclmnNewColumn_1 = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn_1.setWidth(79);
		trclmnNewColumn_1.setText("别名");
		
		TreeColumn treeColumn_2 = new TreeColumn(tree, SWT.NONE);
		treeColumn_2.setWidth(44);
		treeColumn_2.setText("类别");
		
		TreeColumn treeColumn_1 = new TreeColumn(tree, SWT.NONE);
		treeColumn_1.setWidth(107);
		treeColumn_1.setText("描述");
		
		TreeColumn treeColumn_3 = new TreeColumn(tree, SWT.NONE);
		treeColumn_3.setWidth(66);
		treeColumn_3.setText("使用者");
		
		TreeColumn treeColumn_4 = new TreeColumn(tree, SWT.NONE);
		treeColumn_4.setWidth(100);
		treeColumn_4.setText("优先级");
		
		TreeColumn trclmnNewColumn_2 = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn_2.setWidth(139);
		trclmnNewColumn_2.setText("备注");
		
		TreeItem trtmXxx = new TreeItem(tree, SWT.NONE);
		trtmXxx.setText("XXX模块");
		
		TreeItem trtmNewTreeitem = new TreeItem(trtmXxx, SWT.NONE);
		trtmNewTreeitem.setText("XXX功能");
		trtmXxx.setExpanded(true);

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
