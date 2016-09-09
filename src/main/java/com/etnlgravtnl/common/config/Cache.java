package com.etnlgravtnl.common.config;

/**
 * Created by admin on 2016/6/23.
 */
public class Cache {

    private static String imgCodeCache;      //图形验证码
    private static String codeCache;    //验证码

    public static String getImgCodeCache() {
        return imgCodeCache;
    }

    public static void setImgCodeCache(String imgCodeCache) {
        Cache.imgCodeCache = imgCodeCache;
    }

    public static String getCodeCache() {
        return codeCache;
    }

    public static void setCodeCache(String codeCache) {
        Cache.codeCache = codeCache;
    }
}
