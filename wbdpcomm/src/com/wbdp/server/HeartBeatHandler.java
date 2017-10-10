package com.wbdp.server;

import org.apache.log4j.Logger;
import com.wbdp.cy.MessageBack;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
/**
 * netty心跳处理类，规定时间未收到心跳包则主动关闭连接
 * @author 汪赛军
 *
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {
	private static Logger logger = Logger.getLogger(HeartBeatHandler.class);
	
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case READER_IDLE:
                    handleReaderIdle(ctx);
                    break;
                case WRITER_IDLE:
                    handleWriterIdle(ctx);
                    break;
                case ALL_IDLE:
                    handleAllIdle(ctx);
                    break;
                default:
                    break;
            }
        }
		super.userEventTriggered(ctx, evt);
	}
	protected void handleReaderIdle(ChannelHandlerContext ctx) {
		//System.out.println("此时的channel:"+ctx.channel());
        System.err.println("---READER_IDLE---");
        //关闭当前通道
        ctx.close();
    }

    protected void handleWriterIdle(ChannelHandlerContext ctx) {
    	//System.out.println("此时的channel:"+ctx.channel());
        System.err.println("---WRITER_IDLE---");
    }

    protected void handleAllIdle(ChannelHandlerContext ctx) {
    	//System.out.println("此时的channel:"+ctx.channel());
        System.err.println("---ALL_IDLE---");
    }
	
	
}
