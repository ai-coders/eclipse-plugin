package net.aicoder.epi.util;

import org.eclipse.ui.internal.WorkbenchPlugin;

@SuppressWarnings("restriction")
final  public class Log {
	public static void log(String message) {
		StringBuffer buf = new StringBuffer();
		buf.append("Message: ");
		buf.append(message);
		WorkbenchPlugin.log(buf.toString());
	}
	
	

}
