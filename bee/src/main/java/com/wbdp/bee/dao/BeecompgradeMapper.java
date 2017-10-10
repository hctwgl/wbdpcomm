package com.wbdp.bee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.BeecompgradeNewEntity;


/**
 * 公司信用管理数据接口
 * @author 汪赛军
 * date:2017年9月7日下午2:53:20
 *
 */
public interface BeecompgradeMapper {
	
	/**
	 * 查看公司信息(非拉黑)
	 * @param pageNum
	 * @return
	 */
	public List<BeecompgradeNewEntity> selectBeeCompany(@Param("pageNum")Integer pageNum);
	
	/**
	 * 查看公司信息(已拉黑)
	 * @param pageNum
	 * @return
	 */
	public List<BeecompgradeNewEntity> selectBeeCompanyBlack(@Param("pageNum")Integer pageNum);
	
	/**
	 * 查询公司数量，用于分页(非拉黑)
	 * @return
	 */
	public Integer getCount();
	/**
	 * 查询公司数量，用于分页(已拉黑)
	 * @return
	 */
	public Integer getCountBlack();

	/**
	 * 根据ID查询公司数据
	 * @param id
	 * @return
	 */
	public BeecompgradeNewEntity selectBeeCompanyByID(@Param("id")Long id);
	
	/**
	 * 新增公司信息
	 * @param beecompgradeNewEntity
	 * @return
	 */
	public Integer insertBeeCompany(BeecompgradeNewEntity beecompgradeNewEntity);
	
	/**
	 * 修改公司评级，即客服进行评分
	 * @param beecompgradeNewEntity
	 * @return
	 */
	public Integer updateCompanyGrade(BeecompgradeNewEntity beecompgradeNewEntity);
	
	/**
	 * 拉黑公司
	 * @param beecompgradeNewEntity
	 * @return
	 */
	public Integer blackCompany(BeecompgradeNewEntity beecompgradeNewEntity);
	/**
	 * 将公司从黑名单清除
	 * @param beecompgradeNewEntity
	 * @return
	 */
	public Integer outBlackCompany(@Param("id")Long id);
	
	/**
	 * 获取公司列表
	 * @return
	 */
	public List<BeecompgradeNewEntity> listCompany(@Param("phone")String phone);

	/**
	 * 客户经理管理员获取公司列表
	 * @return
	 */
	public List<BeecompgradeNewEntity> listCompanyByMan(@Param("phone")String phone);
	/**
	 * 客服审核公司完毕
	 * @param id
	 * @param checkStatus
	 * @return
	 */
	public Integer companyYseOrNo(BeecompgradeNewEntity beecompgradeNewEntity);
	

	/**
	 * 客户经理获取公司列表
	 * @return
	 */
	public List<BeecompgradeNewEntity> listCompanyBySale(@Param("phone")String phone);
	
	/**
	 * 客户经理获取数量
	 * @param phone
	 * @return
	 */
	public Integer listCompanyCount(@Param("phone")String phone);
	
	/**
	 * 客户经理管理员获取数量
	 * @param phone
	 * @return
	 */
	public Integer listCompanyByManCount(@Param("phone")String phone);
	
	/**
	 * 修改公司信息
	 * @param beecompgradeNewEntity
	 * @return
	 */
	public Integer updateBeeCompany(BeecompgradeNewEntity beecompgradeNewEntity);
} 
