package com.etnlgravtnl.common.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.etnlgravtnl.common.config.Global;
import com.etnlgravtnl.common.exception.Constant.WebExceptionType;
import com.etnlgravtnl.common.exception.MapperSupport.WebActionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;

import javax.inject.Singleton;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 *
 */
@Singleton
@Component("jedisAlterRedisManager")
public class JedisAlterRedisManager {

    @Autowired
    JedisPool jedisPool;

/*

    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }
*/
    ////////////////////Basic Functions(String related) - Start /////////////////////////////

    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-27（创建日期）
     * @param ip type计数类型
     * @Description:
     * 根据计数器类型跟用户访问ip 获取当前计数值
     */
    public  String getCounter4TypeAndIp(String ip,String type)
    {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            int expire=getExpireTime4Counter(type);
            String key=ip+"/"+type+".key";
            String count="";
            if(!jedis.exists(key))
            {
                jedis.setex(key,expire,"0");
            }

            return jedis.get(key);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }

    }

    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-27（创建日期）
     * @param ip type计数类型
     * @Description:
     * 根据计数器类型跟用户访问ip 对当前计数器追加值
     */
    public  long incrCounter4TypeAndIp(String ip,String type)
    {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            int expire=getExpireTime4Counter(type);

            String key=ip+"/"+type+".key";

            if(!jedis.exists(key))
            {
                jedis.setex(key,expire,"0");
            }
            return jedis.incr(key);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-27（创建日期）
     * @param ip type计数类型
     * @Description:
     * 根据计数器类型跟用户访问ip 对当前计数器减少值
     */
    public  long decrounter4TypeAndIp(String ip,String type)
    {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            int expire=getExpireTime4Counter(type);
            String key=ip+"/"+type+".key";

            if(!jedis.exists(key))
            {
                jedis.setex(key,expire,"0");
            }
            return jedis.decr(key);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-27（创建日期）
     * @param  type 计数类型
     * @Description:
     * 根据计数器类型，获取该类型计数器在缓存中存放的时间 ,单位为妙
     */
    public int getExpireTime4Counter(String type)
    {

        int expire=0;
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            expire=Global.counter.get(type);
            return expire;
        }catch (Exception ex)
        {
            throw new WebActionException(WebExceptionType.COUNTERNOTFOUND,type);
        }finally {
            if(jedis!=null)
                jedis.close();
        }

    }

    /**
     * If the value existed, will override the value
     * @param entries
     */
    public void set(Map<String, String> entries) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            for (Map.Entry<String, String> entry : entries.entrySet()) {
                jedis.set(entry.getKey(), entry.getValue());
            }
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * If the key exited, will override the value
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            jedis.set(key, value);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     *
     * @param entries
     */
    public void setnx(Map<String, String> entries) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            for (Map.Entry<String, String> entry : entries.entrySet()) {
                jedis.setnx(entry.getKey(), entry.getValue());
            }
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Only set the value when the key not exist
     * @param key
     * @param value
     */
    public void setnx(String key, String value) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            jedis.setnx(key, value);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Set the value to the key and specify the key's life cycle as seconds.
     * @param key
     * @param live
     * @param value
     */
    public void setKeyLive(String key, int live, String value) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            jedis.setex(key, live, value);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Append the value to an existing key
     * @param key
     * @param value
     */
    public void append(String key, String value) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            jedis.append(key, value);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Get the value by the given key
     * @param key
     * @return
     */
    public String getValue(String key) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.get(key);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Get the values of the specified keys
     * @param keys
     * @return
     */
    public List<String> getValues(String... keys) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.mget(keys);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * remove the value by the key from the cache
     * @param key
     * @return
     */
    public Long removeValue(String key) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.del(key);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Delete the expected values from the cache
     * @param keys
     * @return
     */
    public Long removeValues(String... keys) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.del(keys);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Identify whether the key in the cache or not
     * @param key
     * @return
     */
    public boolean exists(String key) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.exists(key);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }



    /**
     * Clear the cache
     */
    public String clear() {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.flushDB();
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Calculate the size of the cache
     * @return
     */
    public long calculateSize() {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.dbSize();
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Get and update the member by the key in the cache
     * @param key
     * @param value
     * @return
     */
    public String getSet(String key, String value) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.getSet(key, value);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     *
     * @param key
     * @param startOffset
     * @param endOffset
     * @return
     */
    public String getRange(String key, int startOffset, int endOffset) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.getrange(key, startOffset, endOffset);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    ////////////////////Basic Functions(String related) - End /////////////////////////////



    ////////////////////List Functions - Start /////////////////////////////
    /**
     * push the value to the given list
     *
     * 将一个或多个值 value 插入到列表 key 的表头

     *如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头：
     *比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，
     *这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。

     *如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。

     *当 key 存在但不是列表类型时，返回一个错误。
     *
     * @param listName
     * @param values
     * @return
     */
    public long add2List(String listName, String... values) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.lpush(listName, values);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * get the list size
     * @param listName
     * @return
     */
    public long getListSize(String listName) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.llen(listName);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }



    /**
     * Update the member on the index
     * @param listName
     * @param index
     * @param value
     */
    public void updateList(String listName, int index, String value) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            jedis.lset(listName, index, value);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Get the value on the index
     * @param listName
     * @param index
     * @return
     */
    public String getIndexValue(String listName, int index) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.lindex(listName, index);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * 根据参数 count 的值，移除列表中与参数 value 相等的元素。
     * count 的值可以是以下几种：

     *count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
     *count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
     *count = 0 : 移除表中所有与 value 相等的值。

     *
     * @param listName
     * @param count
     * @param value
     * @return
     */
    public long removeLValue(String listName, int count, String value) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.lrem(listName, count, value);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Remove the value out side of the range
     *
     * @param listName
     * @param start
     * @param end
     * @return
     */
    public String removeOutterValue(String listName, int start, int end) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.ltrim(listName, start, end);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Pop the lists
     * @param listName
     * @return
     */
    public String popList(String listName) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.lpop(listName);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Get the specified list values
     *
     * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。

     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。

     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
     *
     * 注意LRANGE命令和编程语言区间函数的区别

     假如你有一个包含一百个元素的列表，对该列表执行 LRANGE list 0 10 ，结果是一个包含11个元素的列表，
     这表明 stop 下标也在 LRANGE 命令的取值范围之内(闭区间)，  这和某些语言的区间函数可能不一致，
     比如Ruby的 Range.new 、 Array#slice 和Python的 range() 函数
     *
     * @param listName
     * @param start
     * @param end
     * @return
     */
    public List<String> getListValues(String listName, long start, long end) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.lrange(listName, start, end);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Get all of the values of the list
     *
     * @param listName
     * @return
     */
    public List<String> getAllListValues(String listName) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.lrange(listName, 0, -1);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Sort the list
     * @param listName
     * @return
     */
    public List<String> sort(String listName) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.sort(listName);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     *
     * @param key
     * @param params
     * @param dstKey
     * @return
     */
    public Long sort(String key, SortingParams params, String dstKey) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.sort(key, params, dstKey);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    ////////////////////List Functions - End /////////////////////////////


    ////////////////////Set Functions - Start /////////////////////////////
    /**
     * Identify whether the member in the given set or not
     * @param setName
     * @param member
     * @return
     */
    public boolean exists(String setName, String member) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.sismember(setName, member);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Add the members to set
     * @param setName
     * @param members
     * @return
     */
    public long add2Set(String setName, String... members) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.sadd(setName, members);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Get all of the values of the set
     *
     * @param setName
     * @return
     */
    public Set<String> getAllSetValues(String setName) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.smembers(setName);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Remove members from the set
     *
     * @param setName
     * @param members
     * @return
     */
    public Long removeSValues(String setName, String ... members) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.srem(setName, members);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Set Pop
     * @param setName
     * @return
     */
    public String popSet(String setName) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.spop(setName);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * 交集
     * Get the intersection
     * @param sets
     * @return
     */
    public Set<String> intersection(String... sets) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.sinter(sets);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * 并集
     * Get the union set of the given sets
     * @param sets
     * @return
     */
    public Set<String> union(String... sets) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.sunion(sets);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * 差集
     * Get the difference set of the given sets
     *
     * @param sets
     * @return
     */
    public Set<String> diff(String... sets) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.sdiff(sets);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    ////////////////////Set Functions - End /////////////////////////////


    ////////////////////Sorted Set Functions - Start /////////////////////////////
    /**
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。
     * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，
     * 来保证该 member 在正确的位置上。
     *
     *
     * @param ssetName - 如果 ssetName 不存在，则创建一个空的有序集并执行 ZADD 操作。
     *                     当 ssetName 存在但不是有序集类型时，返回一个错误。
     * @param score - 可以是整数值或者双精度浮点数，用于排序
     * @param member
     * @return
     */
    public long add2SSet(String ssetName, double score, String member) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.zadd(ssetName, score, member);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Return the size of the sorted set
     * @param sset
     * @return
     */
    public long card(String sset) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.zcard(sset);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Get the sub set
     * @param sset
     * @param start
     * @param end
     * @return
     */
    public Set<String> getSubSet(String sset, long start, long end) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.zrange(sset, start, end);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Get the index of the member
     * @param sset
     * @param member
     * @return
     */
    public Double getIndex(String sset, String member) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.zscore(sset, member);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Remove the members
     * @param sset
     * @param members
     * @return
     */
    public Long removeSSValues(String sset, String ...members) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.zrem(sset, members);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Get all of the values of the sorted set
     * @param sset
     * @return
     */
    public Set<String> getAllSSValues(String sset) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.zrange(sset, 0, -1);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     *
     * @param sset
     * @param start
     * @param end
     * @return
     */
    public Long countRange(String sset, double start, double end) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.zcount(sset, start, end);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    ////////////////////Sorted Set Functions - End /////////////////////////////


    ////////////////////Hash Map Functions - Start /////////////////////////////
    /**
     * Push the value to the map on the key
     * @param map
     * @param key
     * @param value
     * @return
     */
    public long push(String map, String key, String value) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.hset(map, key, value);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Identify whether the key exist or not
     * @param map
     * @param key
     * @return
     */
    public boolean hexists(String map, String key) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.hexists(map, key);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Get the value of the key
     * @param map
     * @param key
     * @return
     */
    public String getValue(String map, String key) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.hget(map, key);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Get the values of the keys
     *
     * @param map
     * @param keys
     * @return
     */
    public List<String> getHValues(String map, String... keys) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.hmget(map, keys);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Remove the values by the keys
     * @param map
     * @param keys
     * @return
     */
    public Long removeHValues(String map, String ... keys) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.hdel(map, keys);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Increment the value on the key for the map
     * @param map
     * @param key
     * @param value
     * @return
     */
    public Long increment(String map, String key, long value) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.hincrBy(map, key, value);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Get all of the keys of the map
     * @param map
     * @return
     */
    public Set<String> getKeys(String map) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.hkeys(map);
        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * Get all of the values of the map
     * @param map
     * @return
     */
    public List<String> getValues(String map) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            return jedis.hvals(map);

        }catch (Exception ex)
        {
            throw  new WebActionException(ex,WebExceptionType.JEDISOPRATOREXCEPTION,jedis);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    ////////////////////Hash Map Functions - End //////////////////////////////
    public static void main(String[] args) {

    }
}