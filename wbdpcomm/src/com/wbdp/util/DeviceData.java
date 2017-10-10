package com.wbdp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * 查询设备对应的UUID
 * @author wisedata003
 * 2017-6-9
 */
public class DeviceData {
	private static Logger logger = Logger.getLogger(DeviceData.class);
	public static Map<String,String> searchAlert(){
		Map<String,String> m = new HashMap<String, String>();
		int rs = 0;
			PreparedStatement stmt = null;
			ResultSet ru = null;
			Connection con=null;
			//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
			try {
				con =  DruidDataStore.getConnection();
				String sql ="SELECT DeviceNumber,DeviceUUID FROM `wbdp_device`"; 
				stmt = con.prepareStatement(sql);
				ResultSet result = stmt.executeQuery();
				while(result.next()){
					m.put(result.getString("DeviceNumber"),result.getString("DeviceUUID") );
				 }
				return m;
			} catch (SQLException e) {
				//System.out.println("新增失败");
				e.printStackTrace();
			}finally{
				try {
					if(stmt!=null){
						stmt.close();
					}
					if(con!=null){
						con.close();
					}
				} catch (Exception e) {
					logger.error(e);
				}
			}
		return null;
	}
	/**
	 * 根据设备号查询UUID
	 * @return
	 */
	public static String searchUUID(String deviceNumber){
		int rs = 0;
			PreparedStatement stmt = null;
			ResultSet ru = null;
			Connection con=null;
			//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
			try {
				con =  DruidDataStore.getConnection();
				String sql ="SELECT DeviceUUID FROM `wbdp_device` where DeviceNumber=?"; 
				stmt = con.prepareStatement(sql);
				stmt.setString(1, deviceNumber);
				ResultSet result = stmt.executeQuery();
				while(result.next()){
					return result.getString("DeviceUUID");
				 }
				//return m;
			} catch (SQLException e) {
				//System.out.println("新增失败");
				e.printStackTrace();
			}finally{
				try {
					if(stmt!=null){
						stmt.close();
					}
					if(con!=null){
						con.close();
					}
				} catch (Exception e) {
					logger.error(e);
				}
			}
		return null;
	}
}
