package net.aicoder.epi.devp.product;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import net.aicoder.tcom.tools.util.ImageCache;

public final class ProductImageConstant {
	public static final String E_PRDLINE = "icons/product/prdline.png";
	public static final String E_PRODUCT = "icons/product/product.png";

	// public static final String E_CATEGORY = "icons/prdline.gif";

	public static final String E_PRD_DEPLOY = "icons/product/prd_deploy.png";
	public static final String E_PRD_CPNT = "icons/product/prd_component.png";
	public static final String E_PRD_IDEPRJ = "icons/product/prd_ideprj.png";

	public static final String E_MODULE = "icons/product/module.png";

	public static ImageDescriptor getImageDescriptor(String path) {
		return ProductPlugin.getImageDescriptor(path);
	}

	public static final Image getImage(String path) {
		return ImageCache.getInstance().getImage(getImageDescriptor(path));
	}
}
