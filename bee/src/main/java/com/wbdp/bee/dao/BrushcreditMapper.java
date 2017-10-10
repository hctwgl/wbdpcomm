package com.wbdp.bee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.BrushcreditEntity;


/**
 * 维泽任性刷数据接口类
 * @author 汪赛军
 * date:2017年9月8日下午5:34:29
 *
 */
public interface BrushcreditMapper {
	
	/**
	 * 
	 * 查询全部任性刷数据
	 * @param pageNum
	 * @return
	 */
	public List<Map<String,Object>> selectAllBrushcredit(@Param("pageNum")Integer pageNum);
	
	/**
	 * 
	 * 查询全部任性刷等待审核数据
	 * @param pageNum
	 * @return
	 */
	public List<Map<String,Object>> selectAllBrushcreditWait(@Param("pageNum")Integer pageNum);
	
	/**
	 * 查询全部任性刷等待审核数据量
	 * @return
	 */
	public Integer getCountWait();
	
	/**
	 * 查询全部任性刷数据量
	 * @return
	 */
	public Integer getCount();
	
	/**
	 * 客户经理只能查询对应他的客户任性刷记录
	 * @param pageNum
	 * @param recomCode
	 * @return
	 */
	public List<Map<String,Object>> selectAllBrushcreditBySaleman(@Param("pageNum")Integer pageNum,@Param("recomCode")String recomCode);
	
	/**
	 * 客户经理查询任性刷记录数量
	 * @param recomCode
	 * @return
	 */
	public Integer getCountBySaleman(@Param("recomCode")String recomCode);
	
	/**
	 * 客户经理管理员查询客户任性刷记录
	 * @param pageNum
	 * @param recomCode
	 * @param username
	 * @return
	 */
	public List<Map<String,Object>> selectAllBrushcreditBySalemanMan(@Param("pageNum")Integer pageNum,@Param("recomCode")String recomCode,@Param("username")String username);
	
	/**
	 * 客户经理管理员查询客户任性刷数量
	 * @param recomCode
	 * @param username
	 * @return
	 */
	public Integer getCountBySalemanMan(@Param("recomCode")String recomCode,@Param("username")String username);
	/**
	 * 审核刷脸未通过的任性刷记录
	 * @param id 任性刷ID
	 * @return
	 */
	public Integer reviewFaceStatus(@Param("id")Long id,@Param("faceStatus")Integer faceStatus);
	
	/**
	 * 查询任性刷审核数据
	 * @param brushid
	 * @return
	 */
	public Map<String,Object> brushReview(@Param("brushid")Long brushid);
	
	/**
	 * 批量保存任性刷记录
	 * @param list
	 * @return
	 */
	public Integer batchSaveBrush(List<BrushcreditEntity> list);
	
	/**
	 * 客户经理单独为客户新增套餐
	 * @param brushcreditEntity
	 * @return
	 */
	public Integer insertBrush(BrushcreditEntity brushcreditEntity);
	
	/**
	 * 客服查询本月记录
	 * @return
	 */
	public List<Map<String,Object>> selectBrushThisMouth(@Param("pageNum") Integer pageNum);
	
	/**
	 * 客户获取本月记录数量
	 * @return
	 */
	public Integer countBrushThisMouth();
	/**
	 * 客户经理查询本月记录
	 * @return
	 */
	public List<Map<String,Object>> selectBrushThisMouthBySale(@Param("pageNum") Integer pageNum,@Param("recomCode")String recomCode);
	
	/**
	 * 客户经理获取本月记录数量
	 * @return
	 */
	public Integer countBrushThisMouthBySale(@Param("recomCode")String recomCode);
	
	/**
	 * 客户管理员查询本月记录
	 * @return
	 */
	public List<Map<String,Object>> selectBrushThisMouthBySaleMan(@Param("pageNum")Integer pageNum,@Param("recomCode")String recomCode,@Param("username")String username);
	
	/**
	 * 客户管理员获取本月记录数量
	 * @return
	 */
	public Integer countBrushThisMouthBySaleMan(@Param("recomCode")String recomCode,@Param("username")String username);
	
	/**
	 * 客服查询上月记录
	 * @return
	 */
	public List<Map<String,Object>> selectBrushLastMouth(@Param("pageNum") Integer pageNum);
	
	/**
	 * 客服获取上月记录数量
	 * @return
	 */
	public Integer countBrushLastMouth();
	/**
	 * 客户经理查询上月记录
	 * @return
	 */
	public List<Map<String,Object>> selectBrushLastMouthBySale(@Param("pageNum") Integer pageNum,@Param("recomCode")String recomCode);
	
	/**
	 * 客户经理获取上月记录数量
	 * @return
	 */
	public Integer countBrushLastMouthBySale(@Param("recomCode")String recomCode);
	
	/**
	 * 客户管理员查询上月记录
	 * @return
	 */
	public List<Map<String,Object>> selectBrushLastMouthBySaleMan(@Param("pageNum")Integer pageNum,@Param("recomCode")String recomCode,@Param("username")String username);
	
	/**
	 * 客户管理员获取上月记录数量
	 * @return
	 */
	public Integer countBrushLastMouthBySaleMan(@Param("recomCode")String recomCode,@Param("username")String username);
} 
