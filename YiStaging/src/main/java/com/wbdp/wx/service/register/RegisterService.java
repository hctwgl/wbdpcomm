package com.wbdp.wx.service.register;

import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.model.*;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

/**
 * 注册Service接口
 * Created by wisedata005 on 2017/7/5.
 */
public interface RegisterService {
    /**
     * 获取用户opendid并且跳转页面
     * @param session
     * @param code
     * @return
     */
    String getOpenIDAndJumpHtml(HttpSession session, String code) throws CustomException;

    /**
     * 发送短信验证码
     * @param session
     * @param phone
     * @return
     */
    Result sentVerifieCodeByPhone(HttpSession session, String phone) throws CustomException;

    /**
     * 验证短信验证码并保存数据库
     * @param session
     * @param code
     * @return
     */
    Result checkVerifieCode(HttpSession session, String code) throws CustomException;

    /**
     * 保存基本资料到session
     * @param session
     * @param userBasicInfo
     * @return
     */
    Result saveUserBaseInfoInSession(HttpSession session, UserBasicInfo userBasicInfo) throws CustomException;

    /**
     * 保存常用联系人到session
     * @param session
     * @param userContacts
     * @return
     */
    Result saveUserContacts(HttpSession session, UserContacts userContacts) throws CustomException;

    /**
     * 保存社保账户到session
     * @param session
     * @param socialSecurityAccount
     * @return
     */
    Result saveSocialSecurityAccount(HttpSession session, SocialSecurityAccount socialSecurityAccount) throws CustomException;

    /**
     * 保存银行卡到session
     * @param session
     * @param userBankCard
     * @return
     */
    Result saveUserBankCard(HttpSession session, UserBankCard userBankCard) throws Exception;

    /**
     * 保存身份证到session
     * @param session
     * @param oneserverId1
     * @param oneserverId2
     * @param oneserverId3 
     * @param type 
     * @return
     */
    Result saveIDCard(HttpSession session, String oneserverId1, String oneserverId2,int type) throws CustomException;
    /**
     * 后台获取ticket并且生成签名
     * @param url
     * @return
     */
    JSONObject getTicketAndCreateWebSignature(String url);
    /**
     * 保存社车险保单号到session
     * @param session
     * @param insuranceNum
     * @return
     */
	Result saveInsuranceNum(HttpSession session, String insuranceNum);
	/**
	 * 保存信用卡号到session
	 * @param session
	 * @param userCreditCard
	 * @return
	 * @throws CustomException 
	 */
	Result saveUserCreditCard(HttpSession session, UserCreditCard userCreditCard) throws CustomException;
	/**
	 * 保存SSAINCC到session
	 * @param session
	 * @param ssaincc
	 * @return
	 * @throws CustomException 
	 */
	Result saveSSAINCC(HttpSession session, SSAINCC ssaincc) throws CustomException;
	/**
	 * 
	 * @param session
	 * @param socialSecurityAccount
	 * @param userCreditCard
	 * @param userCreditCard2
	 * @return
	 * @throws CustomException 
	 */
	Result saveSSAINCC(HttpSession session,
			SocialSecurityAccount socialSecurityAccount,
			UserCreditCard userCreditCard, MyInsurance myInsurance,Integer isNoOlder) throws CustomException;
}
