package net.aicoder.epi.util.network;

public class NetworkConstant{
	public static final String HOST = "http://39.108.171.116:8000";//"http://127.0.0.1:8000"; // "http://183.63.91.141:11201";//
	public static final String AUTHENTICATE = HOST+"/security/login/authenticate";//登陆
	public static final String PRODUCT = HOST+"/product/devpPrdProduct";//产品
	public static final String PRODUCT_LINE = HOST+"/product/devpPrdProduct";//产品线
	
	public static final String PRODUCTOPS_GROUP = HOST+"/supply/load_productops/";//产品组产品
	public static final String PRODUCTOPS_SYS_CMP = HOST+"/supply/load_devpsyscmp/";//产品下系统、子系统、组件
	public static final String PRODUCTOPS_SYS_DPY_RESOURCES = HOST+"/supply/load_devpsysdpyresources/";//产品下关联资源			
	public static final String PRODUCTOPS_SYS_DPY_SCHEMA = HOST+"/supply/load_devpsyscpyschema/";//产品下部署方案			
	
	
	


}
