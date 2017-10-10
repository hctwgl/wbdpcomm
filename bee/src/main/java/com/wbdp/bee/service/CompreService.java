package com.wbdp.bee.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.wbdp.bee.entity.Compre;

/**
 * 公司综合数据管理业务接口
 * @author 汪赛军
 * date:2017年9月17日上午8:48:44
 *
 */
public interface CompreService {
	
	/**
	 * 获取公司综合数据列表
	 * @param pageNum
	 * @param session
	 * @return
	 */
	public Map<String,Object> compreList(Integer pageNum,HttpSession session);
	
	 /**
     * 客户经理上传综合数据
     * @param session
     * @return
     */
    public Map<String, Object> uploadClientZongHe(String company,HttpSession session);
    
    /**
     * 客户经理新增综合数据
     * @param compre
     * @param session
     * @return
     */
    public Map<String,Object> insertCompre(Compre compre,HttpSession session);
    
}
