package com.wbdp.bee.dao;

import java.util.List;

import com.wbdp.bee.entity.Wbl_Goods_attributeEntity;



public interface Wbl_Goods_AttributeDAO {
	
	/**
	 * 新增商品属性关联信息
	 * @param wbl_Goods_attributeEntity
	 * @return
	 */
    public int insertGoods_Attribute(Wbl_Goods_attributeEntity wbl_Goods_attributeEntity);
    
    /**
     * 获取关联信息中商品ID与属性值id的组合字符串，用于过滤重复数据
     * @return
     */
    public List<String> getGoods_AttributeStr();
}