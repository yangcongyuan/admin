package com.etnlgravtnl.system.interceptor;


import com.etnlgravtnl.common.utils.LogUtils;
import org.glassfish.jersey.message.internal.HeaderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.*;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@PreMatching
public class AirLogFilter implements ContainerRequestFilter, ClientRequestFilter, ContainerResponseFilter, ClientResponseFilter {



    private static final Logger LOGGER = LoggerFactory.getLogger(AirLogFilter.class);
    private static final String NOTIFICATION_PREFIX = "* ";
    private static final String SERVER_REQUEST = "> ";
    private static final String SERVER_RESPONSE = "< ";
    private static final String CLIENT_REQUEST = "/ ";
    private static final String CLIENT_RESPONSE = "\\ ";
    private static final AtomicLong logSequence = new AtomicLong(0);
    private static final ThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<Long>("ThreadLocal StartTime");

    private StringBuilder prefixId(StringBuilder b, long id) {
        b.append(Long.toString(id)).append(" ");
        return b;
    }

    private void printRequestLine(final String prefix, StringBuilder b, long id, String method, URI uri) {
        prefixId(b, id).append(NOTIFICATION_PREFIX).append("AirLog - Request received on thread ").append(Thread.currentThread().getName()).append("\n");
        prefixId(b, id).append(prefix).append(method).append(" ").append(uri.toASCIIString()).append("\n");
    }

    private void printResponseLine(final String prefix, StringBuilder b, long id, int status) {
        prefixId(b, id).append(NOTIFICATION_PREFIX).append("AirLog - Response received on thread ").append(Thread.currentThread().getName()).append("\n");
        prefixId(b, id).append(prefix).append(Integer.toString(status)).append("\n");
    }

    private void printPrefixedHeaders(final String prefix, StringBuilder b, long id, MultivaluedMap<String, String> headers) {
        for (Map.Entry<String, List<String>> e : headers.entrySet()) {
            List<?> val = e.getValue();
            String header = e.getKey();

            if (val.size() == 1) {
                prefixId(b, id).append(prefix).append(header).append(": ").append(val.get(0)).append("\n");
            } else {
                StringBuilder sb = new StringBuilder();
                boolean add = false;
                for (Object s : val) {
                    if (add) {
                        sb.append(',');
                    }
                    add = true;
                    sb.append(s);
                }
                prefixId(b, id).append(prefix).append(header).append(": ").append(sb.toString()).append("\n");
            }
        }
    }

    @Override
    public void filter(ClientRequestContext context) throws IOException {
        long id = logSequence.incrementAndGet();
        StringBuilder b = new StringBuilder();
        printRequestLine(CLIENT_REQUEST, b, id, context.getMethod(), context.getUri());
        printPrefixedHeaders(CLIENT_REQUEST, b, id, /*HeadersFactory*/HeaderUtils.asStringHeaders(context.getHeaders()));
        LOGGER.info(b.toString());
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        long id = logSequence.incrementAndGet();
        StringBuilder b = new StringBuilder();
        printResponseLine(CLIENT_RESPONSE, b, id, responseContext.getStatus());
        printPrefixedHeaders(CLIENT_RESPONSE, b, id, responseContext.getHeaders());
        LOGGER.info(b.toString());
    }

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        if (LOGGER.isDebugEnabled()){
            long beginTime = System.currentTimeMillis();//1、开始时间
            startTimeThreadLocal.set(beginTime);		//线程绑定变量（该数据只有当前请求的线程可见）
            LOGGER.debug("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS")
                    .format(beginTime), context.getUriInfo().getRequestUri());
        }
        /*long id = logSequence.incrementAndGet();
        StringBuilder b = new StringBuilder();
        printRequestLine(SERVER_REQUEST, b, id, context.getMethod(), context.getUriInfo().getRequestUri());
        printPrefixedHeaders(SERVER_REQUEST, b, id, context.getHeaders());
        LOGGER.info(b.toString());*/
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis(); 	//2、结束时间
        int timeCost= (int) (endTime - beginTime);
        // 打印JVM信息。
        if (LOGGER.isDebugEnabled()){

            LOGGER.debug("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                    new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), timeCost,
                    requestContext.getUriInfo().getRequestUri(), Runtime.getRuntime().maxMemory()/1024/1024, Runtime.getRuntime().totalMemory()/1024/1024, Runtime.getRuntime().freeMemory()/1024/1024,
                    (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024);
        }

        // 保存日志
        LogUtils.saveAccessLog(requestContext, null,null,timeCost);
       // RedisPoolManager.returnResource(jedisAlterRedisManager.getJedis());
        /*long id = logSequence.incrementAndGet();
        StringBuilder b = new StringBuilder();
        printResponseLine(SERVER_RESPONSE, b, id, responseContext.getStatus());
        printPrefixedHeaders(SERVER_RESPONSE, b, id, *//*HeadersFactory*//*HeaderUtils.asStringHeaders(responseContext.getHeaders()));
        LOGGER.info(b.toString());*/
    }
}
