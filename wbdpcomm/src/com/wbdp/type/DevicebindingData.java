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
 * 查询绑定表中设备对应的微信号
 * @author wisedata003
 *
 */
public class DevicebindingData {
	private static Logger logger = Logger.getLogger(DevicebindingData.class);
	public static Map<String,String> selectWX(Map<String,Object> map){
		Map<String,String> m = new HashMap<String, String>();
		PreparedStatement stmt = null;
		Connection con =  null;
		try {
			con =  DruidDataStore.getConnection();
			String sql = "select OwnerWX,PlateNumber from wbdp_devicebinding where DeviceUUID=? and Binding = 1";
			stmt =  con.prepareStatement(sql);
			stmt.setString(1, map.get("devicenumber").toString());
			ResultSet result = stmt.executeQuery();
			if(result.next()){
				m.put("PlateNumber", result.getString("PlateNumber"));
				m.put("OwnerWX", result.getString("OwnerWX"));
			}
			return m;
		} catch (Exception e) {
			logger.error(e);
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
