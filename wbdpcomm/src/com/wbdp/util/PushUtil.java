package com.wbdp.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import com.wbdp.type.DevicebindingData;
import com.wbdp.type.PushData;

public class PushUtil {
	/**
	 * 推送故障信息
	 * @throws ParseException 
	 */
	public static void pushAlarm(Map<String,Object> map) {
	     try {
	    	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	 		Map<String,Object> m = new HashMap<String, Object>();
	 		//根据经纬度获取详细位置
	 		String position = PositionUtil.getPosition((Double)map.get("longitude"), (Double)map.get("dimension"));
	 		m.put("place", position);
	 		//时间
	 		String date = format.format(format.parse((String)map.get("date")));
	 		m.put("submitDate", date);
	 		//车牌号微信号
	 		Map<String,String> mp = DevicebindingData.selectWX(map);
	 		String plateNumber = mp.get("PlateNumber");
	 		m.put("plateNumber", plateNumber);
	 		//微信号
	 		String ownerWX = (String)mp.get("OwnerWX");
	 		m.put("ownerwx", ownerWX);
	 		//故障原因
	 		String reason = (String)map.get("reason");
	 			m.put("reason", reason);
	 		//故障类型
	 		int pushType = (Integer)map.get("pushType");
	 		m.put("pushType", pushType);
	 		//访问路径
	 		String url =(String)map.get("url");
	 		//参数
	 		String parm =(String)map.get("parm");
	 		//插入推送数据
	 		PushData.insertPush(m);
	 		JSONObject json = new JSONObject();
	 		json.put("ownerwx",ownerWX);
	 		json.put("platenumber", plateNumber);
	 		json.put("submitDate", date);
	 		json.put("reason", reason);
	 		//String json = JSON.toJSONString(m);
	 		HttpTest.pushPost(url, json, parm);
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
		
	}
	/**
	 * 推送设备拔出故障到客户经理
	 * @param map
	 */
	public static void pushDeviceStatus(Map<String,Object> map) {
	     try {
	    	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	 		Map<String,Object> m = new HashMap<String, Object>();
	 		//根据经纬度获取详细位置
	 		String position = PositionUtil.getPosition((Double)map.get("longitude"), (Double)map.get("dimension"));
	 		m.put("place", position);
	 		//时间
	 		String date = format.format(format.parse((String)map.get("date")));
	 		m.put("submitDate", date);
	 		//车牌号微信号
	 		Map<String,String> mp = DevicebindingData.selectWX(map);
	 		String plateNumber = mp.get("PlateNumber");
	 		m.put("plateNumber", plateNumber);
	 		//微信号,此处的微信号通过集合参数传入，为客户经理的微信号
	 		String ownerWX = (String)map.get("OwnerWX");
	 		m.put("ownerwx", ownerWX);
	 		//故障原因
	 		String reason = (String)map.get("reason");
	 			m.put("reason", reason);
	 		//故障类型
	 		int pushType = (Integer)map.get("pushType");
	 		m.put("pushType", pushType);
	 		//访问路径
	 		String url =(String)map.get("url");
	 		//参数
	 		String parm =(String)map.get("parm");
	 		//插入推送数据
	 		PushData.insertPush(m);
	 		JSONObject json = new JSONObject();
	 		json.put("ownerwx",ownerWX);
	 		json.put("platenumber", plateNumber);
	 		json.put("submitDate", date);
	 		json.put("reason", reason);
	 		//String json = JSON.toJSONString(m);
	 		HttpTest.pushPost(url, json, parm);
	 		
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
		
	}
	/**
	 * 推送碰撞信息
	 * @throws ParseException 
	 */
	public static void pushAlert(Map<String,Object> map) {
	     try {
	    	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	 		Map<String,Object> m = new HashMap<String, Object>();
	 		//根据经纬度获取详细位置
	 		String position = PositionUtil.getPosition((Double)map.get("longitude"), (Double)map.get("dimension"));
	 		m.put("place", position);
	 		//时间
	 		String date = format.format(format.parse((String)map.get("time")));
	 		m.put("submitDate", date);
	 		//车牌号微信号
	 		Map<String,String> mp = DevicebindingData.selectWX(map);
	 		String plateNumber = mp.get("PlateNumber");
	 		//微信号
	 		String ownerWX = (String)mp.get("OwnerWX");
	 		m.put("ownerwx", ownerWX);
	 		//故障类型
	 		int pushType = (Integer)map.get("pushType");
	 		m.put("pushType", pushType);
	 		//访问路径
	 		String url =(String)map.get("url");
	 		//参数
	 		String parm =(String)map.get("parm");
	 		m.put("reason", map.get("reason").toString());
	 		m.put("plateNumber", plateNumber);
	 		//插入推送数据
	 		PushData.insertPush(m);
	 		JSONObject json = new JSONObject();
	 		json.put("ownerwx", ownerWX);
	 		json.put("submitDate", date);
	 		json.put("place", position);
	 		//System.out.println(json.toString());
	 		//String json = JSON.toJSONString(m);
	 		HttpTest.pushPost(url, json, parm);
	 		//return json;
	     } catch (Exception e) {
	         e.printStackTrace();
	         //return null;
	     }
		
	}
	/**
	 * 推送故障码信息
	 * @throws ParseException 
	 */
	public static void pushAlertCode(Map<String,Object> map) {
	     try {
	    	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	 		Map<String,Object> m = new HashMap<String, Object>();
	 		//根据经纬度获取详细位置
	 		String position = "暂无地址信息";
	 		m.put("place", position);
	 		//时间
	 		String date = (String)map.get("date");
	 		m.put("submitDate", date);
	 		//车牌号微信号
	 		Map<String,String> mp = DevicebindingData.selectWX(map);
	 		String plateNumber = mp.get("PlateNumber");
	 		m.put("plateNumber", plateNumber);
	 		//微信号
	 		String ownerWX = (String)mp.get("OwnerWX");
	 		m.put("ownerwx", ownerWX);
	 		//故障原因
	 		String reason = (String)map.get("reason");
	 			m.put("reason", reason);
	 		//故障类型
	 		int pushType = (Integer)map.get("pushType");
	 		m.put("pushType", pushType);
	 		//访问路径
	 		String url =(String)map.get("url");
	 		//参数
	 		String parm =(String)map.get("parm");
	 		//插入推送数据
	 		PushData.insertPush(m);
	 		JSONObject json = new JSONObject();
	 		json.put("ownerwx",ownerWX);
	 		json.put("platenumber", plateNumber);
	 		json.put("submitDate", date);
	 		json.put("reason", reason);
	 		//String json = JSON.toJSONString(m);
	 		HttpTest.pushPost(url, json, parm);
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
		
	}
}
