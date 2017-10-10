package com.wbdp.bee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Compre;


/**
 * 公司综合数据接口类
 * @author 汪赛军
 * date:2017年9月16日下午6:45:44
 *
 */
public interface CompreMapper {

	/**
	 * 批量新增公司综合数据
	 * @param list
	 * @return
	 */
	public Integer batchInsertCompre(@Param("list")List<Compre> list);

	/**
	 * 新增公司综合数据
	 * @param compre
	 * @return
	 */
	public Integer insertCompre(Compre compre);
	
	/**
	 * 客户经理查询公司综合数据列表
	 * @param pageNum
	 * @param username
	 * @return
	 */
	public List<Map<String, Object>> selectCompre(@Param("pageNum")Integer pageNum,@Param("username")String username);
	
	/**
	 * 客户经理取数量
	 * @param pageNum
	 * @param username
	 * @return
	 */
	public Integer getCountByManeger(@Param("username")String username);
	
	/**
	 * 客户管理员查询公司综合数据列表
	 * @param pageNum
	 * @param username
	 * @return
	 */
	public List<Map<String, Object>> selectCompreByman(@Param("pageNum")Integer pageNum,@Param("username")String username);
	
	/**
	 * 客户经理管理员取数量
	 * @param pageNum
	 * @param username
	 * @return
	 */
	public Integer getCount(@Param("username")String username);
	/**
	 * 查询公司综合数据列表
	 * @param pageNum
	 * @param username
	 * @return
	 */
	public List<Map<String, Object>> selectCompreAll(@Param("pageNum")Integer pageNum);
	
	/**
	 * 获取数量
	 * @param pageNum
	 * @return
	 */
	public Integer countCompreAll();
} 
