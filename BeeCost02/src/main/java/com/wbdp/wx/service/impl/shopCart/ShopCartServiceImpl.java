package com.wbdp.wx.service.impl.shopCart;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.ibatis.ognl.SetPropertyAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.wbdp.wx.constant.HTMLStatic;
import com.wbdp.wx.domain.MineM;
import com.wbdp.wx.domain.PhoneShopM;
import com.wbdp.wx.domain.ShopCartM;
import com.wbdp.wx.enums.ResultEnum;
import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.mapper.AttributevalueMapper;
import com.wbdp.wx.mapper.BeeMapper;
import com.wbdp.wx.mapper.GoodsMapper;
import com.wbdp.wx.mapper.OrderMapper;
import com.wbdp.wx.mapper.PollenMapper;
import com.wbdp.wx.mapper.ShoppingCartMapper;
import com.wbdp.wx.model.AddOrder;
import com.wbdp.wx.model.CommodityDetails;
import com.wbdp.wx.model.GoodCSO;
import com.wbdp.wx.model.ISBlackBee;
import com.wbdp.wx.model.Quota;
import com.wbdp.wx.model.Result;
import com.wbdp.wx.model.SKU;
import com.wbdp.wx.model.UserOrder;
import com.wbdp.wx.service.impl.BaseServiceImpl;
import com.wbdp.wx.service.shopCart.ShopCartService;
import com.wbdp.wx.utils.RedisDataStore;
import com.wbdp.wx.utils.ResultUtils;
@Service
public class ShopCartServiceImpl extends BaseServiceImpl implements ShopCartService {
    /**SHOPCARTM存seesion的字段*/
    private static final String SHOPCART = "ShopCart";
    /**创建人*/
    private static final String CREATBY = "微信管理员";

    /**日志log*/
    private static Logger log = LoggerFactory.getLogger(ShopCartServiceImpl.class);
    
    
    @Autowired
    private BeeMapper shopCartBeeMapper;
    @Autowired
    private ShoppingCartMapper shopCartShoppingCartMapper;
    @Autowired
    private AttributevalueMapper shopCartAttributevalueMapper;
    @Autowired
    private GoodsMapper shopCartGoodsMapper;
    @Autowired
    private PollenMapper shopCartPollenMapper;
    @Autowired
    private OrderMapper shopCartOrderMapper;
    /**
	 * 跳转购物车
	 * @param code
	 * @param session
	 * @return
     * @throws CustomException 
	 */
	@Override
	public String toShopCart(String code, HttpSession session) throws CustomException {
		 if (code.equals(null)||code.equals(""))
	            throw new CustomException(ResultEnum.NOCODE);
	        String id = session.getId();
	        ShopCartM mineM = (ShopCartM)session.getAttribute(id+SHOPCART);
	        String result = "";

	        //实例当前session中的注册用例
	        if (mineM==null)
	            // 创建实体
	            mineM = new ShopCartM();

	        //判断是否已经获取到用户微信id
	        if (mineM.getOpenid()==null||mineM.getOpenid().equals("")){
	            result = jumpHTMLByCodeAndSessionAndRegisterM(mineM,id, code, session);
	            return result;
	        }
	        //刷新页面
	        if (mineM.getCode().equals(code)){
	            return  HTMLStatic.SHOPCART;
	        }

	        //返回链接code改变
	        if (!mineM.getCode().equals("")&&!mineM.getCode().equals(code)){
	            result = jumpHTMLByCodeAndSessionAndRegisterM(mineM,id, code, session);
	            return result;
	        }

	        log.error("授权获取openID出错");

	        return result;
	}

