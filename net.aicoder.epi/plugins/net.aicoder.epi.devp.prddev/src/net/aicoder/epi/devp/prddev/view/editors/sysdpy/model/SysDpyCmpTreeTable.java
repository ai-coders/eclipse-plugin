package net.aicoder.epi.devp.prddev.view.editors.sysdpy.model;

import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DragSourceEvent;
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
import net.aicoder.epi.base.model.ITreeNode;
import net.aicoder.epi.base.model.OptionItem;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.action.tree.EpiAddBrotherAction;
import net.aicoder.epi.base.view.action.tree.EpiAddChildAction;
import net.aicoder.epi.base.view.action.tree.EpiDegradeAction;
import net.aicoder.epi.base.view.action.tree.EpiDeleteNodeAction;
import net.aicoder.epi.base.view.action.tree.EpiDownRowAction;
import net.aicoder.epi.base.view.action.tree.EpiEditorAction;
import net.aicoder.epi.base.view.action.tree.EpiFilterAction;
import net.aicoder.epi.base.view.action.tree.EpiLeftNodeAction;
import net.aicoder.epi.base.view.action.tree.EpiRefreshAction;
import net.aicoder.epi.base.view.action.tree.EpiRightNodeAction;
import net.aicoder.epi.base.view.action.tree.EpiUpRowAction;
import net.aicoder.epi.base.view.action.tree.EpiUpgradeAction;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.control.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.control.tree.EpiTree;
import net.aicoder.epi.base.view.control.tree.EpiTreeDefiner;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.drag.BaseDragSource;
import net.aicoder.epi.base.view.part.area.BaseTitleArea;
import net.aicoder.epi.devp.prddev.doper.dev.DropdownOptionsDoper;
import net.aicoder.epi.devp.prddev.doper.dev.ExtendWidgetDoper;
import net.aicoder.epi.devp.prddev.doper.ops.SysCmpDoper;
import net.aicoder.epi.devp.prddev.model.dev.ProductDevVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysCmpVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElmCatgVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.util.network.MakeId;
import net.aicoder.tcom.tools.util.BeanUtil;

/**
 * 部署模型-组件(树表)
 * @author WANGQINGPING
 *
 */
