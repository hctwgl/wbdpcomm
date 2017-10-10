package com.wbdp.bee.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;



/**
 * 维泽任性刷业务接口类
 * @author 汪赛军
 * date:2017年9月8日下午6:31:27
 *
 */
public interface BrushcreditService {
	/**
	 * 查询全部
	 * @param pageNum
	 * @param session
	 * @return
	 */
	public Map<String,Object> selectAllBrushcredit(Integer pageNum,HttpSession session);
	
	
	/**
	 * 查询全部待审核
	 * @param pageNum
	 * @param session
	 * @return
	 */
	public Map<String,Object> selectAllBrushcreditWait(Integer pageNum,HttpSession session);
	
	/**
	 * 查询本月
	 * @param pageNum
	 * @param session
	 * @return
	 */
	public Map<String,Object> brushListTheMouth(Integer pageNum,HttpSession session);
	
	/**
	 * 查询上月
	 * @param pageNum
	 * @param session
	 * @return
	 */
	public Map<String,Object> brushListLastMouth(Integer pageNum,HttpSession session);
	
	/**
	 * 获取待审核数量
	 * @return
	 */
	public Integer getCountWait();
	
	/**
	 * 跳转审核任性刷
	 * @param brushid
	 * @param beeWX
	 * @param session
	 * @return
	 */
	public Map<String,Object> brushReview(Long brushid,String beeWX,HttpSession session);
	
	/**
	 * 开始审核任性刷
	 * @param brushid
	 * @param type
	 * @param session
	 * @return
	 */
	public Integer reviewBrush(Long brushid,String beeWX,Integer type,HttpSession session);
}
