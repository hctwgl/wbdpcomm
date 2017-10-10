package com.wbdp.bee.entity;

/**
 * 商品sku模型实体类
 * @author 汪赛军
 * date:2017年7月14日上午11:12:17
 *
 */
public class Wbl_GoodsSkuModelEntity {
	private Long id;
	private Long goodsID;
	private String brand;
	private String model;
	private String color;
	private String memory;
	private String operator;
	private int price;
	private Integer skustatus;
	 
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	public Integer getSkustatus() {
		return skustatus;
	}

	public void setSkustatus(Integer skustatus) {
		this.skustatus = skustatus;
	}
	public Long getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(Long goodsID) {
		this.goodsID = goodsID;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
