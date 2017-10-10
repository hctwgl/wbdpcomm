package com.wbdp.bee.service;


import java.util.Map;

import javax.servlet.http.HttpSession;


/**
 * 购物车业务接口类
 * @author 汪赛军
 * date:2017年7月18日上午9:34:04
 *
 */
public interface Wbl_ShoppingcartService {
	/**
	 * 获取购物车以及关联查询列表（分页）
	 * @param pageNum
	 * @return
	 */
	public Map<String,Object> listShoppingcart(Integer pageNum,HttpSession session);
	
	/**
	 * 获取购物车以及关联数据总页数
	 * @return
	 */
	public Integer getPage();
}
