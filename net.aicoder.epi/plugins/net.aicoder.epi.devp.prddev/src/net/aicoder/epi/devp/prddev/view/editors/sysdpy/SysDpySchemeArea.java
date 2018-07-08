package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.action.IEpiAction;
import net.aicoder.epi.base.view.action.table.EpiAddAction;
import net.aicoder.epi.base.view.action.table.EpiDeleteAction;
import net.aicoder.epi.base.view.context.EpiInput;
import net.aicoder.epi.base.view.context.IEpiEditorInput;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.context.ViewContext;
import net.aicoder.epi.base.view.definer.IColumnDefiner;
import net.aicoder.epi.base.view.element.area.BaseArea;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;
import net.aicoder.epi.base.view.element.table.EpiSelectionProvider;
import net.aicoder.epi.base.view.element.table.EpiTable;
import net.aicoder.epi.base.view.element.table.EpiTableDefiner;
import net.aicoder.epi.devp.prddev.PrddevImageConstant;
import net.aicoder.epi.devp.prddev.doper.ops.SysDpySchemaDoper;
import net.aicoder.epi.devp.prddev.model.ops.SysDpySchemaVo;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;

/**
 * 部署模型-部署方案(视图)
 * @author WANGQINGPING
 *
 */
public class SysDpySchemeArea extends BaseArea{
	public static final String ID = SysDpySchemeArea.class.getName();
	private SysDpySchemaDoper sysDpySchemaDoper;
	private PrdProductVo currentSelectProduct;//当前选中的产品
	private Combo combo;
	private List<IBaseVo> currentSysDpySchemas;//当前部署方案集合
	
	
	public SysDpySchemeArea() {
		super();
		this.sysDpySchemaDoper = new SysDpySchemaDoper();
	}
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		//加载该产品部署方案
		IEpiEditorInput editorInput = (IEpiEditorInput)this.getEditorInput();
		IBaseVo currentData = editorInput.getCurrentData();
		if(currentData instanceof PrdProductVo){
			currentSelectProduct = (PrdProductVo)currentData;
		}
		
		//选择“部署方案”，也可点选“+”增加或删除部署方案；(部署方案为一整套开发、测试或生产的配套部署)
		//依据所选择的部署方案，显示对应的资源实例以及以下“XXX组件”所对应的实例关联设置
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
		parent.setLayout(fillLayout);
		parent.setBounds(0, 0, 40, 12);

		
		//文本
		Label label = new Label(parent, SWT.LEFT|SWT.CENTER);
		label.setText("部署方案");
		label.setBounds(0, 0, 15, 8);
		
		//下拉选择
		currentSysDpySchemas = sysDpySchemaDoper.loadSysDpySchemaList(currentSelectProduct);	
		combo = new Combo(parent, SWT.LEFT|SWT.CENTER);
		for(int i=0;i<currentSysDpySchemas.size();i++) {
			String name = currentSysDpySchemas.get(i).getName();
			combo.add(name, i);
		}
		
//		combo.addSelectionListener(new SelectionListener() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				IBaseVo baseVo = null;
//				String source = e.getSource().toString();
//				String name = source.substring(source.indexOf("{")+1, source.indexOf("}"));
//				for (IBaseVo iBaseVo : currentSysDpySchemas) {
//					if(iBaseVo.getName().equals(name)) {
//						baseVo = iBaseVo;
//						break;
//					}
//				}
//				if(baseVo == null) return;
//				
//				
//				System.out.println("widgetSelected:"+name);
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				System.out.println("widgetDefaultSelected:"+e.getSource().toString());
//			}
//		});
		
