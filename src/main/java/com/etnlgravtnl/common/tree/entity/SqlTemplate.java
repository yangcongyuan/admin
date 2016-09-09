package com.etnlgravtnl.common.tree.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2016/7/7.
 */
@XmlRootElement(name="sqlTemplate")
public class SqlTemplate
{
    private String id;
    private String sql;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
