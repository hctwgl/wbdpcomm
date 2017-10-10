package com.wbdp.bee.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_UserEntity;

public interface Wbl_UserService {


   /**
    * 方法名: UserLogin   
    * 方法描述: 用户登录
    * 入参描述: username(用户名),password(密码)
    * 回调描述: 该用户信息(返回为null为没有该用户)
    * 创建人:wisedata004  
    * 创建时间: 2017年7月6日 
    */
	public Map<String, Object> UserLogin(String username,String password);
	
	/**
	 * 方法名: UserInsert   
	 * 方法描述: 用户增加
	 * 入参描述: 用户实体
	 * 回调描述: 1:增加成功  0:增加失败 
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月6日 
	 */
	public Map<String, Object> UserInsert(Wbl_UserEntity UserEntity);
	
	/**
	 * 方法名: UserDelete   
	 * 方法描述: 用户删除
	 * 入参描述: 需删除的用户ID
	 * 回调描述: 1:删除成功 0:删除失败  
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月6日 
	 */
    public Map<String, Object> UserDelete(Long id);
    
	/**
	 * 方法名: UserStop   
	 * 方法描述: 用户停用
	 * 入参描述: 需停用的用户ID
	 * 回调描述: 1:停用成功 0:停用失败  
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月6日
	 */
    public Map<String, Object> UserStop(Long id);
    
    /**
     * 方法名: UserUpdate   
     * 方法描述: 用户修改
     * 入参描述: 用户实体
     * 回调描述: 1:修改成功 0:修改失败  
     * 创建人:wisedata004  
     * 创建时间: 2017年7月6日 
     */
    public Map<String, Object> UserUpdate(Wbl_UserEntity UserEntity);
    
    /**
     * 方法名: UserAllList   
     * 方法描述: 用户列表
     * 入参描述: 无
     * 回调描述: 所有用户信息  
     * 创建人:wisedata004  
     * 创建时间: 2017年7月6日 
     */
    public Map<String, Object> UserAllList(Integer pageNum);
   
    /**
     * 获取用户权限列表
     * @param json
     * @return
     */
    public Map<String, Object> listModel(String json);
    
    /**
     * 根据用户ID查询用户的权限
     * @param userID
     * @return
     */
    public Map<String, Object> selsectModel(Long userID);
    
    /**
     * 根据用户ID设置用户的权限
     * @param userID
     * @return
     */
    public Integer setModel(String[] list,String userID);
}
