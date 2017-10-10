package com.wbdp.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wbdp.util.DruidDataStore;

public class AlertCodeData {
	private static Logger logger = Logger.getLogger(AlertCodeData.class);
		//插入故障码信息
		public static int insertAlert(Map<String,Object> map){
			int rs = 0;
				PreparedStatement stmt = null;
				ResultSet ru = null;
				Connection con=null;
				//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
				try {
					con =  DruidDataStore.getConnection();
					String sql = "insert into wbdp_alertcode(deviceNumber,alert,date) values(?,?,?)";
					stmt = con.prepareStatement(sql);
					stmt.setString(1, map.get("devicenumber").toString());
					stmt.setString(2, map.get("alert").toString());
					stmt.setString(3, (String)map.get("date"));
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
			
			
			return rs;
		}
				//查询故障码信息
				public static String searchAlert(Map<String,Object> map){
					int rs = 0;
					
						PreparedStatement stmt = null;
						ResultSet ru = null;
						Connection con=null;
						//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
						try {
							con =  DruidDataStore.getConnection();
							String sql ="select content from alertcode where number like ? "; 
							stmt = con.prepareStatement(sql);
							stmt.setString(1,  "%" + map.get("alert").toString() + "%" );
							ResultSet result = stmt.executeQuery();
							if(result.next()){
								return result.getString("content");
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
