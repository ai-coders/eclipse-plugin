package net.aicoder.epi.sample.edittable;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UsingTableEditorComposite {
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(600, 800);
		shell.setLayout(new FillLayout());
		
		new Ch9TableEditorComposite(shell);

		shell.open();
		shell.layout();
		
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}

}
