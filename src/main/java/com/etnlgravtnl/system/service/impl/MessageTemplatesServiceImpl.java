package com.etnlgravtnl.system.service.impl;

import com.etnlgravtnl.common.service.impl.CrudServiceImpl;
import com.etnlgravtnl.system.dao.MessageTemplatesDao;
import com.etnlgravtnl.system.entity.MessageTemplates;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MessageTemplatesServiceImpl extends CrudServiceImpl<MessageTemplatesDao,MessageTemplates>
{
    @Autowired
    private MessageTemplatesDao messageTemplatesDao;

    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-23（创建日期）
     * @Description:
     *往信息模板里的变量注入内容
     * 分为List 跟 Map两种 map需要名称对应key List需要次序正确
     *
     */
    public String inject2MsgTpl4Code(Object params, String msgTplCode) throws Exception
    {
        MessageTemplates msgTpl=new MessageTemplates();
        msgTpl.setMsgTplCode(msgTplCode);
        msgTpl= messageTemplatesDao.get(msgTpl);
        String dynamicContent=msgTpl.getMsgTplContent();
//        String dynamicContent= "${cat} really needs some ${beverage}.";

        //生成匹配模式的正则表达式
        String patternString = "";
        StringBuffer sb = new StringBuffer();
        if(params instanceof Map)
        {
            Map mapParams=(Map)params;
            patternString = "\\$\\{(" + StringUtils.join(mapParams.keySet(), "|") + ")\\}";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(dynamicContent);
            while(matcher.find()) {
                matcher.appendReplacement(sb, mapParams.get(matcher.group(1)).toString());
            }
            matcher.appendTail(sb);
        }else if (params instanceof List)
        {
            patternString= "\\$\\{(.+?)\\}";
            List listParams=(List)params;
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(dynamicContent);
            int i=0;
            while(matcher.find()) {
                //matcher.appendReplacement(sb, list.get(i).toString());
                matcher.appendReplacement(sb,listParams.get(i).toString());
                i++;
            }
            matcher.appendTail(sb);
        }else
        {
            throw new Exception("参数类型不支持，只支持list map");
        }

        System.out.println(sb.toString());
        return sb.toString();
    }

}