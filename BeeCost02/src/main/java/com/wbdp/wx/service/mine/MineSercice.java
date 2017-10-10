package com.wbdp.wx.service.mine;

import java.util.Map;

import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.model.*;

import javax.servlet.http.HttpSession;

/**
 * Created by wisedata005 on 2017/7/5.
 */
public interface MineSercice {

    /**
     * 跳转我的页面
     * @param code
     * @param session
     * @return
     */
    String toMine(String code, HttpSession session) throws CustomException;

    /**
     * 获取用户基本信息
     * @param session
     * @return
     */
    Result selectUserInfos(HttpSession session) throws CustomException;

    /**
     * 修改用户信息
     * @param session
     * @param userBasicInfo
     * @return
     */
    Result updateUserInfo(HttpSession session, UserBasicInfo userBasicInfo) throws CustomException;

    /**
     * 查看常联系人
     * @param session
     * @return
     */
    Result selectUserContacts(HttpSession session) throws CustomException;

    /**
     * 修改常联系人
     * @param session
     * @param userContacts
     * @return
     */
    Result updateUserContact(HttpSession session, UserContacts userContacts) throws CustomException;

    /**
     * 查看用户身份证正反面图片
     * @param session
     * @return
     */
    Result selectUserIDCard(HttpSession session) throws CustomException;

    /**
     * 修改用户身份证正反面
     * @param session
     * @param userIDCard
     * @return
     */
    Result updateUserIDCard(HttpSession session, UserIDCard userIDCard) throws CustomException;

    /**
     * 查询用户社保信息
     * @param session
     * @return
     */
    Result selectSocialSecurityAccount(HttpSession session) throws Exception;

    /**
     * 修改用户社保信息
     * @param session
     * @param socialSecurityAccount
     * @return
     */
    Result updateSocialSecurityAccount(HttpSession session, SocialSecurityAccount socialSecurityAccount) throws Exception;

    /**
     * 获取用户银行卡信息
     * @param session
     * @return
     */
    Result selectBankCard(HttpSession session) throws CustomException;

    /**
     * 修改用户银行卡信息
     * @param session
     * @param userBankCard
     * @return
     */
    Result updateBankCard(HttpSession session, UserBankCard userBankCard) throws CustomException;
    /**
     * 获取信用额度
     * @param session
     * @return
     * @throws CustomException 
     */
	Result getPollen(HttpSession session) throws CustomException;
	/**
	 * 判断是否注册
	 * @param session
	 * @return
	 */
	Result isRegist(HttpSession session);
	/**
	 * 订单列表
	 * @param session
	 * @return
	 * @throws CustomException
	 */
	Result getMineOrders(HttpSession session) throws CustomException;
	/**
	 * 查询用户信用卡
	 * @param session
	 * @return
	 * @throws CustomException 
	 */
	Result getMineCC(HttpSession session) throws CustomException;
	/**
	 * 修改用户信用卡
	 * @param session
	 * @param userCreditCard
	 * @return
	 * @throws CustomException 
	 */
	Result updateMineCC(HttpSession session, UserCreditCard userCreditCard) throws CustomException;
	/**
	 * 修改车险保险单
	 * @param session
	 * @param insuranceNum
	 * @return
	 * @throws CustomException 
	 */
	Result updateMineInsNum(HttpSession session, String insuranceNum) throws CustomException;
	/**
	 * 查询用户车险保险单
	 * @param session
	 * @return
	 * @throws CustomException
	 */
	Result getMineInsNum(HttpSession session) throws CustomException;
	/**
	 * 
	 * @param session
	 * @param socialSecurityAccount
	 * @param userCreditCard
	 * @param myInsurance
	 * @return
	 * @throws CustomException 
	 * @throws Exception 
	 */
	Result updateSSAINCC(HttpSession session,
			SocialSecurityAccount socialSecurityAccount,
			UserCreditCard userCreditCard, MyInsurance myInsurance) throws CustomException, Exception;
	/**
	 * 获取商品订单详情
	 * @param session
	 * @param oid
	 * @return
	 */
	Result getOrderListDetail(HttpSession session, int oid)throws CustomException, Exception;
	
	/**
	 * 获取用户任性刷记录
	 * @param session
	 * @return
	 */
	public Map<String,Object> getUserBrush(HttpSession session);
	
	/**
	 * 生成二维码
	 * @param pacPeriods 期数
	 * @param pacMonthlyPrice 每月应付金额
	 * @return
	 */
	public Map<String,Object> creatQRCode(Integer pacPeriods,Integer pacMonthlyPrice,HttpSession session);
	
	/**
	 * 二维码页面获取数据
	 * @param session
	 * @return
	 */
	public  Map<String,Object> getQRCode(HttpSession session);
	
	/**
	 * 删除二维码数据
	 * @param session
	 * @param id
	 * @return
	 */
	public  Map<String,Object> delQRCode(HttpSession session,Long id);
}
