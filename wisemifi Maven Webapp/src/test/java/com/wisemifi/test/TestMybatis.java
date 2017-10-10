package com.wisemifi.test;

import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wisemifi.wx.button.model.Button;
import com.wisemifi.wx.button.model.ClickButton;
import com.wisemifi.wx.button.model.ComplexButton;
import com.wisemifi.wx.button.model.Menu;
import com.wisemifi.wx.button.model.ViewButton;
import com.wisemifi.wx.constant.DeveloInfor;
import com.wisemifi.wx.dao.WiseClientMapper;
import com.wisemifi.wx.dao.WiseCustomerMapper;
import com.wisemifi.wx.entity.WiseClient;
import com.wisemifi.wx.model.JsAPIConfig;
import com.wisemifi.wx.model.MiFiAuthorModel;
import com.wisemifi.wx.model.UnifiedOrder;
import com.wisemifi.wx.service.MyInfoService;
import com.wisemifi.wx.util.CacheManager;
import com.wisemifi.wx.util.HttpClientUtil;
import com.wisemifi.wx.util.MessageUtil;
import com.wisemifi.wx.util.PayUtil;
import com.wisemifi.wx.util.RandomStrUtils;
import com.wisemifi.wx.util.RedisDataStore;
import com.wisemifi.wx.util.UtilMD5;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = "classpath:applicationContext.xml")  
public class TestMybatis {
	private static final String ACCESS_TOKEN="mmWFm3q2zTLPW4mFZlUlyKXrkZ_7rKRTMy6-BBTMlXCL0YwapkFWfpZNcJVJNyolP_RmkcEQGnWPjp-"+
"20AGokI4xHivjV-P1tcmmj46fJ2k12oxMWM1GnUc5uGSwfiHmSTEcACAKLW";
	@Autowired
	private WiseClientMapper wiseClientMapper;
	@Autowired
	private WiseCustomerMapper myInfoService;
	/**
	 * 测试框架
	 */
	@Test
	public void test(){
		HttpSession session = null;
		String result = myInfoService.getQRcode("oN2fM1JyCrnLiQ-x1LlXHoegaebs");
		if(result==null){
			System.out.println("");
		}
		System.out.println(result);
	}
	