public class SysDpyCmpTreeTable extends BaseTitleArea{
	public static String ID = SysDpyCmpTreeTable.class.getName();
	private EpiTree tree;
	private EpiTreeDefiner definer;
	private IViewContext context;
	private SysCmpDoper doper;
	private ExtendWidgetDoper extendDoper;//获取下拉控件、checkbox控件数据
	private DropdownOptionsDoper dropdownOptionsDoper;
	private PrdProductVo currentSelectProduct;//当前选中的产品
	
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"*名称", "name", 20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"代码", "code", -20, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE },
		{"别名", "alias", 0,IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE},
		{"类型", "type", 0,IColumnDefiner.CE_TEXT,null,null,IColumnDefiner.EDITABLE},
		{"版本", "version", 0,IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE},
		{"描述", "description", 0, IColumnDefiner.CE_TEXT, null, null, IColumnDefiner.EDITABLE }
	};
	
	
	//构造
	public SysDpyCmpTreeTable() {
		super("组件清单",null);
		doper = new SysCmpDoper();
		extendDoper = new ExtendWidgetDoper();
		dropdownOptionsDoper = new DropdownOptionsDoper();
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		IEpiAction[] epiAction = new IEpiAction[9];
//		epiAction[0] = new SysCmpAddChildAction(tree);//新增（子）
//		epiAction[1] = new SysCmpAddBrotherAction(tree);//新增
//		epiAction[2] = new EpiUpRowAction(tree);//上移
//		epiAction[3] = new EpiDownRowAction(tree);//下移
//		epiAction[4] = new SysCmpUpgradeAction(tree);//升级
//		epiAction[5] = new SysCmpDegradeAction(tree);//降级
//		epiAction[6] = new SysCmpDeleteAction(tree);//删除
////	epiAction[7] = new SysCmpFilterAction(tree);//过滤
//		epiAction[7] = new SysCmpRefreshAction(tree);//刷新
		
		epiAction[0] = new SysCmpAddChildAction(tree);//新增（子）
		epiAction[1] = new SysCmpEditorAction(tree);//编辑
		epiAction[2] = new SysCmpAddBrotherAction(tree);//新增（兄弟）
		epiAction[3] = new EpiUpRowAction(tree);//上移
		epiAction[4] = new EpiDownRowAction(tree);//下移
		epiAction[5] = new EpiLeftNodeAction(tree);//升级
		epiAction[6] = new EpiRightNodeAction(tree);//降级
		epiAction[7] = new EpiDeleteNodeAction(tree);//删除
		epiAction[8] = new SysCmpRefreshAction(tree);//刷新
		
		toolBarManager.add(epiAction[0]);
		toolBarManager.add(epiAction[1]);
		toolBarManager.add(epiAction[2]);
		toolBarManager.add(epiAction[3]);
		toolBarManager.add(epiAction[4]);
		toolBarManager.add(epiAction[5]);
		toolBarManager.add(epiAction[6]);
		toolBarManager.add(epiAction[7]);
		toolBarManager.add(epiAction[8]);
		toolBarManager.update(true);
		
	}

	@Override
	public void assembleControl(Composite parent) {
		IEpiEditorInput editorInput = (IEpiEditorInput)this.getEditorInput();
		IBaseVo currentData = editorInput.getCurrentData();
		if(currentData instanceof SysElmCatgVo) {
			ProductDevVo productDev = (ProductDevVo) ((SysElmCatgVo)currentData).getParentNode();
			currentSelectProduct = productDev.getProduct();
		}else if(currentData instanceof PrdProductVo){
			currentSelectProduct = (PrdProductVo)currentData;
		}
		
		definer = new EpiTreeDefiner(null, columnsDefine);
		context = new ViewContext();
		tree = new EpiTree(parent, definer, context);
		
		//获取combo,checkbox数据
//		List<OptionItem> listDropdownOption = dropdownOptionsDoper.listDropdownOption("cmpType");
//		if(listDropdownOption != null && listDropdownOption.size() > 0) {
//			String[] cellEditorItems = new String[listDropdownOption.size()];
//			for (int i=0;i<listDropdownOption.size();i++) {
//				cellEditorItems[i] = listDropdownOption.get(i).getValue();
//			}
//			definer.putRefObjects("类型", listDropdownOption);
//			definer.putCellEditorItems("类型", cellEditorItems);
//		}
//		tree.setRefObjectsType(refObjectsCode);

		//点选XXX产品时，获取当前产品的系统、子系统、组件；
		//可新增/删除系统、子系统、组件等，及维护系统、子系统、组件结构
		IEpiInput input = doper.loadSysCmpList(currentSelectProduct);
		if(input != null && input.getDataList().size() > 0) {
			input.setCurrentData(input.getDataList().get(0));
			context.setInput(input);
			TreeViewer treeViewer = tree.getViewer();
			treeViewer.setInput(input);
			treeViewer.refresh();
		}
		
	}
	
	public void bindSelectionDataEvent(ISelection selection) {
		tree.bindSelectionDataEvent();
	}
	
	public EpiSelectionProvider getSelectionProvider() {
		return tree.getSelectionProvider();
	}
	
	/**
	 * 设置当前操作实体
	 * @param currentData
	 */
	public void setCurrentData(IBaseVo currentData) {
		EpiInput input = (EpiInput) tree.getViewer().getInput();
		input.setCurrentData(currentData);
	}

	/**
	 * 获取当前tree对象
	 * @return tree
	 */
	public EpiTree getTree() {
		return tree;
	}



	
	
	public class SysCmpEditorAction extends EpiEditorAction{
		public SysCmpEditorAction(EpiTree tree) {
			super(tree);
		}
		
		@Override
		protected IBaseVo doEditorNode(IBaseVo parentData) {
			//做系统组件编辑处理
			
			
			
			
			return parentData;
		}
	}

	/**
	 * 部署模型-系统、子系统新增(子节点)动作
	 * @author WANGQINGPING
	 */
	public class SysCmpAddChildAction extends EpiAddChildAction {
		
		public SysCmpAddChildAction(EpiTree tree) {
			super(tree);
		}
		
		@Override
		protected IBaseVo doAddChildNode(IBaseVo parentData) {
			if(parentData == null) {
				MessageDialog.openInformation(getControl().getShell(), "提示", "当前未选择父级节点,请选择父级节点后再添加子节点");
				return null;
			}
			if("SYS_CMP".equals(((SysCmpVo)parentData).getEtype())) {
				MessageDialog.openInformation(getControl().getShell(), "提示", "当前选择为叶子节点,请选择父级节点后再添加子节点");
				return null;
			}
			
			SysCmpVo scv = (SysCmpVo)parentData;			
			SysCmpVo sysCmpVo = new SysCmpVo();
			sysCmpVo.setRid(MakeId.newId());
			sysCmpVo.setTid(currentSelectProduct.getTid());
			sysCmpVo.setCode(scv.getCode()+"_Copy");
			sysCmpVo.setName(scv.getName()+"_Copy");
			if("SYSTEM".equals(scv.getEtype())) {
				sysCmpVo.setEtype("SUB_SYS");
			}else if("SUB_SYS".equals(scv.getEtype())) {
				sysCmpVo.setEtype("SYS_CMP");
			}
			sysCmpVo.setParentNode(scv);
			scv.getChildrenList().add(sysCmpVo);
			
			return sysCmpVo;
		}
	}
	
	/**
	 * 部署模型-系统、子系统、组件新增(兄弟节点)动作
	 * @author WANGQINGPING
	 */
	public class SysCmpAddBrotherAction extends EpiAddBrotherAction {
		
		public SysCmpAddBrotherAction(EpiTree tree) {
			super(tree);
		}
		
		@Override
		protected IBaseVo doAddBrotherRow(IBaseVo currData) {
			//如果currData为空则表示没有选中添加节点位置，则直接添加在跟节点上
			//如果currData非空若表示有选中添加节点位置，则添加在同级节点上
			
			SysCmpVo sysCmpVo = new SysCmpVo();
			if(currData instanceof SysCmpVo) {//有选中添加节点位置处理
				SysCmpVo currModData = (SysCmpVo)currData;
				SysCmpVo parentData = (SysCmpVo) currModData.getParentNode();
//				if("SYS_CMP".equals(currModData.getEtype())) {
//					MessageDialog.openInformation(getControl().getShell(), "提示", "当前选择为叶子节点,请选择父级节点后再添加子节点");
//					return null;
//				}
				
				sysCmpVo.setRid(MakeId.newId());
				sysCmpVo.setTid(currentSelectProduct.getTid());
				sysCmpVo.setName(currModData.getName()+"_New_Copy");
				sysCmpVo.setCode(currModData.getCode()+"_New_Copy");
				sysCmpVo.setPrdRid(currentSelectProduct.getRid());
				if("SYSTEM".equals(currModData.getEtype())) {
					sysCmpVo.setEtype("SYSTEM");
				}else if("SUB_SYS".equals(currModData.getEtype())) {
					sysCmpVo.setEtype("SUB_SYS");
				}else if("SYS_CMP".equals(currModData.getEtype())) {
					sysCmpVo.setEtype("SYS_CMP");
				}
				
				//是否存在父级节点
				if(parentData != null) {
					sysCmpVo.setParentNode(parentData);
					parentData.getChildrenList().add(sysCmpVo);
				}else {
					IEpiInput input = (IEpiInput) tree.getViewer().getInput();
					List<IBaseVo> dataList = input.getDataList();
					dataList.add(sysCmpVo);
				}
			}else {//没有选中添加节点位置处理
				sysCmpVo.setRid(MakeId.newId());
				sysCmpVo.setTid(currentSelectProduct.getTid());
				sysCmpVo.setName("New_Copy");
				sysCmpVo.setCode("New_Copy");
				sysCmpVo.setPrdRid(currentSelectProduct.getRid());
				sysCmpVo.setEtype("SYSTEM");
				IEpiInput input = (IEpiInput) tree.getViewer().getInput();
				List<IBaseVo> dataList = input.getDataList();
				dataList.add(sysCmpVo);
			}
			
			tree.getViewer().refresh();
//			tree.putInsertedData(sysCmpVo);
			return sysCmpVo;
		}
	}
	
	/**
	 * 部署模型-系统、子系统、组件刷新动作
	 * @author WANGQINGPING
	 */
	public class SysCmpRefreshAction extends EpiRefreshAction {
		public SysCmpRefreshAction(EpiTree tree) {
			super(tree);
		}
		
		@Override
		protected IEpiInput doRefresh(IEpiInput adapter) {
			IEpiEditorInput editInput = (IEpiEditorInput)getEditorInput();
			IBaseVo product = editInput.getCurrentData();
			IEpiInput input = doper.loadSysCmpList(product);
			return input;
		}
	}
	
	/**
	 * 部署模型-系统、子系统、组件过滤动作
	 * @author WANGQINGPING
	 */
	public class SysCmpFilterAction extends EpiFilterAction {
		public SysCmpFilterAction(EpiTree tree) {
			super(tree);
		}
		
		@Override
		protected void doFilterAction() {
			//过滤处理
			SysDpyCmpFilterDialog sdcfd = new SysDpyCmpFilterDialog(getControl().getShell());
			sdcfd.open();
		}
	}
	
	/**
	 * 部署模型-系统、子系统、组件升级动作
	 * @author WANGQINGPING
	 */
	public class SysCmpUpgradeAction extends EpiUpgradeAction{
		public SysCmpUpgradeAction(EpiTree tree) {
			super(tree);
		}
		
		@Override
		protected void doUpgradeAction(IBaseVo[] baseVos) {
			for (IBaseVo currData : baseVos) {
				SysCmpVo currNode = (SysCmpVo)currData;
				SysCmpVo parentNode = (SysCmpVo) currNode.getParentNode();
				if(parentNode == null) continue;
				
				SysCmpVo dest = new SysCmpVo();
				BeanUtil.copyBeanToBean(dest, currNode);
				dest.setEtype(parentNode.getEtype());
				if(currData instanceof SysCmpVo && parentNode.getParentNode() != null) {//当前操作数据为第三级
					dest.setParentNode(parentNode.getParentNode());
					parentNode.getParentNode().getChildrenList().add(dest);
					parentNode.getChildrenList().remove(currNode);
				}else if(currData instanceof SysCmpVo && parentNode.getParentNode() == null) {//当前操作数据为第二级
					dest.setParentNode(null);
					List<IBaseVo> childrenList = dest.getChildrenList();
					for (IBaseVo iBaseVo : childrenList) { 
						iBaseVo.setEtype(currData.getEtype()); 
					}
					IEpiInput input = (IEpiInput) tree.getViewer().getInput();
					List<IBaseVo> dataList = input.getDataList();
					dataList.add(dest);
					currNode.getParentNode().getChildrenList().remove(currData);
				}
			}
		}
	}
	
	/**
	 * 部署模型-系统、子系统、组件降级动作
	 * @author WANGQINGPING
	 */
	public class SysCmpDegradeAction extends EpiDegradeAction{
		public SysCmpDegradeAction(EpiTree tree) {
			super(tree);
		}
		
		@Override
		protected IBaseVo doDegradeAction(IBaseVo currData) {
			IBaseVo newData = currData;

			if (currData instanceof ITreeNode) {
				ITreeNode parentData = ((ITreeNode) currData).getParentNode();
				if (parentData != null) {
					List<IBaseVo> childrenList = parentData.getChildrenList();
					int index = 0;
					for (index = 0; index < childrenList.size(); index++) {
						if (currData.equals(childrenList.get(index))) {
							break;
						}
					}
					if (index > 0) {
						IBaseVo newParentData = childrenList.get(index - 1);
						if(newParentData instanceof ITreeNode) {
							childrenList.remove(currData);
							
							ITreeNode newParent = (ITreeNode)newParentData;
							((ITreeNode) currData).setParentNode(newParent);
							newParent.getChildrenList().add(currData);
						}
					}
				} else {
					newData = null;
				}
			} else {
				newData = null;
			}
			return newData;
		}
	}
	
	
	/**
	 * 部署模型-系统、子系统、组件删除动作
	 * @author WANGQINGPING
	 */
	public class SysCmpDeleteAction extends EpiDeleteNodeAction{
		public SysCmpDeleteAction(EpiTree tree) {
			super(tree);
		}
		
		@Override
		protected int doDdeleteNode(IBaseVo[] currDatas) {
			if(currDatas == null) return 0;
			for (IBaseVo currData : currDatas) {
				SysCmpVo currNode = (SysCmpVo)currData;
				SysCmpVo parentNode = (SysCmpVo) currNode.getParentNode();
				if(parentNode != null) {
					parentNode.getChildrenList().remove(currNode);
				}else {
					((IEpiInput)tree.getViewer().getInput()).getDataList().remove(currNode);
				}
			}
			tree.getViewer().refresh();
			
			return currDatas.length;
		}
	}
	
	
	/**
	 * 系统、子系统、组件过滤弹框
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyCmpFilterDialog extends Dialog{
		private Composite composite;
		private Text textType;//类型
		private Text textName;//关联资源名称
		private Text textCode;//代码
		private Text textDesc;//描述
		
		protected SysDpyCmpFilterDialog(Shell parentShell) {
			super(parentShell);
		}
		
		@Override
		protected Point getInitialSize() {
			return new Point(600, 400);
		}
		
		private void doFilterSysCmp() {
			SysCmpVo scv = new SysCmpVo();
			String type = textType.getText().trim();
			String name = textName.getText().trim();
			String code = textCode.getText().trim();
			String desc = textDesc.getText().trim();
			scv.setType(type);
			scv.setName(name);
			scv.setCode(code);
			scv.setDescription(desc);
			scv.setPrdRid(currentSelectProduct.getRid());
			
			EpiInput input = doper.loadSysCmpFilterList(scv);
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
					doFilterSysCmp();
				}
			});
			return composite;
		}
		
		@Override
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText("过滤系统、子系统、组件数据");
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
	 * tree拖动响应监听
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpyCmpDragSource extends BaseDragSource{
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
