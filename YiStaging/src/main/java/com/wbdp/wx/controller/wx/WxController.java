package com.wbdp.wx.controller.wx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.wbdp.wx.service.wx.WXMeauServer;
import com.wbdp.wx.utils.wx.CheckUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wisedata005 on 2017/7/5.
 */
@Controller
public class WxController {
    /**日志log*/
    private static Logger log = Logger.getLogger(WxController.class);

    /**微信加密签名，signature结合了开发者填写的
     * token参数和请求中的timestamp参数、nonce参数。*/
    private String signature;
    /**时间戳*/
    private String timestamp;
    /**随机数*/
    private String nonce;
    /**随机字符串*/
    private String echostr;

    @Autowired
    private WXMeauServer meauServer;
    
    /**
     * 成为开发者
     * @return
     */
    @RequestMapping(value = "todeveloper",method= RequestMethod.GET)
    @ResponseBody
    public  String beDeveloperByGet(HttpServletRequest request){
        signature = request.getParameter("signature");
        timestamp = request.getParameter("timestamp");
        nonce = request.getParameter("nonce");
        echostr = request.getParameter("echostr");
        String result = null;
        if ("".equals(signature)||signature==null){
            log.info("signature为空，校验失败");
        }else {
            boolean checkSignature = CheckUtil.checkSignature(
                    signature,timestamp, nonce);
            if (checkSignature){
                log.info("校验成功，成功接入");
                result = echostr;
            }
        }
        return result;
    }
    /**
	 * 推送信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value="/todeveloper",method=RequestMethod.POST)
	public void todeveloperPost(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
			String meauType = meauServer.MeauType(request);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(meauType);
		}catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