	/**
	 * 创建自定义菜单
	 */
	@Test
	public void testButton(){
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		String urlStr = url.replace("ACCESS_TOKEN", ACCESS_TOKEN);
		ComplexButton comp = new ComplexButton();
		comp.setName("维泽无线");
		ViewButton view1 = new ViewButton();
		view1.setName("申购维泽无线");
		view1.setType("view");
		view1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb125fba57e2524e2&"+
		"redirect_uri=http%3A%2F%2Fwisedp.com%2Fwisemifi%2Fwx%2Fwisemifi%2Ftobuypage&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		ViewButton view2 = new ViewButton();
		view2.setName("实名制");
		view2.setType("view");
		view2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb125fba57e2524e2&"+
		"redirect_uri=http%3A%2F%2Fwisedp.com%2Fwisemifi%2Fwx%2Fverified%2Ftoverified&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		comp.setSub_button(new Button[]{view1,view2});
		ViewButton view3 = new ViewButton();
		view3.setType("view");
		view3.setName("充值");
		view3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb125fba57e2524e2&"+
		"redirect_uri=http%3A%2F%2Fwisedp.com%2Fwisemifi%2Fwx%2Frecharge%2Ftorecharge&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		ViewButton view4 = new ViewButton();
		view4.setType("view");
		view4.setName("我的");
		view4.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb125fba57e2524e2&"+
		"redirect_uri=http%3A%2F%2Fwisedp.com%2Fwisemifi%2Fwx%2Fmyinfo%2Ftomyinfo&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		Menu menu = new Menu();
		menu.setButton(new Button[]{comp,view3,view4});
		Object obj = JSON.toJSON(menu);
		System.out.println(obj.toString());
		String result = HttpClientUtil.httpPost(urlStr, obj.toString());
		System.out.println(result);
		//ButtonUtil.creatButton(JSON.toJSONString(obj));
	}
	@Test
	public void testPage(){
		String codeUrl = DeveloInfor.CODE_ACCESS_TOKEN.replace("APPID", DeveloInfor.AppID).replace("SECRET", DeveloInfor.AppSecret).replace("CODE", "021gYDIi2UMX7F0OlpJi2fHSIi2gYDIV");
		String result = HttpClientUtil.httpGet(codeUrl);
		System.out.println(result);
	}
	//测试支付秘钥
	@Test
	public void testmiyao(){
		String str = UtilMD5.toMD5("wisemifi");
		System.out.println(str);
	}
	//测试支付回调
	@Test
	public void testpay(){
		SortedMap<Object, Object> map = new TreeMap<Object, Object>();
		 String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		 String nonce = UUID.randomUUID().toString();
		 String packageName = "prepay_id="+"wx2017081213454681406ded1a0202547071";
		map.put("appId", DeveloInfor.AppID);
		map.put("timeStamp", timestamp);
		map.put("nonceStr", nonce);
		map.put("package", packageName);
		map.put("signType", "MD5");
		String sing = PayUtil.createSign(map,DeveloInfor.API_KEY);
		System.out.println(sing);
	}
	//测试xml解析
	@Test
	public void testxml(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String time = "20170812165443";
			String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
			time = time.replaceAll(reg, "$1-$2-$3 $4:$5:$6");
			System.out.println(time);
			String xmlstr = "<xml><appid><![CDATA[wxb125fba57e2524e2]]></appid><attach><![CDATA[附加数据]]></attach><bank_type><![CDATA[CFT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><device_info><![CDATA[WEB]]></device_info><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1487331152]]></mch_id><nonce_str><![CDATA[619ae8cb-dcf7-481d-b31c-db0cdc]]></nonce_str><openid><![CDATA[oN2fM1JyCrnLiQ-x1LlXHoegaebs]]></openid><out_trade_no><![CDATA[1487331152201708123NdlXLF4XH]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[8F1705E0C64C1B0FD7FDF01A3B77C82A]]></sign><time_end><![CDATA[20170812165443]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[4008572001201708125858345334]]></transaction_id></xml>";
			String prepay_id = "";
			Document document = DocumentHelper.parseText(xmlstr);
			Element root = document.getRootElement();
			List<Element> list = root.elements();
			for(Element e:list){
				System.out.println(e.getName()+":"+e.getText());
				if(e.getName().equals("attach")){
					System.out.println(e.getText()+":"+e.getName());
					//prepay_id = e.getText();
				}
			}
			//JsAPIConfig con = PayUtil.createPayConfig(prepay_id);
			//System.out.println(con.getPackageName());
		} catch (Exception e) {
			
		}
		
	}
	//测试统一下单
	@Test
	public void testorder(){
		UnifiedOrder unifiedOrder = new UnifiedOrder();
		String callbackUrl = "http://www.wisedp.com/wisemifi/wisemifi/topay_back/";
		
		try {
			//String sessionid = session.getId();
			//MiFiAuthorModel pageModel = (MiFiAuthorModel)session.getAttribute(sessionid+"MiFiModel");
			String openid = "oN2fM1JyCrnLiQ-x1LlXHoegaebs";//pageModel.getOpenid();
			unifiedOrder.setOpenid(openid);
			unifiedOrder.setAppid(DeveloInfor.AppID);
			//附加数据，自定义
			unifiedOrder.setAttach("附加数据");
			//商品描述
			unifiedOrder.setBody("维泽mifi无线路由");
			//商户号
			unifiedOrder.setMch_id(DeveloInfor.BUSINESS_NUMBER);
			//随机串
			String nonce = UUID.randomUUID().toString().substring(0, 30);
			unifiedOrder.setNonce_str(nonce);
			//回调地址
			unifiedOrder.setNotify_url(callbackUrl);
			//用户IP
			unifiedOrder.setSpbill_create_ip("120.76.138.73");
			//订单号
			String orderid = RandomStrUtils.getRebPacktMch_billno(DeveloInfor.BUSINESS_NUMBER);
			unifiedOrder.setOut_trade_no(orderid);
			//金额
			unifiedOrder.setTotal_fee(12);
			//签名
			String sign = createUnifiedOrderSign(unifiedOrder);
			unifiedOrder.setSign(sign);
			//将实体转换为xml
			//XStream stream = new XStream();
			xstream.alias("xml", unifiedOrder.getClass());
			 String xml = xstream.toXML(unifiedOrder);
			 xml = xml.replace("__", "_");
			 System.out.println(xml);
			 String url = DeveloInfor.UNIFORM_ORDERS;
			 String str = HttpClientUtil.httpPost(url, xml);
			 System.out.println(str);
		} catch (Exception e) {
			
		}
	}
	
	//测试集合对象存入redis
	@Test
	public void testRedis(){
		Integer price = Integer.parseInt("123456");
		System.out.println(price);
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
    public String createUnifiedOrderSign(UnifiedOrder unifiedOrder){
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
}
