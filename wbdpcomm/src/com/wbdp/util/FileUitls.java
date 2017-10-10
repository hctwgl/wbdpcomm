package com.wbdp.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Test;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;


/**
 * 读取阿里大鱼环境配置文件
 * @author wisedata005
 */
public class FileUitls {

	private static Logger log = Logger.getLogger(FileUitls.class
			.getCanonicalName());

	/**
	 * 读取Properties的屬性
	 * @param key  Properties中的鍵
	 * @param proName Properties的名稱
	 * @return
	 */
	public static String readWeiChatProperties(String key, String proName) {
		Properties prop = new Properties();
		ClassLoader cl = FileUitls.class.getClassLoader();
		InputStream in = null;
		// 读取属性文件a.properties
		if (cl != null)
			in = cl.getResourceAsStream(proName);
		else
			in = ClassLoader.getSystemResourceAsStream(proName);
		try {
			if (in != null) {
				prop.load(in);
				Iterator<String> it = prop.stringPropertyNames().iterator();
				while (it.hasNext())
					if (it.next().equals(key))
						return prop.getProperty(key);
			}
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException：找不到指定的文件");
			e.printStackTrace();
		} catch (IOException e) {
			log.error("IO流异常");
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				log.error("IO流异常");
				e.printStackTrace();
			}
		}
		return key;
	}
	/**
	 * 转化成xml
	 * @param <T>
	 * @param unifiedOrder
	 * @return
	 */
	public static <T> String changeToXML(Class<T> cls){
//		XStream stream = new XStream(new XppDriver(new NameCoder() {
//			
//			public String encodeNode(String arg0) {
//				// TODO Auto-generated method stub
//				return arg0;
//			}
//			
//			public String encodeAttribute(String arg0) {
//				// TODO Auto-generated method stub
//				return arg0;
//			}
//			
//			public String decodeNode(String arg0) {
//				// TODO Auto-generated method stub
//				return arg0;
//			}
//			
//			public String decodeAttribute(String arg0) {
//				// TODO Auto-generated method stub
//				return arg0;
//			}
//		}));

		XStream stream=new XStream();
        stream.alias("xml", cls.getClass());
        String xml = stream.toXML(cls);
		return xml;
	}
	
	
	/**
	 * 调试
	 * @param argv
	 */
	@Test
	public void test(){
//        String readTemproaryBodyFile = readTemproaryBodyFile();
//        log.info(readTemproaryBodyFile);
//		String readWeiChatProperties = readWeiChatProperties("smsfreesignname", "message.properties");
//		System.out.println(readWeiChatProperties);
//		UnifiedOrder unifiedOrder = new UnifiedOrder();
//		try {
//			Map<String, String> sortMap = ReflectUitls.getSortMap(unifiedOrder);
//			System.out.println(sortMap.toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
}
