package com.wbdp.wx.service.shopCart;

import javax.servlet.http.HttpSession;

import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.model.AddOrder;
import com.wbdp.wx.model.GoodCSO;
import com.wbdp.wx.model.Result;

public interface ShopCartService {
	/**
	 * 跳转购物车
	 * @param code
	 * @param session
	 * @return
	 * @throws CustomException 
	 */
	String toShopCart(String code, HttpSession session) throws CustomException;
	/**
	 * 查看购物车
	 * @param session
	 * @return
	 */
	Result getShopCart(HttpSession session)throws CustomException;
	/**
	 * 判断是否注册
	 * @param session
	 * @return
	 */
	Result isSCRegist(HttpSession session)throws CustomException;
	/**
	 * 付款页面获取信息
	 * @param session
	 * @param goodCSO
	 * @return
	 * @throws CustomException
	 */
	Result getGoodsInfoInPay(HttpSession session, GoodCSO goodCSO)throws CustomException;
	/**
	 * 添加订单
	 * @param session
	 * @param addOrder
	 * @return
	 * @throws CustomException 
	 */
	Result addOrder(HttpSession session, AddOrder addOrder) throws CustomException;
	
	/**
	 * 删除购物车
	 * @param session
	 * @param cartIDarry
	 * @return
	 * @throws CustomException
	 */
	Result deleteCarts(HttpSession session, long[] cartIDarry)throws CustomException;

}
