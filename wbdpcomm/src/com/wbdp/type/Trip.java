package com.wbdp.type;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wbdp.util.DateUtil;


/**
 * 行程数据解析类
 * @author 汪赛军
 *
 */
public class Trip {
	private static Logger logger = Logger.getLogger(Trip.class);
	/**
	 * 解析行程数据
	 * @param str
	 * @return
	 */
	public static Map<String,Object> trip(String str){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			
		
		//解析设备编号
		String devicenumber = str.substring(0, 12);
		map.put("devicenumber", devicenumber);
		//解析行程ID
		int tripId = Integer.parseInt(str.substring(20,24),16);
		map.put("tripId", tripId);
		//System.out.println("行程id："+tripId);
		//解析点火日期与时间
		// 解析报警日期信息
		String startdate = "20" + str.substring(28, 30) + "-"
							+ str.substring(26, 28) + "-" + str.substring(24, 26);
		// 解析报警时间信息
		String starttime = str.substring(30, 32) + ":" + str.substring(32, 34) + ":"
				+ str.substring(34, 36);
		//将格林威治时间转换为北京时间
		String startdateStr = DateUtil.formatTime(startdate+" "+ starttime);
		map.put("startTime",startdateStr);
		//System.out.println("点火日期时间：" +startdateStr);
		//解析熄火日期与时间
		// 解析报警日期信息
				String enddate = "20" + str.substring(40, 42) + "-"
									+ str.substring(38, 40) + "-" + str.substring(36, 38);
				// 解析报警时间信息
				String endtime = str.substring(42, 44) + ":" + str.substring(44, 46) + ":"
						+ str.substring(46, 48);
				//将格林威治时间转换为北京时间
				String enddateStr = DateUtil.formatTime(enddate+" "+ endtime);
				map.put("endTime",enddateStr);
				//System.out.println("熄火日期时间：" +enddateStr);
				//解析该次行驶时间
				String drivertime = str.substring(48,52);
				map.put("drivertime", Integer.parseInt(drivertime,16));
				//System.out.println("本次行驶时间："+Integer.parseInt(drivertime,16)+"s");
				
				//解析本次行程油耗
				String oilwear = str.substring(52,60);
				map.put("oilwear", Integer.parseInt(oilwear,16));
				//System.out.println("本次行程油耗："+Integer.parseInt(oilwear,16)+"ml");
				
				//解析本次里程
				String mail = str.substring(60,68);
				map.put("mail", Integer.parseInt(mail,16));
				//System.out.println("本次里程："+Integer.parseInt(mail,16)+"m");
				
				//解析最高速度
				String hspeed = str.substring(68,70);
				map.put("hspeed", Integer.parseInt(hspeed,16));
				//System.out.println("最高速度："+Integer.parseInt(hspeed,16)+"KM/H");
				
				//解析发动机最高转速
				int rmp = ((Integer.parseInt(str.substring(70,72),16)*256+Integer.parseInt(str.substring(72,74),16)))>>2;
				map.put("rmp", rmp);
				//System.out.println("发动机最高转速："+rmp+"rmp");
				
				//解析冷却液最高温度
				int temp = Integer.parseInt(str.substring(74,76),16)-40;
				map.put("temp", temp);
				//System.out.println("冷却液最高温度："+temp+"℃");
				
				//解析急加速次数
				int rapidSpeed = Integer.parseInt(str.substring(76,78),16);
				map.put("rapidSpeed", rapidSpeed);
				//System.out.println("急加速次数："+rapidSpeed+"次");
				
				//急减速次数
				int suddenBrake = Integer.parseInt(str.substring(78,80),16);
				map.put("suddenBrake", suddenBrake);
				//System.out.println("急减速次数："+suddenBrake+"次");
				
				//超速行驶时间
				int overSpeedTime = Integer.parseInt(str.substring(80,84),16);
				map.put("overSpeedTime", overSpeedTime);
				//System.out.println("超速时间："+overSpeedTime+"s");
				
				//超速行驶里程
				int overSpeedMail = Integer.parseInt(str.substring(84,92),16);
				map.put("overSpeedMail", overSpeedMail);
				//System.out.println("超速行驶里程："+overSpeedMail+"m");
				
				//超速行驶耗油量
				int overSpeedOilWear = Integer.parseInt(str.substring(92,100),16);
				map.put("overSpeedOilWear", overSpeedOilWear);
				//System.out.println("超速行驶耗油量："+overSpeedOilWear+"ml");
				
				//高速行驶时间
				int hSpeedTime = Integer.parseInt(str.substring(100,104),16);
				map.put("hSpeedTime", hSpeedTime);
				//System.out.println("高速行驶时间："+hSpeedTime+"s");
				
				//高速行驶里程
				int hSpeedMail = Integer.parseInt(str.substring(104,112),16);
				map.put("hSpeedMail", hSpeedMail);
				//System.out.println("高速行驶里程："+hSpeedMail+"m");
				
				//高速行驶油耗
				int hSpeedOilWear = Integer.parseInt(str.substring(112,120),16);
				map.put("hSpeedOilWear", hSpeedOilWear);
				//System.out.println("高速行驶油耗："+hSpeedOilWear+"ml");
				
				//中速行驶时间
				int mSpeedTime = Integer.parseInt(str.substring(120,124),16);
				map.put("mSpeedTime", mSpeedTime);
				//System.out.println("中速行驶时间："+mSpeedTime+"s");
				
				//中速行驶里程
				int mSpeedMail = Integer.parseInt(str.substring(124,132),16);
				map.put("mSpeedMail", mSpeedMail);
				//System.out.println("中速行驶里程："+mSpeedMail+"m");
				
				//中速行驶油耗
				int mSpeedOilWear = Integer.parseInt(str.substring(132,140),16);
				map.put("mSpeedOilWear", mSpeedOilWear);
				//System.out.println("中速行驶油耗："+mSpeedOilWear+"ml");
				
				//低速行驶时间
				int lSpeedTime = Integer.parseInt(str.substring(140,144),16);
				map.put("lSpeedTime", lSpeedTime);
				//System.out.println("低速行驶时间："+lSpeedTime+"s");
				
				//低速行驶里程
				int lSpeedMail = Integer.parseInt(str.substring(144,152),16);
				map.put("lSpeedMail", lSpeedMail);
				//System.out.println("低速行驶里程："+lSpeedMail+"m");
				
				//低速行驶油耗
				int lSpeedOilWear = Integer.parseInt(str.substring(152,160),16);
				map.put("lSpeedOilWear", lSpeedOilWear);
				//System.out.println("低速行驶油耗："+lSpeedOilWear+"ml");
				
				//怠速时间
				int idleTime = Integer.parseInt(str.substring(160,164),16);
				map.put("idleTime", idleTime);
				//System.out.println("怠速时间："+idleTime+"s");
				
				//怠速油耗
				int idleTimeOilWear = Integer.parseInt(str.substring(164,172),16);
				map.put("idleTimeOilWear", idleTimeOilWear);
				//System.out.println("怠速油耗："+idleTimeOilWear+"ml");
				
				//急转弯次数
				int suddenTurn = Integer.parseInt(str.substring(172,174),16);
				map.put("suddenTurn", suddenTurn);
				//System.out.println("急转弯次数："+suddenTurn+"次");
		} catch (Exception e) {
			logger.error(e);
		}
		return map;
	}
}
