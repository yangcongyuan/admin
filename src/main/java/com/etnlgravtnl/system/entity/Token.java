package com.etnlgravtnl.system.entity;

import java.util.Date;

/**
 * Created by Administrator on 2016/7/1.
 */
public class Token {
    private String value;
    private Date expire;
    private String scope;
    private String subject;

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
