package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import net.aicoder.epi.base.view.element.area.BaseArea;

/**
 * 部署模型-子区域-右边区域-上边区域
 * @author WANGQINGPING
 *
 */
public class SysDpySubRightUpArea extends BaseArea{
	public static final String ID = SysDpySubRightUpArea.class.getName();
	

	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}

	@Override
	protected Control createAreaControl(Composite parent) {
		Text text = new Text(parent, SWT.NONE);
		text.setText("部署方案");
		return parent;
	}

}
