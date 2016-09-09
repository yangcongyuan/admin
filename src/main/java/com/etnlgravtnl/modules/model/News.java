package com.etnlgravtnl.modules.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by admin on 2016/8/4.
 */
@XmlRootElement(name = "news")
public class News {

    @FormParam("id")
    private Integer id;
    @FormParam("title")
    private String title;
    @FormParam("detail")
    private String content;
    @FormParam("publish")
    private Integer publish;
    @FormParam("publishDate")
    private Date publishDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPublish() {
        return publish;
    }

    public void setPublish(Integer publish) {
        this.publish = publish;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
