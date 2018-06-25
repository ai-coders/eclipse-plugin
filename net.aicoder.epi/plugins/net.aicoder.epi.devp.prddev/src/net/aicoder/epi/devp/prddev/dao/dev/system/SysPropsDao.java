package net.aicoder.epi.devp.prddev.dao.dev.system;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.ElementInfo;
import net.aicoder.epi.base.model.ElementInfoDefine;
import net.aicoder.epi.base.model.IBaseVo;

public class SysPropsDao extends BaseDao {
	public SysPropsDao() {
		super();
	}

	public List<ElementInfoDefine> listCommonExtInfoDefine(IBaseVo element){
		List<ElementInfoDefine> list = null;
		if(element == null) {
			return list;
		}
		String etype = element.getEtype();
		long tid = PLATFORM_COMMON_TID;
		
		list = _listExtInfoDefine(etype, tid);
		return list;
	}
	
	public List<ElementInfoDefine> listTenantExtInfoDefine(IBaseVo element){
		List<ElementInfoDefine> list = null;
		if(element == null) {
			return list;
		}
		String etype = element.getEtype();
		long tid = element.getTid();
		
		list = _listExtInfoDefine(etype, tid);
		return list;
	}
	
	private List<ElementInfoDefine> _listExtInfoDefine(String etype, long tid){
		List<ElementInfoDefine> list = new ArrayList<ElementInfoDefine>(0);
		{
			ElementInfoDefine infoDefine = new ElementInfoDefine();
			infoDefine.setTid(tid);
			infoDefine.setEtype(etype);
			infoDefine.setCode("svnUrl");
			infoDefine.setName("SVN地址");
			infoDefine.setAlias("SVN_URL");
			infoDefine.setDescription("SVN地址");
			
			infoDefine.setDataType("String");
			infoDefine.setInfoValue("");
			infoDefine.setNotes("SVN地址");
			
			list.add(infoDefine);
		}
		{
			ElementInfoDefine infoDefine = new ElementInfoDefine();
			infoDefine.setTid(tid);
			infoDefine.setEtype(etype);
			infoDefine.setCode("svnUser");
			infoDefine.setName("SVN用户");
			infoDefine.setAlias("SVN_User");
			infoDefine.setDescription("SVN用户");
			
			infoDefine.setDataType("String");
			infoDefine.setInfoValue("Stone.Shi");
			infoDefine.setNotes("SVN用户");
			
			list.add(infoDefine);
		}
		{
			ElementInfoDefine infoDefine = new ElementInfoDefine();
			infoDefine.setTid(tid);
			infoDefine.setEtype(etype);
			infoDefine.setCode("svnPassword");
			infoDefine.setName("SVN密码");
			infoDefine.setAlias("SVN_URL");
			infoDefine.setDescription("SVN密码");
			
			infoDefine.setDataType("String");
			infoDefine.setInfoValue("stoneshi");
			infoDefine.setNotes("SVN密码");
			
			list.add(infoDefine);
		}
		return list;		
	}
	
	public List<ElementInfo> listElementInfo(IBaseVo element){
		List<ElementInfo> list = null;
		if(element == null) {
			return list;
		}
		String etype = element.getEtype();
		long objRid = element.getRid();
		
		list = _listElementInfo(etype, objRid);
		return list;
	}

	private List<ElementInfo> _listElementInfo(String etype, long objRid){
		Long tid = 10001l;
		List<ElementInfo> list = new ArrayList<ElementInfo>(0);
		{
			ElementInfo info = new ElementInfo();
			info.setTid(tid);
			info.setEtype(etype);
			info.setObjRid(objRid);
			info.setCode("svnUrl");
			info.setName("SVN地址");
			info.setAlias("SVN_URL");
			info.setDescription("SVN地址");
			
			info.setDataType("String");
			info.setInfoValue("Http://svn.daan.com/code/...");
			info.setNotes("SVN地址");
			
			list.add(info);
		}
		{
			ElementInfo info = new ElementInfo();
			info.setTid(tid);
			info.setEtype(etype);
			info.setObjRid(objRid);
			info.setCode("svnUser");
			info.setName("SVN用户");
			info.setAlias("SVN_User");
			info.setDescription("SVN用户");
			
			info.setDataType("String");
			info.setInfoValue("Stone.Shi");
			info.setNotes("SVN用户");
			
			list.add(info);
		}
		{
			ElementInfo info = new ElementInfo();
			info.setTid(tid);
			info.setEtype(etype);
			info.setObjRid(objRid);
			info.setCode("svnPassword");
			info.setName("SVN密码");
			info.setAlias("SVN_URL");
			info.setDescription("SVN密码");
			
			info.setDataType("String");
			info.setInfoValue("stoneshi");
			info.setNotes("SVN密码");
			
			list.add(info);
		}
		return list;
	}
}
