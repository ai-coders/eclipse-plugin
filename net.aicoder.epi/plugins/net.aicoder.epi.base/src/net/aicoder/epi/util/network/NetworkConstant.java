package net.aicoder.epi.util.network;

public class NetworkConstant{
//	public static final String HOST = "http://127.0.0.1:8000";
//	public static final String HOST = "http://39.108.171.116:8000";
	public static final String HOST = "http://183.63.91.140:11202";
	
	//登录
	public static final String AUTHENTICATE = HOST+"/security/login/authenticate";//登陆
	
	//产品组产品
	public static final String SYS_DPY_OPS = HOST+"/product/devpPrdPrdline/tree";
	//产品->系统、子系统、组件
	public static final String SYS_DPY_CMP = HOST+"/product/devpPrdProduct/tree";
	//产品->资源规划
	public static final String SYS_DPY_RES_PLAN = HOST+"/supply/load_devpsysdpyresources/";
	
	//产品->部署方案
	public static final String SYS_DPY_SCHEMA = HOST+"/supply/load_devpsysdpyresinst/";
	//部署方案->环境准备->参数准备
	public static final String SYS_DPY_PARAM_CONF = HOST+"/supply/load_devpsysdpyschema/";
	//部署方案->环境准备->主机节点
	public static final String SYS_DPY_HOST_NODE = HOST+"/supply/load_devpopsassetcmdb/";
	//部署方案->环境准备->资源配置
	public static final String SYS_DPY_RES_CONF = HOST+"/supply/load_devpopsassetcmdb/";	
	//部署方案->构建及发布
	public static final String SYS_DPY_BUILD_PUBLISH = HOST+"/supply/load_devpopsassetcmdb/";
	//部署方案->系统监控
	public static final String SYS_DPY_SYS_MONITOR = HOST+"/supply/load_devpopsassetcmdb/";	
	

}
