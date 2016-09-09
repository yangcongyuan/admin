package com.etnlgravtnl.common.utils;

import com.etnlgravtnl.common.config.CommonDictionaryConfig;
import com.etnlgravtnl.common.session.VirtualSession;
import com.etnlgravtnl.common.session.VirtualSessionManager;
import com.etnlgravtnl.system.dao.LogAccessDao;
import com.etnlgravtnl.system.entity.AdminUser;
import com.etnlgravtnl.system.entity.LogAccess;


import javax.ws.rs.container.ContainerRequestContext;

/**
 * @Name:
 * @Author: junz（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2016-06-10（创建日期）
 * @Description:
 * 日志工具类
 */
public class LogUtils {

	private static LogAccessDao logAccessDao = SpringContextHolder.getBean(LogAccessDao.class);



	/**
	 * @Name:
	 * @Author: junz（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016-06-10（创建日期）
	 * @param ex 异常  title 记录日志的url描述 timeCost耗时
	 * @Description:
	 * 保存访问日志日志记录
	 */
	public static void saveAccessLog(ContainerRequestContext requestContext, Exception ex,String title,int timeCost){
		 String authHeader=requestContext.getHeaderString("Authorization");
    	String tokenText=null;
    	if (authHeader!=null)
    		tokenText=authHeader;
		AdminUser user=null;
		VirtualSession session= VirtualSessionManager.getInstance().getSession(tokenText, false);
		if(tokenText!=null)
			user = (AdminUser) session.getAttribute("user");
		LogAccess log = new LogAccess();
		log.setCreateBy(user==null?"visitor":String.valueOf(user.getId()));
		log.setCreateDate(DateUtils.getDateTime());
		log.setTitle(title);
		log.setType(ex == null ? CommonDictionaryConfig.getConfig("logAccess.TYPE_ACCESS"):
				CommonDictionaryConfig.getConfig("logAccess.TYPE_EXCEPTION"));
		log.setRemoteAddr(StringUtils.getRemoteAddr(requestContext));
		log.setUserAgent(requestContext.getHeaderString("user-agent"));
		log.setRequestUri(requestContext.getUriInfo().getRequestUri().toString());
		log.setParams("");
		log.setMethod(requestContext.getMethod());
		log.setException(ex==null?"":ex.getMessage());
		log.setTimeCost(timeCost);
		// 异步保存日志
		new SaveAccessLogThread(log,null, ex).start();
	}

	/**
	 * @Name:
	 * @Author: junz（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016-06-10（创建日期）
	 * @Description:
	 * 保存访问日志日志线程
	 */
	public static class SaveAccessLogThread extends Thread{
		
		private LogAccess log;
		private Object handler;
		private Exception ex;
		
		public SaveAccessLogThread(LogAccess log, Object handler, Exception ex){
			super(SaveAccessLogThread.class.getSimpleName());
			this.log = log;
			this.handler = handler;
			this.ex = ex;
		}
		
		@Override
		public void run() {
			// 获取日志标题
			if (StringUtils.isBlank(log.getTitle())){

				log.setTitle(log.getRequestUri());
			}
			// 如果有异常，设置异常信息
			log.setException(Exceptions.getStackTraceAsString(ex));
			// 如果无标题并无异常日志，则不保存信息
			if (StringUtils.isBlank(log.getTitle()) && StringUtils.isBlank(log.getException())){
				return;
			}
			// 保存日志信息
			//log.preInsert();
			logAccessDao.insert(log);
		}
	}

	/**
	 * 获取菜单名称路径（如：系统设置-机构用户-用户管理-编辑）
	 */
	/*public static String getMenuNamePath(String requestUri, String permission){
		String href = StringUtils.substringAfter(requestUri, Global.getAdminPath());
		@SuppressWarnings("unchecked")
		Map<String, String> menuMap = (Map<String, String>)CacheUtils.get(CACHE_MENU_NAME_PATH_MAP);
		if (menuMap == null){
			menuMap = Maps.newHashMap();
			List<Menu> menuList = MenuDao.findAllList(new Menu());
			for (Menu menu : menuList){
				// 获取菜单名称路径（如：系统设置-机构用户-用户管理-编辑）
				String namePath = "";
				if (menu.getParentIds() != null){
					List<String> namePathList = Lists.newArrayList();
					for (String id : StringUtils.split(menu.getParentIds(), ",")){
						if (Menu.getRootId().equals(id)){
							continue; // 过滤跟节点
						}
						for (Menu m : menuList){
							if (m.getId().equals(id)){
								namePathList.add(m.getName());
								break;
							}
						}
					}
					namePathList.add(menu.getName());
					namePath = StringUtils.join(namePathList, "-");
				}
				// 设置菜单名称路径
				if (StringUtils.isNotBlank(menu.getHref())){
					menuMap.put(menu.getHref(), namePath);
				}else if (StringUtils.isNotBlank(menu.getPermission())){
					for (String p : StringUtils.split(menu.getPermission())){
						menuMap.put(p, namePath);
					}
				}
				
			}
			CacheUtils.put(CACHE_MENU_NAME_PATH_MAP, menuMap);
		}
		String menuNamePath = menuMap.get(href);
		if (menuNamePath == null){
			for (String p : StringUtils.split(permission)){
				menuNamePath = menuMap.get(p);
				if (StringUtils.isNotBlank(menuNamePath)){
					break;
				}
			}
			if (menuNamePath == null){
				return "";
			}
		}
		return menuNamePath;
	}*/

	
}
