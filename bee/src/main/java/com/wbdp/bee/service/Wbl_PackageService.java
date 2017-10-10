package com.wbdp.bee.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_PackageEntity;

/**
 * 话费套餐业务接口类
 * @author 汪赛军
 * date:2017年7月20日下午3:20:19
 *
 */
public interface Wbl_PackageService {
	/**
	 * 新增话费套餐数据
	 * @param json
	 * @return
	 */
	public Map<String,Object> insertPackage(String json);
	
	/**
	 * 删除话费套餐数据
	 * @param json
	 * @return
	 */
	public Map<String,Object> deletePackage(String json);
	
	/**
	 * 修改话费套餐数据
	 * @param json
	 * @return
	 */
	public Map<String,Object> updatePackage(String json);
	
	/**
	 * 关联查询话费套餐数据（分页）
	 * @param json
	 * @return
	 */
	public Map<String,Object> listPackage(Integer pageNum);
	
	/**
	 * 根据skuid查询套餐数据
	 * @param skuID
	 * @return
	 */
	public Map<String,Object> getPackage(Long skuID);
}
