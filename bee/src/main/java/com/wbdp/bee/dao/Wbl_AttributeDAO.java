package com.wbdp.bee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_AttributeEntity;
import com.wbdp.bee.entity.Wbl_AttributeView;



public interface Wbl_AttributeDAO {
	
	/**
	 * 获取属性名列表
	 * @return
	 */
    public List<Wbl_AttributeEntity> listWbl_Attribute();
    
    /**
     * 获取属性名对象
     * @return
     */
    public Wbl_AttributeEntity getWbl_Attribute(@Param("attribute")String attribute);
    /**
     * 分页查询属性名与属性值对应视图
     * @param pageNum
     * @return
     */
    public List<Wbl_AttributeView> selectAttribute(@Param("pageNum")Integer pageNum); 

    /**
     * 获取属性名与属性值对应视图长度
     * @param pageNum
     * @return
     */
    public Integer getViewcount(); 
    /**
     * 新增属性名称
     * @param wbl_AttributeEntity
     * @return
     */
    public Integer insertAttribute(Wbl_AttributeEntity wbl_AttributeEntity);
}