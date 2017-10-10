package com.wbdp.bee.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.wbdp.bee.entity.Wbl_CompblacklistEntity;


public interface Wbl_CompblacklistDAO {

	/**
	 * 方法名: CompblackAllList   
	 * 方法描述: 运营商黑名单列表
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月14日
	 */
	public List<Wbl_CompblacklistEntity> CompblackAllList(@Param("pageNum")Integer pageNum);
	
	/**
	 * 获取公司黑名单数据条数（用于分页）
	 * @return
	 */
	public Integer getCount();
	
	
	/**
	 * 方法名: insertCompblack   
	 * 方法描述: 添加运营商黑名单 
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月14日
	 */
	public Integer insertCompblack(Wbl_CompblacklistEntity CompblacklistEntity);
	
	/**
	 * 将公司从黑名单清除
	 * @param id
	 * @return
	 */
	public Integer deleteCompblack(@Param("id")Long id);
	
	/**
	 * 查询是否有重复数据
	 * @param company
	 * @return
	 */
	public String getCompany(@Param("company")String company);
}