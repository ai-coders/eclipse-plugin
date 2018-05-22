package net.aicoder.epi.devp.prddev.uidemo.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import net.aicoder.epi.devp.prddev.uidemo.editors.ModuleEditor;
import net.aicoder.epi.devp.prddev.uidemo.editors.SysCmpDgmEditor;
import net.aicoder.epi.devp.prddev.uidemo.editors.SysCmpEditor;
import net.aicoder.epi.devp.prddev.uidemo.editors.SysDpyDgmEditor;
import net.aicoder.epi.devp.prddev.uidemo.editors.SysDpyEditor;
import net.aicoder.epi.devp.prddev.uidemo.editors.SysIdeEditor;

import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class ProductDevExplorer extends ViewPart {

	public static final String ID = ProductDevExplorer.class.getName(); //$NON-NLS-1$
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Table table;
	
	public ProductDevExplorer() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		{
			ToolBar toolBar = new ToolBar(container, SWT.FLAT | SWT.RIGHT);
			toolBar.setBounds(0, 0, 249, 28);
			{
				ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
				tltmNewItem.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
					}
				});
				tltmNewItem.setText("过滤");
			}
			{
				ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
				toolItem.setText("切换");
			}
		}
		{
			Tree tree = new Tree(container, SWT.BORDER);
			//treeViewer = new TreeViewer(tree);
			tree.setBounds(10, 34, 249, 376);
			{
				TreeItem trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
				trtmNewTreeitem.setText("XXX产品");
				{
					TreeItem trtmNewTreeitem_1 = new TreeItem(trtmNewTreeitem, SWT.NONE);
					trtmNewTreeitem_1.setText("功能模型");
					{
						TreeItem trtmNewTreeitem_5 = new TreeItem(trtmNewTreeitem_1, SWT.NONE);
						trtmNewTreeitem_5.setText("模块");
						{
							TreeItem trtmNewTreeitem_6 = new TreeItem(trtmNewTreeitem_5, SWT.NONE);
							trtmNewTreeitem_6.setText("功能");
						}
						trtmNewTreeitem_5.setExpanded(true);
					}
					trtmNewTreeitem_1.setExpanded(true);
				}
				{
					TreeItem trtmNewTreeitem_2 = new TreeItem(trtmNewTreeitem, SWT.NONE);
					trtmNewTreeitem_2.setText("组件模型");
					{
						TreeItem trtmNewTreeitem_7 = new TreeItem(trtmNewTreeitem_2, SWT.NONE);
						trtmNewTreeitem_7.setText("系统/子系统");
						{
							TreeItem trtmNewTreeitem_8 = new TreeItem(trtmNewTreeitem_7, SWT.NONE);
							trtmNewTreeitem_8.setText("组件");
						}
						trtmNewTreeitem_7.setExpanded(true);
					}
					trtmNewTreeitem_2.setExpanded(true);
				}
				{
					TreeItem trtmNewTreeitem_3 = new TreeItem(trtmNewTreeitem, SWT.NONE);
					trtmNewTreeitem_3.setText("开发模型");
					{
						TreeItem treeItem = new TreeItem(trtmNewTreeitem_3, SWT.NONE);
						treeItem.setText("代码目录");
						{
							TreeItem treeItem_1 = new TreeItem(treeItem, SWT.NONE);
							treeItem_1.setText("代码工程");
						}
						treeItem.setExpanded(true);
					}
					trtmNewTreeitem_3.setExpanded(true);
				}
				{
					TreeItem trtmNewTreeitem_4 = new TreeItem(trtmNewTreeitem, SWT.NONE);
					trtmNewTreeitem_4.setText("部署模型");
					{
						TreeItem treeItem = new TreeItem(trtmNewTreeitem_4, SWT.NONE);
						treeItem.setText("节点(环境)");
					}
					{
						TreeItem treeItem = new TreeItem(trtmNewTreeitem_4, SWT.NONE);
						treeItem.setText("组件(资源)");
					}
					trtmNewTreeitem_4.setExpanded(true);
				}
				trtmNewTreeitem.setExpanded(true);
			}
			{
				Menu menu = new Menu(tree);
				tree.setMenu(menu);
				{
					MenuItem menuItem = new MenuItem(menu, SWT.NONE);
					menuItem.setText("新增模块");
				}
				{
					MenuItem menuItem = new MenuItem(menu, SWT.NONE);
					menuItem.setText("新增功能");
				}
				{
					new MenuItem(menu, SWT.SEPARATOR);
				}
				{
					MenuItem menuItem = new MenuItem(menu, SWT.NONE);
					menuItem.setText("查看/修改");
				}
				{
					new MenuItem(menu, SWT.SEPARATOR);
				}
				{
					MenuItem menuItem = new MenuItem(menu, SWT.NONE);
					menuItem.setText("上移");
				}
				{
					MenuItem menuItem = new MenuItem(menu, SWT.NONE);
					menuItem.setText("下移");
				}
				{
					new MenuItem(menu, SWT.SEPARATOR);
				}
				{
					MenuItem menuItem = new MenuItem(menu, SWT.NONE);
					menuItem.setText("删除");
				}
			}
			
			tree.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent event) {
					String itemStr = getTreeSelectedItemValue(tree);
					IStatusLineManager statusLine = getViewSite().getActionBars().getStatusLineManager();
					if (itemStr != null) {
						statusLine.setMessage(itemStr);
					}
				}

				public void widgetDefaultSelected(SelectionEvent event) {
					String itemStr = getTreeSelectedItemValue(tree);
					if (itemStr == null) {
						return;
					}
					switch(itemStr) {
					case "XXX产品":
						break;
					case "功能模型":
						doOpenEditor(itemStr, ModuleEditor.ID);
						break;
					case "组件模型":
						doOpenEditor(itemStr, SysCmpEditor.ID);
						break;
					case "开发模型":
						doOpenEditor(itemStr, SysIdeEditor.ID);
						break;
					case "部署模型":
						doOpenEditor(itemStr, SysDpyEditor.ID);
						break;
					default:
						
					}

				}
			});
		}
		{
			TabFolder tabFolder = new TabFolder(container, SWT.NONE);
			tabFolder.setBounds(10, 420, 255, 331);
			{
				TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
				tabItem.setText("设计图");
				{
					Composite composite = new Composite(tabFolder, SWT.NONE);
					tabItem.setControl(composite);
					formToolkit.paintBordersFor(composite);
					TableColumnLayout tcl_composite = new TableColumnLayout();
					composite.setLayout(tcl_composite);
					{
						table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
						//tableViewer = new TableViewer(table);
						formToolkit.adapt(table);
						formToolkit.paintBordersFor(table);
						table.setHeaderVisible(true);
						table.setLinesVisible(true);

						
						table.addSelectionListener(new SelectionListener() {
							public void widgetSelected(SelectionEvent event) {
								String itemStr = getTableSelectedItemValue(table);
								IStatusLineManager statusLine = getViewSite().getActionBars().getStatusLineManager();
								if (itemStr != null) {
									statusLine.setMessage(itemStr);
								}
							}

							public void widgetDefaultSelected(SelectionEvent event) {
								String itemStr = getTableSelectedItemValue(table);
								if (itemStr == null) {
									return;
								}
								switch(itemStr) {
								case "[Cmp]组件图":
									doOpenEditor(itemStr, SysCmpDgmEditor.ID);
									break;
								case "[Dpy]部署图":
									doOpenEditor(itemStr, SysDpyDgmEditor.ID);
									break;
								default:
									
								}
							}
						});
							
						{
							TableItem tableItem = new TableItem(table, SWT.NONE);
							tableItem.setText("[Cmp]组件图");
						}
						{
							TableColumn tableColumn = new TableColumn(table, SWT.NONE);
							tcl_composite.setColumnData(tableColumn, new ColumnPixelData(150, true, true));
							tableColumn.setText("名称");
						}
						{
							TableColumn tableColumn = new TableColumn(table, SWT.NONE);
							tcl_composite.setColumnData(tableColumn, new ColumnPixelData(150, true, true));
							tableColumn.setText("目录");
						}
						{
							TableItem tableItem = new TableItem(table, SWT.NONE);
							tableItem.setText("[Dpy]部署图");
						}
						{
							Menu menu = new Menu(table);
							table.setMenu(menu);
							{
								MenuItem menuItem = new MenuItem(menu, SWT.NONE);
								menuItem.setText("新增");
							}
							{
								MenuItem menuItem = new MenuItem(menu, SWT.NONE);
								menuItem.setText("查看/修改");
							}
							{
								new MenuItem(menu, SWT.SEPARATOR);
							}
							{
								MenuItem menuItem = new MenuItem(menu, SWT.NONE);
								menuItem.setText("上移");
							}
							{
								MenuItem menuItem = new MenuItem(menu, SWT.NONE);
								menuItem.setText("下移");
							}
							{
								new MenuItem(menu, SWT.SEPARATOR);
							}
							{
								MenuItem menuItem = new MenuItem(menu, SWT.NONE);
								menuItem.setText("删除");
							}
						}
					}
				}
			}
			{
				TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
				tabItem.setText("子元素");
			}
			{
				TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
				tabItem.setText("模板");
			}
		}

		createActions();
		initializeToolBar();
		initializeMenu();
	}
	
	private void doOpenEditor(String title, String editorId) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if (editorId == null || editorId.equals("")) {
			return;
		}
		try {
			EpiEditorInput input = new EpiEditorInput();
			input.setName(title);
			page.openEditor(input, editorId);
		} catch (PartInitException e) {
			System.out.println(e);
		}
	}
	
	private String getTreeSelectedItemValue(Tree tree) {
		final TreeItem[] selections = tree.getSelection();
		if (selections.length == 0) {
			return null;
		}
		String itemStr = selections[0].getText();
		return itemStr;
	}

	private String getTableSelectedItemValue(Table table) {
		final TableItem[] selections = table.getSelection();
		if (selections.length == 0) {
			return null;
		}
		String itemStr = selections[0].getText();
		return itemStr;
	}
	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
