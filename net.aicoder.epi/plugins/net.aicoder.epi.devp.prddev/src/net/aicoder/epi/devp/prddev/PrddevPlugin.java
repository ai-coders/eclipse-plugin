package net.aicoder.epi.devp.prddev;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class PrddevPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "net.aicoder.epi.devp.prddev"; //$NON-NLS-1$

	// The shared instance
	private static PrddevPlugin plugin;
	
	/**
	 * The constructor
	 */
	public PrddevPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
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
	public static PrddevPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	
	public static Integer getInt(String name) {
		return plugin.getPreferenceStore().getInt(name);
	}
	
	public static void setInt(String name,Integer value) {
		plugin.getPreferenceStore().setValue(name, value);
	}
	
	public static String getValue(String name) {
		return plugin.getPreferenceStore().getString(name);
	}
	
	public static void setValue(String name,String value) {
		plugin.getPreferenceStore().setValue(name, value);
	}
	
	public static Boolean getBoolean(String name) {
		return plugin.getPreferenceStore().getBoolean(name);
	}
	
	public static void setBoolean(String name,Boolean value) {
		plugin.getPreferenceStore().setValue(name, value);
	}
	
	public static Double getDouble(String name) {
		return plugin.getPreferenceStore().getDouble(name);
	}
	
	public static void setDouble(String name,Double value) {
		plugin.getPreferenceStore().setValue(name, value);
	}
}
