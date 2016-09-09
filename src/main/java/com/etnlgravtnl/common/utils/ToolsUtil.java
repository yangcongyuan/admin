package com.etnlgravtnl.common.utils;

import com.etnlgravtnl.common.filter.FeatureAnnotatedFactory;
import org.glassfish.jersey.server.ContainerRequest;

import javax.ws.rs.container.ContainerRequestContext;
import java.util.List;

/**
 * Created by Administrator on 2016/7/1.
 */
public class ToolsUtil {
    public  static  boolean tokenWetherCk(ContainerRequestContext requestContext)
    {
        //获取需检验的token url集合
        List<String> tokenCkList= FeatureAnnotatedFactory.gettokenCkListInstance();

        //得到访问的方法 例如GET,POST
        String method = requestContext.getMethod().toLowerCase();
        //得到访问路径
        String path = "/"+((ContainerRequest) requestContext).getPath(true).toLowerCase();

        //判断是否需要跳过验证token，默认为false
        boolean skipCheck=true;
        for(String url:tokenCkList)
        {
            if(path.equals(url.toLowerCase()))
            {
                skipCheck=false;
            }
        }
        return skipCheck;
    }
}
