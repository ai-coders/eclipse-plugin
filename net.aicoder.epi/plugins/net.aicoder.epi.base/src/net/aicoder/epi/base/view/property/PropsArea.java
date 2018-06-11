package net.aicoder.epi.base.view.property;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.BaseImageConstant;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;

public class PropsArea extends BaseWithTitleArea {
	// private Map<String, PropertiesDefine> propertiesDefineMap = new
	// HashMap<String, PropertiesDefine>(0);

	private PropertyTable propertyTable;
	private PropsInput propsInput;

	private PropertyInfo m_activePropertyInfo;

	//// Constructor
	public PropsArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		propertyTable = new PropertyTable(parent);

		createActons();

		return propertyTable;
	}

	public void setElementSelection(String pluginId, ISelection selection) {
		Object item = ((IStructuredSelection) selection).getFirstElement();
		IBaseVo currentData = (IBaseVo) item;
		propsInput = new PropsInput(pluginId, currentData);
		propertyTable.setPropsInput(propsInput);

		updateUI();
	}

	private void updateUI() {

		propertyTable.refresh();
	}

	//// Actions
	private IToolBarManager m_toolBarManager;
	IAction[] propsToolBarAactons;
	IAction[] extToolBarAactons;

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		m_toolBarManager = toolBarManager;
		updateActions();
	}

	private void createActons() {
		create_showAdvancedPropertiesAction();
		create_defaultValueAction();
		
		propsToolBarAactons = new IAction[2];
		propsToolBarAactons[0] = m_showAdvancedPropertiesAction;
		propsToolBarAactons[1] = m_defaultValueAction;
	}

	private void updateActions() {
		// update standard items
		update_defaultValueAction();

		// update toolbar
		Control toolBarControl = ((ToolBarManager) m_toolBarManager).getControl();
		try {
			toolBarControl.setRedraw(false);
			m_toolBarManager.removeAll();
			// add extension items

			// add standard items
			if (propsToolBarAactons != null) {
				for (IAction action : propsToolBarAactons) {
					m_toolBarManager.add(action);
				}
			}
			// done
			m_toolBarManager.update(false);
		} finally {
			toolBarControl.setRedraw(true);
		}
	}

	//// getter/setter
	public PropsInput getPropsInput() {
		return propsInput;
	}

	//// Action: Filter advanced properties
	private Action m_showAdvancedPropertiesAction;

	/**
	 * Creates the {@link #m_showAdvancedPropertiesAction}.
	 */
	private void create_showAdvancedPropertiesAction() {
		m_showAdvancedPropertiesAction = new Action("", IAction.AS_CHECK_BOX) {
			boolean show = false;
			@Override
			public void run() {
				show = m_showAdvancedPropertiesAction.isChecked();
				propertyTable.setShowAdvancedProperties(show);
			}
		};
		m_showAdvancedPropertiesAction
				.setImageDescriptor(BaseImageConstant.getImageDescriptor(BaseImageConstant.PROPS_FILTER_ADVANCED));
		m_showAdvancedPropertiesAction.setText("显示高级属性");
		m_showAdvancedPropertiesAction.setToolTipText("显示高级属性");
	}

	  //// Action: Restore default value
	  private Action m_defaultValueAction;

	  /**
	   * Creates the {@link #m_defaultValueAction}.
	   */
	  private void create_defaultValueAction() {
	    m_defaultValueAction = new Action() {
	      @Override
	      public void run() {
	      }
	    };
	    m_defaultValueAction.setImageDescriptor(BaseImageConstant.getImageDescriptor(BaseImageConstant.PROPS_RESET_DEFAULT));
	    m_defaultValueAction.setText("恢复到原始值");
	    m_defaultValueAction.setToolTipText("恢复到原始值");
	  }

	  /**
	   * Updates the state of {@link #m_defaultValueAction}.
	   */
	  private void update_defaultValueAction() {
	    if (m_activePropertyInfo != null) {
	      m_defaultValueAction.setEnabled(m_activePropertyInfo.isModified());
	    } else {
	      m_defaultValueAction.setEnabled(false);
	    }
	  }

}
