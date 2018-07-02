package net.aicoder.epi.base;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import net.aicoder.tcom.tools.util.ImageCache;

public final class BaseImageConstant {
	public static final String A_ADD = "icons/actions/add.gif";
	public static final String A_ADD_CHILD = "icons/actions/add_child.png";
	public static final String A_ADD_CHILD_DIS = "icons/actions/add_child_dis.png";
	public static final String A_ADD_ROW = "icons/actions/add_row.png";
	public static final String A_ADD_ROW_DIS = "icons/actions/add_row_dis.png";

	public static final String A_DELETE = "icons/actions/delete.gif";
	public static final String A_DELETE_DIS = "icons/actions/delete_dis.gif";

	public static final String A_LEFT = "icons/actions/left.png";
	public static final String A_LEFT_DIS = "icons/actions/left_dis.png";

	public static final String A_RIGHT = "icons/actions/right.png";
	public static final String A_RIGHT_DIS = "icons/actions/right_dis.png";

	public static final String A_UP = "icons/actions/up.png";
	public static final String A_UP_DIS = "icons/actions/up_dis.png";
	
	public static final String A_UPGRADE = "icons/actions/upgrade.png";
	public static final String A_UPGRADE_DIS = "icons/actions/upgrade.png";
	
	public static final String A_DEGRADE = "icons/actions/degrade.png";
	public static final String A_DEGRADE_DIS = "icons/actions/degrade.png";

	public static final String A_DOWN = "icons/actions/down.png";
	public static final String A_DOWN_DIS = "icons/actions/down_dis.png";

	public static final String A_REFRESH = "icons/actions/refresh.png";
	public static final String A_REFRESH_DIS = "icons/actions/refresh_dis.png";

	public static final String A_FILTER = "icons/actions/filter.gif";

	public static final String E_PRDLINE = "icons/product/prdline.gif";
	public static final String E_PRODUCT = "icons/product/product.gif";

	// public static final String E_CATEGORY = "icons/prdline.gif";

	public static final String E_PRD_DEPLOY = "icons/product/prd_deploy.gif";
	public static final String E_PRD_CPNT = "icons/product/prd_component.gif";
	public static final String E_PRD_IDEPRJ = "icons/product/prd_ideprj.gif";

	public static final String E_MODULE = "icons/product/module.gif";
	
	//// property
	public static final String PROPS_VIEW = "icons/property/properties_view.gif";
	public static final String PROPS_FILTER_ADVANCED = "icons/property/filter_advanced_properties.gif";
	public static final String PROPS_RESET_DEFAULT = "icons/property/properties_default.gif";
	

	public static ImageDescriptor getImageDescriptor(String path) {
		return BasePlugin.getImageDescriptor(path);
	}

	public static final Image getImage(String path) {
		return ImageCache.getInstance().getImage(getImageDescriptor(path));
	}
}
