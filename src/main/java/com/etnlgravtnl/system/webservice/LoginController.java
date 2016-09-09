package com.etnlgravtnl.system.webservice;


import com.alibaba.fastjson.JSON;
import com.etnlgravtnl.common.cache.redis.JedisAlterRedisManager;
import com.etnlgravtnl.common.config.Global;
import com.etnlgravtnl.common.exception.Constant.WebExceptionType;
import com.etnlgravtnl.common.exception.MapperSupport.WebActionException;
import com.etnlgravtnl.common.security.JwtUtil;
import com.etnlgravtnl.common.session.VirtualSession;
import com.etnlgravtnl.common.session.VirtualSessionManager;
import com.etnlgravtnl.common.utils.DateUtils;
import com.etnlgravtnl.common.utils.StringUtils;
import com.etnlgravtnl.system.entity.*;
import com.etnlgravtnl.system.service.AdminUserService;
import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.*;

/**
 *登录 Controller
 *Controller
 * @author Junz
 * @version 2015-05-19
 */
@Path("/")
public class LoginController {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private JedisAlterRedisManager jedisAlterRedisManager;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public  Viewable  welcome2System()
    {
        return new Viewable("/WEB-INF/views/modules/system/login", null);
    }


    @POST
    @Path("doLogin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String doLogin(AdminUser user) {

        user = adminUserService.get(user);
        if (user == null) {
            throw  new WebActionException(WebExceptionType.LOGINNOTFUND,user);
        }
        //验证登录等相关操作，暂时省略，等认证以及权限机制搞定再去处理
        //.........
        if (securityContext.getUserPrincipal()==null)
        {
            //登录成功，如果没有mainToken生成mainToken，生成用户虚拟session
            String mainTokenValue = JwtUtil.generateMainToken(String.valueOf(user.getId()));
            Token mainToken=new MainToken();
            mainToken.setValue(mainTokenValue);
            mainToken.setExpire(JwtUtil.getTokenExpire(mainTokenValue));
            user.setMainToken(mainToken);

        }else
        {
            user.setMainToken(((AdminUser)securityContext.getUserPrincipal()).getMainToken());
        }
        //登录成功，如果没有accessToken生成accessToken，生成用户虚拟session
        String accessTokenValue = JwtUtil.generateAccessToken(String.valueOf(user.getId()),user.getMainToken().getValue());
        Token accessToken=new MainToken();
        accessToken.setValue(accessTokenValue);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, Integer.parseInt(Global.loader.getProperty("accesstoken_expiry").toString()));
        accessToken.setExpire(calendar.getTime());
        user.setAccessToken(accessToken);

        //缓存中装入user，键为accessTokenValue
        long userInCacheExpire= DateUtils.getTimeInterval(user.getAccessToken().getExpire(),new Date());
        jedisAlterRedisManager.setKeyLive(accessTokenValue,(int)userInCacheExpire/1000,JSON.toJSONString(user));

        //缓存中装入token 键为maintokenvalue
        Map tokenMap=new HashMap();
        tokenMap.put("mainToken",user.getMainToken());
        tokenMap.put("accessToken",user.getAccessToken());
        long tokenInCacheExpire= DateUtils.getTimeInterval(JwtUtil.getTokenExpire(user.getMainToken().getValue()),new Date());
        jedisAlterRedisManager.setKeyLive(user.getMainToken().getValue(),(int)tokenInCacheExpire/1000,JSON.toJSONString(tokenMap));
        return JSON.toJSONString(user);
    }

    @POST
    @Path("doLogout")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void doLogout() {
        AdminUser user=(AdminUser)securityContext.getUserPrincipal();
        String accessToken=user.getAccessToken().getValue();
        jedisAlterRedisManager.removeValue(accessToken);
    }

}
