package com.etnlgravtnl.common.cache.redis;

import javax.annotation.Resource;


import com.etnlgravtnl.common.cache.redis.testbase.SpringBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
//import com.chuanliu.platform.activity.basic.test.SpringBaseTest;


/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */

public class TestjedisAlterRedisManager extends SpringBaseTest {

    @Resource
    private JedisAlterRedisManager jedisAlterRedisManager;

    @Autowired
    JedisPool jedisPool;

    @Before
    public void init() {
        printHighlight(jedisAlterRedisManager.hashCode() + "");
    }

    @Test
    public void springJedisTest()
    {
        Jedis jedis= jedisPool.getResource();
        jedis.set("josh", "WangSheng");
        System.out.println(jedis.get("josh"));
        jedis.flushAll();
        jedis.close();
    }

    @Test
    public void counterTest()
    {
        String ip="127.0.0.1";
        String type="login1";
        print("计数器开始计数，当前计数类型为"+type+",当前计数值="+jedisAlterRedisManager.incrCounter4TypeAndIp(ip,type));
        print("计数器获取计数，当前计数类型为"+type+",当前计数值="+jedisAlterRedisManager.getCounter4TypeAndIp(ip,type));
        print("计数器减少计数，当前计数类型为"+type+",当前计数值="+jedisAlterRedisManager.decrounter4TypeAndIp(ip,type));
    }
    @Test
    public void basicTest() {
        print("============= Basic1 ==========================");

        // 清空数据
        print(jedisAlterRedisManager.clear());

        print(jedisAlterRedisManager.exists("josh"));

        // 存储数据
        jedisAlterRedisManager.set("josh", "WangSheng");

        print(jedisAlterRedisManager.exists("josh"));

        print(jedisAlterRedisManager.getValue("josh"));

        print("============= Basic 2 ==========================");

        // 若key不存在，则存储
        jedisAlterRedisManager.setnx("josh", "wang sheng");
        print(jedisAlterRedisManager.getValue("josh"));

        // 覆盖数据
        jedisAlterRedisManager.set("josh", "wang sheng");
        print(jedisAlterRedisManager.getValue("josh"));

        // 追加数据
        jedisAlterRedisManager.append("josh", "Lily");
        print(jedisAlterRedisManager.getValue("josh"));

        print("============= Basic 3 ==========================");

        // 设置key的有效期，并存储数据
        jedisAlterRedisManager.setKeyLive("josh", 2, "WangSheng-Lily");
        print(jedisAlterRedisManager.getValue("josh"));

        try {
            Thread.sleep(3000);
            print(jedisAlterRedisManager.getValue("josh"));
        } catch (InterruptedException e) {
            print("Josh released  ... now ^_^");
        }



        print("============= Basic 4 ==========================");

        // 获取并更改数据
        jedisAlterRedisManager.getSet("josh", "wang sheng");
        print(jedisAlterRedisManager.getValue("josh"));

        print("============= Basic 5 ==========================");
        // 截取value的值
        print(jedisAlterRedisManager.getRange("josh", 1, 3));

        jedisAlterRedisManager.set("MJ", "Jordan");

        print(jedisAlterRedisManager.getValues("josh", "MJ"));

        print("============= Basic 6 ==========================");
        jedisAlterRedisManager.removeValues(new String[]{"josh", "MJ"});

        print(jedisAlterRedisManager.getValues("josh", "MJ"));
    }

