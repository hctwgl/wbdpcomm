package com.wbdp.bee.service.Impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.wbdp.bee.dao.Wbl_ModelDAO;
import com.wbdp.bee.dao.Wbl_ModelUserDAO;
import com.wbdp.bee.dao.Wbl_UserDao;
import com.wbdp.bee.entity.Wbl_ModelEntity;
import com.wbdp.bee.entity.Wbl_Model_User;
import com.wbdp.bee.entity.Wbl_UserEntity;
import com.wbdp.bee.service.Wbl_UserService;
import com.wbdp.bee.util.UtilBase64;
import com.wbdp.bee.util.UtilPackaging;



@Service
public class Wbl_UserServiceImpl implements Wbl_UserService {

	@Resource
	private Wbl_UserDao UserDao;
	@Resource
	private Wbl_ModelDAO wbl_ModelDAO;
	@Resource
	private Wbl_ModelUserDAO wbl_ModelUserDAO;
	//日志
	private Logger logger=Logger.getLogger(Wbl_UserServiceImpl.class);
	
	
	/*
	 * 方法名: UserLogin   
	 * 方法描述: 用户登录
	 * 入参描述: username(用户名),password(密码)
	 * 回调描述: 该用户信息(返回为null为没有该用户)
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月6日
	 */
	@Override
	public Map<String, Object> UserLogin(String username, String password) {	      
		   return UserDao.UserLogin(username, UtilBase64.toBase64(password));		  	  
	}

	/*
	 * 方法名: UserInsert   
	 * 方法描述: 用户增加
	 * 入参描述: 用户实体
	 * 回调描述: 1:增加成功  0:增加失败 
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月6日 
	 */
	@Override
	public Map<String, Object> UserInsert(Wbl_UserEntity UserEntity){
		List<Wbl_Model_User> slist = new ArrayList<Wbl_Model_User>();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//密码加密
			String base64Password=UtilBase64.toBase64(UserEntity.getPassword());
			UserEntity.setPassword(base64Password);
			Long result = UserDao.UserInsert(UserEntity);
			//新增成功
			if(UserEntity.getId()!=null){
				List<Wbl_ModelEntity> list = wbl_ModelDAO.selsectAllWbl_Model();
				for(Wbl_ModelEntity w:list){
					Wbl_Model_User m = new Wbl_Model_User();
					m.setUserId(UserEntity.getId());
					m.setModelId(w.getId());
					slist.add(m);
				}
				if(UserEntity.getUsertype()==1){
					map.put("status", 1);
				}
				map.put("userID",UserEntity.getId());
				map.put("list", slist);
				//给新增用户添加初始化权限模块
				wbl_ModelUserDAO.insertModel(map);
				return UtilPackaging.toResultInteger(Integer.parseInt(result.toString()));
			}else{
				return UtilPackaging.toResultInteger(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户增加失败:"+e);
			return UtilPackaging.toResultInteger(0);
		}	
		
	}


	/*
	 * 方法名: UserDelete   
	 * 方法描述: 用户删除
	 * 入参描述: 需删除的用户ID
	 * 回调描述: 1:删除成功 0:删除失败  
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月6日 
	 */
	@Override
	public Map<String, Object> UserDelete(Long id) {
		try {
			return UtilPackaging.toResultInteger(UserDao.UserDelete(id));
		} catch (Exception e) {
			
			logger.error("用户删除失败:"+e);
			throw e;
		}	
	}


	/*
	 * 方法名: UserStop   
	 * 方法描述: 用户停用
	 * 入参描述: 需停用的用户ID
	 * 回调描述: 1:停用成功 0:停用失败  
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月6日
	 */
	@Override
	public Map<String, Object> UserStop(Long id) {
		// TODO Auto-generated method stub
	try {
		return UtilPackaging.toResultInteger(UserDao.UserStop(id));
	} catch (Exception e) {
		// TODO: handle exception
		logger.error("用户停用失败:"+e);
		throw e;
	}	
	}


	/*
     * 方法名: UserUpdate   
     * 方法描述: 用户修改
     * 入参描述: 用户实体
     * 回调描述: 1:修改成功 0:修改失败  
     * 创建人:wisedata004  
     * 创建时间: 2017年7月6日 
     */
	@Override
	public Map<String, Object> UserUpdate(Wbl_UserEntity UserEntity) {
		// TODO Auto-generated method stub
		try {
			return UtilPackaging.toResultInteger(UserDao.UserUpdate(UserEntity));
		  } catch (Exception e) {
			// TODO: handle exception
			logger.error("修改用户失败:"+e);
			throw e;
		}
	}


	 /*
     * 方法名: UserAllList   
     * 方法描述: 用户列表
     * 入参描述: 无
     * 回调描述: 所有用户信息  
     * 创建人:wisedata004  
     * 创建时间: 2017年7月6日 
     */
	@Override
	public Map<String, Object> UserAllList(Integer pageNum) {
   pageNum=pageNum==1?0:(pageNum-1)*10;  //初始化起始页码
		List<Wbl_UserEntity> data=null;  //获取数据 
		            Integer pages=null;  //获取分页大小
		try {
			//查询数据
		    data=UserDao.UserAllList(pageNum);
			//获取大小
		    pages=UserDao.UserAllListSize();
		    //返回数据
			return UtilPackaging.toResultMap(pages,data);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("获取用户列表失败:"+e);
			throw e;
		}
	}

	/**
     * 获取用户权限列表
     * @param json
     * @return
     */
	@Override
	public Map<String, Object> listModel(String username) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			List<Wbl_ModelEntity> list = wbl_ModelDAO.listWbl_Model(username);
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

	@Override
	public Map<String, Object> selsectModel(Long userID) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			List<Wbl_ModelEntity> list = wbl_ModelDAO.selectWbl_Model(userID);
			outMap.put("data", list);
			outMap.put("userID", userID);
			outMap.put("status", "SUCCESS");
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", "");
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}

	@Override
	public Integer setModel(String[] list,String userID) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			map.put("list", list);
			map.put("userId", Long.parseLong(userID));
			wbl_ModelUserDAO.updateModelGive(map);
			wbl_ModelUserDAO.updateModelRel(map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
}
