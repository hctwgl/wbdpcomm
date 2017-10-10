package com.wbdp.wx.service.impl.mine;

import com.wbdp.wx.QRcode.util.CreateQRCode;
import com.wbdp.wx.constant.HTMLStatic;
import com.wbdp.wx.domain.MineM;
import com.wbdp.wx.domain.PhoneShopM;
import com.wbdp.wx.entity.Bee;
import com.wbdp.wx.entity.Brushcredit;
import com.wbdp.wx.enums.ResultEnum;
import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.mapper.*;
import com.wbdp.wx.model.*;
import com.wbdp.wx.service.impl.BaseServiceImpl;
import com.wbdp.wx.service.mine.MineSercice;
import com.wbdp.wx.utils.APIBankCard;
import com.wbdp.wx.utils.ResultUtils;
import com.wbdp.wx.utils.Dowload.DowloadWXImgUtils;
import com.wbdp.wx.utils.Pollen.PollenUtils;
import com.wbdp.wx.utils.encryption.EncryptionUtils;
import com.wbdp.wx.utils.http.ZhimaCreditAntifraudScore;
import com.wbdp.wx.utils.string.StringUtils;
import com.wbdp.wx.utils.wx.AuthorityUtil;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import javax.sound.midi.MidiDevice.Info;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wisedata005 on 2017/7/5.
 */
@Service
public class MineSerciceImpl extends BaseServiceImpl implements MineSercice {
    /**MINEM存seesion的字段*/
    private static final String MINEM = "MineM";
    /**修改人*/
    private static final String UPDATEER = "微信管理员";
    /**USERBASICINFO存seesion的字段*/
    private static final String USERBASICINFO = "UserBasicInfo";
    /**日志log*/
    private static Logger log = LoggerFactory.getLogger(MineSerciceImpl.class);

    @Autowired
    private BeeMapper mineBeeMapper;
    @Autowired
    private BeeAddressMapper mineBeeAddressMapper;
    @Autowired
    private LinkManMapper mineLinkMapper;
    @Autowired
    private SocialdataMapper mineSocialdataMapper;
    @Autowired
    private BankCardMapper mineBankCardMapper;
    @Autowired
    private OrderMapper mineShopOrderMapper;
    @Autowired
    private AttributevalueMapper mineAttributevalueMapper;
    @Autowired
    private CreditCardMapper mineCreditCardMapper;
    @Autowired
    private BrushcreditMapper brushcreditMapper;
    @Autowired
    private SalemanMapper salemanMapper;
    @Autowired
    private QrimgMapper qrimgMapper;
    /**
     * 跳转页面
     * @param code
     * @param session
     * @return
     * @throws CustomException
     */
    @Override
    public String toMine(String code, HttpSession session) throws CustomException {
        if (code.equals(null)||code.equals(""))
            throw new CustomException(ResultEnum.NOCODE);
        String id = session.getId();
        MineM mineM = (MineM)session.getAttribute(id+MINEM);
        String result = "";

        //实例当前session中的注册用例
        if (mineM==null)
            // 创建实体
            mineM = new MineM();

        //判断是否已经获取到用户微信id
        if (mineM.getOpenid()==null||mineM.getOpenid().equals("")){
            result = jumpHTMLByCodeAndSessionAndRegisterM(mineM,id, code, session);
            return result;
        }
        //刷新页面
        if (mineM.getCode().equals(code)){
            return  HTMLStatic.MYSELF;
        }

        //返回链接code改变
        if (!mineM.getCode().equals("")&&!mineM.getCode().equals(code)){
            result = jumpHTMLByCodeAndSessionAndRegisterM(mineM,id, code, session);
            return result;
        }

        log.error("授权获取openID出错");

        return result;
    }

    /**
     * 查询用户基本资料
     * @param session
     * @return
     */
    @Override
    public Result selectUserInfos(HttpSession session) throws CustomException {

        String openid = getWXIDBYSession(session);

        UserBasicInfo userBasicInfo = mineBeeMapper.selectUserInfosByWXid(openid);
//        userBasicInfo.setNIDNum(userBasicInfo.getIDNum());
        userBasicInfo.setIDNum(StringUtils.changeIDCardToCover(userBasicInfo.getIDNum()));
        
        Integer id1 = userBasicInfo.getId();
        List<UserAddress> userAddressesList = mineBeeAddressMapper.selectUserAddressByBeeid(id1);
        Object pollen = PollenUtils.getPollen(id1);
        UserBasicInfo userBasicInfoHasAddress = setAddressToUserBaseInfoByList(userBasicInfo,userAddressesList);
        userBasicInfoHasAddress.setPollen(pollen);
        log.info(ResultUtils.success(userBasicInfoHasAddress)+"");
        return ResultUtils.success(userBasicInfoHasAddress);
    }

