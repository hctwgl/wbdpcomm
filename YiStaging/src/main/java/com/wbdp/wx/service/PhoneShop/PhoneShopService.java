package com.wbdp.wx.service.PhoneShop;

import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.model.*;

import javax.servlet.http.HttpSession;

/**
 * Created by wisedata005 on 2017/7/5.
 */
public interface PhoneShopService {


    /**
     * 跳转
     * @param code
     * @param session
     * @return
     */
    String toPhoneShop(String code, HttpSession session) throws CustomException;

    /**
     * 分页查询商品
     * @param session
     * @param limit
     * @param type
     * @return
     */
    Result getGoods(HttpSession session, int limit, int type);

    /**
     * 获取手机品牌类型
     * @param session
     * @return
     */
    Result getPhoneType(HttpSession session);

    /**
     * 手机品牌-分页查询商品
     * @param session
     * @param selectPhoneTypeSort
     * @return
     */
    Result getGoodsByPhoneTypeID(HttpSession session, SelectPhoneTypeSort selectPhoneTypeSort);

    /**
     * 获取运营商
     * @param session
     * @return
     */
    Result getOperator(HttpSession session);

    /**
     * 运营商-分页查询商品
     * @param session
     * @param operatorTypeSort
     * @return
     */
    Result getGoodsByOperator(HttpSession session, OperatorTypeSort operatorTypeSort);

    /**
     * 获取商品详情
     * @param goodID
     * @return
     */
    Result getGoodsDetails(int goodID);

    /**
     * 获取单个商品的颜色选项
     * @param goodID
     * @return
     */
    Result getGoodsColor(int goodID);

    /**
     * 根据商品的颜色/存储/运营商选项获取价格
     * @param goodID
     * @return
     */
    Result getGoodPriceByCSO(GoodCSO goodID);

    /**
     * 加入购物车和立即购买前判断是否注册了用户
     * @param session
     * @return
     */
    Result isRegist(HttpSession session);

    /**
     * 加入购物车
     *
     * @param session
     * @param goodCSO
     * @return
     * @throws CustomException 
     */
    Result addOrderByCSO(HttpSession session, GoodCSO goodCSO) throws CustomException;

    /**
     * 查看购物车
     * @param session
     * @return
     * @throws CustomException 
     */
    Result getOrderInfo(HttpSession session) throws CustomException;

    /**
     * 查看收货地址
     * @param session
     * @return
     * @throws CustomException 
     */
    Result getGoodsAddress(HttpSession session) throws CustomException;

    /**
     * 修改收货地址
     * @param session
     * @param userGoodAddress
     * @return
     * @throws CustomException 
     */
    Result updateGoodsAddress(HttpSession session, UserGoodAddress userGoodAddress) throws CustomException;

    /**
     * 查询单个收货地址的信息
     * @param id
     * @return
     */
    Result selectSingleGoodsAddress(long id);

    /**
     * 添加收货地址
     * @param session
     * @param userGoodAddress
     * @return
     * @throws CustomException 
     */
    Result addGoodsAddress(HttpSession session, UserGoodAddress userGoodAddress) throws CustomException;

    /**
     * 付款页面获取信息
     * @param session
     * @param goodID
     * @return
     */
    Result getGoodsInfoInPay(HttpSession session, GoodCSO goodID) throws CustomException;

    /**
     * 添加购物车
     * @param addOrder
     * @param addOrder1
     * @return
     */
    Result addOrder(HttpSession addOrder, AddOrder addOrder1) throws CustomException;
    
    /**
     * 获取人脸识别照片，并进行比对
     * @param oneserverId1
     * @return
     */
    public Result faceCheck(String oneserverId1,HttpSession session);
    /**
     * 新增最终的订单，包含用户提交订单的在线签名
     * @param addOrder
     * @param onlineSign
     * @return
     */
    public Result addUserOrder(HttpSession session,String onlineSign,Integer type);
    /**
     * 查询用户订单
     * @param session
     * @return
     */
    Result getUserOrders(HttpSession session) throws CustomException;
    /**
     * 根据城市拿手机
     * @param session
     * @return
     * @throws CustomException
     */
	Result getGoodsByCity(HttpSession session,GoodsByCity goodsByCity) throws CustomException;
	/**
	 * 获取手机套餐
	 * @param session
	 * @param skuid
	 * @return
	 */
	Result getPhonePackage(HttpSession session, long skuid);
	/**
	 * 根据城市、运营商拿手机
	 * @param session
	 * @param goodsByCity
	 * @return
	 */
	Result getGoodsByCityAndOtype(HttpSession session, GoodsByCity goodsByCity);
	/**
	 * 筛选商品
	 * @param session
	 * @param screenGoods
	 * @return
	 */
	Result getGoodsByScreen(HttpSession session, ScreenGoods screenGoods);
	/**
	 * 筛选商品
	 * @param session
	 * @param cityAndotype
	 * @param phoneTypeID
	 * @param type
	 * @param limit
	 * @return
	 */
	Result getGoodsByScreen(HttpSession session, String[] cityAndotype,
			long[] phoneTypeID, int type, int limit);
}
