package com.etnlgravtnl.modules.webservice.home;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.etnlgravtnl.modules.service.home.HomeService;

@Path("/home")
public class HomeController 
{
	@Autowired
	private HomeService homeService;
	
	@Path("/queryBanner")
    @POST
    @SuppressWarnings("unchecked")
    public String queryBanner(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = homeService.queryBanner(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addBanner")
    @POST
    @SuppressWarnings("unchecked")
    public String addBanner(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.addBanner(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getBanner")
    @POST
    @SuppressWarnings("unchecked")
    public String getBanner(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = homeService.getBanner(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updBanner")
    @POST
    @SuppressWarnings("unchecked")
    public String updBanner(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.updBanner(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delBanner")
    @POST
    @SuppressWarnings("unchecked")
    public String delBanner(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.delBanner(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/queryIntroduce")
    @POST
    @SuppressWarnings("unchecked")
    public String queryIntroduce(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = homeService.queryIntroduce(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addIntroduce")
    @POST
    @SuppressWarnings("unchecked")
    public String addIntroduce(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.addIntroduce(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getIntroduce")
    @POST
    @SuppressWarnings("unchecked")
    public String getIntroduce(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = homeService.getIntroduce(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updIntroduce")
    @POST
    @SuppressWarnings("unchecked")
    public String updIntroduce(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.updIntroduce(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delIntroduce")
    @POST
    @SuppressWarnings("unchecked")
    public String delIntroduce(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.delIntroduce(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/queryLayoutRecommend")
    @POST
    @SuppressWarnings("unchecked")
    public String queryLayoutRecommend(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = homeService.queryLayoutRecommend(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addLayoutRecommend")
    @POST
    @SuppressWarnings("unchecked")
    public String addLayoutRecommend(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.addLayoutRecommend(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getLayoutRecommend")
    @POST
    @SuppressWarnings("unchecked")
    public String getLayoutRecommend(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = homeService.getLayoutRecommend(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updLayoutRecommend")
    @POST
    @SuppressWarnings("unchecked")
    public String updLayoutRecommend(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.updLayoutRecommend(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delLayoutRecommend")
    @POST
    @SuppressWarnings("unchecked")
    public String delLayoutRecommend(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.delLayoutRecommend(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/queryLayoutOverview")
    @POST
    @SuppressWarnings("unchecked")
    public String queryLayoutOverview(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = homeService.queryLayoutOverview(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addLayoutOverview")
    @POST
    @SuppressWarnings("unchecked")
    public String addLayoutOverview(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.addLayoutOverview(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getLayoutOverview")
    @POST
    @SuppressWarnings("unchecked")
    public String getLayoutOverview(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = homeService.getLayoutOverview(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updLayoutOverview")
    @POST
    @SuppressWarnings("unchecked")
    public String updLayoutOverview(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.updLayoutOverview(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delLayoutOverview")
    @POST
    @SuppressWarnings("unchecked")
    public String delLayoutOverview(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.delLayoutOverview(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/queryInformation")
    @POST
    @SuppressWarnings("unchecked")
    public String queryInformation(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = homeService.queryInformation(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addInformation")
    @POST
    @SuppressWarnings("unchecked")
    public String addInformation(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.addInformation(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getInformation")
    @POST
    @SuppressWarnings("unchecked")
    public String getInformation(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = homeService.getInformation(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updInformation")
    @POST
    @SuppressWarnings("unchecked")
    public String updInformation(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.updInformation(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delInformation")
    @POST
    @SuppressWarnings("unchecked")
    public String delInformation(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        homeService.delInformation(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
}