    /**
     * 修改用户信息
     * @param session
     * @param userBasicInfo
     * @return
     */
    @Override
    @Transactional
    public Result updateUserInfo(HttpSession session, UserBasicInfo userBasicInfo)throws CustomException  {
        if (userBasicInfo.equals(null)||userBasicInfo.equals(""))
            throw new CustomException(ResultEnum.EMTYUSERBASICINFO);
        log.info("userBasicInfo:"+userBasicInfo.toString());
        //判断用户是否修改了身份证号码，未修改则参数为"".
        if(!"".equals(userBasicInfo.getIDNum())){
        	//进行身份证与姓名的验证
            String result = ZhimaCreditAntifraudScore.ZhimaCreditAntifraudScoreGet(userBasicInfo.getIDNum(), userBasicInfo.getName());
            log.info("身份证与姓名的验证："+result);
            JSONObject obj = JSONObject.fromObject(result);
            if("true".equals(obj.getString("success"))){
            	String score = obj.getString("score");
                //score不等于100时，代表该用户姓名与身份证信息不匹配
                if(!"100".equals(score)){
                	log.info("用户姓名与身份证信息不匹配");
                	return ResultUtils.error("用户姓名与身份证信息不匹配");
                }
            }else{
            	log.info("身份证号码不存在");
            	return ResultUtils.error("身份证号码不存在");
            }
        }
        String openid = getWXIDBYSession(session);

        ISBlackBee uid = mineBeeMapper.selectIDByWXID(openid);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userBasicInfo",userBasicInfo);
        map.put("wxid",openid);
        map.put("id",uid.getId());
        map.put("updateby",UPDATEER);
        map.put("updatetime",new Date());
        
        //为之前用户注册的添加资料
        int update = mineBeeMapper.updateUserInfoByID(map);
        
        //修改用户相关地址
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
        companymap.put("beeid",uid.getId());
        companymap.put("creatdate",date);
        companymap.put("type",0);

        int i = mineBeeAddressMapper.updateAddressInfo(companymap);


        Map<String,Object> goodsmap = new HashMap<String,Object>();
        //收货地址
        String goodsAddress = userBasicInfo.getGoodsAddress();
        goodsmap.put("detaddress",goodsAddress);
        String goodsProvince = userBasicInfo.getGoodsProvince();
        goodsmap.put("province",goodsProvince);
        String goodsCity = userBasicInfo.getGoodsCity();
        goodsmap.put("city",goodsCity);
        String goodsArea = userBasicInfo.getGoodsArea();
        goodsmap.put("area",goodsArea);
        goodsmap.put("beeid",uid.getId());
        goodsmap.put("creatdate",date);
        goodsmap.put("type",1);

        int i2 = mineBeeAddressMapper.updateAddressInfo(goodsmap);

        if (update>0&&i2>0&&i>0){
             return ResultUtils.success();
        }else{
            return ResultUtils.error(ResultEnum.UPDATEERROR);
        }
    }
    /**
     * 获取用户任性刷记录
     */
	@Override
	public Map<String, Object> getUserBrush(HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			String openid = getWXIDBYSession(session);
			log.info("获取用户任性刷记录中的用户微信号："+openid);
			if(openid==null||"".equals(openid)){
				log.info("获取用户微信号失败");
				outMap.put("data", "");
				outMap.put("status", "FALSE");
				outMap.put("msg", "获取微信号失败");
				return outMap;
			}
			List<Brushcredit> list = brushcreditMapper.getUserBrush(openid);
			if(list!=null&&list.size()!=0){
				log.info("获取维泽任性刷记录成功");
				outMap.put("data", list);
				outMap.put("status", "SUCCESS");
				outMap.put("msg", "获取维泽任性刷记录成功");
				return outMap;
			}else{
				log.info("获取维泽任性刷记录失败");
				outMap.put("data", "");
				outMap.put("status", "FALSE");
				outMap.put("msg", "获取维泽任性刷记录失败");
				return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", "");
			outMap.put("status", "EXCEPTION");
			outMap.put("msg", "获取维泽任性刷记录异常");
			return outMap;
		}
	}
    /**
     * 查看常联系人
     * @param session
     * @return
     */
    @Override
    public Result selectUserContacts(HttpSession session) throws CustomException {
        long beeId = getBeeIDBYWXid(session);

        UserContacts userContacts = mineLinkMapper.selectUserContactsByBeeID(beeId);

        if(userContacts!=null)
            log.info(ResultUtils.success(userContacts.toString())+"");

        return ResultUtils.success(userContacts);
    }