	/**
	 * 判断是否注册
	 * @param session
	 * @return
	 */
	@Override
	public Result isSCRegist(HttpSession session) throws CustomException {
        String id = session.getId();
	 	ShopCartM shopCartM = (ShopCartM)session.getAttribute(id+SHOPCART);
        log.info("shopCartM:"+shopCartM.toString());
	 	ISBlackBee uid = shopCartBeeMapper.selectIDByWXID(shopCartM.getOpenid());
        log.info("uid:"+uid);
        if (uid==null)
            return ResultUtils.success("未注册");
        else{
//        	if(uid.getBeeStatus()==1){
//            	shopCartM.setBeeID(-1);
//    			session.setAttribute(id+SHOPCART,shopCartM);
//                return ResultUtils.success("黑名单");
//        	}
//        	else{
//            	shopCartM.setBeeID(Long.valueOf(String.valueOf(uid.getId())));
//    			session.setAttribute(id+SHOPCART,shopCartM);
                return ResultUtils.success("已注册");
//        	}
        }
	}
	
	/**
	 * 查看购物车
	 */
	@Override
	public Result getShopCart(HttpSession session) throws CustomException {
		    String id = session.getId();
		 	ShopCartM shopCartM = (ShopCartM)session.getAttribute(id+SHOPCART);
	        long beeID = shopCartM.getBeeID();
	        log.info("beeID:"+beeID);
	        
	        if(beeID==0)
	            throw new CustomException(ResultEnum.NOREGISTER);
	        //这里需要改动，暂时写死
	        List<UserOrder> userOrdersList = shopCartShoppingCartMapper.selectOrderByBeeid(beeID, 12);
	        log.info("userOrdersList:"+ResultUtils.success(userOrdersList).toString());
	        List<UserOrder> userOrdersList2 = new ArrayList<UserOrder>(); 
	        for (UserOrder userOrder:userOrdersList){
	        	UserOrder newuserOrder = new UserOrder();
	            String valueIDStr = userOrder.getValueIDStr();
	            String[] split = valueIDStr.split(",");
	            newuserOrder.setColor(shopCartAttributevalueMapper.selectValueByID(Long.valueOf(split[0])));
	            newuserOrder.setStorage(shopCartAttributevalueMapper.selectValueByID(Long.valueOf(split[1])));
//	            newuserOrder.setOperator(shopCartAttributevalueMapper.selectValueByID(Long.valueOf(split[2])));
	            newuserOrder.setSupplier(userOrder.getSupplier());
	            newuserOrder.setCtype(Integer.valueOf(split[0]));
	            newuserOrder.setStype(Integer.valueOf(split[1]));
	            newuserOrder.setOtype(Integer.valueOf(split[2]));
	            newuserOrder.setGoodID(userOrder.getGoodID());
	            newuserOrder.setGoodImg(userOrder.getGoodImg());
	            newuserOrder.setGoodName(userOrder.getGoodName());
	            newuserOrder.setGoodPrice(userOrder.getGoodPrice());
	            newuserOrder.setpID(userOrder.getpID());
	            newuserOrder.setWpackage(userOrder.getWpackage());
	            newuserOrder.setNumer(userOrder.getNumer());
	            newuserOrder.setOnePeriod(userOrder.getOnePeriod());
	            newuserOrder.setState(userOrder.getState());
	            newuserOrder.setValueIDStr(userOrder.getValueIDStr());
	            newuserOrder.setPeriods(userOrder.getPeriods());
	            newuserOrder.setShopcartID(userOrder.getShopcartID());
	            newuserOrder.setSupID(userOrder.getSupID());
	            newuserOrder.setPacPrice(userOrder.getPacPrice());
	            userOrdersList2.add(newuserOrder);
	        }

	        log.info("userOrdersList2:"+ResultUtils.success(userOrdersList2).toString());
	        return ResultUtils.success(userOrdersList2);
	}


