package com.wbdp.wx.controller.mine;

import java.util.Map;

import com.wbdp.wx.constant.HTMLStatic;
import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.model.*;
import com.wbdp.wx.service.mine.MineSercice;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

/**
 * Created by wisedata005 on 2017/7/5.
 */
@Controller
public class MineController {

    /**日志log*/
    private static Logger log = Logger.getLogger(MineController.class);

    /**我的业务层*/
    @Autowired
    private MineSercice mineSercicel;

    /**
     * 授权跳转
     * @param code
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "tomine",method=RequestMethod.GET)
    public String toMine(@PathParam("code") String code, HttpSession session) throws CustomException {
        return mineSercicel.toMine(code,session);
    }
    /**
     * 跳转用户基本资料
     * @return
     */
    @RequestMapping("toUserInfs")
    public String toUserBankCard(){
        return HTMLStatic.MYAPPLY;
    }

    /**
     * 查询用户信息
     * @param session
     * @return
     */
    @RequestMapping(value = "getUserInfs",method=RequestMethod.GET)
    @ResponseBody
    public Result selectUserInfos(HttpSession session) throws CustomException {
        return mineSercicel.selectUserInfos(session);
    }
    /**
     * 跳转用户维泽任性刷记录页面
     * @return
     */
    @RequestMapping(value = "torecord",method=RequestMethod.GET)
    public String torecord(){
        return "record";
    }
    /**
     * 查询用户维泽任性刷记录
     * @param session
     * @return
     */
    @RequestMapping(value = "getUserBrush",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUserBrush(HttpSession session) throws CustomException {
    	log.info("获取维泽任性刷记录");
        return mineSercicel.getUserBrush(session);
    }
    /**
     * 修改保存用户信息
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "updateUserInfo",method=RequestMethod.POST)
    @ResponseBody
    public Result updateUserInfo(HttpSession session,@Valid UserBasicInfo userBasicInfo) throws CustomException {
        return mineSercicel.updateUserInfo(session,userBasicInfo);
    }

    /**
     * 跳转常用联系人
     * @return
     */
    @RequestMapping("toMyCaller")
    public String toMyCaller(){
        return HTMLStatic.MYCALLER;
    }
    
    /**
     * 查询常联系人
     * @param session
     * @return
     */
    @RequestMapping(value = "getUserContacts",method=RequestMethod.GET)
    @ResponseBody
    public Result selectUserContacts(HttpSession session) throws CustomException {
        return mineSercicel.selectUserContacts(session);
    }

    /**
     * 修改常用联系人
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "updateUserContact",method=RequestMethod.POST)
    @ResponseBody
    public Result updateUserContact(HttpSession session,@Valid UserContacts userContacts) throws CustomException {
        return mineSercicel.updateUserContact(session,userContacts);
    }

    /**
     * 跳转身份证正反面
     * @return
     */
    @RequestMapping("toMyCard")
    public String toMyCard(){
        return HTMLStatic.MYCARD;
    }

    /**
     * 查询用户身份证正反面
     * @param session
     * @return
     */
    @RequestMapping(value = "getUserIDCard",method=RequestMethod.GET)
    @ResponseBody
    public Result selectUserIDCard(HttpSession session) throws CustomException {
        return mineSercicel.selectUserIDCard(session);
    }
    /**
     * 修改用户身份证正反面
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "updateUserIDCard",method=RequestMethod.POST)
    @ResponseBody
    public Result updateUserIDCard(HttpSession session,@Valid UserIDCard userIDCard) throws CustomException {
    	System.out.println("开始修改用户身份证图片");
        return mineSercicel.updateUserIDCard(session,userIDCard);
    }

    /**
     * 跳转社保信息
     * @return
     */
    @RequestMapping("toSSA")
    public String toSSA(){
        return HTMLStatic.MYSOCIAL;
    }

    /**
     * 查询用户社保信息
     * @param session
     * @return
     */
    @RequestMapping(value = "getSSA",method=RequestMethod.GET)
    @ResponseBody
    public Result selectSocialSecurityAccount(HttpSession session) throws Exception {
        return mineSercicel.selectSocialSecurityAccount(session);
    }

    /**
     * 修改用户社保信息
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "updateSSA",method=RequestMethod.POST)
    @ResponseBody
    public Result updateSocialSecurityAccount(HttpSession session,@Valid SocialSecurityAccount socialSecurityAccount) throws Exception {
        return mineSercicel.updateSocialSecurityAccount(session,socialSecurityAccount);
    }

    /**
     * 跳转银行卡
     * @return
     */
    @RequestMapping("toMyBank")
    public String toMyBank(){
        return HTMLStatic.MYBANK;
    }

    /**
     * 查询银行卡信息
     * @param session
     * @return
     */
    @RequestMapping(value = "getBankCard",method=RequestMethod.GET)
    @ResponseBody
    public Result selectBankCard(HttpSession session) throws Exception {
        return mineSercicel.selectBankCard(session);
    }
    /**
     * 修改用户银行卡信息
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "updateBankCard",method=RequestMethod.POST)
    @ResponseBody
    public Result updateBankCard(HttpSession session,@Valid UserBankCard userBankCard) throws Exception {
        return mineSercicel.updateBankCard(session,userBankCard);
    }
    
    /**
     * 获取信用额度
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getPollen",method=RequestMethod.GET)
    @ResponseBody 
    public Result getPollen(HttpSession session) throws Exception {
        return mineSercicel.getPollen(session);
    	
    }

    /**
     * 加入购物车和立即购买前判断是否注册了用户
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "isMRegist",method=RequestMethod.GET)
    @ResponseBody
    public Result isRegist(HttpSession  session)throws CustomException {
        return mineSercicel.isRegist(session);
    }


    /**
     * 跳转订单列表
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "toMOrderList",method=RequestMethod.GET)
    public String toOrderList()throws CustomException {
        return  HTMLStatic.ORDERLIST;
    }
    /**
     * 查询用户订单
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getMineOrders",method=RequestMethod.GET)
    @ResponseBody
    public Result getMineOrders(HttpSession session)throws CustomException {
        return mineSercicel.getMineOrders(session);
    }

    /**
     * 查询用户信用卡
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getMineCC",method=RequestMethod.GET)
    @ResponseBody
    public Result getMineCC(HttpSession session)throws CustomException {
        return mineSercicel.getMineCC(session);
    }

   /**
    * 修改用户信用卡
    * @param session
    * @param userCreditCard
    * @return
    * @throws CustomException
    */
    @RequestMapping(value = "updateMineCC",method=RequestMethod.POST)
    @ResponseBody
    public Result updateMineCC(HttpSession session,@Valid UserCreditCard userCreditCard)throws CustomException {
        return mineSercicel.updateMineCC(session,userCreditCard);
    }
    /**
     * 查询用户车险保险单
     * @param session
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "getMineInsNum",method=RequestMethod.GET)
    @ResponseBody
    public Result getMineInsNum(HttpSession session)throws CustomException {
        return mineSercicel.getMineInsNum(session);
    }
   /**
    * 修改车险保险单
    * @param session
    * @param insuranceNum
    * @return
    * @throws CustomException
    */
     @RequestMapping(value = "updateMineInsNum",method=RequestMethod.POST)
     @ResponseBody
     public Result updateMineInsNum(HttpSession session,@RequestParam("insuranceNum") String insuranceNum)throws CustomException {
         return mineSercicel.updateMineInsNum(session,insuranceNum);
     }
     /**
      * 修改SSAINCC到session
      * @param userCreditCard
      * @param session
      * @param bindingResult
      * @return
     * @throws Exception 
      */
     @RequestMapping(value = "updateSSAINCC",method=RequestMethod.POST)
     @ResponseBody
     public  Result updateSSAINCC(@Valid SocialSecurityAccount socialSecurityAccount,
     		@Valid UserCreditCard userCreditCard,@Valid MyInsurance myInsurance, HttpSession session)throws Exception{

         return mineSercicel.updateSSAINCC(session,socialSecurityAccount,userCreditCard,myInsurance);
     }
     

     /**
      * 跳转订单详情
      * @return
      * @throws CustomException
      */
     @RequestMapping(value = "toOrderListDetail",method=RequestMethod.GET)
     public String toOrderListDetail()throws CustomException {
         return  HTMLStatic.LISTDETAIL;
     }
     
     /**
      * 获取商品订单详情
      * @param session
      * @param oid
      * @return
     * @throws Exception 
      */
     @RequestMapping(value = "getOrderListDetail",method=RequestMethod.GET)
     @ResponseBody
     public Result getOrderListDetail(HttpSession session,@RequestParam("oid") int oid)throws Exception {
         return mineSercicel.getOrderListDetail(session,oid);
     }
     
     /**
      * 跳转二维码页面
      * @return
      * @throws CustomException
      */
     @RequestMapping(value = "toORCode",method=RequestMethod.GET)
     public String toORCode(){
         return  "mycode";
     }
     /**
      * 跳转二维码详情
      * @return
      * @throws CustomException
      */
     @RequestMapping(value = "toORCodeDetil",method=RequestMethod.GET)
     public String toORCodeDetil(){
         return  "codedetail";
     }
     /**
      * 二维码页面获取数据
      * @param session
      * @return
      */
      @RequestMapping(value = "getQRCode",method=RequestMethod.POST)
      @ResponseBody
      public  Map<String,Object> getQRCode(HttpSession session){
     	 
          return mineSercicel.getQRCode(session);
      }
      /**
       * 跳转新增二维码页面
       * @return
       * @throws CustomException
       */
      @RequestMapping(value = "toaddcode",method=RequestMethod.GET)
      public String toaddcode(){
          return  "addcode";
      }
    /**
     * 生成二维码
     * @param pacPeriods 期数
     * @param pacMonthlyPrice 每月应付金额
     * @return
     */
     @RequestMapping(value = "createQRCode",method=RequestMethod.POST)
     @ResponseBody
     public  Map<String,Object> createQRCode(Integer pacPeriods,Integer pacMonthlyPrice,HttpSession session){
    	 
         return mineSercicel.creatQRCode(pacPeriods, pacMonthlyPrice, session);
     }
     
     /**
      * 删除二维码数据
      * @param session
      * @return
      */
      @RequestMapping(value = "delQRCode",method=RequestMethod.POST)
      @ResponseBody
      public  Map<String,Object> delQRCode(HttpSession session,Long id){
     	 
          return mineSercicel.delQRCode(session,id);
      }
}
