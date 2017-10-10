package com.wbdp.bee.util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

public class UtilPOI {

	 //日志
	 private static Logger logger  = Logger.getLogger(UtilPOI.class);  
	 
	    private final static String xls = "xls";  
	    private final static String xlsx = "xlsx";  
	    
	      
	    //总行数
	    private static int totalRows = 0;  
	    //总条数
	    private static int totalCells = 0; 
	    //获取总行数
	    public int getTotalRows()  { return totalRows;} 
	    
	    /** 
	     * 读入excel文件，解析后返回 
	     * @param file 
	     * @throws IOException  
	     */  
	    public static List<String[]> readExcel(MultipartFile file) throws IOException{  
	    	List<String[]> result=new ArrayList<String[]>();
	    	
	        //检查文件  
	        checkFile(file);  
	        //获得Workbook工作薄对象  
	        Workbook wb = getWorkBook(file);          
	        //得到第一个shell  
	        Sheet sheet=wb.getSheetAt(0);        
	       //得到Excel的行数
	        totalRows=sheet.getPhysicalNumberOfRows();	        
	       //得到Excel的列数(前提是有行数)
	        if(totalRows>=1 && sheet.getRow(0) != null){
	             totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
	        }                
	       //循环Excel行数,从第二行开始。标题不入库
	        for(int r=1;r<totalRows;r++){
	            Row row = sheet.getRow(r);
	             if (row == null) continue;
	              String[] temp = new String[totalCells];    
	            //循环Excel的列
	            for(int c = 0; c <totalCells; c++){
	            	//获取列内容
	                Cell cell = row.getCell(c);
	                   temp[c]=getCellValue(cell);
	                	System.out.println("第"+r+"行,"+"第"+c+"列数据:"+getCellValue(cell));
	            }
	            result.add(temp);
	        }
	        return result;  
	    }  
	    
	    /*
	     * 检查文件
	     */
	    public static void checkFile(MultipartFile file) throws IOException{  
	        //判断文件是否存在  
	        if(null == file){  
	            logger.error("文件不存在！");  
	            throw new FileNotFoundException("文件不存在！");  
	        }  
	        //获得文件名  
	        String fileName = file.getOriginalFilename();  
	        //判断文件是否是excel文件  
	        if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){  
	            logger.error(fileName + "不是excel文件");  
	            throw new IOException(fileName + "不是excel文件");  
	        }  
	    }  
	    
	    /*
	     * 获取工作簿
	     */
	    public static Workbook getWorkBook(MultipartFile file) {  
	        //获得文件名  
	        String fileName = file.getOriginalFilename();  
	        //创建Workbook工作薄对象，表示整个excel  
	        Workbook workbook = null;  
	        try {  
	            //获取excel文件的io流  
	            InputStream is = file.getInputStream();  
	            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象  
	            if(fileName.endsWith(xls)){  
	                //2003  
	                workbook = new HSSFWorkbook(is);  
	            }else if(fileName.endsWith(xlsx)){  
	                //2007  
	                workbook = new XSSFWorkbook(is);  
	            }  
	        } catch (IOException e) {  
	            logger.info(e.getMessage());  
	        }  
	        return workbook;  
	    }  
	    
	    /*
	     * 获取工作行
	     */
	    public static String getCellValue(Cell cell){  
	        String cellValue = "";  
	        if(cell==null||cell.equals("")||cell.getCellType() ==HSSFCell.CELL_TYPE_BLANK){  
	            return cellValue;  
	        }  
	        //把数字当成String来读，避免出现1读成1.0的情况  
	        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){  
	            cell.setCellType(Cell.CELL_TYPE_STRING);  
	        }  
	        //判断数据的类型  
	        switch (cell.getCellType()){  
	            case Cell.CELL_TYPE_NUMERIC: //数字  
	                cellValue = String.valueOf(cell.getNumericCellValue());  
	                break;  
	            case Cell.CELL_TYPE_STRING: //字符串  
	                cellValue = String.valueOf(cell.getStringCellValue());  
	                break;  
	            case Cell.CELL_TYPE_BOOLEAN: //Boolean  
	                cellValue = String.valueOf(cell.getBooleanCellValue());  
	                break;  
	            case Cell.CELL_TYPE_FORMULA: //公式  
	                cellValue = String.valueOf(cell.getCellFormula());  
	                break;  
	            case Cell.CELL_TYPE_BLANK: //空值   
	                cellValue = "NULL";  
	                break;  
	            case Cell.CELL_TYPE_ERROR: //故障  
	                cellValue = "非法字符";  
	                break;  
	            default:  
	                cellValue = "未知类型";  
	                break;  
	        }  
	        return cellValue;  
	    }  
	    
	    

	    
}