	/**
	 * 付款页面获取信息
	 */
	@Override
	public Result getGoodsInfoInPay(HttpSession session, GoodCSO goodID)
			throws CustomException {
		log.info("购物车付款页面信息获取："+goodID.toString());
        String id = session.getId();
	 	ShopCartM shopCartM = (ShopCartM)session.getAttribute(id+SHOPCART);
        long beeID = shopCartM.getBeeID();
        if(beeID==0)
            throw new CustomException(ResultEnum.NOREGISTER);
        //通过套餐id获取套餐价格
        //Integer pacPrice = shopCartShoppingCartMapper.selectPriceByID(goodID.getpID());
        //log.info("付款页面获取的套餐价格："+pacPrice);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("goodID",goodID.getGoodID());
        map.put("cType","%"+goodID.getCtype()+"%");
        map.put("sType","%"+goodID.getStype()+"%");
        map.put("supID",goodID.getSupID());
        /**获取价格*/
        SKU sku = shopCartGoodsMapper.selectGoodPiceByCSO(map);
        String cType = shopCartAttributevalueMapper.selectValueByID(goodID.getCtype());
        String sType = shopCartAttributevalueMapper.selectValueByID(goodID.getStype());
        CommodityDetails commodityDetails = shopCartGoodsMapper.selectOneCommodityDetailsByGoodID(goodID.getGoodID());
        Quota quota = shopCartPollenMapper.selectPollenByBeeID(beeID);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodID",goodID.getGoodID());
        jsonObject.put("goodImg",commodityDetails.getGoodImg());
        jsonObject.put("goodName",commodityDetails.getGoodName());
        jsonObject.put("goodPrice",sku.getGoodPice());
        if(goodID.getPeriods()!=0)
          jsonObject.put("onePeriod",sku.getGoodPice()/goodID.getPeriods());
        jsonObject.put("cType",cType);
        jsonObject.put("sType",sType);
        jsonObject.put("payer",commodityDetails.getPayer());
        jsonObject.put("oType",commodityDetails.getSupplier());
        jsonObject.put("skuID",sku.getGskuID());
        if(beeID!=-1){
        	if(quota!=null){
	            if((quota.getQuota()-quota.getUserquota())<=0)
	                jsonObject.put("quota",0);
	            else
	            	jsonObject.put("quota",quota.getQuota()-quota.getUserquota());
	            jsonObject.put("pollenID",quota.getPollenID());
        	}else
                jsonObject.put("quota",-2);
        }else
            jsonObject.put("quota",-1);
        jsonObject.put("pollenID",quota.getPollenID());
        jsonObject.put("pID",goodID.getpID());
        jsonObject.put("wpackage",goodID.getWpackage());
        jsonObject.put("shopcartID", goodID.getShopcartID());
        //jsonObject.put("pacPrice", pacPrice);
        log.info(ResultUtils.success(jsonObject)+"");
        return ResultUtils.success(jsonObject);
	}

