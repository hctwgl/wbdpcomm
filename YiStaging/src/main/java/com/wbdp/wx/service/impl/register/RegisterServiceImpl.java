package com.wbdp.wx.service.impl.register;

import com.alibaba.fastjson.JSON;
import com.wbdp.wx.constant.HTMLStatic;
import com.wbdp.wx.domain.RegisterM;
import com.wbdp.wx.enums.ResultEnum;
import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.mapper.*;
import com.wbdp.wx.model.*;
import com.wbdp.wx.service.impl.BaseServiceImpl;
import com.wbdp.wx.service.register.RegisterService;
import com.wbdp.wx.utils.Dowload.DowloadWXImgUtils;
import com.wbdp.wx.utils.APIBankCard;
import com.wbdp.wx.utils.ResultUtils;
import com.wbdp.wx.utils.encryption.EncryptionUtils;
import com.wbdp.wx.utils.http.ZhimaCreditAntifraudScore;
import com.wbdp.wx.utils.phone.SentVerifieCodeUtils;
import com.wbdp.wx.utils.wx.AuthorityUtil;
import com.wbdp.wx.utils.wx.JSAPIUitl;
import com.wbdp.wx.utils.wx.WXCacheUnit;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册服务实例接口
 * Created by wisedata005 on 2017/7/5.
 */
@Service
public class RegisterServiceImpl extends BaseServiceImpl implements RegisterService
{
    /**REGISTERM存seesion的字段*/
    private static final String REGISTERM = "RegisterM";
    /**USERBASICINFO存seesion的字段*/
    private static final String USERBASICINFO = "UserBasicInfo";
    /**USERCONTACTS存seesion的字段*/
    private static final String USERCONTACTS = "UserContacts";
    /**SOCIALSECURITYACCOUNT存seesion的字段*/
    private static final String SOCIALSECURITYACCOUNT = "SocialSecurityAccount";
    /**USERBANKCARD存seesion的字段*/
    private static final String USERBANKCARD = "UserBankCard";
    /**USERIDCARD存seesion的字段*/
    private static final String USERIDCARD = "UserIDCard";
    /**USERCREDITCARD存seesion的字段*/
    private static final String USERCREDITCARD = "UserCreditCard";
    /**创建人*/
    private static final String CREATBY = "微信管理员";

