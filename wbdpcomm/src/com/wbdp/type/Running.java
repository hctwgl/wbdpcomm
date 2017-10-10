package com.wbdp.type;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wbdp.util.DateUtil;
import com.wbdp.util.HexUtils;


/**
 * 点熄火报告解析类，解析点熄火报告
 * @author 汪赛军
 *
 */
public class Running {
	private static Logger logger = Logger.getLogger(Running.class);
	/**
	 * 解析终端点火熄火报告
	 * @param str
	 * @return
	 */
	public static Map<String,Object> running(String str){
		Map<String,Object> map = new HashMap<String, Object>();
		//解析设备编号
		String devicenumber = str.substring(0, 12);
		map.put("devicenumber", devicenumber);
		// 通过消息长度判断是否携带GPS数据
		int length = Integer.parseInt(str.substring(16, 20));
		//System.out.println("消息长度："+length);
		if(length==9){
			try {
		
			//解析点熄火状态类型，1代表点火，0代表熄火
			int runType = Integer.parseInt(str.substring(20,22),16);
			map.put("runType", runType);
			//System.out.println("数据类型："+runType);
			//解析行程ID
			int tripId =  Integer.parseInt(str.substring(22,26),16);
			map.put("tripId", tripId);
			//System.out.println("行程ID："+tripId);
			//解析日期时间
			//解析日期时间
			// 解析日期信息
			String startdate = "20" + str.substring(30, 32) + "-"
								+ str.substring(28, 30) + "-" + str.substring(26, 28);
			// 解析时间信息
			String starttime = str.substring(32, 34) + ":" + str.substring(34, 36) + ":"
					+ str.substring(36, 38);
			//将格林威治时间转换成北京时间
			String dateStr = DateUtil.formatTime( startdate+" "+ starttime);
			map.put("date",dateStr);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			//System.out.println("日期时间：" +dateStr);
		}else{
			try {
				
			
			// 解析点熄火编号
						int runType = Integer.parseInt(str.substring(20, 22));
						map.put("runType", runType);
						//System.out.println("点熄火状态：" + runType);
						//解析行程ID
						int tripId =  Integer.parseInt(str.substring(22,26),16);
						map.put("tripId", tripId);
						//System.out.println("行程ID："+tripId);
						// 解析点熄火日期信息
						String date = "20" + str.substring(30, 32) + "-"
								+ str.substring(28, 30) + "-" + str.substring(26, 28);
						// 解析点熄火时间信息
						String time = str.substring(32, 34) + ":" + str.substring(34, 36) + ":"
								+ str.substring(36, 38);
						//将格林威治时间转换成北京时间
						String dateStr = DateUtil.formatTime( date+" "+ time);
						//System.out.println("日期时间：" + dateStr);
						// 解析点熄火信息经纬度
						// 纬度38-46
						double dimension = Double.parseDouble(new DecimalFormat("#.0000").format(Double.parseDouble(str.substring(38, 40))+Double.parseDouble(str.substring(40, 46))/10000/60));
						//System.out.println("纬度：" + dimension);
						// 经度
						double longitude = Double.parseDouble(new DecimalFormat("#.0000").format(Double.parseDouble(str.substring(46, 49))+Double.parseDouble(str.substring(49, 55))/10000/60));
						//System.out.println("经度：" + longitude);
						// 位指示
						String msg = str.substring(55, 56);
						String hexMsg = HexUtils.hexString2binaryString("0" + msg);
						char[] c = hexMsg.toCharArray();
						// c[7]=1表示定位，c[6]=1表示N，即北纬。c[5]=1表示E，即东经。c[4]为0则表示GPS定位，为1则表示基站定位
						//System.out.println(hexMsg);
						map.put("time", dateStr);
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
