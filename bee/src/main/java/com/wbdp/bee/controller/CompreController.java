package com.wbdp.bee.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wbdp.bee.entity.Compre;
import com.wbdp.bee.service.CompreService;

/**
 * 公司综合数据管理控制器
 * @author 汪赛军
 * date:2017年9月17日上午8:47:56
 *
 */
@Controller
public class CompreController {
	
	@Autowired
	private CompreService compreService;
	
	 /**
     * 方法名: compreList   
     * 方法描述:  客户所有信息
     * 入参描述:  pageNum:页码
     * 回调描述:   
     * 创建人:wisedata004  
     * 创建时间: 2017年7月10日
     */
	@ResponseBody
   	@RequestMapping(value="/compreList",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView compreList(Integer pageNum,HttpSession session){
          return new ModelAndView("compre","data",compreService.compreList(pageNum, session));
    }
	/**
	 * 上传综合数据
	 * @param excel
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/confirmCompre",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Map<String,Object> confirmCompre(String company,HttpSession session){	
		   System.out.println("确认保存综合数据:"+company);
		   return compreService.uploadClientZongHe(company, session);
	}
	/**
	 * 新增综合数据
	 * @param compre
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertCompre",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Map<String,Object> insertCompre(Compre compre,HttpSession session){	
		   //System.out.println("确认保存集团客户:"+company);
		   return compreService.insertCompre(compre, session);//BeeService.uploadClientZongHe(company, session);
	}
	
	
}
