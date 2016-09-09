package com.etnlgravtnl.modules.webservice.apartment;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.etnlgravtnl.modules.service.apartment.ApartmentService;

@Path("/apartment")
public class ApartmentController 
{
	@Autowired
	private ApartmentService apartmentService;
	
	@Path("/queryCommunity")
    @POST
    @SuppressWarnings("unchecked")
    public String queryCommunity(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.queryCommunity(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addCommunity")
    @POST
    @SuppressWarnings("unchecked")
    public String addCommunity(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.addCommunity(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getCommunity")
    @POST
    @SuppressWarnings("unchecked")
    public String getCommunity(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.getCommunity(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updCommunity")
    @POST
    @SuppressWarnings("unchecked")
    public String updCommunity(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.updCommunity(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delCommunity")
    @POST
    @SuppressWarnings("unchecked")
    public String delCommunity(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.delCommunity(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/queryCommunitySel")
    @POST
    public String queryCommunitySel()
    {
		List<Map<String, Object>> list = apartmentService.queryCommunitySel();
        return JSON.toJSONString(list);
    }
	
	@Path("/queryApartmentType")
    @POST
    @SuppressWarnings("unchecked")
    public String queryApartmentType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.queryApartmentType(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addApartmentType")
    @POST
    @SuppressWarnings("unchecked")
    public String addApartmentType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.addApartmentType(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getApartmentType")
    @POST
    @SuppressWarnings("unchecked")
    public String getApartmentType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.getApartmentType(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updApartmentType")
    @POST
    @SuppressWarnings("unchecked")
    public String updApartmentType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.updApartmentType(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delApartmentType")
    @POST
    @SuppressWarnings("unchecked")
    public String delApartmentType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.delApartmentType(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/queryApartmentArea")
    @POST
    @SuppressWarnings("unchecked")
    public String queryApartmentArea(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.queryApartmentArea(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addApartmentArea")
    @POST
    @SuppressWarnings("unchecked")
    public String addApartmentArea(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.addApartmentArea(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getApartmentArea")
    @POST
    @SuppressWarnings("unchecked")
    public String getApartmentArea(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.getApartmentArea(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updApartmentArea")
    @POST
    @SuppressWarnings("unchecked")
    public String updApartmentArea(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.updApartmentArea(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delApartmentArea")
    @POST
    @SuppressWarnings("unchecked")
    public String delApartmentArea(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.delApartmentArea(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/queryApartmentHouseType")
    @POST
    @SuppressWarnings("unchecked")
    public String queryApartmentHouseType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.queryApartmentHouseType(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addApartmentHouseType")
    @POST
    @SuppressWarnings("unchecked")
    public String addApartmentHouseType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.addApartmentHouseType(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getApartmentHouseType")
    @POST
    @SuppressWarnings("unchecked")
    public String getApartmentHouseType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.getApartmentHouseType(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updApartmentHouseType")
    @POST
    @SuppressWarnings("unchecked")
    public String updApartmentHouseType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.updApartmentHouseType(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delApartmentHouseType")
    @POST
    @SuppressWarnings("unchecked")
    public String delApartmentHouseType(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.delApartmentHouseType(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/queryApartmentTypeSel")
    @POST
    public String queryApartmentTypeSel()
    {
		List<Map<String, Object>> list = apartmentService.queryApartmentTypeSel();
        return JSON.toJSONString(list);
    }
	
	@Path("/queryApartmentAreaSel")
    @POST
    public String queryApartmentAreaSel()
    {
		List<Map<String, Object>> list = apartmentService.queryApartmentAreaSel();
        return JSON.toJSONString(list);
    }
	
	@Path("/queryApartmentHouseTypeSel")
    @POST
    public String queryApartmentHouseTypeSel()
    {
		List<Map<String, Object>> list = apartmentService.queryApartmentHouseTypeSel();
        return JSON.toJSONString(list);
    }
	
	@Path("/queryModelSel")
    @POST
    public String queryModelSel()
    {
		List<Map<String, Object>> list = apartmentService.queryModelSel();
        return JSON.toJSONString(list);
    }
	
	@Path("/queryLayoutSel")
    @POST
    public String queryLayoutSel()
    {
		List<Map<String, Object>> list = apartmentService.queryLayoutSel();
        return JSON.toJSONString(list);
    }
	
	@Path("/queryEnvironmentSel")
    @POST
    public String queryEnvironmentSel()
    {
		List<Map<String, Object>> list = apartmentService.queryEnvironmentSel();
        return JSON.toJSONString(list);
    }
	
	@Path("/queryApartment")
    @POST
    @SuppressWarnings("unchecked")
    public String queryApartment(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.queryApartment(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addApartment")
    @POST
    @SuppressWarnings("unchecked")
    public String addApartment(String json) throws ParseException
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.addApartment(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getApartment")
    @POST
    @SuppressWarnings("unchecked")
    public String getApartment(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.getApartment(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updApartment")
    @POST
    @SuppressWarnings("unchecked")
    public String updApartment(String json) throws ParseException
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.updApartment(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delApartment")
    @POST
    @SuppressWarnings("unchecked")
    public String delApartment(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.delApartment(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/queryModel")
    @POST
    @SuppressWarnings("unchecked")
    public String queryModel(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.queryModel(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addModel")
    @POST
    @SuppressWarnings("unchecked")
    public String addModel(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.addModel(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getModel")
    @POST
    @SuppressWarnings("unchecked")
    public String getModel(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.getModel(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updModel")
    @POST
    @SuppressWarnings("unchecked")
    public String updModel(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.updModel(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delModel")
    @POST
    @SuppressWarnings("unchecked")
    public String delModel(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.delModel(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/queryLayout")
    @POST
    @SuppressWarnings("unchecked")
    public String queryLayout(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.queryLayout(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addLayout")
    @POST
    @SuppressWarnings("unchecked")
    public String addLayout(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.addLayout(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getLayout")
    @POST
    @SuppressWarnings("unchecked")
    public String getLayout(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.getLayout(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updLayout")
    @POST
    @SuppressWarnings("unchecked")
    public String updLayout(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.updLayout(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delLayout")
    @POST
    @SuppressWarnings("unchecked")
    public String delLayout(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.delLayout(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/queryEnvironment")
    @POST
    @SuppressWarnings("unchecked")
    public String queryEnvironment(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.queryEnvironment(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addEnvironment")
    @POST
    @SuppressWarnings("unchecked")
    public String addEnvironment(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.addEnvironment(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getEnvironment")
    @POST
    @SuppressWarnings("unchecked")
    public String getEnvironment(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.getEnvironment(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updEnvironment")
    @POST
    @SuppressWarnings("unchecked")
    public String updEnvironment(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.updEnvironment(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delEnvironment")
    @POST
    @SuppressWarnings("unchecked")
    public String delEnvironment(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.delEnvironment(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/queryHousePrice")
    @POST
    @SuppressWarnings("unchecked")
    public String queryHousePrice(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.queryHousePrice(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/addHousePrice")
    @POST
    @SuppressWarnings("unchecked")
    public String addHousePrice(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.addHousePrice(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/getHousePrice")
    @POST
    @SuppressWarnings("unchecked")
    public String getHousePrice(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentService.getHousePrice(args);
        return JSON.toJSONString(result);
    }
	
	@Path("/updHousePrice")
    @POST
    @SuppressWarnings("unchecked")
    public String updHousePrice(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.updHousePrice(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
	
	@Path("/delHousePrice")
    @POST
    @SuppressWarnings("unchecked")
    public String delHousePrice(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentService.delHousePrice(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
}













