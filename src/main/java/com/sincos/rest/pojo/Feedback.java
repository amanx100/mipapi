package com.sincos.rest.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Feedback {

    private String size;
    private String sizeStatus;
    private String msg;
    private String msgStatus;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSizeStatus() {
        return sizeStatus;
    }

    public void setSizeStatus(String sizeStatus) {
        this.sizeStatus = sizeStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }
}
