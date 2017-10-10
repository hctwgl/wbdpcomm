package com.wbdp.bee.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbdp.bee.dao.Wbl_BeecompgradeDAO;
import com.wbdp.bee.dao.Wbl_SocialdataDAO;
import com.wbdp.bee.entity.Wbl_BeecompgradeEntity;
import com.wbdp.bee.entity.Wbl_SocialdataEntity;
import com.wbdp.bee.service.Wbl_BeeCompGradeService;

/**
 * 公司评级业务实现类
 * @author 汪赛军
 * date:2017年7月18日下午4:13:17
 *
 */
@Service
public class Wbl_BeeCompGradeServiceImpl implements Wbl_BeeCompGradeService {
	
	@Autowired
	private Wbl_SocialdataDAO wbl_SocialdataDAO;
	@Autowired
	private Wbl_BeecompgradeDAO wbl_BeecompgradeDAO;
	/**
	 * 获取公司评级列表
	 */
	@Override
	public Map<String, Object> listBeeCompGrade(Integer pageNum,
			HttpSession session) {
		//初始化返回集合对象
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//将当前页放入集合
			outMap.put("pageNow", pageNum);
			//处理初始化起始页码
			 pageNum=(pageNum-1)*10; 
			 //计算总页数
			 Integer count = wbl_SocialdataDAO.listCount().size();
			 Integer pages = ((count+10)-1)/10;
			 List<Wbl_SocialdataEntity> socialList = wbl_SocialdataDAO.listWblSocialdata(pageNum);
			 outMap.put("pages", pages);
			 outMap.put("data", socialList);
			 outMap.put("status", "SUCCESS");
			 return outMap;
		} catch (Exception e) {
			outMap.put("data", "");
			 outMap.put("status", "EXCEPTION");
			 return outMap;
		}
	}
	
	/**
	 * 新增公司评级数据
	 */
	@Override
	public Integer insertBeeCompGrade(
			Wbl_BeecompgradeEntity wbl_BeecompgradeEntity) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			wbl_BeecompgradeEntity.setCreatDate(format.format(new Date()));
			Integer type = wbl_BeecompgradeDAO.insertBeecompgrade(wbl_BeecompgradeEntity);
			return type;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 修改公司评级数据
	 */
	@Override
	public Integer updateBeeCompGrade(
			Wbl_BeecompgradeEntity wbl_BeecompgradeEntity) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			wbl_BeecompgradeEntity.setUpdateTime(format.format(new Date()));
			Integer type = wbl_BeecompgradeDAO.updateBeecompgrade(wbl_BeecompgradeEntity);
			return type;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
