package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;
import net.aicoder.epi.base.view.element.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.element.tree.EpiTree;
import net.aicoder.epi.base.view.element.tree.EpiTreeDefiner;
import net.aicoder.epi.devp.prddev.doper.dev.system.SysElementDoper;
import net.aicoder.epi.devp.prddev.model.dev.ProductDevVo;
import net.aicoder.epi.devp.prddev.model.dev.system.SysElmCatgVo;

public class SysDpyElmTreeTable extends BaseWithTitleArea{
	public static String ID = SysDpyElmTreeTable.class.getName();

	// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
	private static Object[][] columnsDefine = {
			{"Code", "code", -20, null, null, null, IColumnDefiner.EDITABLE },
			{"Name", "name", 20, null, null, null, IColumnDefiner.EDITABLE },
			{"Alias", "alias", },
			{"Description", "description", 0, null, null, null, IColumnDefiner.EDITABLE },
			{"Type", "type", },
			{"Stereotype", "stereotype", 0, null, null, IColumnDefiner.HIDDEN},
			{"Version", "version", },
	};
	
	private EpiTree tree;
	private EpiTreeDefiner definer;
	private IViewContext context;
	private SysElementDoper doper;
	
	public SysDpyElmTreeTable() {
		super();
		doper = new SysElementDoper();
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		definer = new EpiTreeDefiner(null, columnsDefine);
		IEpiEditorInput editorInput = (IEpiEditorInput)this.getEditorInput();
		IBaseVo elmCatg = editorInput.getCurrentData();
		IBaseVo product = ((SysElmCatgVo)elmCatg).getParentNode();
		List<IBaseVo> dataList = doper.listSysDpyElement((ProductDevVo)product);
		IEpiInput input = new EpiInput();
		input.setDataList(dataList);
		context = new ViewContext();
		context.setInput(input);
		tree = new EpiTree(parent, definer, context);
		return tree;
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
	}
	
	public void setViewContext(IViewContext context) {
		this.context = context;
	}

	public EpiSelectionProvider getSelectionProvider() {
		return tree.getSelectionProvider();
	}
}
