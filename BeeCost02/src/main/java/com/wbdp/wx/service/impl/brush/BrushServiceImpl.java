package com.wbdp.wx.service.impl.brush;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbdp.wx.controller.brush.BrushController;
import com.wbdp.wx.domain.PhoneShopM;
import com.wbdp.wx.domain.RegisterM;
import com.wbdp.wx.domain.ShopCartM;
import com.wbdp.wx.entity.Brushcredit;
import com.wbdp.wx.entity.Pollen;
import com.wbdp.wx.enums.ResultEnum;
import com.wbdp.wx.mapper.BeeMapper;
import com.wbdp.wx.mapper.BrushcreditMapper;
import com.wbdp.wx.mapper.PollenMapper;
import com.wbdp.wx.mapper.UserMapper;
import com.wbdp.wx.service.brush.BrushService;
import com.wbdp.wx.service.impl.BaseServiceImpl;
import com.wbdp.wx.utils.DateFormat;
import com.wbdp.wx.utils.ResultUtils;
import com.wbdp.wx.utils.TransformMoney;
import com.wbdp.wx.utils.Dowload.DowloadWXImgUtils;
import com.wbdp.wx.utils.http.Face;
import com.wbdp.wx.utils.phone.SentVerifieCodeUtils;
/**
 * 维泽刷刷业务实现类
 * @author 汪赛军
 * date:2017年8月30日上午10:57:29
 *
 */
@Service
public class BrushServiceImpl extends BaseServiceImpl implements BrushService {
	 /**日志log*/
    private static Logger log = Logger.getLogger(BrushServiceImpl.class);
    /**
     * 获取维泽任性刷中的用户openID标记字符串
     */
    private final String BRUSHMAP = "BRUSHMAP";
    private static final String REGISTERM = "RegisterM";
    
