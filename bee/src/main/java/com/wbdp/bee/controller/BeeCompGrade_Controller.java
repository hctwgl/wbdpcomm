package com.wbdp.bee.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wbdp.bee.entity.BeecompgradeNewEntity;
import com.wbdp.bee.entity.Wbl_BeecompgradeEntity;
import com.wbdp.bee.entity.Wbl_UserEntity;
import com.wbdp.bee.service.BeeCompanyNewService;
import com.wbdp.bee.service.Wbl_BeeCompGradeService;
import com.wbdp.bee.util.UtilURL;

/**
 * 公司信用评级控制器类
 * @author 汪赛军
 * date:2017年7月18日下午4:46:51
 *
 */
@Controller
public class BeeCompGrade_Controller {
	@Autowired
	private Wbl_BeeCompGradeService wbl_BeeCompGradeService;
	@Autowired
	private BeeCompanyNewService beeCompanyNewService;
	/**
	 * 获取公司评级数据列表
	 * @param id
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/listBeeCompGrade",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView listBeeCompGrade(Integer pageNum,HttpSession session){
		return new ModelAndView("beecompgrade",wbl_BeeCompGradeService.listBeeCompGrade(pageNum, session));
    }
	/**
	 * 增加、修改公司评级数据
	 * @param id
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/insertAndUpdateBeeCompGrade",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Integer insertAndUpdateBeeCompGrade(Wbl_BeecompgradeEntity wbl_BeecompgradeEntity,Integer type){
		Integer result = 0;
		switch (type) {
		case 1:
			result = wbl_BeeCompGradeService.insertBeeCompGrade(wbl_BeecompgradeEntity);
			break;
		case 2:
			result = wbl_BeeCompGradeService.updateBeeCompGrade(wbl_BeecompgradeEntity);
			break;
		default:
			break;
		}
		return result;
    }
	
	
	
	
	/**
	 * 获取公司数据列表
	 * @param id
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/listBeeCompany",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView listBeeCompany(Integer pageNum,HttpSession session){
		return new ModelAndView("beecompgradenew",beeCompanyNewService.selectBeeCompany(session, pageNum));
    }
	/**
	 * 新增公司数据
	 * @param beecompgradeNewEntity
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/insertCompany",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Map<String,Object> userInsertAndUpdate(BeecompgradeNewEntity beecompgradeNewEntity,HttpSession session){
		return beeCompanyNewService.insertBeeCompany(session, beecompgradeNewEntity);
	}
	/**
	 * 拉黑该公司
	 * @param id
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/blackBeeComp",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Integer blackBeeComp(BeecompgradeNewEntity beecompgradeNewEntity,HttpSession session){
		System.out.println("拉黑公司："+beecompgradeNewEntity.getBlackReason());
		return beeCompanyNewService.blackBeeComp(beecompgradeNewEntity, session);
    }
	/*
	 * 上传执照图片@RequestParam(value = "image", required = false)
	 */
   @ResponseBody
   @RequestMapping(value="/imgUpload",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
   public String picUpload(@RequestParam(value = "business", required = false)MultipartFile file,HttpServletRequest request){	   
	  String result=null;
	  Map<String,Object> outMap = new HashMap<String, Object>();
	  //获取上传到服务器的路径
	  String timestamp = String.valueOf(System.currentTimeMillis());
	  //获取项目根目录路径
	  String sysPath = request.getSession().getServletContext().getRealPath("/company");
	  //String path="/usr/local/tomcat7/webapps/yfq/company"; //request.getSession().getServletContext().getRealPath("down/goods/");
	  //System.out.println("图片保存路径："+path);
	  //重命名文件
	  String newFileName=String.valueOf(System.currentTimeMillis())+"-"+file.getOriginalFilename();
	 // System.out.println("图片保存文件名："+newFileName);
	  //创建文件
	  File targetFile = new File(sysPath, newFileName);  
        try {
        	 if(!targetFile.exists()){  
 	            targetFile.mkdirs();  
 	       //保存文件
			file.transferTo(targetFile);
		   //获取保存后的文件绝对路径	
			result="http://www.wisedp.com/yfq/company"+"/"+newFileName;
        	System.out.println("营业执照保存后路径:"+result);
        	 }
        	 return result;
		} catch (IllegalStateException e) {
			return null;
		} catch (IOException e) {
       	 	return null;
		}  
    }
   /*
	 * 上传合同图片@RequestParam(value = "image", required = false)
	 */
  @ResponseBody
  @RequestMapping(value="/conUpload",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
  public String conUpload(@RequestParam(value = "image", required = false)MultipartFile file,HttpServletRequest request){	   
	  String result=null;
	  Map<String,Object> outMap = new HashMap<String, Object>();
	  //获取上传到服务器的路径
	  String timestamp = String.valueOf(System.currentTimeMillis());
	  //获取项目根目录路径
	  String sysPath = request.getSession().getServletContext().getRealPath("/company");
	 // System.out.println("获取的服务器路径："+sysPath);
	 // String path="/usr/local/tomcat7/webapps/yfq/company"; //request.getSession().getServletContext().getRealPath("down/goods/");
	  //System.out.println("图片保存路径："+path);
	  //重命名文件
	  String newFileName=String.valueOf(System.currentTimeMillis())+"-"+file.getOriginalFilename();
	  //System.out.println("图片保存文件名："+newFileName);
	  //创建文件
	  File targetFile = new File(sysPath, newFileName);  
       try {
       	 if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	       //保存文件
			file.transferTo(targetFile);
		   //获取保存后的文件绝对路径	
			result="http://www.wisedp.com/yfq/company"+"/"+newFileName;
       	System.out.println("合同保存后路径:"+result);
       	 }
       	 return result;
		} catch (IllegalStateException e) {
			return null;
		} catch (IOException e) {
      	 	return null;
		}  
   }
  
  /**
	 * 获取公司黑名单数据列表
	 * @param id
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
 	@RequestMapping(value="/listBeeCompanyBlack",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
 	public ModelAndView listBeeCompanyBlack(Integer pageNum,HttpSession session){
		return new ModelAndView("beeCompanyBlackNew",beeCompanyNewService.selectBeeCompanyBlack(session, pageNum));
  }
  /**
	 * 将公司从黑名单清除
	 * @param id
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
 	@RequestMapping(value="/outBlackBeeComp",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
 	public Integer outBlackBeeComp(Long id,HttpSession session){
		System.out.println("公司id："+id);
		return beeCompanyNewService.outBlackCompany(id);
  }
	 /**
		 * 修改公司评级
		 * @param id
		 * @param pageNum
		 * @param session
		 * @return
		 */
		@ResponseBody
	 	@RequestMapping(value="/updateBeeCompGrade",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	 	public Map<String, Object> updateBeeCompGrade(HttpSession session,BeecompgradeNewEntity beecompgradeNewEntity){
			System.out.println("公司id："+beecompgradeNewEntity.getId());
			return beeCompanyNewService.updateCompanyGrade(session, beecompgradeNewEntity);
	  }
	

	 /**
		 * 获取公司列表
		 * @param id
		 * @param pageNum
		 * @param session
		 * @return
		 */
		@ResponseBody
	 	@RequestMapping(value="/listCompany",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	 	public Map<String, Object> listCompany(HttpSession session){
			//System.out.println("公司id："+beecompgradeNewEntity.getId());
			return beeCompanyNewService.listCompany(session);
	  }
		
	/**
	 * 客服审核公司信用并评级
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/reviewCompany",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public ModelAndView reviewCompany(Long id,HttpSession session){
		
		return new ModelAndView("companyreview",beeCompanyNewService.reviewCompany(id, session));
	}
	/**
	 * 客户管理员确认公司信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/comfirmCompany",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public ModelAndView comfirmCompany(Long id,HttpSession session){
		
		return new ModelAndView("companycomfirm",beeCompanyNewService.reviewCompany(id, session));
	}
	
	/**
	 * 查看公司详情
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/companyDetail",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public ModelAndView companyDetail(Long id,HttpSession session){
		
		return new ModelAndView("companydetail",beeCompanyNewService.reviewCompany(id, session));
	}
	/**
	 * 客服审核公司完毕
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/companyYseOrNo",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Integer companyYseOrNo(BeecompgradeNewEntity beecompgradeNewEntity,HttpSession session){
		return beeCompanyNewService.companyYseOrNo(beecompgradeNewEntity, session);
	}
	
	/**
	 * 客服审核未通过时，让客户经理修改公司资料
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/toupdateCompany",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public ModelAndView toupdateCompany(Long id,HttpSession session){
		
		return new ModelAndView("updatecompany",beeCompanyNewService.reviewCompany(id, session));
	}
	
	/**
	 * 修改公司数据
	 * @param beecompgradeNewEntity
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/updateCompany",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Map<String,Object> updateCompany(BeecompgradeNewEntity beecompgradeNewEntity,HttpSession session){
		return beeCompanyNewService.updateBeeCompany(session, beecompgradeNewEntity);
	}
}
