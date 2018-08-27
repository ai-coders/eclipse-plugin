package net.aicoder.epi.devp.prddev.view.editors.sysdpy.model;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import net.aicoder.epi.base.BaseImageConstant;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.BaseAction;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.action.table.EpiAddRowAction;
import net.aicoder.epi.base.view.action.table.EpiDeleteRowAction;
import net.aicoder.epi.base.view.action.table.EpiDownRowAction;
import net.aicoder.epi.base.view.action.table.EpiUpRowAction;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.control.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.control.table.EpiTable;
import net.aicoder.epi.base.view.control.table.EpiTableDefiner;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;
import net.aicoder.epi.devp.prddev.doper.ops.SysDpyResPlanDoper;
import net.aicoder.epi.devp.prddev.model.ops.OpsResPlanVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.util.network.MakeId;

/**
 * 部署模型-资源规划(列表)
 * @author WANGQINGPING
 *
 */
public class SysDpyResPlanTable extends BaseTitleArea{
	public static final String ID = SysDpyResPlanTable.class.getName();		
	private EpiTable table;
	private EpiTableDefiner definer;
	private IViewContext context;
	private SysDpyResPlanDoper doper;
	private static PrdProductVo currentSelectProduct;//当前选中的产品
	
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"资源名称", "name", -20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE},
		{"资源代码", "code", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"资源别名", "alias", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"资源类型", "type", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"需求描述", "description", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"组件关联", "associate", 20, IColumnDefiner.CE_BUTTON, null, null, IColumnDefiner.EDITABLE }
	};
	
	//Constructor
	public SysDpyResPlanTable() {
		super("资源规划",null);
		doper = new SysDpyResPlanDoper();
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		IEpiAction[] epiAction = new IEpiAction[5];
		epiAction[0] = new SysDpyResPlanAddRowAction(table); //新增
		epiAction[1] = new SysDpyResPlanDisplayAction(table);//展示
		epiAction[2] = new EpiUpRowAction(table); //上移
		epiAction[3] = new EpiDownRowAction(table); //下移
		epiAction[4] = new EpiDeleteRowAction(table); //删除
		toolBarManager.add(epiAction[0]);
		toolBarManager.add(epiAction[1]);
		toolBarManager.add(epiAction[2]);
		toolBarManager.add(epiAction[3]);
		toolBarManager.add(epiAction[4]);
		toolBarManager.update(true);
	}

	@Override
	public void assembleControl(Composite parent) {
		IEpiEditorInput editorInput = (IEpiEditorInput)this.getEditorInput();
		IBaseVo currentData = editorInput.getCurrentData();
		if(currentData instanceof PrdProductVo){
			currentSelectProduct = (PrdProductVo)currentData;
		}
		
		//点选XXX产品时，获取当前产品的资源规划数据	
		IEpiInput input = doper.loadSysDpyResPlanList(currentSelectProduct);
		
		definer = new EpiTableDefiner(null, columnsDefine);
		context = new ViewContext();
		context.setInput(input);
		table = new EpiTable(parent, definer, context);

	}
	
	public void bindSelectionDataEvent(ISelection selection) {
		table.bindSelectionDataEvent();
	}
	
	public EpiSelectionProvider getSelectionProvider() {
		return table.getSelectionProvider();
	}
	
	/**
	 * 获取当前table对象
	 * @return table
	 */
	public EpiTable getTable() {
		return table;
	}
	
	
	
	
	/**
	 * 资源规划-新增行动作
	 * @author WANGQINGPING
	 */
	public class SysDpyResPlanAddRowAction extends EpiAddRowAction{
		public SysDpyResPlanAddRowAction(EpiTable epiTable) {
			super(epiTable);
		}
		
		@Override
		protected void doAddAction() {
			SysDpyResPlanAddDialog sysDpyResourcesAddDialog = new SysDpyResPlanAddDialog(getControl().getShell());
			sysDpyResourcesAddDialog.open();
		}
		
	}
	
	/**
	 * 资源规划-展示动作
	 * @author WANGQINGPING
	 */
	public static class SysDpyResPlanDisplayAction extends BaseAction{
		private static String actionText = "展示";
		private static ImageDescriptor actionImageDescriptor = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_DISPLAY);
		private ImageDescriptor actionImageDescriptorDis = BaseImageConstant.getImageDescriptor(BaseImageConstant.A_DISPLAY);
		private String toolTipText = "展示!";
		private String visibleWhenCount = "*";
		private EpiTable epiTable;
		
		
		private SysDpyResPlanDisplayAction() {
			super(actionText, actionImageDescriptor);
			this.setVisibleWhenCount(visibleWhenCount);
			this.setDisabledImageDescriptor(actionImageDescriptorDis);
			this.setToolTipText(toolTipText);
		}
		
		public SysDpyResPlanDisplayAction(String actionText, ImageDescriptor actionImageDescriptor) {
			super(actionText, actionImageDescriptor);
			this.setVisibleWhenCount(visibleWhenCount);
			this.setDisabledImageDescriptor(actionImageDescriptorDis);
			this.setToolTipText(toolTipText);
		}
		
		public SysDpyResPlanDisplayAction(EpiTable epiTable) {
			super(actionText, actionImageDescriptor);
			this.setVisibleWhenCount(visibleWhenCount);
			this.setDisabledImageDescriptor(actionImageDescriptorDis);
			this.setToolTipText(toolTipText);
			this.setTable(epiTable);
		}

		
		@Override
		public void run() {
			//process
			ISelection selection = epiTable.getViewer().getSelection();
			IBaseVo firstElement = (IBaseVo) ((StructuredSelection)selection).getFirstElement();

			doDisplayAction(firstElement);
			epiTable.getViewer().refresh();
		}
		
		
		protected void doDisplayAction(IBaseVo baseVo) {
//			if(baseVo == null || !(baseVo instanceof SysDpyResourcesVo)) return;
//			SysDpyResourcesVo sysDpyResourcesVo = (SysDpyResourcesVo) baseVo;
//			IEpiInput input = (IEpiInput) epiTable.getViewer().getInput();
//			input.getDataList().remove(sysDpyResourcesVo);
//			epiTable.getViewer().refresh();
//			epiTable.putDeletedData(baseVo);
			
		}

		public void setTable(EpiTable epiTable) {
			this.epiTable = epiTable;
			TableViewer viewer = epiTable.getViewer();
			if (isCheckVisibleWhenCount() && viewer != null) {
				setEnabledByCount(null);
				viewer.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection) event.getSelection();
						setEnabledByCount(selection);
					}
				});
			}
		}

	}
	
	
	/**
	 *  资源规划弹框【新增】
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyResPlanAddDialog extends Dialog{
		private Composite composite;
		private Text resNameText;//资源名称
		private Text resCodeText;//资源代码
		private Text resAliasText;//资源别名
		private Text resTypeText;//资源类型
		private Text resDescText;//需求描述
		
		protected SysDpyResPlanAddDialog(Shell parentShell) {
			super(parentShell);
		}
		
		@Override
		protected Point getInitialSize() {
			return new Point(600, 400);
		}
		
		private void doAddSysDpyResPlan() {
			OpsResPlanVo opv = new OpsResPlanVo();
			opv.setRid(MakeId.newId());
//			opv.setTid(currentSelectProduct.getTid());
			opv.setName(resNameText.getText().trim());
			opv.setCode(resCodeText.getText().trim());
			opv.setAlias(resAliasText.getText().trim());
			opv.setDescription(resDescText.getText().trim());
			opv.setType(resTypeText.getText().trim());
			
//			SysDpyResourcesVo sdrv = new SysDpyResourcesVo();
//			sdrv.setRid(MakeId.newId());
//			sdrv.setTid(currentSelectProduct.getTid());
//			sdrv.setName(textResName.getText().trim());
//			sdrv.setCode(code.getText().trim());
//			sdrv.setAlias("");
//			sdrv.setEtype("SYS_DPY_RES");
//			sdrv.setType(comboResType.getItem(comboResType.getSelectionIndex()));
//			sdrv.setSubType(textAccsType.getText().trim());
//			sdrv.setDescription(desc.getText().trim());
//			sdrv.setPrdRid(currentSelectProduct.getRid());
//			
			IEpiInput input = (IEpiInput) table.getViewer().getInput();
			input.getDataList().add(opv);
			table.getViewer().refresh();
			table.putInsertedData(opv);
			close();
		}
		
		
		@Override
		protected Control createDialogArea(Composite parent) {
			composite = new Composite(parent, SWT.NONE);
			GridLayout gridLayout = new GridLayout(2,false);
			composite.setLayout(gridLayout);
			composite.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, false,2,2));
			
			GridData gridDataLabel = new GridData(SWT.RIGHT, SWT.FILL, true, false,1,1);
			GridData gridDataText = new GridData(200,20);
			
			//资源名称
			Label resNameLabel = new Label(composite, SWT.NONE);
			resNameLabel.setText("资源名称:");
			resNameLabel.setAlignment(SWT.RIGHT);
			resNameLabel.setLayoutData(gridDataLabel);
			resNameText = new Text(composite, SWT.NONE);
			resNameText.setLayoutData(gridDataText);
			
			//资源代码
			Label resCodeLabel = new Label(composite, SWT.NONE);
			resCodeLabel.setText("资源代码:");
			resCodeLabel.setAlignment(SWT.RIGHT);
			resCodeLabel.setLayoutData(gridDataLabel);
			resCodeText = new Text(composite, SWT.NONE);
			resCodeText.setLayoutData(gridDataText);
			
			//资源别名
			Label resAliasLabel = new Label(composite, SWT.NONE);
			resAliasLabel.setText("资源别名:");
			resAliasLabel.setAlignment(SWT.RIGHT);
			resAliasLabel.setLayoutData(gridDataLabel);
			resAliasText = new Text(composite, SWT.NONE);
			resAliasText.setLayoutData(gridDataText);
			
			//资源类型
			Label resTypeLabel = new Label(composite, SWT.NONE);
			resTypeLabel.setText("资源类型:");
			resTypeLabel.setAlignment(SWT.RIGHT);
			resTypeLabel.setLayoutData(gridDataLabel);
			resTypeText = new Text(composite, SWT.NONE);
			resTypeText.setLayoutData(gridDataText);
			
			//需求描述
			Label resDescLabel = new Label(composite, SWT.NONE);
			resDescLabel.setText("需求描述:");
			resDescLabel.setAlignment(SWT.RIGHT);
			resDescLabel.setLayoutData(gridDataLabel);
			resDescText = new Text(composite, SWT.NONE);
			resDescText.setLayoutData(gridDataText);
			
			
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
					doAddSysDpyResPlan();
				}
			});
			
			return composite;
		}
		
		@Override
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText("新增资源规划");
//			newShell.setLayout(new FillLayout());
//			newShell.setImage(PrddevImageConstant.getImage(BaseImageConstant.A_ADD));
//			newShell.setBounds((int) (newShell.getSize().x/2.5), (int) (newShell.getSize().y/3), 600, 400);
		}
		
		@Override
		protected Control createButtonBar(Composite parent) {
			Control createButtonBar = super.createButtonBar(parent);
			getButton(IDialogConstants.OK_ID).setVisible(false);
			getButton(IDialogConstants.CANCEL_ID).setVisible(false);
			return createButtonBar;
		}
		
	}
	

}