    /**
     * List 是无序的，所以测试结果和expect的结果可能不一致
     *
     * 还是是从-1开始？
     */
    @Test
    public void listTest() {
        System.out.println("============= list1 ==========================");
        // 清空数据
        print(jedisAlterRedisManager.clear());

        // 添加数据
        jedisAlterRedisManager.add2List("ball", "Jordan");
        jedisAlterRedisManager.add2List("ball", "Maddie");
        jedisAlterRedisManager.add2List("ball", "AI");
        jedisAlterRedisManager.add2List("ball", "Yao");

        // The order should be : Yao, AI, Maddie, Jordan

        // 数组长度
        print(jedisAlterRedisManager.getListSize("ball"));

        print(jedisAlterRedisManager.getAllListValues("ball"));

        print(jedisAlterRedisManager.getListValues("ball", 0, -1));

        System.out.println("============= list2 ==========================");

        // 修改列表中单个值
        jedisAlterRedisManager.updateList("ball", 1, "Allen Iversen");
        print(jedisAlterRedisManager.getListValues("ball", 0, 3));

        // 获取列表指定下标的值
        print(jedisAlterRedisManager.getIndexValue("ball", 2));

        // 删除列表指定下标的值
        print(jedisAlterRedisManager.removeLValue("ball", 1, "Yao"));
        print(jedisAlterRedisManager.getAllListValues("ball"));

        // 删除区间以外的数据
        print(jedisAlterRedisManager.removeOutterValue("ball", 0, 1));
        print(jedisAlterRedisManager.getAllListValues("ball"));

        // 列表出栈
        print(jedisAlterRedisManager.popList("ball"));
    }

    @Test
    public void testSet() {
        print("========================= Set ====================");

        // 清空数据
        print(jedisAlterRedisManager.clear());

        // 添加数据
        jedisAlterRedisManager.add2Set("ball", "Jordan");
        jedisAlterRedisManager.add2Set("ball", "Maddie");
        jedisAlterRedisManager.add2Set("ball", "AI");
        jedisAlterRedisManager.add2Set("ball", "Yao");

        // 判断value是否在列表中
        print(jedisAlterRedisManager.exists("ball", "AI"));
        print(jedisAlterRedisManager.exists("ball", "Yao "));

        // 整个列表的值
        print(jedisAlterRedisManager.getAllSetValues("ball"));

        // 删除指定的元素
        print(jedisAlterRedisManager.removeSValues("ball", "Yao"));

        // 出栈
        print(jedisAlterRedisManager.popSet("ball"));

        // 整个列表的值
        print(jedisAlterRedisManager.getAllSetValues("ball"));

        jedisAlterRedisManager.add2Set("bball", "Jordan");
        jedisAlterRedisManager.add2Set("bball", "Maddie");
        jedisAlterRedisManager.add2Set("bball", "AI");
        jedisAlterRedisManager.add2Set("bball", "Yao");

        jedisAlterRedisManager.add2Set("fball", "Jordan");
        jedisAlterRedisManager.add2Set("fball", "Ronaldo");
        jedisAlterRedisManager.add2Set("fball", "Rivaldo");
        jedisAlterRedisManager.add2Set("fball", "Cristiano Ronaldo");
        jedisAlterRedisManager.add2Set("fball", "Ronaldinho");

        // 交集
        print(jedisAlterRedisManager.intersection("bball", "fball"));

        // 并集
        print(jedisAlterRedisManager.union("bball", "fball"));

        // 差集
        print(jedisAlterRedisManager.diff("bball", "fball"));

    }

    @Test
    public void testHash() {
        System.out.println("=============  hash ==========================");

        // 清空数据
        print(jedisAlterRedisManager.clear());

        // 添加数据
        jedisAlterRedisManager.push("hball", "Jordan", "Chicago");
        jedisAlterRedisManager.push("hball", "Maddie", "Houston");
        jedisAlterRedisManager.push("hball", "AI", "Philadelphia");
        jedisAlterRedisManager.push("hball", "Yao", "Houston");

        // 判断某个值是否存在
        print(jedisAlterRedisManager.hexists("hball", "Yao "));
        print(jedisAlterRedisManager.hexists("hball", "AI"));

        // 获取指定的值
        print(jedisAlterRedisManager.getValue("hball", "Jordan"));

        // 批量获取指定的值
        print(jedisAlterRedisManager.getHValues("hball", "Jordan", "Maddie"));

        // 删除指定的值
        print(jedisAlterRedisManager.removeHValues("hball", "Yao"));

        // 为key中的域 field 的值加上增量 increment, hash value must be a data value
        // print(jedisAlterRedisManager.increment("hball", "Jordan", 123l));

        // 获取所有的keys
        print(jedisAlterRedisManager.getKeys("hball"));

        // 获取所有的values
        print(jedisAlterRedisManager.getValues("hball"));
    }

}