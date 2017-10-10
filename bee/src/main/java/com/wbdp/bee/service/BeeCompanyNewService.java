package com.wbdp.bee.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.wbdp.bee.entity.BeecompgradeNewEntity;

/**
 * 新版一分期公司信息管理业务接口类
 * @author 汪赛军
 * date:2017年9月7日下午3:30:01
 *
 */
public interface BeeCompanyNewService {
	
	/**
	 * 查看公司信息
	 * @param pageNum
	 * @return
	 */
	public Map<String,Object> selectBeeCompany(HttpSession session,Integer pageNum);
	
	/**
	 * 根据ID查询公司数据
	 * @param id
	 * @return
	 */
	public Map<String,Object> selectBeeCompanyByID(HttpSession session,Long id);
	
	/**
	 * 新增公司信息
	 * @param beecompgradeNewEntity
	 * @return
	 */
	public Map<String,Object> insertBeeCompany(HttpSession session,BeecompgradeNewEntity beecompgradeNewEntity);
	
	/**
	 * 修改公司评级，即客服进行评分
	 * @param beecompgradeNewEntity
	 * @return
	 */
	public Map<String,Object> updateCompanyGrade(HttpSession session,BeecompgradeNewEntity beecompgradeNewEntity);
	
	/**
	 * 对公司执行拉黑操作
	 * @param beecompgradeNewEntity
	 * @param session
	 * @return
	 */
	public Integer blackBeeComp(BeecompgradeNewEntity beecompgradeNewEntity,HttpSession session);
	
	/**
	 * 查看公司黑名单信息
	 * @param pageNum
	 * @return
	 */
	public Map<String,Object> selectBeeCompanyBlack(HttpSession session,Integer pageNum);
	
	/**
	 * 将公司从黑名单清除
	 * @param beecompgradeNewEntity
	 * @return
	 */
	public Integer outBlackCompany(Long id);
	
	/**
	 * 获取公司列表
	 * @return
	 */
	public Map<String,Object> listCompany(HttpSession session);
	
	/**
	 * 客服审核公司信用并评级
	 * @param id
	 * @param session
	 * @return
	 */
	public Map<String,Object> reviewCompany(Long id,HttpSession session);
	/**
	 * 客服审核公司完毕
	 * @param id
	 * @param checkStatus
	 * @param session
	 * @return
	 */
	public Integer companyYseOrNo(BeecompgradeNewEntity beecompgradeNewEntity,HttpSession session);
	
	/**
	 * 跳转公司资料修改页面，并传递数据
	 * @return
	 */
	public Map<String,Object> toupdateCompany(Long id,HttpSession session);
	
	/**
	 * 修改公司信息
	 * @param beecompgradeNewEntity
	 * @return
	 */
	public Map<String,Object> updateBeeCompany(HttpSession session,BeecompgradeNewEntity beecompgradeNewEntity);
}
