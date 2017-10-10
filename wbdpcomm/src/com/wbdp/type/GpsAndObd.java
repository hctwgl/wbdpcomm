package com.wbdp.type;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSONObject;
import com.wbdp.util.DateUtil;
import com.wbdp.util.HexUtils;
import com.wbdp.util.RedisDataStore;

/**
 * GPS、OBD数据解析类，用于解析GPSOBD数据
 * 
 * @author 汪赛军
 * 
 */
public class GpsAndObd {
	private static Logger logger = Logger.getLogger(GpsAndObd.class);
	private static Jedis jedis = null;
	/**
	 * 解析GPS位置信息
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, Object> gpsMsg(String str) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 解析设备编号
			String devicenumber = str.substring(0, 12);
			map.put("devicenumber", devicenumber);
			// 解析数据类型，是否为盲区数据00代表盲区数据
			int msgType = Integer.parseInt(str.substring(20, 22), 16);
			map.put("msgType", msgType);
			 //System.out.println("数据类型："+str.substring(20, 22));

			// 解析行程ID
			int tripId = Integer.parseInt(str.substring(22, 26), 16);
			map.put("tripId", tripId);
			 //System.out.println("行程ID："+str.substring(22, 26));

			// 解析日期时间
			// 解析日期信息
			String startdate = "20" + str.substring(30, 32) + "-"
					+ str.substring(28, 30) + "-" + str.substring(26, 28);
			// 解析时间信息
			String starttime = str.substring(32, 34) + ":"
					+ str.substring(34, 36) + ":" + str.substring(36, 38);
			String dateStr = DateUtil.formatTime(startdate + " " + starttime);
			map.put("date", dateStr);
			 //System.out.println("日期时间：" +dateStr);
			// 纬度38-46
			double dimension = Double.parseDouble(new DecimalFormat("#.0000")
					.format(Double.parseDouble(str.substring(38, 40))
							+ Double.parseDouble(str.substring(42, 46)) / 10000
							/ 60));
			map.put("dimension", dimension);
			 //System.out.println("纬度：" + str.substring(38, 46));
			// 经度46-55
			double longitude = Double.parseDouble(new DecimalFormat("#.0000")
					.format(Double.parseDouble(str.substring(46, 49))
							+ Double.parseDouble(str.substring(51, 55))));
			map.put("longitude", longitude);
			 //System.out.println("经度：" + str.substring(46, 55));
			// 位指示
			String msg = str.substring(55, 56);
			String hexMsg = HexUtils.hexString2binaryString("0" + msg);
			char[] c = hexMsg.toCharArray();
			map.put("gpsStatus", c[7] - '0');
			map.put("southOrNorth", c[6] - '0');
			map.put("eastOrWest", c[5] - '0');
			map.put("gpsType", c[4] - '0');
			// c[7]=1表示定位，c[6]=1表示N，即北纬。c[5]=1表示E，即东经。c[4]为0则表示GPS定位，为1则表示基站定位
			 //System.out.println("定位位指示据："+str.substring(55, 56));

			// 解析速度信息
			int speed = Integer.parseInt(str.substring(56, 58), 16);
			map.put("speed", speed);
			 //System.out.println("速度："+str.substring(56, 58));

			// 解析方向信息
			int direction = Integer.parseInt(str.substring(58, 60), 16) << 1;
			map.put("direction", direction);
			 //System.out.println("方向："+str.substring(58, 60));

			// 解析GPS卫星个数
			int gpsNum = Integer.parseInt(str.substring(60, 62), 16);
			map.put("gpsNum", gpsNum);
			 //System.out.println("GPS卫星个数："+str.substring(60, 62));

			// 解析GPS信号质量
			int gpsPower = Integer.parseInt(str.substring(62, 64), 16);
			map.put("gpsPower", gpsPower);
			 //System.out.println("GPS信号质量："+str.substring(62, 64));

			// 解析里程str.substring(64,72)此处示例数据有误
			int mail = Integer.parseInt(str.substring(64, 72), 16);
			map.put("mail", mail);
			 //System.out.println("里程："+str.substring(64, 72));

			// 解析设备状态
			String deviceStatus = str.substring(72, 80);
			//System.out.println("设备状态："+str.substring(72, 80));
			// 发送机状态:0为正常，包括续航m[7]、发动机故障m[6]、冷却液温度过低m[5]是否支持该车m[4]，读取汽车协议m[3]-m[1]
			String motor = HexUtils.hexString2binaryString(deviceStatus
					.substring(2, 4));
			char[] m = motor.toCharArray();
			map.put("life", m[7] - '0');
			map.put("motor", m[6] - '0');
			map.put("lcoolant", m[5] - '0');
			map.put("orNot", m[4] - '0');
			map.put("protocol", motor.substring(1, 4));
			// acc状态：0为关闭或正常。a[7]acc状态、a[6]左前门、a[5]右前门、a[5]左后门、a[3]右后门、a[2]尾箱、a[1]中控锁、a[0]电瓶电压
			String acc = HexUtils.hexString2binaryString(deviceStatus
					.substring(4, 6));
			char[] a = acc.toCharArray();
			map.put("acc", a[7] - '0');
			map.put("leftFace", a[6] - '0');
			map.put("rightFace", a[5] - '0');
			map.put("leftBack", a[4] - '0');
			map.put("rightBack", a[3] - '0');
			map.put("tailBox", a[2] - '0');
			map.put("lock", a[1] - '0');
			map.put("power", a[0] - '0');
			// 0为正常状态：l[7]GPS模块异常、l[6]超速、l[5]疲劳驾驶、l[4]充电电路、l[3]冷却液温度过高、l[2]保养提醒、l[1]节气门清理、l[0]插拔状态
			String alert = HexUtils.hexString2binaryString(deviceStatus
					.substring(6, 8));
			char[] l = alert.toCharArray();
			map.put("gps", l[7] - '0');
			map.put("overSpeed", l[6] - '0');
			map.put("tired", l[5] - '0');
			map.put("circuit", l[4] - '0');
			map.put("hcoolant", l[3] - '0');
			map.put("keep", l[2] - '0');
			map.put("clean", l[1] - '0');
			map.put("deviceStatus", l[0] - '0');
			// //System.out.println(alert);
			// //System.out.println(deviceStatus);
			// 解析流水号
			int serialNumber = Integer.parseInt(str.substring(84, 86), 16);
			map.put("serialNumber", serialNumber);
			// //System.out.println("流水号："+serialNumber);
		} catch (Exception e) {
			logger.error(e);
		}
		return map;
	}

	/**
	 * 解析GPS混合OBD数据
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, Object> gpsAndobd(String str) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			// 解析设备编号
			String devicenumber = str.substring(0, 12);
			map.put("devicenumber", devicenumber);
			// 解析数据类型，是否为盲区数据00代表盲区数据
			int msgType = Integer.parseInt(str.substring(20, 22), 16);
			map.put("msgType", msgType);
			 //System.out.println("数据类型："+str.substring(20, 22));

			// 解析行程ID
			int tripId = Integer.parseInt(str.substring(22, 26), 16);
			map.put("tripId", tripId);
			 //System.out.println("行程ID："+str.substring(22, 26));

			// 解析日期时间

			// 解析日期信息
			String startdate = "20" + str.substring(30, 32) + "-"
					+ str.substring(28, 30) + "-" + str.substring(26, 28);
			// 解析时间信息
			Integer hour = Integer.parseInt(str.substring(32, 34)) + 8;
			String hourStr = "";
			String starttime = str.substring(32, 34) + ":"
					+ str.substring(34, 36) + ":" + str.substring(36, 38);
			String dateStr = DateUtil.formatTime(startdate + " " + starttime);
			map.put("date", dateStr);
			 //System.out.println("日期时间：" +dateStr);
			// 纬度
			double dimension = Double.parseDouble(new DecimalFormat("#.0000")
					.format(Double.parseDouble(str.substring(38, 40))
							+ Double.parseDouble(str.substring(40, 46)) / 10000
							/ 60));
			map.put("dimension", dimension);
			//System.out.println("纬度：" + str.substring(38, 46));
			// 经度
			double longitude = Double.parseDouble(new DecimalFormat("#.0000")
					.format(Double.parseDouble(str.substring(46, 49))
							+ Double.parseDouble(str.substring(49, 55)) / 10000
							/ 60));
			map.put("longitude", longitude);
			//System.out.println("经度度：" + str.substring(46, 55));
			// 位指示
			String msg = str.substring(55, 56);
			String hexMsg = HexUtils.hexString2binaryString("0" + msg);
			char[] c = hexMsg.toCharArray();
			map.put("gpsStatus", c[7] - '0');
			map.put("southOrNorth", c[6] - '0');
			map.put("eastOrWest", c[5] - '0');
			map.put("gpsType", c[4] - '0');
			// c[7]=1表示定位，c[6]=1表示N，即北纬。c[5]=1表示E，即东经。c[4]为0则表示GPS定位，为1则表示基站定位
			 //System.out.println("定位数据："+msg);

			// 解析速度信息
			int speed = Integer.parseInt(str.substring(56, 58), 16);
			map.put("speed", speed);
			 //System.out.println("速度："+str.substring(56, 58));

			// 解析方向信息
			int direction = Integer.parseInt(str.substring(58, 60), 16) << 1;
			map.put("direction", direction);
			//System.out.println("方向："+str.substring(58, 60));

			// 解析GPS卫星个数
			int gpsNum = Integer.parseInt(str.substring(60, 62), 16);
			map.put("gpsNum", gpsNum);
			 //System.out.println("GPS卫星个数："+str.substring(60, 62));

			// 解析GPS信号质量
			int gpsPower = Integer.parseInt(str.substring(62, 64), 16);
			map.put("gpsPower", gpsPower);
			 //System.out.println("GPS信号质量："+str.substring(62, 64));

			// 解析里程str.substring(64,72)此处示例数据有误
			int mail = Integer.parseInt(str.substring(64, 72), 16);
			map.put("mail", mail);
			 //System.out.println("里程："+str.substring(64, 72));

			// 解析设备状态
			String deviceStatus = str.substring(72, 80);
			//System.out.println("设备状态："+deviceStatus);
			// 发送机状态:0为正常，包括续航m[7]、发动机故障m[6]、冷却液温度过低m[5]是否支持该车m[4]，读取汽车协议m[3]-m[1]
			String motor = HexUtils.hexString2binaryString(deviceStatus
					.substring(2, 4));
			char[] m = motor.toCharArray();
			map.put("life", m[7] - '0');
			map.put("motor", m[6] - '0');
			map.put("lcoolant", m[5] - '0');
			map.put("orNot", m[4] - '0');
			map.put("protocol", motor.substring(1, 4));
			// acc状态：0为关闭或正常。a[7]acc状态、a[6]左前门、a[5]右前门、a[5]左后门、a[3]右后门、a[2]尾箱、a[1]中控锁、a[0]电瓶电压
			String acc = HexUtils.hexString2binaryString(deviceStatus
					.substring(4, 6));
			char[] a = acc.toCharArray();
			map.put("acc", a[7] - '0');
			map.put("leftFace", a[6] - '0');
			map.put("rightFace", a[5] - '0');
			map.put("leftBack", a[4] - '0');
			map.put("rightBack", a[3] - '0');
			map.put("tailBox", a[2] - '0');
			map.put("lock", a[1] - '0');
			map.put("power", a[0] - '0');
			// 0为正常状态：l[7]GPS模块异常、l[6]超速、l[5]疲劳驾驶、l[4]充电电路、l[3]冷却液温度过高、l[2]保养提醒、l[1]节气门清理、l[0]插拔状态
			String alert = HexUtils.hexString2binaryString(deviceStatus
					.substring(6, 8));
			char[] l = alert.toCharArray();
			map.put("gps", l[7] - '0');
			map.put("overSpeed", l[6] - '0');
			map.put("tired", l[5] - '0');
			map.put("circuit", l[4] - '0');
			map.put("hcoolant", l[3] - '0');
			map.put("keep", l[2] - '0');
			map.put("clean", l[1] - '0');
			map.put("deviceStatus", l[0] - '0');
			 ////System.out.println("gps模块状态："+(l[7] - '0'));
			// //System.out.println(deviceStatus);

			// 解析负荷计算值
			Double loadvalue = Double
					.parseDouble((new DecimalFormat("#.0000")
							.format((double) Integer.parseInt(
									str.substring(80, 82), 16) * 100 / 255)));
			map.put("loadvalue", loadvalue);
			 //System.out.println("负荷计算值："+str.substring(80, 82));

			// 解析冷却液温度
			int coolant = Integer.parseInt(str.substring(82, 84), 16) - 40;
			map.put("coolant", coolant);
			 //System.out.println("冷却液温度："+str.substring(82, 84));

			// 解析发动机转速
			// (Integer.parseInt(str.substring(84,86),16)*256+Integer.parseInt(str.substring(86,88),16))>>2;
			double rmp = Double.parseDouble((new DecimalFormat("#.0000")
					.format(((double) (Integer.parseInt(str.substring(84, 86),
							16) * 256 + Integer.parseInt(str.substring(86, 88),
							16)) / 4))));
			map.put("rmp", rmp);
			 //System.out.println("发动机转速："+str.substring(84, 88));

			// 解析obd车速
			int obdspeed = Integer.parseInt(str.substring(88, 90), 16);
			map.put("obdspeed", obdspeed);
			 //System.out.println("obd车速："+str.substring(88, 90));

			// 解析点火提前角
			int saangle = Integer.parseInt(str.substring(90, 92), 16) - 64;
			map.put("saangle", saangle);
			 //System.out.println("点火提前角："+str.substring(90, 92));

			// 解析进气歧管绝对压力
			 if("FF".equals(str.substring(92, 94))){
				 //System.out.println("暂不支持进气歧管绝对压力该项数据");
				 map.put("mapressure", 0);
			 }else{
				 int mapressure = Integer.parseInt(str.substring(92, 94), 16);
					map.put("mapressure", mapressure);
					 //System.out.println("进气歧管绝对压力:"+str.substring(92, 94));

			 }
			
			// 解析控制模块电压
			double cmvoltage = (double) (Integer.parseInt(
					str.substring(94, 96), 16)) / 10;
			map.put("cmvoltage", cmvoltage);
			 //System.out.println("控制模块电压："+str.substring(94, 96));

			// 解析进气温度
			int inlettemp = Integer.parseInt(str.substring(96, 98), 16) - 40;
			map.put("inlettemp", inlettemp);
			 //System.out.println("进气温度："+str.substring(96, 98));

			// 解析空气流量
			// ((Integer.parseInt(str.substring(98,100),16)*256+Integer.parseInt(str.substring(100,102),16))/100)
			double airflow = Double.parseDouble(new DecimalFormat("#.0000")
					.format((((double) (Integer.parseInt(
							str.substring(98, 100), 16) * 256 + Integer
							.parseInt(str.substring(100, 102), 16)) / 100))));
			map.put("airflow", airflow);
			 //System.out.println("空气流量："+str.substring(98, 102));

			// 解析节气门相对位置
			 if("FF".equals(str.substring(102, 104))){
				 map.put("trposition", (double)0);
				 //System.out.println("暂不支持节气门相对位置该项数据");
			 }else{
				 double trposition = Double.parseDouble(new DecimalFormat("#.0000")
					.format(((double) Integer.parseInt(str.substring(102, 104),
							16) * 100) / 255));
			map.put("trposition", trposition);
			 //System.out.println("节气门相对位置："+str.substring(102, 104));
			 }
			// 解析长期燃油修正
			double fuelcorrection = Double.parseDouble(new DecimalFormat(
					"#.0000").format((double) ((Integer.parseInt(
					str.substring(104, 106), 16) - 128) * 100) / 128));
			map.put("fuelcorrection", fuelcorrection);
			 //System.out.println("长期燃油修正："+str.substring(104, 106));

			// 空燃比系数
			// (Integer.parseInt(str.substring(106,108),16)*256+Integer.parseInt(str.substring(108,110),16))
			double fuelratio = Double.parseDouble(new DecimalFormat("#.0000")
					.format((double) (Integer.parseInt(str.substring(106, 108),
							16) * 256 + Integer.parseInt(
							str.substring(108, 110), 16)) * 0.0000305));
			map.put("fuelratio", fuelratio);
			 //System.out.println("空燃比系数："+str.substring(106, 110));

			// 节气门绝对位置
			 if("FF".equals(str.substring(110, 112))){
				 //System.out.println("暂不支持节气门绝对位置该项数据");
				 map.put("atposition", (double)0);
			 }else{
				 double atposition = Double.parseDouble(new DecimalFormat("#.0000")
					.format((double) (Integer.parseInt(str.substring(110, 112),
							16) * 100) / 255));
				 map.put("atposition", atposition);
				 //System.out.println("节气门绝对位置："+str.substring(110, 112));
			 }
			

			// 解析燃油压力
			 if("FF".equals(str.substring(112, 114))){
				 //System.out.println("暂不支持燃油压力该项数据");
				 map.put("fuelpressure", 0);
			 }else{
				 int fuelpressure = Integer.parseInt(str.substring(112, 114), 16) * 3;
					map.put("fuelpressure", fuelpressure);
					 //System.out.println("燃油压力："+str.substring(112, 114));
			 }
			

			// 瞬间油耗
			// ((Integer.parseInt(str.substring(114,116),16)*256)+Integer.parseInt(str.substring(116,118),16))*0.1
			double ifwera = (double) ((Integer.parseInt(
					str.substring(114, 116), 16) * 256) + Integer.parseInt(
					str.substring(116, 118), 16)) * 0.1;
			map.put("ifwera", ifwera);
			 //System.out.println("瞬间油耗："+str.substring(114,118));

			// 剩余油量
			 if("FFFF".equals(str.substring(118, 122))){
				 map.put("remainingoil", (double)0);
				 //System.out.println("暂不支持剩余油量该项数据");
			 }else{
				 if ((Integer.parseInt(str.substring(118, 120), 16) & 128) == 128) {
						double premainingoil = Double.parseDouble(new DecimalFormat(
								"#.0000").format((double) ((Integer.parseInt(
								str.substring(118, 120), 16) - 128) * 256 + Integer
								.parseInt(str.substring(120, 122), 16)) * 0.1));
						map.put("premainingoil", premainingoil);
						// 单位为%
						 //System.out.println("剩余油量："+str.substring(118, 122));
					} else {
						double remainingoil = Double.parseDouble(new DecimalFormat(
								"#.0000").format((double) (Integer.parseInt(
								str.substring(118, 120), 16) * 256 + Integer.parseInt(
								str.substring(120, 122), 16)) * 0.1));
						map.put("remainingoil", remainingoil);
						// //System.out.println("剩余油量："+remainingoil+"L");
					}
			 }
			// 基站 122-138
			// 运营商代码
			String operator = str.substring(122, 130);
			map.put("operator", operator);
			 //System.out.println("基站"+str.substring(122, 138));
			// 位置区码
			String lac = str.substring(130, 134);
			map.put("lac", lac);
			// //System.out.println("位置区码："+lac);

			// 未知
			String cell = str.substring(134, 138);
			map.put("cell", cell);
			////System.out.println("未知："+cell);

			// obd油耗
			 int obdoilwear = Integer.parseInt(str.substring(138,146),16);
			 map.put("obdoilwear", obdoilwear);
			 //System.out.println("obd油耗："+str.substring(138,146));

			// 流水号
			int serialnumber = Integer.parseInt(str.substring(146,148), 16);
			map.put("serialnumber", serialnumber);
			 //System.out.println("流水号"+str.substring(146,148));
			// ////System.out.println(str.substring(146,148));
			// ////System.out.println(Integer.parseInt(str.substring(118,120),16)&128);
			/*
			 * //解析流水号 int serialNumber
			 * =Integer.parseInt(str.substring(84,86),16);
			 * map.put("serialNumber", serialNumber);
			 * ////System.out.println("流水号："+serialNumber);
			 */
			 //jedis = RedisDataStore.getconnetion();
			//String json = JSONObject.toJSON(map).toString();
			//将数据存入redis
			/*if((Integer)map.get("msgType")!=0){
				jedis.lpush("GPS", json);
			}*/
			return map;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}finally{
			//释放redis资源
			RedisDataStore.close(jedis);
		}
		
	}
}
