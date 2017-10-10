package com.wbdp.bee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.CompblacklistNewEntity;


/**
 * 一分期新版公司黑名单管理
 * @author 汪赛军
 * date:2017年9月8日上午9:38:52
 *
 */
public interface CompblacklistMapper {
	/**
	 * 新增公司黑名单
	 * @param compblacklistNewEntity
	 * @return
	 */
	public Integer insertCompBlack(CompblacklistNewEntity compblacklistNewEntity);
	
	/**
	 * 查看全部公司黑名单（客服、系统管理员）
	 * @param pageNum
	 * @return
	 */
	public List<CompblacklistNewEntity> selectAllCompBlack(@Param("pageNum")Integer pageNum);
	
	/**
	 * 客户经理查看他名下的公司黑名单，即该公司添加人是该客户经理
	 * @param phone
	 * @param pageNum
	 * @return
	 */
	public List<CompblacklistNewEntity> selectSalemanCompBlack(@Param("phone")String phone,@Param("pageNum")Integer pageNum);
	
	/**
	 * 获取黑名单数量，用于分页
	 * @return
	 */
	public Integer getCount();
	
	/**
	 * 删除公司黑名单数据
	 * @param id
	 * @return
	 */
	public Integer delCompBlack(@Param("id")Long id);
} 
