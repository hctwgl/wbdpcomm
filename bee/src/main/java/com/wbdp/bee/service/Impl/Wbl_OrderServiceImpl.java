package com.wbdp.bee.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.bee.api.aliyun.AliyunBankCard;
import com.wbdp.bee.api.aliyun.ZhimaCreditAntifraudScore;
import com.wbdp.bee.dao.Wbl_AttributevalueDAO;
import com.wbdp.bee.dao.Wbl_BeeDao;
import com.wbdp.bee.dao.Wbl_OrderDAO;
import com.wbdp.bee.entity.Wbl_BeeEntity;
import com.wbdp.bee.entity.Wbl_OrderEntity;
import com.wbdp.bee.entity.Wbl_OrderReviewModel;
import com.wbdp.bee.service.Wbl_OrderService;
import com.wbdp.bee.util.HttpTest;

/**
 * 订单业务实现类
 * @author 汪赛军
 * date:2017年7月17日下午3:30:18
 *
 */
@Service
public class Wbl_OrderServiceImpl implements Wbl_OrderService {
	@Autowired
	private Wbl_OrderDAO wbl_OrderDAO;
	@Autowired
	private Wbl_BeeDao wbl_BeeDao;
	@Autowired
	private Wbl_AttributevalueDAO wbl_AttributevalueDAO;
	/**
	 * 订单列表（分页）  
	 */
	@Override
	public Map<String, Object> listOrder(Integer pageNum,HttpSession session) {
		//初始化返回的集合对象
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//保存当前页数
			outMap.put("pageNow", pageNum);
			
			//处理初始化起始页码
			 pageNum=(pageNum-1)*10;  
			 //计算总页数
			 Integer count = wbl_OrderDAO.getCount();
			 Integer pages = ((count+10)-1)/10;
			 //将总页数保存到session中
			 //获取订单以及关联列表
			 List<Wbl_OrderEntity> orderList = wbl_OrderDAO.listOrder(pageNum);
			 outMap.put("data", orderList);
			 outMap.put("status", "SUCCESS");
			 outMap.put("pages", pages);
			 return outMap;
		} catch (Exception e) {
			 e.printStackTrace();
			 outMap.put("data", "");
			 outMap.put("status", "EXCEPTION");
			 return outMap;
		}
	}
	
	/**
	 * ajax定时刷新获取未审核订单数量
	 * @return
	 */
	@Override
	public Integer getOrderCount() {
		try {
			Integer count = wbl_OrderDAO.getOrderCount();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 审核订单并修改订单状态
	 */
	@Override
	public Integer updateOrder(String json) {
		Wbl_OrderEntity order = new Wbl_OrderEntity();
		Wbl_BeeEntity bee = new Wbl_BeeEntity();
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> valueMap = new HashMap<String, Object>();
		List<Long> valueList = new ArrayList<Long>();
		StringBuffer valueBuf = new StringBuffer();
		try {
			//解析json数据
			JSONObject obj = JSON.parseObject(json);
			//订单id
			String id = obj.getString("id");
			String beeid = obj.getString("beeid");
			String orderState = obj.getString("orderState");
			String reason =  obj.getString("reason");
			//审核通过
			if("1".equals(orderState)){
				//审核通过修改用户状态
				bee.setId(Long.parseLong(beeid));
				bee.setBeestatus(0);
				wbl_BeeDao.updateBee(bee);
				//获取推送所需数据
				Wbl_OrderReviewModel orderReview = wbl_OrderDAO.listOrderReview(Long.parseLong(id));
				//封装推送数据
				map.put("orderStatus", 0);
				map.put("goodName", orderReview.getGoodsName());
				map.put("ownerwx", orderReview.getBeeWX());
				map.put("reason", "");
				//获取手机属性值
				String valueStr = orderReview.getValueStr();
				String [] value = valueStr.split(",");
				valueList.add(Long.parseLong(value[0]));
				valueList.add(Long.parseLong(value[1]));
				valueMap.put("valueIDs", valueList);
				//得到批量查询属性值的结果集
				List<String> attriValue = wbl_AttributevalueDAO.batchValue(valueMap);
				//拼接手机属性
				valueBuf.append(attriValue.get(0));
				valueBuf.append(",");
				valueBuf.append(attriValue.get(1));
				valueBuf.append(",");
				valueBuf.append(orderReview.getSupplier());
				valueBuf.append(",");
				valueBuf.append(orderReview.getCallPackage());
				map.put("goodAttribute", valueBuf.toString());
				String msg = HttpTest.pushGet("http://www.wisedp.com/BeeCost/push/orderPush", JSON.toJSONString(map), "orderPush");
				System.out.println(msg);
			}else if("2".equals(orderState)){//审核未通过
				//获取推送所需数据
				Wbl_OrderReviewModel orderReview = wbl_OrderDAO.listOrderReview(Long.parseLong(id));
				//封装推送数据
				map.put("orderStatus", 1);
				map.put("goodName", orderReview.getGoodsName());
				map.put("ownerwx", orderReview.getBeeWX());
				map.put("reason", reason);
				//获取手机属性值
				String valueStr = orderReview.getValueStr();
				String [] value = valueStr.split(",");
				valueList.add(Long.parseLong(value[0]));
				valueList.add(Long.parseLong(value[1]));
				valueMap.put("valueIDs", valueList);
				//得到批量查询属性值的结果集
				List<String> attriValue = wbl_AttributevalueDAO.batchValue(valueMap);
				//拼接手机属性
				valueBuf.append(attriValue.get(0));
				valueBuf.append(",");
				valueBuf.append(attriValue.get(1));
				valueBuf.append(",");
				valueBuf.append(orderReview.getSupplier());
				valueBuf.append(",");
				valueBuf.append(orderReview.getCallPackage());
				map.put("goodAttribute", valueBuf.toString());
				String msg = HttpTest.pushGet("http://www.wisedp.com/BeeCost/push/orderPush", JSON.toJSONString(map), "orderPush ");
				System.out.println(msg);
			}
			//修改订单审核状态
			order.setId(Long.parseLong(id));
			order.setOrderState(Integer.parseInt(orderState));
			Integer type = wbl_OrderDAO.updateOrder(order);
			return type;
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}
	/**
	 * 将订单审核资料发送至页面
	 * @return
	 */
	@Override
	public Map<String, Object> reviewOrder(String beeid,String id) {
		//初始化返回的集合对象
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//订单ID
			Wbl_OrderEntity order = wbl_OrderDAO.getOrder(Long.parseLong(id));	
System.err.println(JSON.toJSONString(order));			
			//解析json数据(获取身份证图片)
			JSONObject obj = JSON.parseObject(order.getBee().getCardimage());	
			
			//解析json数据(获取客户名,手机号,身份证号进行芝麻欺诈分计算)
			//String zhimaScore=ZhimaCreditAntifraudScore.ZhimaCreditAntifraudScoreGet(order.getBee().getBeecard(), order.getBee().getBeename(),order.getBee().getPhone());
			
			//解析json数据(获取银行卡,身份证号,客户名进行银行卡三要素认证)
	//		String bankCard=AliyunBankCard.query(order.getBankcard().getBankcard(),order.getBee().getBeecard(),order.getBee().getBeename());
			
			outMap.put("data", order);
			outMap.put("status", "SUCCESS");
			outMap.put("secondPath", obj.getString("secondPath"));
			outMap.put("firstPath", obj.getString("firstPath"));
			outMap.put("onlineSign", order.getOnlineSign());
	//		outMap.put("bankCard", bankCard);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", "");
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}
	
	

}
