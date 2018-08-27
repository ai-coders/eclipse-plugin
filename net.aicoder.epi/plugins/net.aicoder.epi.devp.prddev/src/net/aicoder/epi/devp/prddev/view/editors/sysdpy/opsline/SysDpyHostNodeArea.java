package net.aicoder.epi.devp.prddev.view.editors.sysdpy.opsline;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPart;

import net.aicoder.epi.base.view.part.area.IArea;
import net.aicoder.epi.base.view.part.area.SashArea;
import net.aicoder.epi.devp.prddev.view.property.PrddevWithPropArea;

/**
 * 运维流水线->环境准备->主机节点区域
 * @author WANGQINGPING
 *
 */
public class SysDpyHostNodeArea extends PrddevWithPropArea{
	private SysDpyHostNodeTable sysDpyHostNodeTable;
	
	public SysDpyHostNodeArea(IWorkbenchPart workbenchPart) {
		super(workbenchPart);
		setTitleText("主机节点");
	}

	@Override
	protected IArea newWorkArea() {
		int[] weights = new int[1];
		weights[0] = 1;

		sysDpyHostNodeTable = new SysDpyHostNodeTable();
		IArea[] areas = new IArea[1];
		areas[0] = sysDpyHostNodeTable;

		SashArea sashArea = new SashArea(getWorkbenchPart());
		sashArea.setAreas(areas);
		sashArea.setWeights(weights);
		sashArea.setFixedOrientation(SWT.VERTICAL);

		return sashArea;
	}
	
	@Override
	public void attachEvent() {
		sysDpyHostNodeTable.getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				setElementSelection(selection);//进入属性区域处理
				sysDpyHostNodeTable.bindSelectionDataEvent(selection);//进入属性区域逆向处理
			}
		});
	}
	
	public SysDpyHostNodeTable getSysDpyHostNodeTable() {
		return sysDpyHostNodeTable;
	}

}
