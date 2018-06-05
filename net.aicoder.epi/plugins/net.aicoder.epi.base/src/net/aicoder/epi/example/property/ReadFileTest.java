package net.aicoder.epi.example.property;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.aicoder.epi.base.BasePlugin;
import net.aicoder.epi.base.view.definer.property.PropertiesDefine;
import net.aicoder.tcom.tools.util.JaxbUtil;

public class ReadFileTest {
	private static final Log log = LogFactory.getLog(ReadFileTest.class);
	
	public static void main(String[] args) {
		String fileName = "net/aicoder/epi/example/property/0_dpy_type_subtype.xml";
		String outStr = BasePlugin.readFile2String(fileName);
		System.out.print(outStr);
		PropertiesDefine propertiesDefine = loadProperitesDefine(outStr);
		dumpPropertiesDefine(propertiesDefine);
	}
	
	private static PropertiesDefine loadProperitesDefine(String outStr) {
		PropertiesDefine propertiesDefine = null;
		propertiesDefine = JaxbUtil.convertToJavaBean(PropertiesDefine.class, outStr);
		return propertiesDefine;
	}

	private static void dumpPropertiesDefine(PropertiesDefine propertiesDefine) {
		String xmlStr = JaxbUtil.convertToXml(propertiesDefine);
		log.debug("----Begine Dump propertiesDefine-----");
		log.debug("\n" + xmlStr + "\n");
		log.debug("----End Dump propertiesDefine-----");
		System.out.print("----Begine Dump propertiesDefine-----\n");
		System.out.print(xmlStr);
	}
}
