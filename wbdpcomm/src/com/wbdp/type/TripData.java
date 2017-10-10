package com.wbdp.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wbdp.util.DruidDataStore;




/**
 * 行程数据保存类，保存到数据库
 * @author 汪赛军
 *
 */
public class TripData {
	private static Logger logger = Logger.getLogger(TripData.class);
	/**
	 * 插入行程数据
	 * @param map
	 * @return
	 */
	public static int insertTrip(Map<String,Object> map){
		int rs = 0;
		PreparedStatement stmt = null;
		Connection con =  DruidDataStore.getConnection();
		try {
			con =  DruidDataStore.getConnection();
			String sql = "insert into wbdp_trip(tripid,starttime,endtime,drivertime,oilwear,mail,hspeed,rmp,temp,rapidSpeed,suddenBrake,overSpeedTime,overSpeedMail,overSpeedOilWear,hSpeedTime,hSpeedMail,hSpeedOilWear,mSpeedTime,mSpeedMail,mSpeedOilWear,lSpeedTime,lSpeedMail,lSpeedOilWear,idleTime,idleTimeOilWear,suddenTurn,deviceNumber) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
			stmt =  con.prepareStatement(sql);
			stmt.setInt(1, (Integer)map.get("tripId"));
			stmt.setString(2, map.get("startTime").toString());
			stmt.setString(3, map.get("endTime").toString());
			stmt.setInt(4, (Integer)map.get("drivertime"));
			stmt.setInt(5, (Integer)map.get("oilwear"));
			stmt.setInt(6, (Integer)map.get("mail"));
			stmt.setInt(7, (Integer)map.get("hspeed"));
			stmt.setInt(8, (Integer)map.get("rmp"));
			stmt.setInt(9, (Integer)map.get("temp"));
			stmt.setInt(10, (Integer)map.get("rapidSpeed"));
			stmt.setInt(11, (Integer)map.get("suddenBrake"));
			stmt.setInt(12, (Integer)map.get("overSpeedTime"));
			stmt.setInt(13, (Integer)map.get("overSpeedMail"));
			stmt.setInt(14, (Integer)map.get("overSpeedOilWear"));
			stmt.setInt(15, (Integer)map.get("hSpeedTime"));
			stmt.setInt(16, (Integer)map.get("hSpeedMail"));
			stmt.setInt(17, (Integer)map.get("hSpeedOilWear"));
			stmt.setInt(18, (Integer)map.get("mSpeedTime"));
			stmt.setInt(19, (Integer)map.get("mSpeedMail"));
			stmt.setInt(20, (Integer)map.get("mSpeedOilWear"));
			stmt.setInt(21, (Integer)map.get("lSpeedTime"));
			stmt.setInt(22, (Integer)map.get("lSpeedMail"));
			stmt.setInt(23, (Integer)map.get("lSpeedOilWear"));
			stmt.setInt(24, (Integer)map.get("idleTime"));
			stmt.setInt(25, (Integer)map.get("idleTimeOilWear"));
			stmt.setInt(26, (Integer)map.get("suddenTurn"));
			stmt.setString(27, map.get("devicenumber").toString());
			 rs=stmt.executeUpdate();
		} catch (SQLException e) {
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
	}
}
