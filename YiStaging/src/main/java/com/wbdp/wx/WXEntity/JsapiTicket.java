package com.wbdp.wx.WXEntity;

/**
 * Created by wisedata005 on 2017/7/5.
 */
public class JsapiTicket {
    private String errcode;
    private String errmsg;
    private String ticket;
    private int expiresIn;
    public String getErrcode() {
        return errcode;
    }
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public String getTicket() {
        return ticket;
    }
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    public int getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
    @Override
    public String toString() {
        return "JsapiTicket [errcode=" + errcode + ", errmsg=" + errmsg
                + ", ticket=" + ticket + ", expiresIn=" + expiresIn + "]";
    }
}
