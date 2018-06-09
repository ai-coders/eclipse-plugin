package net.aicoder.epi.base.view.property;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.action.EpiAddAction;
import net.aicoder.epi.base.view.action.EpiDeleteAction;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;

public class PropsArea extends BaseWithTitleArea {
	//private Map<String, PropertiesDefine> propertiesDefineMap = new HashMap<String, PropertiesDefine>(0);
	
	private Composite composite;
	private Tree tree;
	private TreeViewer viewer;
	private PropsInput propsInput;
	
	//// Constructor
	public PropsArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		
		tree = new Tree(composite,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		viewer = new TreeViewer(tree);
		
		{
			TreeColumn column = new TreeColumn(tree, SWT.LEFT);
			column.setText("属性名");
			column.setWidth(200);
		}

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

		return composite;
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		IEpiAction[] toolBarAactons = new IEpiAction[2];
		toolBarAactons[0] = new EpiAddAction();
		toolBarAactons[1] = new EpiDeleteAction();
		toolBarManager.add(toolBarAactons[0]);
		toolBarManager.add(toolBarAactons[1]);
		toolBarManager.update(false);
	}
	
	public void setSelection(ISelection selection) {
		
	}

	//// getter/setter
	public PropsInput getPropsInput() {
		return propsInput;
	}
}
