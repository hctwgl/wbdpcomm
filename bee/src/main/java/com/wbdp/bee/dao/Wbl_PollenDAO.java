package com.wbdp.bee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_PollenEntity;





public interface Wbl_PollenDAO {
   
	/**
	 * 方法名: checkBeeid   
	 * 方法描述: 检查该客户是否存在表中(即是否第一次申请信用)
	 * 入参描述: beeid
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月13日
	 */
	public Integer checkBeeid(@Param("beeid")Long beeid);
	
	/**
	 * 方法名: checkBeeidOfDate   
	 * 方法描述: 检查该客户上次查询时间是否大于一个月
	 * 入参描述: beeid
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月13日
	 */
	public boolean checkBeeidOfDate(@Param("beeid")Long beeid);
	
	/**
	 * 方法名: insertPollen   
	 * 方法描述: 客户第一次申请后,增加额度客户
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月13日
	 */
	public Integer insertPollen(@Param("beeid")Long beeid,
			                    @Param("huafentotal")Integer huafenTotal,
			                    @Param("nowtime")String nowTime);
	
	
	/**
	 * 方法名: updatePollen   
	 * 方法描述: 客户再次点击获取额度后,修改额度 
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月13日
	 */
	public Integer updatePollen(@Param("beeid")Long beeid,
                                @Param("huafentotal")Integer huafenTotal,
                                @Param("nowtime")String nowTime);
	
	/**
	 * 根据客户id获取用户额度
	 * @param beeid
	 * @return
	 */
	public Wbl_PollenEntity getWbl_PollenEntity(@Param("beeid")Long beeid);
	
	
	/**
	 * 方法名: getPollenInfoOfBee   
	 * 方法描述: 根据客户id获取计算花粉额度的条件(社保账号密码,车险号码,信用卡号)
	 * 入参描述: 
	 * 回调描述: {SocialPassWord=46df37e3be90c0658087945a58bccade, SocialAccount=SSA78, ID=10, BeeName=陈梓平, InsuranceNum=998998849849498, BankName=中国工行, CreditCard=8254784169874123654784}  
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月21日
	 */
	public Map<String,Object> getPollenInfoOfBee(@Param("beeid")Long beeid);
	
	/**
	 * 审核通过后修改用户额度
	 * @param beeid
	 * @param UsedCredit
	 * @return
	 */
	public Integer updatePollenReview(@Param("beeid")Long beeid,@Param("usedCredit")Integer usedCredit);
	
	/**
	 * 根据客户id查询已用额度
	 * @param beeid
	 * @return
	 */
	public Integer getUsedCredit(@Param("beeid")Long beeid);
	
	/**
	 * 根据公司名称查询公司最大额度
	 * @param company
	 * @return
	 */
	public Integer getCompanyMaxAmount(@Param("company")String company);
}