package com.etnlgravtnl.modules.webservice.user;

import com.alibaba.fastjson.JSON;
import com.etnlgravtnl.modules.service.news.NewsService;
import com.etnlgravtnl.modules.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

@Path("/stage/user")
public class StageUserController {


    @Autowired
    private UserService userService;

    @Path("/list")
    @POST
    @SuppressWarnings("unchecked")
    public String list(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result =userService.getAllList(args);
        return JSON.toJSONString(result);
    }
}










