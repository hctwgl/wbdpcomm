package com.wbdp.type;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;


public class AlertCode {
	private static Logger logger = Logger.getLogger(AlertCode.class);
	/**
	 * 解析故障码
	 * 
	 * @param str
	 *            需要解析的消息字符串
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> alertCode(String str) throws Exception {
		Map<String,Object> map = null;
		try {
		map = new HashMap<String,Object>();
		
		//System.out.println("解析的消息：" + str);
		String devicenumber = str.substring(0, 12);
		map.put("devicenumber", devicenumber);
		//System.out.println("设备ID："+devicenumber);
		// 通过消息长度判断故障码个数
		int length = Integer.parseInt(str.substring(16, 20),16);
		//System.out.println("指令长度："+str.substring(16, 20));
		//解析故障码
		if(length<18){
			int first = Integer.parseInt(str.substring(20,21),16);
			//P开头故障码
			if(first>=0&&first<=3){
				String alert = "P"+ str.substring(20,24);
				map.put("alertcode", alert);
			}
			//C开头故障码
			if(first>3&&first<8){
				String alert = "";
				switch (first) {
				case 4:
					 alert = "C0"+ str.substring(21,24);
					 map.put("alertcode", alert);
					break;
				case 5:
					 alert = "C1"+ str.substring(21,24);
					 map.put("alertcode", alert);
					break;
				case 6:
					 alert = "C2"+ str.substring(21,24);
					 map.put("alertcode", alert);
					break;
				case 7:
					 alert = "C3"+ str.substring(21,24);
					 map.put("alertcode", alert);
					break;

				default:
					break;
				}
			}
			//U开头故障码
			if(first>11&&first<16){
				String alert = "";
				switch (first) {
				case 12:
					 alert = "U0"+ str.substring(21,24);
					 map.put("alertcode", alert);
					break;
				case 13:
					 alert = "U1"+ str.substring(21,24);
					 map.put("alertcode", alert);
					break;
				case 14:
					 alert = "U2"+ str.substring(21,24);
					 map.put("alertcode", alert);
					break;
				case 15:
					 alert = "U3"+ str.substring(21,24);
					 map.put("alertcode", alert);
					break;

				default:
					break;
				}
			}
		}else if(length==0){
			map.put("alertcode", "P000");
		}else{
			int first1 = Integer.parseInt(str.substring(20,21),16);
			int first2 = Integer.parseInt(str.substring(26,27),16);
			int first3 = Integer.parseInt(str.substring(32,33),16);
			/**
			 * 第一个故障码
			 */
			String alert1 = "";
			//p开头故障码
			if(first1>=0&&first1<4){
				 alert1 = "P"+ str.substring(20,24);
				 map.put("alert1", alert1);
			}
			//c开头故障码
			if(first1>3&&first1<8){
				String alert2 = "";
				switch (first1) {
				case 4:
					alert1 = "C0"+ str.substring(21,24);
					 map.put("alert1", alert1);
					break;
				case 5:
					alert1 = "C1"+ str.substring(21,24);
					 map.put("alert1", alert1);
					break;
				case 6:
					alert1 = "C2"+ str.substring(21,24);
					 map.put("alert1", alert1);
					break;
				case 7:
					alert1 = "C3"+ str.substring(21,24);
					 map.put("alert1", alert1);
					break;

				default:
					break;
				}
			}
			//U开头故障码
			if(first1>11&&first1<16){
				switch (first1) {
				case 12:
					alert1 = "U0"+ str.substring(21,24);
					 map.put("alert1", alert1);
					break;
				case 13:
					alert1 = "U1"+ str.substring(21,24);
					 map.put("alert1", alert1);
					break;
				case 14:
					alert1 = "U2"+ str.substring(21,24);
					 map.put("alert1", alert1);
					break;
				case 15:
					alert1 = "U3"+ str.substring(21,24);
					 map.put("alert1", alert1);
					break;

				default:
					break;
				}
			}
			String code = "531608010049,3087,0012,(20-25)010201,(26-31)011801,(32-37)011301,010202,011802,011302,A3";
			/**
			 * 第二个故障码
			 */
			String alert2 = "";
			//p开头故障码
			if(first2>=0&&first2<4){
				alert2 = "P"+ str.substring(26,30);
				map.put("alert2", alert2);
			}
			//C开头故障码
			if(first2>3&&first2<8){
				String alert = "";
				switch (first2) {
				case 4:
					alert2 = "C0"+ str.substring(27,30);
					 map.put("alert2", alert2);
					break;
				case 5:
					alert2 = "C1"+ str.substring(27,30);
					 map.put("alert2", alert2);
					break;
				case 6:
					alert2 = "C2"+ str.substring(27,30);
					 map.put("alert2", alert2);
					break;
				case 7:
					alert2 = "C3"+ str.substring(27,30);
					 map.put("alert2", alert2);
					break;

				default:
					break;
				}
			}
			//U开头故障码
			if(first2>11&&first2<16){
				String alert = "";
				switch (first2) {
				case 12:
					alert2 = "U0"+ str.substring(27,30);
					 map.put("alert2", alert2);
					break;
				case 13:
					alert2 = "U1"+ str.substring(27,30);
					 map.put("alert2", alert2);
					break;
				case 14:
					alert2 = "U2"+ str.substring(27,30);
					 map.put("alert2", alert2);
					break;
				case 15:
					alert2 = "U3"+ str.substring(27,30);
					 map.put("alert2", alert2);
					break;

				default:
					break;
				}
			}
			/**
			 * 第三个故障码
			 */
			String alert3 = "";
			//p开头故障码
			if(first3>=0&&first3<4){
				alert3 = "P"+ str.substring(32,36);
				map.put("alert3", alert3);
			}
			//C开头故障码
			if(first3>3&&first3<8){
				String alert = "";
				switch (first3) {
				case 4:
					alert3 = "C0"+ str.substring(33,36);
					 map.put("alert3", alert3);
					break;
				case 5:
					alert3 = "C1"+ str.substring(33,36);
					 map.put("alert3", alert3);
					break;
				case 6:
					alert3 = "C2"+ str.substring(33,36);
					 map.put("alert3", alert3);
					break;
				case 7:
					alert3 = "C3"+ str.substring(33,36);
					 map.put("alert3", alert3);
					break;

				default:
					break;
				}
			}
			//U开头故障码
			if(first3>11&&first3<16){
				String alert = "";
				switch (first2) {
				case 12:
					alert3 = "U0"+ str.substring(33,36);
					 map.put("alert3", alert3);
					break;
				case 13:
					alert3 = "U1"+ str.substring(33,36);
					 map.put("alert3", alert3);
					break;
				case 14:
					alert3 = "U2"+ str.substring(33,36);
					 map.put("alert3", alert3);
					break;
				case 15:
					alert3 = "U3"+ str.substring(33,36);
					 map.put("alert3", alert3);
					break;
				default:
					break;
				}
			}
		}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			map.put("date", format.format(date));
			//System.out.println(format.format(date));
			} catch (Exception e) {
				logger.error(e);
			}
		return map;
	}
}
