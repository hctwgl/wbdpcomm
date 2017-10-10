package com.wbdp.wx.utils.http;

/**
 * Created by wisedata005 on 2017/7/5.
 */

import com.wbdp.wx.utils.wx.WXCacheUnit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取微信服务器上传图片后下载
 */
public class DloadImgUtil {
    /**
     * 获取媒体文件
     * @param accessToken 接口访问凭证
     * @param mediaId 媒体文件id
     * @param savePath 文件在本地服务器上的存储路径
     * @param openID
     * @param type
     * */
    public static String downloadMedia(String accessToken, String mediaId,
                                       String savePath, String openID, int type,String timestamp) {

        accessToken = WXCacheUnit.getOrdinaryAccess_Token();
        System.out.println("mediaId:"+mediaId);
        System.out.println("accessToken:"+accessToken);

        String filePath = null;
        System.out.println("savePath:"+savePath);
        // 拼接请求地址
        String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+accessToken+"&media_id="+mediaId;
//		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
        System.out.println("requestUrl:"+requestUrl);

        System.out.println("savePath:"+savePath);
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            File file =new File(savePath);
            file.setWritable(true,false);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("目錄文件不存在，創建目錄");
            }

            if (!savePath.endsWith("/")) {
                savePath += "/";
            }

            // 根据内容类型获取扩展名
            String fileExt = DloadImgUtil.getFileexpandedName(conn
                    .getHeaderField("Content-Type"));

            System.out.println("fileExt:"+fileExt);
            // 将mediaId作为文件名
            switch (type) {
                case 1:
                    filePath = savePath + openID+"-"+timestamp+"-01"+".png";
                    break;
                case 2:
                    filePath = savePath + openID+"-"+timestamp+"-02"+".png";;
                    break;
                case 3:
                    filePath = savePath + openID+"-"+timestamp+"-03"+".png";;
                    break;
            }


            System.out.println("filePath:"+filePath);
            BufferedInputStream bis = new BufferedInputStream(
                    conn.getInputStream());
            File file2 = new File(filePath);
            file.setWritable(true,false);
            FileOutputStream fos = new FileOutputStream(file2);

            System.out.println("fos讀取");
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1)
                fos.write(buf, 0, size);

            fos.close();
            bis.close();
            conn.disconnect();

            String info = String.format("下载媒体文件成功，filePath=" + filePath);
            System.out.println(info);

        } catch (Exception e) {
            filePath = null;
            String error = String.format("下载媒体文件失败：%s", e);
            System.out.println(error);
        }
        return filePath;
    }
    /**
     * 根据内容类型判断文件扩展名
     * @param contentType 内容类型
     * @return
     */
    public static String getFileexpandedName(String contentType) {
        String fileEndWitsh = "";
        if ("image/jpeg".equals(contentType))
            fileEndWitsh = ".jpg";
        else if ("audio/mpeg".equals(contentType))
            fileEndWitsh = ".mp3";
        else if ("audio/amr".equals(contentType))
            fileEndWitsh = ".amr";
        else if ("video/mp4".equals(contentType))
            fileEndWitsh = ".mp4";
        else if ("video/mpeg4".equals(contentType))
            fileEndWitsh = ".mp4";
        return fileEndWitsh;
    }
}
