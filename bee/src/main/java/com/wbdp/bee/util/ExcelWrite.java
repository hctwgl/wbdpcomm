package com.wbdp.bee.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelWrite {

	
	/**
	 * 
	 * <p>方法名: writeExcel</p> 
	 * <p>方法描述: 生成Excel</p>
	 * <p>入参描述: 传入Map,Map需要包含键名:name,card,phone</p>
	 * <p>回调描述: </p>
	 * <p>创建人:wisedata004  </p>
	 * <p>创建时间: 2017年9月9日 </p>
	 */
	public static String writeExcel(List<Map<String,Object>> list) {
		XSSFWorkbook wb = new XSSFWorkbook();
		// 生成一个sheet1
		XSSFSheet sheet = wb.createSheet("sheet1");
		          //设置每行宽度
                  sheet.setColumnWidth(0,15*256);
                  sheet.setColumnWidth(1,25*256);
                  sheet.setColumnWidth(2,25*256);
                  sheet.setColumnWidth(3,15*256);
                  sheet.setColumnWidth(4,15*256);
                  sheet.setColumnWidth(5,15*256);
                  sheet.setColumnWidth(6,15*256);
      
      //-----------------------------------设置样式------------------------------------
      // 生成一个样式  
       XSSFCellStyle style = wb.createCellStyle();
      //样式字体居中
       style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
       style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); 
       
      //----------------------------------设置表头--------------------------------------
		// 为sheet1生成第一行，用于放表头信息
		XSSFRow row = sheet.createRow(0);
		        //设置行高
		        row.setHeight((short) 800);
		//第一列
		XSSFCell cell = row.createCell((short) 0);
		cell.setCellStyle(style);
		cell.setCellValue("姓名");
		//第二列
		cell = row.createCell((short) 1);
		cell.setCellStyle(style);
		cell.setCellValue("身份证号");
		//第三列
		cell = row.createCell((short) 2);
		cell.setCellStyle(style);
		cell.setCellValue("手机号");
		//第四列
		cell = row.createCell((short) 3);
		cell.setCellStyle(style);
		cell.setCellValue("部门");
		//第五列
		cell = row.createCell((short) 4);
		cell.setCellStyle(style);
		cell.setCellValue("套餐价格(每月)");
		//第六列
		cell = row.createCell((short) 5);
		cell.setCellStyle(style);
		cell.setCellValue("期数");
		//第七列
		cell = row.createCell((short) 6);
		cell.setCellStyle(style);
		cell.setCellValue("手机型号");
        //第8列
		cell = row.createCell((short) 7);
		cell.setCellStyle(style);
		cell.setCellValue("总金额(价格x期数)");

	

		//----------------------------------设置表数据--------------------------
        //循环入参List里面的Map
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map=list.get(i);
                      
			// 数据每增加一行，表格就再生成一行
			row = sheet.createRow(1+i);
			//设置行高
			row.setHeight((short) 500);
			
			//设置第二列数据,序号随着数据量的增加而增加
			cell = row.createCell((short) 0);
			cell.setCellStyle(style);
			cell.setCellValue(map.get("BeeName").toString());
			//设置第二列数据
			cell = row.createCell((short) 1);
			cell.setCellStyle(style);
			cell.setCellValue(map.get("BeeCard").toString());
			//设置第二列数据
			cell = row.createCell((short) 2);
			cell.setCellStyle(style);
			cell.setCellValue(map.get("Phone").toString());
		}

		
		//开启流,写出文件
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
		    byte[] content = os.toByteArray();
		    //获取桌面路径
		   /* FileSystemView fsv = FileSystemView.getFileSystemView();
			File com=fsv.getHomeDirectory();  
			System.out.println("桌面路径："+com.getPath());*/
		    File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		    String desktopPath = desktopDir.getAbsolutePath();
		    Long timestamp = new Date().getTime();
		    System.out.println("桌面路径："+desktopPath);///usr/local/tomcat7/webapps/YiStagingSystem/excel
		    File file = new File("/usr/local/tomcat7/webapps/bee/excel"+"/"+timestamp.toString()+"-"+"beeClient.xlsx");// Excel文件生成后存储的位置。
            //关闭流
		    OutputStream fos = null;
			fos = new FileOutputStream(file);
			fos.write(content);
			os.close();
			fos.close();
			return timestamp.toString()+"-"+"beeClient.xlsx";
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public static void main(String[] args) {
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File com=fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
		System.out.println(com.getPath());
		Map<String,Object> map1=new HashMap<String, Object>();
		                   map1.put("name", "蒋宴炜");
		                   map1.put("card", "421124199410192057");
		                   map1.put("phone", "15527477588");
//		           		Map<String,Object> map2=new HashMap<String, Object>();
//		                   map1.put("name", "丁杰");
//		                   map1.put("card", "421124199410192058");
//		                   map1.put("phone", "133333333333");
		                   
	List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	                         list.add(map1);
//	                         list.add(map2);
		                   
		ExcelWrite.writeExcel(list);
	}
}
