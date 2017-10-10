package com.wbdp.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wbdp.util.DruidDataStore;

public class CustomerCRMData {
	private static Logger logger = Logger.getLogger(CustomerCRMData.class);
	public static Map<String,Long> selectCustomer(Long vehiceID){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Long> map = new HashMap<String, Long>();
		PreparedStatement stmt = null;
		Connection con =  null;
		try {
			con =  DruidDataStore.getConnection();
			String sql = " select OwnerManagerID from `wbdp_customerCRM` where VehiceID = ?";
			stmt =  con.prepareStatement(sql);
			stmt.setLong(1, vehiceID);
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				map.put("manager", result.getLong("OwnerManagerID"));
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
