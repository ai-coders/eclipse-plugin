package net.aicoder.epi.base.view.property;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.property.PropsDefine;
import net.aicoder.epi.util.FileUtil;
import net.aicoder.tcom.tools.util.AiStringUtil;
import net.aicoder.tcom.tools.util.JaxbUtil;

public final class PropsManager {
	public static final String FILE_ENCODING = "UTF-8";

	private static final Log log = LogFactory.getLog(PropsManager.class);
	private static Map<String, PropsDefine> propsDefineMap = new HashMap<String, PropsDefine>(0);
	
	public static PropsDefine getPropsDefine(String pluginId,IBaseVo element) {
		PropsDefine define = null;
		String propsId = element.getPropsId();
		if(!AiStringUtil.isEmpty(propsId)) {
			if(propsDefineMap.containsKey(propsId)) {
				define = propsDefineMap.get(propsId);
			}else {
				define = loadPropsDefine(pluginId, element);
				propsDefineMap.put(propsId, define);
			}
		}
		return define;
	}
	
	private static PropsDefine loadPropsDefine(String pluginId,IBaseVo element) {
		PropsDefine define = null;
		String xmlStr = readFile2String(pluginId,element);
		if(xmlStr == null) {
			return define;
		}
		define = JaxbUtil.convertToJavaBean(PropsDefine.class, xmlStr);
		define.buildPitemDefineMap();
		dumpPropsDefine(define);
		
		return define;
	}
	
	private static String readFile2String(String pluginId, IBaseVo element) {
		String xmlStr = null;
		String fileName = getXmlFileName(element);
		xmlStr = FileUtil.readFile2String(pluginId, fileName);
		return xmlStr;
	}
	
	
	private static String getXmlFileName(IBaseVo element){
		String propsId = element.getPropsId();
		if(AiStringUtil.isEmpty(propsId)) {
			return null;
		}

		URL base = element.getClass().getResource("");
		String elementPath = base.getPath();
		String fileFullPath = elementPath  + "/" + propsId + ".xml";;
		return fileFullPath;
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
