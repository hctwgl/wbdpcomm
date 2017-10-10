package com.wbdp.cy;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import com.wbdp.type.AlarmData;
import com.wbdp.type.Alert;
import com.wbdp.type.AlertCode;
import com.wbdp.type.AlertCodeData;
import com.wbdp.type.AlertData;
import com.wbdp.type.CustomerCRMData;
import com.wbdp.type.DevicebindingData;
import com.wbdp.type.GpsAndObd;
import com.wbdp.type.GpsAndObdData;
import com.wbdp.type.PositionData;
import com.wbdp.type.Running;
import com.wbdp.type.RunningData;
import com.wbdp.type.StartPositionData;
import com.wbdp.type.Trip;
import com.wbdp.type.TripData;
import com.wbdp.type.VehiceData;
import com.wbdp.type.VehiceownerData;
import com.wbdp.util.Constants;
import com.wbdp.util.Converts;
import com.wbdp.util.DeviceChannelMap;
import com.wbdp.util.DeviceData;
import com.wbdp.util.PushCheckData;
import com.wbdp.util.PushUtil;
import com.wbdp.util.RedisDataStore;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * 车云网通信协议消息类型判断类，判断不同消息并调用不同的解析方法
 * 
 * @author 汪赛军
 * 做出了一点点修改
 */
public class CyData {
	private static Logger logger = Logger.getLogger(CyData.class);
	private String str;
	private ChannelHandlerContext ctx;
	private static Jedis jedis = RedisDataStore.getconnetion();

	public CyData(String str, ChannelHandlerContext ctx) throws Exception {
		this.str = str;
		this.ctx = ctx;
		// 将设备ID与通道保存到redis缓存中
		// System.out.println("查询到的UUID："+jedis.get("531608010049"));
		// System.out.println("ip地址"+ctx.channel().toString());
		// jedis.set(findDeviceid(str),
		// ctx.channel().remoteAddress().toString());
		// 回收redis资源
		// RedisDataStore.close(jedis);
		// 将通道存入全局map中，对应的key为设备号+通道常量名称。
		DeviceChannelMap.saveChannel(findDeviceid(str) + Constants.CHANNEL,
				ctx.channel());
		// System.out.println("存入缓存的通道信息："+jedis.get(findDeviceid(str)));
		// MemcachedJava.saveMemcached(findDeviceid(str), ctx);
		checkType(str, ctx);
	}

