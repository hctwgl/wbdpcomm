package com.wbdp.bee.service.Impl;




import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wbdp.bee.dao.Wbl_BeeDao;
import com.wbdp.bee.dao.Wbl_BlackbeeDAO;
import com.wbdp.bee.entity.Wbl_BeeEntity;
import com.wbdp.bee.entity.Wbl_BlackbeeEntity;
import com.wbdp.bee.service.Wbl_BlackBeeService;
import com.wbdp.bee.util.UtilPackaging;




@Service
public class Wbl_BlackBeeServiceImpl implements Wbl_BlackBeeService {

	@Resource
	private Wbl_BlackbeeDAO BlackbeeDAO; 
	@Resource
	private Wbl_BeeDao BeeDao;
	
	//日志
	private Logger logger=Logger.getLogger(Wbl_BlackBeeServiceImpl.class);

	
	/**
	 * 方法名: blackbeeAllList   
	 * 方法描述: 客户黑名单列表
	 * 入参描述: id:黑名单主键id, pageNum:页码
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月14日
	 */
	@Override
	public Map<String, Object> blackbeeAllList(Long id, Integer pageNum) {
		// TODO Auto-generated method stub
		 pageNum=pageNum==1?0:(pageNum-1)*10;  //初始化起始页码
		List<Wbl_BlackbeeEntity> data=null;  //获取数据 
			            Integer pages=null;  //获取分页大小
			try {
				//查询数据
				data=BlackbeeDAO.blackbeeAllList(id, pageNum);
				//查询页面
				pages=BlackbeeDAO.blackbeeAllListSize(id);
			    //执行封装
				return UtilPackaging.toResultMap(pages,data);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error("客户黑名单获取失败:"+e);
				throw e;
			}            
	}

	
	/**
	 * 方法名: insertBlackbee   
	 * 方法描述: 添加客户黑名单 
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月14日
	 */
	@Override
	public Map<String,Object> insertBlackbee(Wbl_BlackbeeEntity BlackbeeEntity) {
		// TODO Auto-generated method stub
		try {
			//查询出该客户的信息
			List<Wbl_BeeEntity> BeeEntity=BeeDao.BeeAllListOfID(BlackbeeEntity.getBeeid());
           //设置数据
			BlackbeeEntity.setBeename(BeeEntity.get(0).getBeename());  //设置客户名
			BlackbeeEntity.setBeecard(BeeEntity.get(0).getBeecard());  //设置客户银行卡号
			BlackbeeEntity.setPhone(BeeEntity.get(0).getPhone());      //设置客户手机号
			BlackbeeEntity.setCreatdate(new Date());                   //设置创建时间
		   //拉黑时增加黑名单,幷设置客户表状态为拉黑状态
			if(BlackbeeDAO.insertBlackbee(BlackbeeEntity)==1){                  //加入黑名单成功
				if(BlackbeeDAO.updateBeeStatus(BlackbeeEntity.getBeeid())==1){  //设置拉黑状态成功
					return UtilPackaging.toResultInteger(1);
				}
			}
				return UtilPackaging.toResultInteger(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("客户黑名单添加失败:"+e);
			throw e;
		}
	}
	


	

	
}
