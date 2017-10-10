package com.wbdp.bee.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_PackageSkuEntity;
/**
 * sku关联套餐业务接口类
 * @author 汪赛军
 * date:2017年7月21日上午11:25:02
 *
 */
public interface Wbl_PackageSkuService {
	/**
	 * 新增sku套餐关联数据
	 * @param wbl_PackageSkuEntity
	 * @return
	 */
	public Map<String,Object> insertPackageSku(String json);
	
	/**
	 * 修改sku套餐关联数据
	 * @param wbl_PackageSkuEntity
	 * @return
	 */
	public Integer updatePackageSku(String json);
	
	/**
	 * 删除sku套餐关联数据
	 * @param wbl_PackageSkuEntity
	 * @return
	 */
	public Integer deletePackageSku(@Param("id")Long id);
	
	/**
	 * 获取sku套餐关联数据
	 * @param wbl_PackageSkuEntity
	 * @return
	 */
	public Map<String,Object> listPackageSku(@Param("skuId")Integer skuId);
}
