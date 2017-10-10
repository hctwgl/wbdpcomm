package com.wbdp.bee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_PackageSkuEntity;


/**
 * sku关联套餐数据接口类
 * @author 汪赛军
 * date:2017年7月20日下午5:59:55
 *
 */
public interface Wbl_PackageSkuDAO {
	
	/**
	 * 新增sku套餐关联数据
	 * @param wbl_PackageSkuEntity
	 * @return
	 */
	public Integer insertPackageSku(Wbl_PackageSkuEntity wbl_PackageSkuEntity);
	
	/**
	 * 修改sku套餐关联数据
	 * @param wbl_PackageSkuEntity
	 * @return
	 */
	public Integer updatePackageSku(Wbl_PackageSkuEntity wbl_PackageSkuEntity);
	
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
	public List<Wbl_PackageSkuEntity> listPackageSku(@Param("skuId")Long skuId);

} 
