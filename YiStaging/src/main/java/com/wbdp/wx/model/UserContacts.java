package com.wbdp.wx.model;

/**
 * 常联系人
 * Created by wisedata005 on 2017/7/5.
 */
public class UserContacts {
    /**姓名*/
    private String  name;
    /**关系*/
    private String  relationship;
    /**电话*/
    private String  phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", relationship='" + relationship + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
