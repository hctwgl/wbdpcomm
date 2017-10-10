package com.wbdp.bee.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.bee.dao.BrushcreditMapper;
import com.wbdp.bee.dao.Wbl_PollenDAO;
import com.wbdp.bee.service.BrushcreditService;
import com.wbdp.bee.util.AliyunVerify;
import com.wbdp.bee.util.UtilHttpSend;
/**
 * 维泽任性刷业务实现类
 * @author 汪赛军
 * date:2017年9月8日下午6:31:54
 *
 */
@Service
public class BrushcreditServiceImpl implements BrushcreditService {
	//日志
	private Logger log=Logger.getLogger(BrushcreditServiceImpl.class);
	@Autowired
	private BrushcreditMapper brushcreditMapper;
	@Autowired
	private Wbl_PollenDAO wbl_PollenDAO;
	/**
	 * 获取维泽任性刷数据
	 */
	@Override
	public Map<String, Object> selectAllBrushcredit(Integer pageNum,
			HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		try {
			Integer userType = (Integer)session.getAttribute("userType");
			String username = session.getAttribute("username").toString();
			//清除之前的session数据
			session.removeAttribute("pageNowThisMouth");
			session.removeAttribute("pageNowLastMouth");
			session.removeAttribute("pagesLastMouth");
			session.removeAttribute("pagesThisMouth");
			//保存当前页
			session.setAttribute("pageNow", pageNum);
			pageNum=pageNum==1?0:(pageNum-1)*10;  //初始化起始页码
			if(userType==1||userType==2){//系统管理员和客服登录
				list = brushcreditMapper.selectAllBrushcredit(pageNum);
				//计算总页数
				Integer count = brushcreditMapper.getCount();//((count+10)-1)/10
				//保存总页数
				session.setAttribute("pages",((count+10)-1)/10);
				outMap.put("data", list);
				return outMap;
			}else if(userType==3){//客户经理登录
				String recomCode = session.getAttribute("recomCode").toString();
				list = brushcreditMapper.selectAllBrushcreditBySaleman(pageNum, recomCode);
				//计算总页数
				Integer count = brushcreditMapper.getCountBySaleman(recomCode);//((count+10)-1)/10
				//保存总页数
				session.setAttribute("pages",((count+10)-1)/10);
				outMap.put("data", list);
				return outMap;
			}else if(userType==4){//客户经理管理员登录
				String recomCode = "";
				list = brushcreditMapper.selectAllBrushcreditBySalemanMan(pageNum, recomCode, username);
				//计算总页数
				Integer count = brushcreditMapper.getCountBySalemanMan(recomCode, username);//((count+10)-1)/10
				//保存总页数
				session.setAttribute("pages",((count+10)-1)/10);
				outMap.put("data", list);
				return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询本月
	 */
	@Override
	public Map<String,Object> brushListTheMouth(Integer pageNum, HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		try {
			//清除之前的session数据
			session.removeAttribute("pageNow");
			session.removeAttribute("pageNowLastMouth");
			session.removeAttribute("pages");
			session.removeAttribute("pagesLastMouth");
			//获取session中的用户类型
			Integer userType = (Integer)session.getAttribute("userType");
			String username = session.getAttribute("username").toString();
			//保存当前页
			session.setAttribute("pageNowThisMouth", pageNum);
			pageNum=pageNum==1?0:(pageNum-1)*10;  //初始化起始页码
			if(userType==2){//系统管理员和客服登录
				list = brushcreditMapper.selectBrushThisMouth(pageNum);
				//计算总页数
				Integer count = brushcreditMapper.countBrushThisMouth();//((count+10)-1)/10
				//保存总页数
				session.setAttribute("pagesThisMouth",((count+10)-1)/10);
				outMap.put("data", list);
				return outMap;
			}else if(userType==3){//客户经理登录
				String recomCode = session.getAttribute("recomCode").toString();
				list = brushcreditMapper.selectBrushThisMouthBySale(pageNum, recomCode);
				//计算总页数
				Integer count = brushcreditMapper.countBrushThisMouthBySale(recomCode);//((count+10)-1)/10
				//保存总页数
				session.setAttribute("pagesThisMouth",((count+10)-1)/10);
				outMap.put("data", list);
				return outMap;
			}else if(userType==4){//客户经理管理员登录
				String recomCode = "";//session.getAttribute("recomCode").toString();
				list = brushcreditMapper.selectBrushThisMouthBySaleMan(pageNum, recomCode, username);
				//计算总页数
				Integer count = brushcreditMapper.countBrushThisMouthBySaleMan(recomCode, username);//((count+10)-1)/10
				//保存总页数
				session.setAttribute("pagesThisMouth",((count+10)-1)/10);
				outMap.put("data", list);
				return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询上月
	 */
	@Override
	public Map<String, Object> brushListLastMouth(Integer pageNum,
			HttpSession session) {
		
		Map<String, Object> outMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		try {
			//清除之前的session数据
			session.removeAttribute("pageNow");
			session.removeAttribute("pageNowThisMouth");
			session.removeAttribute("pages");
			session.removeAttribute("pagesThisMouth");
			//获取session中的用户类型
			Integer userType = (Integer)session.getAttribute("userType");
			String username = session.getAttribute("username").toString();
			//保存当前页
			session.setAttribute("pageNowLastMouth", pageNum);
			pageNum=pageNum==1?0:(pageNum-1)*10;  //初始化起始页码
			if(userType==2){//系统管理员和客服登录
				list = brushcreditMapper.selectBrushLastMouth(pageNum);
				//计算总页数
				Integer count = brushcreditMapper.countBrushLastMouth();//((count+10)-1)/10
				//保存总页数
				session.setAttribute("pagesLastMouth",((count+10)-1)/10);
				outMap.put("data", list);
				return outMap;
			}else if(userType==3){//客户经理登录
				String recomCode = session.getAttribute("recomCode").toString();
				
				list = brushcreditMapper.selectBrushLastMouthBySale(pageNum, recomCode);
				//计算总页数
				Integer count = brushcreditMapper.countBrushLastMouthBySale(recomCode);//((count+10)-1)/10
				//保存总页数
				session.setAttribute("pagesLastMouth",((count+10)-1)/10);
				outMap.put("data", list);
				return outMap;
			}else if(userType==4){//客户经理管理员登录
				String recomCode = "";//session.getAttribute("recomCode").toString();
				list = brushcreditMapper.selectBrushLastMouthBySaleMan(pageNum, recomCode, username);
				//计算总页数
				Integer count = brushcreditMapper.countBrushLastMouthBySaleMan(recomCode, username);//((count+10)-1)/10
				//保存总页数
				session.setAttribute("pagesLastMouth",((count+10)-1)/10);
				outMap.put("data", list);
				return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Map<String, Object> selectAllBrushcreditWait(Integer pageNum,
			HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		try {
			Integer userType = (Integer)session.getAttribute("userType");
			//保存当前页
			session.setAttribute("pageNow", pageNum);
			pageNum=pageNum==1?0:(pageNum-1)*10;  //初始化起始页码
			if(userType==1||userType==2){//系统管理员和客服登录
				list = brushcreditMapper.selectAllBrushcreditWait(pageNum);
				//计算总页数
				Integer count = brushcreditMapper.getCountWait();//((count+10)-1)/10
				//保存总页数
				session.setAttribute("pages",((count+10)-1)/10);
				outMap.put("data", list);
				return outMap;
			}else if(userType==3){//客户经理登录
				String recomCode = session.getAttribute("recomCode").toString();
				list = brushcreditMapper.selectAllBrushcreditWait(pageNum);
				//计算总页数
				Integer count = brushcreditMapper.getCountWait();//((count+10)-1)/10
				//保存总页数
				session.setAttribute("pages",((count+10)-1)/10);
				outMap.put("data", list);
				return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Integer getCountWait() {
		try {
			//获取总数量
			Integer count = brushcreditMapper.getCountWait();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	
	public Map<String, Object> brushReview(Long brushid, String beeWX,
			HttpSession session) {
		try {
			//获取任性刷审核数据
			Map<String, Object> outMap = brushcreditMapper.brushReview(brushid);
			//计算总金额
			Integer price = (Integer)outMap.get("PacPeriods")*(Integer)outMap.get("PacMonthlyPrice");
			outMap.put("price", price);
			//数据库中上传的身份证照片
			String card = outMap.get("CardImage").toString();
			JSONObject obj = JSON.parseObject(card);
			outMap.put("CardImage", obj.getString("firstPath"));
			//对身份证号码做处理
			String BeeCard = outMap.get("BeeCard").toString();
			String name = outMap.get("BeeName").toString();
			//获取身份证真实照片
			String cardImg = AliyunVerify.query(name, BeeCard);
			//解析返回的json
			com.alibaba.fastjson.JSONObject cardObj = JSON.parseObject(cardImg);
			String result = cardObj.getString("result");
			com.alibaba.fastjson.JSONObject resultobj = JSON.parseObject(result);
			String msg = resultobj.getString("pic");
			//身份证真实照片
			outMap.put("cardImg", msg);
			StringBuilder sb = new StringBuilder(BeeCard);
			BeeCard = sb.replace(6, 14, "********").toString();
			outMap.put("BeeCard", BeeCard);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Integer reviewBrush(Long brushid,String beeWX, Integer type,
			HttpSession session) {
		String json = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Integer result = brushcreditMapper.reviewFaceStatus(brushid, type);
			if(result==1&&type==1){//审核通过
				//获取客户ID
				Map<String, Object> map = brushcreditMapper.brushReview(brushid);
				Long beeid = (Long)map.get("beeID");
				//获取期数与每月金额，计算总金额
				Integer pacPeriods = (Integer)map.get("PacPeriods");
				Integer pacMonthlyPrice = (Integer)map.get("PacMonthlyPrice");
				Integer usedCredit = wbl_PollenDAO.getUsedCredit(beeid);
				//修改客户花粉信息
				Integer resultPollen = wbl_PollenDAO.updatePollenReview(beeid, usedCredit+(pacPeriods*pacMonthlyPrice));
				if(resultPollen==1){
					log.info("修改花粉额度成功");
				}else{
					log.info("修改花粉额度失败");
				}
				//发送推送信息
				JSONObject obj = new JSONObject();
				obj.put("openid", beeWX);
				obj.put("time", format.format(new Date()));
				String msg = UtilHttpSend.doPost("http://www.wisedp.com/BeeCost/push/faceCheckPush",obj.toString());
				log.info("推送返回数据："+msg);
			}else if(result==1&&type==0){//审核不通过
				//发送推送信息
				JSONObject obj = new JSONObject();
				obj.put("openid", beeWX);
				obj.put("reason", "身份证审核不通过");
				String msg = UtilHttpSend.doPost("http://www.wisedp.com/BeeCost/push/faceCheckPushNO",obj.toString());
				log.info("推送返回数据："+msg);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
