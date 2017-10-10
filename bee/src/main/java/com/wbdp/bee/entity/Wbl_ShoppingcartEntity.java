package com.wbdp.bee.entity;

/**
 * 
 * @author wangsaijun
 */
public class Wbl_ShoppingcartEntity {
	/**
	 *  ID
	 */
	private Long id;
	/**
	 *  用户ID
	 */
	private Long beeId;
	/**
	 *  商品ID
	 */
	private Long goodsId;
	/**
	 *  期数
	 */
	private Integer periods;
	/**
	 *  商品数量
	 */
	private Integer number;
	/**
	 *  状态：1未清空2已清空
	 */
	private Integer cartState;
	/**
	 *  创建人
	 */
	private String creatBy;
	/**
	 *  创建时间
	 */
	private String creatDate;
	/**
	 *  修改人
	 */
	private String upDateBy;
	/**
	 *  修改时间
	 */
	private String upDateTime;
	/**
	 *  skuID
	 */
	private Long goodsskuId;
	/**
	 *  购物车客户
	 */
	private Wbl_BeeEntity bee;
	/**
	 *  购物车商品
	 */
	private Wbl_GoodsEntity goods;
	/**
	 *  购物车商品
	 */
	private Wbl_GoodsskuEntity sku;
	
	public Wbl_GoodsskuEntity getSku() {
		return sku;
	}

	public void setSku(Wbl_GoodsskuEntity sku) {
		this.sku = sku;
	}

	public Wbl_BeeEntity getBee() {
		return bee;
	}

	public void setBee(Wbl_BeeEntity bee) {
		this.bee = bee;
	}

	public Wbl_GoodsEntity getGoods() {
		return goods;
	}

	public void setGoods(Wbl_GoodsEntity goods) {
		this.goods = goods;
	}

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
	 * 用户ID
	 * @param beeId
	 */
	public void setBeeId(Long beeId){
		this.beeId = beeId;
	}
	
    /**
     * 用户ID
     * @return
     */	
    public Long getBeeId(){
    	return beeId;
    }
	/**
	 * 商品ID
	 * @param goodsId
	 */
	public void setGoodsId(Long goodsId){
		this.goodsId = goodsId;
	}
	
    /**
     * 商品ID
     * @return
     */	
    public Long getGoodsId(){
    	return goodsId;
    }
	/**
	 * 期数
	 * @param periods
	 */
	public void setPeriods(Integer periods){
		this.periods = periods;
	}
	
    /**
     * 期数
     * @return
     */	
    public Integer getPeriods(){
    	return periods;
    }
	/**
	 * 商品数量
	 * @param number
	 */
	public void setNumber(Integer number){
		this.number = number;
	}
	
    /**
     * 商品数量
     * @return
     */	
    public Integer getNumber(){
    	return number;
    }
	/**
	 * 状态：1未清空2已清空
	 * @param cartState
	 */
	public void setCartState(Integer cartState){
		this.cartState = cartState;
	}
	
    /**
     * 状态：1未清空2已清空
     * @return
     */	
    public Integer getCartState(){
    	return cartState;
    }
	/**
	 * 创建人
	 * @param creatBy
	 */
	public void setCreatBy(String creatBy){
		this.creatBy = creatBy;
	}
	
    /**
     * 创建人
     * @return
     */	
    public String getCreatBy(){
    	return creatBy;
    }
	/**
	 * 创建时间
	 * @param creatDate
	 */
	public void setCreatDate(String creatDate){
		this.creatDate = creatDate;
	}
	
    /**
     * 创建时间
     * @return
     */	
    public String getCreatDate(){
    	return creatDate;
    }
	/**
	 * 修改人
	 * @param upDateBy
	 */
	public void setUpDateBy(String upDateBy){
		this.upDateBy = upDateBy;
	}
	
    /**
     * 修改人
     * @return
     */	
    public String getUpDateBy(){
    	return upDateBy;
    }
	/**
	 * 修改时间
	 * @param upDateTime
	 */
	public void setUpDateTime(String upDateTime){
		this.upDateTime = upDateTime;
	}
	
    /**
     * 修改时间
     * @return
     */	
    public String getUpDateTime(){
    	return upDateTime;
    }
	/**
	 * skuID
	 * @param goodsskuId
	 */
	public void setGoodsskuId(Long goodsskuId){
		this.goodsskuId = goodsskuId;
	}
	
    /**
     * skuID
     * @return
     */	
    public Long getGoodsskuId(){
    	return goodsskuId;
    }
}