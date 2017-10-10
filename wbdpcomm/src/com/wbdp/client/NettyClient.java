package com.wbdp.client;

import org.apache.log4j.Logger;

import com.wbdp.server.WiseDataServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * 客户端类
 * @author 汪赛军
 *
 */
public class NettyClient {
	private static Logger logger = Logger.getLogger(NettyClient.class);  
	 public void connect(int port,String host, final String msg){  
         
	        EventLoopGroup group = new NioEventLoopGroup();  
	          
	        try {  
	            Bootstrap bootstrap = new Bootstrap();  
	            bootstrap.group(group)  
	            .channel(NioSocketChannel.class)  
	            .option(ChannelOption.TCP_NODELAY, true)  
	            .handler(new ChannelInitializer<SocketChannel>() {  
	  
	                @Override  
	                protected void initChannel(SocketChannel ch) throws Exception {  
	                    ch.pipeline().addLast(new SimpleClientHandler(msg));  
	                }  
	            });  
	            //发起异步链接操作  
	            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();  
	              //等待客户端链路关闭
	            channelFuture.channel().closeFuture().sync();  
	        } catch (InterruptedException e) {  
	            // TODO Auto-generated catch block  
	        	logger.error(e);
	        }finally{  
	            //关闭，释放线程资源  
	            group.shutdownGracefully();  
	        }  
	    }  
	 public static void main(String [] atgs){
		 NettyClient c = new NettyClient();
		 c.connect(6666, "127.0.0.1", "你好啊");
		}
	  
	      
}
