package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.action.tree.EpiAddBrotherAction;
import net.aicoder.epi.base.view.action.tree.EpiAddChildAction;
import net.aicoder.epi.base.view.action.tree.EpiDeleteNodeAction;
import net.aicoder.epi.base.view.action.tree.EpiFilterAction;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.control.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.control.tree.EpiTree;
import net.aicoder.epi.base.view.control.tree.EpiTreeDefiner;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.drag.BaseDragSource;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;
import net.aicoder.epi.devp.prddev.doper.ops.OpsAssetCmdbDoper;
import net.aicoder.epi.devp.prddev.doper.ops.SysDpyResInstDoper;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDpyResInstVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDpyResourcesVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysDpySchemaVo;
import net.aicoder.epi.devp.prddev.model.ops.OpsAssetCmdbVo;

/**
 * 部署模型-资源应用场景(树表)
 * @author WANGQINGPING
 *
 */
public class SysDpyResInstanceTreeTable extends BaseTitleArea{
	public static final String ID = SysDpyResInstanceTreeTable.class.getName();		
	private EpiTree tree;
	private EpiTreeDefiner definer;
	private IViewContext context;
	private SysDpyResInstDoper doper;//资源应用场景
	private OpsAssetCmdbDoper doperOps;//IT资产
//	private SysDpyResInstVo currentSysDpyResIns;//当前操作资源应用场景对象引用
//	private PrdProductVo currentPrdProduct;//当前操作产品对象引用
	private SysDpyResourcesVo currentSysDpyResources;//当前关联资源对象引用
	private SysDpySchemaVo currentSysDpySchema;//当前部署方案对象引用
	private int currentHandlerRole = 0;//默认0,关联资源1,部署方案2
	private SysDpyResInsAddDialogType sysDpyResInsAddDialogType = SysDpyResInsAddDialogType.NONE;//当前资源使用场景添加弹框的操作类型
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"名称", "name", -20, null, null, null, IColumnDefiner.CE_TEXT },
		{"代码", "code", 20, null, null, null, IColumnDefiner.CE_TEXT }
	};
	
	
	
	public SysDpyResInstanceTreeTable() {
		super("资源应用场景",null);
		this.doper = new SysDpyResInstDoper();
		this.doperOps = new OpsAssetCmdbDoper();
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		IEpiAction[] epiAction = new IEpiAction[4];
		epiAction[0] = new SysDpyResInsAddChildAction(tree);
		epiAction[1] = new SysDpyResInsAddBrotherAction(tree);
		epiAction[2] = new SysDpyResInsDeleteAction(tree);
		epiAction[3] = new SysDpyResInsFilterAction(tree);
		
		toolBarManager.add(epiAction[0]);
		toolBarManager.add(epiAction[1]);
		toolBarManager.add(epiAction[2]);
		toolBarManager.add(epiAction[3]);
		toolBarManager.update(false);
	}

	@Override
	public void assembleControl(Composite parent) {
		definer = new EpiTreeDefiner(null, columnsDefine);
		context = new ViewContext();
		tree = new EpiTree(parent, definer, context);

		//添加拖动支持
		DragSource dragSource = new DragSource(tree.getViewer().getControl(), DND.DROP_MOVE|DND.DROP_COPY);
		dragSource.setTransfer(new Transfer[] {SysDpyTransfer.getInstance()});
		dragSource.addDragListener(new SysDpyResInsDragSource());

	}
	
	public void bindSelectionDataEvent(ISelection selection) {
		tree.bindSelectionDataEvent();
	}

	public EpiSelectionProvider getSelectionProvider() {
		return tree.getSelectionProvider();
	}
	
	//刷新树表数据
	public void setSelection(ISelection selection) {	
		//点选“关联资源”的记录时，维护当前关联资源的“资源实例”；
		//添加“资源实例”时，可多选所属的部署方案；
		//依据所选择的部署方案，显示缺省的资源实例；
		//选择“过滤”按钮进行其它部署方案的

		Object firstElement = ((IStructuredSelection)selection).getFirstElement();
		if(firstElement instanceof SysDpyResourcesVo) {
			currentSysDpyResources = (SysDpyResourcesVo)firstElement;
		}else {
			return;
		}
		
		//依据当前[关联资源]条件查询资源应用场景数据
		IEpiInput input = doper.loadSysDypResInstList(currentSysDpyResources);
		if(input != null) {
			context.setInput(input);
			tree.getViewer().setInput(input);
			tree.getViewer().refresh();
		}

		currentHandlerRole = 1;//设置[关联资源]进入
	}
	
	/**
	 * 从[部署方案]进入到资源应用场景,做数据更新操作
	 * //添加“资源实例”时，可多选所属的部署方案；
	 * //依据所选择的部署方案，显示缺省的资源实例；
	 * //选择“过滤”按钮进行其它部署方案的
	 * @param baseVo
	 */
	public void setSelectionBySysDpySchema(IBaseVo baseVo) {
		if(baseVo instanceof SysDpySchemaVo) {
			currentSysDpySchema = (SysDpySchemaVo)baseVo;
		}else {
			return;
		}
		
		//依据当前[部署方案]条件查询资源应用场景数据
		IEpiInput input = doper.loadSysDypResInstList(currentSysDpySchema);
		if(input != null) {
			context.setInput(input);
			tree.getViewer().setInput(input);
			tree.getViewer().refresh();
		}
		
		currentHandlerRole = 2;//设置[部署方案]进入
	}
	
	/**
	 * 判断当前是否有选中交联资源或部署方案,若未选中其一则资源使用场景区域工具按钮无效
	 * @return
	 */
	public boolean isSelectResOrSchema() {
		if(currentSysDpyResources != null && currentHandlerRole == 1) {
			return true;
		}else if (currentSysDpySchema != null && currentHandlerRole == 2) {
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * 部署模型-资源应用场景新增(子节点)动作
	 * @author WANGQINGPING
	 */
	public class SysDpyResInsAddChildAction extends EpiAddChildAction {
		public SysDpyResInsAddChildAction(EpiTree tree) {
			super(tree);
		}
		
		@Override
		protected IBaseVo doAddChildNode(IBaseVo parentData) {
			if(!isSelectResOrSchema()) return null;
			if(parentData == null) {
				MessageDialog.openInformation(getControl().getShell(), "提示", "请选择父级节点后再添加子节点");
				return null;
			}
			SysDpyResInsAddDialog sysDpyResInsAddDialog = new SysDpyResInsAddDialog(getControl().getShell(),parentData);			
			sysDpyResInsAddDialogType = SysDpyResInsAddDialogType.ADD_CHILD;
			sysDpyResInsAddDialog.open();
			return null;
		}
	}
	
	/**
	 * 部署模型-资源应用场景新增(兄弟节点)动作
	 * @author WANGQINGPING
	 */
	public class SysDpyResInsAddBrotherAction extends EpiAddBrotherAction {
		public SysDpyResInsAddBrotherAction(EpiTree tree) {
			super(tree);
		}
		
		@Override
		protected IBaseVo doAddBrotherRow(IBaseVo parentData) {
			if(!isSelectResOrSchema()) return null;
			SysDpyResInsAddDialog sysDpyResInsAddDialog = new SysDpyResInsAddDialog(getControl().getShell(),parentData);
			sysDpyResInsAddDialogType = SysDpyResInsAddDialogType.ADD_BROTHER;
			sysDpyResInsAddDialog.open();
			return null;
		}
	}
	
	/**
	 * 部署模型-资源应用场景删除动作
	 * @author WANGQINGPING
	 */
	public class SysDpyResInsDeleteAction extends EpiDeleteNodeAction{
		public SysDpyResInsDeleteAction(EpiTree tree) {
			super(tree);
		}
		
		@Override
		protected int doDdeleteNode(IBaseVo[] currDatas) {
			if(!isSelectResOrSchema()) return 0;
			if(currDatas == null) return 0;
			for (IBaseVo currData : currDatas) {
				SysDpyResInstVo currNode = (SysDpyResInstVo)currData;
				SysDpyResInstVo parentNode = (SysDpyResInstVo) currNode.getParentNode();
				if(parentNode != null) {
					parentNode.getChildrenList().remove(currNode);
				}else {
					((IEpiInput)tree.getViewer().getInput()).getDataList().remove(currNode);
				}
			}
			return currDatas.length;
		}
	}
	
	/**
	 * 部署模型-资源应用场景过滤动作
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyResInsFilterAction extends EpiFilterAction {
		public SysDpyResInsFilterAction(EpiTree tree) {
			super(tree);
		}
		
		@Override
		protected void doFilterAction() {
			if(!isSelectResOrSchema()) return;
			SysDpyResInsFilterDialog sysDpyResInsFilterDialog = new SysDpyResInsFilterDialog(getControl().getShell());
			sysDpyResInsFilterDialog.open();
		}
	}
	
	
	
	/**
	 * [添加]资源应用场景弹框
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyResInsAddDialog extends Dialog{
		private SysDpyResInstVo currentData;
		private List<IBaseVo> currentOpsAssetCmdbList;//当前IT资产 数据列表
		
		private Composite composite;
		private Text textAssoResCode;//关联资源 代码
		private Text textAssoResName;//关联资源 名称
		private Combo comboAssoResType;//关联资源 类型
		private Text textAssoResDesc;//关联资源 描述
		
//		private Text textITResNo;//关联IT资产 编号
		private Combo comboITAssetEType;//关联IT资产 元素类型
//		private Combo textITResCode;//关联IT资产 代码
		

		protected SysDpyResInsAddDialog(Shell parentShell,IBaseVo baseVo) {
			super(parentShell);
			this.currentData = (SysDpyResInstVo) baseVo;
		}
		
		@Override
		protected Point getInitialSize() {
			return new Point(600, 400);
		}
		
		/**
		 * 执行[添加]资源应用场景数据
		 */
		private void doAddSysDpyResIns() {
			//IT资产类型和关联资源的类型要保持一致
			OpsAssetCmdbVo oacv = (OpsAssetCmdbVo) currentOpsAssetCmdbList.get(comboITAssetEType.getSelectionIndex());		
			SysDpyResInstVo newData = new SysDpyResInstVo();
			newData.setCode(textAssoResCode.getText().trim());
			newData.setName(textAssoResName.getText().trim());
			newData.setEtype("SYS_DPY_RES_INST");
			newData.setType(comboAssoResType.getItem(comboAssoResType.getSelectionIndex()));
			newData.setDescription(textAssoResDesc.getText().trim());
			newData.setAssetRid(oacv.getRid());//关联IT资产ID
			newData.setPrdRid(currentHandlerRole == 1 ? currentSysDpyResources.getPrdRid():currentHandlerRole == 2 ? currentSysDpySchema.getPrdRid():null);						
			if(sysDpyResInsAddDialogType == SysDpyResInsAddDialogType.ADD_CHILD) {//子节点
				newData.setParentNode(currentData);
				currentData.getChildrenList().add(newData);
			}else if(sysDpyResInsAddDialogType == SysDpyResInsAddDialogType.ADD_BROTHER) {//兄弟节点
				newData.setParentNode(currentData != null ? currentData.getParentNode():null);
				if(currentData == null || currentData.getParentNode() == null) {
					((IEpiInput)tree.getViewer().getInput()).getDataList().add(newData);
				}else {
					currentData.getParentNode().getChildrenList().add(newData);
				}
			}
			tree.getViewer().refresh();
			tree.putInsertedData(newData);
			
			//关闭弹框
			close();
		}
		
		
		@Override
		protected Control createDialogArea(Composite parent) {
			composite = new Composite(parent, SWT.NONE);
			composite.setLayout(new GridLayout(1,false));
			
			//关联资源
			Group groupAssoRes = new Group(composite, SWT.NONE);
			groupAssoRes.setText("关联资源");
			groupAssoRes.setLayout(new GridLayout(2,true));
//			groupAssoRes.setLayoutData(gridData);
			
			Label label1 = new Label(groupAssoRes, SWT.NONE);
			label1.setText("代码:");
			textAssoResCode = new Text(groupAssoRes, SWT.NONE);
			textAssoResCode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			
			Label label2 = new Label(groupAssoRes, SWT.NONE);
			label2.setText("名称:");
			textAssoResName = new Text(groupAssoRes, SWT.NONE);
			textAssoResName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			
			Label label3 = new Label(groupAssoRes, SWT.NONE);
			label3.setText("类型:");
			comboAssoResType = new Combo(groupAssoRes, SWT.NONE);
			comboAssoResType.setItems(new String[] {"部署到","连接","调用"});
			comboAssoResType.select(0);
			
			Label label4 = new Label(groupAssoRes, SWT.NONE);
			label4.setText("描述:");
			textAssoResDesc = new Text(groupAssoRes, SWT.NONE);
			textAssoResDesc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			

			//IT资产
			Group groupITAsset = new Group(composite, SWT.NONE);
			groupITAsset.setText("关联IT资产");
			groupITAsset.setLayout(new GridLayout(2,true));
			
			currentOpsAssetCmdbList = doperOps.loadOpsAssetCmdbList(currentData);
			List<String> items = new ArrayList<>();
			for (IBaseVo bv : currentOpsAssetCmdbList) {
				OpsAssetCmdbVo oacv = (OpsAssetCmdbVo) bv;
				items.add(oacv.getEtype());
			}
			Label label21 = new Label(groupITAsset, SWT.NONE);
			label21.setText("元素类型:");
			comboITAssetEType = new Combo(groupITAsset, SWT.NONE);
			comboITAssetEType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			comboITAssetEType.setItems(items.toArray(new String[]{}));
			
			
			
//			Label label11 = new Label(groupITRes, SWT.NONE);
//			label11.setText("资产编号:");
//			textITResNo = new Text(groupITRes, SWT.NONE);
//			textITResNo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			
//			Label label13 = new Label(groupITRes, SWT.NONE);
//			label13.setText("类型代码:");
//			textITResCode = new Combo(groupITRes, SWT.NONE);
//			textITResCode.setItems(new String[] {"部署到","连接","调用"});
//			textITResCode.select(0);
			
			
			//添加按钮
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
					doAddSysDpyResIns();
				}
			});
			return composite;
		}
		
		@Override
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText("添加资源应用场景数据");
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
	
	/**
	 * [过滤]资源应用场景弹框
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyResInsFilterDialog extends Dialog{
		private Composite composite;
		private Text textType;//资源实例 类型
		private Text textName;//资源实例 名称
		private Text textCode;//资源实例 代码
		private Text textDesc;//资源实例 描述
		

		protected SysDpyResInsFilterDialog(Shell parentShell) {
			super(parentShell);
		}
		
		@Override
		protected Point getInitialSize() {
			return new Point(600, 400);
		}
		
		/**
		 * 执行[添加]资源应用场景数据
		 */
		private void doFilterSysDpyResIns() {
			SysDpyResInstVo sdriv = new SysDpyResInstVo();
			String type = textType.getText().trim();
			String name = textName.getText().trim();
			String code = textCode.getText().trim();
			String desc = textDesc.getText().trim();
			sdriv.setType(type);
			sdriv.setName(name);
			sdriv.setCode(code);
			sdriv.setDescription(desc);
			sdriv.setPrdRid(currentHandlerRole == 1 ? currentSysDpyResources.getPrdRid():currentHandlerRole == 2 ? currentSysDpySchema.getPrdRid():null);
			
			IEpiInput input = doper.loadSysDypResInstFilterList(sdriv);
			context.setInput(input);
			tree.getViewer().setInput(input);
			tree.getViewer().refresh();
			close();//关闭弹框
		}
		
		
		@Override
		protected Control createDialogArea(Composite parent) {
			composite = new Composite(parent, SWT.NONE);
			GridLayout gridLayout = new GridLayout(2,false);
			GridData gridData = new GridData();
			composite.setLayout(gridLayout);
			composite.setLayoutData(gridData);
			
			Label label1 = new Label(composite, SWT.NONE);
			label1.setText("类型:");
			textType = new Text(composite, SWT.NONE);
			textType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			
			
			Label label2 = new Label(composite, SWT.NONE);
			label2.setText("名称:");
			textName = new Text(composite, SWT.NONE);
			textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			
			Label label3 = new Label(composite, SWT.NONE);
			label3.setText("代码:");
			textCode = new Text(composite, SWT.NONE);
			textCode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			
			Label label4 = new Label(composite, SWT.NONE);
			label4.setText("描述:");
			textDesc = new Text(composite, SWT.NONE);
			textDesc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			
			Button button = new Button(composite,SWT.NONE);
			button.setText("过滤");
			GridData buttonGridData = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);		
			buttonGridData.horizontalSpan = 2;
			buttonGridData.widthHint = 80;
			buttonGridData.heightHint = 25;
			button.setLayoutData(buttonGridData);
			
			button.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event event) {
					doFilterSysDpyResIns();
				}
			});
			return composite;
		}
		
		@Override
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText("过滤资源应用场景数据");
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
	
	
	/**
	 * 资源使用场景添加操作类型枚举
	 * @author WANGQINGPING
	 */
	public enum SysDpyResInsAddDialogType{
		NONE("NONE"),
		ADD_CHILD("ADD_CHILD"),
		ADD_BROTHER("ADD_BROTHER"),
		;
		
		private String type;
		
		private SysDpyResInsAddDialogType(String type) {
			this.type = type;
		}
		
		public String getType() {
			return type;
		}
	}
	
	
	/**
	 * tree拖动响应监听
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyResInsDragSource extends BaseDragSource{
		@Override
		public void dragStart(DragSourceEvent event) {
			super.dragStart(event);
			if(tree.getFirstSelectedItem() instanceof IBaseVo) {
				event.doit = true;
			}else {
				event.doit = false;
			}
		}
		@Override
		public void dragSetData(DragSourceEvent event) {
			super.dragSetData(event);
			event.data = tree.getFirstSelectedItem();
		}
	}
	
}
