package com.etnlgravtnl.system.webservice;

import com.etnlgravtnl.system.entity.LogAccess;
import com.etnlgravtnl.system.entity.ResultAPI;
import com.etnlgravtnl.system.service.LogAccessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Component
@Path("/log")
public class LogAccessController {

    @Autowired
    private LogAccessServiceImpl logAccessService;

    @GET
    @Path("get")
    public ResultAPI get(){

        ResultAPI resultAPI = new ResultAPI();


        return resultAPI;
    }


    @POST
    @Path("pageList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultAPI pageList(@BeanParam LogAccess logAccess){

        ResultAPI resultAPI = new ResultAPI();
        try {
            resultAPI.setMsg(logAccessService.findPageList(logAccess));
            resultAPI.setAccess_result("SUCCESS");
        }catch (RuntimeException e){
            resultAPI.setAccess_result("FAILURE");
            resultAPI.setMsg(e.getMessage());
        }catch (Exception e){
            resultAPI.setAccess_result("FAILURE");
            resultAPI.setMsg("服务器异常");
        }
        return resultAPI;
    }


}
