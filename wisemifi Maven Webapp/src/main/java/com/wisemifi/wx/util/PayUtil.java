package com.wisemifi.wx.util;

import java.io.Writer;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wisemifi.wx.constant.DeveloInfor;
import com.wisemifi.wx.controller.WiseMIFIController;
import com.wisemifi.wx.model.JsAPIConfig;
import com.wisemifi.wx.model.UnifiedOrder;

/**
 * 微信支付工具类
 * @author 汪赛军
 * date:2017年8月11日下午5:40:15
 *
 */
public class PayUtil {
	private static Logger log = Logger.getLogger(PayUtil.class);
	/**
	 * 统一下单后获取prepay_id
	 * @param callbackUrl 回调URL
	 * @param money 金额
	 * @param userip 用户IP
	 * @param openid 用户openID
	 * @param desc 商品描述
	 * @return
	 */
	public static Map<String,Object> unifiedOrder(String callbackUrl,int money,String userip,String openid,String desc){
		UnifiedOrder unifiedOrder = new UnifiedOrder();
		Map<String,Object> outMap = new HashMap<String, Object>();
		try {
			unifiedOrder.setOpenid(openid);
			unifiedOrder.setAppid(DeveloInfor.AppID);
			//附加数据，自定义
			unifiedOrder.setAttach("附加数据");
			//商品描述
			unifiedOrder.setBody(desc);
			//商户号
			unifiedOrder.setMch_id(DeveloInfor.BUSINESS_NUMBER);
			//随机串
			String nonce = UUID.randomUUID().toString().substring(0, 30);
			unifiedOrder.setNonce_str(nonce);
			//回调地址
			unifiedOrder.setNotify_url(callbackUrl);
			//用户IP
			unifiedOrder.setSpbill_create_ip(userip);
			//商户订单号
			String orderid = RandomStrUtils.getRebPacktMch_billno(DeveloInfor.BUSINESS_NUMBER);
			unifiedOrder.setOut_trade_no(orderid);
			//金额
			unifiedOrder.setTotal_fee(money);
			//生成签名
			String sign = createUnifiedOrderSign(unifiedOrder);
			unifiedOrder.setSign(sign);
			//将实体转换为xml
			//XStream stream = new XStream();
			xstream.alias("xml", unifiedOrder.getClass());
			 String xml = xstream.toXML(unifiedOrder);
			 //xstream转换的xml中单下划线会变成双下划线，这里需要进行一个替换
			 xml = xml.replace("__", "_");
			 //发送post请求进行统一下单
			 String result = HttpClientUtil.httpPost(DeveloInfor.UNIFORM_ORDERS, xml);
			 log.info("统一下单回复消息："+result);
			 //取出xml中的prepay_id，并生成支付配置
			 String prepay_id = "";
			 Document document = DocumentHelper.parseText(result);
			Element root = document.getRootElement();
			List<Element> list = root.elements();
			for(Element e:list){
				if(e.getName().equals("prepay_id")){
					prepay_id = e.getText();
				}
			}
			log.info("返回的prepay_id:"+prepay_id);
			JsAPIConfig con = createPayConfig(prepay_id);
			outMap.put("JsAPIConfig", con);
			outMap.put("orderid", orderid);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取统一下单签名
	 * @Title: createUnifiedOrderSign
	 * @Description: TODO
	 * @param @param unifiedOrder
	 * @param @return    
	 * @return String    
	 * @throws
	 */
	public static String createUnifiedOrderSign(UnifiedOrder unifiedOrder){
	    StringBuffer sign = new StringBuffer();
	    sign.append("appid=").append(unifiedOrder.getAppid());
	    sign.append("&attach=").append(unifiedOrder.getAttach());
	    sign.append("&body=").append(unifiedOrder.getBody());
	    sign.append("&device_info=").append(unifiedOrder.getDevice_info());
	    sign.append("&mch_id=").append(unifiedOrder.getMch_id());
	    sign.append("&nonce_str=").append(unifiedOrder.getNonce_str());
	    sign.append("&notify_url=").append(unifiedOrder.getNotify_url());
	    sign.append("&openid=").append(unifiedOrder.getOpenid());
	    sign.append("&out_trade_no=").append(unifiedOrder.getOut_trade_no());
	    sign.append("&spbill_create_ip=").append(unifiedOrder.getSpbill_create_ip());
	    sign.append("&total_fee=").append(unifiedOrder.getTotal_fee());
	    sign.append("&trade_type=").append(unifiedOrder.getTrade_type());
	    sign.append("&key=").append(DeveloInfor.API_KEY);
	    return DigestUtils.md5Hex(sign.toString()).toUpperCase();
	}
	/**
	 * 扩展xstream使其支持CDATA
	 */
	private static XStream xstream = new XStream(new XppDriver() {
	    public HierarchicalStreamWriter createWriter(Writer out) {
	        return new PrettyPrintWriter(out) {
	            // 对所有xml节点的转换都增加CDATA标记
	            boolean cdata = true;
	            String cname = "";
	            @SuppressWarnings("unchecked")
	            public void startNode(String name, Class clazz) {
	            	cname = name;
	                super.startNode(name, clazz);
	            }
	            protected void writeText(QuickWriter writer, String text) {
	                if (cdata) {
	                	if(cname.equals("CreateTime"))
	                		writer.write(text);
	                	else
	                		writer.write("<![CDATA["+text+"]]>");
	                } else {
	                    writer.write(text);
	                }
	            }
	        };
	    }
	});

	/**
	 * 获取支付配置
	 * @Title: createPayConfig
	 * @Description: TODO
	 * @param @param preayId 统一下单prepay_id
	 * @param @return
	 * @param @throws Exception    
	 * @return JsAPIConfig    
	 * @throws
	 */
	/*
	public static JsAPIConfig createPayConfig(String prepayId) throws Exception {
	    JsAPIConfig config = new JsAPIConfig();
	    String nonce = UUID.randomUUID().toString();
	    String timestamp = Long.toString(System.currentTimeMillis() / 1000);
	    String packageName = "prepay_id="+prepayId;
	    //签名
	    StringBuffer sign = new StringBuffer();
	    sign.append("appId=").append(DeveloInfor.AppID);
	    sign.append("&nonceStr=").append(nonce);
	    sign.append("&package=").append(packageName);
	    sign.append("&signType=").append(config.getSignType());
	    sign.append("&timeStamp=").append(timestamp);
	    sign.append("&key=").append(DeveloInfor.API_KEY);
	    //生成签名
	    String signature = DigestUtils.md5Hex(sign.toString()).toUpperCase();
	
	    config.setAppId(DeveloInfor.AppID);
	    config.setNonce(nonce);
	    config.setTimestamp(timestamp);
	    config.setPackageName(packageName);
	    config.setSignature(signature);
	
	    return config;
}*/
	/**
	 * 获取支付配置
	 * @Title: createPayConfig
	 * @Description: TODO
	 * @param @param preayId 统一下单prepay_id
	 * @param @return
	 * @param @throws Exception    
	 * @return JsAPIConfig    
	 * @throws
	 */
	public static JsAPIConfig createPayConfig(String prepayId){
		JsAPIConfig config = new JsAPIConfig();
		SortedMap<Object, Object> map = new TreeMap<Object, Object>();
		 String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		 String nonce = UUID.randomUUID().toString();
		 String packageName = "prepay_id="+prepayId;
		 log.info("prepay_Id拼接后："+packageName);
		map.put("appId", DeveloInfor.AppID);
		map.put("timeStamp", timestamp);
		map.put("nonceStr", nonce);
		map.put("package", packageName);
		map.put("signType", config.getSignType());
		//生成签名
		String sign = createSign(map,DeveloInfor.API_KEY);
		log.info("生成的签名："+sign);
		config.setAppId(DeveloInfor.AppID);
	    config.setNonce(nonce);
	    config.setTimestamp(timestamp);
	    config.setPackageName(packageName);
	    config.setSignature(sign);
		return config;
	}
	/**
	 * 生成签名，微信专用
	 * @param parameters
	 * @param key
	 * @return
	 */
	public static String createSign(SortedMap<Object, Object> parameters, String key) {
	       StringBuffer sb = new StringBuffer();
	       Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
	       Iterator it = es.iterator();
	       while (it.hasNext()) {
	           Map.Entry entry = (Map.Entry)it.next();
	           String k = (String)entry.getKey();
	           Object v = entry.getValue();
	           if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
	               sb.append(k + "=" + v + "&");
	           }
	       }
	       sb.append("key=" + key);
	       String tosend = sb.toString();
	       MessageDigest md = null;
	       byte[] bytes = null;
	       try {
	            
	           md = MessageDigest.getInstance("MD5");
	           bytes = md.digest(tosend.getBytes("utf-8"));
	       }
	       catch (Exception e) {
	           e.printStackTrace();
	       }
	       String signStr = new String(bytes);
	       String sign = byteToStr(bytes).toUpperCase();
	       return sign;
	   }
	/**
	 * 字节数组转换为字符串
	 * @param byteArray
	 * @return
	 */
	public static String byteToStr(byte[] byteArray) {
	      String strDigest = "";
	      for (int i = 0; i < byteArray.length; i++) {
	          strDigest += byteToHexStr(byteArray[i]);
	      }
	      return strDigest;
	  }
	/**
	 * 字节转换为字符串
	 * @param mByte
	 * @return
	 */
	 public static String byteToHexStr(byte mByte) {
	      char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	      char[] tempArr = new char[2];
	      tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
	      tempArr[1] = Digit[mByte & 0X0F];
	       
	      String s = new String(tempArr);
	      return s;
	  }
}
