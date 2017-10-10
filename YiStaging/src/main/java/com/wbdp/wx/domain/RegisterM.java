package com.wbdp.wx.domain;

/**
 * 注册session存储
 * Created by wisedata005 on 2017/7/5.
 */
public class RegisterM {
    /**微信code*/
    private String code;
    /**注册手机号*/
    private String phone;
    /**注册验证码*/
    private String VerifieCode;
    /**微信用户id*/
    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerifieCode() {
        return VerifieCode;
    }

    public void setVerifieCode(String verifieCode) {
        VerifieCode = verifieCode;
    }
    @Override
    public String toString() {
    	return "RegisterM"+":"+"["+"openid"+":"+openid+" "+"VerifieCode"+":"+VerifieCode+"]";
    }
}
