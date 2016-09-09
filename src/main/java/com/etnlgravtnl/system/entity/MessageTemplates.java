package com.etnlgravtnl.system.entity;

import com.etnlgravtnl.common.persistence.DataEntity;

import java.util.Date;

public class MessageTemplates extends DataEntity<MessageTemplates> {

    private String msgTplName;

    private String msgTplCode;

    private String msgTplContent;

    private String msgTplType;

    private String msgTplDesc;

    private String msgTplStatus;

    private Integer maintainner;

    private Date maintaintime;




    public String getMsgTplName() {
        return msgTplName;
    }

    public void setMsgTplName(String msgTplName) {
        this.msgTplName = msgTplName == null ? null : msgTplName.trim();
    }

    public String getMsgTplCode() {
        return msgTplCode;
    }

    public void setMsgTplCode(String msgTplCode) {
        this.msgTplCode = msgTplCode == null ? null : msgTplCode.trim();
    }

    public String getMsgTplContent() {
        return msgTplContent;
    }

    public void setMsgTplContent(String msgTplContent) {
        this.msgTplContent = msgTplContent == null ? null : msgTplContent.trim();
    }

    public String getMsgTplType() {
        return msgTplType;
    }

    public void setMsgTplType(String msgTplType) {
        this.msgTplType = msgTplType == null ? null : msgTplType.trim();
    }

    public String getMsgTplDesc() {
        return msgTplDesc;
    }

    public void setMsgTplDesc(String msgTplDesc) {
        this.msgTplDesc = msgTplDesc == null ? null : msgTplDesc.trim();
    }

    public String getMsgTplStatus() {
        return msgTplStatus;
    }

    public void setMsgTplStatus(String msgTplStatus) {
        this.msgTplStatus = msgTplStatus == null ? null : msgTplStatus.trim();
    }

    public Integer getMaintainner() {
        return maintainner;
    }

    public void setMaintainner(Integer maintainner) {
        this.maintainner = maintainner;
    }

    public Date getMaintaintime() {
        return maintaintime;
    }

    public void setMaintaintime(Date maintaintime) {
        this.maintaintime = maintaintime;
    }
}