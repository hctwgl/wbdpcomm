package com.wbdp.server;


import org.apache.log4j.Logger;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * netty服务器类,用于初始化服务器
 * @author 汪赛军
 *
 */
public class WiseDataServer {
	private static Logger logger = Logger.getLogger(WiseDataServer.class);
	public  void bind(int port){
		//配置服务器端的线程组
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			//将两个线程组传递到ServerBootstrap中
			b.group(boss,worker)
			//设置通道类型，对应ServerSocketChannel
			.channel(NioServerSocketChannel.class)
			//设置tcp参数
			.option(ChannelOption.SO_BACKLOG, 10000)
			//绑定I/O事件的处理类
			.childHandler(new ChildChannelHandler());
			// b.childOption(ChannelOption.SO_KEEPALIVE, true);
			//绑定端口，同步等待成功，ChannelFuture用于异步操作的通知回调
			ChannelFuture f = b.bind(port).sync();
			//等待服务器监听端口关闭，该方法会阻塞
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			logger.error(e);
		}finally{
			//优雅的关闭线程，释放资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
		}
	}
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			//添加两个个解决粘包的解码器
			//ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
			//ch.pipeline().addLast(new StringDecoder());
			//添加分包解码器，以29作为结束符
			ByteBuf delimiter = Unpooled.copiedBuffer(")".getBytes()); //使用)为分隔符
            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
            //ch.pipeline().addLast(new StringDecoder());
			//心跳包机制
			ch.pipeline().addLast(new IdleStateHandler(600, 600, 600));
			//添加消息处理类
			ch.pipeline().addLast(new WiseDataServerHandler());
			
		}
	}
	public static void main(String [] atgs){
		WiseDataServer server = new WiseDataServer();
		server.bind(6666);
	}
}
