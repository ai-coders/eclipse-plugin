package net.aicoder.epi.devp.prddev.uidemo.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.FillLayout;

public class ProductDevExplorerOrg extends ViewPart {

	public static final String ID = "net.aicoder.epi.devp.prddev.uidemo.views.ProductDevExplorer"; //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	public ProductDevExplorerOrg() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = toolkit.createComposite(parent, SWT.NONE);
		toolkit.paintBordersFor(container);
		container.setLayout(null);
		
		TreeViewer treeViewer = new TreeViewer(container, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		tree.setBounds(0, 0, 247, 506);
		toolkit.paintBordersFor(tree);
		
		TreeItem trtmNewTreeitem_1 = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem_1.setText("XXXX产品");
		
		TreeItem trtmNewTreeitem_2 = new TreeItem(trtmNewTreeitem_1, SWT.NONE);
		trtmNewTreeitem_2.setText("\u529F\u80FD\u6A21\u578B");
		
		TreeItem trtmXxx = new TreeItem(trtmNewTreeitem_2, SWT.NONE);
		trtmXxx.setText("XXX\u6A21\u5757");
		
		TreeItem trtmNewTreeitem_6 = new TreeItem(trtmXxx, SWT.NONE);
		trtmNewTreeitem_6.setText("XXX\u529F\u80FD");
		trtmXxx.setExpanded(true);
		
		TreeItem trtmNewTreeitem_5 = new TreeItem(trtmNewTreeitem_2, SWT.NONE);
		trtmNewTreeitem_5.setText("XXX\u6A21\u5757");
		trtmNewTreeitem_2.setExpanded(true);
		
		TreeItem trtmNewTreeitem = new TreeItem(trtmNewTreeitem_1, SWT.NONE);
		trtmNewTreeitem.setText("\u7EC4\u4EF6\u6A21\u578B");
		
		TreeItem trtmXxx_1 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmXxx_1.setText("XXX\u7CFB\u7EDF");
		
		TreeItem trtmXxx_2 = new TreeItem(trtmXxx_1, SWT.NONE);
		trtmXxx_2.setText("XXX\u5B50\u7CFB\u7EDF");
		
		TreeItem trtmXxx_3 = new TreeItem(trtmXxx_2, SWT.NONE);
		trtmXxx_3.setText("XXX\u7EC4\u4EF6");
		trtmXxx_2.setExpanded(true);
		
		TreeItem trtmNewTreeitem_7 = new TreeItem(trtmXxx_1, SWT.NONE);
		trtmNewTreeitem_7.setText("XXX\u7EC4\u4EF6");
		trtmXxx_1.setExpanded(true);
		trtmNewTreeitem.setExpanded(true);
		
		TreeItem trtmNewTreeitem_3 = new TreeItem(trtmNewTreeitem_1, SWT.NONE);
		trtmNewTreeitem_3.setText("\u5F00\u53D1\u6A21\u578B");
		
		TreeItem treeItem = new TreeItem(trtmNewTreeitem_3, SWT.NONE);
		treeItem.setText("\u4EE3\u7801\u76EE\u5F551");
		
		TreeItem trtmNewTreeitem_8 = new TreeItem(treeItem, SWT.NONE);
		trtmNewTreeitem_8.setText("\u524D\u7AEF");
		
		TreeItem treeItem_1 = new TreeItem(trtmNewTreeitem_8, SWT.NONE);
		treeItem_1.setText("\u524D\u7AEF\u5DE5\u7A0B");
		treeItem_1.setExpanded(true);
		trtmNewTreeitem_8.setExpanded(true);
		
		TreeItem trtmNewTreeitem_9 = new TreeItem(treeItem, SWT.NONE);
		trtmNewTreeitem_9.setText("\u540E\u7AEF");
		
		TreeItem trtmNewTreeitem_10 = new TreeItem(trtmNewTreeitem_9, SWT.NONE);
		trtmNewTreeitem_10.setText("\u63A5\u53E3\u5DE5\u7A0B");
		
		TreeItem trtmNewTreeitem_11 = new TreeItem(trtmNewTreeitem_9, SWT.NONE);
		trtmNewTreeitem_11.setText("\u540E\u7AEF\u670D\u52A1\u5DE5\u7A0B");
		trtmNewTreeitem_9.setExpanded(true);
		treeItem.setExpanded(true);
		trtmNewTreeitem_3.setExpanded(true);
		
		TreeItem trtmNewTreeitem_4 = new TreeItem(trtmNewTreeitem_1, SWT.NONE);
		trtmNewTreeitem_4.setText("\u90E8\u7F72\u6A21\u578B");
		
		TreeItem trtmNewTreeitem_12 = new TreeItem(trtmNewTreeitem_4, SWT.NONE);
		trtmNewTreeitem_12.setText("\u8282\u70B9(\u8FD0\u884C\u73AF\u5883)");
		
		TreeItem trtmNewTreeitem_13 = new TreeItem(trtmNewTreeitem_12, SWT.NONE);
		trtmNewTreeitem_13.setText("\u7EC4\u4EF6");
		trtmNewTreeitem_12.setExpanded(true);
		trtmNewTreeitem_4.setExpanded(true);
		trtmNewTreeitem_1.setExpanded(true);
		
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		tabFolder.setBounds(0, 514, 247, 463);
		toolkit.adapt(tabFolder);
		toolkit.paintBordersFor(tabFolder);
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("\u8BBE\u8BA1\u56FE");
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("\u5B50\u5143\u7D20");
		
		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("\u6A21\u677F");

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	public void dispose() {
		toolkit.dispose();
		super.dispose();
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
		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
