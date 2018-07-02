package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
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
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;
import net.aicoder.epi.base.view.element.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.element.tree.EpiTree;
import net.aicoder.epi.base.view.element.tree.EpiTreeDefiner;
import net.aicoder.epi.devp.prddev.doper.ops.SysDpyResInstDoper;
import net.aicoder.epi.devp.prddev.model.ops.SysDpyResInstVo;
import net.aicoder.epi.devp.prddev.model.ops.SysDpyResourcesVo;
import net.aicoder.epi.devp.prddev.model.ops.SysDpySchemaVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

/**
 * 部署模型-资源应用场景(树表)
 * @author WANGQINGPING
 *
 */
public class SysDpyResInstanceTreeTable extends BaseWithTitleArea{
	public static final String ID = SysDpyResInstanceTreeTable.class.getName();		
	private EpiTree tree;
	private EpiTreeDefiner definer;
	private IViewContext context;
	private SysDpyResInstDoper doper;
	private PrdProductVo currentProduct;//当前产品
	private SysDpyResourcesVo currentDpyResources;//当前关联资源
	private SysDpyResInstVo currentDpyResIns;//当前关联应用场景
	private SysDpySchemaVo currentDpySchema;//当前部署方案
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"名称", "name", -20, null, null, null, IColumnDefiner.EDITABLE },
		{"代码", "code", 20, null, null, null, IColumnDefiner.EDITABLE }
	};
	
	
	
	public SysDpyResInstanceTreeTable() {
		super("资源应用场景",null);
		doper = new SysDpyResInstDoper();
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
	protected Control createAreaControl(Composite parent) {
		definer = new EpiTreeDefiner(null, columnsDefine);
		context = new ViewContext();
		tree = new EpiTree(parent, definer, context);
		return tree;
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
		SysDpyResourcesVo sysDpyResourcesVo = null;
		if(firstElement instanceof SysDpyResourcesVo) {
			sysDpyResourcesVo = (SysDpyResourcesVo)firstElement;
		}
		
		IEpiInput input = doper.loadSysDypResInstList(sysDpyResourcesVo);
		context.setInput(input);
		tree.getViewer().setInput(context.getInput());
		tree.getViewer().refresh();
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
			if(parentData == null) return null;
			SysDpyResourcesVo sysDpyResVo = null;
			SysDpyResourcesVo scv = (SysDpyResourcesVo)parentData;
			
			sysDpyResVo = new SysDpyResourcesVo();
			sysDpyResVo.setCode(scv.getCode()+"_Copy");
			sysDpyResVo.setName(scv.getName()+"_Copy");
			sysDpyResVo.setEtype("SYS_DPY_RES_INST");
			sysDpyResVo.setParentNode(scv);
			scv.getChildrenList().add(sysDpyResVo);
			
			return sysDpyResVo;
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
		protected IBaseVo doAddBrotherRow(IBaseVo currData) {
			IBaseVo brotherData = null;
			if(currData == null) return brotherData;
			if(currData instanceof SysDpyResourcesVo) {
				SysDpyResourcesVo currModData = (SysDpyResourcesVo)currData;
				SysDpyResourcesVo parentData = (SysDpyResourcesVo) currModData.getParentNode();
				
				
				SysDpyResourcesVo sysDpyResVo = new SysDpyResourcesVo();
				sysDpyResVo.setCode(currModData.getCode()+"_Copy");
				sysDpyResVo.setName(currModData.getName()+"_Copy");
				sysDpyResVo.setPrdRid(currModData.getPrdRid());
				sysDpyResVo.setEtype("SYS_DPY_RES_INST");
				if(parentData != null) {
					sysDpyResVo.setParentNode(parentData);
					parentData.getChildrenList().add(sysDpyResVo);
				}else {
					IEpiInput input = context.getInput();
					List<IBaseVo> dataList = input.getDataList();
					dataList.add(sysDpyResVo);
				}
				tree.getViewer().refresh();
				
//				if (parentData != null) {
//					sysCmpVo.setParentSysCmpVo(parentData);
//					List<IBaseVo> childrenList = parentData.getChildrenSysCmpVoList();
//					int index=0;
//					for(index=0; index<childrenList.size();index++) {
//						if(currData.equals(childrenList.get(index))) {
//							break;
//						}
//					}
//					childrenList.add(index + 1, sysCmpVo);
//				}
				brotherData = sysDpyResVo;
			}
			
			return brotherData;
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
			if(currDatas == null) return 0;
			for (IBaseVo currData : currDatas) {
				SysDpyResourcesVo currNode = (SysDpyResourcesVo)currData;
				SysDpyResourcesVo parentNode = (SysDpyResourcesVo) currNode.getParentNode();
				if(parentNode != null) {
					parentNode.getChildrenList().remove(currNode);
				}else {
					context.getInput().getDataList().remove(currNode);
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
			
			
			
			
			
			
		}
	}
	
	
	
	/**
	 * 新增资源应用场景弹框
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyResInsCreateDialog extends Dialog{
		private Composite composite;
		private Text textAssoResCode;//关联资源代码
		private Text textAssoResName;//关联资源名称
		private Combo comboAssoResType;//关联资源类型
		private Text textAssoResDesc;//关联资源描述
		private Combo comboAssoITRes;//关联资源IT资产
		
		private Text textITResNo;//关联IT资产元素类型
		private Text textITResType;//关联IT资产元素类型
		private Combo textITResCode;//关联IT资产类型代码
		
		protected SysDpyResInsCreateDialog(Shell parentShell) {
			super(parentShell);
		}
		
		@Override
		protected Point getInitialSize() {
			return new Point(600, 400);
		}
		
		private void doAddSysDpyResIns() {
			SysDpyResInstVo sdriv = new SysDpyResInstVo();
			sdriv.setCode(textAssoResCode.getText().trim());
			sdriv.setName(textAssoResName.getText().trim());
			sdriv.setType(comboAssoResType.getItem(comboAssoResType.getSelectionIndex()));
			sdriv.setDescription(textAssoResDesc.getText().trim());
//			sdriv.setAssetRid(textITResNo.getText().trim());
//			IEpiInput input = context.getInput();
//			input.getDataList().add(sdrv);
//			tree.getViewer().refresh();
//			tree.putInsertedData(sdrv);
			close();
		}
		
		
		@Override
		protected Control createDialogArea(Composite parent) {
			GridLayout gridLayout = new GridLayout(2,false);
			gridLayout.makeColumnsEqualWidth = false;
			GridData gridData = new GridData();
			gridData.grabExcessHorizontalSpace = true;
			
			//关联资源
			Group groupAssoRes = new Group(composite, SWT.NONE);
			groupAssoRes.setText("关联资源");
			groupAssoRes.setLayout(gridLayout);
			groupAssoRes.setLayoutData(gridData);
			
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
			
			Label label5 = new Label(groupAssoRes, SWT.NONE);
			label5.setText("描述:");
			comboAssoITRes = new Combo(groupAssoRes, SWT.NONE);
			comboAssoITRes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			
			
			//IT资产
			Group groupITRes = new Group(composite, SWT.NONE);
			groupAssoRes.setText("IT资产");
			groupAssoRes.setLayout(gridLayout);
			groupAssoRes.setLayoutData(gridData);
			
			Label label11 = new Label(groupITRes, SWT.NONE);
			label11.setText("资产编号:");
			textITResNo = new Text(groupITRes, SWT.NONE);
			textITResNo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			
			Label label12 = new Label(groupITRes, SWT.NONE);
			label12.setText("元素类型:");
			textITResType = new Text(groupITRes, SWT.NONE);
			textITResType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
			
			Label label13 = new Label(groupITRes, SWT.NONE);
			label13.setText("类型代码:");
			textITResCode = new Combo(groupITRes, SWT.NONE);
			textITResCode.setItems(new String[] {"部署到","连接","调用"});
			textITResCode.select(0);
			
			
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
