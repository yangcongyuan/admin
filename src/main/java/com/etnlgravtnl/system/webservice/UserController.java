package com.etnlgravtnl.system.webservice;

import com.alibaba.fastjson.JSON;
import com.etnlgravtnl.common.web.BaseController;
import com.etnlgravtnl.system.entity.AdminUser;
import com.etnlgravtnl.system.entity.LogAccess;
import com.etnlgravtnl.system.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Component
@Path("/user")
public class UserController extends BaseController {

    @Autowired
    private AdminUserService adminUserService;


    @POST
    @Path("getUserBaseInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getUserBaseInfo( ){
        AdminUser user= (AdminUser) securityContext.getUserPrincipal();
        return JSON.toJSONString(user);
    }


}
