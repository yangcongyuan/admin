package com.etnlgravtnl.common.web;

import com.etnlgravtnl.common.cache.redis.JedisAlterRedisManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;


public class BaseController {
    @Autowired
    protected JedisAlterRedisManager jedisAlterRedisManager;

    @Context
    protected SecurityContext securityContext;
}
