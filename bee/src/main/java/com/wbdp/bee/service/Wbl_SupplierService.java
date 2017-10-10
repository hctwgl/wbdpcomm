package com.wbdp.bee.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_SupplierEntity;
import com.wbdp.bee.entity.Wbl_UserEntity;

public interface Wbl_SupplierService {

	/**
	 * 方法名: SupplierInsert   
	 * 方法描述: 运营商增加
	 * 入参描述: 运营商实体
	 * 回调描述: 1:增加成功  0:增加失败 
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月8日 
	 */
	public Map<String,Object> SupplierInsert(Wbl_SupplierEntity SupplierEntity);
	
	/**
	 * 方法名: SupplierDelete   
	 * 方法描述: 运营商删除
	 * 入参描述: 需删除的ID
	 * 回调描述: 1:删除成功 0:删除失败  
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月8日 
	 */
    public Map<String,Object> SupplierDelete(Long id);
    
    /**
     * 方法名: SupplierUpdate   
     * 方法描述: 运营商修改
     * 入参描述: 运营商实体
     * 回调描述: 1:修改成功 0:修改失败  
     * 创建人:wisedata004  
     * 创建时间: 2017年7月8日 
     */
    public Map<String,Object> SupplierUpdate(Wbl_SupplierEntity SupplierEntity);
    
    /**
     * 方法名: SupplierDeleteAllList   
     * 方法描述: 运营商列表
     * 入参描述: pageNum:页码
     * 回调描述: 所有用户信息  
     * 创建人:wisedata004  
     * 创建时间: 2017年7月8日 
     */
    public Map<String,Object> SupplierAllList(Integer pageNum);
    
    /***
     * 用于添加商品时ajax调用
     * @return
     */
    public Map<String,Object> listSupplier();
}