		//添加按钮
		Button addBtn = new Button(parent, SWT.CENTER);
		addBtn.setBounds(0, 0, 10, 10);
		addBtn.setText("维护");
		addBtn.setBackgroundImage(PrddevImageConstant.getImage(PrddevImageConstant.DPY_DEFEND));			
		addBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SysDpySchemaDefendDialog sdscd = new SysDpySchemaDefendDialog(getControl().getShell());
				sdscd.open();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println("[添加部署方案]widgetDefaultSelected");
			}
		});
		
		return parent;
	}
	
	/**
	 * 获取下拉选框对象
	 * @return
	 */
	public Combo getSelectionCombo() {
		return combo;
	}
	
	/**
	 * 获取下拉选框对象数据
	 * @return
	 */
	public List<IBaseVo> getSelectionComboData() {
		return currentSysDpySchemas;
	}
	
	public EpiSelectionProvider getSelectionProvider() {
		return null;
	}
	
	public void setSelection(ISelection selection) {
		//刷新部署方案区域数据
		
		
		
	}
	
	/**
	 * 内部刷新部署方案数据
	 */
	public void innerRefresh() {
		//内部刷新部署方案区域数据
		combo.removeAll();
//		String[] items = new String[currentSysDpySchemas.size()];
		for(int i=0;i<currentSysDpySchemas.size();i++) {
			String name = currentSysDpySchemas.get(i).getName();
			combo.add(name, i);
		}
		combo.update();
	}
	
	
	/**
	 * 部署方案维护弹框
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpySchemaDefendDialog extends Dialog{
		private SysDpySchemaDefendTable sysDpySchemaDefendTable;
		
		protected SysDpySchemaDefendDialog(Shell parentShell) {
			super(parentShell);
		}
		
		@Override
		protected Point getInitialSize() {
			return new Point(600, 400);
		}
		
		@Override
		protected Control createDialogArea(Composite parent) {
			int[] areaWeights = new int[1];
			areaWeights[0] = 1;
			sysDpySchemaDefendTable = new SysDpySchemaDefendTable();
			IEpiArea[] epiAreas = new IEpiArea[1];
			epiAreas[0] = sysDpySchemaDefendTable;
			EpiSashArea sashArea = new EpiSashArea(getWorkbenchPart(), SWT.HORIZONTAL|SWT.H_SCROLL);
			sashArea.setEpiAreas(epiAreas);
			sashArea.setAreaWeights(areaWeights);
			sashArea.createControl(parent);
			return sashArea.getControl();
		}
		
		@Override
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText("维护部署方案数据");
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
		
		@Override
		public boolean close() {
			//移除掉空数据,此处按没填写name值则移除处理
			List<IBaseVo> temp = new ArrayList<>();
			for (IBaseVo iBaseVo : currentSysDpySchemas) {
				if(StringUtils.isEmpty(iBaseVo.getName())) {
					temp.add(iBaseVo);
				}
			}
			for (IBaseVo iBaseVo : temp) {
				currentSysDpySchemas.remove(iBaseVo);
			}
			innerRefresh();
			return super.close();
		}
		
	}
	
	
	/**
	 * 部署方案维护列表
	 * @author WANGQINGPING
	 *
	 */
	public class SysDpySchemaDefendTable extends BaseWithTitleArea{
		public final String ID = SysDpySchemaDefendTable.class.getName();
		private EpiTable table;
		private EpiTableDefiner definer;
		private IViewContext context;
		
		// 0-列名, 1-数据属性名称, 2-列显示的宽度, 3-数据类型, 4-数据格式, 5-是否隐藏的标志, 6-是否可编辑的标志
		private Object[][] columnsDefine = {
			{"类型", "type", -5, null, null, null, IColumnDefiner.EDITABLE},
			{"系统元素名称", "name", 13, null, null, null, IColumnDefiner.EDITABLE },
			{"系统元素代码", "code", 13, null, null, null, IColumnDefiner.EDITABLE },
			{"系统元素别名", "alias", 13, null, null, null, IColumnDefiner.EDITABLE },
			{"系统元素描述", "description", 13, null, null, null, IColumnDefiner.EDITABLE }
		};
		
		
		public SysDpySchemaDefendTable() {
			super("部署方案", null);
		}
		
		@Override
		public void setToolBar(IToolBarManager toolBarManager) {
			IEpiAction[] epiAction = new IEpiAction[2];
			epiAction[0] = new SysDpySchemaAddAction(table);
			epiAction[1] = new SysDpySchemaDeleteAction(table);
			toolBarManager.add(epiAction[0]);
			toolBarManager.add(epiAction[1]);
			toolBarManager.update(true);
		}
		
		@Override
		protected Control createAreaControl(Composite parent) {
//			List<IBaseVo> loadSysDpySchemaList = sysDpySchemaDoper.loadSysDpySchemaList(currentProduct);
			EpiInput input = new EpiInput();
			input.setDataList(currentSysDpySchemas);
			
			definer = new EpiTableDefiner(null, columnsDefine);
			context = new ViewContext();
			context.setInput(input);
			table = new EpiTable(parent, definer, context);
			return table;
		}
		
		/**
		 * 部署方案新增动作
		 * @author WANGQINGPING
		 */
		public class SysDpySchemaAddAction extends EpiAddAction{
			public SysDpySchemaAddAction(EpiTable epiTable) {
				super(epiTable);
			}
			
			@Override
			protected void doAddAction() {
				//新增列表一行
				SysDpySchemaVo sdsv = new SysDpySchemaVo();
				IEpiInput input = context.getInput();
				input.getDataList().add(sdsv);
				table.getViewer().refresh();
				table.putInsertedData(sdsv);
			}
			
		}
		
		/**
		 * 部署方案删除动作
		 * @author WANGQINGPING
		 */
		public class SysDpySchemaDeleteAction extends EpiDeleteAction{
			public SysDpySchemaDeleteAction(EpiTable epiTable) {
				super(epiTable);
			}
			
			@Override
			protected void doDeleteAction(IBaseVo baseVo) {
				//删除一行
				if(baseVo == null || !(baseVo instanceof SysDpySchemaVo)) return;
				SysDpySchemaVo sysDpySchemaVo = (SysDpySchemaVo) baseVo;
				IEpiInput input = context.getInput();
				input.getDataList().remove(sysDpySchemaVo);
				table.getViewer().refresh();
				table.putDeletedData(baseVo);
				currentSysDpySchemas.remove(sysDpySchemaVo);

				innerRefresh();
			}
		}
		
	}

}
