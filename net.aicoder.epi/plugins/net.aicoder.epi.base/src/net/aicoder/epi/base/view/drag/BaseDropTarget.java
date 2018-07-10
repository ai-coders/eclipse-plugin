package net.aicoder.epi.base.view.drag;

import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;

/**
 * 拖拽释放目的地dropTarget抽象类
 * @author WANGQINGPING
 *
 */
public abstract class BaseDropTarget implements DropTargetListener {

	@Override
	public void dragEnter(DropTargetEvent event) {
		// TODO Auto-generated method stub
		System.out.println("BaseDropTarget-->dragEnter");
	}

	@Override
	public void dragLeave(DropTargetEvent event) {
		// TODO Auto-generated method stub
		System.out.println("BaseDropTarget-->dragLeave");
	}

	@Override
	public void dragOperationChanged(DropTargetEvent event) {
		// TODO Auto-generated method stub
		System.out.println("BaseDropTarget-->dragOperationChanged");
	}

	@Override
	public void dragOver(DropTargetEvent event) {
		// TODO Auto-generated method stub
		System.out.println("BaseDropTarget-->dragOver");
	}

	@Override
	public void drop(DropTargetEvent event) {
		// TODO Auto-generated method stub
		System.out.println("BaseDropTarget-->drop");
	}

	@Override
	public void dropAccept(DropTargetEvent event) {
		// TODO Auto-generated method stub
		System.out.println("BaseDropTarget-->dropAccept");
	}

}
