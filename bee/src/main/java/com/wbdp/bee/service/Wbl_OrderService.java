package com.wbdp.bee.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * 订单业务接口类
 * @author 汪赛军
 * date:2017年7月17日下午3:28:49
 *
 */
public interface Wbl_OrderService {

	/**
	 * 获取订单列表（分页）
	 * @param pageNum
	 * @return
	 */
	public Map<String,Object> listOrder(Integer pageNum,HttpSession session);
	
	/**
	 * ajax定时刷新获取未审核订单数量
	 * @return
	 */
	public Integer getOrderCount();
	
	/**
	 * 审核订单并修改订单状态
	 * @param json
	 * @return
	 */
	public Integer updateOrder(String json);
	
	/**
	 * 将订单审核资料发送至页面
	 * @return
	 */
	public Map<String,Object> reviewOrder(String beeid,String id);
}
