package com.wbdp.bee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_AttributevalueEntity;



public interface Wbl_AttributevalueDAO {
	/**
	 * 获取属性值列表
	 * @return
	 */
    public List<Wbl_AttributevalueEntity> getWbl_Attributevalue();
    
    /**
     * 根据属性名ID获取属性值对象列表
     * @param attributeid
     * @return
     */
    public List<Wbl_AttributevalueEntity> listWbl_Attributevalue(@Param("attributeid")Long attributeid);
    
    /**
     * 根据属性值查询属性值对象
     * @param attributevalue
     * @return
     */
    public Wbl_AttributevalueEntity selectWbl_AttributevalueEntity(@Param("attributevalue")String attributevalue);
    
    /**
     * 新增属性值
     * @param wbl_AttributevalueEntity
     * @return
     */
    public int insertWbl_Attributevalue(Wbl_AttributevalueEntity wbl_AttributevalueEntity);
    
    /**
     * 根据ID查询属性值
     * @param attributevalue
     * @return
     */
    public String selectWbl_AttributevalueforID(@Param("id")Long id);
    
    /**
     * 批量查询sku属性值
     * @param map
     * @return
     */
    public List<String> batchValue(@Param("map")Map<String,Object> map);
}