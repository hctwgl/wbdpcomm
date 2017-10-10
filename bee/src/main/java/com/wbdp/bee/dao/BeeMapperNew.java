package com.wbdp.bee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_BeeEntity;

/**
 * 新版一分期后台客户管理数据接口类
 * @author 汪赛军
 * date:2017年9月7日上午10:33:13
 *
 */
public interface BeeMapperNew {
	/**
	 * 查询全部客户信息，分页（客服）
	 * @return
	 */
	public List<Map<String,Object>> selectAllBee(Integer pageNum);
	
	/**
	 * 客服删除客户（假删）
	 * @param id
	 * @return
	 */
	public Integer delBee(Long id);
	
	
	/**
	 * 查询全部客户时查询其数量，用于分页
	 * @return
	 */
	public Integer selectAllBeeCount();
	
	/**
	 * 客户经理只能查看其名下的客户（通过业务员推荐码匹配任性刷记录中的推荐码）
	 * code 业务员推荐码 pageNum 第几页
	 * @return
	 */
	public List<Map<String,Object>> selectSaleManBee(@Param("code")String code,@Param("pageNum")Integer pageNum);
	
	/**
	 * 查询客户经理名下客户数量
	 * @param code
	 * @return
	 */
	public Integer selectSaleManBeeCount(@Param("code")String code);
	/**
	 * 客户管理员查询客户列表
	 * @param username
	 * @param pageNum
	 * @return
	 */
	public List<Map<String,Object>> selectBeeBySaleMan(@Param("username")String username,@Param("pageNum")Integer pageNum);
	/**
	 * 客户管理员查询客户列表
	 * @param username
	 * @param pageNum
	 * @return
	 */
	public Integer selectBeeBySaleManCount(@Param("username")String username);
	 /**
     * 用户上传集团客户数据，保存至数据库
     * @param beeList
     * @return
     */
    public Integer uploadClient(@Param("beeList")List<Wbl_BeeEntity> beeList);
    
    /**
     * 客户经理客户管理页面新增公对公客户
     * @param wbl_BeeEntity
     * @return
     */
    public Integer insertBeeBySaleMan(Wbl_BeeEntity wbl_BeeEntity);
}
