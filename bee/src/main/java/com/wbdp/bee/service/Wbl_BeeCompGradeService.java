package com.wbdp.bee.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.wbdp.bee.entity.Wbl_BeecompgradeEntity;

/**
 * 公司信用评级业务接口类
 * @author 汪赛军
 * date:2017年7月18日下午4:12:38
 *
 */
public interface Wbl_BeeCompGradeService {
	
	/**
	 * 获取公司评级列表
	 * @param pageNum
	 * @param session
	 * @return
	 */
	public Map<String,Object> listBeeCompGrade(Integer pageNum,HttpSession session);
	
	/**
	 * 新增公司评级数据
	 * @param wbl_BeecompgradeEntity
	 * @return
	 */
	public Integer insertBeeCompGrade(Wbl_BeecompgradeEntity wbl_BeecompgradeEntity);
	
	/**
	 * 修改公司评级数据
	 * @param wbl_BeecompgradeEntity
	 * @return
	 */
	public Integer updateBeeCompGrade(Wbl_BeecompgradeEntity wbl_BeecompgradeEntity);
}
