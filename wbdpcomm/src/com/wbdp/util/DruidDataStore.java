package com.wbdp.util;



import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import com.alibaba.druid.pool.DruidDataSourceFactory;



/**
 * 数据库连接类，用于初始化连接池
 * @author 汪赛军
 *
 */
public class DruidDataStore {
	private static Logger logger = Logger.getLogger(DruidDataStore.class);
	private static DataSource ds = null;	
	
	static {
		try{
			InputStream in = DruidDataStore.class.getClassLoader()
					               .getResourceAsStream("druid.properties");
            Properties props = new Properties();
			props.load(in);
			ds = DruidDataSourceFactory.createDataSource(props);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection(){
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			logger.error(e);
		}
		return con;
	}	
	
}