    /**
     * 修改常联系人
     * @param session
     * @param userContacts
     * @return
     */
    @Override
    public Result updateUserContact(HttpSession session, UserContacts userContacts) throws CustomException {
        String openid = getWXIDBYSession(session);

        ISBlackBee beeId = mineBeeMapper.selectIDByWXID(openid);

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("phone",userContacts.getPhone());
        map.put("linkmanname",userContacts.getName());
        map.put("relation",userContacts.getRelationship());
        map.put("beeid",beeId.getId());
        map.put("updateby",UPDATEER);
        map.put("updatetime",new Date());

        int i = mineLinkMapper.updateUserContactsByBeeId(map);
        log.info("i:"+i);
        if(i>0)
            return ResultUtils.success();
        else
            return ResultUtils.error(ResultEnum.UPDATEERROR);
    }

    /**
     * 查看用户身份证正反面图片
     * @param session
     * @return
     */
    @Override
    @Transactional
    public Result selectUserIDCard(HttpSession session) throws CustomException {
        String openid = getWXIDBYSession(session);

        String imgPathStr = mineBeeMapper.selectCardImageByWXID(openid);
        UserIDCard userIDCard = null;
        if(!imgPathStr.equals("")&&!imgPathStr.equals(null)){
            JSONObject imgPathJson = JSONObject.fromObject(imgPathStr);
            log.info("图片地址json串："+imgPathJson.toString());
            userIDCard = new UserIDCard();
            userIDCard.setOneserverId1(imgPathJson.getString("firstPath"));
            userIDCard.setOneserverId2(imgPathJson.getString("secondPath"));
            //userIDCard.setOneserverId3(imgPathJson.getString("threePath"));
        }
        if(userIDCard!=null)
             log.info(ResultUtils.success(userIDCard)+"");
        return ResultUtils.success(userIDCard);
    }

    /**
     * 修改用户身份证正反面图片
     * @param session
     * @param userIDCard
     * @return
     * @throws CustomException
     */
    @Override
    @Transactional
    public Result updateUserIDCard(HttpSession session, UserIDCard userIDCard) throws CustomException {

        log.info("userIDCard:"+userIDCard.toString());
        String openid = getWXIDBYSession(session);
        if(userIDCard.equals("")||userIDCard==null)
            throw new CustomException(ResultEnum.EMTYIDCRADPOSITIVE);
        	
        JSONObject imgJson = DowloadWXImgUtils.downloadAndSaveImg(userIDCard, openid);
//        JSONObject imgJson = new JSONObject();
//        imgJson.put("firstPath",userIDCard.getOneserverId1());
//        imgJson.put("secondPath",userIDCard.getOneserverId2());
        log.info("imgJson:"+imgJson.toString());

        ISBlackBee beeId = mineBeeMapper.selectIDByWXID(openid);
        log.info("beeId:"+beeId.toString());

        int i = mineBeeMapper.updateCardImageByID(imgJson.toString(), Long.valueOf(String.valueOf(beeId.getId())));

            log.info("i:"+i);
        if (i>0)
             return ResultUtils.success();
        else
            return ResultUtils.error(ResultEnum.UPDATEERROR);
    }

    /**
     * 查询用户社保信息
     * @param session
     * @return
     */
    @Override
    public Result selectSocialSecurityAccount(HttpSession session) throws Exception {
        long beeId = getBeeIDBYWXid(session);
        SocialSecurityAccount socialSecurityAccount = mineSocialdataMapper.selectSSAByBeeID(beeId);
        if(!"".equals(socialSecurityAccount)&&socialSecurityAccount!=null){
            //自定义密钥
            EncryptionUtils des = new EncryptionUtils("wbdp");
            socialSecurityAccount.setPassword(des.decrypt(socialSecurityAccount.getPassword()));
        }

        log.info(ResultUtils.success(socialSecurityAccount)+"");
        return ResultUtils.success(socialSecurityAccount);
    }

    /**
     * 修改用户社保信息
     * @param session
     * @param socialSecurityAccount
     * @return
     */
    @Override
    public Result updateSocialSecurityAccount(HttpSession session, SocialSecurityAccount socialSecurityAccount) throws Exception {

        long beeId = getBeeIDBYWXid(session);
        String password = socialSecurityAccount.getPassword();
        EncryptionUtils des = new EncryptionUtils("wbdp");//自定义密钥
        String encrypt = des.encrypt(password);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("socialpassword",encrypt);
        map.put("socialaccount",socialSecurityAccount.getSSA());
        map.put("updateby",UPDATEER);
        map.put("updatetime",new Date());
        map.put("beeid",beeId);
        int i = mineSocialdataMapper.updateSSAByBeeID(map);
        log.info("i:"+i);
        if (i>0)
             return ResultUtils.success();
        else
            return ResultUtils.error(ResultEnum.UPDATEERROR);
    }

