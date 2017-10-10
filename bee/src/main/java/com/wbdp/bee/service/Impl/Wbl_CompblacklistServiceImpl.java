package com.wbdp.bee.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.bee.dao.CompblacklistMapper;
import com.wbdp.bee.dao.Wbl_CompblacklistDAO;
import com.wbdp.bee.entity.CompblacklistNewEntity;
import com.wbdp.bee.entity.Wbl_CompblacklistEntity;
import com.wbdp.bee.service.Wbl_CompblacklistService;

/**
 * 公司黑名单业务实现类
 * @author 汪赛军
 * date:2017年7月22日上午10:29:50
 *
 */
@Service
public class Wbl_CompblacklistServiceImpl implements Wbl_CompblacklistService {
	@Autowired
	private Wbl_CompblacklistDAO wbl_CompblacklistDAO;
	@Autowired
	private CompblacklistMapper compblacklistMapper;
	/**
	 * 获取公司黑名单列表
	 */
	@Override
	public Map<String, Object> CompblackAllList(Integer pageNum) {
		Map<String, Object> outMap = new HashMap<String, Object>(); 
		try {
			//将当前页存入返回数据集合
			outMap.put("pageNow", pageNum);
			//处理初始化页数
			pageNum = (pageNum-1)*10;
			//计算总页数
			Integer pages = ((wbl_CompblacklistDAO.getCount()+10)-1)/10;
			outMap.put("pages", pages);
			List<Wbl_CompblacklistEntity> list = wbl_CompblacklistDAO.CompblackAllList(pageNum);
			outMap.put("data", list);
			outMap.put("status", "SUCCESS");
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", "");
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}

	@Override
	public Integer getCount() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 新增公司黑名单数据
	 */
	@Override
	public Integer insertCompblack(String json,HttpSession session) {
		Wbl_CompblacklistEntity compblack = new Wbl_CompblacklistEntity();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//解析json
			JSONObject obj = JSON.parseObject(json);
			String company = obj.getString("company");
			String creatby = session.getAttribute("username").toString();
			//查询是否有重复数据
			String str = wbl_CompblacklistDAO.getCompany(company);
			if(str!=null &&str !=""){
				return 0;
			}else{
				compblack.setCompany(company);
				compblack.setCreatby(creatby);
				compblack.setCreatdate(new Date());
				Integer data = wbl_CompblacklistDAO.insertCompblack(compblack);
				return data;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 删除公司黑名单数据
	 */
	@Override
	public Integer deleteCompblack(String json) {
		try {
			//解析json
			JSONObject obj = JSON.parseObject(json);
			String id = obj.getString("id");
			return wbl_CompblacklistDAO.deleteCompblack(Long.parseLong(id));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 新增公司黑名单
	 */
	@Override
	public Integer insertCompBlack(
			CompblacklistNewEntity compblacklistNewEntity, HttpSession session) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String username = (String)session.getAttribute("username");
			compblacklistNewEntity.setCreatBy(username);
			compblacklistNewEntity.setCreatDate(format.format(new Date()));
			Integer result = compblacklistMapper.insertCompBlack(compblacklistNewEntity);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取公司黑名单列表
	 */
	@Override
	public Map<String, Object> selectCompBlack(Integer pageNum,
			HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		List<CompblacklistNewEntity> list = null;
		try {
			Integer userType = (Integer)session.getAttribute("userType");
			switch (userType) {
			case 1://系统管理员，查看全部
				list = compblacklistMapper.selectAllCompBlack(pageNum);
				break;
			case 2://客服，查看全部
				list = compblacklistMapper.selectAllCompBlack(pageNum);
				break;
			case 3://客户经理，查看他所添加的公司
				
				break;
			case 4://客户经理管理员,查看他所添加的公司以及他名下客户经理添加的公司
						
				break;
			default:
				break;
			}
			outMap.put("data", list);
			return outMap;
		} catch (Exception e) {
			
		}
		return null;
	}
}
