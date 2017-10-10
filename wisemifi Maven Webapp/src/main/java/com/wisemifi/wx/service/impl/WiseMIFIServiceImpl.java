package com.wisemifi.wx.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wisemifi.wx.constant.DeveloInfor;
import com.wisemifi.wx.dao.CustomerAddressMapper;
import com.wisemifi.wx.dao.CustomerOrderMapper;
import com.wisemifi.wx.dao.WiseCustomerMapper;
import com.wisemifi.wx.entity.CustomerAddress;
import com.wisemifi.wx.entity.CustomerOrder;
import com.wisemifi.wx.entity.WiseCustomer;
import com.wisemifi.wx.model.JsAPIConfig;
import com.wisemifi.wx.model.PageAuthorModel;
import com.wisemifi.wx.model.UnifiedOrder;
import com.wisemifi.wx.service.WiseMIFIService;
import com.wisemifi.wx.util.CacheManager;
import com.wisemifi.wx.util.HttpClientUtil;
import com.wisemifi.wx.util.JSAPIUitl;
import com.wisemifi.wx.util.PayUtil;
import com.wisemifi.wx.util.PushUtil;
import com.wisemifi.wx.util.RandomStrUtils;
import com.wisemifi.wx.util.RedisDataStore;

/**
 * 维泽mifi业务实现类
 * @author 汪赛军
 * date:2017年8月10日上午9:08:59
 */
@Service
public class WiseMIFIServiceImpl implements WiseMIFIService {
	private static Logger log = Logger.getLogger(WiseMIFIServiceImpl.class);
	
