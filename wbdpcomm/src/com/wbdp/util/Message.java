package com.wbdp.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;



import net.sf.json.JSONObject;
/**
 * 发送短信验证码
 * 
 *2017-6-13
 */
public class Message {
	/**日志log*/
	private static Logger log = Logger.getLogger(Message.class);
	public static final String URL = "url"; //官网地址
	public static final String APPKEY = "appkey";//成为开发者自动生成
	public static final String SECRET = "secret";//成为开发者自动生成
	public static final String SMSTYPE = "normal";
	public static final String SMSFREESIGNNAME ="smsfreesignname";
	public static final String SMSTEMPLATECODE = "smstemplatecode";
	
	/**
	 * 发送短信验证,成功并返回200状态码与6位数验证码
	 * @param phone 待发送手机号 	orderCode 发送的短信内容
	 * @return json : 成功：{"statuscode":"200","code":"123456"}，失败：{"statuscode":"500"}
	 */
	public static JSONObject sentMessage(String phone,String orderCode){
		JSONObject resultJson = new JSONObject();
		if(!phone.equals(null)&&!phone.equals("")){
			if(isMobileNO(phone)){
				TaobaoClient client = new DefaultTaobaoClient(FileUitls.readWeiChatProperties(URL, MessageConstant.MESSAGE)
						, FileUitls.readWeiChatProperties(APPKEY, MessageConstant.MESSAGE)
						, FileUitls.readWeiChatProperties(SECRET, MessageConstant.MESSAGE));
				AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
				JSONObject smsParam = new JSONObject();
					req.setSmsType(FileUitls.readWeiChatProperties(SECRET, MessageConstant.MESSAGE));
					req.setSmsFreeSignName(FileUitls.readWeiChatProperties(SMSFREESIGNNAME, MessageConstant.MESSAGE));
					req.setRecNum(phone);
					req.setSmsTemplateCode(FileUitls.readWeiChatProperties(SMSTEMPLATECODE, MessageConstant.MESSAGE));
					//发送的内容
					String code = orderCode;
						smsParam.put("code", code);
					req.setSmsParam(smsParam.toString());
				AlibabaAliqinFcSmsNumSendResponse response;//根据接受号码发送短信
				try {
					response = client.execute(req);
					log.info("response:"+response);
					JSONObject repJson = JSONObject.fromObject(response);
					if(!repJson.has("error_response")){
						resultJson.put("statuscode", "200");
						resultJson.put("code", code);
						log.info("发送成功");
					}else{
						resultJson.put("statuscode", "500");
						log.info("发送失败");
					}
				} catch (ApiException e) {
					e.printStackTrace();
				}
			}else{
				resultJson.put("statuscode", "500");
				log.info("发送失败");
			}
			
		}else{
			resultJson.put("statuscode", "500");
			log.info("发送失败");
		}
		return resultJson;
	}
	/**
	 * 获取随机数
	 * @return
	 */
	private static String getRodomCode() {
		 String charValue = "";
		 StringBuffer buffer = null;
		 for (int i = 0; i < 6; i++) {
		      char c = (char) (randomInt(1, 10) + '0');
		      charValue += String.valueOf(c);
		 }
		 if(!charValue.equals("")){
			 buffer = new StringBuffer();
			 buffer.append("【");
			 buffer.append(charValue);
			 buffer.append("】");
		 }
		return buffer.toString();
	}
	/**
	 * 获取随机整数
	 * @param from
	 * @param to
	 * @return
	 */
	private static int randomInt(int from, int to) {
		Random r = new Random();//获取随机数
		return from + r.nextInt(to - from);
	}
	/**
	 * 去除验证码的前后“【”“】”
	 * @param code
	 * @return
	 */
	private static String interceptionCode(String code) {
		return code.substring(1, code.length()-1);
	}
	/**
	 * 校验手机号码
	 * @param mobiles
	 * @return
	 */
	private static boolean isMobileNO(String mobiles){  
		  Pattern p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$");  
		  Matcher m = p.matcher(mobiles);  
		return m.matches();  
	}  
	/**
	 * 处理json格式的验证码
	 * @param json
	 * @return
	 */
	public static String jsonOut(String phone){
		
		return "";
	}
	@Test
	public void test(){
//		System.out.println("randomInt:"+randomInt(0,10));
//		System.out.println("getRodomCode:"+getRodomCode());
		//sentMessage("13714318834").get("code");
		System.out.println(sentMessage("13714318834","AT+HOSTS=120.76.138.73,6666").toString());;
//		String rodomCode = getRodomCode();
//		System.out.println(rodomCode);
//		System.out.println(InterceptionCode(rodomCode));
//		System.out.println("isMobileNO:"+isMobileNO("18475525887"));
//		JSONObject sentMessage = Message.sentMessage("18475525887");
//		System.out.println(sentMessage.toString());
	}
}
