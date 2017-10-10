package com.wbdp.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wbdp.util.DeviceData;
import com.wbdp.util.DruidDataStore;



/**
 * 警情信息数据保存类，用于调用连接池将数据保存至数据库
 * @author 汪赛军
 *
 */
public class AlertData {
	private static Logger logger = Logger.getLogger(AlertData.class);
	//插入警情信息
		public static int insertAlert(Map<String,Object> map){
			int rs = 0;
			if(map.size()<=3){
				PreparedStatement stmt = null;
				ResultSet ru = null;
				Connection con=null;
				//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
				try {
					con =  DruidDataStore.getConnection();
					String sql = "insert into wbdp_alert(deviceNumber,alarmTime,alarmType) values(?,?,?)";
					stmt = con.prepareStatement(sql);
					stmt.setString(1, map.get("devicenumber").toString());
					stmt.setString(2, map.get("timeStamp").toString());
					stmt.setInt(3, (Integer)map.get("alarmType"));
					 rs=stmt.executeUpdate();
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
			}else{
				PreparedStatement stmt = null;
				ResultSet ru = null;
				Connection con = null;;
				//String sql = "insert into wbdp_alert(deviceNumber,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,alarmTime,alarmType,gpsType) values(?,?,?,?,?,?,?,?,?)";//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
				try {
					con =  DruidDataStore.getConnection();
					String sql = "insert into wbdp_alert(deviceNumber,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,alarmTime,alarmType,gpsType) values(?,?,?,?,?,?,?,?,?)";
					stmt = con.prepareStatement(sql);
					stmt.setString(1, map.get("devicenumber").toString());
					stmt.setString(2, map.get("longitude").toString());
					stmt.setString(3, map.get("dimension").toString());
					stmt.setInt(4, (Integer)map.get("EastOrWest"));
					stmt.setInt(5, (Integer)map.get("SouthOrNorth"));
					stmt.setInt(6, (Integer)map.get("gpsStatus"));
					stmt.setString(7, map.get("date").toString());
					stmt.setInt(8, (Integer)map.get("alarmType"));
					stmt.setInt(9, (Integer)map.get("gpsType"));
					 rs=stmt.executeUpdate();
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
			}
			
			return rs;
		}
		/**
		 * 查询是否重复插入警情数据
		 * @return
		 */
		public static String searchAlert(Map<String,Object> map){
			Map<String,String> m = new HashMap<String, String>();
			int rs = 0;
				PreparedStatement stmt = null;
				ResultSet ru = null;
				Connection con=null;
				//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
				try {
					con =  DruidDataStore.getConnection();
					String sql ="SELECT alarmType FROM `wbdp_alert` where TO_DAYS(alarmTime) = TO_DAYS(NOW()) and deviceNumber = ? and alarmType = ? "; 
					stmt = con.prepareStatement(sql);
					stmt.setString(1, map.get("devicenumber").toString());
					stmt.setString(2, map.get("alarmType").toString());
					ResultSet result = stmt.executeQuery();
					while(result.next()){
						return result.getString("alarmType");
					 }
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
