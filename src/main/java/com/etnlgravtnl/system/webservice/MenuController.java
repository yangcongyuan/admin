package com.etnlgravtnl.system.webservice;


import com.etnlgravtnl.common.web.BaseController;

import com.etnlgravtnl.system.entity.AdminUser;
import com.etnlgravtnl.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;


@Component
@Path("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @Context
    SecurityContext securityContext;

    @POST
    @Path("getMineMenuTree")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getMineMenuTree( )
    {
        AdminUser user=(AdminUser)securityContext.getUserPrincipal();
        String htmlText=menuService.getMenuHtmlText4User(String.valueOf(user.getId()));
        return htmlText;
    }


}
