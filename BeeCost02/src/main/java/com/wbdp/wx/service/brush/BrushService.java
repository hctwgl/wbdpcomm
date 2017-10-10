package com.wbdp.wx.service.brush;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;

import com.wbdp.wx.entity.Brushcredit;

/**
 * 维泽刷刷业务接口类
 * @author 汪赛军
 * date:2017年8月30日上午10:54:36
 *
 */
public interface BrushService {
	/**
	 * 获取用户授权，得到用户openID并保存到session中
	 * @param code
	 * @param session
	 * @return
	 */
	public String getOpenidAndjump(String code, HttpSession session);
	
	/**
	 * 推送消息跳转
	 * @param code
	 * @param time
	 * @param session
	 * @return
	 */
	public String getOpenidAndjump(String code,String time, HttpSession session);
	
	/**
	 *  任性刷推送页面获取数据
	 * @param session
	 * @return
	 */
	public Map<String,Object> getBrushPush(HttpSession session);
	/**
	 * 发送短信验证码到业务员
	 * @param recomCode
	 * @param session
	 * @return
	 */
	public Map<String,Object>  sendMessage(String recomCode,HttpSession session);
	
	/**
	 * 保存用户填写的业务员推荐码到session中，并发送短信验证码至对应业务员，等待下一步操作
	 * @param recomCode 业务员推荐码
	 * @param session
	 * @return
	 */
	public Map<String,Object> saveBrushToSession(Brushcredit brushcredit,String message,HttpSession session);
	
	 /**
     * 进行人脸识别，成功后返回成功标识
     * @param oneserverId1
     * @return
     */
	public Map<String,Object> faceCheck(HttpSession session,String oneserverId1);
	
	 /**
     * 在维泽任性刷中获取用户额度
     * @param session 
     * @return
     */
	public Map<String,Object> getBrushPollen(HttpSession session);
	
	/**
     * 保存在线签名
     * @param onlineSign 在线签名字符串
     * @return
     */
	public  Map<String,Object> saveBrushSign(HttpSession session,String onlineSign);
	
	/**
     * 借款合同页面获取数据
     * @param session
     * @return
     */
	public Map<String,Object> getContract(HttpSession session);
	
	/**
     * 确认通过维泽任性刷刷取额度购买手机，将最终数据保存至数据库中
     * @param session
     * @return
     */
	public Map<String,Object> confirmBrush(HttpSession session,Integer type);
}
