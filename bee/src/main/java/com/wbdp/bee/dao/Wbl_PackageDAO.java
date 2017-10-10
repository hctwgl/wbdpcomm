package com.wbdp.bee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_PackageEntity;



/**
 * 话费套餐数据接口类
 * @author 汪赛军
 * date:2017年7月20日下午2:47:30
 *
 */
public interface Wbl_PackageDAO {

	/**
	 * 新增话费套餐数据
	 * @param wbl_PackageEntity
	 * @return
	 */
	public Integer insertPackage(Wbl_PackageEntity wbl_PackageEntity);
	
	/**
	 * 删除话费套餐数据
	 * @param id
	 * @return
	 */
	public Integer deletePackage(@Param("id")Long id);
	
	/**
	 * 修改话费套餐数据
	 * @param wbl_PackageEntity
	 * @return
	 */
	public Integer updatePackage(Wbl_PackageEntity wbl_PackageEntity);
	
	/**
	 * 获取话费套餐列表（分页以及关联查询）
	 * @param pageNum
	 * @return
	 */
	public List<Wbl_PackageEntity> listPackage(@Param("pageNum")Integer pageNum);
	
	/**
	 * 获取数据总数（用于分页）
	 * @return
	 */
	public Integer getCount();
	
	/**
	 * 根据skuid查询套餐数据
	 * @param skuID
	 * @return
	 */
	public List<Wbl_PackageEntity> getPackage(@Param("skuID")Long skuID);
} 
