package com.wbdp.bee.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * 商品业务逻辑接口类
 * @author 汪赛军
 * date:2017年7月13日上午9:42:34
 *
 */
public interface Wbl_GoodsService {
	/**
	 * 新增商品
	 * @param json
	 * @return
	 */
	public Map<String,Object> addGoods(String json,HttpSession session);
	
	/**
	 * 商品列表
	 * @param json
	 * @return
	 */
	public Map<String,Object> goodsList(Integer pageNum,HttpSession session);
	
	/**
	 * 修改商品sku上下架状态
	 * @param json
	 * @return
	 */
	public Map<String,Object> updateStatus(String json);
}
