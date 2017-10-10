package com.wbdp.wx.controller.register;

import com.wbdp.wx.constant.HTMLStatic;
import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.model.*;
import com.wbdp.wx.service.register.RegisterService;
import com.wbdp.wx.utils.ResultUtils;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

/**
 * 注册页面控制器
 * Created by wisedata005 on 2017/7/5.
 */
@Controller
public class RegisterController {
    /**注册service*/
    @Autowired
    private RegisterService registerService;

    /**日志log*/
    private static Logger log = Logger.getLogger(RegisterController.class);
    /**
     * 跳转注册页面
     * @param session
     * @param code
     * @return
     */
    @RequestMapping(value = "toregister")
    public String toRegister(HttpSession session, @PathParam("code") String code)throws CustomException {
       return registerService.getOpenIDAndJumpHtml(session,code);
    }

    /**
     * 根据手机发送短信验证码
     * @param session
     * @param phone
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "sentcode",method=RequestMethod.POST)
    @ResponseBody
    public Result sentVerifieCodeByPhone(HttpSession session,@RequestParam(value = "phone",required = true )String phone)throws CustomException{
        log.info("phone:"+phone);
        return registerService.sentVerifieCodeByPhone(session,phone);
    }

    /**
     * 验证短信验证码并且保存数据到数据库
     * @param session
     * @param code
     * @return
     * @throws CustomException
     */
    @RequestMapping(value="/checkcode",method= RequestMethod.POST)
    @ResponseBody
    public Result checkVerifieCode(HttpSession session,@RequestParam("code")String code)throws CustomException{
        return registerService.checkVerifieCode(session,code);
    }
    
    @RequestMapping(value = "/rtest")
    public String  test(){
        return "test";
    }

    /**
     * 跳转用户基本信息页面
     * @return
     */
    @RequestMapping("toUserBaseInfo")
    public String toBaseInfo(){
        return HTMLStatic.APPLY;
    }


    /**
     * 保存基本资料到session
     * @param session
     * @param userBasicInfo
     * @param bindingResult
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "saveUserBaseInfo",method=RequestMethod.POST)
    @ResponseBody
    public  Result saveUserBaseInfo(HttpSession session,@Valid UserBasicInfo userBasicInfo, BindingResult bindingResult)throws CustomException{

        if(bindingResult.hasErrors())
            return ResultUtils.error(-2,bindingResult.getFieldError().getDefaultMessage());

        return registerService.saveUserBaseInfoInSession(session,userBasicInfo);
    }

    /**
     * 跳转常用联系人
     * @return
     */
    @RequestMapping("toContacts")
    public String toUserContacts(){
        return HTMLStatic.CALLER;
    }

    /**
     * 保存常用联系人到session
     * @param userContacts
     * @param session
     * @param bindingResult
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "saveContacts",method=RequestMethod.POST)
    @ResponseBody
    public  Result saveUserContacts(@Valid UserContacts userContacts,HttpSession session,BindingResult bindingResult)throws CustomException{
        if(bindingResult.hasErrors())
            return ResultUtils.error(-2,bindingResult.getFieldError().getDefaultMessage());

        return registerService.saveUserContacts(session,userContacts);
    }

    /**
     * 跳转社保账号
     * @return
     */
    @RequestMapping("toSocial")
    public String toSocialSecurityAccount(){
        return HTMLStatic.SOCIAL;
    }

    /**
     * 保存社保账户到session
     * @param socialSecurityAccount
     * @param session
     * @param bindingResult
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "saveSocialSecurityAccount",method=RequestMethod.POST)
    @ResponseBody
    public  Result saveSocialSecurityAccount(@Valid SocialSecurityAccount socialSecurityAccount, HttpSession session, BindingResult bindingResult)throws CustomException{
        if(bindingResult.hasErrors())
            return ResultUtils.error(-2,bindingResult.getFieldError().getDefaultMessage());

        return registerService.saveSocialSecurityAccount(session,socialSecurityAccount);
    }
    /**
     * 保存车险保单号到session
     * @param insuranceNum
     * @param session
     * @param bindingResult
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "saveInsuranceNum",method=RequestMethod.POST)
    @ResponseBody
    public  Result saveInsuranceNum(@RequestParam("insuranceNum") String insuranceNum, HttpSession session)throws CustomException{

        return registerService.saveInsuranceNum(session,insuranceNum);
    }
    /**
     * 保存信用卡号到session
     * @param userCreditCard
     * @param session
     * @param bindingResult
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "saveUserCreditCard",method=RequestMethod.POST)
    @ResponseBody
    public  Result saveUserCreditCard(@Valid UserCreditCard userCreditCard, HttpSession session)throws CustomException{

        return registerService.saveUserCreditCard(session,userCreditCard);
    }
    /**
     * 保存SSAINCC到session
     * @param userCreditCard
     * @param session
     * @param bindingResult
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "saveSSAINCC",method=RequestMethod.POST)
    @ResponseBody
    public  Result saveSSAINCC(@Valid SocialSecurityAccount socialSecurityAccount,
    		@Valid UserCreditCard userCreditCard,@Valid MyInsurance myInsurance,@RequestParam("isNoOlder")Integer isNoOlder, HttpSession session)throws CustomException{
    	System.out.println("是否宽带老用户："+isNoOlder);
        return registerService.saveSSAINCC(session,socialSecurityAccount,userCreditCard,myInsurance,isNoOlder);
    }
    
    /**
     * 跳转银行卡
     * @return
     */
    @RequestMapping("toBank")
    public String toUserBankCard(){
        return HTMLStatic.BANK;
    }
    /**
     * 保存银行卡并全部保存数据库
     * @param userBankCard
     * @param session
     * @param bindingResult
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "saveBankCard",method=RequestMethod.POST)
    @ResponseBody
    public  Result saveUserBankCard(@Valid UserBankCard userBankCard, HttpSession session, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors())
            return ResultUtils.error(-2,bindingResult.getFieldError().getDefaultMessage());
        return registerService.saveUserBankCard(session,userBankCard);
    }
    
    /**
     * 跳转身份证
     * @return
     */
    @RequestMapping("toCard")
    public String toIDCard(){
        return HTMLStatic.CARD;
    }
    
    /**
     * 保存身份证到session
     * @param oneserverId1
     * @param oneserverId2
     * @return
     */
    @RequestMapping(value = "saveIDCard",method=RequestMethod.POST)
    @ResponseBody
    public  Result saveIDCard(HttpSession session,@RequestParam("oneserverId1") String oneserverId1,
    		@RequestParam("oneserverId2") String oneserverId2,@RequestParam("type") int type) throws CustomException {
        return registerService.saveIDCard(session,oneserverId1,oneserverId2,type);
    }

	/**
	 * 后台获取ticket并且生成签名
	 * @param url
	 */
	@RequestMapping(value="/gettic",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getticket(@RequestParam("url") String url){
		return registerService.getTicketAndCreateWebSignature(url);
	} 
}
