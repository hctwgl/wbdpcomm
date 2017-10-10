package com.wbdp.bee.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;










import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbdp.bee.dao.Wbl_ShoppingcartDAO;
import com.wbdp.bee.entity.Wbl_ShoppingcartEntity;
import com.wbdp.bee.service.Wbl_ShoppingcartService;
/**
 * 购物车业务实现类
 * @author 汪赛军
 * date:2017年7月18日上午9:35:45
 *
 */
@Service
public class Wbl_ShoppingcartServiceImpl implements Wbl_ShoppingcartService {
	@Autowired
	private Wbl_ShoppingcartDAO wbl_ShoppingcartDAO;
	/**
	 * 获取购物车以及关联数据列表
	 */ 
	@Override
	public Map<String, Object> listShoppingcart(Integer pageNum,HttpSession session) {
		//初始化返回数据集合对象
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//保存当前页
			outMap.put("pageNow", pageNum);
			//将当前页存入session中
			//session.setAttribute("pageNow", pageNum);
			//处理初始化起始页码
			 pageNum=(pageNum-1)*10;  
			//计算总页数
			 Integer count = wbl_ShoppingcartDAO.getCount();
			 Integer pages = ((count+10)-1)/10;
			 List<Wbl_ShoppingcartEntity> shopList = wbl_ShoppingcartDAO.listShoppingcart(pageNum);
			 outMap.put("data", shopList);
			 outMap.put("status", "SUCCESS");
			 outMap.put("pages", pages);
			 return outMap;
		} catch (Exception e) {
			 e.printStackTrace();
			 outMap.put("data", "");
			 outMap.put("status", "EXCEPTION");
			 outMap.put("pages", "");
			return outMap;
		}
	}
	@Override
	public Integer getPage() {
		try {
			//计算总页数
			 Integer count = wbl_ShoppingcartDAO.getCount();
			 Integer pages = ((count+10)-1)/10;
			 return pages;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
