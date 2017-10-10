package com.wbdp.bee.dao;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_GoodsEntity;



public interface Wbl_GoodsDAO {
	
	/**
	 * 根据商品名称查询商品信息
	 * @param goodsName
	 * @return
	 */
   public Wbl_GoodsEntity getWbl_GoodsEntity(@Param("goodsName")String goodsName,@Param("supid")Long supid);
   
   /**
    * 新增商品
    * @param wbl_GoodsEntity
    * @return
    */
   public int insertWbl_GoodsEntity(Wbl_GoodsEntity wbl_GoodsEntity);
}