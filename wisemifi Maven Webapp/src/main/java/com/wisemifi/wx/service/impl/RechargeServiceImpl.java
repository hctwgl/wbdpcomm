package com.wisemifi.wx.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
import com.wisemifi.wx.dao.CustomerOrderMapper;
import com.wisemifi.wx.entity.CustomerOrder;
import com.wisemifi.wx.model.JsAPIConfig;
import com.wisemifi.wx.model.PageAuthorModel;
import com.wisemifi.wx.model.RechargeAuthorModel;
import com.wisemifi.wx.model.UnifiedOrder;
import com.wisemifi.wx.service.RechargeService;
import com.wisemifi.wx.util.CacheManager;
import com.wisemifi.wx.util.HttpClientUtil;
import com.wisemifi.wx.util.JSAPIUitl;
import com.wisemifi.wx.util.PayUtil;
import com.wisemifi.wx.util.PushUtil;
import com.wisemifi.wx.util.RedisDataStore;
/**
 * 维泽mifi充值业务实现类
 * @author 汪赛军
 * date:2017年8月10日上午10:42:21
 *
 */
@Service
public class RechargeServiceImpl implements RechargeService {
	private static Logger log = Logger.getLogger(RechargeServiceImpl.class);
	
	@Autowired
	private CustomerOrderMapper customerOrderMapper;
	@Override
	public String getCodeAndopenID(String code, HttpSession session) {
		String result = "";
		String pathUrl = "recharge";
		PageAuthorModel pageModel = new PageAuthorModel();
		try {
			//拼接获取网页授权access_token接口
			String codeUrl = DeveloInfor.CODE_ACCESS_TOKEN.replace("APPID", DeveloInfor.AppID).replace("SECRET", DeveloInfor.AppSecret).replace("CODE",code);
			//获取微信服务端回复消息
			 result = HttpClientUtil.httpGet(codeUrl);
			 log.info("网页授权接收的数据："+result);
			//解析json数据
			 if(result!=null&&result!=""){
				 JSONObject obj = JSON.parseObject(result);
				 String access_token = obj.getString("access_token");
				 String openid = obj.getString("openid");
				 //获取sessionID
				 String sessionid = session.getId();
				 //将code与openID封装到模型中
				 pageModel.setCode(code);
				 pageModel.setOpenid(openid);
				 session.setAttribute(sessionid+"pageModel", pageModel);
			 }
			return pathUrl;
		} catch (Exception e) {
			log.error("异常原因："+e);
		}
		return null;
	}
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
	 * 统一下单
	 */
	@Override
	public Map<String, Object> wxPay(HttpSession session,CustomerOrder customerOrder,
			String userip) {
		UnifiedOrder unifiedOrder = new UnifiedOrder();
		Map<String,Object> outMap = new HashMap<String, Object>();
		//String callbackUrl = "http://www.wisedp.com/wisemifi/wisemifi/topay_back/";
		try {
			String sessionid = session.getId();
			PageAuthorModel pageModel = (PageAuthorModel)session.getAttribute(sessionid+"pageModel");
			String openid = pageModel.getOpenid();
			//将用户微信存入订单对象中
			customerOrder.setCustomerOpenId(openid);
			//统一下单,并返回支付配置实体类
			Map<String, Object> jsconfig = PayUtil.unifiedOrder(DeveloInfor.RECHAR_CALLBACK_URL,customerOrder.getPrice(),userip,openid,customerOrder.getGoodInfo());
			//将返回的商户订单号，即我们自己生成的订单号放入订单对象中
			customerOrder.setOrderNumber(jsconfig.get("orderid").toString());
			log.info("保存到全局map前的订单对象"+customerOrder.toString());
			//将订单对象存入自定义的全局map中
			CacheManager.putCache(openid+"ORDER", customerOrder);
			outMap.put("data", (JsAPIConfig)jsconfig.get("JsAPIConfig"));
			outMap.put("status", "SUCCESS");
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", "");
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}
	/**
	 * 支付成功后回调
	 */
	@Override
	public void payback(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		//CustomerOrder customerOrder = new CustomerOrder();
		Map<String,Object> map = new HashMap<String, Object>();
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//获取request中的流数据
			BufferedReader in=new BufferedReader(new InputStreamReader(request.getInputStream()));
			 StringBuilder sb = new StringBuilder();   
		       String xmlHead = "";
		         String xmlContent="";
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
						Integer price = Integer.parseInt(map.get("total_fee").toString());
						//订单金额，此处需将微信的单位分修改为元
						customerOrder.setPrice(price/100);
						customerOrder.setOrderStatus(1);
						customerOrder.setOrderType(2);
						//支付完成时间yyyyMMddHHmmss格式转换为yyyy-MM-dd HH:mm:ss格式
						String time = (String)map.get("time_end");
						String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
						time = time.replaceAll(reg, "$1-$2-$3 $4:$5:$6");
						customerOrder.setOrderCreateTime(time);
						//String sessionid = session.getId();
						//将订单对象存入数据库中
						//session.setAttribute(sessionid+"ORDER", customerOrder);
						Integer result = customerOrderMapper.insertOrder(customerOrder);
						if(result==1){
							log.info("保存客户充值订单成功");
						}else{
							log.info("保存客户充值订单失败");
						}
						//推送支付成功消息到客户
						PushUtil.payPush(customerOrder);
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

}
