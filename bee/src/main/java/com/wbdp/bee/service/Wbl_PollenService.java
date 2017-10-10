package com.wbdp.bee.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_UserEntity;

public interface Wbl_PollenService {

    
	/**
	 * 方法名: getPollen  
	 * 方法描述: 微信花粉额度(保存到数据库) 
	 * 入参描述: beeid:客户id，socialaccount:社保账号,socialpassword:社保密码
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月13日
	 */
    public String getPollen(Long beeid);
    
    
	/**
	 * 方法名: getPollenTest   
	 * 方法描述: 后台试算花粉额度(不保存到数据库) 
	 * 入参描述: beeid:客户id，socialaccount:社保账号,socialpassword:社保密码
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月13日
	 */
    public String getPollenTest(Long beeid);
    
    
   
}
