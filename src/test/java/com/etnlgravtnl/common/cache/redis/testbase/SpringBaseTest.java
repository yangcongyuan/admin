package com.etnlgravtnl.common.cache.redis.testbase;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/6/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class SpringBaseTest extends AbstractTransactionalJUnit4SpringContextTests
{
    public static void print(Object flag) {
        System.out.println(flag.toString());
    }

    public static void printHighlight(String message) {
        System.err.println(message);
    }
}
