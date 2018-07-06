package net.aicoder.epi.devp.product.view.editors;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.editor.FormEditor;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.action.tree.EpiAddBrotherAction;
import net.aicoder.epi.base.view.action.tree.EpiAddChildAction;
import net.aicoder.epi.base.view.action.tree.EpiDeleteNodeAction;
import net.aicoder.epi.base.view.action.tree.EpiDownRowAction;
import net.aicoder.epi.base.view.action.tree.EpiLeftNodeAction;
import net.aicoder.epi.base.view.action.tree.EpiRefreshAction;
import net.aicoder.epi.base.view.action.tree.EpiRightNodeAction;
import net.aicoder.epi.base.view.action.tree.EpiUpRowAction;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.control.tree.EpiTree;
import net.aicoder.epi.base.view.control.tree.EpiTreeDefiner;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.base.view.part.editor.BaseFormPage;
import net.aicoder.epi.devp.product.doper.ModuleDoper;
import net.aicoder.epi.devp.product.model.product.PrdModuleVo;

public class PrdFunctionTreePage extends BaseFormPage {
	public static String ID = PrdFunctionTreePage.class.getName();

	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
			{"Code", "code", -20, null, null, null, IColumnDefiner.EDITABLE },
			{"Name", "name", 20, null, null, null, IColumnDefiner.EDITABLE },
			{"Alias", "alias", },
			{"Description", "description", 0, null, null, null, IColumnDefiner.EDITABLE },
			{"Type", "type", },
			{"Stereotype", "stereotype", 0, null, null, IColumnDefiner.HIDDEN},
			{"Version", "version", },
			{"Phase", "phase", },
			{"Status", "status", },
	};
	
	private FormEditor editor;
	private EpiTree tree;
	private EpiTreeDefiner definer;
	
	private ModuleDoper dataoper;

	public PrdFunctionTreePage(FormEditor editor) {
		super(editor, ID, "功能树");
		this.editor = editor;
		dataoper = new ModuleDoper();
	}
	
	@Override
	public IArea newPageArea() {
		return null;
	}
	
	@Override
	public void createControl(Composite parent) {
		definer = new EpiTreeDefiner(null, columnsDefine);
		IEpiEditorInput editInput = (IEpiEditorInput)editor.getEditorInput();
		IBaseVo product = editInput.getCurrentData();
		IEpiInput input = dataoper.loadModuleList(product);
		IViewContext context = new ViewContext();
		context.setInput(input);
		//definer.setInput(input);
		tree = new EpiTree(parent, definer, context);
	}

	@Override
	protected IEpiAction[] makeToolBarAction() {
		IEpiAction[] toolBarAactons = new IEpiAction[8];
		toolBarAactons[0] = new ModuleRefreshAction(tree);
		toolBarAactons[1] = new ModuleAddChildAction(tree);
		toolBarAactons[2] = new ModuleAddBrotherAction(tree);
		toolBarAactons[3] = new EpiUpRowAction(tree);
		toolBarAactons[4] = new EpiDownRowAction(tree);
		toolBarAactons[5] = new EpiLeftNodeAction(tree);
		toolBarAactons[6] = new EpiRightNodeAction(tree);
		toolBarAactons[7] = new EpiDeleteNodeAction(tree);
		return toolBarAactons;
	}
	
	
	class ModuleRefreshAction extends EpiRefreshAction {
		public ModuleRefreshAction(EpiTree tree) {
			super(tree);
		}
		
		@Override
		protected IEpiInput doRefresh(IEpiInput adapter) {
			IEpiEditorInput editInput = (IEpiEditorInput)editor.getEditorInput();
			IBaseVo product = editInput.getCurrentData();
			IEpiInput input = dataoper.loadModuleList(product);
			return input;
		}

	}
	
	class ModuleAddChildAction extends EpiAddChildAction {
		public ModuleAddChildAction(EpiTree tree) {
			super(tree);
		}

		@Override
		protected IBaseVo doAddChildNode(IBaseVo parentData) {
			IBaseVo childData = null;

			PrdModuleVo module11 = new PrdModuleVo();
			module11.setRid(110000);
			module11.setCode("MAC_C110000");
			module11.setName("MAC_N110000");

			if (parentData != null && parentData instanceof PrdModuleVo) {
				module11.setParentModule((PrdModuleVo) parentData);
				((PrdModuleVo) parentData).getChildrenModuleList().add(module11);
			}
			childData = module11;

			return childData;
		}
	}

	class ModuleAddBrotherAction extends EpiAddBrotherAction {
		public ModuleAddBrotherAction(EpiTree tree) {
			super(tree);
		}

		@Override
		protected IBaseVo doAddBrotherRow(IBaseVo currData) {
			IBaseVo childData = null;
			if (currData == null) {
				return childData;
			}
			if (currData instanceof PrdModuleVo) {
				PrdModuleVo currModData = (PrdModuleVo) currData;
				PrdModuleVo parentData = currModData.getParentModule();

				PrdModuleVo module11 = new PrdModuleVo();
				module11.setRid(110000);
				module11.setCode("MAB_C110000");
				module11.setName("MAB_N110000");

				if (parentData != null) {
					module11.setParentModule((PrdModuleVo) parentData);
					List<IBaseVo> childrenList = parentData.getChildrenModuleList();
					int index=0;
					for(index=0; index<childrenList.size();index++) {
						if(currData.equals(childrenList.get(index))) {
							break;
						}
					}
					childrenList.add(index + 1, module11);
				}
				childData = module11;
			}

			return childData;
		}
	}

}
