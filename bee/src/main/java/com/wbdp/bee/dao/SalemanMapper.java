package com.wbdp.bee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Saleman;
import com.wbdp.bee.entity.UserNewEntity;


/**
 * 新版一分期客户经理数据接口类
 * @author 汪赛军
 * date:2017年9月8日下午1:45:38
 *
 */
public interface SalemanMapper {
	/**
	 * 新增普通客户经理
	 * @param saleman
	 * @return
	 */
	public Integer insertSaleman(Saleman saleman);
	
	/**
	 * 查询全部客户经理数据（系统管理员、客服）
	 * @param pageNum
	 * @return
	 */
	public List<Saleman> selectAllSaleman(@Param("pageNum")Integer pageNum);
	
	/**
	 * 查询全部客户经理数量
	 * @return
	 */
	public Integer getCount();
	
	/**
	 * 修改客户经理资料
	 * @param saleman
	 * @return
	 */
	public Integer updateSaleman(Saleman saleman);
	
	/**
	 * 新增客户经理查询是否存在相同手机号码
	 * @param phone
	 * @return
	 */
	public Integer checkPhone(@Param("phone")String phone);
	
	/**
	 * 客户经理管理员查看其名下所有客户经理信息
	 * @param creatBy 客户经理管理员用户名，即客户经理添加人
	 * @return
	 */
	public List<Saleman> selectSalemanByManager(@Param("creatBy")String creatBy,@Param("pageNum")Integer pageNum);
	
	/**
	 * 客户经理管理员查看其名下所有客户经理数量，用于分页
	 * @param creatBy
	 * @return
	 */
	public Integer getCountByManager(@Param("creatBy")String creatBy);
	
	/**
	 * 生成推荐码时判断是否存在相同推荐码
	 * @param code
	 * @return
	 */
	public Integer checkCode(@Param("code")String code);
	
	/**
	 * 客户经理登录
	 * @param phone
	 * @param code
	 * @return
	 */
	public Saleman salLogin(@Param("username")String username,@Param("passWord")String passWord);
	
	/**
	 * 根据id查询客户经理信息
	 * @param id
	 * @return
	 */
	public Saleman selectSalemanByID(@Param("id")Long id);
	
	/**
	 * 客服新增客户经理获取上级管理员数据
	 * @return
	 */
	public List<Saleman> listSaleman();
	
	/**
	 * 验证推荐码是否已存在
	 * @param recommend
	 * @return
	 */
	public Integer checkRecommend(@Param("recommend")String recommend);
	
	/**
	 * 客户经理重置密码
	 * @param userNewEntity
	 * @return
	 */
	public Integer resetPass(Saleman saleman);
	
} 
