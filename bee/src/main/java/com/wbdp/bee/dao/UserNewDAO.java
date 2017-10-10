package com.wbdp.bee.dao;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.UserNewEntity;

/**
 * 新版一分期用户数据接口
 * @author 汪赛军
 * date:2017年9月10日上午10:03:43
 *
 */
public interface UserNewDAO {
	/**
	 * 新增用户
	 * @param userNewEntity
	 * @return
	 */
	public Integer insertUser(UserNewEntity userNewEntity);
	
	/**
	 * 充值密码
	 * @param id
	 * @param oldPass
	 * @param newPass
	 * @return
	 */
	public Integer updatePassWord(Long id,String oldPass,String newPass);
	
	/**
	 * 用户登录
	 * @param userNewEntity
	 * @return
	 */
	public UserNewEntity userLogin(@Param("userName")String userName,@Param("passWord")String passWord);
	
	/**
	 * 用户重置密码
	 * @param userNewEntity
	 * @return
	 */
	public Integer resetPass(UserNewEntity userNewEntity);
	
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 */
	public UserNewEntity selectUserByID(@Param("id")Long id);
	
	/**
	 * 修改用户
	 * @param userNewEntity
	 * @return
	 */
	public Integer updateUser(UserNewEntity userNewEntity);
}
