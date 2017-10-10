package com.wbdp.client;

import org.apache.log4j.Logger;
import com.wbdp.util.Converts;
import com.wbdp.util.HexUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * 客户端处理类，用于发送消息到终端并接收终端回复消息
 * @author wisedata003
 *
 */
public class SimpleClientHandler extends SimpleChannelInboundHandler<ByteBuf>{
	private static Logger logger = Logger.getLogger(SimpleClientHandler.class);
	private ByteBuf clientMessage;  
    
	  
    public SimpleClientHandler(String msg) {  
          
        //byte [] req = msg.getBytes();  
    	//将字符串转换为十六进制字节数组，终端能正确解析
         byte[] b = HexUtils.hexStringToBytes(msg);
        clientMessage = Unpooled.buffer(b.length);  
        clientMessage.writeBytes(b);  
    }  
  
   
  @Override
public void channelRead(ChannelHandlerContext arg0, Object arg1)
		throws Exception {
	
}
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
  
        ctx.close();  
    }



	@Override
	protected void channelRead0(ChannelHandlerContext arg0, ByteBuf arg1)
			throws Exception {
		
		
	}  
}
