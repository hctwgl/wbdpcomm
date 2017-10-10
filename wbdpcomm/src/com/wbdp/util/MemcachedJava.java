package com.wbdp.util;

import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import net.spy.memcached.MemcachedClient;

/**
 * 缓存类，用于将终端ip与端口信息实时保存
 * @author 汪赛军
 *
 */
public class MemcachedJava {
	private static String ip = "127.0.0.1";
	private static int port = 11211;
	private static MemcachedClient mcc;
	/**
	 * @param args
	 */
	public static void saveMemcached(String key,ChannelHandlerContext ctx) {
		try {
			//连接本地Memcached
			mcc = new MemcachedClient(new InetSocketAddress(ip, port));
			//System.out.println("Connection to server sucessful.");
			//System.out.println("通道："+ctx);
			String msg = ctx.channel().remoteAddress().toString();
			mcc.set(key, 200, msg);
			//System.out.println("保存成功！！！");
			System.out.println("存入缓存的通道:"+mcc.get(key));
		} catch (Exception e) { 
			e.printStackTrace();
		}finally{
			mcc.shutdown();
		}
	}
	public static String getMemcached(String key){
		//连接本地Memcached
		try {
			mcc = new MemcachedClient(new InetSocketAddress(ip, port));
			//System.out.println("存入缓存的通道:"+mcc.get(key));
			Object address = mcc.get(key);
			if(address!=null){
				String ctx = mcc.get(key).toString();
				return    ctx;
			}else{
				return "false";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			mcc.shutdown();
		}
		return null;
	}
	
	public static void main(String[] args){
		//连接本地Memcached
		try {
			mcc = new MemcachedClient(new InetSocketAddress(ip, port));
			mcc.set("1206602", 200, "大家好才是真的好");
			System.out.println(getMemcached("531610100016"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			mcc.shutdown();
		}
	}
}