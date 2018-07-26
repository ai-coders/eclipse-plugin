package net.aicoder.epi.base.view;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class EpiPerspective implements IPerspectiveFactory {
	public static String ID = EpiPerspective.class.getName();
	
	public EpiPerspective() {
		super();
	}

	@Override
	public void createInitialLayout(IPageLayout layout) {
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		activeWorkbenchWindow.getActivePage().addPartListener(new IPartListener() {
			
			@Override
			public void partOpened(IWorkbenchPart part) {
				// TODO Auto-generated method stub
				System.out.println("partOpened");
			}
			
			@Override
			public void partDeactivated(IWorkbenchPart part) {
				// TODO Auto-generated method stub
				System.out.println("partDeactivated");
			}
			
			@Override
			public void partClosed(IWorkbenchPart part) {
				// TODO Auto-generated method stub
//				IEditorPart activeEditor = part.getSite().getPage().getActiveEditor();
//				part.getSite().getPage().closeEditor(activeEditor, true);
				part.getSite().getPage().closeAllEditors(true);
				System.out.println("partClosed");
			}
			
			@Override
			public void partBroughtToTop(IWorkbenchPart part) {
				// TODO Auto-generated method stub
				System.out.println("partBroughtToTop");
			}
			
			@Override
			public void partActivated(IWorkbenchPart part) {
				// TODO Auto-generated method stub
				System.out.println("partActivated");
			}
		});
	}
	
	
}
