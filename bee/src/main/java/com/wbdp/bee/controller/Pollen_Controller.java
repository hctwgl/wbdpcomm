package com.wbdp.bee.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;

import com.wbdp.bee.service.Wbl_BeeService;
import com.wbdp.bee.service.Wbl_PollenService;
import com.wbdp.bee.util.UtilPackaging;


@Controller
public class Pollen_Controller {
	
	@Resource
	private Wbl_PollenService PollenService;

	private Logger logger=Logger.getLogger(Pollen_Controller.class);

	/**
	 * 方法名: pollen   
	 * 方法描述: 根据客户id,社保账号,密码计算出花粉信用额度(一个月只能获取一次)
	 * 入参描述: beeid:客户id
	 * 回调描述: {"data":9300,"msg":"","stutus":"SUCCESS"}
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月12日
	 */
	@ResponseBody
   	@RequestMapping(value="/getPollen",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public String getPollen(Long beeid){
		return PollenService.getPollen(beeid);
    }
    
  
	/**
	 * 方法名: getPollenTest   
	 * 方法描述: 后台试算花粉额度(不保存到数据库) 
	 * 入参描述: beeid:客户id
	 * 回调描述: {"data":{"bal":8239,"baseDeposit":2130,"corpName":"维泽数据有限公司","numBer":5,"huafenTotal":9300},"msg":"","stutus":"SUCCESS"}   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月13日
	 */
	@ResponseBody
   	@RequestMapping(value="/getPollenTest",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public String getPollenTest(Long beeid){              	
		return PollenService.getPollenTest(beeid); 
    }
}
