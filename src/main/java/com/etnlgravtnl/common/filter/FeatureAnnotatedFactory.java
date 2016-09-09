package com.etnlgravtnl.common.filter;

import com.etnlgravtnl.common.persistence.annotation.TokenCheck;
import org.glassfish.jersey.server.model.AnnotatedMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Path;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @Name:
 * @Author: junz（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2016-06-20（创建日期）
 * @Description:
 * 容器初始化时，获取注解等资源
 */
public class FeatureAnnotatedFactory implements DynamicFeature
{
    private static final Logger logger = LoggerFactory.getLogger(FeatureAnnotatedFactory.class);

    //需验证token的URL集合，@TokenCheck
    private static  List<String> tokenCkList;
    //单利模式 线程安全保证唯一性
    public synchronized  static List<String> gettokenCkListInstance() {
        if (tokenCkList == null) {
            tokenCkList =  new ArrayList<String>();
        }
        return tokenCkList;
    }

    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-19（创建日期）
     * @Description:
     * annotated 生产工厂
     */
    public void configure(ResourceInfo resourceInfo, FeatureContext context)
    {
        produceTokenCkList(resourceInfo,context);

    }
    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-19（创建日期）
     * @Description:
     * 生产需检查token的url集合
     */
    public void produceTokenCkList(ResourceInfo resourceInfo, FeatureContext context)
    {
        //获取资源中注解的函数集合
        AnnotatedMethod am = new AnnotatedMethod(resourceInfo.getResourceMethod());

        //判断该注解是否使用了@TokenCheck注解
        if(am.isAnnotationPresent(TokenCheck.class))
        {
            //取方法@path
            Path methodPath = resourceInfo.getResourceMethod().getAnnotation(Path.class);
            //取类@path
            Path classPath = resourceInfo.getResourceClass().getAnnotation(Path.class);
            //拼接方法访问url
            String pathText=classPath.value()+"/"+methodPath.value();
            //如果类为根（/） 访问，替换// 为/
            pathText=pathText.indexOf("//")==-1?pathText:pathText.replaceAll("//","/");

            if (logger.isDebugEnabled()){
                logger.debug("发现使用@TokenCheck 注解函数：{}  访问该函数的URL地址为：{}  ",am.getMethod(),pathText
                        );
            }
            //塞入单例 token检查序列
            tokenCkList= gettokenCkListInstance();
            tokenCkList.add(pathText);
            //context.register(new AuthorizationRequestFilter(tokenCkList));
        }
    }
}
