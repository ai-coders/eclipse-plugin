package net.aicoder.epi.example.property;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.aicoder.epi.base.BasePlugin;
import net.aicoder.epi.base.view.definer.property.PropsDefine;
import net.aicoder.epi.util.FileUtil;
import net.aicoder.tcom.tools.util.JaxbUtil;

public class ReadFileTest {
	private static final Log log = LogFactory.getLog(ReadFileTest.class);
	
	public static void main(String[] args) {
		String fileName = "net/aicoder/epi/example/property/0_dpy_type_subtype.xml";
		String outStr = FileUtil.readFile2String("", fileName);
		System.out.print(outStr);
		PropsDefine propertiesDefine = loadProperitesDefine(outStr);
		dumpPropertiesDefine(propertiesDefine);
	}
	
	private static PropsDefine loadProperitesDefine(String outStr) {
		PropsDefine propertiesDefine = null;
		propertiesDefine = JaxbUtil.convertToJavaBean(PropsDefine.class, outStr);
		return propertiesDefine;
	}

	private static void dumpPropertiesDefine(PropsDefine propertiesDefine) {
		String xmlStr = JaxbUtil.convertToXml(propertiesDefine);
		log.debug("----Begine Dump propertiesDefine-----");
		log.debug("\n" + xmlStr + "\n");
		log.debug("----End Dump propertiesDefine-----");
		System.out.print("----Begine Dump propertiesDefine-----\n");
		System.out.print(xmlStr);
	}
}
