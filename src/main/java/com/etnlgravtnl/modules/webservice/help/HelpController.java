package com.etnlgravtnl.modules.webservice.help;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.etnlgravtnl.modules.service.help.HelpService;

@Path("/help")
public class HelpController 
{
	@Autowired
	private HelpService helpService;
	
	@Path("/queryQuestionType")
    @POST
    @SuppressWarnings("unchecked")
    public String queryQuestionType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = helpService.queryQuestionType(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addQuestionType")
    @POST
    @SuppressWarnings("unchecked")
    public String addQuestionType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        helpService.addQuestionType(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getQuestionType")
    @POST
    @SuppressWarnings("unchecked")
    public String getQuestionType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = helpService.getQuestionType(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updQuestionType")
    @POST
    @SuppressWarnings("unchecked")
    public String updQuestionType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        helpService.updQuestionType(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delQuestionType")
    @POST
    @SuppressWarnings("unchecked")
    public String delQuestionType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        helpService.delQuestionType(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/queryQuestionTypeSel")
    @POST
    public String queryQuestionTypeSel()
    {
		List<Map<String, Object>> list = helpService.queryQuestionTypeSel();
        return JSON.toJSONString(list);
    }
	
	@Path("/queryQuestion")
    @POST
    @SuppressWarnings("unchecked")
    public String queryQuestion(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = helpService.queryQuestion(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addQuestion")
    @POST
    @SuppressWarnings("unchecked")
    public String addQuestion(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        helpService.addQuestion(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getQuestion")
    @POST
    @SuppressWarnings("unchecked")
    public String getQuestion(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = helpService.getQuestion(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updQuestion")
    @POST
    @SuppressWarnings("unchecked")
    public String updQuestion(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        helpService.updQuestion(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delQuestion")
    @POST
    @SuppressWarnings("unchecked")
    public String delQuestion(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        helpService.delQuestion(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
}











