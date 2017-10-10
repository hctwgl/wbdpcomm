package com.wbdp.wx.utils.Dowload;

import com.wbdp.wx.model.UserIDCard;
import com.wbdp.wx.service.impl.register.RegisterServiceImpl;
import com.wbdp.wx.utils.http.DloadImgUtil;
import com.wbdp.wx.utils.wx.WXCacheUnit;
import net.sf.json.JSONObject;

import java.util.Date;

import org.slf4j.LoggerFactory;

/**
 * Created by CatalpaFlat on 2017/7/9.
 */
public class DowloadWXImgUtils {
    /**日志log*/
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DowloadWXImgUtils.class);
    /**
     * 下载微信图片
     * @param userIDCard
     * @param openid
     * @return
     */
    public static JSONObject dowloadWXImg(UserIDCard userIDCard, String openid) {
        String savePath = "/usr/local/tomcat7/webapps/YiStaging/downimg/";
        String oneserverId1 = userIDCard.getOneserverId1();
        String oneserverId2 = userIDCard.getOneserverId2();
        String oneserverId3 = userIDCard.getOneserverId3();
        int type = userIDCard.getType();
        log.info("type:"+type);
        String timestamp = String.valueOf(new Date().getTime() / 1000);
        String firstPath = "";
        String ordinaryAccess_Token = WXCacheUnit.getOrdinaryAccess_Token();
        if(type==1||type==3){
        	 String firstPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
                     oneserverId1, savePath,openid,1,timestamp);
             if(!firstPathDload.equals(null))
                 firstPath = "http://wisedp.com/YiStaging/downimg/"+openid+"-"+timestamp+"-01"+".png";
        }
        String secondPath = "";
        if(type==2||type==3){
            String secondPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
                    oneserverId2, savePath,openid,2,timestamp);
            if(!secondPathDload.equals(null))
                secondPath = "http://wisedp.com/YiStaging/downimg/"+openid+"-"+timestamp+"-02"+".png";
        }
        String threePath = "";
        if(type==4||type==3){
       	 String threePathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
       			oneserverId3, savePath,openid,3,timestamp);
            if(!threePathDload.equals(null))
            	threePath = "http://wisedp.com/YiStaging/downimg/"+openid+"-"+timestamp+"-03"+".png";
        }
        
        if(type==5){

       	   String firstPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
                    oneserverId1, savePath,openid,1,timestamp);
            if(!firstPathDload.equals(null))
                firstPath = "http://wisedp.com/YiStaging/downimg/"+openid+"-"+timestamp+"-01"+".png";
            String secondPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
                    oneserverId2, savePath,openid,2,timestamp);
            if(!secondPathDload.equals(null))
                secondPath = "http://wisedp.com/YiStaging/downimg/"+openid+"-"+timestamp+"-02"+".png";
        }
        if(type==6){
        	  String firstPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
                     oneserverId1, savePath,openid,1,timestamp);
             if(!firstPathDload.equals(null))
                 firstPath = "http://wisedp.com/BeeCost/downimg/"+openid+"-"+timestamp+"-01"+".png";
           	 String threePathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
            			oneserverId3, savePath,openid,3,timestamp);
                 if(!threePathDload.equals(null))
                 	threePath = "http://wisedp.com/BeeCost/downimg/"+openid+"-"+timestamp+"-03"+".png";
        }
        if(type==7){
            String secondPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
                    oneserverId2, savePath,openid,2,timestamp);
            if(!secondPathDload.equals(null))
                secondPath = "http://wisedp.com/BeeCost/downimg/"+openid+"-"+timestamp+"-02"+".png";
         	String threePathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
          			oneserverId3, savePath,openid,3,timestamp);
            if(!threePathDload.equals(null))
               	threePath = "http://wisedp.com/BeeCost/downimg/"+openid+"-"+timestamp+"-03"+".png";
      }
        JSONObject imgpathJson = null;

        if(!oneserverId1.equals("")&&!oneserverId2.equals("")){
            imgpathJson = new JSONObject();
            if(type==1||type==3||type==6||type==5)
            	imgpathJson.put("firstPath",firstPath);
            else
            	imgpathJson.put("firstPath",oneserverId1);
            if(type==2||type==3||type==7||type==5)
            	imgpathJson.put("secondPath",secondPath);
            else
                imgpathJson.put("secondPath",oneserverId2);
            if(type==4||type==3||type==7||type==6)
            	imgpathJson.put("threePath",threePath);
            else
                imgpathJson.put("threePath",oneserverId3);
        }

        log.info("imgpathJson:"+imgpathJson);
        return  imgpathJson;
    }
    
    /**
     * 我写的图片下载加保存
     * @return
     */
    public static JSONObject downloadAndSaveImg(UserIDCard userIDCard, String openid){
	    try {
	    	String savePath = "/usr/local/tomcat7/webapps/YiStaging/downimg/";
	        String oneserverId1 = userIDCard.getOneserverId1();
	        String oneserverId2 = userIDCard.getOneserverId2();
	        int type = userIDCard.getType();
	        log.info("type:"+type);
	        String timestamp = String.valueOf(new Date().getTime() / 1000);
	        String firstPath = "";
	        String secondPath = "";
	        String ordinaryAccess_Token = WXCacheUnit.getOrdinaryAccess_Token();
	        JSONObject imgpathJson = new JSONObject();
	        //身份证正面上传到服务器后返回可访问路径
	        String firstPathDload= "";
	        //身份证反面上传到服务器后返回可访问路径
	        String secondPathDload = "";
	        switch (type) {
			case 1://上传了第一张
				//身份证正面
		         firstPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
	                    oneserverId1, savePath,openid,1,timestamp);
		         firstPath = "http://wisedp.com/YiStaging/downimg/"+openid+"-"+timestamp+"-01"+".png";
		         imgpathJson.put("firstPath", firstPath);
		         imgpathJson.put("secondPath", oneserverId2);
				break;
			case 2://上传了第二张
				//身份证反面
		         secondPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
	                    oneserverId2, savePath,openid,2,timestamp);
		         secondPath = "http://wisedp.com/YiStaging/downimg/"+openid+"-"+timestamp+"-02"+".png";
		         imgpathJson.put("firstPath", oneserverId1);
		         imgpathJson.put("secondPath", secondPath);
				break;
			case 3://两张都上传了
				//身份证正面
		         firstPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
	                    oneserverId1, savePath,openid,1,timestamp);
		         firstPath = "http://wisedp.com/YiStaging/downimg/"+openid+"-"+timestamp+"-01"+".png";
		         //身份证反面
		         secondPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
	                    oneserverId2, savePath,openid,2,timestamp);
		         secondPath = "http://wisedp.com/YiStaging/downimg/"+openid+"-"+timestamp+"-02"+".png";
		         imgpathJson.put("firstPath", firstPath);
		         imgpathJson.put("secondPath", secondPath);
				break;
			default:
				break;
			}
	        
	        return imgpathJson;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 人脸识别图片保存
     * @return
     */
    public static String  downloadAndSaveFace(String oneserverId1, String openid){
	    try {
	    	String savePath = "/usr/local/tomcat7/webapps/YiStaging/downimg/";
	        String timestamp = String.valueOf(new Date().getTime() / 1000);
	        String ordinaryAccess_Token = WXCacheUnit.getOrdinaryAccess_Token();
	        JSONObject imgpathJson = new JSONObject();
		         String firstPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
		        		 oneserverId1, savePath,openid,1,timestamp);
		         String imagePath = "http://wisedp.com/YiStaging/downimg/"+openid+"-"+timestamp+"-01"+".png";
	        return imagePath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
}
