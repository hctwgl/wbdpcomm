package com.wbdp.wx.controller.phoneShop;

import com.wbdp.wx.constant.HTMLStatic;
import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.model.*;
import com.wbdp.wx.service.PhoneShop.PhoneShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

/**
 * 手机商城
 * Created by wisedata005 on 2017/7/5.
 */
@Controller
public class PhoneShopController {

    /**手机商城业务层*/
    @Autowired
    private PhoneShopService phoneShopService;

    /**
     * 授权跳转
     * @param code
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "toPhoneShop",method=RequestMethod.GET)
    public String toPhoneShop(@PathParam("code") String code, HttpSession session) throws CustomException {
        return phoneShopService.toPhoneShop(code,session);
    }

    /**
     * 全部-分页查询商品
     * @param session
     * @param limit
     * @param type
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getGoods",method=RequestMethod.GET)
    @ResponseBody
    public Result getGoods(HttpSession  session,@RequestParam("limit") int limit,@RequestParam("type") int type )throws CustomException {
        return phoneShopService.getGoods(session,limit,type);
    }

    /**
     * 获取手机品牌类型
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getPhoneType",method=RequestMethod.GET)
    @ResponseBody
    public Result getPhoneType(HttpSession  session)throws CustomException {
        return phoneShopService.getPhoneType(session);
    }

    /**
     * 手机品牌-分页查询商品
     * @param session
     * @param selectPhoneTypeSort
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getGoodsByPhoneTypeID",method=RequestMethod.GET)
    @ResponseBody
    public Result getGoodsByPhoneTypeID(HttpSession  session, @Valid SelectPhoneTypeSort selectPhoneTypeSort)throws CustomException {
        return phoneShopService.getGoodsByPhoneTypeID(session,selectPhoneTypeSort);
    }

    /**
     * 获取运营商
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getOperator",method=RequestMethod.GET)
    @ResponseBody
    public Result getOperator(HttpSession  session)throws CustomException {
        return phoneShopService.getOperator(session);
    }


    /**
     * 运营商-分页查询商品
     * @param session
     * @param operatorTypeSort
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getGoodsByOperator",method=RequestMethod.GET)
    @ResponseBody
    public Result getGoodsByOperator(HttpSession  session, @Valid OperatorTypeSort operatorTypeSort)throws CustomException {
        return phoneShopService.getGoodsByOperator(session,operatorTypeSort);
    }

    /**
     * 跳转商品详情页面
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "toGoodsDetails",method=RequestMethod.GET)
    public String toGoodsDetails()throws CustomException {
        return  HTMLStatic.GOODSDETAIL;
    }


    /**
     * 获取商品详情
     * @param goodID
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getGoodsDetails",method=RequestMethod.GET)
    @ResponseBody
    public  Result getGoodsDetails(@RequestParam(value = "goodID" )int goodID)throws CustomException {
        return phoneShopService.getGoodsDetails(goodID);
    }

    /**
     * 获取商品的颜色/存储/运营商选项
     * @param goodID
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getGoodsCSO",method=RequestMethod.GET)
    @ResponseBody
    public Result getGoodsCSO(@RequestParam(value = "goodID" )int goodID)throws CustomException {
        return phoneShopService.getGoodsColor(goodID);
    }

    /**
     * 根据商品的颜色/存储/运营商选项获取价格
     * @param goodCSO
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getGoodPriceByCSO",method=RequestMethod.GET)
    @ResponseBody
    public Result getGoodPriceByCSO(@Valid GoodCSO goodCSO)throws CustomException {
        return phoneShopService.getGoodPriceByCSO(goodCSO);
    }

    /**
     * 加入购物车和立即购买前判断是否注册了用户
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "isRegist",method=RequestMethod.GET)
    @ResponseBody
    public Result isRegist(HttpSession  session)throws CustomException {
        return phoneShopService.isRegist(session);
    }

    /**
     * 加入购物车
     * @param goodCSO
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "addOrder",method=RequestMethod.GET)
    @ResponseBody
    public Result addOrderByCSO(HttpSession session,@Valid GoodCSO goodCSO)throws CustomException {
        return phoneShopService.addOrderByCSO(session,goodCSO);
    }
    /**
     * 跳转购物车
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "toBuyCar",method=RequestMethod.GET)
    public String toBuyCar()throws CustomException {
        return  HTMLStatic.SHOPCART;
    }
    /**
     * 查看购物车
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getOrderInfo",method=RequestMethod.GET)
    @ResponseBody
    public Result getOrderInfo(HttpSession session)throws CustomException {
        return phoneShopService.getOrderInfo(session);
    }
    /**
     * 跳转付款页面
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "toPay",method=RequestMethod.GET)
    public String toPay()throws CustomException {
        return  HTMLStatic.PAY;
    }
    /**
     * 付款页面获取信息
     * @param session
     * @param goodCSO
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getGoodsInfoInPay",method=RequestMethod.GET)
    @ResponseBody
    public Result getGoodsInfoInPay(HttpSession session,@Valid GoodCSO goodCSO )throws CustomException {
        return phoneShopService.getGoodsInfoInPay(session,goodCSO);
    }

    /**
     * 添加订单
     * @param session
     * @param addOrder
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "addUserOrder",method=RequestMethod.GET)
    @ResponseBody
    public Result addOrder(HttpSession session,@Valid AddOrder addOrder)throws CustomException {
        return phoneShopService.addOrder(session,addOrder);
    }
    /**
     * 跳转人脸识别页面
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "toface",method=RequestMethod.GET)
    public String toface()throws CustomException {
        return "face";
    }
    /**
     * 保存人脸识别图片到session
     * @param oneserverId1
     * @return
     */
    @RequestMapping(value = "saveFace",method=RequestMethod.POST)
    @ResponseBody
    public  Result saveFace(HttpSession session,@RequestParam("oneserverId1") String oneserverId1
    		) throws CustomException {
        return phoneShopService.faceCheck(oneserverId1, session);
    }
    /**
     * 添加订单中的用户在线签名
     * @param session
     * @param addOrder
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "addUserOrdersign",method=RequestMethod.POST)
    @ResponseBody
    public Result addUserOnlinesign(HttpSession session,@Valid String onlineSign,@Valid Integer type)throws CustomException {
    	System.out.println("添加最终订单："+onlineSign);
        return phoneShopService.addUserOrder(session,onlineSign,type);
    }
    /**
     * 跳转在线签名页面
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "toOrderSign",method=RequestMethod.GET)
    public String toOrderSign()throws CustomException {
        return "sign";
    }
    /**
     * 跳转订单列表
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "toOrderList",method=RequestMethod.GET)
    public String toOrderList()throws CustomException {
        return  HTMLStatic.ORDERLIST;
    }
    /**
     * 查询用户订单
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getUserOrders",method=RequestMethod.GET)
    @ResponseBody
    public Result getUserOrders(HttpSession session)throws CustomException {
        return phoneShopService.getUserOrders(session);
    }
    /**
     * 根据城市拿手机
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getGoodsByCity",method=RequestMethod.POST)
    @ResponseBody
    public Result getGoodsByCity(HttpSession session,@Valid GoodsByCity goodsByCity)throws CustomException {
        return phoneShopService.getGoodsByCity(session,goodsByCity);
    }
    
    /**
     * 查看收货地址
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getGoodsAddress",method=RequestMethod.GET)
    @ResponseBody
    public Result getGoodsAddress(HttpSession session)throws CustomException {
        return phoneShopService.getGoodsAddress(session);
    }

    /**
     * 查询单个收货地址的信息
     * @param id
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getSingleGoodsAddress",method=RequestMethod.GET)
    @ResponseBody
    public Result selectSingleGoodsAddress(@RequestParam(value = "id" )long id)throws CustomException {
        return phoneShopService.selectSingleGoodsAddress(id);
    }

    /**
     * 修改收货地址
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "updateGoodsAddress",method=RequestMethod.POST)
    @ResponseBody
    public Result updateGoodsAddress(HttpSession session, @Valid UserGoodAddress userGoodAddress)throws CustomException {
        return phoneShopService.updateGoodsAddress(session,userGoodAddress);
    }

    /**
     * 添加收货地址
     * @param session
     * @param userGoodAddress
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "addGoodsAddress",method=RequestMethod.POST)
    @ResponseBody
    public Result addGoodsAddress(HttpSession session, @Valid UserGoodAddress userGoodAddress)throws CustomException {
        return phoneShopService.addGoodsAddress(session,userGoodAddress);
    }
    
    
    /**
     * 获取手机套餐
     * @param session
     * @param skuid
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getPhonePackage",method=RequestMethod.POST)
    @ResponseBody
    public Result getPhonePackage(HttpSession session, @RequestParam(value = "skuid" )long skuid)throws CustomException {
        return phoneShopService.getPhonePackage(session,skuid);
    }

    /**
     * 根据城市、运营商拿手机
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getGoodsByCityAndOtype",method=RequestMethod.POST)
    @ResponseBody
    public Result getGoodsByCityAndOtype(HttpSession session,@Valid GoodsByCity goodsByCity)throws CustomException {
        return phoneShopService.getGoodsByCityAndOtype(session,goodsByCity);
    }
    
    /**
     * 筛选商品
     * @param session
     * @param screenGoods
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getGoodsByScreen",method=RequestMethod.POST)
    @ResponseBody
    public Result getGoodsByScreen(HttpSession session,@RequestParam(value = "cityAndotype[]",required = false,defaultValue = "") String[] cityAndotype,
    		@RequestParam(value = "phoneTypeID[]",required = false,defaultValue = "") long[] phoneTypeID,@RequestParam(value = "type",required = false) int type,
    		@RequestParam(value = "limit",required = false) int limit)throws CustomException {
        return phoneShopService.getGoodsByScreen(session,cityAndotype,phoneTypeID,type,limit);
    }
}