    /**
     * 获取用户银行卡信息
     * @param session
     * @return
     */
    @Override
    public Result selectBankCard(HttpSession session) throws CustomException {
        long beeId = getBeeIDBYWXid(session);

        UserBankCard userBankCard = mineBankCardMapper.selectUserBankCardByBeeID(beeId);
        if (!userBankCard.equals("")&&!userBankCard.equals(null)){
//        	userBankCard.setNbankCard(userBankCard.getBankCard());
            userBankCard.setBankCard(StringUtils.changeIDCardCover(userBankCard.getBankCard()));
            log.info(ResultUtils.success(userBankCard)+"");
        }
        return ResultUtils.success(userBankCard);
    }

    /**
     * 修改用户银行卡信息
     * @param session
     * @param userBankCard
     * @return
     */
    @Override
    public Result updateBankCard(HttpSession session, UserBankCard userBankCard) throws CustomException {
        long beeId = getBeeIDBYWXid(session);
        Bee bee = mineBeeMapper.selectByID(beeId);
        //判断用户是否修改了银行卡
        if(!"bankCard".equals(userBankCard.getBankCard())){
        	//验证修改后银行卡信息与用户是否匹配
        	String result = APIBankCard.query(userBankCard.getBankCard(), bee.getBeecard(), bee.getBeename());
            if(!"T".equals(result)){
            	log.info("用户基本信息与银行卡不匹配");
            	return ResultUtils.error("银行卡信息不匹配");
            }
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("bank",userBankCard.getBankType());
        map.put("openbank",userBankCard.getOpenBank());
        map.put("bankcard",userBankCard.getBankCard());
        map.put("updateby",UPDATEER);
        map.put("beeid",beeId);
        map.put("updatetime",new Date());
        int i = mineBankCardMapper.updateUserBankCarByBeeID(map);
        log.info("i:"+i);
        if (i>0)
            return ResultUtils.success();
        else
            return ResultUtils.error(ResultEnum.UPDATEERROR);
    }

    /**
     * 获取信用额度
     * @throws CustomException 
     */
	@Override
	public Result getPollen(HttpSession session) throws CustomException {
		long beeIDBYWXid = getBeeIDBYWXid(session);
		Object pollen = PollenUtils.getPollen(beeIDBYWXid);
		return ResultUtils.success(pollen);
	}
	
	
    /**
     * 通过session获取微信id
     * @param session
     * @return
     * @throws CustomException
     */
    private String getWXIDBYSession(HttpSession session) throws CustomException {
        String id = session.getId();
        MineM mineM = getMineMBySession(id, session);
        String openid = mineM.getOpenid();
        if ("".equals(openid) || openid==null)
            throw new CustomException(ResultEnum.NOOPPENDID);;
        return openid;
    }
    /**
     * 通过微信id获取用户id
     * @param session
     * @return
     * @throws CustomException
     */
    private long getBeeIDBYWXid(HttpSession session) throws CustomException {
        String openid = getWXIDBYSession(session);
        ISBlackBee uid = mineBeeMapper.selectIDByWXID(openid);
        return Long.valueOf(String.valueOf(uid.getId()));
    }

    /**
     * 将地址赋值到类中
     * @param userBasicInfo
     * @param userAddressesList
     * @return
     */
    private UserBasicInfo setAddressToUserBaseInfoByList(UserBasicInfo userBasicInfo, List<UserAddress> userAddressesList) {
        for (UserAddress userAddress:userAddressesList){
            if (userAddress.getType()==0){
                userBasicInfo.setCompanyAddress(userAddress.getDetailsAddress());
                userBasicInfo.setCompanyProvince(userAddress.getProvince());
                userBasicInfo.setCompanyArea(userAddress.getArea());
                userBasicInfo.setCompanyCity(userAddress.getCity());
            }
            if (userAddress.getType()==1){
                userBasicInfo.setHomeAddress(userAddress.getDetailsAddress());
                userBasicInfo.setHomeProvince(userAddress.getProvince());
                userBasicInfo.setHomeArea(userAddress.getArea());
                userBasicInfo.setHomeCity(userAddress.getCity());
            }
            if (userAddress.getType()==2){
                userBasicInfo.setGoodsAddress(userAddress.getDetailsAddress());
                userBasicInfo.setGoodsProvince(userAddress.getProvince());
                userBasicInfo.setGoodsArea(userAddress.getArea());
                userBasicInfo.setGoodsCity(userAddress.getCity());
            }
        }
        return userBasicInfo;
    }


    /**
     * 从session中获取MineM
     * @param session
     * @return
     */
    private MineM getMineMBySession(String id,HttpSession session){
        MineM mineM = (MineM)session.getAttribute(id+MINEM);
        return  mineM;
    }

    /**
     * 跳转页面
     * @param mineM
     * @param id
     * @param code
     * @param session
     * @return
     * @throws CustomException 
     */
    private String jumpHTMLByCodeAndSessionAndRegisterM(MineM mineM, String id, String code, HttpSession session) throws CustomException {
        String openID = getOpenIDByWXCode(code);
       // String openID = "odXEdwalok7od7Rs6lkZI0YWju70";
      //String openID = "odXEdwVBkxuAsZhh7LjFiqVmfpi4";
    	mineM.setBeeID(24);
        mineM.setOpenid(openID);
        mineM.setCode(code);
        session.setAttribute(id+MINEM,mineM);
        return HTMLStatic.MYSELF;
    }

	@Override
	public Result isRegist(HttpSession session) {
        String id = session.getId();
        MineM mineM = (MineM)session.getAttribute(id+MINEM);
        ISBlackBee uid = mineBeeMapper.selectIDByWXID(mineM.getOpenid());
        if (uid==null)
            return ResultUtils.success("未注册");
        else{
//        	if(uid.getBeeStatus()==1)
//                return ResultUtils.success("黑名单");
//        	else{
            	mineM.setBeeID(Long.valueOf(String.valueOf(uid.getId())));
                session.setAttribute(id+MINEM,mineM);
                return ResultUtils.success("已注册");
//        	}
        }
	}

	@Override
	public Result getMineOrders(HttpSession session) throws CustomException {
		String id = session.getId();
		MineM mineM = (MineM)session.getAttribute(id+MINEM);
        long beeID = mineM.getBeeID();
        if (beeID==0)
            throw new CustomException(ResultEnum.NOREGISTER);

        List<UserOrder> goodsModelsList = mineShopOrderMapper.selectOrdersByBeeID(12, beeID);

        for (UserOrder userOrder:goodsModelsList){
            String valueIDStr = userOrder.getValueIDStr();
            String[] split = valueIDStr.split(",");
            userOrder.setColor(mineAttributevalueMapper.selectValueByID(Long.valueOf(split[0])));
            userOrder.setStorage(mineAttributevalueMapper.selectValueByID(Long.valueOf(split[1])));
//            userOrder.setOperator(mineAttributevalueMapper.selectValueByID(Long.valueOf(split[2])));
        }
        
        log.info(""+ResultUtils.success(goodsModelsList).toString());
        return ResultUtils.success(goodsModelsList);
	}
	/**
	 * 查询用户信用卡
	 * @param session
	 * @return
	 * @throws CustomException 
	 */
	@Override
	public Result getMineCC(HttpSession session) throws CustomException {
		long beeid = this.getBeeIDBYWXid(session);
        if (beeid==0)
            throw new CustomException(ResultEnum.NOREGISTER);
		UserCreditCard userCreditCard = mineCreditCardMapper.selectCreditCardByBeeID(beeid);
		if(userCreditCard!=null){
			userCreditCard.setCreditcard(StringUtils.changeIDCardCover(userCreditCard.getCreditcard()));
			log.info("userCreditCard:"+ResultUtils.success(userCreditCard).toString());
		}
		return ResultUtils.success(userCreditCard);
	}
	/**
	 * 修改用户信用卡
	 * @param session
	 * @param userCreditCard
	 * @return
	 * @throws CustomException 
	 */
	@Override
	public Result updateMineCC(HttpSession session,
			UserCreditCard userCreditCard) throws CustomException {
		long beeid = this.getBeeIDBYWXid(session);
        if (beeid==0)
            throw new CustomException(ResultEnum.NOREGISTER);
		if(userCreditCard==null)
            throw new CustomException(ResultEnum.NOCREDITCARD);
		log.info("userCreditCard:"+userCreditCard.toString());

		if(userCreditCard.getBankname()==null||userCreditCard.getBankname().equals(""))
            throw new CustomException(ResultEnum.NOCREDITCARD2);
		if(userCreditCard.getCreditcard()==null||userCreditCard.getCreditcard().equals(""))
            throw new CustomException(ResultEnum.NOCREDITCARD1);
		
        Map<String,Object> ccmap = new HashMap<String,Object>();
        ccmap.put("bankname", userCreditCard.getBankname());
        ccmap.put("creditcard", userCreditCard.getCreditcard());
        ccmap.put("beeid", beeid);
        ccmap.put("updateby", UPDATEER);
        ccmap.put("updatetime", new Date());
        int i = mineCreditCardMapper.updateCreditCardByBeeID(ccmap);
        if(i>0)
        	return ResultUtils.success();
        else
		    return ResultUtils.error(ResultEnum.UPDATEERROR);
	}
	/**
	 * 修改车险保险单
	 * @param session
	 * @param insuranceNum
	 * @return
	 */
	@Override
	public Result updateMineInsNum(HttpSession session, String insuranceNum)throws CustomException {
		long beeid = this.getBeeIDBYWXid(session);
        if (beeid==0)
            throw new CustomException(ResultEnum.NOREGISTER);
        if(insuranceNum==null||insuranceNum.equals(""))
            throw new CustomException(ResultEnum.EMTYINSNUM);
//        int i = mineBeeMapper.updateInsNumByBeeID(insuranceNum, beeid);
//        if(i>0)
          return ResultUtils.success();
//        else
//		    return ResultUtils.error(ResultEnum.UPDATEERROR);
	}
	
	/**
	 * 查询用户车险保险单
	 * @param session
	 * @return
	 * @throws CustomException
	 */
	@Override
	public Result getMineInsNum(HttpSession session) throws CustomException {
		long beeid = this.getBeeIDBYWXid(session);
        if (beeid==0)
            throw new CustomException(ResultEnum.NOREGISTER);
        
        MyInsurance insNum = mineBeeMapper.selectInsNumByBeeID(beeid);
        if(insNum!=null)
        	log.info("insNum:"+ResultUtils.success(insNum).toString());
		return ResultUtils.success(insNum);
	}

	@Override
	@Transactional
	public Result updateSSAINCC(HttpSession session,
			SocialSecurityAccount socialSecurityAccount,
			UserCreditCard userCreditCard, MyInsurance myInsurance) throws Exception {
		long beeid = this.getBeeIDBYWXid(session);
        if (beeid==0)
            throw new CustomException(ResultEnum.NOREGISTER);
        
        int cc = -1;
        if(!userCreditCard.equals("")||userCreditCard!=null){
    		log.info("userCreditCard:"+userCreditCard.toString());

    		if((!userCreditCard.getBankname().equals("")&&userCreditCard.getBankname()!=null)
    				&&(userCreditCard.getCreditcard()!=null&&!userCreditCard.getCreditcard().equals(""))){
        		log.info("userCreditCard:"+userCreditCard.toString());
                Map<String,Object> ccmap = new HashMap<String,Object>();
                ccmap.put("bankname", userCreditCard.getBankname());
                ccmap.put("creditcard", userCreditCard.getCreditcard());
                ccmap.put("beeid", beeid);
                
                UserCreditCard uCreditCard = mineCreditCardMapper.selectCreditCardByBeeID(beeid);
                if(uCreditCard!=null){
                    ccmap.put("updateby", UPDATEER);
                    ccmap.put("updatetime", new Date());
                    int i = mineCreditCardMapper.updateCreditCardByBeeID(ccmap);
                    if(i>0)
                    	cc = 0;
                    else
                    	cc = -2;
                }else{
                    ccmap.put("creatby", UPDATEER);
                    ccmap.put("createdate", new Date());
                    int i = mineCreditCardMapper.insertCreditCard(ccmap);
                    if(i>0)
                    	cc = 0;
                    else
                    	cc = -2;
                }
    		}
    		
        }
        int in = -1;
        if(!myInsurance.equals("")||myInsurance!=null){
    		log.info("myInsurance:"+myInsurance.toString());
    		log.info("plateNum:"+myInsurance.getPlateNum());
        	log.info("insuranceNum:"+myInsurance.getInsuranceNum());
        	log.info("insuranceComp:"+myInsurance.getInsuranceComp());
        	log.info("isNolder:"+myInsurance.getIsNoOlder());
    		/*if(!myInsurance.getPlateNum().equals("")&&myInsurance.getPlateNum()!=null
    				&&!myInsurance.getInsuranceComp().equals("")&&myInsurance.getInsuranceComp()!=null){*/
		        Map<String,Object> map = new HashMap<String,Object>();
		        map.put("insuranceNum",myInsurance.getInsuranceNum());
		        map.put("plateNum",myInsurance.getPlateNum());
		        map.put("insuranceComp",myInsurance.getInsuranceComp());
		        map.put("beeid",beeid);
		        map.put("isNoOlder", myInsurance.getIsNoOlder());
    	        int i = mineBeeMapper.updateInsNumByBeeID(map);
                if(i>0)
                	in = 0;
                else
                	in = -2;
    		//}
        }
		
        int ss = -1;
		if(!socialSecurityAccount.equals("")||socialSecurityAccount!=null){
    		log.info("socialSecurityAccount:"+socialSecurityAccount.toString());
    		if((!socialSecurityAccount.getPassword().equals("")&&socialSecurityAccount.getPassword()!=null)
    				&&(socialSecurityAccount.getSSA()!=null&&!socialSecurityAccount.getSSA().equals(""))){
        		log.info("socialSecurityAccount:"+socialSecurityAccount.toString());
        		SocialSecurityAccount ssAccount = mineSocialdataMapper.selectSSAByBeeID(beeid);

    			String password = socialSecurityAccount.getPassword();
		        EncryptionUtils des = new EncryptionUtils("wbdp");//自定义密钥
		        String encrypt = des.encrypt(password);
		        Map<String,Object> map = new HashMap<String,Object>();
		        map.put("socialpassword",encrypt);
		        map.put("socialaccount",socialSecurityAccount.getSSA());
		        map.put("beeid",beeid);
		        
        		if(ssAccount!=null){
    		        map.put("updateby",UPDATEER);
    		        map.put("updatetime",new Date());
    		        int i = mineSocialdataMapper.updateSSAByBeeID(map);
    	           if(i>0)
    	            	ss = 0;
    	            else
    	            	ss = -2;
        		}else{
    		        map.put("CreatBy",UPDATEER);
    		        map.put("CreatDate",new Date());
    		        int i = mineSocialdataMapper.insertSocialAccount(map);
    	           if(i>0)
    	            	ss = 0;
    	            else
    	            	ss = -2;
        			
        		}
    		}
			 
		}
		
		if(ss==-2)
		    return ResultUtils.error(ResultEnum.UPDATEERROR);
		if(in==-2)
		    return ResultUtils.error(ResultEnum.UPDATEERROR);
		if(cc==-2)
		    return ResultUtils.error(ResultEnum.UPDATEERROR);
		return ResultUtils.success();
	}
	
	@Override
	public Result getOrderListDetail(HttpSession session, int oid)throws CustomException, Exception {
		UserOrder userOrder = mineShopOrderMapper.selectOrdersByOID(oid);
		if(userOrder!=null){
			log.info("insNum:"+ResultUtils.success(userOrder).toString());
		            String valueIDStr = userOrder.getValueIDStr();
		            String[] split = valueIDStr.split(",");
		            userOrder.setColor(mineAttributevalueMapper.selectValueByID(Long.valueOf(split[0])));
		            userOrder.setStorage(mineAttributevalueMapper.selectValueByID(Long.valueOf(split[1])));
		}
		return ResultUtils.success(userOrder);
	}
	/**
	 * 生成二维码
	 * @param pacPeriods 期数
	 * @param pacMonthlyPrice 每月应付金额
	 * @return
	 */
	@Override
	public Map<String, Object> creatQRCode(Integer pacPeriods,
			Integer pacMonthlyPrice,HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		String savePath = "/usr/local/tomcat7/webapps/YiStaging/downimg/";
		Qrimg qrimg = new Qrimg();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			log.info("生成二维码获取参数："+pacPeriods+":"+pacMonthlyPrice);
			//获取用户openID
			String id = session.getId();
	        MineM mineM = getMineMBySession(id, session);
	        String openid = "";
	        if(mineM!=null){
	        	openid = mineM.getOpenid();
	        	log.info("生成二维码openID获取成功："+openid);
	        }else{
	        	log.info("生成二维码openID获取失败");
	        	outMap.put("status", "FALSE");
	        	outMap.put("msg", "openID获取失败");
	        	outMap.put("imgUrl", "");
	        	return outMap;
	        }
	        //通过openID查询业务员数据
	        Bee bee = mineBeeMapper.selectBeeByWx(openid);
	        if(bee==null){
	        	log.info("生成二维码用户信息获取失败");
	        	outMap.put("status", "FALSE");
	        	outMap.put("msg", "用户信息获取失败");
	        	outMap.put("imgUrl", "");
	        	return outMap;
	        }
	        //通过手机号查询业务员推荐码
	        Saleman saleman = salemanMapper.selectSalemanByPhone(bee.getPhone());
	        if(saleman==null){
	        	log.info("生成二维码业务员信息获取失败");
	        	outMap.put("status", "FALSE");
	        	outMap.put("msg", "业务员信息获取失败");
	        	outMap.put("imgUrl", "");
	        	return outMap;
	        }
	        JSONObject obj = new JSONObject();
	        //业务员姓名
	        obj.put("name", bee.getBeename());
	        //业务员手机号
	        obj.put("phone", bee.getPhone());
	        //业务员推荐码
	        obj.put("recommend", saleman.getRecommend());
	        //套餐期数
	        obj.put("pacPeriods", pacPeriods);
	        //套餐每月应付金额
	        obj.put("pacMonthlyPrice", pacMonthlyPrice);
	        //生成二维码，并返回二维码保存地址
	        String imgUrl = CreateQRCode.createQRCode(obj.toString(),savePath,openid);
	        log.info("返回二维码图片保存地址："+imgUrl);
	        qrimg.setBeeWx(openid);
	        qrimg.setImgUrl(imgUrl);
	        qrimg.setPacPeriods(pacPeriods);
	        qrimg.setPacMonthlyPrice(pacMonthlyPrice);
	        qrimg.setPacPrice(pacPeriods*pacMonthlyPrice);
	        qrimg.setCreatDate(format.format(new Date()));
	        Integer result = qrimgMapper.saveQRCode(qrimg);
	        if(result==1){
	        	log.info("保存套餐二维码成功");
	        	outMap.put("status", "SUCCESS");
	        	outMap.put("msg", "保存套餐二维码成功");
	        	outMap.put("imgUrl", imgUrl);
	        	return outMap;
	        }else{
	        	log.info("保存套餐二维码失败");
	        	outMap.put("status", "FALSE");
	        	outMap.put("msg", "保存套餐二维码失败");
	        	outMap.put("imgUrl", "");
	        	return outMap;
	        }
		} catch (Exception e) {
			log.info("生成二维码业务员信息获取异常");
			e.printStackTrace();
        	outMap.put("status", "EXCEPTION");
        	outMap.put("msg", "业务员信息获取异常");
        	return outMap;
		}
	}
	/**
	 * 业务员页面获取数据,先通过openID查出该用户手机号，手机号匹配业务员表
	 */
	@Override
	public Map<String, Object> getQRCode(HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//获取用户openID
			String id = session.getId();
	        MineM mineM = getMineMBySession(id, session);
	        String openid = "";
	        if(mineM!=null){
	        	openid = mineM.getOpenid();
	        	log.info("生成二维码openID获取成功："+openid);
	        }else{
	        	log.info("生成二维码openID获取失败");
	        	outMap.put("status", "FALSE");
	        	outMap.put("msg", "openID获取失败");
	        	return outMap;
	        }
	      //通过openID查询业务员数据
	       Bee bee = mineBeeMapper.selectBeeByWx(openid);
	       if(bee==null){
	        	log.info("获取二维码用户信息获取失败");
	        	outMap.put("status", "FALSE");
	        	outMap.put("msg", "用户信息获取失败");
	        	return outMap;
	        }
	       //通过手机号查询业务员推荐码,如果对应业务员不存在则传入type为0
	        Saleman saleman = salemanMapper.selectSalemanByPhone(bee.getPhone());
	        if(saleman==null){
	        	log.info("该用户非业务员");
	        	outMap.put("status", "SUCCESS");
	        	outMap.put("type", 0);
	        	outMap.put("data", "");
	        	outMap.put("msg", "该用户非业务员");
	        	return outMap;
	        }else{
	        	log.info("该用户为业务员");
	        	//获取业务员套餐二维码数据
	        	List<Qrimg> list = qrimgMapper.selectQrimgByOpenid(openid);
	        	outMap.put("status", "SUCCESS");
	        	outMap.put("type", 1);
	        	outMap.put("data", list);
	        	outMap.put("msg", "该用户为业务员");
	        	return outMap;
	        }
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
        	outMap.put("type", 1);
        	outMap.put("data", "");
        	outMap.put("msg", "获取数据异常");
        	return outMap;
		}
	}
	/**
	 * 删除二维码数据
	 */
	@Override
	public Map<String, Object> delQRCode(HttpSession session, Long id) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			log.info("删除二维码获取的ID："+id);
			Integer result = qrimgMapper.deleteQrimgByID(id);
			if(result==1){
				log.info("删除业务员二维码成功");
	        	outMap.put("status", "SUCCESS");
	        	outMap.put("data", result);
	        	outMap.put("msg", "删除业务员二维码成功");
	        	return outMap;
			}else{
				log.info("删除业务员二维码失败");
	        	outMap.put("status", "SUCCESS");
	        	outMap.put("data", result);
	        	outMap.put("msg", "删除业务员二维码失败");
	        	return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
        	outMap.put("data", 0);
        	outMap.put("msg", "删除业务员二维码异常");
        	return outMap;
		}
	}
}
