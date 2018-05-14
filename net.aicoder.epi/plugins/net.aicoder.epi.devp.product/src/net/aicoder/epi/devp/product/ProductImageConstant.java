package net.aicoder.epi.devp.product;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import net.aicoder.tcom.tools.util.ImageCache;

public final class ProductImageConstant {
	public static final String A_ADD = "icons/actions/add.gif";
	public static final String A_DELETE = "icons/actions/delete.gif";
	public static final String A_DELETE_DIS = "icons/actions/delete_dis.gif";
	public static final String A_FILTER = "icons/actions/filter.gif";

	public static final String E_PRDLINE = "icons/product/prdline.gif";
	public static final String E_PRODUCT = "icons/product/product.gif";

	// public static final String E_CATEGORY = "icons/prdline.gif";

	public static final String E_PRD_DEPLOY = "icons/product/prd_deploy.gif";
	public static final String E_PRD_CPNT = "icons/product/prd_component.gif";
	public static final String E_PRD_IDEPRJ = "icons/product/prd_ideprj.gif";

	public static final String E_MODULE = "icons/product/module.gif";

	//编辑器相关
	public static final String IMG_FORM_BG = "icons/editors/form_banner.gif"; //$NON-NLS-1$
	public static final String IMG_HORIZONTAL = "icons/editors/th_horizontal.gif"; //$NON-NLS-1$
	public static final String IMG_VERTICAL = "icons/editors/th_vertical.gif"; //$NON-NLS-1$

	public static ImageDescriptor getImageDescriptor(String path) {
		return ProductPlugin.getImageDescriptor(path);
	}

	public static final Image getImage(String path) {
		return ImageCache.getInstance().getImage(getImageDescriptor(path));
	}
}
