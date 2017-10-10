package com.wbdp.wx.model;

/**
 * Created by wisedata005 on 2017/7/10.
 */
public class OperatorTypeSort {
    /**当前起始*/
    private int limit ;
    /**运营商类型*/
    private int operatorType;
    /**价格类型*/
    private int type;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(int operatorType) {
        this.operatorType = operatorType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

	@Override
	public String toString() {
		return "[limit=" + limit + ", operatorType="
				+ operatorType + ", type=" + type + "]";
	}
    
}
