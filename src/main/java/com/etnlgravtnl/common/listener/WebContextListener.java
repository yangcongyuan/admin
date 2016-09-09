package com.etnlgravtnl.common.listener;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * Created by guozheng on 2015/4/20.
 */
public class WebContextListener extends ContextLoaderListener {

    @Override
    public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
        if (!SystemService.printKeyLoadMessage()){
            return null;
        }
        return super.initWebApplicationContext(servletContext);
    }

}
