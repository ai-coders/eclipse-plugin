package net.aicoder.epi.devp.prddev;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import net.aicoder.tcom.tools.util.ImageCache;

public final class PrddevImageConstant {
	public static final String PRODUCT = "icons/product/product.png";
	public static final String PRODUCT_DIS = "icons/product/product_dis.png";

	public static final String E_MODULE = "icons/product/module.png";

	public static final String E_PRD_CPNT = "icons/product/prd_component.png";
	public static final String E_PRD_IDEPRJ = "icons/product/prd_ideprj.png";
	public static final String E_PRD_DEPLOY = "icons/product/prd_deploy.png";

	public static ImageDescriptor getImageDescriptor(String path) {
		return PrddevPlugin.getImageDescriptor(path);
	}

	public static final Image getImage(String path) {
		return ImageCache.getInstance().getImage(getImageDescriptor(path));
	}
}
