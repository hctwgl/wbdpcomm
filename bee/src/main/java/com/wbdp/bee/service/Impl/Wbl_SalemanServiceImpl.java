package com.wbdp.bee.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.servlet.ModelAndView;

import com.wbdp.bee.dao.SalemanMapper;
import com.wbdp.bee.dao.Wbl_SalemanDAO;
import com.wbdp.bee.entity.Saleman;
import com.wbdp.bee.entity.Wbl_SalemanEntity;
import com.wbdp.bee.entity.Wbl_SupplierEntity;
import com.wbdp.bee.service.Wbl_SalemanService;
import com.wbdp.bee.util.UtilBase64;
import com.wbdp.bee.util.UtilPackaging;
import com.wbdp.bee.util.UtilUuidRandom;

@Service
public class Wbl_SalemanServiceImpl implements Wbl_SalemanService {

	@Resource
	private Wbl_SalemanDAO SalemanDAO;
	 
	//日志
	private Logger logger=Logger.getLogger(Wbl_SalemanServiceImpl.class);
	
	@Resource
	private SalemanMapper salemanMapper;
	
	/*
	 * 方法名: SalemanInsert   
	 * 方法描述: 业务员增加 
	 * 创建人:wisedata004
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> SalemanInsert(Wbl_SalemanEntity SalemanEntity) {
		// TODO Auto-generated method stub
		try {
			//设置时间
			SalemanEntity.setCreatdate(new Date());
			//执行添加,如果添加成功则生成推荐码(推荐码规则:前两位为运营商ID,后四位为业务员添加成功后返回的主键ID)
			if(SalemanDAO.SalemanInsert(SalemanEntity)==1){
				String supplierID=String.format("%02d",SalemanEntity.getSupplierid()); //生成推荐码前两位
				String salemanID=String.format("%04d",SalemanEntity.getId());    //生成推荐码后四位
				String recommend=supplierID+salemanID;
				//添加生成的推荐码
				return UtilPackaging.toResultInteger(SalemanDAO.SalemanUpdateRecommend(SalemanEntity.getId(),recommend));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("业务员添加失败:"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return UtilPackaging.toResultInteger(0); 
	}

	
	/*
	 * 方法名: SalemanDelete   
	 * 方法描述: 业务员删除
	 * 创建人:wisedata004
	 */
	@Override
	public Map<String, Object> SalemanDelete(Long id) {
		// TODO Auto-generated method stub
		try {
			//执行删除
			return UtilPackaging.toResultInteger(SalemanDAO.SalemanDelete(id)); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("业务员删除失败:"+e);
			throw e;
		}
	}

	/*
	 * 方法名: SalemanUpdate   
	 * 方法描述:  业务员修改
	 * 创建人:wisedata004
	 */
	@Override
	public Map<String, Object> SalemanUpdate(Wbl_SalemanEntity SalemanEntity) {
		// TODO Auto-generated method stub
		try {
			//设置时间
			SalemanEntity.setUpdatetime(new Date());
			//执行删除
			return UtilPackaging.toResultInteger(SalemanDAO.SalemanUpdate(SalemanEntity)); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("业务员修改失败:"+e);
			throw e;
		}
	}

