package com.wbdp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 查询推送是否存在重复信息
 * @author wisedata003
 *
 */
public class PushCheckData {
	private static Logger logger = Logger.getLogger(PushCheckData.class);
	//查询故障码信息
	public static String searchAlert(Map<String,Object> map){
		int rs = 0;
			PreparedStatement stmt = null;
			ResultSet ru = null;
			Connection con=null;
			//+map.get("devicenumber")+","+map.get("timeStamp")+","+map.get("alarmType")+")";
			try {
				con =  DruidDataStore.getConnection();
				String sql ="select PushContent from wbdp_pushwxmess where TO_DAYS(SaveDate) = TO_DAYS(NOW()) and PushContent = ? and PlateNumber = ?"; 
				stmt = con.prepareStatement(sql);
				stmt.setString(1, map.get("alert").toString());
				stmt.setString(2, map.get("PlateNumber").toString());
				ResultSet result = stmt.executeQuery();
				if(result.next()){
					return result.getString("PushContent");
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
