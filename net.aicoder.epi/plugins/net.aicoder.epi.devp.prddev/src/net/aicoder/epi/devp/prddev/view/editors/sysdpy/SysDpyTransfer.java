package net.aicoder.epi.devp.prddev.view.editors.sysdpy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.eclipse.swt.dnd.TransferData;

import net.aicoder.epi.base.view.drag.BaseVoTransfer;

/**
 * 定义部署模型IBaseVo拖拽传输对象
 * @author WANGQINGPING
 *
 */
public class SysDpyTransfer extends BaseVoTransfer{
	
	private SysDpyTransfer() {
		
	}
	
	public static SysDpyTransfer getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new SysDpyTransfer();
		}
        return (SysDpyTransfer) INSTANCE;
    }
	
	@Override
	protected void javaToNative(Object object, TransferData transferData) {
		if(object == null) return;
		super.javaToNative(objectToByteArray(object), transferData);
	}
	
	@Override
	protected Object nativeToJava(TransferData transferData) {
		byte[] bytes = (byte[]) super.nativeToJava(transferData);
        if (bytes == null) {
			return null;
		}
        return byteArrayToObject(bytes);
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
