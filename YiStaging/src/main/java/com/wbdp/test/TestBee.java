package com.wbdp.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;





















import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.wbdp.wx.button.model.Button;
import com.wbdp.wx.button.model.ComplexButton;
import com.wbdp.wx.button.model.Menu;
import com.wbdp.wx.button.model.ViewButton;
import com.wbdp.wx.entity.Brushcredit;
import com.wbdp.wx.entity.Pollen;
import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.mapper.BeeMapper;
import com.wbdp.wx.mapper.BrushcreditMapper;
import com.wbdp.wx.mapper.PollenMapper;
import com.wbdp.wx.mapper.UserMapper;
import com.wbdp.wx.model.ISBlackBee;
import com.wbdp.wx.model.Result;
import com.wbdp.wx.model.UserOrder;
import com.wbdp.wx.service.shopCart.ShopCartService;
import com.wbdp.wx.utils.RedisDataStore;
import com.wbdp.wx.utils.http.HttpRequestUtil;
import com.wbdp.wx.utils.http.HttpUtil;
import com.wbdp.wx.utils.http.WeiXinResult;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = "classpath:applicationContext.xml")  
public class TestBee {
	@Autowired
	private ShopCartService shopCartService;
	@Autowired
    private BeeMapper mineBeeMapper;
	 @Autowired
	 private BeeMapper beeMapper;
	 @Autowired
	 private UserMapper userMapper;
	 @Autowired
	 private BrushcreditMapper brushcreditMapper;
	 @Autowired
	 private PollenMapper pollenMapper;
	//购物车是否能用
	@Test
	public void test01() throws CustomException{
		/*HttpSession session = null;
		Result result = shopCartService.getShopCart(session);
		 List<UserOrder> userOrdersList2 = (List<UserOrder>)result.getData();
		 for(UserOrder o:userOrdersList2){
			 System.out.println(o.getShopcartID());
		 }*/
		//测试检查字符串是否包含子字符串
		String str = "16164654611adfadcaconfidenceacacweca65a1c5wea";
		if(str.indexOf("confidence")!=-1){  
			System.out.println("存在");
		 }else{ 
			 System.out.println("不存在");
		} 
	}
	@Test
	public void testBrush(){
		/*Map<String,Object> result = brushcreditMapper.getContract("ojzcuwq6u4dfe8KzZcwuyGtqxc5s");
		System.out.println(result.get("BeeName"));*/
		List<Brushcredit> list = brushcreditMapper.getUserBrush("ojzcuwq0nYE8LLMFdy6iIbB7bL7U");
		for(Brushcredit b:list){
			System.out.println(b.getEndTime());
		}
	}
	/**
	 * 创建自定义菜单
	 */
	@Test
	public void testButton(){
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		String urlStr = url.replace("ACCESS_TOKEN", "QxUxh9mpO_6DZPwxIsnEKPz0KnPOSjfJOtLi3Ntfj-lwBcFnFC8tcTNai-yTAi0TDD7uMfSO-cRYHEOswyUcWmDS0wRcyk7lu7JePQUXu8DnwczcXtxI4Z_Z6AzNQNCGXUDfADAPMV");
		ComplexButton comp = new ComplexButton();
		comp.setName("手机商城");
		ViewButton view1 = new ViewButton();
		view1.setName("手机商城");
		view1.setType("view");
		view1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&"+
		"redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2FtoPhoneShop&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		ViewButton view2 = new ViewButton();
		view2.setName("购物车");
		view2.setType("view");
		view2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&"+
				"redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2FtoShopCart&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		//comp.setSub_button(new Button[]{view1,view2});
		//将手机商城与购物车放入组合键
		comp.setSub_button(new Button[]{view1,view2});
		ViewButton view3 = new ViewButton();
		view3.setType("view");
		view3.setName("我的");
		view3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&"+
				"redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftomine&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		ViewButton view4 = new ViewButton();
		view4.setType("view");
		view4.setName("任性刷");
		view4.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&"+
				"redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftobrush&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		Menu menu = new Menu();
		menu.setButton(new Button[]{view4,view3});
		Object obj = JSONObject.fromObject(menu);
		System.out.println(obj.toString());
		String result = HttpUtil.sendPost(urlStr, obj.toString());
		System.out.println(result);
		//ButtonUtil.creatButton(JSON.toJSONString(obj));
	}
	@Test
	public void test(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beeid", 52l);
		map.put("isNoOlder",0);
		map.put("insuranceNum", null);
		map.put("plateNum", null);
		map.put("insuranceComp", null);
		int result = mineBeeMapper.updateInsNumByBeeID(map);
		System.out.println(result);
	}
	@Test
	public void test1(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beeid", 52l);
		map.put("isNoOlder",0);
		map.put("insuranceNum", null);
		map.put("plateNum", null);
		map.put("insuranceComp", null);
		int result = mineBeeMapper.updateInsNumByBeeID(map);
		System.out.println(result);
	}
	@Test
	public void test2() throws ParseException{
		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = "1503551513938";
		System.out.println(format.format(new Date(Long.parseLong(time))));*/
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("usedcredit", 52l);
		map.put("updatetime",new Date());
		map.put("id", 51);
		Object pobj = JSON.toJSON(map);
		 Jedis jedis = RedisDataStore.getconnetion();
		 jedis.set("map",pobj.toString());
		 Map<String,Object> pmap = (Map<String,Object>) JSONObject.fromObject(jedis.get("map"));
		System.out.println(pmap.get("usedcredit") instanceof Integer);
	}
	/**
	 * 测试二维码生成
	 */
	@Test
	public void test3(){
		String token = "k2_Ge0a-yScBMInb-KvWjeV7otNsQCF0GU1oxit7UX1nbr79t6SrcJ87YmsycZaRTLDSuyo"+
	"u2_GODBNgU22aST424B4WQs3u6YLx7N2cw2ZKhhJ9S2VWK1orgnUbArsiRQSiAAAXWY";
		//临时二维码
		String getticket = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKENPOST";
		//通过ticket换取二维码
		String creat = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		String json = "{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", "+
		"\"action_info\": {\"scene\": {\"scene_str\": \"test\"}}}";
		//String result = HttpUtil.sendPost(getticket.replace("TOKENPOST", token),json);
		//System.out.println(result);
		String ticket = "gQGW8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyQ3RUSzlXZlRjSzIxR0drVTFwY0EAAgQqWq9ZAwSAOgkA";
		//JSONObject msg = HttpUtil.doGetStr(creat.replace("TICKET", "gQGW8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyQ3RUSzlXZlRjSzIxR0drVTFwY0EAAgQqWq9ZAwSAOgkA"));
		//Object obj = JSON.parse(msg.toString());
		//System.out.println(msg.toString());
		TreeMap<String,String> params = new TreeMap<String,String>();  
	    params.put("ticket", HttpRequestUtil.urlEncode(ticket, HttpRequestUtil.DEFAULT_CHARSET));  
	    WeiXinResult result = HttpRequestUtil.downMeaterMetod(params,HttpRequestUtil.GET_METHOD,"https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket,"E:/QRcode/test.jpg");  
	    System.out.println(result.getMsg());
	   //result.setSuccess(true);
	}
	//测试生成自定义二维码
	@Test
	public void test4(){
		
	}
}
