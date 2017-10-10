package com.wbdp.wx.controller.shopCart;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wbdp.wx.constant.HTMLStatic;
import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.model.AddOrder;
import com.wbdp.wx.model.GoodCSO;
import com.wbdp.wx.model.Result;
import com.wbdp.wx.service.shopCart.ShopCartService;

@Controller
public class ShopCartController {

	@Autowired
	private ShopCartService shopCartService;

    /**
     * 授权跳转购物车
     * @param code
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "toShopCart",method=RequestMethod.GET)
    public String toShopCart(@PathParam("code") String code, HttpSession session) throws CustomException {
        return shopCartService.toShopCart(code,session);
    }


    /**
     * 加入购物车和立即购买前判断是否注册了用户
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "isSCRegist",method=RequestMethod.GET)
    @ResponseBody
    public Result isSCRegist(HttpSession  session)throws CustomException {
        return shopCartService.isSCRegist(session);
    }

    /**
     * 查看购物车
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getShopCart",method=RequestMethod.GET)
    @ResponseBody
    public Result getShopCart(HttpSession session)throws CustomException {
        return shopCartService.getShopCart(session);
    }
    
    /**
     * 跳转付款页面
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "toSCPay",method=RequestMethod.GET)
    public String toSCPay()throws CustomException {
        return  HTMLStatic.PAYTWO;
    }
    /**
     * 付款页面获取信息
     * @param session
     * @param goodCSO
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getSCGoodsInfoInPay",method=RequestMethod.POST)
    @ResponseBody
    public Result getGoodsInfoInPay(HttpSession session,@Valid GoodCSO goodCSO )throws CustomException {
        return shopCartService.getGoodsInfoInPay(session,goodCSO);
    }
    
    /**
     * 添加订单
     * @param session
     * @param addOrder
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "addSCUserOrder",method=RequestMethod.POST)
    @ResponseBody
    public Result addOrder(HttpSession session,@Valid AddOrder addOrder)throws CustomException {
    	System.out.println("从购物车添加最终订单");
        return shopCartService.addOrder(session,addOrder);
    }
   /**
    * 删除购物车
    * @param session
    * @param cartIDarry
    * @return
    * @throws CustomException
    */
    @RequestMapping(value = "deleteCarts",method=RequestMethod.POST)
    @ResponseBody
    public Result deleteCarts(HttpSession session,@RequestParam(value = "cartIDarry[]",required = false,defaultValue = "") long[] cartIDarry)throws CustomException {
        return shopCartService.deleteCarts(session,cartIDarry);
    }
}
