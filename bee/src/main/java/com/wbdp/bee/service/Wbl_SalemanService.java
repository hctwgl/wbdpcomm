package com.wbdp.bee.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.servlet.ModelAndView;

import com.wbdp.bee.entity.Saleman;
import com.wbdp.bee.entity.Wbl_SalemanEntity;




public interface Wbl_SalemanService {

	
	/**
	 * 方法名: SalemanInsert   
	 * 方法描述: 业务员增加
	 * 入参描述: 业务员实体
	 * 回调描述: 1:增加成功  0:增加失败 
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月8日 
	 */
	public Map<String,Object> SalemanInsert(Wbl_SalemanEntity SalemanEntity);
	
	/**
	 * 方法名: SalemanDelete   
	 * 方法描述: 业务员删除
	 * 入参描述: 需删除的ID
	 * 回调描述: 1:删除成功 0:删除失败  
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月8日 
	 */
    public Map<String,Object> SalemanDelete(Long id);
    
    /**
     * 方法名: SalemanUpdate   
     * 方法描述: 业务员修改
     * 入参描述: 业务员实体
     * 回调描述: 1:修改成功 0:修改失败  
     * 创建人:wisedata004  
     * 创建时间: 2017年7月8日 
     */
    public Map<String,Object> SalemanUpdate(Wbl_SalemanEntity SalemanEntity);
    
    /**
     * 方法名: SalemanAllList   
     * 方法描述: 业务员列表
     * 入参描述: pageNum:页码
     * 回调描述: 所有用户信息  
     * 创建人:wisedata004  
     * 创建时间: 2017年7月8日 
     */
    public Map<String,Object> SalemanAllList(Integer pageNum);
    
    
    
    
    /**
	 * 新增普通客户经理
	 * @param saleman
	 * @return
	 */
	public Integer insertSaleman(Saleman saleman,HttpSession session);
	
	/**
	 * 查询全部客户经理数据（系统管理员、客服）
	 * @param pageNum
	 * @return
	 */
	public Map<String,Object> selectAllSaleman(Integer pageNum,HttpSession session);
	
	/**
	 * 修改客户经理资料
	 * @param saleman
	 * @return
	 */
	public Integer updateSaleman(Saleman saleman,HttpSession session);
	
	/**
	 * 客户经理管理员查看其名下所有客户经理信息
	 * @param creatBy 客户经理管理员用户名，即客户经理添加人
	 * @return
	 */
	public Map<String,Object> selectSalemanByManager(Integer pageNum,HttpSession session);
	
	/**
	 * 生成客户经理推荐码
	 * @param phone
	 * @return
	 */
	public String creatCode(String phone);
	
	/**
	 * 根据ID查询客户信息
	 * @param id
	 * @param session
	 * @return
	 */
	public Map<String,Object> selectSaleman(Long id,HttpSession session);
	
	/**
	 * 客户新增客户经理获取上级管理员数据
	 * @param session
	 * @return
	 */
	public Map<String, Object> listSaleman(HttpSession session);
	
	/**
	 * 客服新增客户经理
	 * @param saleman
	 * @param session
	 * @return
	 */
	public Integer insertSalemanBykefu(Saleman saleman,HttpSession session);
}
