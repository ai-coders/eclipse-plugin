package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.action.tree.EpiAddBrotherAction;
import net.aicoder.epi.base.view.action.tree.EpiAddChildAction;
import net.aicoder.epi.base.view.action.tree.EpiDegradeAction;
import net.aicoder.epi.base.view.action.tree.EpiDeleteNodeAction;
import net.aicoder.epi.base.view.action.tree.EpiDownRowAction;
import net.aicoder.epi.base.view.action.tree.EpiFilterAction;
import net.aicoder.epi.base.view.action.tree.EpiRefreshAction;
import net.aicoder.epi.base.view.action.tree.EpiUpRowAction;
import net.aicoder.epi.base.view.action.tree.EpiUpgradeAction;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;
import net.aicoder.epi.base.view.element.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.element.tree.EpiTree;
import net.aicoder.epi.base.view.element.tree.EpiTreeDefiner;
import net.aicoder.epi.devp.prddev.doper.ops.SysCmpDoper;
import net.aicoder.epi.devp.prddev.model.dev.ProductDevVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElmCatgVo;
import net.aicoder.epi.devp.prddev.model.ops.SysCmpVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.tcom.tools.util.BeanUtil;

/**
 * 部署模型-组件(树表)
 * @author WANGQINGPING
 *
 */
public class SysDpyCmpTreeTable extends BaseWithTitleArea{
	public static String ID = SysDpyCmpTreeTable.class.getName();
	private EpiTree tree;
	private EpiTreeDefiner definer;
	private IViewContext context;
	private SysCmpDoper doper;
	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
		{"名称*", "name", 20, null, null, null, IColumnDefiner.EDITABLE },
		{"代码", "code", -20, null, null, null, IColumnDefiner.EDITABLE },
		{"别名", "alias", },
		{"类型", "type", },
		{"版本", "version", },
		{"描述", "description", 0, null, null, null, IColumnDefiner.EDITABLE }
	};
	
	
	//构造
	public SysDpyCmpTreeTable() {
		super();
		doper = new SysCmpDoper();
	}

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		IEpiAction[] epiAction = new IEpiAction[9];
		epiAction[0] = new SysCmpAddChildAction(tree);//新增（子）
		epiAction[1] = new SysCmpAddBrotherAction(tree);//新增
		epiAction[2] = new EpiUpRowAction(tree);//上移
		epiAction[3] = new EpiDownRowAction(tree);//下移
		epiAction[4] = new SysCmpUpgradeAction(tree);//升级
		epiAction[5] = new SysCmpDegradeAction(tree);//降级
		epiAction[6] = new SysCmpDeleteAction(tree);//删除
		epiAction[7] = new SysCmpFilterAction(tree);//过滤
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
		toolBarManager.update(false);
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		IEpiEditorInput editorInput = (IEpiEditorInput)this.getEditorInput();
		IBaseVo currentData = editorInput.getCurrentData();
		PrdProductVo product = null;
		if(currentData instanceof SysElmCatgVo) {
			ProductDevVo productDev = (ProductDevVo) ((SysElmCatgVo)currentData).getParentNode();
			product = productDev.getProduct();
		}else if(currentData instanceof PrdProductVo){
			product = (PrdProductVo)currentData;
		}
		
		
		//点选XXX产品时，获取当前产品的系统、子系统、组件；
		//可新增/删除系统、子系统、组件等，及维护系统、子系统、组件结构
		IEpiInput input = doper.loadSysCmpList(product);
		
		definer = new EpiTreeDefiner(null, columnsDefine);
		context = new ViewContext();
		context.setInput(input);
		tree = new EpiTree(parent, definer, context);

		return tree;
	}
	
	public EpiSelectionProvider getSelectionProvider() {
		return tree.getSelectionProvider();
	}
	
	public void setSelection(ISelection selection) {
		//刷新树表数据

		
		
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
			if(parentData == null) return null;
			SysCmpVo sysCmpVo = null;
			SysCmpVo scv = (SysCmpVo)parentData;
			if("SYS_CMP".equals(scv.getEtype())) return sysCmpVo;
			
			sysCmpVo = new SysCmpVo();
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
			IBaseVo brotherData = null;
			if(currData == null) return brotherData;
			if(currData instanceof SysCmpVo) {
				SysCmpVo currModData = (SysCmpVo)currData;
				SysCmpVo parentData = (SysCmpVo) currModData.getParentNode();
				
				
				SysCmpVo sysCmpVo = new SysCmpVo();
				sysCmpVo.setCode(currModData.getCode()+"_Copy");
				sysCmpVo.setName(currModData.getName()+"_Copy");
				sysCmpVo.setPrdRid(currModData.getPrdRid());
				if("SYSTEM".equals(currModData.getEtype())) {
					sysCmpVo.setEtype("SYSTEM");
				}else if("SUB_SYS".equals(currModData.getEtype())) {
					sysCmpVo.setEtype("SUB_SYS");
				}else if("SYS_CMP".equals(currModData.getEtype())) {
					sysCmpVo.setEtype("SYS_CMP");
				}

				if(parentData != null) {
					sysCmpVo.setParentNode(parentData);
					parentData.getChildrenList().add(sysCmpVo);
				}else {
					IEpiInput input = context.getInput();
					List<IBaseVo> dataList = input.getDataList();
					dataList.add(sysCmpVo);
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
				brotherData = sysCmpVo;
			}
			
			return brotherData;
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
				if(currData instanceof SysCmpVo && parentNode.getParentNode() != null) {//第三级
					dest.setParentNode(parentNode.getParentNode());
					parentNode.getParentNode().getChildrenList().add(dest);
					parentNode.getChildrenList().remove(currNode);
				}else if(currData instanceof SysCmpVo && parentNode.getParentNode() == null) {//第二级
					dest.setParentNode(null);
					IEpiInput input = tree.getViewContext().getInput();
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
		protected void doDegradeAction(IBaseVo currData) {
//			SysCmpVo currNode = (SysCmpVo)currData;
//			SysCmpVo parentNode = (SysCmpVo) currNode.getParentNode();

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
					context.getInput().getDataList().remove(currNode);
				}
			}
			
			return currDatas.length;
		}
	}

}
