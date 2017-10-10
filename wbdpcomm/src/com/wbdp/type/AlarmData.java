package com.wbdp.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wbdp.util.DruidDataStore;

public class AlarmData {
	private static Logger logger = Logger.getLogger(AlarmData.class);
	/**
	 * 插入GPS故障信息
	 * @param map
	 * @return
	 */
	public static int insertGps(Map<String,Object> map){
		int rs = 0;
		PreparedStatement stmt = null;
		Connection con =  null;
		//String sql = "insert into wbdp_realtimegps(deviceNumber,datatype,tripid,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType,gpsspeed,direction,gpsnumber,gsmquality,mail,mileage,engine,lcoolant,yesornot,protocol,acc,leftface,rightface,leftback,rightback,tailBox,centerlock,voltage,gpsModular,overspeed,tireddriving,circuit,hcoolant,keep,clean,deviceStatus,serialnumber) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
		try {
			con =  DruidDataStore.getConnection();
			String sql = "insert into wbdp_alarm(deviceNumber,datatype,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType,mileage,engine,lcoolant,yesornot,protocol,acc,leftface,rightface,leftback,rightback,tailBox,centerlock,voltage,gpsModular,overspeed,tireddriving,circuit,hcoolant,keep,clean,deviceStatus) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt =  con.prepareStatement(sql);
			stmt.setString(1, map.get("devicenumber").toString());
			stmt.setString(2, map.get("msgType").toString());
			stmt.setString(3, map.get("date").toString());
			stmt.setString(4, map.get("longitude").toString());
			stmt.setString(5, map.get("dimension").toString());
			stmt.setInt(6, (Integer)map.get("eastOrWest"));
			stmt.setInt(7, (Integer)map.get("southOrNorth"));
			stmt.setInt(8, (Integer)map.get("gpsStatus"));
			stmt.setInt(9, (Integer)map.get("gpsType"));
			stmt.setInt(10, (Integer)map.get("life"));
			stmt.setInt(11, (Integer)map.get("motor"));
			stmt.setInt(12, (Integer)map.get("lcoolant"));
			stmt.setInt(13, (Integer)map.get("orNot"));
			stmt.setString(14, map.get("protocol").toString());
			stmt.setInt(15, (Integer)map.get("acc"));
			stmt.setInt(16, (Integer)map.get("leftFace"));
			stmt.setInt(17, (Integer)map.get("rightFace"));
			stmt.setInt(18, (Integer)map.get("leftBack"));
			stmt.setInt(19, (Integer)map.get("rightBack"));
			stmt.setInt(20, (Integer)map.get("tailBox"));
			stmt.setInt(21, (Integer)map.get("lock"));
			stmt.setInt(22, (Integer)map.get("power"));
			stmt.setInt(23, (Integer)map.get("gps"));
			stmt.setInt(24, (Integer)map.get("overSpeed"));
			stmt.setInt(25, (Integer)map.get("tired"));
			stmt.setInt(26, (Integer)map.get("circuit"));
			stmt.setInt(27, (Integer)map.get("hcoolant"));
			stmt.setInt(28, (Integer)map.get("keep"));
			stmt.setInt(29, (Integer)map.get("clean"));
			stmt.setInt(30, (Integer)map.get("deviceStatus"));
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
	}
	
	/**
	 * 插入GPS混合OBD故障数据
	 * @param map
	 * @return
	 */
	public static int insertalarm(Map<String,Object> map){
		int rs = 0;
		PreparedStatement stmt = null;
		Connection con = null;
		//String sql01 = "insert into wbdp_realtimegps(deviceNumber,datatype,tripid,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType,gpsspeed,direction,gpsnumber,gsmquality,mail,mileage,engine,lcoolant,yesornot,protocol,acc,leftface,rightface,leftback,rightback,tailBox,centerlock,voltage,gpsModular,overspeed,tireddriving,circuit,hcoolant,keep,clean,deviceStatus,loadvalue,coolant,rmp,obdspeed,saangle,mapressure,cmvoltage,inlettemp,airflow,trposition,fuelcorrection,fuelratio,atposition,fuelpressure,ifwera,remainingoil,operator,lac,cell,serialnumber) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
		//String sql02 = "insert into wbdp_realtimegps(deviceNumber,datatype,tripid,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType,gpsspeed,direction,gpsnumber,gsmquality,mail,mileage,engine,lcoolant,yesornot,protocol,acc,leftface,rightface,leftback,rightback,tailBox,centerlock,voltage,gpsModular,overspeed,tireddriving,circuit,hcoolant,keep,clean,deviceStatus,loadvalue,coolant,rmp,obdspeed,saangle,mapressure,cmvoltage,inlettemp,airflow,trposition,fuelcorrection,fuelratio,atposition,fuelpressure,ifwera,premainingoil,operator,lac,cell,serialnumber) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";//+map
		try {
			con = DruidDataStore.getConnection();
			String sql = "insert into wbdp_alarm(deviceNumber,datatype,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType,mileage,engine,lcoolant,yesornot,protocol,acc,leftface,rightface,leftback,rightback,tailBox,centerlock,voltage,gpsModular,overspeed,tireddriving,circuit,hcoolant,keep,clean,deviceStatus) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			//String sql02 = "insert into wbdp_realtimegps(deviceNumber,datatype,tripid,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType,gpsspeed,direction,gpsnumber,gsmquality,mail,mileage,engine,lcoolant,yesornot,protocol,acc,leftface,rightface,leftback,rightback,tailBox,centerlock,voltage,gpsModular,overspeed,tireddriving,circuit,hcoolant,keep,clean,deviceStatus,loadvalue,coolant,rmp,obdspeed,saangle,mapressure,cmvoltage,inlettemp,airflow,trposition,fuelcorrection,fuelratio,atposition,fuelpressure,ifwera,premainingoil,operator,lac,cell,serialnumber) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";//+map
			stmt =  con.prepareStatement(sql);
			stmt.setString(1, map.get("devicenumber").toString());
			stmt.setString(2, map.get("msgType").toString());
			stmt.setString(3, map.get("date").toString());
			stmt.setString(4, map.get("longitude").toString());
			stmt.setString(5, map.get("dimension").toString());
			stmt.setInt(6, (Integer)map.get("eastOrWest"));
			stmt.setInt(7, (Integer)map.get("southOrNorth"));
			stmt.setInt(8, (Integer)map.get("gpsStatus"));
			stmt.setInt(9, (Integer)map.get("gpsType"));
			stmt.setInt(10, (Integer)map.get("life"));
			stmt.setInt(11, (Integer)map.get("motor"));
			stmt.setInt(12, (Integer)map.get("lcoolant"));
			stmt.setInt(13, (Integer)map.get("orNot"));
			stmt.setString(14, map.get("protocol").toString());
			stmt.setInt(15, (Integer)map.get("acc"));
			stmt.setInt(16, (Integer)map.get("leftFace"));
			stmt.setInt(17, (Integer)map.get("rightFace"));
			stmt.setInt(18, (Integer)map.get("leftBack"));
			stmt.setInt(19, (Integer)map.get("rightBack"));
			stmt.setInt(20, (Integer)map.get("tailBox"));
			stmt.setInt(21, (Integer)map.get("lock"));
			stmt.setInt(22, (Integer)map.get("power"));
			stmt.setInt(23, (Integer)map.get("gps"));
			stmt.setInt(24, (Integer)map.get("overSpeed"));
			stmt.setInt(25, (Integer)map.get("tired"));
			stmt.setInt(26, (Integer)map.get("circuit"));
			stmt.setInt(27, (Integer)map.get("hcoolant"));
			stmt.setInt(28, (Integer)map.get("keep"));
			stmt.setInt(29, (Integer)map.get("clean"));
			stmt.setInt(30, (Integer)map.get("deviceStatus"));
			 rs=stmt.executeUpdate();
			//stmt.setString(2, map.get("pwd"));
		} catch (Exception e) {
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
		
		return rs;
	}
}
