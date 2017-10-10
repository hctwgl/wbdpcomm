package com.wbdp.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wbdp.util.DruidDataStore;





/**
 * 点熄火报告数据保存类，保存到数据库
 * @author 汪赛军
 *
 */
public class RunningData {
	private static Logger logger = Logger.getLogger(RunningData.class);
	/**
	 * 插入点熄火报告
	 * @param map
	 * @return
	 */
	public static int insertrun(Map<String,Object> map){
		int rs = 0;
		if(map.size()<=4){
			PreparedStatement stmt = null;
			Connection con =  null;
			//String sql = "insert into wbdp_running(deviceNumber,tripid,acc,time) values(?,?,?,?)";//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
			try {
				con =  DruidDataStore.getConnection();
				String sql = "insert into wbdp_running(deviceNumber,tripid,acc,time) values(?,?,?,?)";
				stmt =  con.prepareStatement(sql);
				stmt.setString(1, map.get("devicenumber").toString());
				stmt.setInt(2, (Integer)map.get("tripId"));
				stmt.setInt(3, (Integer)map.get("runType"));
				stmt.setString(4, map.get("date").toString());
				 rs=stmt.executeUpdate();
				//stmt.setString(2, map.get("pwd"));
			} catch (SQLException e) {
				System.out.println("新增失败");
				logger.error(e);
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
		}else{
			PreparedStatement stmt = null;
			Connection con =  null;
			//String sql = "insert into wbdp_running(deviceNumber,tripid,acc,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType) values(?,?,?,?,?,?,?,?,?,?)";//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
			try {
				con =  DruidDataStore.getConnection();
				String sql = "insert into wbdp_running(deviceNumber,tripid,acc,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType) values(?,?,?,?,?,?,?,?,?,?)";//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
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
		}
		
		return rs;
	}
}
