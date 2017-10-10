package com.wbdp.bee.dao;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_BeeEntity;




public interface Wbl_BeeDao {


    /**
     * 方法名: BeeAllList   
     * 方法描述:  客户所有信息
     * 入参描述:  pageNum:页码
     * 回调描述:   
     * 创建人:wisedata004  
     * 创建时间: 2017年7月10日
     */
    public List<Map<String,String>> BeeAllList(@Param("pageNum")Integer pageNum);
    public Integer BeeAllListSize();
    
    
    /**
     * 方法名: BeeAllListOfID   
     * 方法描述: 客户信息详细 
     * 入参描述: id:客户id 
     * 回调描述:   
     * 创建人:wisedata004  
     * 创建时间: 2017年7月17日
     */
    public List<Wbl_BeeEntity> BeeAllListOfID(@Param("id")Long id);
    
    /**
     * 审核并修改用户状态
     * @param wbl_BeeEntity
     * @return
     */
    public Integer updateBee(Wbl_BeeEntity wbl_BeeEntity);
    
    /**
     * 用户上传集团客户数据，保存至数据库
     * @param beeList
     * @return
     */
    public Integer uploadClient(@Param("beeList")List<Wbl_BeeEntity> beeList);
    
    /**
     * 根据业务员推荐码查询客户经理所属客户
     * @param recomCode
     * @return
     */
    public List<Map<String,String>> selectBeeByCode(@Param("recomCode")String recomCode,@Param("pageNum")Integer pageNum);
    
    
    /**
     * 根据业务员推荐码查询业务员所属客户数量
     * @param recomCode
     * @return
     */
    public Integer selectBeeByCodeNum(@Param("recomCode")String recomCode);
    /**
     * 客户经理管理员查询客户
     * @return
     */
    public List<Map<String,String>> selectBeeByCodeMan(@Param("recomCode")String recomCode,@Param("username")String username,@Param("pageNum")Integer pageNum);
   /**
    * 客户经理管理员查询客户数量
    * @param recomCode
    * @param username
    * @return
    */
    public Integer selectBeeByManNum(@Param("recomCode")String recomCode,@Param("username")String username);
    /**
     * 获取所有用户的手机号与ID
     * @return
     */
    public List<Wbl_BeeEntity> getPhoneAndID();
    
    /**
     * 获取所有用户的手机号与微信号
     * @return
     */
    public List<Wbl_BeeEntity> getPhoneAndWX();
    
    /**
     * 客户经理下载其名下公拉私客户数据
     * @param compony
     * @return
     */
    public List<Map<String,Object>> downLoadBee(@Param("id")Long id);
    
    /**
     * 根据手机号查询客户是否存在
     * @param id
     * @return
     */
    public Wbl_BeeEntity selectBeeByPhone(@Param("phone")String phone);
    
   
}
