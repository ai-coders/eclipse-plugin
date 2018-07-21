package net.aicoder.epi.base.view.extend;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ExtendWidgetTest {

	
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(600, 800);
		shell.setLayout(new FormLayout());
		
//		DatePickerCombo datePickerCombo = new DatePickerCombo(shell,SWT.SINGLE);
		DatePicker datePicker = new DatePicker(shell, SWT.NONE);
		
		
		shell.open();
		shell.layout();
		
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}
	
	
}
