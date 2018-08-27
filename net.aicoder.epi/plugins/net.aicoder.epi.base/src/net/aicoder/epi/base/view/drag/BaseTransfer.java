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
		return super.getSupportedTypes();
	}

	@Override
	public boolean isSupportedType(TransferData transferData) {
		return super.isSupportedType(transferData);
	}

	@Override
	protected int[] getTypeIds() {
		return null;
	}

	@Override
	protected String[] getTypeNames() {
		return null;
	}

	@Override
	protected void javaToNative(Object object, TransferData transferData) {
		super.javaToNative(object, transferData);
	}

	@Override
	protected Object nativeToJava(TransferData transferData) {
		return super.nativeToJava(transferData);
	}
}
