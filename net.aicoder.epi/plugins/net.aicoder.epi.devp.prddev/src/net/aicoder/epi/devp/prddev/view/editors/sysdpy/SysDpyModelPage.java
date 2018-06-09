package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.editor.FormEditor;

import net.aicoder.epi.base.view.editor.BaseFormPage;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;

public class SysDpyModelPage extends BaseFormPage {
	public final static String ID = SysDpyModelPage.class.getName();
	
	private EpiSashArea sashArea;
	
	public SysDpyModelPage(FormEditor editor) {
		super(editor,ID, "部署模型");
	}
	
	@Override
	public IEpiArea newPageArea() {
		sashArea = new EpiSashArea(SWT.VERTICAL);
		return sashArea;
	}
	
	@Override
	public void createControl(Composite parent) {
		IEpiArea[] epiAreas = new IEpiArea[2];
		epiAreas[0] = new SysDpyElmListArea();
		epiAreas[1] = new SysDpyElmListArea();
		
		int[] areaWeights = new int[2];
		areaWeights[0] = 3;
		areaWeights[1] = 1;
		
		sashArea.setEpiAreas(epiAreas);
		sashArea.setAreaWeights(areaWeights);
		sashArea.createControl(parent);	
	}
}