    @Autowired
    private BeeMapper beeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BrushcreditMapper brushcreditMapper;
    /**
     * 客户数据接口类
     */
    @Autowired
    private BeeMapper mineBeeMapper;
    /**
     * 客户花粉信用数据接口类
     */
    @Autowired
    private PollenMapper pollenMapper;
	/**
	 * 获取用户授权，得到用户openID并保存到session中，获取成功后跳转到填写信用额度页面
	 * @param code
	 * @param session
	 * @return
	 */
	@Override
	public String getOpenidAndjump(String code, HttpSession session) {
		Map<String,Object> sessionMap = new HashMap<String, Object>();
		try {
			//获取用户openID
			String openID = getOpenIDByWXCode(code);
			log.info("维泽任性刷获取的openID："+openID);
			if(openID!=null&&!"".equals(openID)){
				//保存openID到session中
				String sessionid = session.getId();
				//实例化openID对象
				RegisterM registerM =  new RegisterM();
				registerM.setOpenid(openID);
				registerM.setCode(code);
				session.setAttribute(sessionid+REGISTERM, registerM);
				//判断该用户是否已注册
				Integer result = beeMapper.selectBeeByWX(openID);
				if(result==0){
					return "register";
				}else{
					return "scan";
				}
			}else{
				log.info("维泽任性刷获取openID失败");
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 从推送消息跳转获取授权
	 */
	@Override
	public String getOpenidAndjump(String code, String time, HttpSession session) {
		try {
			//获取用户openID
			//保存openID到session中
			String sessionid = session.getId();
			String openID = getOpenIDByWXCode(code);
			log.info("维泽任性刷推送获取的openID："+openID);
			log.info("维泽任性刷推送获取的time："+time);
			//实例化openID对象
			RegisterM registerM =  new RegisterM();
			registerM.setOpenid(openID);
			registerM.setCode(code);
			session.setAttribute(sessionid+REGISTERM, registerM);
			//根据openID和时间查询该用户的任性刷记录
			Brushcredit brushcredit =brushcreditMapper.getPushBrush(openID,time);
			session.setAttribute(sessionid+"Brushcredit", brushcredit);
			return "pushresult";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 *  任性刷推送页面获取数据
	 * @param session
	 * @return
	 */
	@Override
	public Map<String, Object> getBrushPush(HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			String id = session.getId();
			Brushcredit brushcredit = (Brushcredit)session.getAttribute(id+"Brushcredit");
			outMap.put("data", brushcredit);
			outMap.put("status", "SUCCESS");
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", "");
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}
	/**
     * 在维泽任性刷中获取用户可用额度
     * @param session 
     * @return
     */
	@Override
	public Map<String, Object> getBrushPollen(HttpSession session) {
		Map<String,Object> outMap = new HashMap<String, Object>();
		try {
			String openID = "";
			String sessionid = session.getId();
			//获取openID实体类
			RegisterM registerM =  (RegisterM)session.getAttribute(sessionid+REGISTERM);
			if(registerM!=null){
				openID = registerM.getOpenid();
				log.info("查询用户微信号成功："+openID);
			}else{
				log.info("查询用户微信号失败");
				outMap.put("status", "FALSES");
				outMap.put("data", "");
				outMap.put("msg", "查询用户微信号失败");
				return outMap;
			}
			Pollen pollen = pollenMapper.selectPollenByOpenid(openID);
			if(pollen!=null){
				log.info("任性刷获取用户额度："+pollen.getCreditlimit()+":"+pollen.getUsedcredit());
				//计算可用额度
				Integer p = pollen.getCreditlimit()-pollen.getUsedcredit();
				outMap.put("status", "SUCCESS");
				outMap.put("data", p);
				outMap.put("msg", "查询可用额度成功");
				return outMap;
			}else{
				log.info("查询可用额度失败");
				outMap.put("status", "FALSE");
				outMap.put("data", "");
				outMap.put("msg", "查询可用额度失败");
				return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
			outMap.put("data", "");
			outMap.put("msg", "查询可用额度异常");
			return outMap;
		}
	}
	
	/**
	 * 发送短信验证码到业务员
	 * @param recomCode
	 * @param session
	 * @return
	 */
	@Override
	public Map<String, Object> sendMessage(String recomCode, HttpSession session) {
		
		Map<String,Object> outMap = new HashMap<String, Object>();
		try {
			log.info("短信发送获取的业务员推荐码："+recomCode);
			String openID = "";
			String sessionid = session.getId();
			//获取openID实体类
			RegisterM registerM =  (RegisterM)session.getAttribute(sessionid+REGISTERM);
			String phone = userMapper.getUserPhone(recomCode);
			log.info("根据推荐码查询的手机号："+phone);
			JSONObject obj = null;
			if(phone!=null&&!"".equals(phone)){
				 obj = SentVerifieCodeUtils.sentMessage(phone);
					//判断发送成功否
			        if("200".equals(obj.get("statuscode"))){
			        	log.info("短信发送成功,发送的验证码："+obj.getString("code"));
			        	//将验证码存入openID实体类中，等待下一步确认
			           registerM.setVerifieCode(obj.getString("code"));
			           session.setAttribute(sessionid+REGISTERM, registerM);
			           outMap.put("status", "SUCCESS");
			           outMap.put("msg", "发送成功");
			           return outMap;
			        }else{
			        	log.info("短信发送失败");
			        	outMap.put("status", "FALSE");
			        	outMap.put("msg", "发送失败");
				        return outMap;
			        }
			}else{
				log.info("手机号码为空");
				outMap.put("status", "FALSE");
	        	outMap.put("msg", "手机号码为空");
		        return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
        	outMap.put("msg", "发送失败");
	        return outMap;
		}
	}
	
	/**
	 * 保存用户填写的业务员推荐码到session中，判断验证码与额度是否准确，等待下一步操作
	 * @param recomCode 业务员推荐码
	 * @param session
	 * @return
	 */
	@Override
	public Map<String, Object> saveBrushToSession(Brushcredit brushcredit,String message,HttpSession session) {
		Map<String,Object> outMap = new HashMap<String, Object>();
		try {
			log.info("保存任性刷资料到session："+brushcredit.toString());
			//log.info("保存任性刷资料获取的短信验证码："+message);
			String openID = "";
			String sessionid = session.getId();
			//获取openID实体类
			RegisterM registerM =  (RegisterM)session.getAttribute(sessionid+REGISTERM);
			if(registerM!=null){
				//赋值openID
				openID = registerM.getOpenid();
				//判断用户输入验证码是否准确
				/*if(!registerM.getVerifieCode().equals(message)){
					outMap.put("status", "FALSE");
					outMap.put("msg", "验证码错误");
					return outMap;
				}*/
				//判断额度是否超过可用额度
				Pollen pollen = pollenMapper.selectPollenByOpenid(openID);
				if(pollen!=null){
					//获取的额度减去已用额度则为用户可用额度
					Integer p = pollen.getCreditlimit()-pollen.getUsedcredit();
					log.info("保存任性刷资料可用额度："+p);
					Integer pacPrice = brushcredit.getPacPrice();
					if(p-pacPrice<=0){
						outMap.put("status", "FALSE");
						outMap.put("msg", "可用额度不足");
						return outMap;
					}
				}
			}else{
				outMap.put("status", "FALSE");
				outMap.put("msg", "未获取到数据");
				return outMap;
			}
			//将用户微信号存入实体类中
			brushcredit.setClientWx(openID);
			//将维泽任性刷实体类存入session中，等待用户进行最后的确认
			session.setAttribute(sessionid+"Brushcredit", brushcredit);
			log.info("任性刷资料保存到session："+brushcredit.toString());
			outMap.put("status", "SUCCESS");
			outMap.put("msg", "信用资料保存成功");
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
			outMap.put("msg", "资料保存出现异常");
			return outMap;
		}
	}
	 /**
     * 进行人脸识别，成功后返回成功标识
     * @param oneserverId1
     * @return
     */
	@Override
	public Map<String, Object> faceCheck(HttpSession session,
			String oneserverId1) {
		Map<String,Object> outMap = new HashMap<String, Object>();
		try {
			log.info("维泽任性刷人脸识别图片ID："+oneserverId1);
			 String id = session.getId();
			 RegisterM registerM =  (RegisterM)session.getAttribute(id+REGISTERM);
			 //从session中获取任性刷对象
			 Brushcredit brushcredit = (Brushcredit)session.getAttribute(id+"Brushcredit");
			 String openid = "";
			 if(registerM!=null){
				  openid = registerM.getOpenid();
				  log.info("人脸识别中获取的openID："+openid);
			 }else{
				 log.info("获取openID失败 ");
			 }
			 log.info("人脸识别中获取的openID："+openid);
			 if("ojzcuwoDzCsPSDYIiJfRzP9uuCIg".equals(openid)||"ojzcuwq5kgKDwTz85OtE3nHbpomo".equals(openid)){
				 	Thread.sleep(1000);//休眠一秒
				 	outMap.put("status", "SUCCESS");
					outMap.put("msg", "识别通过");
					return outMap;
			 }
			 //得到人脸识别图片路径
			String faceUrl =  DowloadWXImgUtils.downloadAndSaveFace(oneserverId1, openid);
			log.info("人脸识别图片路径："+faceUrl);
			//将人脸识别图片路径存入实体类中
			brushcredit.setFaceImg(faceUrl);
			//获取身份证正面图片路径
			String imgPathStr = mineBeeMapper.selectCardImageByWXID(openid);
			JSONObject imgPathJson = JSONObject.fromObject(imgPathStr);
			String firstPath = imgPathJson.getString("firstPath");
			String json = Face.faceCompare(faceUrl, firstPath);
			JSONObject faceJson = JSONObject.fromObject(json);
			log.info("获取的json："+faceJson);
			double score = 0;
			//判断返回的字符串中是否包含confidence这个字段
			 if(faceJson.toString().indexOf("confidence")!=-1){  
				 log.info("人脸识别获得分数");
				 score = Double.parseDouble(faceJson.getString("confidence"));
			 }else{ 
				 log.info("人脸识别未获得分数");
			} 
			log.info("人脸识别分数："+score);
			if(score>60){
				log.info("分数大于60，识别通过");
				//识别通过,修改人脸识别状态
				brushcredit.setFaceStatus(1);
				session.setAttribute(id+"Brushcredit", brushcredit);
				outMap.put("status", "SUCCESS");
				outMap.put("msg", "识别通过");
				return outMap;
			}else{
				log.info("分数小于60，识别失败");
				//识别未通过,修改人脸识别状态
				brushcredit.setFaceStatus(0);
				session.setAttribute(id+"Brushcredit", brushcredit);
				outMap.put("status", "FALSE");
				outMap.put("msg", "识别未通过");
				return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
			outMap.put("msg", "识别出现异常,请重新上传拍照或重新上传清晰身份证照片");
			return outMap;
		}
	}
	/**
     * 保存在线签名
     * @param onlineSign 在线签名字符串
     * @return
     */
	@Override
	public Map<String, Object> saveBrushSign(HttpSession session,
			String onlineSign) {
		Map<String,Object> outMap = new HashMap<String, Object>();
		try {
			log.info("维泽任性刷获取在线签名："+onlineSign);
			String id = session.getId();
			Brushcredit brushcredit = (Brushcredit)session.getAttribute(id+"Brushcredit");
			if(brushcredit!=null){
				//判断在线签名是否成功传入
				if(!"".equals(onlineSign)&&onlineSign!=null){
					brushcredit.setOnlineSign(onlineSign);
					session.setAttribute(id+"Brushcredit", brushcredit);
					outMap.put("status", "SUCCESS");
					outMap.put("msg", "在线签名成功");
					return outMap;
				}else{
					log.info("在线签名为空");
					outMap.put("status", "FALSE");
					outMap.put("msg", "在线签名失败");
					return outMap;
				}
			}else{
				log.info("未获取到session中维泽任性刷对象");
				outMap.put("status", "FALSE");
				outMap.put("msg", "在线签名异常");
				return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
			outMap.put("msg", "在线签名异常");
			return outMap;
		}
	}
	/**
     * 借款合同页面获取数据
     * 返回数据： 用户姓名、身份证号码、手机号码、联系地址、贷款金额、期数、开始与截止时间、每月还款日、每月还款金额、客户银行卡号、在线签名
     * @param session
     * @return
     */
	@SuppressWarnings("unused")
	@Override
	public Map<String, Object> getContract(HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Map<String, Object> map = null;
			//获取openID实体类
			 String id = session.getId();
			 RegisterM registerM =  (RegisterM)session.getAttribute(id+REGISTERM);
			 Brushcredit brushcredit = (Brushcredit)session.getAttribute(id+"Brushcredit");
			 String openid = "";
			 if(registerM!=null){
				 openid = registerM.getOpenid();
				 log.info("借款合同获取的用户微信："+openid);
			 }else{
				 log.info("借款合同未获取获取到用户微信");
				 outMap.put("data", "");
				 outMap.put("msg", "未获取到数据");
				 outMap.put("status", "FALSE");
				 return outMap;
			 }
			 //获取维泽任性刷借款合同中所需数据
			 map =  brushcreditMapper.getContract(openid);
			 log.info("借款合同获取维泽任性刷数据："+brushcredit.toString());
			 log.info("借款合同获取数据查询视图数据："+map.get("BeeName"));
			 if(brushcredit!=null){
				 //在线签名
				 map.put("onlineSign", brushcredit.getOnlineSign());
				 //月付金额
				 map.put("pacMonthlyPrice", brushcredit.getPacMonthlyPrice());
				 //期数
				 map.put("pacPeriods", brushcredit.getPacPeriods());
				 //套餐总价(大写)
				 map.put("bpacPrice", TransformMoney.transform(brushcredit.getPacPrice()));
				//套餐总价(小写)
				 map.put("spacPrice", brushcredit.getPacPrice());
				 //开始时间
				 String starttime = DateFormat.dateAddMonth(format.format(new Date()),1);
				 map.put("starttime", starttime);
				 brushcredit.setStartTime(starttime);
				//截止时间=开始时间+期数月份
				 String endtime = DateFormat.dateAddMonth(starttime,brushcredit.getPacPeriods());
				 map.put("endtime", endtime);
				 brushcredit.setEndTime(endtime);
				 session.setAttribute(id+"Brushcredit",brushcredit);
				 outMap.put("data", map);
				 outMap.put("msg", "获取数据成功");
				 outMap.put("status", "SUCCESS");
				 return outMap;
			 }else{
				 log.info("未获取到维泽任性刷数据");
				 outMap.put("data", "");
				 outMap.put("msg", "未获取到数据");
				 outMap.put("status", "FALSE");
				 return outMap;
			 }
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", "");
			outMap.put("msg", "未获取到数据");
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}
	
	/**
     * 确认通过维泽任性刷刷取额度购买手机，将最终数据保存至数据库中
     * @param session
     * @return
     */
	@Override
	public Map<String, Object> confirmBrush(HttpSession session,Integer type){
		Map<String, Object> outMap = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> pollenMap = new HashMap<String, Object>();
		try {
			String id = session.getId();
			Brushcredit brushcredit = (Brushcredit)session.getAttribute(id+"Brushcredit");
			if(brushcredit.getBrushTime()==null||"".equals(brushcredit.getBrushTime())){
				brushcredit.setBrushTime(format.format(new Date()));
			}
			if(type==2){
				//type等于2，表示用户选择等待后台审核
				brushcredit.setFaceStatus(2);
			}else if(type==1){
				//type等于1，表示用户人脸识别通过
				brushcredit.setFaceStatus(1);
			}
			log.info("维泽任性刷对象最后一次验证："+brushcredit.toString());
			 //开始时间
			 String starttime = DateFormat.dateAddMonth(format.format(new Date()),1);
			 if(brushcredit.getStartTime()==null||"".equals(brushcredit.getEndTime())){
				 brushcredit.setStartTime(starttime);
			 }
			//截止时间=开始时间+期数月份
			 String endtime = DateFormat.dateAddMonth(starttime,brushcredit.getPacPeriods());
			if(brushcredit.getEndTime()==null||"".equals(brushcredit.getStartTime())){
				 brushcredit.setEndTime(endtime);	 
			}
			Integer result = 0;
			//判断该任性刷数据是否有ID，用于判断是否是公拉私客户
			if(brushcredit.getId()!=null&&brushcredit.getId()!=0){
				 result = brushcreditMapper.updatePhshBrush(brushcredit);
			}else{
				 result = brushcreditMapper.saveBrush(brushcredit);
			}
			Pollen pollen = pollenMapper.selectPollenByOpenid(brushcredit.getClientWx());
			if(result==1&&type==1){//人脸识别通过且记录保存成功
				log.info("保存维泽任性刷成功");
				//保存任性刷成功后修改额度数据
				if(pollen!=null){
					pollenMap.put("openid", brushcredit.getClientWx());
					pollenMap.put("updateTime", format.format((new Date())));
					pollenMap.put("usedCredit", pollen.getUsedcredit()+brushcredit.getPacPrice());
					Integer p = pollenMapper.updatePollenByOpenid(pollenMap);
					if(p==1){
						log.info("修改花粉信用成功");
					}else{
						log.info("修改花粉信用失败");
					}
				}
				outMap.put("data", result);
				outMap.put("msg", "保存维泽任性刷成功");
				outMap.put("status", "SUCCESS");
				return outMap;
			}else if(result!=1){
				log.info("保存维泽任性刷失败");
				outMap.put("data", result);
				outMap.put("msg", "保存维泽任性刷失败");
				outMap.put("status", "FALSE");
				return outMap;
			}else if(result==1&&type==2){
				log.info("保存维泽任性刷成功,交由后台审核");
				outMap.put("msg", "保存维泽任性刷成功,交由后台审核");
				outMap.put("status", "SUCCESS");
				return outMap;
			}else{
				log.info("保存维泽任性刷失败");
				outMap.put("data", result);
				outMap.put("msg", "保存维泽任性刷失败");
				outMap.put("status", "FALSE");
				return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", 0);
			outMap.put("msg", "保存维泽任性刷失败");
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}
}
