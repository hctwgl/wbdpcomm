package com.wbdp.bee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_SalemanEntity;
import com.wbdp.bee.entity.Wbl_SalemanEntity;


public interface Wbl_SalemanDAO {

	
	/**
	 * 方法名: SalemanInsert   
	 * 方法描述: 业务员增加
	 * 入参描述: 业务员实体
	 * 回调描述: 1:增加成功  0:增加失败 
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月8日 
	 */
	public Integer SalemanInsert(Wbl_SalemanEntity SalemanEntity);
	
	/**
	 * 方法名: SalemanDelete   
	 * 方法描述: 业务员删除
	 * 入参描述: 需删除的ID
	 * 回调描述: 1:删除成功 0:删除失败  
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月8日 
	 */
    public Integer SalemanDelete(@Param("id")Long id);
    
    /**
     * 方法名: SalemanUpdate   
     * 方法描述: 业务员修改
     * 入参描述: 业务员实体
     * 回调描述: 1:修改成功 0:修改失败  
     * 创建人:wisedata004  
     * 创建时间: 2017年7月8日 
     */
    public Integer SalemanUpdate(Wbl_SalemanEntity SalemanEntity);
    
    /**
     * 方法名: SalemanUpdateRecommend   
     * 方法描述:  业务员推荐码修改
     * 入参描述: 
     * 回调描述:   
     * 创建人:wisedata004  
     * 创建时间: 2017年7月24日
     */
    public Integer SalemanUpdateRecommend(@Param("id")Long id,@Param("recommend")String recommend);
    
    /**
     * 方法名: SalemanAllList   
     * 方法描述: 业务员列表
     * 入参描述: pageNum:页码
     * 回调描述: 所有用户信息  
     * 创建人:wisedata004  
     * 创建时间: 2017年7月8日 
     */
    public List<Wbl_SalemanEntity> SalemanAllList(@Param("pageNum")Integer pageNum);
    public Integer SalemanAllListSize();
}
