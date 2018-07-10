package net.aicoder.epi.base.view.drag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipse.swt.internal.ole.win32.IDataObject;
import org.eclipse.swt.internal.ole.win32.STGMEDIUM;
import org.eclipse.swt.internal.win32.OS;

import net.aicoder.epi.base.model.IBaseVo;


public class BaseVoTransfer extends BaseTransfer {
	private static final BaseVoTransfer INSTANCE = new BaseVoTransfer();
	private static final String TYPE_NAME = "locat-selection-custom-basevo-format"; //$NON-NLS-1$;
	private static final int TYPEID = registerType(TYPE_NAME);
	
	
	private BaseVoTransfer() {
		
	}
	
	public static BaseVoTransfer getInstance() {
        return INSTANCE;
    }
	
	@Override
	protected int[] getTypeIds() {
		return new int[] { TYPEID };
	}
	
	@Override
	protected String[] getTypeNames() {
		return new String[] { TYPE_NAME };
	}
	
	@Override
	protected boolean validate(Object object) {
		return (object != null  && object instanceof IBaseVo);
	}
	
	@Override
	protected void javaToNative(Object object, TransferData transferData) {
		if(!(object instanceof IBaseVo)) return;
		IBaseVo baseVo = (IBaseVo) object;
		ByteArrayOutputStream out = null;
		ObjectOutputStream oos = null;
		try {
			out = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(out);
			oos.writeObject(baseVo);
			
			byte[] data = out.toByteArray();
			super.javaToNative(data, transferData);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(oos != null) oos.close();
				if(out != null) out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected Object nativeToJava(TransferData transferData) {
		if (!isSupportedType(transferData) || transferData.pIDataObject == 0)  return null;

//		IDataObject data = new IDataObject(transferData.pIDataObject);
//		data.AddRef();
//		FORMATETC formatetc = transferData.formatetc;
//		STGMEDIUM stgmedium = new STGMEDIUM();
//		stgmedium.tymed = COM.TYMED_HGLOBAL;
//		transferData.result = getData(data, formatetc, stgmedium);
//		data.Release();
//		if (transferData.result != COM.S_OK) return null;
//		long /*int*/ hMem = stgmedium.unionField;
//		int size = OS.GlobalSize(hMem);
//		byte[] buffer = new byte[size];
//		long /*int*/ ptr = OS.GlobalLock(hMem);
//		OS.MoveMemory(buffer, ptr, size);
//		OS.GlobalUnlock(hMem);
//		OS.GlobalFree(hMem);
		
		
		
		
		
		byte[] bytes = (byte[]) super.nativeToJava(transferData);
        if (bytes == null) {
			return null;
		}
        
        IBaseVo baseVo = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
        	bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			baseVo = (IBaseVo) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bais != null) bais.close();
				if(ois != null) ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        
        return baseVo;
	}
}
