package com.sincos.rest.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Feedback {
    private boolean success;
    private int msgsize;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
