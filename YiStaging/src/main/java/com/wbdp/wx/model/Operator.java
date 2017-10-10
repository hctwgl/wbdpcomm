package com.wbdp.wx.model;

/**
 * Created by wisedata005 on 2017/7/10.
 */
public class Operator {
    /**运营商类型id*/
    private long operatorID;
    /**运营商名*/
    private String operatorName;

    public long getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(long operatorID) {
        this.operatorID = operatorID;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public String toString() {
        return "{" +
                "operatorID=" + operatorID +
                ", operatorName='" + operatorName + '\'' +
                '}';
    }
}
