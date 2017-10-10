package com.wbdp.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wbdp.util.DruidDataStore;


/**
 * 查询某一行程的起点位置
 * @author wisedata003
 *
 */
public class StartPositionData {

	private static Logger logger = Logger.getLogger(StartPositionData.class);
	public static Map<String,Object> selectPosition(Map map){
		Map<String,Object> m = new HashMap<String, Object>();
		try {
			PreparedStatement stmt = null;
			Connection con =  null;
			//String sql = "insert into wbdp_running(deviceNumber,tripid,acc,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType) values(?,?,?,?,?,?,?,?,?,?)";//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
			try {
				con =  DruidDataStore.getConnection();
				String sql = "SELECT * FROM wbdp_running WHERE tripid = ? AND deviceNumber = ? AND acc=0 ";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, (((Integer)map.get("tripId"))-1));
				stmt.setString(2, (String)map.get("devicenumber"));
				ResultSet result = stmt.executeQuery();
				 if(result.next()){
					 m.put("devicenumber", result.getString("deviceNumber"));
					 m.put("tripId", (Integer)map.get("tripId"));
					 m.put("runType", result.getInt("acc"));
					 m.put("time", result.getString("time"));
					 m.put("longitude", result.getDouble("longitude"));
					 m.put("dimension", result.getDouble("latitude"));
					 m.put("EastOrWest", result.getInt("EastOrWest"));
					 m.put("SouthOrNorth", result.getInt("SouthOrNorth"));
					 m.put("gpsStatus", result.getInt("gpsStatus"));
					 m.put("gpsType", result.getInt("gpsType"));
					 m.put("type", 1);
				 }
				//stmt.setString(2, map.get("pwd"));
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
				} catch (SQLException e) {
					
					logger.error(e);
				}
			}
			return m;
		} catch (Exception e) {
			logger.error(e);
		}
		return m;
	}
	
}
