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
/**
 * 插入推送数据
 * @author wisedata003
 *
 */
public class PushData {
	private static Logger logger = Logger.getLogger(PushData.class);
	public static void insertPush(Map<String,Object> map){
		//System.out.println("保存推送数据。。。");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PreparedStatement stmt = null;
		Connection con =  null;
		try {
			con =  DruidDataStore.getConnection();
			String sql = "insert into wbdp_pushwxmess(OwnerWX,PushType,PushContent,PlateNumber,PushCount,SaveDate,UpdateDate,EditBy,position) values(?,?,?,?,?,?,?,?,?)";
			stmt =  con.prepareStatement(sql);
			stmt.setString(1, map.get("ownerwx").toString());
			stmt.setInt(2, (Integer)map.get("pushType"));
			stmt.setString(3, map.get("reason").toString());
			stmt.setString(4, map.get("plateNumber").toString());
			stmt.setInt(5, 1);
			stmt.setString(6, format.format(new Date()));
			stmt.setString(7,format.format(new Date()));
			stmt.setString(8, "server");
			stmt.setString(9, map.get("place").toString());
			 stmt.executeUpdate();
		} catch (Exception e) {
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
}
