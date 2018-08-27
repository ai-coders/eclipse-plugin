package net.aicoder.epi.base.view.drag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import net.aicoder.epi.base.model.IBaseVo;


public class BaseVoTransfer extends BaseTransfer {
	protected static BaseVoTransfer INSTANCE = null;
	private static final String TYPE_NAME = "custom-define-local-selection-basevo-format";				
	private static final int TYPEID = registerType(TYPE_NAME);
	
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
	
//	@Override
//	protected void javaToNative(Object object, TransferData transferData) {
//		if(object == null) return;
//		super.javaToNative(objectToByteArray(object), transferData);
//	}
	
//	@Override
//	protected Object nativeToJava(TransferData transferData) {
//		byte[] bytes = (byte[]) super.nativeToJava(transferData);
//        if (bytes == null) {
//			return null;
//		}
//        return byteArrayToObject(bytes);
//	}
	
	/**
	 * 对象转对象字节数组
	 * @param obj
	 * @return
	 */
	public byte[] objectToByteArray(Object obj) {
		byte[] bytes = null;      
        ByteArrayOutputStream bos = new ByteArrayOutputStream();      
        try {        
            ObjectOutputStream oos = new ObjectOutputStream(bos);         
            oos.writeObject(obj);        
            oos.flush();         
            bytes = bos.toByteArray();      
            oos.close();         
            bos.close();        
        } catch (IOException ex) {        
            ex.printStackTrace();   
        }      
        return bytes;  
	}
	
	/**
	 * 对象字节数据转对象
	 * @param bytes
	 * @return
	 */
	public Object byteArrayToObject (byte[] bytes) {      
        Object obj = null;      
        try {        
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);        
            ObjectInputStream ois = new ObjectInputStream (bis);        
            obj = ois.readObject();   
            ois.close();   
            bis.close();   
        } catch (IOException ex) {        
            ex.printStackTrace();   
        } catch (ClassNotFoundException ex) {        
            ex.printStackTrace();   
        }      
        return obj;    
    }
	
}
