package com.wbdp.server;




import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.wbdp.util.Adapter;
import com.wbdp.util.Converts;
import com.wbdp.util.HexUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;
/**
 * netty通讯处理类，将收到的终端消息转为字符串后调用适配器
 * @author 汪赛军
 *
 */
public class WiseDataServerHandler extends HeartBeatHandler {
	private static Logger logger = Logger.getLogger(WiseDataServerHandler.class);
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		//byte[] req = buf.getBytes();
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req,"utf-8");
		//System.out.println("原始消息："+HexUtils.BinaryToHexString(req));
		String bcdStr = Converts.bytesToHexString(req);
		System.out.println("时间："+format.format(new Date())+" "+"转换后的BCD数据："+bcdStr);
		//调取适配器，解析不同协议
		 Adapter.checkProtocol(bcdStr,ctx);
		 ReferenceCountUtil.release(buf); 
		// buf.release();
		//这里可以判断客户端传输的是什么消息，并进行判断选择回复
		/*String currentTime = "7e9906170307110333000512345678904e7e".equalsIgnoreCase(body)?new java.util.Date(
				System.currentTimeMillis()).toString():"BAD ORDER";*/
		/*ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.write(resp);*/
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		 ctx.flush(); 
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		 cause.printStackTrace(); 
		  ctx.close(); 
	}
	
}
