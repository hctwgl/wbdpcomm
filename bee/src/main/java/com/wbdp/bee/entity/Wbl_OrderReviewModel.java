package com.wbdp.bee.entity;
/**
 * 订单审核数据模型类
 * @author 汪赛军
 * date:2017年7月26日下午5:38:22
 *
 */
public class Wbl_OrderReviewModel {
	/**
	 * 订单ID
	 */
	private Long id;
	/**
	 * 客户姓名
	 */
	private String beeName;
	/**
	 * 客户微信
	 */
	private String beeWX;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 商品对应套餐
	 */
	private String callPackage;
	/**
	 * 商品对应供应商
	 */
	private String supplier;
	/**
	 * sku属性值串
	 */
	private String valueStr;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBeeName() {
		return beeName;
	}
	public void setBeeName(String beeName) {
		this.beeName = beeName;
	}
	public String getBeeWX() {
		return beeWX;
	}
	public void setBeeWX(String beeWX) {
		this.beeWX = beeWX;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getCallPackage() {
		return callPackage;
	}
	public void setCallPackage(String callPackage) {
		this.callPackage = callPackage;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getValueStr() {
		return valueStr;
	}
	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
	}
	
	
}
