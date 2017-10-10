package com.wbdp.wx.model;

/**
 * Created by wisedata005 on 2017/7/12.
 */
public class Quota {
    /**额度id*/
    private long pollenID;
    /**信用额度*/
    private int quota;
    /**已用信用额度*/
    private int userquota;
    
    public long getPollenID() {
        return pollenID;
    }

    public void setPollenID(long pollenID) {
        this.pollenID = pollenID;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }
    
    public int getUserquota() {
		return userquota;
	}

	public void setUserquota(int userquota) {
		this.userquota = userquota;
	}

	@Override
	public String toString() {
		return "[pollenID=" + pollenID + ", quota=" + quota
				+ ", userquota=" + userquota + "]";
	}

}
