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
import com.wbdp.bee.dao.Wbl_PackageSkuDAO;
import com.wbdp.bee.entity.Wbl_PackageSkuEntity;
import com.wbdp.bee.service.Wbl_PackageSkuService;
/**
 * sku关联套餐业务实现类
 * @author 汪赛军
 * date:2017年7月21日上午11:26:08
 *
 */
@Service
public class Wbl_PackageSkuServiceImpl implements Wbl_PackageSkuService {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private Wbl_PackageSkuDAO wbl_PackageSkuDAO;
	/**
	 * 新增
	 */
	@Override
	public Map<String,Object> insertPackageSku(String json) {
		Wbl_PackageSkuEntity pacSku = new Wbl_PackageSkuEntity();
		Map<String,Object> outMap = new HashMap<String, Object>();
		try {
			//解析json数据
			JSONObject obj = JSON.parseObject(json);
			String skuId = obj.getString("skuID");
			String pacId = obj.getString("pacID");
			String payable = obj.getString("payable");
			String createDate = format.format(new Date());
			pacSku.setPacId(Long.parseLong(pacId));
			pacSku.setSkuId(Long.parseLong(skuId));
			pacSku.setCreateDate(createDate);
			pacSku.setPayable(Integer.parseInt(payable));
			Integer data = wbl_PackageSkuDAO.insertPackageSku(pacSku);
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
	 * 修改
	 */
	@Override
	public Integer updatePackageSku(String json) {
		
		return null;
	}
	/**
	 * 删除
	 */
	@Override
	public Integer deletePackageSku(Long id) {
		try {
			Integer data = wbl_PackageSkuDAO.deletePackageSku(id);
			return data;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获取列表
	 */
	@Override
	public  Map<String,Object> listPackageSku(Integer skuId) {
		Map<String,Object> outMap = new HashMap<String, Object>();
		try {
			List<Wbl_PackageSkuEntity> pacSku = wbl_PackageSkuDAO.listPackageSku(Long.parseLong(skuId.toString()));
			outMap.put("data", pacSku);
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
