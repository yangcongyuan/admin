package com.etnlgravtnl.common.config;

import com.etnlgravtnl.common.utils.PropertiesLoader;
import com.etnlgravtnl.common.utils.StringUtils;
import jersey.repackaged.com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by Administrator on 2016/6/18.
 */
public class CommonDictionaryConfig {
    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();
    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader dictionary = new PropertiesLoader("properties/dictionary.properties");

    /**
     * 获取配置
     * @see {fns:getConfig('adminPath')}
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null){
            value = dictionary.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }
}