	/*
	 * 方法名: SalemanAllList   
	 * 方法描述:  业务员列表
	 * 创建人:wisedata004
	 */
	@Override
	public Map<String, Object> SalemanAllList(Integer pageNum) {
		// TODO Auto-generated method stub
		 pageNum=pageNum==1?0:(pageNum-1)*10;  //初始化起始页码
		   List<Wbl_SalemanEntity> data=null;  //获取数据 
					      Integer pages=null;  //获取分页大小
				try {
					//获取数据
					data=SalemanDAO.SalemanAllList(pageNum);
					//获取分页
				   pages=SalemanDAO.SalemanAllListSize();
				  //返回数据
				 return UtilPackaging.toResultMap(pages, data);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					logger.error("业务员列表获取失败:"+e);
					throw e;
				}
	}
	
	
	
	
	/**
	 * 新增客户经理
	 */
	@Override
	public Integer insertSaleman(Saleman saleman,HttpSession session) {
		SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String creatBy = session.getAttribute("username").toString();
			saleman.setPassWord(UtilBase64.toBase64(saleman.getPassWord()));
			saleman.setCreatBy(creatBy);
			saleman.setCreatDate(format.format(new Date()));
			//不存在类型时默认为客户管理员
			if(saleman.getSaleManType()==null||saleman.getSaleManType()==0){
				saleman.setSaleManType(4);
				saleman.setRecommend("");
			}else{
				String recommend = String.valueOf(new Random().nextInt(999999));
				//判断该推荐码是否已存在
				Integer count = salemanMapper.checkRecommend(recommend);
				if(count>0){
					recommend = String.valueOf(new Random().nextInt(999999));
					count = salemanMapper.checkRecommend(recommend);
					if(count>0){
						recommend = String.valueOf(new Random().nextInt(999999));
						count = salemanMapper.checkRecommend(recommend);
						if(count>0){
							recommend = String.valueOf(new Random().nextInt(999999));
							count = salemanMapper.checkRecommend(recommend);
							if(count>0){
								recommend = String.valueOf(new Random().nextInt(999999));
								count = salemanMapper.checkRecommend(recommend);
							}
						}
					}
				}
				saleman.setRecommend(recommend);
			}
			Integer result = salemanMapper.insertSaleman(saleman);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查看全部客户经理数据
	 */
	@Override
	public Map<String, Object> selectAllSaleman(Integer pageNum,HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//保存当前页
			session.setAttribute("pageNow", pageNum);
			pageNum=pageNum==1?0:(pageNum-1)*10;  //初始化起始页码
			//获取总页数
			Integer count = salemanMapper.getCount();
			Integer pages = ((count+10)-1)/10;
			session.setAttribute("pages", pages);
			List<Saleman> list = salemanMapper.selectAllSaleman(pageNum);
			outMap.put("data", list);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 客户经理管理员查看其名下所有客户经理信息
	 */
	@Override
	public Map<String, Object> selectSalemanByManager(
			Integer pageNum,HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			String creatBy = session.getAttribute("username").toString();
			//保存当前页
			session.setAttribute("pageNow", pageNum);
			pageNum=pageNum==1?0:(pageNum-1)*10;  //初始化起始页码
			//获取总页数
			Integer count = salemanMapper.getCountByManager(creatBy);
			Integer pages = ((count+10)-1)/10;
			session.setAttribute("pages", pages);
			List<Saleman> list = salemanMapper.selectSalemanByManager(creatBy, pageNum);
			outMap.put("data", list);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 修改客户经理信息
	 */
	@Override
	public Integer updateSaleman(Saleman saleman,HttpSession session) {
		SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String user = session.getAttribute("username").toString();
			saleman.setUpDateBy(user);
			saleman.setUpDateTime(format.format(new Date()));
			Integer result = salemanMapper.updateSaleman(saleman);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 生成客户经理推荐码13714318834
	 */
	@Override
	public String creatCode(String phone) {
		String code = "";
		Integer result = 0;
		try {
			Integer msg = salemanMapper.checkPhone(phone);
			if(msg>0){
				return "2";
			}
			 code = phone.substring(5);
			 result = salemanMapper.checkCode(code);
			if(result==0){
				return code;
			}else{
				 code = phone.substring(4,10);
				 result = salemanMapper.checkCode(code);
				if(result==0){
					return code;
				}else{
					code = phone.substring(3,9);
					result = salemanMapper.checkCode(code);
					if(result==0){
						return code;
					}else{
						return null;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Map<String, Object> selectSaleman(Long id, HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			Saleman saleman = salemanMapper.selectSalemanByID(id);
			outMap.put("data", saleman);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 客服新增客户经理获取上级管理员数据
	 */
	@Override
	public Map<String, Object> listSaleman(HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			List<Saleman> list = salemanMapper.listSaleman();
			outMap.put("data", list);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 客服新增客户经理
	 */
	@Override
	public Integer insertSalemanBykefu(Saleman saleman, HttpSession session) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			saleman.setCreatDate(format.format(new Date()));
			saleman.setSaleManType(3);
			String recommend = String.valueOf(new Random().nextInt(999999));
			//判断该推荐码是否已存在
			Integer count = salemanMapper.checkRecommend(recommend);
			if(count>0){
				recommend = String.valueOf(new Random().nextInt(999999));
				count = salemanMapper.checkRecommend(recommend);
				if(count>0){
					recommend = String.valueOf(new Random().nextInt(999999));
					count = salemanMapper.checkRecommend(recommend);
					if(count>0){
						recommend = String.valueOf(new Random().nextInt(999999));
						count = salemanMapper.checkRecommend(recommend);
						if(count>0){
							recommend = String.valueOf(new Random().nextInt(999999));
							count = salemanMapper.checkRecommend(recommend);
						}
					}
				}
			}
			saleman.setRecommend(String.valueOf(Integer.parseInt(recommend)));
			System.out.println((int)((Math.random()*9+1)*100000));
			//密码加密
			saleman.setPassWord(UtilBase64.toBase64(saleman.getPassWord()));
			Integer result = salemanMapper.insertSaleman(saleman);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
