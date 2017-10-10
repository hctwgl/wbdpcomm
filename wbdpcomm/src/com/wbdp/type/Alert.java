package com.wbdp.type;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wbdp.server.WiseDataServerHandler;
import com.wbdp.util.DateUtil;
import com.wbdp.util.HexUtils;

/**
 * 警情信息解析类，用于解析警情信息
 * @author 
 *
 */
public class Alert {
	private static Logger logger = Logger.getLogger(Alert.class);
	/**
	 * 解析警报数据
	 * 
	 * @param str
	 *            需要解析的消息字符串
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> alarm(String str) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		//System.out.println("解析的消息：" + str);
		String devicenumber = str.substring(0, 12);
		map.put("devicenumber", devicenumber);
		//System.out.println("设备ID："+devicenumber);
		// 通过消息长度判断是否携带GPS数据
		int length = Integer.parseInt(str.substring(16, 20),16);
		//System.out.println("指令长度："+length);
		if (length == 9) {
			try {
			// 解析报警编号
			int num = Integer.parseInt(str.substring(20, 22),16);
			//System.out.println("警报编号：" + num);
			// 解析报警日期信息
			String date = "20" + str.substring(26, 28) + "-"
					+ str.substring(24, 26) + "-" + str.substring(22, 24);
			// 解析报警时间信息
			String time = str.substring(28, 30) + ":" + str.substring(30, 32) + ":"
					+ str.substring(32, 34);
			//将格林威治时间转换成北京时间
			String dateStr =DateUtil.formatTime(date+" "+time);
			//System.out.println("日期时间：" + dateStr);
			map.put("length", length);
			map.put("timeStamp", dateStr);
			map.put("alarmType", num);
			} catch (Exception e) {
				logger.error(e);
			}
		} else {
			try {
			// 解析报警编号
			int num = Integer.parseInt(str.substring(20, 22),16);
			//System.out.println("警报编号：" + num);
			// 解析报警日期信息
			String date = "20" + str.substring(26, 28) + "-"
					+ str.substring(24, 26) + "-" + str.substring(22, 24);
			// 解析报警时间信息
			String time = str.substring(28, 30) + ":" + str.substring(30, 32) + ":"
					+ str.substring(32, 34);
			//将格林威治时间转换成北京时间
			String dateStr =DateUtil.formatTime(date+" "+time);
			//System.out.println("日期时间："+dateStr);
			// 解析警报信息经纬度
			// 纬度34-42
			double dimension =Double.parseDouble(new DecimalFormat("#.0000").format(Double.parseDouble(str.substring(34, 36))+Double.parseDouble(str.substring(36, 42))/10000/60));
			//System.out.println("纬度：" + dimension);
			// 经度
			double longitude = Double.parseDouble(new DecimalFormat("#.0000").format(Double.parseDouble(str.substring(42, 45))+Double.parseDouble(str.substring(45, 51))/10000/60));
			//System.out.println("经度：" + longitude);
			// 位指示
			String msg = str.substring(51, 52);
			String hexMsg = HexUtils.hexString2binaryString("0" + msg);
			char[] c = hexMsg.toCharArray();
			// c[7]=1表示定位，c[6]=1表示N，即北纬。c[5]=1表示E，即东经。c[4]为0则表示GPS定位，为1则表示基站定位
			//System.out.println("位指示："+msg);
			map.put("length", length);
			map.put("date", dateStr);
			map.put("alarmType", num);
			map.put("dimension", dimension);
			map.put("longitude", longitude);
			map.put("gpsStatus", c[7]-'0');
			map.put("SouthOrNorth", c[6]-'0');
			map.put("EastOrWest", c[5]-'0');
			map.put("gpsType", c[4]-'0');
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return map;
	}
}