    /**
     * 添加订单
     * @throws CustomException 
     */
	@Override
	public Result addOrder(HttpSession session, AddOrder addOrder1) throws CustomException {
		 log.info("购物车中添加订单："+addOrder1.toString());
		 String id = session.getId();
		 	ShopCartM shopCartM = (ShopCartM)session.getAttribute(id+SHOPCART);
	        long beeID = shopCartM.getBeeID();
	        if(beeID==0)
	            throw new CustomException(ResultEnum.NOREGISTER);
	        else if(beeID==-1)
	            throw new CustomException(ResultEnum.ISBLACKBEE);
	        
	        if (addOrder1.equals("")||addOrder1.equals(null))
	            throw new CustomException(ResultEnum.EMTYORDER);
	        //业务员推荐码
	        String salesMancode = addOrder1.getSalesMancode();
	        //商品skuid
	        Integer skuID = addOrder1.getSkuID();
	        //额度ID
	        long pollenID = addOrder1.getPollenID();
	        long goodsID = addOrder1.getGoodsID();
	        //信用额度
	        int quota = addOrder1.getQuota();
	        //商品金额
	        int goodsMoney = addOrder1.getGoodsMoney();
	        log.info("购物车中商品金额："+goodsMoney);
	        Date nowdate = new Date();
	        Map<String,Object> map = new HashMap<String,Object>();
	        Map<String,Object> mapPollen= new HashMap<String,Object>();
	        //查询用户已用额度
	        int usepollen = shopCartPollenMapper.selectUsedCreditByID(pollenID);
	        log.info("购物车中已用额度："+usepollen);
	        map.put("actualmoney",0);
	        //额度更新为订单金额加上之前已用额度
	        mapPollen.put("usedcredit",goodsMoney+usepollen);
	        //从购物车添加进入订单，暂时存到session，等待与在线签名一起存入数据库
	        map.put("shopcartID", addOrder1.getShopcartID());
	        map.put("beeid",beeID);
	        map.put("money",goodsMoney);
	        map.put("orderdate",nowdate);
	        map.put("orderstate",0);
	        map.put("number",1);
	        map.put("skuid",Long.parseLong(skuID.toString()));
	        map.put("salesmancode",salesMancode);
	        map.put("pID",addOrder1.getpID());
	        map.put("creatby",CREATBY);
	        map.put("creatdate",nowdate);
	        map.put("goodsid",goodsID);
	        map.put("periods",addOrder1.getPeriods());
	        //int i = shopCartOrderMapper.insertOrder(map);
	        //将从购物车中提交的订单集合存入session中
	        //Object obj = JSON.toJSON(map);
	        //jedis.set(id+"shopMap", obj.toString());
	        session.setAttribute(id+"shopMap", map);
	        //log.info("i:"+i);

	        mapPollen.put("updatetime",nowdate);
	        mapPollen.put("id",pollenID);
	        //Object pobj = JSON.toJSON(mapPollen);
	        //jedis.set(id+"mapPollen", pobj.toString());
	        //释放资源
	        //RedisDataStore.close(jedis);
	        session.setAttribute(id+"mapPollen", mapPollen);
	        //int i2 = shopCartPollenMapper.updatePollenByID(mapPollen);

	        //log.info("i2:"+i2);
	        
	        //long shopcartID = addOrder1.getShopcartID();
	        //int id3 = shopCartShoppingCartMapper.updateStatefoByID(shopcartID, 2);
	        
	        //log.info("id3:"+id3);
	        
	        /*if (i2==1&&id3>0){
		        log.info("ResultUtils.success():"+ResultUtils.success());
	             return ResultUtils.success();
	        } else{
	        	log.info("添加失败");
	            return ResultUtils.error(ResultEnum.ADDERROR);
	        }*/
	        return ResultUtils.success();
	}

	/**
	 * 删除购物车
	 * @param session
	 * @param cartIDarry
	 * @return
	 * @throws CustomException
	 */
	@Override
	public Result deleteCarts(HttpSession session, long[] cartIDarry)
			throws CustomException {
		log.info("cartIDarry:"+cartIDarry.toString());
		for (int i = 0; i < cartIDarry.length; i++) {
			shopCartShoppingCartMapper.updateStatefoByID(cartIDarry[i], 2);
		}
		log.info("ResultUtils.success():"+ResultUtils.success());
		return ResultUtils.success();
	}
    /**
     * 跳转页面
     * @param mineM
     * @param id
     * @param code
     * @param session
     * @return
     * @throws CustomException 
     */
    private String jumpHTMLByCodeAndSessionAndRegisterM(ShopCartM mineM, String id, String code, HttpSession session) throws CustomException {
        String openID = getOpenIDByWXCode(code);
//        String openID = "odXEdwalok7od7Rs6lkZI0YWju70";
        log.info("openID:"+openID);
//        String openID = "odXEdwVBkxuAsZhh7LjFiqVmfpi4";
        ISBlackBee uid = shopCartBeeMapper.selectIDByWXID(openID);
        log.info("uid:"+uid);
        if (uid!=null){
        	if(uid.getBeeStatus()==1)
            	mineM.setBeeID(-1);
        	else
            	mineM.setBeeID(Long.valueOf(String.valueOf(uid.getId())));
        }else
        	mineM.setBeeID(0);
        mineM.setOpenid(openID);
        mineM.setCode(code);
        session.setAttribute(id+SHOPCART,mineM);
        return HTMLStatic.SHOPCART;
    }
}
