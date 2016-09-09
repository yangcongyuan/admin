package com.etnlgravtnl.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JqueryDataTableUtils {
    public static Map<String,String> returnDataTableParams(String tableDataParamJsonStr)
    {
        Map<String,String> dataTableParams=new HashMap<String,String>();
        //获取请求次数
        String draw="0";
        //数据起始位置
        int start =0;
        //数据长度
        int length =0;
        String tableDataText="";

        //总记录数
        String recordsTotal = "0";

        //过滤后记录数
        String recordsFiltered = "";

        //获取客户端需要那一列排序
        int orderColumn =0;
        //排序字段名称
        String orderColumnName="";
        //获取排序方式 默认为asc
        String orderDir = "asc";

        String orderSqlText=" ";
        //获取用户过滤框里的字符
        String searchValue = "";

        StringBuffer searchSqlText=new StringBuffer();

        try {
            tableDataParamJsonStr= URLDecoder.decode(tableDataParamJsonStr,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        tableDataParamJsonStr=tableDataParamJsonStr.replaceAll("&","\",\"");
        tableDataParamJsonStr=tableDataParamJsonStr.replaceAll("=","\":\"");
        tableDataParamJsonStr="{\""+tableDataParamJsonStr+"\"}";
        JSONObject tableDataParamJson= JSON.parseObject(tableDataParamJsonStr);

        Map<Integer,String> colsMap = new HashMap<Integer,String>();

        Iterator iterator = tableDataParamJson.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = tableDataParamJson.getString(key);
            if(key.indexOf("columns")!=-1)
            {
                String rex="columns\\[([0-9])\\]\\[data\\]";
                Pattern p=Pattern.compile(rex);
                Matcher matcher=p.matcher(key);
                if(matcher.find())
                {
                    colsMap.put(Integer.parseInt(matcher.group(1)),value);
                }
            }
        }

        //获取请求次数
        draw =tableDataParamJson.getString("draw");
        //数据起始位置
       start = Integer.parseInt(tableDataParamJson.getString("start"));
        //数据长度
        length = Integer.parseInt(tableDataParamJson.getString("length"));


        orderColumn = Integer.parseInt(tableDataParamJson.getString("order[0][column]"));
        orderColumnName = colsMap.get(orderColumn);

        orderDir = tableDataParamJson.getString("order[0][dir]");
        orderSqlText=" order by "+orderColumnName+" "+orderDir+" ";
        //获取用户过滤框里的字符
        searchValue = tableDataParamJson.getString("search[value]");

        if(!searchValue.equals(""))
        {
            searchSqlText.append("AND ( ");
            Iterator it = colsMap.keySet().iterator();
            while (it.hasNext())
            {
                int key = (int) it.next();
                if(colsMap.get(key).toLowerCase().indexOf("date")==-1)
                    searchSqlText.append("  "+colsMap.get(key)+" like '%"+searchValue+"%' OR ");
            }
            searchSqlText.append(" 1=2 ) ");
        }
        dataTableParams.put("draw",draw);
        dataTableParams.put("start",String.valueOf(start));
        dataTableParams.put("length",String.valueOf(length));
        dataTableParams.put("tableDataText",tableDataText);
        dataTableParams.put("recordsTotal",recordsTotal);
        dataTableParams.put("recordsFiltered",recordsFiltered);
        dataTableParams.put("orderColumn",String.valueOf(orderColumn));
        dataTableParams.put("orderColumnName",orderColumnName);
        dataTableParams.put("orderDir",orderDir);
        dataTableParams.put("orderSqlText",orderSqlText);
        dataTableParams.put("searchValue",searchValue);
        dataTableParams.put("searchSqlText",searchSqlText.toString());

        return dataTableParams;
    }
}
