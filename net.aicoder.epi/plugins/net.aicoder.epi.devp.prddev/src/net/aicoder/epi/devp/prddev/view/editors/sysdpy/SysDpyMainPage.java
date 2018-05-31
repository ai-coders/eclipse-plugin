package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.editor.FormEditor;

import net.aicoder.epi.base.view.editor.BaseFormPage;
import net.aicoder.epi.base.view.element.area.EpiSashArea;
import net.aicoder.epi.base.view.element.area.IEpiArea;

public class SysDpyMainPage extends BaseFormPage {
	public final static String ID = SysDpyMainPage.class.getName();
	
	public SysDpyMainPage(FormEditor editor) {
		super(editor,ID, "部署模型");
	}

	@Override
	public void createControl(Composite parent) {
		//EpiPropertiesArea propertiesArea = new EpiPropertiesArea();
		//IPropertiesDoper propertiesDoper = new SysElmPropDoper();
		//propertiesArea.setPropertiesDoper(propertiesDoper);
		
		IEpiArea[] epiAreas = new IEpiArea[2];
		//epiAreas[0] = new TableEditorArea();
		//epiAreas[1] = propertiesArea;
		
		int[] areaWeights = new int[2];
		areaWeights[0] = 3;
		areaWeights[1] = 1;
		
		EpiSashArea sashArea = new EpiSashArea();
		sashArea.setEpiAreas(epiAreas);
		sashArea.setAreaWeights(areaWeights);
		
		sashArea.createControl(parent);		
	}
}