	/**
	 * 解析终端消息类型，调用不同方法进行数据解析。调用碰撞报警、故障提醒推送接口。
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String checkType(String str, ChannelHandlerContext ctx)
			throws Exception {
		try {
		// 去包头包尾，转义，异或校验
		String del = CyDataUtil.delStr(str);
		String escape = CyDataUtil.escapeStr(del);
		String[] split = CyDataUtil.splitStr(escape);
		int i = CyDataUtil.xorStr(split)
				^ Integer.parseInt(escape.substring(escape.length() - 2), 16);
		System.out.println("校验和：" + i);
		// 判断命令类型
		String orderType = escape.substring(12, 14);
		// System.out.println("命令类型："+Integer.parseInt(orderType));
		// 终端回复类型
		String answerType = escape.substring(14, 16);
		System.out.println("终端回复类型：" + orderType + answerType);
		System.out.println("转义后的数据：" + escape);
		
		// 设置IP指令
		String devicenumber = "";
		String order = "";
		if(i!=0){
			if ("0".equals(str.substring(2, 3))) {
				// 获取命令类型280531608010667100111411411411429
				 order = str.substring(15, 19);
				devicenumber = str.substring(3, 15);
				System.out.println(devicenumber);
			} else {
				// 获取命令类型280531608010667100111411411411429
				 order = str.substring(14, 18);
				devicenumber = str.substring(2, 14);
			}
		}
		if ("1001".equals(order)) {
			String msg = MessageBack.orderIP(str);
			System.out.println("发送的设置IP指令："+msg);
			// System.out.println("拼接后的命令："+msg);
			// 将消息转换为十六进制数据发送
			ByteBuf resp = Unpooled.copiedBuffer(Converts.str2Bcd(msg));
			Channel channel = DeviceChannelMap.getChannel(devicenumber
					+ Constants.CHANNEL);
			if (channel != null) {
				if (channel.isActive()) {
					channel.writeAndFlush(resp);
					System.out.println("已发送设置IP指令");
					//ByteBuf res = Unpooled.copiedBuffer("1".getBytes());
					//ctx.channel().writeAndFlush(res);
				} else {
					System.out.println("该设备未连接上");
					ByteBuf res = Unpooled.copiedBuffer("0".getBytes());
					ctx.channel().writeAndFlush(res);
				}
			} else {
				ByteBuf res = Unpooled.copiedBuffer("0".getBytes());
				ctx.channel().writeAndFlush(res);
			}
		}
		if (i == 0) {
			switch (Integer.parseInt(orderType)) {
			// 通用命令
			case 0:
				switch (Integer.parseInt(answerType, 16)) {
				// 终端上传警情信息
				case 136:
					Map<String, Object> map = Alert.alarm(escape);
					int alertLength = Integer.parseInt(map.get("length").toString());
					//判断警情信息是否有GPS数据，如没有则不对给数据进行处理
					if(alertLength!=9){
						String msg = MessageBack.alarmBack(str);
						// 将消息转换为十六进制数据发送
						ByteBuf resp = Unpooled.copiedBuffer(Converts.str2Bcd(msg));
						Channel channel = DeviceChannelMap
								.getChannel(findDeviceid(str) + Constants.CHANNEL);
						channel.writeAndFlush(resp);
						// ctx.writeAndFlush(resp);
						// 将设备号替换为UUID
						map.put("devicenumber",
								jedis.get(map.get("devicenumber").toString()));
						// 查看是否有重复数据后插入警报数据
						String alert = AlertData.searchAlert(map);
						if (alert == null || alert == "") {
							AlertData.insertAlert(map);
						}
						// 推送警情
						if ((Integer) map.get("alarmType") == 16) {
							// 查询是否存在重复推送数据
							// 车牌号微信号
							Map<String, String> mp = DevicebindingData
									.selectWX(map);
							String plateNumber = mp.get("PlateNumber");
							map.put("PlateNumber", plateNumber);
							map.put("alert", "车辆震动警报");
							String pushContent = PushCheckData.searchAlert(map);
							System.out.println("查询的推送数据：" + pushContent + "车牌："
									+ map.get("PlateNumber") + "设备号："
									+ map.get("alert"));
							// 当不存在重复推送数据时，执行推送与插入操作
							if (pushContent == null || pushContent == "") {
								// 根据故障推送不同故障信息
								map.put("pushType", 2);
								map.put("reason", "车辆震动警报");
								map.put("url",
										"http://www.wisedp.com/WbdpSSM/wc/push/aclarm");
								map.put("parm", "aclarm");
								PushUtil.pushAlert(map);
							}
						}
						// 冷却液温度过高报警
						if ((Integer) map.get("alarmType") == 6) {
							// 查询是否存在重复推送数据
							// 车牌号微信号
							Map<String, String> mp = DevicebindingData
									.selectWX(map);
							String plateNumber = mp.get("PlateNumber");
							map.put("PlateNumber", plateNumber);
							map.put("alert", "冷却液温度过高");
							String pushContent = PushCheckData.searchAlert(map);
							System.out.println("查询的推送数据：" + pushContent + "车牌："
									+ map.get("PlateNumber") + "设备号："
									+ map.get("alert"));
							// 当不存在重复推送数据时，执行推送与插入操作
							if (pushContent == null || pushContent == "") {
								// 根据故障推送不同故障信息
								map.put("pushType", 2);
								map.put("reason", "冷却液温度过高");
								map.put("url",
										"http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
								map.put("parm", "aclarm");
								//PushUtil.pushAlarm(map);
							}
						}
						// 设备拔出报警
						if ((Integer) map.get("alarmType") == 10) {
							// 查询是否存在重复推送数据
							// 车牌号微信号
							Map<String, String> mp = DevicebindingData
									.selectWX(map);
							String plateNumber = mp.get("PlateNumber");
							map.put("PlateNumber", plateNumber);
							map.put("alert", "设备拔出");
							String pushContent = PushCheckData.searchAlert(map);
							System.out.println("查询的推送数据：" + pushContent + "车牌："
									+ map.get("PlateNumber") + "设备号："
									+ map.get("alert"));
							// 当不存在重复推送数据时，执行推送与插入操作
							if (pushContent == null || pushContent == "") {
								// 根据故障推送不同故障信息
								map.put("reason", "设备拔出");
								map.put("pushType", 2);
								map.put("url",
										"http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
								map.put("parm", "faultremind");
								// 设备拔出推送车主
								//PushUtil.pushAlarm(map);
								// 查询该设备对应车牌
								// String plateNumber =
								// DevicebindingData.selectWX(map).get("PlateNumber");
								// 查询车牌对应的车辆ID
								Long id = VehiceData.selectVehice(plateNumber).get(
										"vehiceID");
								// 根据车辆ID查询客户经理ID
								if (CustomerCRMData.selectCustomer(id) != null
										&& plateNumber != null) {
									Long managerid = CustomerCRMData
											.selectCustomer(id).get("manager");
									if (managerid != null) {
										// 根据客户经理ID查询微信号
										String wx = VehiceownerData.selectOwnerWX(
												managerid).get("wx");
										map.put("OwnerWX", wx);
										// 设备拔出推送客户经理
										//PushUtil.pushDeviceStatus(map);
									}
								}
							}
						}
					}
					break;
				case 20:

					break;
				default:
					break;
				}
				break;
			// GSM命令
			case 10:
				switch (Integer.parseInt(answerType, 16)) {
				// 设置设备的IP
				case 1:
					System.out.println(str);
					break;

				default:
					break;
				}
				break;
			// GPS命令
			case 20:
				switch (Integer.parseInt(answerType, 16)) {
				case 130:
					Map<String, Object> map = GpsAndObd.gpsMsg(escape);
					// 通过右弹出redis中的GPS数据
					List<String> list = jedis.brpop(0, "GPS");
					if (list != null) {
						for (String s : list) {
							System.out.println(s);
						}
					}
					// 将设备号替换为UUID
					map.put("devicenumber",
							jedis.get(map.get("devicenumber").toString()));
					GpsAndObdData.insertGps(map);
					// 插入GPS数据中的故障信息
					if (((Integer) map.get("life") + (Integer) map.get("motor")
							+ (Integer) map.get("lcoolant")
							+ (Integer) map.get("gps") + (Integer) map
								.get("hcoolant")) != 0) {
						AlarmData.insertGps(map);
					}
					/*
					 * if((Integer)map.get("life")!=0){ map.put("reason",
					 * "续航里程不足50KM"); map.put("pushType", 2); map.put("url",
					 * "http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
					 * map.put("parm", "faultremind");
					 * //PushUtil.pushAlarm(map); }
					 * if((Integer)map.get("motor")!=0){ map.put("reason",
					 * "发动机故障"); map.put("pushType", 2); map.put("url",
					 * "http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
					 * map.put("parm", "faultremind");
					 * //PushUtil.pushAlarm(map); }
					 * if((Integer)map.get("lcoolant")!=0){ map.put("reason",
					 * "冷却液温度过低"); map.put("pushType", 2); map.put("url",
					 * "http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
					 * map.put("parm", "faultremind");
					 * //PushUtil.pushAlarm(map); }
					 * if((Integer)map.get("gps")!=0){ map.put("reason",
					 * "GPS模块故障"); map.put("pushType", 2); map.put("url",
					 * "http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
					 * map.put("parm", "faultremind");
					 * //PushUtil.pushAlarm(map); }
					 * if((Integer)map.get("hcoolant")!=0){ map.put("reason",
					 * "冷却液温度过高"); map.put("pushType", 2); map.put("url",
					 * "http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
					 * map.put("parm", "faultremind");
					 * //PushUtil.pushAlarm(map); }
					 */
					break;
				case 132:
					Map<String, Object> mapobd = GpsAndObd.gpsAndobd(escape);
					if (mapobd != null) {
						// 通过右弹出redis中的GPS数据
						/*
						 * List<String> gps = jedis.brpop(0, "GPS");
						 * if(gps!=null){ JSONObject obj =
						 * JSONObject.parseObject(gps.get(1));
						 * GpsAndObdData.insertGpsAndObd(obj); }
						 */
						// 将设备号替换为UUID
						String uuid = jedis.get(mapobd.get("devicenumber").toString());
						if(uuid==null||uuid==""){
							mapobd.put("devicenumber", DeviceData.searchUUID(mapobd.get("devicenumber").toString()));
						}else{
							mapobd.put("devicenumber", jedis.get(mapobd.get(
									"devicenumber").toString()));
						}
						// 判断是否是盲区数据
						if ((Integer) mapobd.get("msgType") != 0) {
							// 将GPS数据存入数据库
							GpsAndObdData.insertGpsAndObd(mapobd);
						}
						if (((Integer) mapobd.get("life")
								+ (Integer) mapobd.get("motor")
								+ (Integer) mapobd.get("lcoolant")
								+ (Integer) mapobd.get("gps")
								+ (Integer) mapobd.get("hcoolant") + (Integer) mapobd
									.get("deviceStatus")) != 0) {
							// 插入GPS混合obd中的故障信息
							AlarmData.insertalarm(mapobd);
						}
						// 根据故障推送不同故障信息
						/*
						 * if((Integer)mapobd.get("life")!=0){
						 * mapobd.put("reason", "续航里程不足50KM");
						 * mapobd.put("pushType", 2); mapobd.put("url",
						 * "http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
						 * mapobd.put("parm", "faultremind");
						 * //PushUtil.pushAlarm(mapobd); }
						 * if((Integer)mapobd.get("motor")!=0){
						 * mapobd.put("reason", "发动机故障"); mapobd.put("pushType",
						 * 2); mapobd.put("url",
						 * "http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
						 * mapobd.put("parm", "faultremind");
						 * //PushUtil.pushAlarm(mapobd); }
						 * if((Integer)mapobd.get("lcoolant")!=0){
						 * mapobd.put("reason", "冷却液温度过低");
						 * mapobd.put("pushType", 2); mapobd.put("url",
						 * "http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
						 * mapobd.put("parm", "faultremind");
						 * //PushUtil.pushAlarm(mapobd); }
						 * if((Integer)mapobd.get("gps")!=0){
						 * mapobd.put("reason", "GPS模块故障");
						 * mapobd.put("pushType", 2); mapobd.put("url",
						 * "http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
						 * mapobd.put("parm", "faultremind");
						 * //PushUtil.pushAlarm(mapobd); }
						 * if((Integer)mapobd.get("hcoolant")!=0){
						 * mapobd.put("reason", "冷却液温度过高");
						 * mapobd.put("pushType", 2); mapobd.put("url",
						 * "http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
						 * mapobd.put("parm", "faultremind");
						 * //PushUtil.pushAlarm(mapobd); }
						 * if((Integer)mapobd.get("deviceStatus")!=0){
						 * mapobd.put("reason", "设备拔出"); mapobd.put("pushType",
						 * 2); mapobd.put("url",
						 * "http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
						 * mapobd.put("parm", "faultremind"); //查询是否存在同样推送数据
						 * //车牌号微信号 Map<String,String> mp =
						 * DevicebindingData.selectWX(mapobd); String
						 * plateNumber = mp.get("PlateNumber");
						 * mapobd.put("PlateNumber", plateNumber); String
						 * pushContent = PushCheckData.searchAlert(mapobd);
						 * //设备拔出推送车主 PushUtil.pushAlarm(mapobd); //查询该设备对应车牌
						 * String p =
						 * DevicebindingData.selectWX(mapobd).get("PlateNumber"
						 * ); //查询车牌对应的车辆ID Long id =
						 * VehiceData.selectVehice(p).get("vehiceID");
						 * //根据车辆ID查询客户经理ID
						 * if(CustomerCRMData.selectCustomer(id)!=null){ Long
						 * managerid =
						 * CustomerCRMData.selectCustomer(id).get("manager");
						 * if(managerid!=null){ //根据客户经理ID查询微信号 String wx =
						 * VehiceownerData.selectOwnerWX(managerid).get("wx");
						 * mapobd.put("OwnerWX", wx); //设备拔出推送客户经理
						 * //PushUtil.pushDeviceStatus(mapobd); } } }
						 */
					}

