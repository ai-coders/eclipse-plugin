package net.aicoder.epi.base.view.drag;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

/**
 * 拖拽传输对象transfer抽象类
 * @author WANGQINGPING
 *
 */
public abstract class BaseTransfer extends ByteArrayTransfer{

	@Override
	public TransferData[] getSupportedTypes() {
		// TODO Auto-generated method stub
		System.out.println("BaseTransfer-->getSupportedTypes");
		return super.getSupportedTypes();
	}

	@Override
	public boolean isSupportedType(TransferData transferData) {
		// TODO Auto-generated method stub
		System.out.println("BaseTransfer-->isSupportedType");
		return super.isSupportedType(transferData);
	}

	@Override
	protected int[] getTypeIds() {
		// TODO Auto-generated method stub
		System.out.println("BaseTransfer-->getTypeIds");
		return null;
	}

	@Override
	protected String[] getTypeNames() {
		// TODO Auto-generated method stub
		System.out.println("BaseTransfer-->getTypeNames");
		return null;
	}

	@Override
	protected void javaToNative(Object object, TransferData transferData) {
		// TODO Auto-generated method stub
		System.out.println("BaseTransfer-->javaToNative");
		super.javaToNative(object, transferData);
	}

	@Override
	protected Object nativeToJava(TransferData transferData) {
		// TODO Auto-generated method stub
		System.out.println("BaseTransfer-->nativeToJava");
		return super.nativeToJava(transferData);
	}
}
