package com.etnlgravtnl.common.filter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etnlgravtnl.common.cache.redis.JedisAlterRedisManager;
import com.etnlgravtnl.common.config.Global;
import com.etnlgravtnl.common.exception.Constant.WebExceptionType;
import com.etnlgravtnl.common.exception.MapperSupport.WebActionException;
import com.etnlgravtnl.common.security.JwtUtil;

import com.etnlgravtnl.common.utils.DateUtils;
import com.etnlgravtnl.system.entity.AdminUser;
import com.etnlgravtnl.system.entity.MainToken;
import com.etnlgravtnl.system.entity.Token;
import com.etnlgravtnl.system.service.AdminUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Name:
 * @Author: junz（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2016-06-19（创建日期）
 * @Description:
 * token:{
			mainToken:{
			value:"",
			expire:"",
			subject:"",
			}
			accessToken:{
			value:"",
			expire:"",
			subject:"",
			mainToken:"",
			}
	}
 * 权限认证过滤器
 */
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationRequestFilter implements ContainerRequestFilter
{
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationRequestFilter.class);
	private List<String>tokenCkList;
	@Inject
	javax.inject.Provider<UriInfo> uriInfo;

	@Autowired
	AdminUserService adminUserService;

	@Autowired
	JedisAlterRedisManager jedisAlterRedisManager;

	public AuthorizationRequestFilter(List<String> tokenCkList)
	{
		this.tokenCkList = tokenCkList;
	}
	public AuthorizationRequestFilter(){};

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException
	{
		String path = requestContext.getUriInfo().getRequestUri().toString().toLowerCase();
		if(path.endsWith("/rest/")||path.endsWith("/rest"))
		{
			return;
		}
		/*//判断该路径是否需要check token
		if(ToolsUtil.tokenWetherCk(requestContext))
			return;*/
		//获取头信息中的token
		String mainTokenText=requestContext.getHeaderString(Global.loader.getProperty("maintoken_name"));
		String accessTokenText=requestContext.getHeaderString(Global.loader.getProperty("accesstoken_name"));
		//如果token为空抛出403 拒绝 如果是登录访问且mainToken 为空，则抛出拒绝异常403
		if(path.indexOf("dologin")!=-1)
		{
			AdminUser user=new AdminUser();
			if(mainTokenText!=null)
			{
				Token mainToken=new MainToken();
				mainToken.setValue(mainTokenText);
				mainToken.setExpire(JwtUtil.getTokenExpire(mainTokenText));
				user.setMainToken(mainToken);
				//检查mainToken
				ckMainToken(mainTokenText,mainTokenText);
				//注入SecurityContext
				requestContext.setSecurityContext(new SecurityContextAuthorizer(uriInfo,user, new String[]{"user"}));
			}

			//判断用户在已经登录且session未失效时，重复登录时的处理
			if(accessTokenText!=null) {
				//检查accessToken
				ckAccessToken(accessTokenText,mainTokenText);
				return;
			}
		}else {
			//检查accessToken
			ckAccessToken(accessTokenText,mainTokenText);
			AdminUser user=JSON.parseObject(jedisAlterRedisManager.getValue(accessTokenText).toString(),AdminUser.class);

			//注入SecurityContext
			requestContext.setSecurityContext(new SecurityContextAuthorizer(uriInfo,user, new String[]{"user"}));
			//刷新Access 失效日期
			Date newExpire=DateUtils.getDate4Later(Integer.parseInt(Global.loader.getProperty("accesstoken_expiry").toString()));
			user.getAccessToken().setExpire(newExpire);

			//刷新缓存中user的失效日期
			long userInCacheExpire= DateUtils.getTimeInterval(user.getAccessToken().getExpire(),new Date());
			jedisAlterRedisManager.setKeyLive(accessTokenText,(int)userInCacheExpire/1000,JSON.toJSONString(user));
		}

	}

	private void ckMainToken(String mainTokenText,String cacheKey)
	{
		String mainTokenInCache=getTokenInCache("mainToken",cacheKey).getValue();
		if (mainTokenText!=null)
		{
			//对比上传你的mainToken跟cache中存放的token
			if(!mainTokenInCache.equals(mainTokenText))
			{
				throw new WebActionException(HttpStatus.FORBIDDEN,null, WebExceptionType.FORBIDDEN,null);//拒绝
			}
			//验证上传token是否有效
			if(!JwtUtil.isValid(mainTokenText))
			{
				throw new WebActionException(HttpStatus.FORBIDDEN,null, WebExceptionType.FORBIDDEN,null);//拒绝
			}

		}
	}
	private void ckAccessToken(String accessTokenText,String cacheKey){
		String mainTokenInCache=getTokenInCache("mainToken",cacheKey).getValue();
		Token accessTokenInCache=getTokenInCache("accessToken",cacheKey);
		if (accessTokenText!=null)
		{
			String accessTokenInCacheVal=accessTokenInCache.getValue();
			Date accessTokenExpire=accessTokenInCache.getExpire();
			if(!JwtUtil.isValid(accessTokenText))
			{
				throw new WebActionException(HttpStatus.UNAUTHORIZED,null, WebExceptionType.UNAUTHORIZED,null);//拒绝
			}else {
				//验证accessToken里存放的mainToken与服务器内存放的是否一致
				String mainTokenInAccessToken=JwtUtil.getMainTokenInAccessToken(accessTokenInCacheVal);//反解出mainToken
				if(!mainTokenInCache.equals(mainTokenInAccessToken))
				{
					throw new WebActionException(HttpStatus.FORBIDDEN,null, WebExceptionType.FORBIDDEN,null);//拒绝
				}
				//验证access token是否失效
				if((accessTokenExpire.getTime()<(new Date()).getTime()))
				{
					throw new WebActionException(HttpStatus.UNAUTHORIZED,null, WebExceptionType.UNAUTHORIZED,null);//拒绝
				}

			}
		}
	}
	private Token getTokenInCache(String tokenName, String cacheKey){
		try {
			//取cache中存放的token
			String tokenCacheStr=jedisAlterRedisManager.getValue(cacheKey);
			JSONObject tokenCache = JSON.parseObject(tokenCacheStr);
			//取cache中存放的mainToken
			Token mainTokenJson= JSON.parseObject(tokenCache.get(tokenName).toString(),Token.class);
			return mainTokenJson;
		}catch (Exception ex)
		{
			throw new WebActionException(HttpStatus.FORBIDDEN,null, WebExceptionType.FORBIDDEN,null);//拒绝
		}
	}



}