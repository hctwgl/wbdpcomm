package com.wbdp.wx.service.impl.PhoneShop;

import com.alibaba.fastjson.JSON;
import com.wbdp.wx.constant.HTMLStatic;
import com.wbdp.wx.domain.PhoneShopM;
import com.wbdp.wx.domain.ShopCartM;
import com.wbdp.wx.enums.ResultEnum;
import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.mapper.*;
import com.wbdp.wx.model.*;
import com.wbdp.wx.service.PhoneShop.PhoneShopService;
import com.wbdp.wx.service.impl.BaseServiceImpl;
import com.wbdp.wx.utils.RedisDataStore;
import com.wbdp.wx.utils.ResultUtils;
import com.wbdp.wx.utils.Dowload.DowloadWXImgUtils;
import com.wbdp.wx.utils.http.Face;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wisedata005 on 2017/7/5.
 */
@Service
public class PhoneShopImpl extends BaseServiceImpl  implements PhoneShopService {
    /**PHONESHOP存seesion的字段*/
    private static final String PHONESHOP = "PhoneShop";
    /**SHOPCARTM存seesion的字段*/
    private static final String SHOPCART = "ShopCart";
    /**创建人*/
    private static final String CREATBY = "微信管理员";
    /**日志log*/
    private static Logger log = LoggerFactory.getLogger(PhoneShopImpl.class);

    @Autowired
    private GoodsMapper phoneShopGoodsMapper;
    @Autowired
    private AttributevalueMapper phoneShopAttributevalueMapper;
    @Autowired
    private BeeMapper phoneShopBeeMapper;
    @Autowired
    private ShoppingCartMapper phoneShopShoppingCartMapper;
    @Autowired
    private BeeAddressMapper phoneShopBeeAddressMapper;
    @Autowired
    private ReceiptAddressMapper phoneShopReceiptAddressMapper;
    @Autowired
    private OrderMapper phoneShopOrderMapper;
    @Autowired
    private OrderDetailMapper phoneShopOrderDetailMapper;
    @Autowired
    private  PollenMapper phoneShopPollenMapper;
    @Autowired
    private BeeMapper mineBeeMapper;
    /**
     * 授权并跳转页面
     * @param code
     * @param session
     * @return
     */
    @Override
    public String toPhoneShop(String code, HttpSession session) throws CustomException {
        if (code.equals(null)||code.equals(""))
            throw new CustomException(ResultEnum.NOCODE);
        String id = session.getId();
        PhoneShopM phoneShopM = (PhoneShopM)session.getAttribute(id+PHONESHOP);
        String result = "";

        //实例当前session中的注册用例
        if (phoneShopM==null)
            // 创建实体
            phoneShopM = new PhoneShopM();

        //判断是否已经获取到用户微信id
        if (phoneShopM.getOpenid()==null||phoneShopM.getOpenid().equals("")){
            result = jumpHTMLByCodeAndSessionAndRegisterM(phoneShopM,id, code, session);
            return result;
        }
        //刷新页面
        if (phoneShopM.getCode().equals(code)){
            return HTMLStatic.SHOPMALL;
        }

        //返回链接code改变
        if (!phoneShopM.getCode().equals("")&&!phoneShopM.getCode().equals(code)){
            result = jumpHTMLByCodeAndSessionAndRegisterM(phoneShopM,id, code, session);
            return result;
        }

        log.error("授权获取openID出错");

        return result;
    }

