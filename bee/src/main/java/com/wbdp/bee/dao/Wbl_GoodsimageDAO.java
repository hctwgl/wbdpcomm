package com.wbdp.bee.dao;

import com.wbdp.bee.entity.Wbl_GoodsimageEntity;



public interface Wbl_GoodsimageDAO {
	
	/**
	 * 新增商品图片路径
	 * @param wbl_GoodsimageEntity
	 * @return
	 */
    public int insertImage(Wbl_GoodsimageEntity wbl_GoodsimageEntity);
}