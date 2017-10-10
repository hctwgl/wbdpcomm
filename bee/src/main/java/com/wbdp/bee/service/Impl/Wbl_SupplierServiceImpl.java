package com.wbdp.bee.service.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbdp.bee.dao.Wbl_SupplierDAO;
import com.wbdp.bee.entity.Wbl_SupplierEntity;
import com.wbdp.bee.entity.Wbl_UserEntity;
import com.wbdp.bee.service.Wbl_SupplierService;
import com.wbdp.bee.util.UtilPackaging;

@Service
public class Wbl_SupplierServiceImpl implements Wbl_SupplierService {


	@Resource
	private Wbl_SupplierDAO SupplierDAO;

	
	//日志
	private Logger logger=Logger.getLogger(Wbl_SupplierServiceImpl.class);
	
	/*
	 * 方法名: SupplierInsert   
	 * 方法描述: 运营商增加 
	 * 创建人:wisedata004
	 */
	@Override
	public Map<String,Object> SupplierInsert(Wbl_SupplierEntity SupplierEntity) {
		// TODO Auto-generated method stub
		try {
			//设置时间
			SupplierEntity.setCreatdate(new Date());
			//执行添加
			return UtilPackaging.toResultInteger(SupplierDAO.SupplierInsert(SupplierEntity)); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("运营山添加失败:"+e);
			throw e;
		}
	}

	/*
	 * 方法名: SupplierDelete   
	 * 方法描述:  运营商删除
	 * 创建人:wisedata004
	 */
	@Override
	public Map<String,Object> SupplierDelete(Long id) {
		// TODO Auto-generated method stub
		try {
			//执行删除
			return UtilPackaging.toResultInteger(SupplierDAO.SupplierDelete(id)); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("运营山删除失败:"+e);
			throw e;
		}
	}

	/*
	 * 方法名: SupplierUpdate   
	 * 方法描述:  运营商修改
	 * 创建人:wisedata004
	 */	
	@Override
	public Map<String,Object> SupplierUpdate(Wbl_SupplierEntity SupplierEntity) {
		// TODO Auto-generated method stub
		try {
			//设置时间
			SupplierEntity.setUpdatetime(new Date());
			//执行修改
			return UtilPackaging.toResultInteger(SupplierDAO.SupplierUpdate(SupplierEntity));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("运营山修改失败:"+e);
			throw e;
		}
	}

	/*
	 * 方法名: SupplierAllList   
	 * 方法描述:  运营商列表
	 * 创建人:wisedata004
	 */
	@Override
	public Map<String,Object> SupplierAllList(Integer pageNum) {
		// TODO Auto-generated method stub
	 pageNum=pageNum==1?0:(pageNum-1)*10;  //初始化起始页码
  List<Wbl_SupplierEntity> data=null;  //获取数据 
			      Integer pages=null;  //获取分页大小
		try {
			//获取数据
			data=SupplierDAO.SupplierAllList(pageNum);
			//获取分页
		   pages=SupplierDAO.SupplierAllListSize();
		  //返回数据
		 return UtilPackaging.toResultMap(pages, data);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("运营商列表获取失败:"+e);
			throw e;
		}
	}

	/***
     * 用于添加商品时ajax调用
     * @return
     */
	@Override
	public Map<String, Object> listSupplier() {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			List<Wbl_SupplierEntity> list = SupplierDAO.listSupplier();
			outMap.put("data", list);
			outMap.put("status", "SUCCESS");
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", "");
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}

}
