package com.etnlgravtnl.modules.webservice.news;

import com.alibaba.fastjson.JSON;
import com.etnlgravtnl.modules.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import java.util.HashMap;
import java.util.Map;

@Path("/new")
public class NewsController {


    @Autowired
    private NewsService newsService;

    @Path("/list")
    @POST
    @SuppressWarnings("unchecked")
    public String list(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result =newsService.getAllList(args);
        return JSON.toJSONString(result);
    }

    @Path("/add")
    @POST
    @SuppressWarnings("unchecked")
    public String add(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        newsService.save(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }

    @Path("/update")
    @POST
    @SuppressWarnings("unchecked")
    public String edit(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        newsService.update(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }

    @Path("/get")
    @POST
    @SuppressWarnings("unchecked")
    public String get(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result =newsService.get(args);
        return JSON.toJSONString(result);
    }

    @Path("/del")
    @POST
    @SuppressWarnings("unchecked")
    public String del(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        newsService.del(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
}










