package com.wbdp.cy;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.wbdp.util.Converts;
import com.wbdp.util.HexUtils;

/**
 * 服务器回复终端消息
 * @author wisedata003
 *
 */
public class MessageBack {
	private static Logger logger = Logger.getLogger(MessageBack.class);
	/**
	 * 回复警报信息
	 * @return
	 */
	public static String alarmBack(String str){
		String del = CyDataUtil.delStr(str);
		String escape = CyDataUtil.escapeStr(del);
		//System.out.println("回复消息："+escape);
		//取得警报编号
		String num = escape.substring(20, 22);
		//设备编号
		String deviceNum = CyData.findDeviceid(str);
		//System.out.println("回复消息："+num);
		String msg = deviceNum+"0008000201"+num+"FF";
		//分割消息，进行异或校验
		String i =Integer.toHexString(CyDataUtil.xorStr(CyDataUtil.splitStr(msg)));
		//将"FF"从字符串清除
		String newMsg = msg.replaceAll("FF", "");
		//将字符串进行转义
		String esp = CyDataUtil.strEscape(msg+i);
		String msgEnd = "28"+esp+"29";
		return msgEnd;
	}
	/**
	 * 发送设置IP命令
	 * @return
	 */
	public static String orderIP(String str){
		//String s = "53160801066710011922E1682E12E10";
		//120.76.
		try {
			if("0".equals(str.substring(2,3))){
				//获取数据
				String del = CyDataUtil.delStr(str).substring(1);
				//System.out.println("去掉0的数据："+del);
				String escape = CyDataUtil.escapeStr(del);
				//将ip分割
				String[] split = escape.substring(16).split("2E");
				//System.out.println("IP："+split[0]+" "+split[3]);
				//System.out.println("回复消息："+escape); 5316080106671001001B013131342E3131342E3131342E3131342C363636362C434D4E4554
				//取得ip数据								5316080106671001001B013131342E3131342E3131342E3131342C363636362C434D4E4554
				String ip1 = escape.substring(16,19);
				String ip2 = escape.substring(19,22);
				String ip3 = escape.substring(22,25);
				String ip4 = escape.substring(25);
				//分割ip地址将十进制转换为十六进制码
				int p1 = split[0].length();
				int p2 = split[1].length();
				int p3 = split[2].length();
				int p4 = split[3].length();
				String hexip1 = "";
				for(int i = 0;i<p1;i++){
					hexip1+=HexUtils.str2HexStr(split[0].substring(i,i+1), "utf-8");
				}
				String hexip2 = "";
				for(int i = 0;i<p2;i++){
					hexip2+=HexUtils.str2HexStr(split[1].substring(i,i+1), "utf-8");
				}
				String hexip3 = "";
				for(int i = 0;i<p3;i++){
					hexip3+=HexUtils.str2HexStr(split[2].substring(i,i+1), "utf-8");
				}
				String hexip4 = "";
				for(int i = 0;i<p4;i++){
					hexip4+=HexUtils.str2HexStr(split[3].substring(i,i+1), "utf-8");
				}
				//设备编号
				String deviceNum = escape.substring(0,12);
				//System.out.println("回复消息："+num);
				//拼接命令
				String order = hexip1+"2E"+hexip2+"2E"+hexip3+"2E"+hexip4+"2C"+"36363636"+"2C"+"434D4E4554";
				//获取命令长度
				String size = Integer.toHexString((order.length())/2);
				//System.out.println("拼接后的命令："+order+" 消息的长度"+size);
				String msg =deviceNum+"100100"+size+"01"+ hexip1+"2E"+hexip2+"2E"+hexip3+"2E"+hexip4+"2C"+"36363636"+"2C"+"434D4E4554"+"FF";
				//System.out.println("拼接后的消息"+msg);
				//分割消息，进行异或校验
				String i =Integer.toHexString(CyDataUtil.xorStr(CyDataUtil.splitStr(msg)));
				//System.out.println("校验码"+i);
				//将"FF"从字符串清除
				String newMsg = msg.replaceAll("FF", "");
				//将字符串进行转义
				String esp = null;
				if(i.length()==1){
					 esp = CyDataUtil.strEscape(newMsg+"0"+i);
				}else{
					 esp = CyDataUtil.strEscape(newMsg+i);
				}
				String msgEnd = "28"+esp+"29";
				return msgEnd;
			}else{
				//获取数据
				String del = CyDataUtil.delStr(str);
				//System.out.println("去掉0的数据："+del);
				String escape = CyDataUtil.escapeStr(del);
				//将ip分割
				String[] split = escape.substring(16).split("2E");
				//System.out.println("IP："+split[0]+" "+split[3]);
				//System.out.println("回复消息："+escape); 5316080106671001001B013131342E3131342E3131342E3131342C363636362C434D4E4554
				//取得ip数据								5316080106671001001B013131342E3131342E3131342E3131342C363636362C434D4E4554
				//分割ip地址将十进制转换为十六进制码
				int p1 = split[0].length();
				int p2 = split[1].length();
				int p3 = split[2].length();
				int p4 = split[3].length();
				//通过循环将ip每一位转换成ascll码
				String hexip1 = "";
				for(int i = 0;i<p1;i++){
					hexip1+=HexUtils.str2HexStr(split[0].substring(i,i+1), "utf-8");
				}
				String hexip2 = "";
				for(int i = 0;i<p2;i++){
					hexip2+=HexUtils.str2HexStr(split[1].substring(i,i+1), "utf-8");
				}
				String hexip3 = "";
				for(int i = 0;i<p3;i++){
					hexip3+=HexUtils.str2HexStr(split[2].substring(i,i+1), "utf-8");
				}
				String hexip4 = "";
				for(int i = 0;i<p4;i++){
					hexip4+=HexUtils.str2HexStr(split[3].substring(i,i+1), "utf-8");
				}
				//设备编号
				String deviceNum = escape.substring(0,12);
				//拼接命令
				String order = hexip1+"2E"+hexip2+"2E"+hexip3+"2E"+hexip4+"2C"+"36363636"+"2C"+"434D4E4554";
				//获取命令长度
				String size = Integer.toHexString((order.length())/2);
				//System.out.println("拼接后的命令："+order+" 消息的长度"+size);
				String msg =deviceNum+"100100"+size+"01"+ hexip1+"2E"+hexip2+"2E"+hexip3+"2E"+hexip4+"2C"+"36363636"+"2C"+"434D4E4554"+"FF";
				//System.out.println("拼接后的消息"+msg);
				//分割消息，进行异或校验
				String i =Integer.toHexString(CyDataUtil.xorStr(CyDataUtil.splitStr(msg)));
				//System.out.println("校验码"+i);
				//将"FF"从字符串清除
				String newMsg = msg.replaceAll("FF", "");
				//将字符串进行转义
				String esp = null;
				if(i.length()==1){
					 esp = CyDataUtil.strEscape(newMsg+"0"+i);
				}else{
					 esp = CyDataUtil.strEscape(newMsg+i);
				}
				String msgEnd = "28"+esp+"29";
				return msgEnd;
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 清除故障码信息
	 * @return
	 */
	public static String clearCode(String str){
		//设备编号
		String deviceNum = str.substring(0,12);
		//System.out.println("回复消息："+num);
		//加上"FF"用于异或校验
		String msg = deviceNum+"3006000101FF";
		//分割消息，进行异或校验
		String i =Integer.toHexString(CyDataUtil.xorStr(CyDataUtil.splitStr(msg)));
		//System.out.println("校验码："+i);
		//将字符串进行转义
		String newMsg = msg.replaceAll("FF", "");
		String esp = CyDataUtil.strEscape(newMsg+i);
		String msgEnd = "28"+esp+"29";
		//System.out.println(msgEnd);
		return msgEnd;
	}
	
}
