package com.wbdp.bee.service;

import java.util.List;
import java.util.Map;






import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.wbdp.bee.entity.CompblacklistNewEntity;
import com.wbdp.bee.entity.Wbl_CompblacklistEntity;

/**
 * 公司黑名单业务接口类
 * @author 汪赛军
 * date:2017年7月22日上午10:13:25
 *
 */
public interface Wbl_CompblacklistService {
	/**
	 * 方法名: CompblackAllList   
	 * 方法描述: 运营商黑名单列表
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月14日
	 */
	public Map<String,Object> CompblackAllList(Integer pageNum);
	
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
	public Integer insertCompblack(String json,HttpSession session);
	
	/**
	 * 将公司从黑名单清除
	 * @param id
	 * @return
	 */
	public Integer deleteCompblack(String json);
	
	
	
	
	
	
	
	/**
	 * 添加公司黑名单
	 * @param compblacklistNewEntity
	 * @param session
	 * @return
	 */
	public Integer insertCompBlack(CompblacklistNewEntity compblacklistNewEntity,HttpSession session);
	
	/**
	 * 获取公司黑名单列表
	 * @param pageNum
	 * @param session
	 * @return
	 */
	public Map<String,Object> selectCompBlack(Integer pageNum,HttpSession session);
}
