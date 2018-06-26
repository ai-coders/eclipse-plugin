package net.aicoder.epi.devp.prddev.view.property;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.aicoder.epi.base.model.BaseVo;
import net.aicoder.epi.base.model.ElementInfo;
import net.aicoder.epi.base.model.ExtInfosDefine;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.property.PropsDefine;
import net.aicoder.epi.base.view.property.IPropsManager;
import net.aicoder.epi.devp.prddev.PrddevPlugin;
import net.aicoder.epi.devp.prddev.doper.dev.system.SysPropsDoper;
import net.aicoder.epi.util.FileUtil;
import net.aicoder.tcom.tools.util.AiStringUtil;
import net.aicoder.tcom.tools.util.JaxbUtil;

public final class PrddevPropsManager implements IPropsManager {
	public static String PLUGIN_ID = PrddevPlugin.PLUGIN_ID;
	public static final String FILE_ENCODING = "UTF-8";

	private static final Log log = LogFactory.getLog(PrddevPropsManager.class);
	private static Map<String, PropsDefine> propsDefineMap = new HashMap<String, PropsDefine>(0);
	private static Map<String, ExtInfosDefine> extInfosDefineMap = new HashMap<String, ExtInfosDefine>(0);
	
	private static Map<String, Object> refObjectsMap = new HashMap<String, Object>(0);
	
	private static SysPropsDoper doper = new SysPropsDoper();
	
	@Override
	public String getPluginId() {
		return PLUGIN_ID;
	}
	
	@Override
	public ExtInfosDefine getExtInfosDefine(IBaseVo element) {
		return _getExtInfosDefine(element);
	}

	@Override
	public void loadEelementInfos(BaseVo element) {
		if(element == null) {
			return;
		}
		if(element.isLoadedElement(BaseVo.PROP_INFO_PREFIX)) {
			return;
		}
		List<ElementInfo> elementInfoList = doper.listElementInfo(element);
		element.putElementInfos(elementInfoList);
	}
	
	private static ExtInfosDefine _getExtInfosDefine(IBaseVo element) {
		ExtInfosDefine define = null;
		String propsId = element.getEtype();
		if(!AiStringUtil.isEmpty(propsId)) {
			if(extInfosDefineMap.containsKey(propsId)) {
				define = extInfosDefineMap.get(propsId);
			}else {
				define = loadExtInfosDefine(element);
				extInfosDefineMap.put(propsId, define);
			}
		}
		return define;
	}
	
	private static ExtInfosDefine loadExtInfosDefine(IBaseVo element) {
		ExtInfosDefine define = null;
		define = doper.loadExtInfosDefine(element);
		return define;
	}

	@Override
	public PropsDefine getPropsDefine(IBaseVo element) {
		return _getPropsDefine(element);
	}
	
	private static PropsDefine _getPropsDefine(IBaseVo element) {
		String pluginId = PLUGIN_ID;
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
	
	@Override
	public void clearRefObjects() {
		refObjectsMap.clear();
	}
	
	@Override
	public void putRefObjects(String refObjectsCode, Object refObjects) {
		refObjectsMap.put(refObjectsCode, refObjects);
	}
	
	@Override
	public Object getRefObjects(String refObjectsCode) {
		Object refObjects = null;
		if(refObjectsMap.containsKey(refObjectsCode)) {
			refObjects = refObjectsMap.get(refObjectsCode);
		}
		return refObjects;
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
