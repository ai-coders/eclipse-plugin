package net.aicoder.epi.devp.prddev.view.editors.sysdpy.opsline;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
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

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.action.table.EpiAddRowAction;
import net.aicoder.epi.base.view.action.table.EpiCopyRowAction;
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
import net.aicoder.epi.devp.prddev.doper.ops.SysDpySchemaDoper;
import net.aicoder.epi.devp.prddev.model.dev.ProductDevVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDpySchemaVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElmCatgVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.util.network.MakeId;

/**
 * 运维流水线-产品部署方案(列表)
 * @author WANGQINGPING
 *
 */
public class SysDpySchemaTable extends BaseTitleArea{
	public static final String ID = SysDpySchemaTable.class.getName();
	private EpiTable table;
	private EpiTableDefiner definer;
	private IViewContext context;
	private PrdProductVo currentSelectProduct;//当前选中的产品
	private SysDpySchemaDoper doper;
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"部署环境", "name", -20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE},
		{"代码", "code", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"类型", "type", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"版本", "version", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"环境准备", "name", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"构建及发布", "name", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"系统监控", "name", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"备注", "remark", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE }
	};
	
	
	//Constructor
	public SysDpySchemaTable() {
		super("部署方案",null);
		this.doper = new SysDpySchemaDoper();
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		IEpiAction[] epiAction = new IEpiAction[5];
		epiAction[0] = new SysDpySchemaAddRowAction(table);//新增
		epiAction[1] = new SysDpySchemaCopyRowAction(table);//复制
		epiAction[2] = new EpiUpRowAction(table);//上移
		epiAction[3] = new EpiDownRowAction(table);//下移
		epiAction[4] = new EpiDeleteRowAction(table);//删除
		toolBarManager.add(epiAction[0]);
		toolBarManager.add(epiAction[1]);
		toolBarManager.add(epiAction[2]);
		toolBarManager.add(epiAction[3]);
		toolBarManager.add(epiAction[4]);
		toolBarManager.update(true);
	}

	@Override
	public void assembleControl(Composite parent) {
		//加载该产品部署方案
		IEpiEditorInput editorInput = (IEpiEditorInput)this.getEditorInput();
		IBaseVo currentData = editorInput.getCurrentData();
		if(currentData instanceof SysElmCatgVo) {
			ProductDevVo productDev = (ProductDevVo) ((SysElmCatgVo)currentData).getParentNode();
			currentSelectProduct = productDev.getProduct();
		}else if(currentData instanceof PrdProductVo){
			currentSelectProduct = (PrdProductVo)currentData;
		}

		
		IEpiInput input = doper.loadSysDpySchemaList(currentSelectProduct);
		definer = new EpiTableDefiner(null,columnsDefine);
		context = new ViewContext();
		context.setInput(input);
		table = new EpiTable(parent, definer, context);
		
		if(input != null && input.getDataList().size() > 0) {
			input.setCurrentData(input.getDataList().get(0));
			context.setInput(input);
			TableViewer viewer = table.getViewer();
			viewer.setInput(input);
			viewer.refresh();
		}
	}
	
	public EpiSelectionProvider getSelectionProvider() {
		return table.getSelectionProvider();
	}
	
	/**
	 * 部署方案-新建动作
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpySchemaAddRowAction extends EpiAddRowAction{
		public SysDpySchemaAddRowAction(EpiTable epiTable) {
			super(epiTable);
		}
		
		public SysDpySchemaAddRowAction(String actionText, ImageDescriptor actionImageDescriptor) {
			super(actionText, actionImageDescriptor);
		}
		
		@Override
		protected void doAddAction() {
			//执行新增处理
			SysDpySchemaAddDialog sysDpySchemaAddDialog = new SysDpySchemaAddDialog(getControl().getShell());
			sysDpySchemaAddDialog.open();
		}
	}
	
	/**
	 * 部署方案-复制动作
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpySchemaCopyRowAction extends EpiCopyRowAction{
		public SysDpySchemaCopyRowAction(EpiTable epiTable) {
			super(epiTable);
		}
		
		public SysDpySchemaCopyRowAction(String actionText, ImageDescriptor actionImageDescriptor) {
			super(actionText, actionImageDescriptor);
		}
		
		@Override
		protected void doCopyAction(IBaseVo currData) {
			//执行复制处理
			
		}
	}
	
	
	/**
	 *  部署方案弹框【新增】
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpySchemaAddDialog extends Dialog{
		private Composite composite;
		private Text dpyEnvText;//部署环境
		private Text codeText;//代码
		private Text typeText;//类型
		private Text versionText;//版本
		private Text envPrepareText;//环境准备
		private Text buildPublishText;//构建及发布
		private Text sysMonitorText;//系统监控
		private Text remarkText;//备注
		
		protected SysDpySchemaAddDialog(Shell parentShell) {
			super(parentShell);
		}
		
		@Override
		protected Point getInitialSize() {
			return new Point(600, 400);
		}
		
		private void doAddSysDpySchema() {
			SysDpySchemaVo sdsv = new SysDpySchemaVo();
			sdsv.setRid(MakeId.newId());
//			opv.setTid(currentSelectProduct.getTid());
			sdsv.setName(dpyEnvText.getText().trim());
			sdsv.setCode(codeText.getText().trim());
			sdsv.setType(typeText.getText().trim());
			sdsv.setVersion(versionText.getText().trim());
			
			IEpiInput input = (IEpiInput) table.getViewer().getInput();
			input.getDataList().add(sdsv);
			table.getViewer().refresh();
			table.putInsertedData(sdsv);
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
			Label dpyEnvLabel = new Label(composite, SWT.NONE);
			dpyEnvLabel.setText("部署环境:");
			dpyEnvLabel.setAlignment(SWT.RIGHT);
			dpyEnvLabel.setLayoutData(gridDataLabel);
			dpyEnvText = new Text(composite, SWT.NONE);
			dpyEnvText.setLayoutData(gridDataText);
			
			//代码
			Label codeLabel = new Label(composite, SWT.NONE);
			codeLabel.setText("代码:");
			codeLabel.setAlignment(SWT.RIGHT);
			codeLabel.setLayoutData(gridDataLabel);
			codeText = new Text(composite, SWT.NONE);
			codeText.setLayoutData(gridDataText);
			
			//类型
			Label typeLabel = new Label(composite, SWT.NONE);
			typeLabel.setText("类型:");
			typeLabel.setAlignment(SWT.RIGHT);
			typeLabel.setLayoutData(gridDataLabel);
			typeText = new Text(composite, SWT.NONE);
			typeText.setLayoutData(gridDataText);
			
			//版本
			Label versionLabel = new Label(composite, SWT.NONE);
			versionLabel.setText("版本:");
			versionLabel.setAlignment(SWT.RIGHT);
			versionLabel.setLayoutData(gridDataLabel);
			versionText = new Text(composite, SWT.NONE);
			versionText.setLayoutData(gridDataText);
			
			
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
					doAddSysDpySchema();
				}
			});
			
			return composite;
		}
		
		@Override
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText("新增部署方案");
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
