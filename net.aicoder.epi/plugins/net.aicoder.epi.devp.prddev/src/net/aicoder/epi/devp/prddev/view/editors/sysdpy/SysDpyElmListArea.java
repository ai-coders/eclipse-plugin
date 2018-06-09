package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISharedImages;

import net.aicoder.devp.model.EtypeEnum;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.context.IEpiInput;
import net.aicoder.epi.base.view.context.IViewContext;
import net.aicoder.epi.base.view.element.area.BaseArea;
import net.aicoder.epi.base.view.element.area.BaseWithPropArea;
import net.aicoder.epi.base.view.element.area.BaseWithTitleArea;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.EpiTabArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;
import net.aicoder.epi.base.view.element.tree.EpiTree;
import net.aicoder.epi.base.view.element.tree.EpiTreeDefiner;
import net.aicoder.epi.base.view.property.PropsArea;
import net.aicoder.epi.devp.DevpConstant;
import net.aicoder.epi.devp.prddev.PrddevImageConstant;
import net.aicoder.epi.devp.prddev.doper.ProductDevDoper;
import net.aicoder.epi.devp.prddev.model.product.PrdProductVo;
import net.aicoder.epi.devp.prddev.view.editors.function.ModuleEditor;
import net.aicoder.epi.devp.prddev.view.editors.product.ProductEditor;
import net.aicoder.epi.devp.prddev.view.editors.syscmp.SysCmpEditor;
import net.aicoder.epi.devp.prddev.view.editors.sysdpy.SysDpyEditor;
import net.aicoder.epi.devp.prddev.view.editors.syside.SysIdeEditor;
import net.aicoder.epi.example.area.TableEditorArea;
import net.aicoder.epi.example.area.TableEditorWithTitleArea;
import net.aicoder.epi.util.ImageUtil;

public class SysDpyElmListArea extends BaseWithPropArea {
	public static String ID = SysDpyElmListArea.class.getName();

	private IEpiArea workArea;
	//private PropsArea propsArea;
	
	@Override
	protected IEpiArea createWorkArea() {
		workArea = new SysDpyElmTreeTable();

/**		
	    // add listeners
		workArea.getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener() {
	      public void selectionChanged(SelectionChangedEvent event) {
	        ISelection selection = event.getSelection();
	        propsArea.setSelection(selection);
	      }
	    });
**/
		
		return workArea;
	}
	
	@Override
	public void setToolBar(IToolBarManager toolBarManager) {
		
	}
}
