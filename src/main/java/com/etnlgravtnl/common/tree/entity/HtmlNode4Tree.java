package com.etnlgravtnl.common.tree.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2016/7/8.
 */
@XmlRootElement(name="htmlNode")
public class HtmlNode4Tree {
    private String level;
    private String htmlHeadText;
    private String htmlFootText;
    private String leafNodeText;

    public String getLeafNodeText() {
        return leafNodeText;
    }

    public void setLeafNodeText(String leafNodeText) {
        this.leafNodeText = leafNodeText;
    }

    public String getHtmlHeadText() {
        return htmlHeadText;
    }

    public void setHtmlHeadText(String htmlHeadText) {
        this.htmlHeadText = htmlHeadText;
    }

    public String getHtmlFootText() {
        return htmlFootText;
    }

    public void setHtmlFootText(String htmlFootText) {
        this.htmlFootText = htmlFootText;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
