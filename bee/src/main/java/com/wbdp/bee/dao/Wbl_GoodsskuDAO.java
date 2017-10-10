package com.wbdp.bee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_GoodsskuEntity;



public interface Wbl_GoodsskuDAO {
	
	/**
	 * 增加sku商品
	 * @param wbl_GoodsskuEntity
	 */
    public void insertWbl_GoodsskuEntity(Wbl_GoodsskuEntity wbl_GoodsskuEntity);
    
    /**
     * 获取商品sku列表
     * @return
     */
    public List<Wbl_GoodsskuEntity> listWbl_Goodssku(Integer pageNum);
    
    /**
     * 获取商品sku列表
     * @return
     */
    public List<String> listWbl_GoodsskuAll();
    
    /**
     * 获取数据总条数
     * @return
     */
    public Integer getCount();
    
    /**
     * sku上下架
     * @param map
     * @return
     */
    public Integer updateStatus(@Param("map")Map<String,Object> map);
}