package com.wbdp.bee.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.wbdp.bee.service.Wbl_GoodsService;
import com.wbdp.bee.util.UtilURL;

@Controller
public class Goods_Controller {
	@Autowired
	private Wbl_GoodsService wbl_GoodsService;
	//新增商品，存入sku表中的属性值串默认以品牌、型号、颜色、内存、运营商、价格的顺序存入
	@ResponseBody  
   	@RequestMapping(value="/goodsadd",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Map<String,Object> goodsadd(@RequestBody String json,HttpSession session){
		
		System.out.println(json);
		return wbl_GoodsService.addGoods(json, session);
    }
	
	//处理图片上传
   	@RequestMapping(value="/upload",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
   	public Map<String,Object> upload(@RequestParam(value = "image", required = false) MultipartFile file,HttpServletRequest request){
   		System.out.println("进入上传图片控制器");
   		String goodsID=request.getParameter("goodsID");
   		System.out.println("商品id："+goodsID);
   		String path = request.getSession().getServletContext().getRealPath("upload");
   		System.out.println("路径："+path);
   		String fileName = file.getOriginalFilename();  
   		System.out.println("文件名："+fileName);
		return null;
    }
   	/*
	 * 上传图片
	 */
   @ResponseBody
   @RequestMapping(value="/picUpload",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
   public String picUpload(@RequestParam(value = "image", required = false)MultipartFile file,HttpServletRequest request){	   
	  String result=null;
	  Map<String,Object> outMap = new HashMap<String, Object>();
	  //获取上传到服务器的路径
	  String path=request.getSession().getServletContext().getRealPath("down/goods/");
	  System.out.println("图片保存路径："+path);
	  //重命名文件
	  String newFileName=String.valueOf(System.currentTimeMillis())+file.getOriginalFilename();
	  System.out.println("图片保存文件名："+newFileName);
	  //创建文件
	  File targetFile = new File(path, newFileName);  
        try {
        	 if(!targetFile.exists()){  
 	            targetFile.mkdirs();  
 	       //保存文件
			file.transferTo(targetFile);
		   //获取保存后的文件绝对路径	
			result=UtilURL.JIANG_WORK+"/down/goods/"+newFileName;
        	System.out.println("保存后路径:"+result);
        	 }
        	 return result;
		} catch (IllegalStateException e) {
			return null;
		} catch (IOException e) {
       	 	return null;
		}  
    }
	//获取商品列表
	@ResponseBody
   	@RequestMapping(value="/goodslist",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView goodslist(Integer pageNum,HttpSession session){
		try {
			//保存当前页码
			session.setAttribute("pageNow", pageNum);
			return new ModelAndView("goodsceshi",wbl_GoodsService.goodsList(pageNum, session));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	//修改上下架状态
		@ResponseBody
	   	@RequestMapping(value="/updateStatus",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	   	public Map<String,Object> updateStatus(@RequestBody String json){	
				System.out.println(json);
				return wbl_GoodsService.updateStatus(json);
		
	    }
}
