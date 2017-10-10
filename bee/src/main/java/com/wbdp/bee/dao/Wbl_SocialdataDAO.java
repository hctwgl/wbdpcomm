package com.wbdp.bee.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_SocialdataEntity;




public interface Wbl_SocialdataDAO {

	
	/**
	 * 方法名: getSocialdataUserAndPass   
	 * 方法描述: 根据客户id，获取社保账号,密码,城市
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月13日
	 */
	public Map<String,String> getSocialdataUserAndPass(@Param("beeid")Long beeid);
	
	

	/**
	 * 方法名: updateSocialdata   
	 * 方法描述: 更新社保信息
	 * 入参描述: corpName:公司名称,bal:
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月13日
	 */
	public Integer updateSocialdata(@Param("beeid")Long beeid,
			                        @Param("corpname")String corpName,
			                        @Param("bal")Integer bal,
			                        @Param("basedeposit")Integer baseDeposit,
			                        @Param("number")Integer numBer,
			                        @Param("huafentotal")Integer huafenTotal,
			                        @Param("nowtime")String nowTime);
	
	/**
	 * 获取社保以及关联数据列表
	 * @return
	 */
	public List<Wbl_SocialdataEntity> listWblSocialdata(@Param("pageNum")Integer pageNum);
	
	/**
	 * 获取社保以及关联数据列表符合条件数据条数(用于分页)
	 * @return
	 */
	public List<Wbl_SocialdataEntity> listCount();
}