package com.wbdp.wx.model;

/**
 * 业务员二维码
 * @author wangsaijun
 */
public class Qrimg {
	/**
	 *  ID
	 */
	private Long id;
	/**
	 *  业务员微信号
	 */
	private String beeWx;
	/**
	 *  二维码地址
	 */
	private String imgUrl;
	/**
	 *  套餐期数
	 */
	private Integer pacPeriods;
	/**
	 *  套餐内每月应付金额
	 */
	private Integer pacMonthlyPrice;
	/**
	 *  套餐总价
	 */
	private Integer pacPrice;
	/**
	 *  生成时间
	 */
	private String creatDate;
	/**
	 * ID
	 * @param id
	 */
	public void setId(Long id){
		this.id = id;
	}
	
    /**
     * ID
     * @return
     */	
    public Long getId(){
    	return id;
    }
	/**
	 * 业务员微信号
	 * @param beeWx
	 */
	public void setBeeWx(String beeWx){
		this.beeWx = beeWx;
	}
	
    /**
     * 业务员微信号
     * @return
     */	
    public String getBeeWx(){
    	return beeWx;
    }
	/**
	 * 二维码地址
	 * @param imgUrl
	 */
	public void setImgUrl(String imgUrl){
		this.imgUrl = imgUrl;
	}
	
    /**
     * 二维码地址
     * @return
     */	
    public String getImgUrl(){
    	return imgUrl;
    }
	/**
	 * 套餐期数
	 * @param pacPeriods
	 */
	public void setPacPeriods(Integer pacPeriods){
		this.pacPeriods = pacPeriods;
	}
	
    /**
     * 套餐期数
     * @return
     */	
    public Integer getPacPeriods(){
    	return pacPeriods;
    }
	/**
	 * 套餐内每月应付金额
	 * @param pacMonthlyPrice
	 */
	public void setPacMonthlyPrice(Integer pacMonthlyPrice){
		this.pacMonthlyPrice = pacMonthlyPrice;
	}
	
    /**
     * 套餐内每月应付金额
     * @return
     */	
    public Integer getPacMonthlyPrice(){
    	return pacMonthlyPrice;
    }
	/**
	 * 套餐总价
	 * @param pacPrice
	 */
	public void setPacPrice(Integer pacPrice){
		this.pacPrice = pacPrice;
	}
	
    /**
     * 套餐总价
     * @return
     */	
    public Integer getPacPrice(){
    	return pacPrice;
    }
	/**
	 * 生成时间
	 * @param creatDate
	 */
	public void setCreatDate(String creatDate){
		this.creatDate = creatDate;
	}
	
    /**
     * 生成时间
     * @return
     */	
    public String getCreatDate(){
    	return creatDate;
    }
}