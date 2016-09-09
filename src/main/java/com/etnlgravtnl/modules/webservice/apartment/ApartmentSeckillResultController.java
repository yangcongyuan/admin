package com.etnlgravtnl.modules.webservice.apartment;

import com.alibaba.fastjson.JSON;
import com.etnlgravtnl.modules.service.apartment.ApartmentSeckillService;
import com.etnlgravtnl.modules.service.apartment.ApartmentSeckillSuccessService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

@Path("/apartment/seckill/result")
public class ApartmentSeckillResultController
{
    @Autowired
    private ApartmentSeckillSuccessService apartmentSeckillSuccessService;

	@Path("/list")
    @POST
    @SuppressWarnings("unchecked")
    public String queryCommunity(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        Map<String, Object> result = apartmentSeckillSuccessService.getList(args);
        return JSON.toJSONString(result);
    }

    @Path("/pay")
    @POST
    @SuppressWarnings("unchecked")
    public String edit(String json)
    {
        Map<String, Object> args = JSON.parseObject(json, Map.class);
        apartmentSeckillSuccessService.updateStatusByApartId(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errcode", "0");
        return JSON.toJSONString(result);
    }

}













