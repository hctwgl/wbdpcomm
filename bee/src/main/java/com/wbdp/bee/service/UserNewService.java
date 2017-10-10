package com.wbdp.bee.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.wbdp.bee.entity.UserNewEntity;
import com.wbdp.bee.entity.Wbl_UserEntity;

/**
 * 一分期新版用户业务员处理接口
 * @author 汪赛军
 * date:2017年9月5日上午11:40:02
 *
 */
public interface UserNewService {
	/**
	 * 用户登录
	 * @param userName
	 * @param userType
	 * @param passWord
	 * @param session
	 * @return
	 */
	public Map<String,Object> userLogin(String userName,Integer userType,String passWord,HttpSession session);
	
	/**
	 * 获取用户列表
	 * @param pageNum
	 * @param session
	 * @return
	 */
	public Map<String,Object> beeList(Integer pageNum,HttpSession session);
	
	/**
	 * 新增用户，每个类型的用户赋予不同的权限
	 * @param userNewEntity
	 * @return
	 */
	public Integer userAdd(UserNewEntity userNewEntity);
	
	/**
	 * 用户登录后跳转页面
	 * @param session
	 * @return
	 */
	public Map<String,Object> toPage(HttpSession session);
	
	/**
	 * 用户重置密码
	 * @param passWordold
	 * @param passWordnew
	 * @param session
	 * @return
	 */
	public Map<String,Object> resetPassWord(String passWordold,String passWordnew,HttpSession session);
	
	/**
	 * 跳转到修改页面，并传递数据
	 * @param id
	 * @param session
	 * @return
	 */
	public Map<String,Object> toUpdatePage(Long id,HttpSession session);
	
	/**
	 * 修改用户
	 * @param userNewEntity
	 * @param session
	 * @return
	 */
	public Integer updateUser(UserNewEntity userNewEntity,HttpSession session);
}
