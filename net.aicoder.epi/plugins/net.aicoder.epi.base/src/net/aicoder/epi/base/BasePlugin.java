package net.aicoder.epi.base;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class BasePlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "net.aicoder.epi.base"; //$NON-NLS-1$

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
		BundleResourceProvider.configureCleanUp(context);
		addLogListener();
		/*
		 * if (EnvironmentUtils.IS_LINUX) { installPreferenceForwarder(); }
		 */
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

	////////////////////////////////////////////////////////////////////////////
	//
	// Logging
	//
	////////////////////////////////////////////////////////////////////////////
	private static boolean m_displayExceptionOnConsole = true;

	/**
	 * Sets flag if {@link #log(Throwable)} should display exception on console.
	 */
	public static void setDisplayExceptionOnConsole(boolean displayExceptionOnConsole) {
		m_displayExceptionOnConsole = displayExceptionOnConsole;
	}

	/**
	 * Logs given {@link IStatus} into Eclipse .log.
	 */
	public static void log(IStatus status) {
		BasePlugin pluginInstance = getDefault();
		if (pluginInstance != null) {
			pluginInstance.getLog().log(status);
		}
	}

	/**
	 * Logs {@link IStatus} with given message into Eclipse .log.
	 */
	public static void log(String message) {
		log(new Status(IStatus.INFO, PLUGIN_ID, IStatus.INFO, message, null));
	}

	/**
	 * Logs {@link IStatus} with given exception into Eclipse .log.
	 */
	public static void log(Throwable e) {
		// print on console for easy debugging
		if (m_displayExceptionOnConsole) {
			e.printStackTrace();
		}
		// log into Eclipse .log
		{
			String message = e.getMessage();
			if (message == null) {
				message = e.getClass().getName();
			}
			String versionString = "V1.0";// ProductInfo.getProduct().getVersion().toString();
			String buildString = "B1";// ProductInfo.getProduct().getBuild();
			log("Designer [" + versionString + (versionString.endsWith(buildString) ? "" : "." + buildString) + "]: "
					+ message, e);
		}
	}

	/**
	 * Logs {@link IStatus} with given message and exception into Eclipse .log.
	 */
	public static void log(String message, Throwable e) {
		log(createStatus(message, e));
	}

	/**
	 * Creates {@link IStatus} for given message and exception.
	 */
	public static Status createStatus(String message, Throwable e) {
		return new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, message, e) {
			@Override
			public boolean isMultiStatus() {
				return true;
			}
		};
	}

	////////////////////////////////////////////////////////////////////////////
	//
	// Track last log entry
	//
	////////////////////////////////////////////////////////////////////////////
	private IStatus m_lastStatus;

	private void addLogListener() {
		ILog log = getLog();
		log.addLogListener(new ILogListener() {
			public void logging(IStatus status, String plugin) {
				setLastStatus(status);
			}
		});
	}

	private void setLastStatus(IStatus lastStatus) {
		m_lastStatus = lastStatus;
	}

	private IStatus getLastStatus0() {
		return m_lastStatus;
	}

	/**
	 * @return the {@link IStatus} instance which was last logged by the eclipse
	 *         log.
	 */
	public static IStatus getLastStatus() {
		BasePlugin pluginInstance = getDefault();
		if (pluginInstance != null) {
			return pluginInstance.getLastStatus0();
		}
		return null;
	}

}
