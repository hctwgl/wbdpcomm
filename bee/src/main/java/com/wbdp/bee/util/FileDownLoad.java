package com.wbdp.bee.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件下载类
 * @author 汪赛军
 * date:2017年9月11日下午4:10:57
 *
 */
public class FileDownLoad {
	public static void fileDownload(HttpServletResponse response,String fileName) throws IOException{
		System.out.println("开始下载文件");
		response.setCharacterEncoding("utf-8");
        //返回的数据类型
        response.setContentType("application/xlsx");
        //响应头
        response.setHeader("Content-Disposition", "attachment;fileName="
                + fileName);
        //fileName = "一分期客户模板.xlsx";
        InputStream inputStream=null;
       OutputStream outputStream=null;
        ///usr/local/tomcat7/webapps/YiStagingSystem/excel
        String path ="E:/QRcode/";
        byte[] bytes = new byte[2048];
        try {
            File file=new File(path,fileName);
            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            int length;
            //inputStream.read(bytes)从file中读取数据,-1是读取完的标志
           while ((length = inputStream.read(bytes)) > 0) {
                //写数据
                outputStream.write(bytes, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭输入输出流
            if(outputStream!=null) {
                outputStream.close();
            }
            if(inputStream!=null) {
                inputStream.close();
           }
        }
	}
}
