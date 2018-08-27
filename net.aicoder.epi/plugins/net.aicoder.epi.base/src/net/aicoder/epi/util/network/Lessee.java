package net.aicoder.epi.util.network;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * 当前登录租户信息处理
 * @author WANGQINGPING
 *
 */
public class Lessee {
	public static final String LESSEE_USERNAME = "LESSEE_USERNAME";//租户用户名称
	public static final String LESSEE_PASSWORD = "LESSEE_PASSWORD";//租户用户密码
	public static final String LESSEE_PRESENT_SESSION = "LESSEE_PRESENT_SESSION";//这次登录用户Session值			
	
	private static AbstractUIPlugin plugin;
	
	/**
	 * 验证插件是否已经正常创建
	 * @return 
	 */
	private static boolean assertPluginNoEmpty(){ 
		if(plugin == null) {
			return false;
		}else {
			return true;
		}
	}

	public static void registerAbstractUIPlugin(AbstractUIPlugin newPlugin) {
		plugin = newPlugin;
	}
	
	
	public static String getLesseePwd() {
		return assertPluginNoEmpty() ? plugin.getPreferenceStore().getString(LESSEE_USERNAME):null;			
	}
	
	public static void setLesseePwd(String password) {
		if(assertPluginNoEmpty()) plugin.getPreferenceStore().setValue(LESSEE_PASSWORD, password);
	}
	
	public static String getLesseeName() {
		return assertPluginNoEmpty() ? plugin.getPreferenceStore().getString(LESSEE_PASSWORD):null;
	}
	
	public static void setLesseeName(String username) {
		if(assertPluginNoEmpty()) plugin.getPreferenceStore().setValue(LESSEE_USERNAME, username);
	}
	
	public static String getPresentSession() {
		return assertPluginNoEmpty() ? plugin.getPreferenceStore().getString(LESSEE_PRESENT_SESSION):null;
	}
	
	public static void setPresentSession(String presentSession) {
		if(assertPluginNoEmpty()) plugin.getPreferenceStore().setValue(LESSEE_PRESENT_SESSION, presentSession);
	}
	
	/**
	 * 校验请求网络是否已正常登录
	 * @return
	 */
	public static boolean isAccess() {
		boolean state = false;
		if(getPresentSession() != null) {
			state = true;
		}
		return state;
	}
	
	
	
	
	
	
}
