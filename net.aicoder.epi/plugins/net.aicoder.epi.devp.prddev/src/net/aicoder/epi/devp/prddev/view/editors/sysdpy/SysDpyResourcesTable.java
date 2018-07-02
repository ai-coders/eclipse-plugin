package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.action.table.EpiAddAction;
import net.aicoder.epi.base.view.action.table.EpiDeleteAction;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;
import net.aicoder.epi.base.view.element.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.element.table.EpiTable;
import net.aicoder.epi.base.view.element.table.EpiTableDefiner;
import net.aicoder.epi.devp.prddev.doper.ops.SysDpyResourcesDoper;
import net.aicoder.epi.devp.prddev.model.ops.SysDpyResourcesVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

/**
 * 部署模型-关联资源(表)
 * @author WANGQINGPING
 *
 */
public class SysDpyResourcesTable extends BaseWithTitleArea{
	public static final String ID = SysDpyResourcesTable.class.getName();		
	private EpiTable table;
	private EpiTableDefiner definer;
	private IViewContext context;
	private SysDpyResourcesDoper doper;
	private PrdProductVo currentProduct;//当前产品
	
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"关联类型", "type", -20, null, null, null, IColumnDefiner.CE_TEXT},
		{"关联名称", "name", 20, null, null, null, IColumnDefiner.CE_TEXT }
	};
	
	
	
	//Constructor
	public SysDpyResourcesTable() {
		super("关联资源",null);
		doper = new SysDpyResourcesDoper();
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		IEpiAction[] epiAction = new IEpiAction[2];
		epiAction[0] = new SysDpyResourcesAddAction(table);
		epiAction[1] = new SysDpyResourcesDeleteAction(table);
		
		toolBarManager.add(epiAction[0]);
		toolBarManager.add(epiAction[1]);
		toolBarManager.update(true);
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		IEpiEditorInput editorInput = (IEpiEditorInput)this.getEditorInput();
		IBaseVo currentData = editorInput.getCurrentData();
		PrdProductVo product = null;
		if(currentData instanceof PrdProductVo){
			product = (PrdProductVo)currentData;
			this.currentProduct = product;
		}
		
		//点选XXX产品时，获取当前产品的所关联的资源；关联资源为该产品所涉及的外部产品、及其它系统资源；关联资源类型有：部署到、连接（双向/请求）、调用	
		IEpiInput input = doper.loadSysDpyResourcesList(product);
		
		definer = new EpiTableDefiner(null, columnsDefine);
		context = new ViewContext();
		context.setInput(input);
		table = new EpiTable(parent, definer, context);
		
		return table;
	}
	
	public EpiSelectionProvider getSelectionProvider() {
		return table.getSelectionProvider();
	}
	
	public void setSelection(ISelection selection) {
		//刷新列表数据
		
		
		
	}

	
	/**
	 * 关联资源新增动作
	 * @author WANGQINGPING
	 */
	public class SysDpyResourcesAddAction extends EpiAddAction{
		public SysDpyResourcesAddAction(EpiTable epiTable) {
			super(epiTable);
		}
		
		@Override
		protected void doAddAction() {
			SysDpyResourcesCreateDialog sysDpyResourcesCreateDialog = new SysDpyResourcesCreateDialog(getControl().getShell());
			sysDpyResourcesCreateDialog.open();
		}
		
	}
	
	/**
	 * 关联资源删除动作
	 * @author WANGQINGPING
	 */
	public class SysDpyResourcesDeleteAction extends EpiDeleteAction{
		public SysDpyResourcesDeleteAction(EpiTable epiTable) {
			super(epiTable);
		}
		
		@Override
		protected void doDeleteAction(IBaseVo baseVo) {
			if(baseVo == null || !(baseVo instanceof SysDpyResourcesVo)) return;
			SysDpyResourcesVo sysDpyResourcesVo = (SysDpyResourcesVo) baseVo;
			IEpiInput input = context.getInput();
			input.getDataList().remove(sysDpyResourcesVo);
			table.getViewer().refresh();
			table.putDeletedData(baseVo);
		}
	}
	
	/**
	 * 新增关联资源弹框
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyResourcesCreateDialog extends Dialog{
		private Composite composite;
		private Text textAccsType;//关联类型
		private Combo comboResType;//关联资源类型
		private Text textResName;//关联资源名称
		private Text code;//代码
		private Text desc;//描述
		
		protected SysDpyResourcesCreateDialog(Shell parentShell) {
			super(parentShell);
		}
		
		@Override
		protected Point getInitialSize() {
			return new Point(600, 400);
		}
		
		private void doAddSysDpyResources() {
			SysDpyResourcesVo sdrv = new SysDpyResourcesVo();
			sdrv.setSubType(textAccsType.getText().trim());
			sdrv.setType(comboResType.getItem(comboResType.getSelectionIndex()));
			sdrv.setName(textResName.getText().trim());
			sdrv.setCode(code.getText().trim());
			sdrv.setDescription(desc.getText().trim());
			sdrv.setPrdRid(currentProduct.getRid());
			
			IEpiInput input = context.getInput();
			input.getDataList().add(sdrv);
			table.getViewer().refresh();
			table.putInsertedData(sdrv);
			close();
		}
		
		
		@Override
		protected Control createDialogArea(Composite parent) {
			composite = new Composite(parent, SWT.NONE);
			GridLayout gridLayout = new GridLayout(2,false);
			GridData gridData = new GridData();
			composite.setLayout(gridLayout);
			composite.setLayoutData(gridData);
			
			Label label1 = new Label(composite, SWT.NONE);
			label1.setText("关联类型:");
			textAccsType = new Text(composite, SWT.NONE);
			textAccsType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			
			Label label2 = new Label(composite, SWT.NONE);
			label2.setText("关联资源类型:");
			comboResType = new Combo(composite, SWT.NONE);
			comboResType.setItems(new String[] {"部署到","连接","调用"});
			comboResType.select(0);
			
			Label label3 = new Label(composite, SWT.NONE);
			label3.setText("关联资源名称:");
			textResName = new Text(composite, SWT.NONE);
			textResName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			
			Label label4 = new Label(composite, SWT.NONE);
			label4.setText("代码:");
			code = new Text(composite, SWT.NONE);
			code.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			
			Label label5 = new Label(composite, SWT.NONE);
			label5.setText("描述:");
			desc = new Text(composite, SWT.NONE);
			desc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			
			Button button = new Button(composite,SWT.NONE);
			button.setText("添加");
			GridData buttonGridData = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);		
			buttonGridData.horizontalSpan = 2;
			buttonGridData.widthHint = 80;
			buttonGridData.heightHint = 25;
			button.setLayoutData(buttonGridData);
			
			button.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event event) {
					doAddSysDpyResources();
				}
			});
			return composite;
		}
		
		@Override
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText("添加关联资源");
//			newShell.setLayout(new FillLayout());
//			newShell.setImage(PrddevImageConstant.getImage(BaseImageConstant.A_ADD));
//			newShell.setBounds((int) (newShell.getSize().x/2.5), (int) (newShell.getSize().y/3), 600, 400);
		}
		
		
//		@Override
//		protected void createButtonsForButtonBar(Composite parent) {
//			GridData gridData = new GridData(0,0);
//			parent.setLayoutData(gridData);
//			super.createButtonsForButtonBar(parent);
//		}
		
		@Override
		protected Control createButtonBar(Composite parent) {
			Control createButtonBar = super.createButtonBar(parent);
			getButton(IDialogConstants.OK_ID).setVisible(false);
			getButton(IDialogConstants.CANCEL_ID).setVisible(false);
			return createButtonBar;
		}
		
		

	}
}