    /**
     * 分页查询商品
     * @param session
     * @param limit
     * @param type
     * @return
     */
    @SuppressWarnings("rawtypes")
	@Override
    public Result getGoods(HttpSession session, int limit, int type) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("period",12);
        map.put("limit",limit);
        map.put("sortType",type);
        List<GoodsModel> goodsModelList = phoneShopGoodsMapper.selectGoodsByLimit(map);
         log.info(""+ResultUtils.success(goodsModelList).toString());
        return ResultUtils.success(goodsModelList);
    }

    /**
     * 获取手机品牌类型
     * @param session
     * @return
     */
    @Override
    public Result getPhoneType(HttpSession session) {
        List<PhoneBrand> phoneBrandsList = phoneShopAttributevalueMapper.selectPhoneAttribute();
        log.info(""+ResultUtils.success(phoneBrandsList).toString());
        return ResultUtils.success(phoneBrandsList);
    }

    /**
     * 手机品牌-分页查询商品
     * @param session
     * @param selectPhoneTypeSort
     * @return
     */
    @Override
    public Result getGoodsByPhoneTypeID(HttpSession session, SelectPhoneTypeSort selectPhoneTypeSort) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("period",12);
        map.put("phoneTypeID",selectPhoneTypeSort.getPhoneTypeID());
        map.put("limit",selectPhoneTypeSort.getLimit());
        map.put("sortType",selectPhoneTypeSort.getType());

        List<GoodsModel> goodsModelsList = phoneShopGoodsMapper.selectGoodsByLimitAndGoodsTypeID(map);
        log.info(""+ResultUtils.success(goodsModelsList).toString());

        return ResultUtils.success(goodsModelsList);
    }

    /**
     * 获取运营商
     * @param session
     * @return
     */
    @Override
    public Result getOperator(HttpSession session) {
        List<Operator> operatorsList = phoneShopAttributevalueMapper.selectOperator();
        log.info(""+ResultUtils.success(operatorsList).toString());
        return ResultUtils.success(operatorsList);
    }

    /**
     * 运营商-分页查询商品
     * @param session
     * @param operatorTypeSort
     * @return
     */
    @Override
    public Result getGoodsByOperator(HttpSession session, OperatorTypeSort operatorTypeSort) {
    	log.info("operatorTypeSort："+operatorTypeSort.toString());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("period",12);
        map.put("valueID",operatorTypeSort.getOperatorType());
        map.put("limit",operatorTypeSort.getLimit());
        map.put("sortType",operatorTypeSort.getType());
        List<GoodsModel> goodsModelsList = phoneShopGoodsMapper.selectGoodsByOperatorTypeSort(map);

        log.info(""+ResultUtils.success(goodsModelsList).toString());
        return ResultUtils.success(goodsModelsList);
    }

    /**
     * 获取商品详情
     * @param goodID
     * @return
     */
    @Override
    public Result getGoodsDetails(int goodID) {
        List<CommodityDetails> commodityDetailsList = phoneShopGoodsMapper.selectCommodityDetailsByGoodID(12, goodID);
        log.info(""+ResultUtils.success(commodityDetailsList).toString());
        return ResultUtils.success(commodityDetailsList);
    }

    /**
     * 获取单个商品的颜色/存储/运营商选项
     * @param goodID
     * @return
     */
    @Override
    public Result getGoodsColor(int goodID) {
        List<ColorMode> colorModesList = phoneShopGoodsMapper.selectGoodsColorType(goodID,0);
        List<ColorMode> storageModesList = phoneShopGoodsMapper.selectGoodsColorType(goodID,1);
//        List<ColorMode> operatorModesList = phoneShopGoodsMapper.selectGoodsColorType(goodID,2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("color",colorModesList);
        jsonObject.put("storage",storageModesList);
//        jsonObject.put("operator",operatorModesList);
        log.info(""+ResultUtils.success(jsonObject).toString());
        return ResultUtils.success(jsonObject);
    }

    /**
     * 根据商品的颜色/存储/运营商选项获取价格
     * @param goodID
     * @return
     */
    @Override
    public Result getGoodPriceByCSO(GoodCSO goodID) {
    	log.info("goodID:"+goodID);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("goodID",goodID.getGoodID());
        map.put("cType","%"+goodID.getCtype()+"%");
        map.put("sType","%"+goodID.getStype()+"%");
        map.put("supID",goodID.getSupID());
        SKU aLong = phoneShopGoodsMapper.selectGoodPiceByCSO(map);
        if(aLong==null){
            return ResultUtils.success(null);
        }else{
            log.info(""+ResultUtils.success(aLong).toString());
            return ResultUtils.success(aLong);
        }
    }

    /**
     * 加入购物车和立即购买前判断是否注册了用户
     * @param session
     * @return
     */
    @Override
    public Result isRegist(HttpSession session) {
        String id = session.getId();
        PhoneShopM phoneShopM = (PhoneShopM)session.getAttribute(id+PHONESHOP);
        ISBlackBee uid = phoneShopBeeMapper.selectIDByWXID(phoneShopM.getOpenid());
        if (uid==null)
            return ResultUtils.success("未注册");
        else{
//        	if(uid.getBeeStatus()==1){
//	            phoneShopM.setBeeID(-1);
//	            session.setAttribute(id+PHONESHOP,phoneShopM);
//	            return ResultUtils.success("已注册");
//        	}else{
//	            phoneShopM.setBeeID(Long.valueOf(String.valueOf(uid.getId())));
//	            session.setAttribute(id+PHONESHOP,phoneShopM);
	            return ResultUtils.success("已注册");
//        	}
        }
    }

    /**
     * 加入购物车
     *
     * @param session
     * @param goodCSO
     * @return
     * @throws CustomException 
     */
    @Override
    public Result addOrderByCSO(HttpSession session, GoodCSO goodCSO) throws CustomException {
        String id = session.getId();
        PhoneShopM phoneShopM = (PhoneShopM)session.getAttribute(id+PHONESHOP);
        long beeID = phoneShopM.getBeeID();
        if(beeID==0)
            throw new CustomException(ResultEnum.NOREGISTER);
        
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("beeid",beeID);
        map.put("goodsid",goodCSO.getGoodID());
        map.put("cType","%"+goodCSO.getCtype()+"%");
        map.put("sType","%"+goodCSO.getStype()+"%");
//        map.put("oType","%"+goodCSO.getOtype()+"%");
        map.put("number",1);
        map.put("cartstate",1);
        map.put("creatby","微信管理员");
        map.put("pID",goodCSO.getpID());
        map.put("supID",goodCSO.getSupID());
        map.put("creatdate",new Date());
        map.put("periods",goodCSO.getPeriods());

        int i = phoneShopShoppingCartMapper.insertOrderByCSO(map);
        if (i>0)
            return ResultUtils.success();
        else
            return ResultUtils.error(ResultEnum.ADDERROR);
    }

    /**
     * 查看购物车
     * @param session
     * @return
     * @throws CustomException 
     */
    @Override
    public Result getOrderInfo(HttpSession session) throws CustomException {
        String id = session.getId();
        PhoneShopM phoneShopM = (PhoneShopM)session.getAttribute(id+PHONESHOP);
        long beeID = phoneShopM.getBeeID();
        if(beeID==0)
            throw new CustomException(ResultEnum.NOREGISTER);
        
        List<UserOrder> userOrdersList = phoneShopShoppingCartMapper.selectOrderByBeeid(beeID, 12);
        log.info("userOrdersList:"+ResultUtils.success(userOrdersList).toString());
        List<UserOrder> userOrdersList2 = new ArrayList<UserOrder>(); 
        for (UserOrder userOrder:userOrdersList){
        	UserOrder newuserOrder = new UserOrder();
            String valueIDStr = userOrder.getValueIDStr();
            String[] split = valueIDStr.split(",");
            newuserOrder.setColor(phoneShopAttributevalueMapper.selectValueByID(Long.valueOf(split[0])));
            newuserOrder.setStorage(phoneShopAttributevalueMapper.selectValueByID(Long.valueOf(split[1])));
//            newuserOrder.setOperator(phoneShopAttributevalueMapper.selectValueByID(Long.valueOf(split[2])));
            newuserOrder.setCtype(Integer.valueOf(split[0]));
            newuserOrder.setStype(Integer.valueOf(split[1]));
            newuserOrder.setOtype(Integer.valueOf(split[2]));
            newuserOrder.setGoodID(userOrder.getGoodID());
            newuserOrder.setGoodImg(userOrder.getGoodImg());
            newuserOrder.setGoodName(userOrder.getGoodName());
            newuserOrder.setGoodPrice(userOrder.getGoodPrice());
            newuserOrder.setNumer(userOrder.getNumer());
            newuserOrder.setOnePeriod(userOrder.getOnePeriod());
            newuserOrder.setState(userOrder.getState());
            newuserOrder.setValueIDStr(userOrder.getValueIDStr());
            newuserOrder.setPeriods(userOrder.getPeriods());
            userOrdersList2.add(newuserOrder);
        }

        log.info("userOrdersList2:"+ResultUtils.success(userOrdersList2).toString());
        return ResultUtils.success(userOrdersList2);
    }


    /**
     * 付款页面获取信息
     * @param session
     * @param goodID
     * @return
     */
    @Override
    public Result getGoodsInfoInPay(HttpSession session, GoodCSO goodID) throws CustomException {
    	log.info("goodID:"+goodID.toString());
        String id = session.getId();
        PhoneShopM phoneShopM = (PhoneShopM)session.getAttribute(id+PHONESHOP);
        long beeID = phoneShopM.getBeeID();
        if(beeID==0)
            throw new CustomException(ResultEnum.NOREGISTER);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("goodID",goodID.getGoodID());
        map.put("cType","%"+goodID.getCtype()+"%");
        map.put("sType","%"+goodID.getStype()+"%");
        map.put("supID",goodID.getSupID());
        /**获取价格*/
        SKU sku = phoneShopGoodsMapper.selectGoodPiceByCSO(map);
    	log.info("sku:"+sku.toString());
        String cType = phoneShopAttributevalueMapper.selectValueByID(goodID.getCtype());
        String sType = phoneShopAttributevalueMapper.selectValueByID(goodID.getStype());
        CommodityDetails commodityDetails = phoneShopGoodsMapper.selectOneCommodityDetailsByGoodID(goodID.getGoodID());
        Quota quota = phoneShopPollenMapper.selectPollenByBeeID(beeID);
        log.info("quota:"+quota);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodID",goodID.getGoodID());
        jsonObject.put("goodImg",commodityDetails.getGoodImg());
        jsonObject.put("goodName",commodityDetails.getGoodName());
        jsonObject.put("goodPrice",sku.getGoodPice());
//        jsonObject.put("onePeriod",sku.getGoodPice()/goodID.getPeriods());payer
        jsonObject.put("cType",cType);
        jsonObject.put("sType",sType);
        jsonObject.put("payer",commodityDetails.getPayer());
        jsonObject.put("oType",commodityDetails.getSupplier());
        jsonObject.put("skuID",sku.getGskuID());
        jsonObject.put("pID",goodID.getpID());
        jsonObject.put("wpackage",goodID.getWpackage());
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
        log.info(ResultUtils.success(jsonObject)+"");
        return ResultUtils.success(jsonObject);
    }

    /**
     * 添加订单
     * @param session
     * @param addOrder1
     * @return
     * @throws CustomException
     */
    @Override
    @Transactional
    public Result addOrder(HttpSession session, AddOrder addOrder1) throws CustomException {
    	log.info("直接提交订单中的参数："+addOrder1.toString());
        String id = session.getId();
        PhoneShopM phoneShopM = (PhoneShopM)session.getAttribute(id+PHONESHOP);
        long beeID = phoneShopM.getBeeID();
       
        log.info("beeID:"+beeID);
        if(beeID==0)
            throw new CustomException(ResultEnum.NOREGISTER);
        else if(beeID==-1)
            throw new CustomException(ResultEnum.ISBLACKBEE);
        if (addOrder1.equals("")||addOrder1.equals(null))
            throw new CustomException(ResultEnum.EMTYORDER);
        log.info("addOrder1:"+addOrder1.toString());
        
      //业务员推荐码
        String salesMancode = addOrder1.getSalesMancode();
        	
        Integer skuID = addOrder1.getSkuID();
        //用户额度ID
        long pollenID = addOrder1.getPollenID();
        long goodsID = addOrder1.getGoodsID();
        log.info("goodsID:"+goodsID);
        //用户额度
        int quota = addOrder1.getQuota();
        //商品金额
        int goodsMoney = addOrder1.getGoodsMoney();

        Date nowdate = new Date();
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> mapPollen= new HashMap<String,Object>();
        int usepollen = phoneShopPollenMapper.selectUsedCreditByID(pollenID);
        //目前用户实际支付金额默认为0
        map.put("actualmoney",0);
         //将已用额度存入集合
         mapPollen.put("usedcredit",goodsMoney+usepollen);
        //新增订单，暂时放到session,等待与在线签名同时存入数据库
        //map.put("addOrder1", addOrder1);
        map.put("beeid",beeID);
        map.put("money",goodsMoney);
        map.put("orderdate",nowdate);
        //新增订单时默认为未审核状态
        map.put("orderstate",0);
        map.put("number",1);
        map.put("skuid",Long.parseLong(skuID.toString()));
        map.put("salesmancode",salesMancode);
        map.put("creatby",CREATBY);
        map.put("creatdate",nowdate);
        map.put("goodsid",goodsID);
        map.put("pID",addOrder1.getpID());
        map.put("periods",addOrder1.getPeriods());
        //Object obj = JSON.toJSON(map);
        //Jedis jedis = RedisDataStore.getconnetion();
       // jedis.set(id+"orderMap", obj.toString());
        session.setAttribute(id+"orderMap", map);
        //int i = phoneShopOrderMapper.insertOrder(map);
       // log.info("i:"+i);
        mapPollen.put("updatetime",nowdate);
        mapPollen.put("id",pollenID);
        //Object pobj = JSON.toJSON(mapPollen);
        //将花粉信用数据存入redis中
        //jedis.set(id+"mapPollen", pobj.toString());
        //释放jedis资源
        //RedisDataStore.close(jedis);
        session.setAttribute(id+"mapPollen", mapPollen);
        //int i2 = phoneShopPollenMapper.updatePollenByID(mapPollen);
	        log.info("ResultUtils.success():"+ResultUtils.success());
            return ResultUtils.success();
    }

    /**
     * 新增最终的订单，包含用户提交订单的在线签名
     * @param addOrder
     * @param onlineSign
     * @return
     */
    @Override
	public Result addUserOrder(HttpSession session,String onlineSign,Integer type) {
    	log.info("判断是否直接提交订单："+type);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	int i = 0;
    	Map<String,Object> map = null;
    	Map<String,Object> pollenmap = null;
    	Long shopcartID = null;
    	//Jedis jedis = null;
		try {
			//jedis = RedisDataStore.getconnetion();
			String sessionid = session.getId();
			//取出存入session中的订单map集合
			if(type==1){
				log.info("session中的订单信息："+JSON.toJSONString(session.getAttribute(sessionid+"orderMap")));
				log.info("session中的花粉信息："+JSON.toJSONString(session.getAttribute(sessionid+"mapPollen")));
				//订单map集合
				map = (Map<String,Object>) session.getAttribute(sessionid+"orderMap");
				//jedis.del(sessionid+"orderMap");
				//map = (Map<String,Object>)session.getAttribute(sessionid+"orderMap");
				//花粉额度map集合
				pollenmap = (Map<String,Object>)session.getAttribute(sessionid+"mapPollen");
				//jedis.del(sessionid+"mapPollen");
			}else{
				log.info("session中的购物车订单信息："+JSON.toJSONString(session.getAttribute(sessionid+"shopMap")));
				log.info("session中的购物车花粉信息："+JSON.toJSONString(session.getAttribute(sessionid+"mapPollen")));
				map = (Map<String,Object>)session.getAttribute(sessionid+"shopMap");
				//花粉额度map集合
				pollenmap = (Map<String,Object>)session.getAttribute(sessionid+"mapPollen");
				shopcartID = Long.parseLong(map.get("shopcartID").toString());
			}
			if(map!=null&&pollenmap!=null){
				map.put("onlineSign", onlineSign);
				//提交最终的订单
			    i = phoneShopOrderMapper.insertOrder(map);
			    //修改花粉额度
			    phoneShopPollenMapper.updatePollenByID(pollenmap);
			    if(shopcartID!=null){
			    	//从购物车提交订单成功后修改购物车状态，从未清空转换为清空状态
			    	phoneShopShoppingCartMapper.updateStatefoByID(shopcartID, 2);
			    }
			}else{
				log.info("map为空");
			}
			if(i==1){
				log.info("添加最终订单成功"+ResultUtils.success());
	            return ResultUtils.success();
			}else{
				log.info("添加失败");
	            return ResultUtils.error(ResultEnum.ADDERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		return null;
	}

	/**
     * 查询用户订单
     * @param session
     * @return
     */
    @Override
    public Result getUserOrders(HttpSession session) throws CustomException {
        String id = session.getId();
        PhoneShopM phoneShopM = (PhoneShopM)session.getAttribute(id+PHONESHOP);
        long beeID = phoneShopM.getBeeID();
        if (beeID==0)
            throw new CustomException(ResultEnum.NOREGISTER);

        List<UserOrder> goodsModelsList = phoneShopOrderMapper.selectOrdersByBeeID(12, beeID);

        for (UserOrder userOrder:goodsModelsList){
            String valueIDStr = userOrder.getValueIDStr();
            String[] split = valueIDStr.split(",");
            userOrder.setColor(phoneShopAttributevalueMapper.selectValueByID(Long.valueOf(split[0])));
            userOrder.setStorage(phoneShopAttributevalueMapper.selectValueByID(Long.valueOf(split[1])));
//            userOrder.setOperator(phoneShopAttributevalueMapper.selectValueByID(Long.valueOf(split[2])));
        }

        log.info(""+ResultUtils.success(goodsModelsList).toString());
        return ResultUtils.success(goodsModelsList);
    }

    /**
     * 查看收货地址
     * @param session
     * @return
     * @throws CustomException 
     */
    @Override
    public Result getGoodsAddress(HttpSession session) throws CustomException {
        String id = session.getId();
        PhoneShopM phoneShopM = (PhoneShopM)session.getAttribute(id+PHONESHOP);
        long beeID = phoneShopM.getBeeID();
        if(beeID==0)
            throw new CustomException(ResultEnum.NOREGISTER);
        else if(beeID==-1)
            throw new CustomException(ResultEnum.ISBLACKBEE);

        List<UserGoodAddress> userGoodAddressesList = phoneShopReceiptAddressMapper.selectReceiptAddressByBeeid(beeID);

        log.info(""+ResultUtils.success(userGoodAddressesList).toString());
        return ResultUtils.success(userGoodAddressesList);
    }

    /**
     * 修改收货地址
     * @param session
     * @param userGoodAddress
     * @return
     * @throws CustomException 
     */
    @Override
    public Result updateGoodsAddress(HttpSession session, UserGoodAddress userGoodAddress) throws CustomException {

        String id = session.getId();
        PhoneShopM phoneShopM = (PhoneShopM)session.getAttribute(id+PHONESHOP);
        long beeID = phoneShopM.getBeeID();
        if(beeID==0)
            throw new CustomException(ResultEnum.NOREGISTER);
        else if(beeID==-1)
            throw new CustomException(ResultEnum.ISBLACKBEE);


        Map<String,Object> map = new HashMap<String,Object>();
        map.put("phone",userGoodAddress.getPhone());
        map.put("receiptname",userGoodAddress.getName());
        map.put("province",userGoodAddress.getProvince());
        map.put("city",userGoodAddress.getCity());
        map.put("area",userGoodAddress.getArea());
        map.put("detaddress",userGoodAddress.getDetailsAddress());
        map.put("id",userGoodAddress.getId());

        int i = phoneShopReceiptAddressMapper.updateReceiptAddressByID(map);
        if (i==1)
            return ResultUtils.success();
        else
            return ResultUtils.error(ResultEnum.UPDATEERROR);
    }

    /**
     * 查询单个收货地址的信息
     * @param id
     * @return
     */
    @Override
    public Result selectSingleGoodsAddress(long id) {
        UserGoodAddress userGoodAddress =   phoneShopReceiptAddressMapper.selectSingleGoodsAddress(id);

        log.info(""+ResultUtils.success(userGoodAddress).toString());
        return ResultUtils.success(userGoodAddress);
    }

    /**
     * 添加收货地址
     * @param session
     * @param userGoodAddress
     * @return
     * @throws CustomException 
     */
    @Override
    public Result addGoodsAddress(HttpSession session, UserGoodAddress userGoodAddress) throws CustomException {

        String id = session.getId();
        PhoneShopM phoneShopM = (PhoneShopM)session.getAttribute(id+PHONESHOP);
        long beeID = phoneShopM.getBeeID();
        if(beeID==0)
            throw new CustomException(ResultEnum.NOREGISTER);
        else if(beeID==-1)
            throw new CustomException(ResultEnum.ISBLACKBEE);
        Map<String,Object> homemap = new HashMap<String,Object>();
        //家庭地址
        String homeAddress = userGoodAddress.getDetailsAddress();
        homemap.put("detaddress",homeAddress);
        String homeProvince = userGoodAddress.getProvince();
        homemap.put("province",homeProvince);
        String homeCity = userGoodAddress.getCity();
        homemap.put("city",homeCity);
        String homeArea = userGoodAddress.getArea();
        homemap.put("area",homeArea);
        homemap.put("beeid",beeID);
        homemap.put("creatdate",new Date());
        homemap.put("receiptname",userGoodAddress.getName());
        homemap.put("phone",userGoodAddress.getPhone());

        int i1 = phoneShopReceiptAddressMapper.insertReceiptAddress(homemap);

        if (i1>0)
             return ResultUtils.success();
        else
            return ResultUtils.error(ResultEnum.ADDERROR);
    }
    
    /**
     * 根据城市拿手机
     * @param session
     * @return
     * @throws CustomException
     */
	@Override
	public Result getGoodsByCity(HttpSession session,GoodsByCity goodsByCity) throws CustomException {
		log.info("goodsByCity.getCity():"+goodsByCity.getCity());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("period",12);
        map.put("city",goodsByCity.getCity());
        map.put("limit",goodsByCity.getLimit());
        map.put("sortType",goodsByCity.getType());
		List<GoodsModel> selectGoodsByLimitAndCity = phoneShopGoodsMapper.selectGoodsByLimitAndCity(map);
        log.info(""+ResultUtils.success(selectGoodsByLimitAndCity).toString());
        return ResultUtils.success(selectGoodsByLimitAndCity);
	}

    /**
	 * 获取手机套餐
	 * @param session
	 * @param skuid
	 * @return
	 */
	@Override
	public Result getPhonePackage(HttpSession session, long skuid) {
		log.info("skuid:"+skuid);
		List<PhonePackage> selectpackageBySKUID = phoneShopGoodsMapper.selectpackageBySKUID(skuid);
        log.info(""+ResultUtils.success(selectpackageBySKUID).toString());
		return ResultUtils.success(selectpackageBySKUID);
	}
    /**
	 * 根据城市、运营商拿手机
	 * @param session
	 * @param goodsByCity
	 * @return
	 */
	@Override
	public Result getGoodsByCityAndOtype(HttpSession session,
			GoodsByCity goodsByCity) {
		log.info("goodsByCity.getOtype():"+goodsByCity.getOtype());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("otype",goodsByCity.getOtype());
        map.put("limit",goodsByCity.getLimit());
        map.put("sortType",goodsByCity.getType());
		List<GoodsModel> selectGoodsByLimitAndCity = phoneShopGoodsMapper.getGoodsByCityAndOtype(map);
        log.info(""+ResultUtils.success(selectGoodsByLimitAndCity).toString());
        return ResultUtils.success(selectGoodsByLimitAndCity);
	}
    /**
     * 跳转页面
     * @param phoneShopM
     * @param id
     * @param code
     * @param session
     * @return
     * @throws CustomException 
     */
    private String jumpHTMLByCodeAndSessionAndRegisterM(PhoneShopM phoneShopM, String id, String code, HttpSession session) throws CustomException {
        String openID = getOpenIDByWXCode(code);
       // String openID = "odXEdwalok7od7Rs6lkZI0YWju70";
//        String openID = "odXEdwVBkxuAsZhh7LjFiqVmfpi4";
        phoneShopM.setOpenid(openID);
        phoneShopM.setCode(code);
        ISBlackBee uid = phoneShopBeeMapper.selectIDByWXID(openID);
        if (uid==null)
        	phoneShopM.setBeeID(0);
        else{
        	log.info("uid:"+uid.toString());
        	if(uid.getBeeStatus()==1)
            	phoneShopM.setBeeID(-1);
        	else{
            	phoneShopM.setBeeID(Long.valueOf(String.valueOf(uid.getId())));
        	}
        }
        session.setAttribute(id+PHONESHOP,phoneShopM);
        return HTMLStatic.SHOPMALL;
    }
    /**
	 * 筛选商品
	 * @param session
	 * @param screenGoods
	 * @return
	 */
	@Override
	public Result getGoodsByScreen(HttpSession session, ScreenGoods screenGoods) {
		log.info("screenGoods:"+screenGoods.toString());
		String[] cityAndotype = screenGoods.getCityAndotype();
		int[] phoneTypeID = screenGoods.getPhoneTypeID();
		int limit = screenGoods.getLimit();
		int type = screenGoods.getType();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("otype",cityAndotype);
        map.put("limit",limit);
        map.put("sortType",type);
        map.put("phoneTypeID",phoneTypeID);
//        List<GoodsModel> goodsModelsLists = phoneShopGoodsMapper.selectGoodsByLimitAndGoodsTypeIDAndSupplier(map);
        return ResultUtils.success();
	}
	/**
	 * 筛选商品
	 * @param session
	 * @param cityAndotype
	 * @param phoneTypeID
	 * @param type
	 * @param limit
	 * @return
	 */
	@Override
	public Result getGoodsByScreen(HttpSession session, String[] cityAndotype,
			long[] phoneTypeID, int type, int limit) {
		// TODO Auto-generated method stub
		log.info("cityAndotype:"+Arrays.toString(cityAndotype));
		log.info("limit:"+limit);
		log.info("type:"+type);
		log.info("phoneTypeID:"+Arrays.toString(phoneTypeID));
		log.info("cityAndotype.length:"+cityAndotype.length);
		log.info("phoneTypeID.length:"+phoneTypeID.length);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("otype",cityAndotype);
        map.put("limit",limit);
        map.put("sortType",type);
        map.put("phoneTypeID",phoneTypeID);
        if(phoneTypeID.length==0)
            map.put("phoneTypeIDType",0);
        else
            map.put("phoneTypeIDType",1);
        if(cityAndotype.length==0)
            map.put("otypeType",0);
        else
            map.put("otypeType",1);
        List<GoodsModel> goodsModelsLists = phoneShopGoodsMapper.selectGoodsByLimitAndGoodsTypeIDAndSupplier(map);
		log.info("goodsModelsLists:"+ResultUtils.success(goodsModelsLists));
		return ResultUtils.success(goodsModelsLists);
	}
	/**
     * 获取人脸识别照片，并进行比对
     * @param oneserverId1
     * @return
     */
	@Override
	public Result faceCheck(String oneserverId1,HttpSession session) {
		try {
			 String id = session.getId();
		     PhoneShopM phoneShopM = (PhoneShopM)session.getAttribute(id+PHONESHOP);
		     ShopCartM mineM = (ShopCartM)session.getAttribute(id+SHOPCART);
			 String openid = "";
			 if(phoneShopM!=null){
				  openid = phoneShopM.getOpenid();
			 }else{
				 openid = mineM.getOpenid();
			 }
			 log.info("人脸识别中获取的openID："+openid);
			 //得到人脸识别图片路径
			String faceUrl =  DowloadWXImgUtils.downloadAndSaveFace(oneserverId1, openid);
			log.info("人脸识别图片路径："+faceUrl);
			//获取身份证正面图片路径
			String imgPathStr = mineBeeMapper.selectCardImageByWXID(openid);
			 JSONObject imgPathJson = JSONObject.fromObject(imgPathStr);
			String firstPath = imgPathJson.getString("firstPath");
			String json = Face.faceCompare(faceUrl, firstPath);
			JSONObject faceJson = JSONObject.fromObject(json);
			log.info("获取的json："+faceJson);
			double score = Double.parseDouble(faceJson.getString("confidence"));
			log.info("人脸识别分数："+score);
			if(score>70){
				return ResultUtils.success("识别通过");
			}else{
				return ResultUtils.error("识别失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.error("识别失败");
		}
	}
}
