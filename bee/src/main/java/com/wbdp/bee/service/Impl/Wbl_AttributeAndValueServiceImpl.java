package com.wbdp.bee.service.Impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;















import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.bee.dao.Wbl_AttributeDAO;
import com.wbdp.bee.dao.Wbl_AttributevalueDAO;
import com.wbdp.bee.entity.Wbl_AttributeEntity;
import com.wbdp.bee.entity.Wbl_AttributeView;
import com.wbdp.bee.entity.Wbl_AttributevalueEntity;
import com.wbdp.bee.service.Wbl_AttributeAndValueService;
@Service
public class Wbl_AttributeAndValueServiceImpl implements
		Wbl_AttributeAndValueService {
	@Autowired
	private Wbl_AttributeDAO wbl_AttributeDAO;
	@Autowired
	private Wbl_AttributevalueDAO wbl_AttributevalueDAO;
	
	//获取商品属性名与属性值,在前台以下拉列表的形式展示
	@Override
	public Map<String, Object> getWbl_AttributeAndValue(String json) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//解析json
			JSONObject obj = JSON.parseObject(json);
			String attribute = obj.getString("attribute");
			Wbl_AttributeEntity wbl_AttributeEntity = wbl_AttributeDAO.getWbl_Attribute(attribute);
			List<Wbl_AttributevalueEntity> list = wbl_AttributevalueDAO.listWbl_Attributevalue(wbl_AttributeEntity.getId());
			outMap.put("data", list);
			outMap.put("status", "SUCCESS");
			System.out.println("后台返回参数："+JSON.toJSONString(outMap).toString());
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", "");
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}

	@Override
	public Map<String, Object> selectAttributeView(Integer pageNum) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//保存当前页数
			outMap.put("pageNow", pageNum);
			//处理初始化起始页码
			 pageNum=(pageNum-1)*10;
			 //计算总页数
			Integer count =  wbl_AttributeDAO.getViewcount();
			 Integer pages = ((count+10)-1)/10;
			List<Wbl_AttributeView> list = wbl_AttributeDAO.selectAttribute(pageNum);
			outMap.put("pages", pages);
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
	public Map<String, Object> getWbl_Attribute() {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			List<Wbl_AttributeEntity>list = wbl_AttributeDAO.listWbl_Attribute();
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
	/**
	 * 新增属性名与属性值
	 * @param wbl_AttributeView
	 * @param type
	 * @return
	 */
	@Override
	public Map<String, Object> insertAttributeAndValue(
			Wbl_AttributeView wbl_AttributeView, Integer type) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//type等于1代表用户自定义了属性名称，等于2则代表用户选择了已有属性名称
			if(type==1){
				Wbl_AttributeEntity wbl_AttributeEntity = new Wbl_AttributeEntity();
				wbl_AttributeEntity.setAttribute(wbl_AttributeView.getAttribute());
				wbl_AttributeEntity.setCreatdate(new Date());
				//新增属性名称
				wbl_AttributeDAO.insertAttribute(wbl_AttributeEntity);
				//得到属性名称ID
				Long id = wbl_AttributeEntity.getId();
				Wbl_AttributevalueEntity wbl_AttributevalueEntity = new Wbl_AttributevalueEntity();
				wbl_AttributevalueEntity.setAttributeid(id);
				wbl_AttributevalueEntity.setAttributevalue(wbl_AttributeView.getAttributeValue());
				wbl_AttributevalueEntity.setCreatdate(new Date());
				Integer result = wbl_AttributevalueDAO.insertWbl_Attributevalue(wbl_AttributevalueEntity);
				outMap.put("data", result);
				outMap.put("status", "SUCCESS");
				return outMap;
			}else{
				Wbl_AttributevalueEntity wbl_AttributevalueEntity = new Wbl_AttributevalueEntity();
				wbl_AttributevalueEntity.setAttributeid(Long.parseLong(wbl_AttributeView.getAttribute()));
				wbl_AttributevalueEntity.setAttributevalue(wbl_AttributeView.getAttributeValue());
				wbl_AttributevalueEntity.setCreatdate(new Date());
				Integer result = wbl_AttributevalueDAO.insertWbl_Attributevalue(wbl_AttributevalueEntity);
				outMap.put("data", result);
				outMap.put("status", "SUCCESS");
				return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", "");
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}
}
