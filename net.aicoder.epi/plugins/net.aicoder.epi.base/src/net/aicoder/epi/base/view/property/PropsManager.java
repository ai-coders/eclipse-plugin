package net.aicoder.epi.base.view.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.FileLocator;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.view.definer.property.PropsDefine;
import net.aicoder.tcom.tools.util.AiStringUtil;
import net.aicoder.tcom.tools.util.JaxbUtil;

public final class PropsManager {
	public static final String FILE_ENCODING = "UTF-8";

	private static final Log log = LogFactory.getLog(PropsManager.class);
	private static Map<String, PropsDefine> propsDefineMap = new HashMap<String, PropsDefine>(0);
	
	public static PropsDefine getPropsDefine(IBaseVo element) {
		PropsDefine define = null;
		String propsId = element.getPropsId();
		if(!AiStringUtil.isEmpty(propsId)) {
			if(propsDefineMap.containsKey(propsId)) {
				define = propsDefineMap.get(propsId);
			}else {
				define = loadPropsDefine(element);
				propsDefineMap.put(propsId, define);
			}
		}
		return define;
	}
	
	private static PropsDefine loadPropsDefine(IBaseVo element) {
		PropsDefine define = null;
		String xmlStr = readFile2String(element);
		if(xmlStr == null) {
			return define;
		}
		define = JaxbUtil.convertToJavaBean(PropsDefine.class, xmlStr);
		dumpPropsDefine(define);
		
		return define;
	}
	
	private static String readFile2String(IBaseVo element) {
		String xmlStr = null;
		URL fileUrl = getFileURL(element);
		if(fileUrl == null) {
			return xmlStr;
		}
		
		File file = null;
		try {
			file = new File(FileLocator.resolve(fileUrl).toURI());
			Long filelength = file.length();
			byte[] filecontent = new byte[filelength.intValue()];
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
			xmlStr = new String(filecontent, FILE_ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.print(xmlStr);
		return xmlStr;
	}
	
	
	private static URL getFileURL(IBaseVo element){
		String propsId = element.getPropsId();
		if(!AiStringUtil.isEmpty(propsId)) {
			return null;
		}

		URL fileUrl = null;
		String protocol = "file:";
		URL base = element.getClass().getResource("");
		String elementPath = base.getPath();
		String fileFullPath = protocol + elementPath  + "/" + propsId + ".xml";;
		try {
			fileUrl = new URL(fileFullPath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		return fileUrl;
	}
	
	private static void dumpPropsDefine(PropsDefine propsDefine) {
		String xmlStr = JaxbUtil.convertToXml(propsDefine);
		log.debug("----Begine Dump propertiesDefine-----");
		log.debug("\n" + xmlStr + "\n");
		log.debug("----End Dump propertiesDefine-----");
		System.out.print("----Begine Dump propertiesDefine-----\n");
		System.out.print(xmlStr);
	}
}
