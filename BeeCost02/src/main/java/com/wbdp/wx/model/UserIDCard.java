package com.wbdp.wx.model;

/**
 * 身份证
 * Created by wisedata005 on 2017/7/5.
 */
public class UserIDCard {
    /**身份证正面*/
   private  String oneserverId1;
   /**身份证反面*/
   private String oneserverId2;
   /**修改类型*/
   private int type;
   /**手持身份证正面*/
   private String oneserverId3;
   
    public String getOneserverId3() {
	return oneserverId3;
	}
	
	public void setOneserverId3(String oneserverId3) {
		this.oneserverId3 = oneserverId3;
	}

	public String getOneserverId1() {
        return oneserverId1;
    }

    public void setOneserverId1(String oneserverId1) {
        this.oneserverId1 = oneserverId1;
    }

    public String getOneserverId2() {
        return oneserverId2;
    }

    public void setOneserverId2(String oneserverId2) {
        this.oneserverId2 = oneserverId2;
    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "[oneserverId1=" + oneserverId1 + ", oneserverId2="
				+ oneserverId2 + ", type=" + type + ", oneserverId3="
				+ oneserverId3 + "]";
	}

    
	
}
