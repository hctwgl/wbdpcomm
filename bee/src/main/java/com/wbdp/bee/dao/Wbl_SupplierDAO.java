package com.wbdp.bee.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.wbdp.bee.entity.Wbl_SupplierEntity;


public interface Wbl_SupplierDAO {

	
	/**
	 * 方法名: SupplierInsert   
	 * 方法描述: 运营商增加
	 * 入参描述: 运营商实体
	 * 回调描述: 1:增加成功  0:增加失败 
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月8日 
	 */
	public Integer SupplierInsert(Wbl_SupplierEntity SupplierEntity);
	
	/**
	 * 方法名: SupplierDelete   
	 * 方法描述: 运营商删除
	 * 入参描述: 需删除的ID
	 * 回调描述: 1:删除成功 0:删除失败  
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月8日 
	 */
    public Integer SupplierDelete(@Param("id")Long id);
    
    /**
     * 方法名: SupplierUpdate   
     * 方法描述: 运营商修改
     * 入参描述: 运营商实体
     * 回调描述: 1:修改成功 0:修改失败  
     * 创建人:wisedata004  
     * 创建时间: 2017年7月8日 
     */
    public Integer SupplierUpdate(Wbl_SupplierEntity SupplierEntity);
    
    /**
     * 方法名: SupplierDeleteAllList   
     * 方法描述: 运营商列表
     * 入参描述: pageNum:页码
     * 回调描述: 所有用户信息  
     * 创建人:wisedata004  
     * 创建时间: 2017年7月8日 
     */
    public List<Wbl_SupplierEntity> SupplierAllList(@Param("pageNum")Integer pageNum);
    public Integer SupplierAllListSize();
    
    /***
     * 用于添加商品时ajax调用
     * @return
     */
    public  List<Wbl_SupplierEntity> listSupplier();
}
