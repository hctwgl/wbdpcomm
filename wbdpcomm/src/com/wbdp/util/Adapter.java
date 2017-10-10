package com.wbdp.util;



import com.wbdp.cy.CyData;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;


/**
 * 协议的适配器，用于判断协议类型
 * @author 汪赛军
 *
 */
public class Adapter {
	/**
	 * 判断协议类型，并向客户端响应消息
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public static void checkProtocol(String str,ChannelHandlerContext ctx) {
		try {
			int strType = Integer.parseInt(str.substring(0,2),16);
			switch (strType) {
			//为40则表明是车云网现行通信协议
			case 40:
				CyData cy = new CyData(str, ctx);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

