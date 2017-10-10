package com.wbdp.bee.controller;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wbdp.bee.entity.BrushcreditEntity;
import com.wbdp.bee.entity.Compre;
import com.wbdp.bee.entity.Wbl_BeeEntity;
import com.wbdp.bee.service.Wbl_BeeService;
import com.wbdp.bee.util.UtilPackaging;


@Controller
public class Bee_Controller {
	

    @Resource
	private Wbl_BeeService BeeService;


    /**
     * 方法名: beeList   
     * 方法描述:  客户所有信息
     * 入参描述:  pageNum:页码
     * 回调描述:   
     * 创建人:wisedata004  
     * 创建时间: 2017年7月10日
     */
	@ResponseBody
   	@RequestMapping(value="/beeList",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView beeList(Long id,Integer pageNum,HttpSession session){
    try {    
    		//保存当前页码
 		   session.setAttribute("pageNow", pageNum);
          return new ModelAndView("customerceshi","data",BeeService.BeeAllList(pageNum,session));
		  } 
     catch (Exception e) {
		 return new ModelAndView("",UtilPackaging.toException(e));	  
		}
    }
	
	/**
	 * 客服删除客户
	 * @param excel
	 * @param request
	 * @param session
	 * @return
	 */
   @ResponseBody
   @RequestMapping(value="/delBee",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   public Map<String,Object> delBee(Long id,HttpSession session){	
	   
	   return BeeService.delBee(id, session);
   }
	
	/**
	 * 用户确认上传集团客户资料(公对公)
	 * @param excel
	 * @param request
	 * @param session
	 * @return
	 */
   @ResponseBody
   @RequestMapping(value="/confirm",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   public Map<String,Object> confirm(String company,HttpSession session){	
	   System.out.println("确认保存集团客户:"+company);
	   return BeeService.uploadClient(company,session);
   }
   	
   /**
	 * 用户确认上传集团客户资料(公对私)
	 * @param excel
	 * @param request
	 * @param session
	 * @return
	 */
	  @ResponseBody
	  @RequestMapping(value="/confirmPrivate",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	  public Map<String,Object> confirmPrivate(String company,HttpSession session){	
		   System.out.println("确认保存集团客户:"+company);
		   return BeeService.uploadClientPrivate(company,session);
	  }
  
  	

	/**
     * 方法名: beeListOfID   
     * 方法描述:  客户所有信息
     * 入参描述:  id:客户id
     * 回调描述:   
     * 创建人:wisedata004  
     * 创建时间: 2017年7月10日
     */
	@ResponseBody
   	@RequestMapping(value="/beeListOfID",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView beeListOfID(Long id){
		try {
			//执行查询
			return new ModelAndView("customerdetilceshi","data",BeeService.BeeAllListOfID(id));
		} catch (Exception e) {
			
			return new ModelAndView("异常页面",UtilPackaging.toException(e));
		}
	}
	/**
	 * 下载一分期客户导入模板
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	 @RequestMapping("/download")
     public String download(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
		 System.out.println(fileName);
		 
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
         String path ="/usr/local/tomcat7/webapps/bee/excel";
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
         return null;
     }  
	 /**
	  * 客户经理下载其名下公拉私客户数据
	  * @param session
	  * @return
	 * @throws IOException 
	  */
	 @ResponseBody
	 @RequestMapping(value="/downLoadBee")
	 public String downLoadBee(Long id,HttpSession session,HttpServletResponse response,HttpServletRequest request) throws IOException{
		 //Long id=Long.parseLong(request.getParameter("id"));
		 response.setCharacterEncoding("utf-8");
         //返回的数据类型
         response.setContentType("application/xlsx");
         System.out.println("获取的ID："+id);
        String fileName  = BeeService.downLoadBee(id, session,response);
        //String fileName = "1505119056619-yifenqiClient.xlsx";
         //响应头
         response.setHeader("Content-Disposition", "attachment;fileName="
                 + fileName);
         //fileName = "1505119056619-yifenqiClient.xlsx";
         InputStream inputStream=null;
        OutputStream outputStream=null;
         ///usr/local/tomcat7/webapps/YiStagingSystem/myjs
         String path ="/usr/local/tomcat7/webapps/bee/excel";
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
         return null;
		// return BeeService.downLoadBee(company, session,response);
	 }
	 /**
	  * 客户管理页面客户经理为空户单独新增套餐
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping(value="/insertBeePackage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	 public Map<String,Object> insertBeePackage(HttpSession session,Integer userType,BrushcreditEntity brushcreditEntity){
		 return BeeService.insertBeePackage(session, userType, brushcreditEntity);
	 }
	 
	 /**
	  * 客户管理页面客户经理新增公对公客户与套餐数据
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping(value="/insertBeeAndPackage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	 public Map<String,Object> insertBeeAndPackage(HttpSession session,Wbl_BeeEntity wbl_BeeEntity,BrushcreditEntity brushcreditEntity){
		 return BeeService.insertBeeAndPackage(session, wbl_BeeEntity, brushcreditEntity);//BeeService.insertBeePackage(session, userType, brushcreditEntity);
	 }
}
