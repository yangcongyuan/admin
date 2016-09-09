package com.etnlgravtnl.modules.webservice.apartment;

import com.alibaba.fastjson.JSON;
import com.etnlgravtnl.modules.service.apartment.ApartmentSeckillService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

@Path("/apartment/seckill")
public class ApartmentSeckillController
{
	@Autowired
	private ApartmentSeckillService apartmentSeckillService;

	@Path("/list")
    @POST
    @SuppressWarnings("unchecked")
    public String queryCommunity(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentSeckillService.getList(args);
        return JSON.toJSONString(result);
    }

    @Path("/get")
    @POST
    @SuppressWarnings("unchecked")
    public String get(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentSeckillService.get(args);
        return JSON.toJSONString(result);
    }

    @Path("/add")
    @POST
    @SuppressWarnings("unchecked")
    public String add(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentSeckillService.add(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }
    @Path("/edit")
    @POST
    @SuppressWarnings("unchecked")
    public String edit(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentSeckillService.edit(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }

    @Path("/up")
    @POST
    @SuppressWarnings("unchecked")
    public String up(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentSeckillService.editStatusUp(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }

    @Path("/down")
    @POST
    @SuppressWarnings("unchecked")
    public String down(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentSeckillService.editStatusDown(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }

}













