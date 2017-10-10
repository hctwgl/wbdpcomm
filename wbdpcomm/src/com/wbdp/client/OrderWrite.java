package com.wbdp.client;

import org.apache.log4j.Logger;

import com.wbdp.util.HexUtils;
import com.wbdp.util.MemcachedJava;



/**
 * 服务器端向客户端发送指令
 * @author 汪赛军
 *
 */
public class OrderWrite {
	private static Logger logger = Logger.getLogger(OrderWrite.class);
	
	public static void connection(String key,String msg){
		String serverAddress = MemcachedJava.getMemcached(key).replaceAll("/", "");
		if("false".equals(serverAddress)){
			System.out.println("无此连接。。。");
		}else{
			String[] str = serverAddress.split(":");
			String host = str[0];
			System.out.println(host);
			int port = Integer.parseInt(str[1]);
			System.out.println(port);
			NettyClient client = new NettyClient();
			client.connect(port, host, msg);
		}
		
	}
	public static void main(String[] args){
		NettyClient client = new NettyClient();
		String bcdStr = "2853161010001610880000CB29";
		byte[] b = HexUtils.hexStringToBytes(bcdStr);
		int i = 0xff;
		System.out.println(i);
		//System.out.println(BinaryToHexString(b));
		//byte buffer[] = { 0x23, 0x1A, 0x00, 0x00, 0x00, (byte) 0xCC, (byte) 0xC4, 0x14, 0x00, 0x0A, 0x40, (byte) 0xC5, 0x00, (byte) 0xD9, (byte) 0xE4, 0x22, 0x33, 0x1F, (byte) 0x98, 0x7C};
		//System.out.println(bcdStr);
		//client.connect(1234, "192.168.1.111", bcdStr);
		//String serverAddress = MemcachedJava.getMemcached("531610100016");
		//System.out.println(serverAddress);
		//connection("531610100016","28513000029");
	logger.info("log");
	}
    
   
}
