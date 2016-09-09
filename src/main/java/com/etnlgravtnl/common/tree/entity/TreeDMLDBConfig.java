package com.etnlgravtnl.common.tree.entity;

/**
 * Created by Administrator on 2016/7/7.
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;


@XmlRootElement(name="treeDMLDBConfig")
public class TreeDMLDBConfig implements Serializable {
    private  String id;
    private  String parentIdColumnName;
    private  String selfIdColumnName;
    private  String tabname;
    private  String nodeNameColumnName;
    private List<HtmlNode4Tree> htmlNode4Trees;

    @XmlElementWrapper(name = "htmlNodes")
    @XmlElement(name = "htmlNode")
    public List<HtmlNode4Tree> getHtmlNode4Trees() {
        return htmlNode4Trees;
    }

    public void setHtmlNode4Trees(List<HtmlNode4Tree> htmlNode4Trees) {
        this.htmlNode4Trees = htmlNode4Trees;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentIdColumnName() {
        return parentIdColumnName;
    }

    public void setParentIdColumnName(String parentIdColumnName) {
        this.parentIdColumnName = parentIdColumnName;
    }

    public String getSelfIdColumnName() {
        return selfIdColumnName;
    }

    public void setSelfIdColumnName(String selfIdColumnName) {
        this.selfIdColumnName = selfIdColumnName;
    }

    public String getTabname() {
        return tabname;
    }

    public void setTabname(String tabname) {
        this.tabname = tabname;
    }

    public String getNodeNameColumnName() {
        return nodeNameColumnName;
    }

    public void setNodeNameColumnName(String nodeNameColumnName) {
        this.nodeNameColumnName = nodeNameColumnName;
    }
}