    /**日志log*/
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);


    @Autowired
    private BeeMapper registerBeeMapper;
    @Autowired
    private BeeAddressMapper registBeeAddress;
    @Autowired
    private LinkManMapper registLinkMan;
    @Autowired
    private SocialdataMapper registerSocialdataMapper;
    @Autowired
    private BankCardMapper registerBankCardMapper;
    @Autowired
    private ReceiptAddressMapper registerReceiptAddressMapper;
    @Autowired
    private CreditCardMapper registerCreditCardMapper;

    /**
     * 获取微信用户id和跳转页面
     * @param session
     * @param code
     * @return
     * @throws CustomException
     */
    @Override
    public String getOpenIDAndJumpHtml(HttpSession session, String code) throws CustomException {
        if (code.equals(null)||code.equals(""))
            throw new CustomException(ResultEnum.NOCODE);
        String id = session.getId();
        RegisterM registerM = (RegisterM)session.getAttribute(id+REGISTERM);
        String result = "";

        //实例当前session中的注册用例
        if (registerM==null){
            // 创建实体
            registerM = new RegisterM();
            registerM.setCode(code);
        }
        //判断是否已经获取到用户微信id
        if (registerM.getOpenid()==null||registerM.getOpenid().equals("")){
             result = jumpHTMLByCodeAndSessionAndRegisterM(registerM,id, code, session);
            return result;
        }
        //刷新页面
        if (registerM.getCode().equals(code)){
            return HTMLStatic.REGISTER;
        }

        //返回链接code改变
        if (!registerM.getCode().equals("")&&!registerM.getCode().equals(code)){
            result = jumpHTMLByCodeAndSessionAndRegisterM(registerM,id, code, session);
            return result;
        }

        log.error("授权获取openID出错");

        return result;
    }

    /**
     * 发送短信验证码
     * @param session
     * @param phone
     * @return
     */
    @Override
    public Result sentVerifieCodeByPhone(HttpSession session, String phone) throws CustomException {
        if (phone==null||phone.equals(""))
            throw new CustomException(ResultEnum.EMPTPAHONE);

        String id = session.getId();
        RegisterM registerM = getRegisterMBySession(id, session);
        JSONObject sentJSON = SentVerifieCodeUtils.sentMessage(phone);
        //判断发送成功否
        if(sentJSON.get("statuscode").equals("200")){
            registerM.setVerifieCode(sentJSON.getString("code"));
//            registerM.setVerifieCode("123456");
            log.info("sentJSON.getString(code):"+sentJSON.getString("code"));
            registerM.setPhone(phone);
            session.setAttribute(id+REGISTERM,registerM);
            log.info("短信发送成功");
            log.info(registerM.toString());
            return ResultUtils.success();
        }else
            return ResultUtils.error(ResultEnum.SENTMESSAGE_ERROR);

    }

    /**
     * 验证短信验证码并保存数据库
     * @param session
     * @param code
     * @return
     */
    @Override
    public Result checkVerifieCode(HttpSession session, String code) throws CustomException {
        if (code.equals("")||code.equals(null))
            throw new CustomException(ResultEnum.EMPTVERIFIECODE);

        String id = session.getId();
        RegisterM registerM = getRegisterMBySession(id, session);
        log.info("开始验证注册验证码："+registerM.toString());
        String openid = registerM.getOpenid();
        log.info("一分期用户注册上传验证码："+code);
        if (openid.equals("")||openid.equals(null))
            throw  new CustomException(ResultEnum.EMPTVOPPENDID);
        String phone = registerM.getPhone();
        if (phone.equals("")||phone.equals(null))
            throw  new CustomException(ResultEnum.EMPTPAHONE);
        //验证 短信验证码
        String verifieCode = registerM.getVerifieCode();
        log.info("用户收到的验证码："+verifieCode);
        if(!code.equals(verifieCode))
            throw  new CustomException(ResultEnum.ERRORVERIFIECODE);
        Object beeid = registerBeeMapper.selectIDByPhoneAndWXID(phone, openid);
        
        if(beeid==null){
        	
            //插入数据库
            int i = registerBeeMapper.insertPhoneAndWXID(phone, openid);
            if (i!=0)
                 return ResultUtils.success();
            else
                return ResultUtils.error(-2,"注册失败");
        }else{
            return ResultUtils.success();
        }
    }

    /**
     * 保存用户基本资料到session
     * @param session
     * @param userBasicInfo
     * @return
     */
    @Override
    public Result saveUserBaseInfoInSession(HttpSession session, UserBasicInfo userBasicInfo) throws CustomException {
        if (userBasicInfo.equals(null)||userBasicInfo.equals(""))
            throw new CustomException(ResultEnum.EMTYUSERBASICINFO);
        log.info("userBasicInfo:"+userBasicInfo.toString());
        String name = userBasicInfo.getName();
        if (name.equals("")||name.equals(null))
            throw new CustomException(ResultEnum.EMTNAME);
        //long gender = userBasicInfo.getGender();
        /*if (0>gender&&gender>1)
            throw new CustomException(ResultEnum.EMTGENDER);*/
        String idNum = userBasicInfo.getIDNum();
        if (idNum.equals("")||idNum.equals(null))
            throw new CustomException(ResultEnum.EMTYTIDNUM);
        /*long maritalStatus = userBasicInfo.getMaritalStatus();
        if (0>maritalStatus&&maritalStatus>1)
            throw new CustomException(ResultEnum.EMTYMARITALSTATUS);*/
        String companyName = userBasicInfo.getCompanyName();
        if (companyName.equals("")||companyName.equals(null))
            throw new CustomException(ResultEnum.EMTYCOMPANYNAME);
        String companyAddress = userBasicInfo.getCompanyAddress();
        if (companyAddress.equals("")||companyAddress.equals(null))
            throw new CustomException(ResultEnum.EMTYCOMPANYADDRESS);
        /*String homeAddress = userBasicInfo.getHomeAddress();*/
        /*if (homeAddress.equals("")||homeAddress.equals(null))
            throw new CustomException(ResultEnum.EMTYHOMEADDRESS);*/
       /* String goodsAddress = userBasicInfo.getGoodsAddress();
        if (goodsAddress.equals("")||goodsAddress.equals(null))
            throw new CustomException(ResultEnum.EMTGOODSADDRESS);*/
        /*String education = userBasicInfo.getEducation();
        if (education.equals("")||education.equals(null))
            throw new CustomException(ResultEnum.EMTEDUCATION);*/
        String companyProvince = userBasicInfo.getCompanyProvince();
        if (companyProvince.equals("")||companyProvince.equals(null))
            throw new CustomException(ResultEnum.EMTYCOMPANYPROVINCE);
        String companyCity = userBasicInfo.getCompanyCity();
        if (companyCity.equals("")||companyCity.equals(null))
            throw new CustomException(ResultEnum.EMTYCOMPANYCITY);
        String companyArea = userBasicInfo.getCompanyArea();
        if (companyArea.equals("")||companyArea.equals(null))
            throw new CustomException(ResultEnum.EMTYCOMPANYAREA);
        /*String homeProvince = userBasicInfo.getHomeProvince();
        if (homeProvince.equals("")||homeProvince.equals(null))
            throw new CustomException(ResultEnum.EMTYHOMEPROVINCE);
        String homeCity = userBasicInfo.getHomeCity();
        if (homeCity.equals("")||homeCity.equals(null))
            throw new CustomException(ResultEnum.EMTYHOMECITY);
        String homeArea = userBasicInfo.getHomeArea();
        if (homeArea.equals("")||homeArea.equals(null))
            throw new CustomException(ResultEnum.EMTYHOMEAREA);*/
        /*String goodsProvince = userBasicInfo.getGoodsProvince();
        if (goodsProvince.equals("")||goodsProvince.equals(null))
            throw new CustomException(ResultEnum.EMTYGOODSPROVINCE);
        String goodsCity = userBasicInfo.getGoodsCity();
        if (goodsCity.equals("")||goodsCity.equals(null))
            throw new CustomException(ResultEnum.EMTYGOODSCITY);
        String goodsArea = userBasicInfo.getGoodsArea();
        if (goodsArea.equals("")||goodsArea.equals(null))
            throw new CustomException(ResultEnum.EMTYGOODSAREA);*/
       
      //进行身份证与姓名的验证
        String result = ZhimaCreditAntifraudScore.ZhimaCreditAntifraudScoreGet(userBasicInfo.getIDNum(), userBasicInfo.getName());
        log.info("身份证与姓名的验证："+result);
        JSONObject obj = JSONObject.fromObject(result);
        if("true".equals(obj.getString("success"))){
        	String score = obj.getString("score");
            //score不等于100时，代表该用户姓名与身份证信息不匹配
            if("100".equals(score)){
            	log.info("用户姓名与身份证信息匹配成功");
            	 String id = session.getId();
            	 Integer count = registerBeeMapper.selectBeeByCompany(userBasicInfo.getCompanyName());
            	 log.info("查询的公司数量："+count);
            	 if(count>0){//大于0说明该公司已存在，该客户为公拉私客户
            		 userBasicInfo.setBeeType(2);
            	 }else{//小于0或等于0则说明该客户为其他客户
            		 userBasicInfo.setBeeType(0);
            	 }
                 session.setAttribute(id+USERBASICINFO,userBasicInfo);
                 return ResultUtils.success();
            }else{
            	log.info("用户姓名与身份证信息不匹配");
            	return ResultUtils.error("用户姓名与身份证信息不匹配");
            }
        }else{
        	log.info("身份证号码不存在");
        	return ResultUtils.error("身份证号码不存在");
        }
        
        
       
    }

    /**
     * 保存常用联系人到session
     * @param session
     * @param userContacts
     * @return
     * @throws CustomException
     */
    @Override
    public Result saveUserContacts(HttpSession session, UserContacts userContacts) throws CustomException {
        if (userContacts.equals(null)||userContacts.equals(""))
            throw new CustomException(ResultEnum.EMTYUSERCONTACTS);

        if (userContacts.getName().equals("")||userContacts.getName().equals(null))
            throw new CustomException(ResultEnum.EMTNAME);

        if (userContacts.getRelationship().equals("")||userContacts.getRelationship().equals(null))
            throw new CustomException(ResultEnum.EMTYRELATIONSHIP);

        if (userContacts.getPhone().equals("")||userContacts.getPhone().equals(null))
            throw new CustomException(ResultEnum.EMTYPHONE);

        String id = session.getId();

        session.setAttribute(id+USERCONTACTS,userContacts);

        return ResultUtils.success();
    }


    /**
     * 保存图片serverId到session
     * @param session
     * @param oneserverId1
     * @param oneserverId2
     * @return
     * @throws CustomException
     */
    @Override
    public Result saveIDCard(HttpSession session, String oneserverId1, String oneserverId2,  int type) throws CustomException {
        if(oneserverId1.equals("")||oneserverId1.equals(null)) {
            throw new CustomException(ResultEnum.EMTYIDCRADPOSITIVE);
        }
        if(oneserverId2.equals("")||oneserverId2.equals(null)) {
            throw new CustomException(ResultEnum.EMTYIDCRADWRONGSIDE);
        }
        /*if(oneserverId3.equals("")||oneserverId3.equals(null)) {
            throw new CustomException(ResultEnum.EMTYIDCRADWRONGSIDE);
        }*/
        String id = session.getId();
        UserIDCard userIDCard = new UserIDCard();
        userIDCard.setOneserverId1(oneserverId1);
        userIDCard.setOneserverId2(oneserverId2);
        //userIDCard.setOneserverId3(oneserverId3);
        userIDCard.setType(type);
        session.setAttribute(id+USERIDCARD,userIDCard);

        return ResultUtils.success();

    }

    /**
     * 保存社保账号到session
     * @param session
     * @param socialSecurityAccount
     * @return
     */
    @Override
    public Result saveSocialSecurityAccount(HttpSession session, SocialSecurityAccount socialSecurityAccount) throws CustomException {
        if (socialSecurityAccount.equals(null)||socialSecurityAccount.equals(""))
            throw new CustomException(ResultEnum.EMTALLSSA);

        if (socialSecurityAccount.getSSA().equals("")||socialSecurityAccount.getSSA().equals(null))
            throw new CustomException(ResultEnum.EMTSSA);

        if (socialSecurityAccount.getPassword().equals("")||socialSecurityAccount.getPassword().equals(null))
            throw new CustomException(ResultEnum.EMTPASSWORD);
        String id = session.getId();

        session.setAttribute(id+SOCIALSECURITYACCOUNT,socialSecurityAccount);

        return ResultUtils.success();
    }

    /**
     * 上传数据
     * @param session
     * @param userBankCard
     * @return
     */
    @Override
    @Transactional
    public Result saveUserBankCard(HttpSession session, UserBankCard userBankCard) throws Exception {
        if (userBankCard.equals(null)||userBankCard.equals(""))
            throw new CustomException(ResultEnum.EMTYUSERBANKCARD);

        String bankType = userBankCard.getBankType();
        if (bankType.equals("")||bankType.equals(null))
            throw new CustomException(ResultEnum.EMTYBANKTYPE);
        String bankCard = userBankCard.getBankCard();
        if (bankCard.equals("")||bankCard.equals(null))
            throw new CustomException(ResultEnum.EMTYBANKCARD);
        String openBank = userBankCard.getOpenBank();
        if (openBank.equals("")||openBank.equals(null))
            throw new CustomException(ResultEnum.EMTYOPENBANK);
        String id = session.getId();
        //取出用户存在session中的数据
        UserBasicInfo userBasicInfo = (UserBasicInfo)session.getAttribute(id + USERBASICINFO);
        //验证银行卡信息与用户是否匹配
        String result = APIBankCard.query(userBankCard.getBankCard(), userBasicInfo.getIDNum(), userBasicInfo.getName());
        if(!"T".equals(result)){
        	log.info("用户基本信息与银行卡不匹配");
        	return ResultUtils.error("银行卡信息不匹配");
        }
        RegisterM registerM = (RegisterM)session.getAttribute(id+REGISTERM);
//        String openID = "osi9FwMghSkM0ARuer8QIOArwgzI";
//        registerM.setOpenid(openID);
//        registerM.setPhone("18475525887");
        String openid = registerM.getOpenid();
        String phone = registerM.getPhone();
        log.info("openid:"+openid);
        log.info("phone:"+phone);
        //查询用户注册id
        Object uid = registerBeeMapper.selectIDByPhoneAndWXID(phone, openid);
        //保存用户其他基本资料
        if (userBasicInfo==null||"".equals(userBasicInfo))
            throw new CustomException(ResultEnum.EMTYUSERBASICINFO);
        UserIDCard userIDCard = (UserIDCard)session.getAttribute(id+USERIDCARD);
        if(userIDCard.getOneserverId1().equals("")||userIDCard.getOneserverId2().equals(null))
            throw new CustomException(ResultEnum.EMTYIDCRADPOSITIVE);
        Date date = new Date();
        //下载微信图片转为json存储
        JSONObject imgJson = DowloadWXImgUtils.downloadAndSaveImg(userIDCard, openid);
        log.info("uid:"+uid);
        log.info("判断是否有填车险保单号："+userBasicInfo.getInsuranceNum());
        Map<String,Object> map = new HashMap<String,Object>();
        log.info("是否为宽带老用户："+userBasicInfo.getIsNoOlder());
        map.put("userBasicInfo",userBasicInfo);
        map.put("imgPath",imgJson.toString());
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("secondPath", "http://wisedp.com/BeeCost/downimg/odXEdwXSjZpA0PD5sHsaml-CRDtM-1500964795-02.png");
//        jsonObject.put("firstPath", "http://wisedp.com/BeeCost/downimg/odXEdwXSjZpA0PD5sHsaml-CRDtM-1500964795-01.png");
//        jsonObject.put("threePath", "http://www.wisedp.com/img/nav_logo_03.png");
//        map.put("imgPath",jsonObject.toString());
        map.put("updateby",CREATBY);
        map.put("updatetime",date);
        map.put("wxid",openid);
        map.put("phone",phone);
        map.put("id",uid);
        //为之前用户注册的添加资料
        log.info("用户的类型："+userBasicInfo.getBeeType());
        int update = registerBeeMapper.updateInfoByWXIDAndPhone(map);
        log.info("update:"+update);
        //插入用户相关地址
        boolean b = insertBeeAddress(uid, userBasicInfo,phone);
        log.info("b:"+b);
       /* UserContacts userContacts = (UserContacts)session.getAttribute(id + USERCONTACTS);
        if (userContacts.equals(null)||userContacts.equals(""))
            throw new CustomException(ResultEnum.EMTYUSERCONTACTS);*/
       // log.info("userContacts:"+userContacts.toString());
        //插入常联系人
        boolean b1 = true;//insertBeeLinkMan(uid, userContacts);
        log.info("b1:"+b1);
        
        SocialSecurityAccount socialSecurityAccount = (SocialSecurityAccount)session.getAttribute(id+SOCIALSECURITYACCOUNT);
        boolean b2 = false;
        int ss = 0;
        if (socialSecurityAccount!=null){
            log.info("socialSecurityAccount:"+socialSecurityAccount.toString());
        	log.info("社保账户有填");
        	if(socialSecurityAccount.getPassword()!=null&&!socialSecurityAccount.getPassword().equals("")
        			&&socialSecurityAccount.getSSA()!=null&&!socialSecurityAccount.getSSA().equals("")){
                //插入社保账户
                 b2 = insertSocialSecurityAccount(uid, socialSecurityAccount);
                log.info("b2:"+b2);
                ss = 1;
        	}
        }


        //插入银行卡信息
        boolean b3 = insertUserBankCard(uid, openBank, bankCard, bankType);
        log.info("b3:"+b3);
        int b4 = -2;
        UserCreditCard userCreditCard = (UserCreditCard) session.getAttribute(id+USERCREDITCARD);
        if(userCreditCard!=null){
            log.info("userCreditCard:"+userCreditCard.toString());

        	log.info("信用卡有填");
        	if(!userCreditCard.getBankname().equals("")&&userCreditCard.getBankname()!=null&&!userCreditCard.getCreditcard().equals("")&& userCreditCard.getCreditcard()!=null){
                //插入信用卡
                Map<String,Object> ccmap = new HashMap<String,Object>();
                ccmap.put("bankname", userCreditCard.getBankname());
                ccmap.put("creditcard", userCreditCard.getCreditcard());
                ccmap.put("beeid", uid);
                ccmap.put("creatby", CREATBY);
                ccmap.put("createdate", date);
                b4 = registerCreditCardMapper.insertCreditCard(ccmap);
                log.info("b4:"+b4);
        	}
        }
        if(b4!=-2&&b4>0){
        	log.info("信用卡有填并且插入成功");
        	if(ss==0){
        		log.info("信用卡有填并且插入成功，社保没填");
        		if(update>0&&b&&b1&&b3){
                	log.info("ResultUtils.success():"+ResultUtils.success());
                    return ResultUtils.success();
                }else{
                	log.info("ResultUtils.error(ResultEnum.APPLYFAILURE):"+ResultUtils.error(ResultEnum.APPLYFAILURE));
                    return ResultUtils.error(ResultEnum.APPLYFAILURE);
                }
    		}else{
        		log.info("信用卡有填并且插入成功，社保有填");
    			if(b2){
    				log.info("信用卡和社保都填并且插入成功");
    				if(update>0&&b&&b1&&b3){
                    	log.info("ResultUtils.success():"+ResultUtils.success());
                        return ResultUtils.success();
                    }else{
                    	log.info("ResultUtils.error(ResultEnum.APPLYFAILURE):"+ResultUtils.error(ResultEnum.APPLYFAILURE));
                        return ResultUtils.error(ResultEnum.APPLYFAILURE);
                    }
    			}else{
            		log.info("信用卡有填并且插入成功，社保填了但是插入失败");
                	log.info("ResultUtils.error(ResultEnum.APPLYFAILURE):"+ResultUtils.error(ResultEnum.APPLYFAILURE));
                    return ResultUtils.error(ResultEnum.APPLYFAILURE);
    			}
    		}
        }else{
        	log.info("信用卡没填");
        		if(ss==0){
            		log.info("信用卡没填，社保没填");
            		if(update>0&&b&&b1&&b3){
                    	log.info("ResultUtils.success():"+ResultUtils.success());
                        return ResultUtils.success();
                    }else{
                    	log.info("ResultUtils.error(ResultEnum.APPLYFAILURE):"+ResultUtils.error(ResultEnum.APPLYFAILURE));
                        return ResultUtils.error(ResultEnum.APPLYFAILURE);
                    }
        		}else{
            		log.info("信用卡没填，社保填了");
            		if(b2){
            			log.info("信用卡没填，社保填并且插入成功");
                		if(update>0&&b&&b1&&b3){
                        	log.info("ResultUtils.success():"+ResultUtils.success());
                            return ResultUtils.success();
                        }else{
                        	log.info("ResultUtils.error(ResultEnum.APPLYFAILURE):"+ResultUtils.error(ResultEnum.APPLYFAILURE));
                            return ResultUtils.error(ResultEnum.APPLYFAILURE);
                        }
            		}else{
            			log.info("信用卡没填，社保填并且插入失败");
                    	log.info("ResultUtils.error(ResultEnum.APPLYFAILURE):"+ResultUtils.error(ResultEnum.APPLYFAILURE));
                        return ResultUtils.error(ResultEnum.APPLYFAILURE);
                    }
        		}
        }
    }


	/**
	 * 保存社车险保单号到session
	 */
	@Override
	public Result saveInsuranceNum(HttpSession session, String insuranceNum) {
		log.info("insuranceNum:"+insuranceNum);
        String id = session.getId();
        UserBasicInfo userBasicInfo = (UserBasicInfo)session.getAttribute(id + USERBASICINFO);
        userBasicInfo.setInsuranceNum(insuranceNum);
        session.setAttribute(id + USERBASICINFO,userBasicInfo);
        return ResultUtils.success();
	}

	/**
	 * 保存信用卡号到session
	 * @param session
	 * @param userCreditCard
	 * @return
	 * @throws CustomException 
	 */
	@Override
	public Result saveUserCreditCard(HttpSession session,
			UserCreditCard userCreditCard) throws CustomException {
		if(userCreditCard==null)
            throw new CustomException(ResultEnum.NOCREDITCARD);
		log.info("userCreditCard:"+userCreditCard.toString());

		if(userCreditCard.getBankname()==null||userCreditCard.getBankname().equals(""))
            throw new CustomException(ResultEnum.NOCREDITCARD2);
		if(userCreditCard.getCreditcard()==null||userCreditCard.getCreditcard().equals(""))
            throw new CustomException(ResultEnum.NOCREDITCARD1);
        String id = session.getId();

        session.setAttribute(id+USERCREDITCARD,userCreditCard);

		return ResultUtils.success();
	}

	/**
	 * 保存SSAINCC到session
	 * @param session
	 * @param ssaincc
	 * @return
	 * @throws CustomException 
	 */
	@Override
	public Result saveSSAINCC(HttpSession session, SSAINCC ssaincc) throws CustomException {
        String id = session.getId();
        
        if(ssaincc.equals("")||ssaincc==null)
            throw new CustomException(ResultEnum.EMTYSSINCC);
        log.info("ssaincc:"+ssaincc.toString());
		SocialSecurityAccount socialSecurityAccount = ssaincc.getSsa();
		if(!socialSecurityAccount.equals("")||socialSecurityAccount!=null){
	        session.setAttribute(id+SOCIALSECURITYACCOUNT,socialSecurityAccount);
		}
        
        String insuranceNum = ssaincc.getInsuranceNum();
		log.info("insuranceNum:"+insuranceNum);
		if(insuranceNum!=null){
	        UserBasicInfo userBasicInfo = (UserBasicInfo)session.getAttribute(id + USERBASICINFO);
	        userBasicInfo.setInsuranceNum(insuranceNum);
	        session.setAttribute(id + USERBASICINFO,userBasicInfo);
		}
        
        UserCreditCard userCreditCard = ssaincc.getCc();
		if(!userCreditCard.equals("")||userCreditCard!=null){
			if(userCreditCard.getBankname()==null||userCreditCard.getBankname().equals(""))
	            throw new CustomException(ResultEnum.NOCREDITCARD2);
			if(userCreditCard.getCreditcard()==null||userCreditCard.getCreditcard().equals(""))
	            throw new CustomException(ResultEnum.NOCREDITCARD1);
			log.info("userCreditCard:"+userCreditCard.toString());
	        session.setAttribute(id+USERCREDITCARD,userCreditCard);
		}

		return ResultUtils.success();
	}


	@Override
	public Result saveSSAINCC(HttpSession session,
			SocialSecurityAccount socialSecurityAccount,
			UserCreditCard userCreditCard, MyInsurance myInsurance,Integer isNoOlder) throws CustomException {
        String id = session.getId();
        //用户填写了社保资料
		if(!socialSecurityAccount.equals("")||socialSecurityAccount!=null){
			log.info("用户填写了社保资料");
    		if((!socialSecurityAccount.getPassword().equals("")&&socialSecurityAccount.getPassword()!=null)
    				&&(socialSecurityAccount.getSSA()!=null&&!socialSecurityAccount.getSSA().equals(""))){
        		log.info("socialSecurityAccount:"+socialSecurityAccount.toString());
    	        session.setAttribute(id+SOCIALSECURITYACCOUNT,socialSecurityAccount);
    		}
		}
		//用户填写了车险
        if(!myInsurance.equals("")&&myInsurance!=null){
        	log.info("用户填写了车险");
        	log.info("plateNum:"+myInsurance.getPlateNum());
        	log.info("insuranceNum:"+myInsurance.getInsuranceNum());
        	log.info("insuranceComp:"+myInsurance.getInsuranceComp());
    		if(!myInsurance.getPlateNum().equals("")&&myInsurance.getPlateNum()!=null
    				&&!myInsurance.getInsuranceComp().equals("")&&myInsurance.getInsuranceComp()!=null){
    	        UserBasicInfo userBasicInfo = (UserBasicInfo)session.getAttribute(id + USERBASICINFO);
    	        userBasicInfo.setInsuranceNum(myInsurance.getInsuranceNum());
    	        userBasicInfo.setPlateNum(myInsurance.getPlateNum());
    	        userBasicInfo.setInsuranceComp(myInsurance.getInsuranceComp());
    	        //是否是宽带老用户
    	        log.info("将宽带老用户存入用户资料对象："+isNoOlder);
    	        userBasicInfo.setIsNoOlder(isNoOlder);
    	        session.setAttribute(id + USERBASICINFO,userBasicInfo);
    		}
        }
        //用户填写了信用卡
		if(!userCreditCard.equals("")||userCreditCard!=null){
			log.info("用户填写了信用卡");
    		if((!userCreditCard.getBankname().equals("")&&userCreditCard.getBankname()!=null)
    				&&(userCreditCard.getCreditcard()!=null&&!userCreditCard.getCreditcard().equals(""))){
    			log.info("userCreditCard:"+userCreditCard.toString());
    	        session.setAttribute(id+USERCREDITCARD,userCreditCard);
    		}
		}
		//用户点选了是否为宽带老用户
		if(isNoOlder!=null){
			UserBasicInfo userBasicInfo = (UserBasicInfo)session.getAttribute(id + USERBASICINFO);
			 //是否是宽带老用户
	        log.info("将宽带老用户存入用户资料对象："+isNoOlder);
			userBasicInfo.setIsNoOlder(isNoOlder);
		}
		return ResultUtils.success();
	}
    /**
     * 插入银行卡
     * @param uid
     * @param openBank
     * @param bankCard
     * @param bankType
     */
    private boolean insertUserBankCard(Object uid, String openBank, String bankCard, String bankType) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("bank",bankType);
        map.put("openbank",openBank);
        map.put("bankcard",bankCard);
        map.put("creatby",CREATBY);
        map.put("beeid",uid);
        map.put("creatdate",new Date());

        int i = registerBankCardMapper.insertbankcard(map);

        if(i>0)
            return true;
        else
            return false;
    }

    /**
     * 插入社保信息
     * @param uid
     * @param socialSecurityAccount
     */
    private boolean insertSocialSecurityAccount(Object uid, SocialSecurityAccount socialSecurityAccount) throws Exception {
        String password = socialSecurityAccount.getPassword();
        String ssa = socialSecurityAccount.getSSA();
        EncryptionUtils des = new EncryptionUtils("wbdp");//自定义密钥
        String encrypt = des.encrypt(password);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("socialpassword",encrypt);
        map.put("socialaccount",ssa);
        map.put("creatby",CREATBY);
        map.put("creatdate",new Date());
        map.put("beeid",uid);
        int i = registerSocialdataMapper.insertSocialAccount(map);

        if(i>0)
            return true;
        else
            return false;
    }

    /**
     * 插入用户联系人
     * @param uid
     * @param userContacts
     */
    private boolean insertBeeLinkMan(Object uid, UserContacts userContacts) {
        Map<String,Object> map = new HashMap<String,Object>();
        
        String phone = userContacts.getPhone();
        map.put("phone",phone);
        String name = userContacts.getName();
        map.put("linkmanname",name);
        String relationship = userContacts.getRelationship();
        map.put("relation",relationship);
        map.put("beeid",uid);
        map.put("creatby",CREATBY);
        map.put("creatdate",new Date());

        int i = registLinkMan.insertLinkMan(map);
        if(i>0)
            return true;
        else
            return false;
    }

    /**
     * 插入用户相关地址
     * @param id
     * @param userBasicInfo
     * @param phone
     */
    private boolean insertBeeAddress(Object id, UserBasicInfo userBasicInfo, String phone) {
        Date date = new Date();

        Map<String,Object> companymap = new HashMap<String,Object>();
        //公司地址
        String companyAddress = userBasicInfo.getCompanyAddress();//详情地址
        companymap.put("detaddress",companyAddress);
        String companyProvince = userBasicInfo.getCompanyProvince();//省份
        companymap.put("province",companyProvince);
        String companyCity = userBasicInfo.getCompanyCity();//市
        companymap.put("city",companyCity);
        String companyArea = userBasicInfo.getCompanyArea();//区
        companymap.put("area",companyArea);
        companymap.put("beeid",id);
        companymap.put("creatdate",date);
        companymap.put("type",0);

        int i = registBeeAddress.insertAddressInfo(companymap);

        Map<String,Object> homemap = new HashMap<String,Object>();
        //家庭地址
        String homeAddress = userBasicInfo.getHomeAddress();
        homemap.put("detaddress",homeAddress);
        String homeProvince = userBasicInfo.getHomeProvince();
        homemap.put("province",homeProvince);
        String homeCity = userBasicInfo.getHomeCity();
        homemap.put("city",homeCity);
        String homeArea = userBasicInfo.getHomeArea();
        homemap.put("area",homeArea);
        homemap.put("beeid",id);
        homemap.put("creatdate",date);
        homemap.put("receiptname",userBasicInfo.getName());
        homemap.put("phone",phone);
        homemap.put("type",1);

        int i1 = registBeeAddress.insertAddressInfo(homemap);

        if(i>0&&i1>0)
            return true;
        else
            return false;
    }

    /**
     * 通过实体类和微信code保存session并返回前台
     * @param registerM
     * @param code
     * @param session
     * @return
     */
    private String jumpHTMLByCodeAndSessionAndRegisterM( RegisterM registerM,String id,String code,HttpSession session) throws CustomException {
        String openID = getOpenIDByWXCode(code);
//        String openID = "odXEdwalok7od7Rs6lkZI0YWju70";
        registerM.setOpenid(openID);
        registerM.setCode(code);
        session.setAttribute(id+REGISTERM,registerM);
        return HTMLStatic.REGISTER;
    }
    /**
     * 从session中获取RegisterM
     * @param session
     * @return
     */
    private RegisterM getRegisterMBySession(String id,HttpSession session){
        RegisterM registerM = (RegisterM)session.getAttribute(id+REGISTERM);
        log.info("registerM:"+registerM.toString());
        return  registerM;
    }
    /**
     * 转化存放地址
     * @param IdCade01
     * @return
     */
    private String changUrlToLinuxpach(String IdCade01) {
        String string = IdCade01.split("/wisedp.com/YiStaging/")[1];
        String sb = "/usr/local/tomcat7/webapps/YiStaging/"+string;
        return sb;
    }
    /**
     * 后台获取ticket并且生成签名
     */
	@Override
	public JSONObject getTicketAndCreateWebSignature(String url) {
		JSONObject webSignatureJson = null;
		if(!url.equals("")){
//			JsapiTicket webJsapiTicket = weixinUtil.getWebJsapiTicket(WeiChatStatic.TEMPORARY_ACCESS_TOKEN);
//			if(webJsapiTicket!=null){
				log.info("url:"+url);
				log.info("WeichatCacheUnit.getJS_API_TICKETFromCache():"+WXCacheUnit.getJS_API_TICKETFromCache());
				if(WXCacheUnit.getJS_API_TICKETFromCache()!=null){
					webSignatureJson = JSAPIUitl.createWebSignature(WXCacheUnit.getJS_API_TICKETFromCache(), url);
					if(!webSignatureJson.equals(""))
						log.info("生成WebSignature成功。");
					else
						log.error("生成WebSignature失败。");
				}else
				    log.error("获取webJsapiTicket爲空。");
		}else
			log.error("url为空。");
		return webSignatureJson;
	}
}
