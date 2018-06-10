package net.aicoder.epi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import net.aicoder.epi.base.BasePlugin;
import net.aicoder.tcom.tools.util.AiStringUtil;

public final class FileUtil {
	public static final String FILE_ENCODING = "UTF-8";
	
	// bundle.getEntry(filePathName);
	// bundle.getResource(),bundle.getEntry()区别，
	// 个人理解 一个专门针对class 一个针对绝大部分资源文件，都可以获取插件依赖的相关文件
	public static String readFile2String(String pluginId, String fileName) {
		String outStr = null;
		URL fileUrl;
		if(AiStringUtil.isEmpty(pluginId)) {
			fileUrl =getFileURL(fileName);
		}else {
			fileUrl = getFileURLByBundle(pluginId, fileName);
		}
		if(fileUrl == null) {
			return outStr;
		}
		File file = null;
		try {
			file = new File(FileLocator.resolve(fileUrl).toURI());
			Long filelength = file.length();
			byte[] filecontent = new byte[filelength.intValue()];
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
			outStr = new String(filecontent, FILE_ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return outStr;
	}

	private static URL getFileURLByBundle(String pluginId, String fileName){
		Bundle bundle = Platform.getBundle(pluginId);
		URL fileUrl = bundle.getResource(fileName);
		return fileUrl;
	}
	
	private static URL getFileURL(String fileName){
		URL fileUrl = null;
		//String fileFullPath = "platform:/plugin/" + PLUGIN_ID + "/" + fileName;
		String protocol = "file:";
		URL base = BasePlugin.class.getResource("");
		String pluginsPath = base.getPath() + "../../../..";
		String fileFullPath = protocol + pluginsPath  + "/" + fileName;;
		try {
			fileUrl = new URL(fileFullPath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		return fileUrl;
	}
}
