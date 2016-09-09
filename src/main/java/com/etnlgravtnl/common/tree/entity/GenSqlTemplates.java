package com.etnlgravtnl.common.tree.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
@XmlRootElement(name="sqlTemplates")
public class GenSqlTemplates
{
    private List<SqlTemplate> sqlTemplates;

    @XmlElement(name = "sqlTemplate")
    public List<SqlTemplate> getSqlTemplates() {
        return sqlTemplates;
    }

    public void setSqlTemplates(List<SqlTemplate> sqlTemplates) {
        this.sqlTemplates = sqlTemplates;
    }
    public  SqlTemplate getSqlTemplate(String id)
    {
        for (int i = 0; i < sqlTemplates.size(); i++)
        {
            SqlTemplate sqlTemplate=this.sqlTemplates.get(i);
            if(sqlTemplate.getId().equals(id))
            {
                return sqlTemplate;
            }
        }
        return null;
    }
}
