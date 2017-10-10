package com.wbdp.wx.model;

/**
 * Created by wisedata005 on 2017/7/10.
 */
public class ColorMode {
    /**id*/
    private long id;
    /**值*/
    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