					break;
				default:
					break;
				}
				break;
			// OBD命令
			case 30:
				switch (Integer.parseInt(answerType, 16)) {
				// 终端上传故障码
				case 135:
					// 通过消息长度判断故障码个数
					int length = Integer.parseInt(escape.substring(16, 20), 16);
					if (length < 18) {
						Map<String, Object> codemap = AlertCode
								.alertCode(escape);
						// 将设备号替换为UUID
						codemap.put("devicenumber", jedis.get(codemap.get(
								"devicenumber").toString()));
						String code = AlertCodeData.searchAlert(codemap);
						// System.out.println(code);
						codemap.put("alert", code);
						// 查询是否存在重复推送数据
						// 车牌号微信号
						Map<String, String> mp = DevicebindingData
								.selectWX(codemap);
						String plateNumber = mp.get("PlateNumber");
						// System.out.println(plateNumber);
						codemap.put("PlateNumber", plateNumber);
						String pushContent = PushCheckData.searchAlert(codemap);
						// System.out.println("查询的推送数据："+pushContent+" 车牌："+codemap.get("PlateNumber")+" 设备号："+codemap.get("alert"));
						// 当不存在重复推送数据时，执行推送与插入操作
						if (pushContent == null || pushContent == "") {
							// 插入故障码数据
							AlertCodeData.insertAlert(codemap);
							// 清除故障码
							String orderCode = MessageBack.clearCode(escape);
							// 将消息转换为十六进制数据发送
							ByteBuf resp = Unpooled.copiedBuffer(Converts
									.str2Bcd(orderCode));
							Channel channel = DeviceChannelMap
									.getChannel(findDeviceid(str)
											+ Constants.CHANNEL);
							channel.writeAndFlush(resp);
							// 根据故障推送不同故障信息
							codemap.put("reason", code);
							codemap.put("pushType", 2);
							codemap.put("url",
									"http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
							codemap.put("parm", "faultremind");
							PushUtil.pushAlertCode(codemap);
						}
					} else if (length == 0) {

					} else {
						Map<String, Object> codemap = AlertCode
								.alertCode(escape);
						// 将设备号替换为UUID
						String uuid = jedis.get(codemap.get(
								"devicenumber").toString());
						if(uuid==null||uuid==""){
							codemap.put("devicenumber", DeviceData.searchUUID(codemap.get("devicenumber").toString()));
						}else{
							codemap.put("devicenumber", jedis.get(codemap.get(
									"devicenumber").toString()));
						}
						// 将故障码分别替换查询
						codemap.put("alert", codemap.get("alert1"));
						String code1 = AlertCodeData.searchAlert(codemap);
						//System.out.println("code1:"+codemap.get("alert1"));
						codemap.put("alert", codemap.get("alert2"));
						String code2 = AlertCodeData.searchAlert(codemap);
						//System.out.println("code2:"+codemap.get("alert2"));
						codemap.put("alert", codemap.get("alert3"));
						String code3 = AlertCodeData.searchAlert(codemap);
						//System.out.println("code3:"+codemap.get("alert3"));
						String code = "\n" + code1 + "\n" + code2 + "\n"
								+ code3;
						codemap.put("alert", code);
						// codemap.put("alert", code1);
						// 查询是否存在重复推送数据
						// 车牌号微信号
						Map<String, String> mp = DevicebindingData
								.selectWX(codemap);
						String plateNumber = mp.get("PlateNumber");
						// System.out.println(plateNumber);
						codemap.put("PlateNumber", plateNumber);
						String pushContent = PushCheckData.searchAlert(codemap);
						// System.out.println("查询的推送数据："+pushContent+" 车牌："+codemap.get("PlateNumber")+" 设备号："+codemap.get("alert"));
						// 当不存在重复推送数据时，执行推送与插入操作
						if (pushContent == null || pushContent == "") {
							// 插入故障码数据
							AlertCodeData.insertAlert(codemap);
							// 清除故障码
							String orderCode = MessageBack.clearCode(escape);
							// 将消息转换为十六进制数据发送
							ByteBuf resp = Unpooled.copiedBuffer(Converts
									.str2Bcd(orderCode));
							Channel channel = DeviceChannelMap
									.getChannel(findDeviceid(str)
											+ Constants.CHANNEL);
							channel.writeAndFlush(resp);
							// 根据故障推送不同故障信息
							codemap.put("reason", code);
							codemap.put("pushType", 2);
							codemap.put("url",
									"http://www.wisedp.com/WbdpSSM/wc/push/faultremind");
							codemap.put("parm", "faultremind");
							PushUtil.pushAlertCode(codemap);
						}
					}
					break;
				// 终端上传行程信息
				case 136:
					Map<String, Object> map = Trip.trip(escape);
					// 将设备号替换为UUID
					map.put("devicenumber",
							jedis.get(map.get("devicenumber").toString()));
					TripData.insertTrip(map);
					break;
				// 终端上传点火熄火报告
				case 137:
					Map<String, Object> runmap = Running.running(escape);
					int runType = (Integer) runmap.get("runType");
					// 将设备号替换为UUID
					// 将设备号替换为UUID
					String uuid = jedis.get(runmap.get(
							"devicenumber").toString());
					if(uuid==null||uuid==""){
						runmap.put("devicenumber", DeviceData.searchUUID(runmap.get("devicenumber").toString()));
					}else{
						runmap.put("devicenumber", jedis.get(runmap.get(
								"devicenumber").toString()));
					}
					RunningData.insertrun(runmap);
					if (runType == 0) {
						// 查询该次熄火报告的的起点位置
						Map<String, Object> smap = StartPositionData
								.selectPosition(runmap);
						PositionData.insertPosition(smap);
						runmap.put("type", 0);
						PositionData.insertPosition(runmap);
					}
					break;
				default:
					break;
				}
				break;
			// 升级命令
			case 40:

				break;
			// 外设命令
			case 50:
				break;
			default:
				break;
			}
		}
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * 解析设备ID
	 * 
	 * @param str
	 * @return
	 */
	public static String findDeviceid(String str) {
		try {
			// 去包头包尾，转义，异或校验
			String del = CyDataUtil.delStr(str);
			String escape = CyDataUtil.escapeStr(del);
			String[] split = CyDataUtil.splitStr(escape);
			int i = CyDataUtil.xorStr(split)
					^ Integer.parseInt(escape.substring(escape.length() - 2),
							16);
			if (i == 0) {
				return escape.substring(0, 12);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 解析版本号
	 * 
	 * @param str
	 */
	public static String version(String str) {
		String del = CyDataUtil.delStr(str);
		String escape = CyDataUtil.escapeStr(del);
		String[] split = CyDataUtil.splitStr(escape);
		StringBuffer buffer = new StringBuffer(20);
		// 将消息内容与校验码进行异或运算，等于零则校验成功
		int i = CyDataUtil.xorStr(split)
				^ Integer.parseInt(escape.substring(52), 16);
		// System.out.println(i);
		if (i == 0) {
			String version = escape.substring(20, 52);
			String[] verArray = CyDataUtil.splitStr(version);
			int length = verArray.length;
			for (int j = 0; j < length; j++) {
				char c = (char) Integer.parseInt(verArray[j], 16);
				buffer.append(c);
			}
			// System.out.println("设备版本号：" + buffer.toString());
			return buffer.toString();

		}
		return null;
	}
}
