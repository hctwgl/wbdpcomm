package com.wbdp.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wbdp.util.DruidDataStore;
import com.wbdp.util.PositionUtil;

public class PositionData {
	private static Logger logger = Logger.getLogger(PositionData.class);
	public static int insertPosition(Map map){
		int rs = 0;
		try {
			PreparedStatement stmt = null;
			Connection con =  null;
			//String sql = "insert into wbdp_running(deviceNumber,tripid,acc,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType) values(?,?,?,?,?,?,?,?,?,?)";//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
			try {
				con =  DruidDataStore.getConnection();
				String sql = "insert into wbdp_position(deviceNumber,tripid,acc,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType,position,type) values(?,?,?,?,?,?,?,?,?,?,?,?)";//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
				stmt =  con.prepareStatement(sql);
				stmt.setString(1, map.get("devicenumber").toString());
				stmt.setInt(2, (Integer)map.get("tripId"));
				stmt.setInt(3, (Integer)map.get("runType"));
				stmt.setString(4, map.get("time").toString());
				stmt.setDouble(5, (Double)map.get("longitude"));
				stmt.setDouble(6,(Double)map.get("dimension"));
				stmt.setInt(7, (Integer)map.get("EastOrWest"));
				stmt.setInt(8, (Integer)map.get("SouthOrNorth"));
				stmt.setInt(9, (Integer)map.get("gpsStatus"));
				stmt.setInt(10, (Integer)map.get("gpsType"));
				String position = PositionUtil.getPosition((Double)map.get("longitude"),(Double)map.get("dimension"));
				stmt.setString(11, position);
				stmt.setInt(12, (Integer)map.get("type"));
				 rs=stmt.executeUpdate();
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
			return rs;
		} catch (Exception e) {
			logger.error(e);
		}
		return rs;
	}
}
