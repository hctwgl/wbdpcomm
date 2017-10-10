package com.wisemifi.wx.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sun.tools.internal.ws.processor.model.Request;
import com.wisemifi.wx.config.controller.ConfigController;
import com.wisemifi.wx.model.UserIDCard;
import com.wisemifi.wx.service.VerifiedService;
import com.wisemifi.wx.service.WiseMIFIService;

/**
 * 维泽mifi公众号实名认证控制器类
 * @author 汪赛军
 * date:2017年8月10日上午9:00:50
 *
 */
@Controller
@RequestMapping(value="/verified")
public class VerifiedController {
	private static Logger log = Logger.getLogger(VerifiedController.class);
	@Autowired
	private VerifiedService verifiedService;
	/**
	 * 获取code与用户openID,成功后跳转至实名认证页面
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/toverified",method=RequestMethod.GET)
	public String getCode(@PathParam("code") String code,HttpSession session){
		log.info("获取的code："+code);
		return verifiedService.getCodeAndopenID(code, session);	
	}
	
	/**
     * 跳转图片页面
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "tosuccess",method=RequestMethod.GET)
    public String tocard() {
        return  "success";
    }
    /**
	 * 保存Img和保存数据库
	 * @param session
	 * @param cashLoan
	 * @return
	 * @throws CustomException
	 */
    @RequestMapping(value = "saveImg",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveImg(HttpSession session,@Valid UserIDCard userIDCard) {
    	log.info("上传图片："+userIDCard.toString());
        return verifiedService.saveImg(session, userIDCard);
    }  
    
    /**
	 * 后台获取ticket并且生成签名
	 * @param url
	 */
	@RequestMapping(value="/gettic",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getticket(@RequestParam("url") String url){
		return verifiedService.getticket(url);
	} 
}
