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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.bee.dao.BeecompgradeMapper;
import com.wbdp.bee.entity.BeecompgradeNewEntity;
import com.wbdp.bee.service.BeeCompanyNewService;
import com.wbdp.bee.util.ZhimaAuthEngineOrganizationauth;
@Service
public class BeeCompanyNewServiceImpl implements BeeCompanyNewService {
	//日志
	private Logger log=Logger.getLogger(BeeCompanyNewServiceImpl.class);
	
	@Autowired
	private BeecompgradeMapper beecompgradeMapper;
	
	/**
	 * 获取公司列表
	 */
	@Override
	public Map<String, Object> selectBeeCompany(HttpSession session,
			Integer pageNum) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		List<BeecompgradeNewEntity> list = null;
		try {
			log.info("查询公司信息获取的页码"+pageNum);
			Integer userType = (Integer)session.getAttribute("userType");
			if(userType==1||userType==2){//客服或系统管理员
				//保存当前页
				session.setAttribute("pageNow", pageNum);
				//处理页码,如果页码等于1则返回0，否则减去一乘以每页显示数
				pageNum=pageNum==1?0:(pageNum-1)*10;
				Integer count = beecompgradeMapper.getCount();//((count+10)-1)/10
				//保存总页数
				session.setAttribute("pages",((count+10)-1)/10);
				list = beecompgradeMapper.selectBeeCompany(pageNum);
				outMap.put("data", list);
			}else if(userType==3){//客户经理
				//保存当前页
				session.setAttribute("pageNow", pageNum);
				//处理页码,如果页码等于1则返回0，否则减去一乘以每页显示数
				pageNum=pageNum==1?0:(pageNum-1)*10;
				String phone = session.getAttribute("username").toString();
				Integer count = beecompgradeMapper.listCompanyCount(phone);//((count+10)-1)/10
				System.out.println("count:"+count);
				//保存总页数
				session.setAttribute("pages",((count+10)-1)/10);
				list = beecompgradeMapper.listCompanyBySale(phone);
				outMap.put("data", list);
			}else if(userType==4){
				//保存当前页
				session.setAttribute("pageNow", pageNum);
				//处理页码,如果页码等于1则返回0，否则减去一乘以每页显示数
				pageNum=pageNum==1?0:(pageNum-1)*10;
				String phone = session.getAttribute("username").toString();
				Integer count = beecompgradeMapper.listCompanyByManCount(phone);//((count+10)-1)/10
				//保存总页数
				session.setAttribute("pages",((count+10)-1)/10);
				list = beecompgradeMapper.listCompanyByMan(phone);
				outMap.put("data", list);
			}
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> selectBeeCompanyByID(HttpSession session, Long id) {
		
		return null;
	}
	/**
	 * 新增公司信息
	 */
	@Override
	public Map<String, Object> insertBeeCompany(HttpSession session,
			BeecompgradeNewEntity beecompgradeNewEntity) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		try {
			log.info("新增公司数据："+beecompgradeNewEntity.getCompany());
			String creatBy = session.getAttribute("username").toString();
			//获取公司信用评级openID
			String result = ZhimaAuthEngineOrganizationauth.zhimaAuthEngine(beecompgradeNewEntity.getCompanyCode(), beecompgradeNewEntity.getCompany());
			JSONObject obj = (JSONObject) JSON.parse(result);
			//判断接口调用是否成功
			if("true".equals(obj.getString("success"))){
				log.info("获取企业信用分成功");
				String resultScore = ZhimaAuthEngineOrganizationauth.zhimaCreditEpScoreGet(obj.getString("open_id"));
				JSONObject objScore = (JSONObject) JSON.parse(resultScore);
				Integer score = objScore.getInteger("zm_score");
				log.info("查询到的信用分："+score);
				beecompgradeNewEntity.setAliScore(score);
			}else{
				log.info("获取企业信用分失败");
				outMap.put("msg", "获取企业信用分失败，请检查公司名称与信用代码是否正确");
				outMap.put("data", 0);
				return outMap;
			}
			//获取企业信用评级分数
			beecompgradeNewEntity.setCreatBy(creatBy);
			beecompgradeNewEntity.setCreatDate(format.format(new Date()));
			Integer resultstr = beecompgradeMapper.insertBeeCompany(beecompgradeNewEntity);
			outMap.put("data", resultstr);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> updateCompanyGrade(HttpSession session,
			BeecompgradeNewEntity beecompgradeNewEntity) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		try {
			log.info("公司评级："+beecompgradeNewEntity.getGrade());
			beecompgradeNewEntity.setUpdateTime(format.format(new Date()));
			Integer result = beecompgradeMapper.updateCompanyGrade(beecompgradeNewEntity);
			outMap.put("data", result);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 拉黑公司
	 */
	@Override
	public Integer blackBeeComp(BeecompgradeNewEntity beecompgradeNewEntity,
			HttpSession session) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		try {
			log.info("拉黑公司："+beecompgradeNewEntity.getBlackReason());
			String blackBy = session.getAttribute("username").toString();
			beecompgradeNewEntity.setBlackBy(blackBy);
			beecompgradeNewEntity.setUpdateTime(format.format(new Date()));
			beecompgradeNewEntity.setCompStatus(1);
			Integer result = beecompgradeMapper.blackCompany(beecompgradeNewEntity);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查看公司黑名单信息
	 */
	@Override
	public Map<String, Object> selectBeeCompanyBlack(HttpSession session,
			Integer pageNum) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			log.info("查询公司黑名单信息获取的页码"+pageNum);
			//保存当前页
			session.setAttribute("pageNow", pageNum);
			//处理页码,如果页码等于1则返回0，否则减去一乘以每页显示数
			pageNum=pageNum==1?0:(pageNum-1)*10;
			Integer count = beecompgradeMapper.getCountBlack();//((count+10)-1)/10
			//保存总页数
			session.setAttribute("pages",((count+10)-1)/10);
			List<BeecompgradeNewEntity> list = beecompgradeMapper.selectBeeCompanyBlack(pageNum);
			outMap.put("data", list);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Integer outBlackCompany(Long id) {
		try {
			Integer result = beecompgradeMapper.outBlackCompany(id);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Map<String,Object> listCompany(HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			String phone = session.getAttribute("username").toString();
			List<BeecompgradeNewEntity> list = beecompgradeMapper.listCompany(phone);
			outMap.put("data", list);
			return outMap;
		} catch (Exception e) {
			
		}
		return null;
	}
	
	/**
	 * 客服审核公司信用并评级
	 */
	@Override
	public Map<String, Object> reviewCompany(Long id, HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//通过ID查询公司信息
			BeecompgradeNewEntity company = beecompgradeMapper.selectBeeCompanyByID(id);
			outMap.put("data", company);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 客服审核公司完毕
	 */
	@Override
	public Integer companyYseOrNo(BeecompgradeNewEntity beecompgradeNewEntity,
			HttpSession session) {
		try {
			if("暂无数据".equals(beecompgradeNewEntity.getAliScore())){
				beecompgradeNewEntity.setAliScore(0);
			}else if(beecompgradeNewEntity.getAliScore()==null||beecompgradeNewEntity.getAliScore()==0){
				beecompgradeNewEntity.setAliScore(0);
			}
			Integer result = beecompgradeMapper.companyYseOrNo(beecompgradeNewEntity);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 跳转公司资料修改页面，并传递数据
	 */
	@Override
	public Map<String, Object> toupdateCompany(Long id, HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//通过ID查询公司信息
			BeecompgradeNewEntity company = beecompgradeMapper.selectBeeCompanyByID(id);
			outMap.put("data", company);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 修改公司信息
	 */
	@Override
	public Map<String, Object> updateBeeCompany(HttpSession session,
			BeecompgradeNewEntity beecompgradeNewEntity) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			beecompgradeNewEntity.setUpdateTime(format.format(new Date()));
			beecompgradeNewEntity.setCheckStatus(1);
			Integer result = beecompgradeMapper.updateBeeCompany(beecompgradeNewEntity);
			outMap.put("data", result);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
