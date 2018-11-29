package com.sincos.rest.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Feedback {
    private String status;
    private int msgsize;
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMsgsize() {
        return msgsize;
    }

    public void setMsgsize(int msgsize) {
        this.msgsize = msgsize;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
