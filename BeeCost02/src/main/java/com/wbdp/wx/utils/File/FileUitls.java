package com.wbdp.wx.utils.File;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by wisedata005 on 2017/7/5.
 */
public class FileUitls {

    /**日志log*/
    private static Logger log = Logger.getLogger(FileUitls.class);
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
     * 调试
     */
    @Test
    public void test(){
//        String readTemproaryBodyFile = readTemproaryBodyFile();
//        log.info(readTemproaryBodyFile);
		String readWeiChatProperties = readWeiChatProperties("APPID", "properties/WXMessage.properties");
		System.out.println(readWeiChatProperties);
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
