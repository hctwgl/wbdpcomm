package com.wbdp.wx.QRcode.util;

import java.io.File;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.wbdp.wx.service.impl.mine.MineSerciceImpl;

public class CreateQRCode {
	  /**日志log*/
    private static Logger log = LoggerFactory.getLogger(CreateQRCode.class);

	
	/**
	 * 生成二维码
	 * @return
	 */
	public static String createQRCode(String content,String savePath,String openid){
		int width = 300;//二维码图片的宽度
		int height = 300;//二维码图片的高度
		String format = "png";//二维码格式
		//String content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb125fba57e2524e2&redirect_uri=http%3A%2F%2Fwisedp.com%2Fwisemifi%2Fwx%2Fwisemifi%2Ftobuypage&response_type=code&scope=snsapi_base&state=123#wechat_redirect";//二维码内容
		//定义二维码内容参数
		HashMap hints = new HashMap();
		//设置字符集编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		//设置容错等级，在这里我们使用M级别
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		//设置边框距
		hints.put(EncodeHintType.MARGIN, 2);
		//生成二维码
		try {
			String timestamp = String.valueOf(new Date().getTime() / 1000);
			//指定二维码内容
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,hints);
			savePath = savePath+openid+"-"+timestamp+"-qr"+".png";
			log.info("生成二维码地址："+savePath);
			//指定生成图片的保存路径 示例：E:/QRcode/test02.jpg
			Path file = new File(savePath).toPath();
			//生成二维码
			MatrixToImageWriter.writeToPath(bitMatrix, format, file);
			savePath = "http://wisedp.com/YiStaging/downimg/"+openid+"-"+timestamp+"-qr"+".png";
			return savePath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
