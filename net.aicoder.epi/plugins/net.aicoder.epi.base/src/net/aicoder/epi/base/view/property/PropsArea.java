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
import net.aicoder.epi.base.view.part.area.BaseTitleArea;

public class PropsArea extends BaseTitleArea {
	private IPropsManager propsManager;
	//private IExtInfosManager extInfosManager;

	//private PropertyTable propertyTable;
	private PropertyTable propertyTable;
	private PropsInput propsInput;

	private PropertyInfo m_activePropertyInfo;

	//// Constructor
	public PropsArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
	}

	/**	
		@Override
		protected Control createAreaControl(Composite parent) {
			propertyTable = new PropertyTable(parent);
			propsInput = new PropsInput(propsManager);
	
			createActons();
	
			return propertyTable;
		}
	**/

	@Override
	public final void assembleControl(Composite parent) {
		//propertyTable = new PropertyTable(parent);
		propertyTable = new PropertyTable(parent);
		propsInput = new PropsInput(propsManager);

		createActions();
	}

	@Override
	public void attachEvent() {
	}

	/**
	 * 设置属性区域原始对象数据
	 * @param selection
	 */
	public void setElementSelection(ISelection selection) {
		Object item = ((IStructuredSelection) selection).getFirstElement();
		if (item == null) {
			return;
		}
		IBaseVo currentData = (IBaseVo) item;
		//propsInput = new PropsInput(propsManager, currentData);
		IBaseVo oldData = propsInput.getCurrentData();
		if (currentData.equals(oldData)) {
			return;
		}
		propsInput.setCurrentData(currentData);
		propertyTable.setPropsInput(propsInput);

		updateUI();
	}

	private void updateUI() {
		propertyTable.setShowAdvancedProperties(show);
		//propertyTable.refresh();
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

	private void createActions() {
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

	public IPropsManager getPropsManager() {
		return propsManager;
	}

	public void setPropsManager(IPropsManager propsManager) {
		this.propsManager = propsManager;
	}

	//// Action: Filter advanced properties
	private Action m_showAdvancedPropertiesAction;
	boolean show = false;

	/**
	 * Creates the {@link #m_showAdvancedPropertiesAction}.
	 */
	private void create_showAdvancedPropertiesAction() {
		m_showAdvancedPropertiesAction = new Action("", IAction.AS_CHECK_BOX) {
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
		m_defaultValueAction
				.setImageDescriptor(BaseImageConstant.getImageDescriptor(BaseImageConstant.PROPS_RESET_DEFAULT));
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
