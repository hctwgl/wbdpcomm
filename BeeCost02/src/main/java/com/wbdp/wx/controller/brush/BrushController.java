package com.wbdp.wx.controller.brush;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wbdp.wx.entity.Brushcredit;
import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.service.brush.BrushService;

/**
 * 维泽刷刷控制器类
 * @author 汪赛军
 * date:2017年8月30日上午10:52:52
 *
 */
@Controller
public class BrushController {
	 /**日志log*/
    private static Logger log = Logger.getLogger(BrushController.class);

    /**维泽刷刷业务层*/
    @Autowired
    private BrushService brushService;

    /**
     * 获取用户授权，得到用户openID并保存到session中,保存成功后跳转到填写信用额度页面
     * @param code
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "tobrush",method=RequestMethod.GET)
    public String tobrush(@PathParam("code") String code, HttpSession session){
    	log.info("进入维泽刷刷获取用户授权的code："+code);
        return brushService.getOpenidAndjump(code, session);
    }
    
    /**
     * 从推送链接获取用户授权，得到用户openID并保存到session中,保存成功后跳转到查看数据页面
     * @param code
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "tobrushPush",method=RequestMethod.GET)
    public String tobrushPush(@PathParam("code") String code,@PathParam("time") String time, HttpSession session){
    	log.info("进入维泽刷刷推送获取用户授权的code："+code+":"+"时间:"+time);
        return brushService.getOpenidAndjump(code,time, session);
    }
    /**
     * 任性刷推送页面获取数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getBrushPush",method=RequestMethod.POST)
    public Map<String,Object> getBrushPush(HttpSession session){
    	
    	return brushService.getBrushPush(session);
    }
    /**
     * 发送验证码到业务员
     * @param recomCode 业务员推荐码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sendMessages",method=RequestMethod.POST)
    public Map<String,Object> sendMessages(String recomCode,HttpSession session){
    	return brushService.sendMessage(recomCode, session);
    }
    /**
     * 在维泽任性刷中获取用户额度
     * @param recomCode 业务员推荐码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getBrushPollen",method=RequestMethod.POST)
    public Map<String,Object> getBrushPollen(HttpSession session){
    	return brushService.getBrushPollen(session);
    }
    /**
     * 保存用户填写的业务员推荐码到session中，并发送短信验证码至对应业务员，等待下一步操作
     * @param recomCode 业务员推荐码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveBrushToSession",method=RequestMethod.POST)
    public Map<String,Object> saveBrushToSession(Brushcredit brushcredit,String message,HttpSession session){
    	return brushService.saveBrushToSession(brushcredit, message, session);
    }
    
    /**
     * 跳转到维泽任性刷扫描结果页面
     * @return
     */
    @RequestMapping(value = "toscanresult",method=RequestMethod.GET)
    public String toscanresult(){
    	return "scanresult";
    }
    /**
     * 跳转到维泽任性刷人脸识别页面
     * @return
     */
    @RequestMapping(value = "toface1",method=RequestMethod.GET)
    public String toBrushFace(){
    	return "face1";
    }
    /**
     * 进行人脸识别，成功后返回成功标识
     * @param oneserverId1
     * @return
     */
    @RequestMapping(value = "saveBrushFace",method=RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> saveBrushFace(HttpSession session,@RequestParam("oneserverId1") String oneserverId1
    		) {
        return brushService.faceCheck(session, oneserverId1);
    }
    
    /**
     * 跳转到在线签名页面
     * @return
     */
    @RequestMapping(value = "toBrushSign",method=RequestMethod.GET)
    public String toBrushSign(){
    	return "sign1";
    }
    
    /**
     * 保存在线签名
     * @param onlineSign 在线签名字符串
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveBrushSign",method=RequestMethod.POST)
    public  Map<String,Object> saveBrushSign(HttpSession session,String onlineSign) {
        return brushService.saveBrushSign(session, onlineSign);
    }
    
    /**
     * 跳转到借款合同页面
     * @return
     */
    @RequestMapping(value = "toBrushContract",method=RequestMethod.GET)
    public String toBrushContract(){
    	return "contract";
    }
    /**
     * 借款合同页面获取数据
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getContract",method=RequestMethod.POST)
    public Map<String,Object> getContract(HttpSession session){
    	return brushService.getContract(session);
    }
    
    /**
     * 确认通过维泽任性刷刷取额度购买手机，将最终数据保存至数据库中
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "confirmBrush",method=RequestMethod.POST)
    public Map<String,Object> confirmBrush(HttpSession session,Integer type){
    	return brushService.confirmBrush(session,type);
    }
}
