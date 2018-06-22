package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import net.aicoder.epi.base.view.element.area.BaseArea;
import net.aicoder.epi.base.view.element.table.EpiSelectionProvider;

/**
 * 部署模型-部署方案(视图)
 * @author WANGQINGPING
 *
 */
public class SysDpySchemeArea extends BaseArea{
	public static final String ID = SysDpySchemeArea.class.getName();
	

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	@Override
	protected Control createAreaControl(Composite parent) {
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
		Combo combo = new Combo(parent, SWT.LEFT|SWT.CENTER);
		String[] items = new String[] {"仪器解析部署方案","好医生部署方案","记事本部署方案"};
		combo.setItems(items);
		combo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("widgetSelected:"+e.getSource().toString());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("widgetDefaultSelected:"+e.getSource().toString());
			}
		});
		
		
		//添加按钮
		Button addBtn = new Button(parent, SWT.CENTER);
		addBtn.setText("+");
		addBtn.setBounds(0, 0, 10, 10);
		addBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("添加新的部署方案");
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		return parent;
	}
	
	
	
	public EpiSelectionProvider getSelectionProvider() {
		return null;
	}
	
	public void setSelection(ISelection selection) {
		//刷新部署方案区域数据
		
		
		
	}

}
