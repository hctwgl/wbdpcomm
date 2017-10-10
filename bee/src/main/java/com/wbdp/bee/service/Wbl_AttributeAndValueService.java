package com.wbdp.bee.service;

import java.util.Map;

import com.wbdp.bee.entity.Wbl_AttributeView;

public interface Wbl_AttributeAndValueService {
	
	/**
	 * 获取商品的属性名与属性值
	 * @return
	 */
	public Map<String,Object> getWbl_AttributeAndValue(String json);
	
	/**
	 * 分页查询属性名与属性值对应视图
	 * @param pageNum
	 * @return
	 */
	public Map<String,Object> selectAttributeView(Integer pageNum);
	
	/**
	 * 获取所有的属性名数据
	 * @return
	 */
	public Map<String,Object> getWbl_Attribute();
	
	/**
	 * 新增属性名与属性值
	 * @param wbl_AttributeView
	 * @param type
	 * @return
	 */
	public Map<String,Object> insertAttributeAndValue(Wbl_AttributeView wbl_AttributeView,Integer type);
}
