package com.wbdp.bee.service.Impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.bee.dao.Wbl_PackageDAO;
import com.wbdp.bee.entity.Wbl_PackageEntity;
import com.wbdp.bee.service.Wbl_PackageService;


/**
 * 话费套餐业务实现类
 * @author 汪赛军
 * date:2017年7月20日下午3:52:12
 *
 */
@Service
public class Wbl_PackageServiceImpl implements Wbl_PackageService {
	
	@Autowired
	private Wbl_PackageDAO wbl_PackageDAO;
	
	/**
	 * 新增话费套餐数据
	 */
	@Override
	public Map<String, Object> insertPackage(String json) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Wbl_PackageEntity pac = new Wbl_PackageEntity();
		//初始化返回集合对象
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//解析json数据
			JSONObject obj = JSON.parseObject(json);
			String callPackage = obj.getString("callPackage");
			String supId = obj.getString("supId");
			String details = obj.getString("details");
			String pacPrice = obj.getString("pacPrice");
			String createDate = format.format(new Date());
			pac.setCallPackage(callPackage);
			pac.setSupId(Long.parseLong(supId));
			pac.setDetails(details);
			pac.setPacPrice(Integer.parseInt(pacPrice));
			pac.setCreateDate(createDate);
			Integer data = wbl_PackageDAO.insertPackage(pac);
			outMap.put("data", data);
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
	 * 删除话费套餐数据
	 */
	@Override
	public Map<String, Object> deletePackage(String json) {
		//初始化返回集合对象
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//解析json数据
			JSONObject obj = JSON.parseObject(json);
			String id = obj.getString("id");
			Integer data = wbl_PackageDAO.deletePackage(Long.parseLong(id));
			outMap.put("data", data);
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
	 * 修改话费套餐数据
	 */
	@Override
	public Map<String, Object> updatePackage(String json) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Wbl_PackageEntity pac = new Wbl_PackageEntity();
		//初始化返回集合对象
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//解析json数据
			JSONObject obj = JSON.parseObject(json);
			String callPackage = obj.getString("callPackage");
			String supId = obj.getString("supId");
			String details = obj.getString("details");
			String updateTime = format.format(new Date());
			String id = obj.getString("id");
			pac.setCallPackage(callPackage);
			pac.setSupId(Long.parseLong(supId));
			pac.setDetails(details);
			pac.setId(Long.parseLong(id));
			pac.setUpdateTime(updateTime);
			Integer data = wbl_PackageDAO.updatePackage(pac);
			outMap.put("data", data);
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
	 * 关联查询话费套餐数据（分页）
	 */
	@Override
	public Map<String, Object> listPackage(Integer pageNum) {
		
		//初始化返回集合对象
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//将当前页存入集合
			outMap.put("pageNow", pageNum);
			//处理初始化页码
			pageNum = (pageNum-1)*5;
			//计算总页数
			Integer pages = ((wbl_PackageDAO.getCount()+5)-1)/5;
			outMap.put("pages", pages);
			//查询关联话费套餐数据
			List<Wbl_PackageEntity> pacList = wbl_PackageDAO.listPackage(pageNum);
			outMap.put("data", pacList);
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
	 * 根据skuid查询套餐数据
	 */
	@Override
	public Map<String, Object> getPackage(Long skuID) {
		//初始化返回集合对象
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			List<Wbl_PackageEntity> pacList = wbl_PackageDAO.getPackage(skuID);
			outMap.put("data", pacList);
			outMap.put("status", "SUCCESS");
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", "");
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}

}
