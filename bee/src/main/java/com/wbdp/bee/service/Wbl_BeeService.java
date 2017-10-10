package com.wbdp.bee.service;



import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wbdp.bee.entity.BrushcreditEntity;
import com.wbdp.bee.entity.Compre;
import com.wbdp.bee.entity.Wbl_BeeEntity;

public interface Wbl_BeeService {

    
   /**
    * 
    * 方法名: BeeAllList   
    * 方法描述: 客户信息列表 
    * 入参描述: id:客户ID，pageNum:页码
    * 回调描述:   
    * 创建人:wisedata004  
    * 创建时间: 2017年7月10日
    */
    public Map<String, Object> BeeAllList(Integer pageNum,HttpSession session);
    
    /**
     * 客服删除客户（假删）
     * @param id
     * @param session
     * @return
     */
    public Map<String,Object> delBee(Long id,HttpSession session);
 
    /**
     * 方法名: BeeAllListOfID   
     * 方法描述: 客户信息详细 
     * 入参描述: id:客户id
     * 回调描述:   
     * 创建人:wisedata004  
     * 创建时间: 2017年7月17日
     */
    public Map<String, Object> BeeAllListOfID(Long id);
    
    /**
     * 用户上传集团客户数据（公对公）
     * @param session
     * @return
     */
    public Map<String, Object> uploadClient(String company,HttpSession session);
    
    /**
     * 用户上传集团客户数据（公拉私）
     * @param session
     * @return
     */
    public Map<String, Object> uploadClientPrivate(String company,HttpSession session);
    
   
    /**
     * 客户经理下载其名下公拉私客户数据
     * @param session
     */
    public String downLoadBee(Long id,HttpSession session,HttpServletResponse response); 
    
    /**
     * 客户经理为空户单独增加套餐
     * @param session
     * @param id
     * @param brushcreditEntity
     * @return
     */
    public Map<String,Object> insertBeePackage(HttpSession session,Integer userType,BrushcreditEntity brushcreditEntity);
    /**
     * 客户管理页面客户经理新增公对公客户与套餐数据
     * @param session
     * @param wbl_BeeEntity
     * @param brushcreditEntity
     * @return
     */
    public Map<String,Object> insertBeeAndPackage(HttpSession session,Wbl_BeeEntity wbl_BeeEntity,BrushcreditEntity brushcreditEntity);
}