	@Autowired
	private CustomerAddressMapper customerAddressMapper;
	@Autowired
	private CustomerOrderMapper customerOrderMapper;
	@Autowired
	private WiseCustomerMapper wiseCustomerMapper;
	@Override
	public String getCodeAndopenID(String code,String openid, HttpSession session) {
		String result = "";
		String pathUrl = "buy";
		PageAuthorModel pageModel = new PageAuthorModel();
		try {
			//拼接获取网页授权access_token接口
			String codeUrl = DeveloInfor.CODE_ACCESS_TOKEN.replace("APPID", DeveloInfor.AppID).replace("SECRET", DeveloInfor.AppSecret).replace("CODE",code);
			//获取微信服务端回复消息
			 result = HttpClientUtil.httpGet(codeUrl);
			 log.info("网页授权接收的数据："+result);
			//解析json数据
			 if(result!=null&&!"".equals(result)){
				 JSONObject obj = JSON.parseObject(result);
				 String access_token = obj.getString("access_token");
				 //当前客户的openID
				 String openidNow = obj.getString("openid");
				 //获取sessionID
				 String sessionid = session.getId();
				 //将code与openID封装到模型中
				 pageModel.setCode(code);
				 pageModel.setOpenid(openidNow);
				 session.setAttribute(sessionid+"pageModel", pageModel);
				 //判断是否获取到链接中的openID
				 if(openid!=null||!"".equals(openid)){
					 //若获取到则将其存入session中，待保存订单时进行处理
					 session.setAttribute(sessionid+"IntroOpenid", openid);
				 }
			 }
			return pathUrl;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取ticket签名验证
	 */
	@Override
	public JSONObject getticket(String url) {
		Jedis jedis = null;
		try {
			jedis = RedisDataStore.getconnetion();
			String ticket = jedis.get("ticket");
			//生成ticket签名
			JSONObject js_tiket = JSAPIUitl.createWebSignature(ticket, url);
			return js_tiket;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 微信统一下单,成功后返回字符配置数据
	 */
	@Override
	public Map<String,Object> wxPay(HttpSession session,String userip,CustomerOrder customerOrder) {
		UnifiedOrder unifiedOrder = new UnifiedOrder();
		Map<String,Object> outMap = new HashMap<String, Object>();
		//String callbackUrl = "http://www.wisedp.com/wisemifi/wisemifi/topay_back/";
		try {
			if(customerOrder!=null){
				log.info("统一下单中获取的订单对象"+customerOrder.toString());
				String sessionid = session.getId();
				//获取session中的维泽mifi封装对象
				PageAuthorModel pageModel = (PageAuthorModel)session.getAttribute(sessionid+"pageModel");
				String openid = pageModel.getOpenid();
				//将用户微信存入订单对象中
				customerOrder.setCustomerOpenId(openid);
				//统一下单,并返回支付配置实体类
				Map<String,Object> jsconfig = PayUtil.unifiedOrder(DeveloInfor.MIFI_CALLBACK_URL,customerOrder.getPrice(),userip,openid,"维泽MIFI");
				//将返回的商户订单号，即我们自己生成的订单号放入订单对象中
				customerOrder.setOrderNumber(jsconfig.get("orderid").toString());
				//获取session中保存的介绍人openID，并判断是否存在
				String introOpenid = "";
				log.info("session中的推荐人微信："+session.getAttribute(sessionid+"IntroOpenid"));
				if(session.getAttribute(sessionid+"IntroOpenid")!=null){
					introOpenid = session.getAttribute(sessionid+"IntroOpenid").toString();
					customerOrder.setIntroOpenid(introOpenid);
				}
				log.info("保存到全局map前的订单对象"+customerOrder.toString());
				//将订单对象存入自定义的全局map中
				CacheManager.putCache(openid+"ORDER", customerOrder);
				outMap.put("data", (JsAPIConfig)jsconfig.get("JsAPIConfig"));
				outMap.put("status", "SUCCESS");
				return outMap;
			}else{
				outMap.put("data", "");
				outMap.put("status", "FALSE");
				outMap.put("msg", "获取订单数据失败");
				return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", "");
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}
	/**
	 * 微信支付成功后回调函数
	 * appid:wxb125fba57e2524e2
		attach:附加数据
		附加数据:attach
		bank_type:CFT
		cash_fee:1
		device_info:WEB
		fee_type:CNY
		is_subscribe:Y
		mch_id:1487331152
		nonce_str:619ae8cb-dcf7-481d-b31c-db0cdc
		openid:oN2fM1JyCrnLiQ-x1LlXHoegaebs
		out_trade_no:1487331152201708123NdlXLF4XH
		result_code:SUCCESS
		return_code:SUCCESS
		sign:8F1705E0C64C1B0FD7FDF01A3B77C82A
		time_end:20170812165443
		total_fee:1
		trade_type:JSAPI
		transaction_id:4008572001201708125858345334
	 */
	@Override
	public void payback(HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//获取request中的流数据
			BufferedReader in=new BufferedReader(new InputStreamReader(request.getInputStream()));
			 StringBuilder sb = new StringBuilder();   
		      // String xmlHead = "";
		        // String xmlContent="";
		         String line = null;  
		         while ((line = in.readLine()) != null) {   
		        	 sb.append(line);
		           } 
		         log.info("获取到微信回复的流数据："+sb.toString());
		         //解析微信返回的xml数据
		        Document document = DocumentHelper.parseText(sb.toString());
				Element root = document.getRootElement();
				List<Element> list = root.elements();
				for(Element e:list){
					map.put(e.getName(), e.getText());
				}
				//判断返回验证码是否为成功
				if("SUCCESS".equals((String)map.get("return_code"))){
					//String sessionid = session.getId();
					//获取全局map中的订单对象
					CustomerOrder customerOrder = (CustomerOrder)CacheManager.getCache((String)map.get("openid")+"ORDER");
					if(customerOrder!=null){
						//用户微信号
						customerOrder.setCustomerOpenId((String)map.get("openid"));
						//我们自己传到微信的订单号
						String out_trade_no = (String)map.get("out_trade_no");
						//订单号查询是否已经存在，如果存在则终止
						CustomerOrder orderByOrderNumber = customerOrderMapper.selectOrderByOrderNumber(out_trade_no);
						log.info("根据微信回调数据中的订单号查询结果："+orderByOrderNumber);
						if(orderByOrderNumber!=null){
							//拼接回复xml字符串
							String xml = "<xml> <return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
							//回复微信数据
							response.getWriter().write(xml);
							return;
						}
						//判断是否与订单对象中的订单号一致
						if(out_trade_no.equals(customerOrder.getOrderNumber())){
							log.info("订单号一致");
						}else{
							log.info("订单号不一致");
						}
						//订单金额，此处需将微信的单位分修改为元
						Integer price = Integer.parseInt(map.get("total_fee").toString());
						if(price==customerOrder.getPrice()){
							log.info("回调数据中价格与提交的订单价格相同");
							
						}else{
							log.info("回调数据中价格与提交的订单价格不相同");
						}
						//注意，在这一步后map中的金额可能在整除后变成0
						customerOrder.setPrice(price/100);
						customerOrder.setOrderStatus(1);
						//支付完成时间yyyyMMddHHmmss格式转换为yyyy-MM-dd HH:mm:ss格式
						String time = (String)map.get("time_end");
						String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
						time = time.replaceAll(reg, "$1-$2-$3 $4:$5:$6");
						customerOrder.setOrderCreateTime(time);
						//将订单对象存入session中
						//session.setAttribute(sessionid+"ORDER", customerOrder);
						//推送支付成功消息到客户
						PushUtil.payPush(customerOrder);
						//保存用户订单到数据库
						customerOrder.setOrderStatus(1);
						customerOrder.setOrderType(1);
						log.info("保存订单前的验证："+customerOrder.toString());
						Integer result = customerOrderMapper.insertOrder(customerOrder);
						if(result==1){
							log.info("保存用户订单成功");
							//判断买入路由的用户是否拥有推荐人
							if(customerOrder.getIntroOpenid()!=null&&!"".equals(customerOrder.getIntroOpenid())){
								log.info("推荐人微信："+customerOrder.getIntroOpenid());
								//实例化用户收益订单对象
								CustomerOrder incomeOrder = new CustomerOrder();
								//向上级代理发送红包功能暂时不能做。预留，暂时只保存订单
								//查询推荐人是否有上级代理，一级代理20，二级代理50
								WiseCustomer customer = wiseCustomerMapper.selectCustomerByOpenID(customerOrder.getIntroOpenid());
								if(customer.getPid()==0){//表示推荐人没有上级代理
									incomeOrder.setCustomerOpenId(customerOrder.getIntroOpenid());
									incomeOrder.setPrice(70);
									//表示订单类型为收入类型
									incomeOrder.setOrderType(3);
									//表示代理收入未到账
									incomeOrder.setOrderStatus(3);
									incomeOrder.setOrderCreateTime(format.format(new Date()));
									//生成订单号
									String incomeOrderNumber = RandomStrUtils.getRebPacktMch_billno(DeveloInfor.BUSINESS_NUMBER);
									incomeOrder.setOrderNumber(incomeOrderNumber);
									Integer resultinco = customerOrderMapper.insertOrder(incomeOrder);
									if(resultinco==1){
										log.info("推荐人收益订单保存成功");
									}
								}else{//推荐人有上级代理
									//查询推荐人上级代理信息
									WiseCustomer proxyCustomer = wiseCustomerMapper.selectCustomerByID(customer.getPid());
									//实例化推荐人上级代理用户收益订单对象
									CustomerOrder proxyOrder = new CustomerOrder();
									proxyOrder.setCustomerOpenId(proxyCustomer.getCustomerOpenid());
									proxyOrder.setPrice(20);
									//表示订单类型为收入类型
									proxyOrder.setOrderType(3);
									//表示代理收入未到账
									proxyOrder.setOrderStatus(3);
									proxyOrder.setOrderCreateTime(format.format(new Date()));
									proxyOrder.setGoodInfo("推荐收益");
									//生成订单
									String proxyOrderNumber = RandomStrUtils.getRebPacktMch_billno(DeveloInfor.BUSINESS_NUMBER);
									proxyOrder.setOrderNumber(proxyOrderNumber);
									//保存推荐人上级代理收益订单
									Integer resultProxy = customerOrderMapper.insertOrder(proxyOrder);
									if(resultProxy==1){
										log.info("推荐人上级代理收益订单保存成功");
									}
									incomeOrder.setCustomerOpenId(customerOrder.getIntroOpenid());
									incomeOrder.setPrice(50);
									//表示订单类型为收入类型
									incomeOrder.setOrderType(3);
									//表示代理收入未到账
									incomeOrder.setOrderStatus(3);
									incomeOrder.setOrderCreateTime(format.format(new Date()));
									incomeOrder.setGoodInfo("推荐收益");
									String incomeOrderNumber = RandomStrUtils.getRebPacktMch_billno(DeveloInfor.BUSINESS_NUMBER);
									incomeOrder.setOrderNumber(incomeOrderNumber);
									Integer resultinco = customerOrderMapper.insertOrder(incomeOrder);
									if(resultinco==1){
										log.info("推荐人收益订单保存成功");
									}
								}
							}
							//清除全局map中的数据
							CacheManager.getCache((String)map.remove("openid")+"ORDER");
						}else{
							log.info("保存用户订单失败");
						}
					}else{
						log.info("微信支付回调获取用户订单对象失败："+customerOrder.toString());
					}
				}
				//拼接回复xml字符串
				String xml = "<xml> <return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
				//回复微信数据
				response.getWriter().write(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增收货地址
	 */
	@Override
	public Map<String, Object> addAddress(HttpSession session,
			CustomerAddress customerAddress) {
		Map<String,Object> outMap = new HashMap<String, Object>();
		try {
			String sessionid = session.getId();
			String openid = "";
			//获取MIFI的openID对象
			PageAuthorModel pageModel = (PageAuthorModel)session.getAttribute(sessionid+"pageModel");
			//判断openID对象是否存在
			if(pageModel!=null){
				log.info("新增收货地址获取的openID："+pageModel.getOpenid());
				openid = pageModel.getOpenid();
				customerAddress.setCustomerOpenid(openid);
			}else{
				log.info("新增收货地址获取openID失败");
				outMap.put("status", "FALSE");
				outMap.put("msg", "获取用户微信号失败");
				return outMap;
			}
			Integer result = customerAddressMapper.addAddress(customerAddress);
			if(result==1){
				log.info("新增收货地址成功："+result);
				outMap.put("status", "SUCCESS");
				outMap.put("msg", "新增收货地址成功");
				return outMap;
			}else{
				log.info("新增收货地址失败："+result);
				outMap.put("status", "FALSE");
				outMap.put("msg", "新增收货地址失败");
				return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
			outMap.put("msg", "新增收货地址异常");
			return outMap;
		}
	}
	
	/**
	 * 用户购买无线路由前获取地址信息
	 */
	@Override
	public Map<String, Object> getAddress(HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			String sessionid = session.getId();
			//获取session中的维泽mifi封装对象
			PageAuthorModel pageModel = (PageAuthorModel)session.getAttribute(sessionid+"pageModel");
			String openid = pageModel.getOpenid();
			CustomerOrder order = customerOrderMapper.selectOrderNew(openid);
			outMap.put("data", order);
			outMap.put("status", "SUCCESS");
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
