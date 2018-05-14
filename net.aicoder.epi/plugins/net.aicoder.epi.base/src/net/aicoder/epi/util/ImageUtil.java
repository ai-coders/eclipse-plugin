package net.aicoder.epi.util;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;

public final class ImageUtil {
	public static final Image getImage(String path) {
		return PlatformUI.getWorkbench().getSharedImages().getImage(path);
	}
}
