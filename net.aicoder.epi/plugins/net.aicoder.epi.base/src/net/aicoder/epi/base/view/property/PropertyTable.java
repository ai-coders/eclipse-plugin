package net.aicoder.epi.base.view.property;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

public class PropertyTable extends Composite {
	private Tree tree;
	private TreeViewer viewer;
	private PropsInput propsInput;
	
	private boolean m_showAdvancedProperties;

	public PropertyTable(Composite parent) {
		super(parent, SWT.NONE);
		setLayout(new FillLayout());
		
		tree = new Tree(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
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
	}

	public PropsInput getPropsInput() {
		return propsInput;
	}

	public void setPropsInput(PropsInput propsInput) {
		this.propsInput = propsInput;
		viewer.setInput(propsInput);
	}
	
	public void refresh() {
	    viewer.expandAll();
		viewer.refresh();
	}
	
	
	  //// Access
	  /**
	   * Shows or hides {@link Property}-s with {@link PropertyCategory#ADVANCED}.
	   */
	  public void setShowAdvancedProperties(boolean showAdvancedProperties) {
	    m_showAdvancedProperties = showAdvancedProperties;
	    propsInput.setShowAdvancedProperties(m_showAdvancedProperties);
	    viewer.expandAll();
	    viewer.refresh();
	  }

}
