package net.aicoder.epi.util.network;

public class NetworkConstant{
	public static final String HOST = "http://127.0.0.1:8000";//http://39.108.171.116:8000";// "http://183.63.91.141:11201";//
	public static final String AUTHENTICATE = HOST+"/security/login/authenticate";//登陆
	public static final String PRODUCT = HOST+"/product/devpPrdProduct";//产品
	public static final String PRODUCT_LINE = HOST+"/product/devpPrdProduct";//产品线
	
	public static final String PRODUCTOPS_GROUP = HOST+"/supply/load_productops/";//产品组产品
	public static final String PRODUCTOPS_SYS_CMP = HOST+"/supply/load_devpsyscmp/";//产品->系统、子系统、组件
	public static final String PRODUCTOPS_SYS_DPY_RESOURCES = HOST+"/supply/load_devpsysdpyresources/";//产品->关联资源	
	public static final String PRODUCTOPS_SYS_DPY_RES_INST = HOST+"/supply/load_devpsysdpyresinst/";//产品->资源应用场景
	public static final String PRODUCTOPS_SYS_DPY_SCHEMA = HOST+"/supply/load_devpsysdpyschema/";//产品->部署方案
	public static final String PRODUCTOPS_OPS_ASSET_CMDB = HOST+"/supply/load_devpopsassetcmdb/";//产品->IT资源	
			
	
	
	


}
