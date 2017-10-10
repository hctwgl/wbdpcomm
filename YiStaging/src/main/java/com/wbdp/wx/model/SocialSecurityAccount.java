package com.wbdp.wx.model;

/**
 * 社保账户
 * Created by wisedata005 on 2017/7/5.
 */
public class SocialSecurityAccount {
    /**社保账户*/
    private String SSA;
    /**密码*/
    private String password;

    public String getSSA() {
        return SSA;
    }

    public void setSSA(String SSA) {
        this.SSA = SSA;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "SSA='" + SSA + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
