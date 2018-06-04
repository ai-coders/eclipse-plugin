package net.aicoder.epi.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class BasePlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "net.aicoder.epi.base"; //$NON-NLS-1$
	public static final String FILE_ENCODING = "UTF-8";

	// The shared instance
	private static BasePlugin plugin;

	/**
	 * The constructor
	 */
	public BasePlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static BasePlugin getDefault() {
		return plugin;
	}

	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public static String readFile2String(String fileName) {
		String outStr = null;
		URL fileUrl = getFileURL(fileName);
		// bundle.getEntry(filePathName);
		// bundle.getResource(),bundle.getEntry()区别，
		// 个人理解 一个专门针对class 一个针对绝大部分资源文件，都可以获取插件依赖的相关文件
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
	
	private static URL getFileURLByBundle(String fileName){
		Bundle bundle = Platform.getBundle(PLUGIN_ID);
		URL fileUrl = bundle.getResource(fileName);
		return fileUrl;
	}
}
