package com.wbdp.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wbdp.util.DruidDataStore;

public class VehiceownerData {
	private static Logger logger = Logger.getLogger(VehiceownerData.class);
	public static Map<String,String> selectOwnerWX(Long vehiceID){
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,String> map = new HashMap<String, String>();
		PreparedStatement stmt = null;
		Connection con =  null;
		try {
			con =  DruidDataStore.getConnection();
			String sql = " Select OwnerWX from `wbdp_vehiceowner` where id=?";
			stmt =  con.prepareStatement(sql);
			stmt.setLong(1, vehiceID);
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				map.put("wx", result.getString("OwnerWX"));
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
