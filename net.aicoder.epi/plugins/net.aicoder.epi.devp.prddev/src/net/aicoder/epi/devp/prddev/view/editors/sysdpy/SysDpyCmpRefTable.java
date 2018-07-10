package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.apache.commons.lang.ObjectUtils.Null;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
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
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.control.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.control.table.EpiTable;
import net.aicoder.epi.base.view.control.table.EpiTableDefiner;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.drag.BaseDropTarget;
import net.aicoder.epi.base.view.drag.BaseVoTransfer;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;
import net.aicoder.epi.devp.prddev.doper.ops.SysDpyCmpRefDoper;
import net.aicoder.epi.devp.prddev.model.ops.SysCmpVo;
import net.aicoder.epi.devp.prddev.model.ops.SysDpyCmpRefVo;
import net.aicoder.epi.devp.prddev.model.ops.SysDpyResInstVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

/**
 * 部署模型-XXX组件(列表)
 * @author WANGQINGPING
 *
 */
public class SysDpyCmpRefTable extends BaseTitleArea {
	public static final String ID = SysDpyCmpRefTable.class.getName();
	private EpiTable table;
	private EpiTableDefiner definer;
	private IViewContext context;
	private SysDpyCmpRefDoper doper;
	private PrdProductVo currentSelectProduct;//当前选中的产品
	private SysCmpVo currentSysCmp;//当前选中系统、子系统、组件对象引用
	private SysDpyResInstVo currentSysDpyResInst;//当前选中资源应用场景对象引用
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"关联类型", "code", -20, null, null, null, IColumnDefiner.EDITABLE },
		{"关联元素", "name", 20, null, null, null, IColumnDefiner.EDITABLE },
		{"别名", "alias", },
		{"类型", "type", },
		{"描述", "description", 0, null, null, null, IColumnDefiner.EDITABLE }
	};

	public SysDpyCmpRefTable() {
		super("XXX组件",null);
		doper = new SysDpyCmpRefDoper();
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		IEpiAction[] epiAction = new IEpiAction[2];
		epiAction[0] = new SysDpyCmpRefAddAction(table);
		epiAction[1] = new SysDpyCmpRefDeleteAction(table);
		
		toolBarManager.add(epiAction[0]);
		toolBarManager.add(epiAction[1]);
		toolBarManager.update(false);
	}

	@Override
	//protected Control createAreaControl(Composite parent) {
	public void assembleControl(Composite parent) {
		IEpiEditorInput editorInput = (IEpiEditorInput)this.getEditorInput();
		IBaseVo currentData = editorInput.getCurrentData();
		if(currentData instanceof PrdProductVo){
			currentSelectProduct = (PrdProductVo)currentData;
		}
//		List<IBaseVo> dataList = doper.listSysDpyElement(currentSelectProduct);
//		IEpiInput input = new EpiInput();
//		input.setDataList(dataList);
//		context.setInput(input);
		
		definer = new EpiTableDefiner(null, columnsDefine);
		context = new ViewContext();
		table = new EpiTable(parent, definer, context);
		
		//添加拖入支持
		DropTarget dropTarget = new DropTarget(table, DND.DROP_MOVE|DND.DROP_DEFAULT|DND.DROP_COPY);			
		dropTarget.setTransfer(new Transfer[] {BaseVoTransfer.getInstance()});
		dropTarget.addDropListener(new SysDpyCmpRefDropTarget());
		//return table;
	}
	
	public EpiSelectionProvider getSelectionProvider() {
		return table.getSelectionProvider();
	}
	
	/**
	 * 刷新列表数据
	 * @param selection
	 */
	public void setSelection(ISelection selection) {
		Object firstElement = ((StructuredSelection)selection).getFirstElement();
		if(firstElement instanceof SysCmpVo) {
			currentSysCmp = (SysCmpVo)firstElement;
		}else {
			return;
		}
		
		EpiInput input = doper.loadSysDpyCmpRefList(currentSysCmp);
		context.setInput(input);
		table.getViewer().setInput(input);
		table.getViewer().refresh();
	}
	
	
	/**
	 * 从[资源应用场景]进入到xxx组件区域,做xxx组件数据维护操作
	 * @param baseVo
	 */
	public void setSelectionBySysDpyResInstVo(ISelection selection) {
		Object firstElement = ((StructuredSelection)selection).getFirstElement();
		if(firstElement instanceof SysDpyResInstVo) {
			currentSysDpyResInst = (SysDpyResInstVo)firstElement;
		}else {
			return;
		}
		
		//依据当前[资源应用场景]条件查询xxx组件数据
		EpiInput input = doper.loadSysDpyCmpRefList(currentSysDpyResInst);
		context.setInput(input);
		table.getViewer().setInput(input);
		table.getViewer().refresh();
	}
	
	
	/**
	 * XXX组件新增动作
	 * @author WANGQINGPING
	 */
	public class SysDpyCmpRefAddAction extends EpiAddAction{
		public SysDpyCmpRefAddAction(EpiTable epiTable) {
			super(epiTable);
		}
		
		@Override
		protected void doAddAction() {
			SysDpyCmpRefAddDialog sysDpyCmpRefAddDialog = new SysDpyCmpRefAddDialog(getControl().getShell());
			sysDpyCmpRefAddDialog.open();
		}
		
	}
	
	/**
	 * XXX组件删除动作
	 * @author WANGQINGPING
	 */
	public class SysDpyCmpRefDeleteAction extends EpiDeleteAction{
		public SysDpyCmpRefDeleteAction(EpiTable epiTable) {
			super(epiTable);
		}
		
		@Override
		protected void doDeleteAction(IBaseVo baseVo) {
			if(baseVo == null || !(baseVo instanceof SysDpyCmpRefVo)) return;
			SysDpyCmpRefVo sysDpyCmpRefVo = (SysDpyCmpRefVo) baseVo;
			IEpiInput input = (IEpiInput) table.getViewer().getInput();
			input.getDataList().remove(sysDpyCmpRefVo);
			table.getViewer().refresh();
			table.putDeletedData(baseVo);
		}
	}
	
	
	/**
	 * xxx组件新增弹框
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyCmpRefAddDialog extends Dialog{
		private Composite composite;
		private Text textAccsType;//关联类型
		private Combo comboResType;//关联资源类型
		private Text textResName;//关联资源名称
		private Text code;//代码
		private Text desc;//描述
		
		protected SysDpyCmpRefAddDialog(Shell parentShell) {
			super(parentShell);
		}
		
		@Override
		protected Point getInitialSize() {
			return new Point(600, 400);
		}
		
		private void doAddSysDpyCmpRef() {
			SysDpyCmpRefVo sdcrv = new SysDpyCmpRefVo();
			sdcrv.setRid(38882983898L);
			sdcrv.setTid(1);
			sdcrv.setName(textResName.getText().trim());
			sdcrv.setCode(code.getText().trim());
			sdcrv.setAlias("");
			sdcrv.setType(comboResType.getItem(comboResType.getSelectionIndex()));
			sdcrv.setSubType(textAccsType.getText().trim());
			sdcrv.setDescription(desc.getText().trim());
			sdcrv.setPrdRid(currentSelectProduct.getRid());
			
			IEpiInput input = (IEpiInput) table.getViewer().getInput();
			input.getDataList().add(sdcrv);
			table.getViewer().refresh();
			table.putInsertedData(sdcrv);
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
			label1.setText("组件类型:");
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
					doAddSysDpyCmpRef();
				}
			});
			return composite;
		}
		
		@Override
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText("添加xxx组件数据");
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
	
	/**
	 * table拖入响应监听
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyCmpRefDropTarget extends BaseDropTarget{
		@Override
		public void dragEnter(DropTargetEvent event) {
			super.dragEnter(event);
			if (event.detail == DND.DROP_DEFAULT) {
				event.detail = DND.DROP_COPY;
			}
		}
		@Override
		public void drop(DropTargetEvent event) {
			super.drop(event);
			System.out.println(event.data);
		}
		@Override
		public void dragLeave(DropTargetEvent event) {
			// TODO Auto-generated method stub
			super.dragLeave(event);
		}
		
	}
	
	
}
