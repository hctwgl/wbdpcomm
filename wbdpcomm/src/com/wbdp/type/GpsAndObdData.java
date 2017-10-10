package com.wbdp.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import sun.net.www.content.text.plain;

import com.alibaba.fastjson.JSONObject;
import com.wbdp.util.DruidDataStore;




/**
 * GPS、OBD数据保存类，用于将数据保存至数据库
 * @author 汪赛军
 *
 */
public class GpsAndObdData {
	private static Logger logger = Logger.getLogger(GpsAndObdData.class);
	/**
	 * 插入GPS位置信息
	 * @param map
	 * @return
	 */
	public static int insertGps(Map<String, Object> map){
		int rs = 0;
		PreparedStatement stmt = null;
		Connection con =  null;
		//String sql = "insert into wbdp_realtimegps(deviceNumber,datatype,tripid,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType,gpsspeed,direction,gpsnumber,gsmquality,mail,mileage,engine,lcoolant,yesornot,protocol,acc,leftface,rightface,leftback,rightback,tailBox,centerlock,voltage,gpsModular,overspeed,tireddriving,circuit,hcoolant,keep,clean,deviceStatus,serialnumber) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
		try {
			con =  DruidDataStore.getConnection();
			String sql = "insert into wbdp_realtimegps(deviceNumber,datatype,tripid,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType,gpsspeed,direction,gpsnumber,gsmquality,mail,mileage,engine,lcoolant,yesornot,protocol,acc,leftface,rightface,leftback,rightback,tailBox,centerlock,voltage,gpsModular,overspeed,tireddriving,circuit,hcoolant,keep,clean,deviceStatus,serialnumber) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt =  con.prepareStatement(sql);
			stmt.setString(1, map.get("devicenumber").toString());
			stmt.setInt(2, (Integer)map.get("msgType"));
			stmt.setInt(3, (Integer)map.get("tripId"));
			stmt.setString(4, map.get("date").toString());
			stmt.setString(5, map.get("longitude").toString());
			stmt.setString(6, map.get("dimension").toString());
			stmt.setInt(7, (Integer)map.get("eastOrWest"));
			stmt.setInt(8, (Integer)map.get("southOrNorth"));
			stmt.setInt(9, (Integer)map.get("gpsStatus"));
			stmt.setInt(10, (Integer)map.get("gpsType"));
			stmt.setInt(11, (Integer)map.get("speed"));
			stmt.setInt(12, (Integer)map.get("direction"));
			stmt.setInt(13, (Integer)map.get("gpsNum"));
			stmt.setInt(14, (Integer)map.get("gpsPower"));
			stmt.setInt(15, (Integer)map.get("mail"));
			stmt.setInt(16, (Integer)map.get("life"));
			stmt.setInt(17, (Integer)map.get("motor"));
			stmt.setInt(18, (Integer)map.get("lcoolant"));
			stmt.setInt(19, (Integer)map.get("orNot"));
			stmt.setString(20, map.get("protocol").toString());
			stmt.setInt(21, (Integer)map.get("acc"));
			stmt.setInt(22, (Integer)map.get("leftFace"));
			stmt.setInt(23, (Integer)map.get("rightFace"));
			stmt.setInt(24, (Integer)map.get("leftBack"));
			stmt.setInt(25, (Integer)map.get("rightBack"));
			stmt.setInt(26, (Integer)map.get("tailBox"));
			stmt.setInt(27, (Integer)map.get("lock"));
			stmt.setInt(28, (Integer)map.get("power"));
			stmt.setInt(29, (Integer)map.get("gps"));
			stmt.setInt(30, (Integer)map.get("overSpeed"));
			stmt.setInt(31, (Integer)map.get("tired"));
			stmt.setInt(32, (Integer)map.get("circuit"));
			stmt.setInt(33, (Integer)map.get("hcoolant"));
			stmt.setInt(34, (Integer)map.get("keep"));
			stmt.setInt(35, (Integer)map.get("clean"));
			stmt.setInt(36, (Integer)map.get("deviceStatus"));
			stmt.setInt(37, (Integer)map.get("serialNumber"));
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
	 * 插入GPS混合OBD数据
	 * @param map
	 * @return
	 */
	public static int insertGpsAndObd(Map<String,Object> map){
		int rs = 0;
		PreparedStatement stmt = null;
		Connection con = null;
		//String sql01 = "insert into wbdp_realtimegps(deviceNumber,datatype,tripid,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType,gpsspeed,direction,gpsnumber,gsmquality,mail,mileage,engine,lcoolant,yesornot,protocol,acc,leftface,rightface,leftback,rightback,tailBox,centerlock,voltage,gpsModular,overspeed,tireddriving,circuit,hcoolant,keep,clean,deviceStatus,loadvalue,coolant,rmp,obdspeed,saangle,mapressure,cmvoltage,inlettemp,airflow,trposition,fuelcorrection,fuelratio,atposition,fuelpressure,ifwera,remainingoil,operator,lac,cell,serialnumber) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
		//String sql02 = "insert into wbdp_realtimegps(deviceNumber,datatype,tripid,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType,gpsspeed,direction,gpsnumber,gsmquality,mail,mileage,engine,lcoolant,yesornot,protocol,acc,leftface,rightface,leftback,rightback,tailBox,centerlock,voltage,gpsModular,overspeed,tireddriving,circuit,hcoolant,keep,clean,deviceStatus,loadvalue,coolant,rmp,obdspeed,saangle,mapressure,cmvoltage,inlettemp,airflow,trposition,fuelcorrection,fuelratio,atposition,fuelpressure,ifwera,premainingoil,operator,lac,cell,serialnumber) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";//+map
		try {
			con = DruidDataStore.getConnection();
			String sql01 = "insert into wbdp_realtimegps(deviceNumber,datatype,tripid,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType,gpsspeed,direction,gpsnumber,gsmquality,mail,mileage,engine,lcoolant,yesornot,protocol,acc,leftface,rightface,leftback,rightback,tailBox,centerlock,voltage,gpsModular,overspeed,tireddriving,circuit,hcoolant,keep,clean,deviceStatus,loadvalue,coolant,rmp,obdspeed,saangle,mapressure,cmvoltage,inlettemp,airflow,trposition,fuelcorrection,fuelratio,atposition,fuelpressure,ifwera,remainingoil,operator,lac,cell,serialnumber,obdoilwear) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
			String sql02 = "insert into wbdp_realtimegps(deviceNumber,datatype,tripid,time,longitude,latitude,EastOrWest,SouthOrNorth,gpsStatus,gpsType,gpsspeed,direction,gpsnumber,gsmquality,mail,mileage,engine,lcoolant,yesornot,protocol,acc,leftface,rightface,leftback,rightback,tailBox,centerlock,voltage,gpsModular,overspeed,tireddriving,circuit,hcoolant,keep,clean,deviceStatus,loadvalue,coolant,rmp,obdspeed,saangle,mapressure,cmvoltage,inlettemp,airflow,trposition,fuelcorrection,fuelratio,atposition,fuelpressure,ifwera,premainingoil,operator,lac,cell,serialnumber,obdoilwear) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";//+map
			if(map.get("premainingoil")!=null){
				stmt = (PreparedStatement) con.prepareStatement(sql02);
			}else{
				stmt = (PreparedStatement) con.prepareStatement(sql01);
			}
			stmt.setString(1, map.get("devicenumber").toString());
			stmt.setString(2, map.get("msgType").toString());
			stmt.setInt(3, (Integer)map.get("tripId"));
			stmt.setString(4, map.get("date").toString());
			stmt.setString(5, map.get("longitude").toString());
			stmt.setString(6, map.get("dimension").toString());
			stmt.setInt(7, (Integer)map.get("eastOrWest"));
			stmt.setInt(8, (Integer)map.get("southOrNorth"));
			stmt.setInt(9, (Integer)map.get("gpsStatus"));
			stmt.setInt(10, (Integer)map.get("gpsType"));
			stmt.setInt(11, (Integer)map.get("speed"));
			stmt.setInt(12, (Integer)map.get("direction"));
			stmt.setInt(13, (Integer)map.get("gpsNum"));
			stmt.setInt(14, (Integer)map.get("gpsPower"));
			stmt.setInt(15, (Integer)map.get("mail"));
			stmt.setInt(16, (Integer)map.get("life"));
			stmt.setInt(17, (Integer)map.get("motor"));
			stmt.setInt(18, (Integer)map.get("lcoolant"));
			stmt.setInt(19, (Integer)map.get("orNot"));
			stmt.setString(20, map.get("protocol").toString());
			stmt.setInt(21, (Integer)map.get("acc"));
			stmt.setInt(22, (Integer)map.get("leftFace"));
			stmt.setInt(23, (Integer)map.get("rightFace"));
			stmt.setInt(24, (Integer)map.get("leftBack"));
			stmt.setInt(25, (Integer)map.get("rightBack"));
			stmt.setInt(26, (Integer)map.get("tailBox"));
			stmt.setInt(27, (Integer)map.get("lock"));
			stmt.setInt(28, (Integer)map.get("power"));
			stmt.setInt(29, (Integer)map.get("gps"));
			stmt.setInt(30, (Integer)map.get("overSpeed"));
			stmt.setInt(31, (Integer)map.get("tired"));
			stmt.setInt(32, (Integer)map.get("circuit"));
			stmt.setInt(33, (Integer)map.get("hcoolant"));
			stmt.setInt(34, (Integer)map.get("keep"));
			stmt.setInt(35, (Integer)map.get("clean"));
			stmt.setInt(36, (Integer)map.get("deviceStatus"));
			stmt.setDouble(37, (Double)map.get("loadvalue"));
			stmt.setInt(38, (Integer)map.get("coolant"));
			stmt.setDouble(39, (Double)map.get("rmp"));
			stmt.setInt(40, (Integer)map.get("obdspeed"));
			stmt.setInt(41, (Integer)map.get("saangle"));
			stmt.setInt(42, (Integer)map.get("mapressure"));
			stmt.setDouble(43, (Double)map.get("cmvoltage"));
			stmt.setInt(44, (Integer)map.get("inlettemp"));
			stmt.setDouble(45, (Double)map.get("airflow"));
			stmt.setDouble(46, (Double)map.get("trposition"));
			stmt.setDouble(47, (Double)map.get("fuelcorrection"));
			stmt.setDouble(48, (Double)map.get("fuelratio"));
			stmt.setDouble(49, (Double)map.get("atposition"));
			stmt.setInt(50, (Integer)map.get("fuelpressure"));
			stmt.setDouble(51, (Double)map.get("ifwera"));
			if(map.get("premainingoil")!=null){
				stmt.setDouble(52, (Double)map.get("premainingoil"));
			}else{
				stmt.setDouble(52, (Double)map.get("remainingoil"));
			}
			stmt.setString(53, map.get("operator").toString());
			stmt.setString(54, map.get("lac").toString());
			stmt.setString(55, map.get("cell").toString());
			stmt.setInt(56, (Integer)map.get("serialnumber"));
			stmt.setInt(57, (Integer)map.get("obdoilwear"));
			 rs=stmt.executeUpdate();
			//stmt.setString(2, map.get("pwd"));
			 return rs;
		} catch (Exception e) {
			logger.error(e);
			return 0;
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
}
