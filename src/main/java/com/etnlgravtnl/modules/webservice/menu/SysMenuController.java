package com.etnlgravtnl.modules.webservice.menu;

import com.alibaba.fastjson.JSON;
import com.etnlgravtnl.modules.service.menu.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/8/30.
 */

@Path(value = "/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @Path("getMenu")
    @POST
    @SuppressWarnings("unchecked")
    public String getMenu(String json){
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String,Object> result=sysMenuService.getMenu(args);
        return JSON.toJSONString(result);
    }
    @Path("deleteById")
    @POST
    @SuppressWarnings("unchecked")
    public String deleteById(String json){
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        sysMenuService.deleteById(args);
        Map<String,Object> result=new HashMap<String, Object>();
        result.put("errcode","0");
        return JSON.toJSONString(result);
    }

    @Path("updateByPrimaryKey")
    @POST
    @SuppressWarnings("unchecked")
    public String updateByPrimaryKey(String json){
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        sysMenuService.updateByPrimaryKey(args);
        Map<String,Object> result=new HashMap<String, Object>();
        result.put("errcode","0");
        return JSON.toJSONString(result);
    }

    @Path("selectById")
    @POST
    @SuppressWarnings("unchecked")
    public String selectById(String json){
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String,Object> result=sysMenuService.selectById(args);
        return JSON.toJSONString(result);
    }

    @Path("insert")
    @POST
    @SuppressWarnings("unchecked")
    public String insert(String json){
        Map<String,Object> args=JSON.parseObject(json,Map.class);
        sysMenuService.insert(args);
        Map<String,Object> result=new HashMap<String, Object>();
        result.put("errcode","0");
        return JSON.toJSONString(result);
    }

    @Path("getSecondaryMenu")
    @POST
    public String getSecondaryMenu(){
        List<Map<String,Object>> list=sysMenuService.getSecondaryMenu();
        return JSON.toJSONString(list);
    }


}
