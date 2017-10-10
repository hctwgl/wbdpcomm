package com.wbdp.bee.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;








import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbdp.bee.dao.SalemanMapper;
import com.wbdp.bee.dao.UserNewDAO;
import com.wbdp.bee.dao.Wbl_BeeDao;
import com.wbdp.bee.dao.Wbl_ModelDAO;
import com.wbdp.bee.dao.Wbl_UserDao;
import com.wbdp.bee.entity.Saleman;
import com.wbdp.bee.entity.UserNewEntity;
import com.wbdp.bee.entity.Wbl_BeeEntity;
import com.wbdp.bee.entity.Wbl_ModelEntity;
import com.wbdp.bee.entity.Wbl_UserEntity;
import com.wbdp.bee.service.UserNewService;
import com.wbdp.bee.util.ResultUtil;
import com.wbdp.bee.util.UtilBase64;


/**
 * 一分期新版业务处理类
 * @author 汪赛军
 * date:2017年9月5日上午11:40:32
 *
 */
@Service
public class UserNewServiceImpl implements UserNewService {
	//日志
	 private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserNewServiceImpl.class);
	@Autowired
	private Wbl_UserDao wbl_UserDao;
	@Autowired
	private Wbl_BeeDao wbl_BeeDao;
	@Autowired
	private UserNewDAO userNewDAO;
	@Autowired
	private Wbl_ModelDAO wbl_ModelDAO;
	@Autowired
	private SalemanMapper salemanMapper;
	/**
	 * 用户登录
	 */
	@Override
	public Map<String, Object> userLogin(String userName,Integer userType,String passWord,HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//用户开始登录
			UserNewEntity userNewEntity = userNewDAO.userLogin(userName, UtilBase64.toBase64(passWord));
			//判断是否为空
			if(userNewEntity!=null){//表示登录的用户为客服或系统管理员
				if(userNewEntity.getUserType()==1){//用户为系统管理员
					log.info("系统管理员登录成功");
					//保存用户类型
					session.setAttribute("userType", userNewEntity.getUserType());
					//保存用户名
					session.setAttribute("username", userNewEntity.getUserName());
					//map = wbl_ModelDAO.selectModelBySys();
					map.put("status", "SUCCESS");
					return map;
				}else{
					log.info("客服登录成功");
					//保存用户类型
					session.setAttribute("userType", userNewEntity.getUserType());
					//保存用户名
					session.setAttribute("username", userNewEntity.getUserName());
					//map = wbl_ModelDAO.selectModelBySys();
					map.put("status", "SUCCESS");
					return map;
				}
			}else{
				Saleman saleman = salemanMapper.salLogin(userName, UtilBase64.toBase64(passWord));
				if(saleman!=null){
					if(saleman.getSaleManType()==3){//客户经理登录
						log.info("客户经理登录成功");
						//保存用户类型
						session.setAttribute("userType", saleman.getSaleManType());
						//保存用户名
						session.setAttribute("username", saleman.getName());
						//客户经理则将推荐码存入session中
						session.setAttribute("recomCode", saleman.getRecommend());
						//map = wbl_ModelDAO.selectModelBySys();
						map.put("status", "SUCCESS");
						return map;
					}else{
						log.info("客户管理员登录成功");
						//保存用户类型
						session.setAttribute("userType", saleman.getSaleManType());
						//保存用户名
						session.setAttribute("username", saleman.getName());
						//map = wbl_ModelDAO.selectModelBySys();
						map.put("status", "SUCCESS");
						return map;
					}
				}else{
					//用户登录失败
					log.info("登录失败");
					map.put("status", "FALSE");
					return map;
				}
			}
	/*		System.out.println(userType);
			if(userType==1){//系统管理员
				passWord = UtilBase64.toBase64(passWord);
				UserNewEntity userNewEntity = userNewDAO.userLogin(userName, passWord);
				if(userNewEntity!=null){//登录成功
					if(userNewEntity.getUserType()==userType){
						log.info("系统管理员登录成功");
						//保存用户类型
						session.setAttribute("userType", userType);
						//保存用户名
						session.setAttribute("username", userName);
						 //map = wbl_ModelDAO.selectModelBySys();
						 map.put("status", "SUCCESS");
						 return map;
					}else{
						map.put("status", "FALSE");
						return map;
					}
				}else{
					map.put("status", "FALSE");
					return map;
				}
			}
			if(userType==2){//客服
				passWord = UtilBase64.toBase64(passWord);
				UserNewEntity userNewEntity = userNewDAO.userLogin(userName, passWord);
				if(userNewEntity!=null){
					if(userNewEntity.getUserType()==userType){
						log.info("客服登陆成功");
						//保存用户类型
						session.setAttribute("userType", userType);
						//保存用户名
						session.setAttribute("username", userName);
						map.put("status", "SUCCESS");
						 return map;
					}else{
						map.put("status", "FALSE");
						return map;
					}
					
				}else{
					map.put("status", "FALSE");
					return map;
				}
			}
			if(userType==3){//普通客户经理
				Saleman saleman = salemanMapper.salLogin(userName, UtilBase64.toBase64(passWord));
				if(saleman!=null){
					if(saleman.getSaleManType()==userType){
						log.info("客户经理登录成功");
						session.setAttribute("userType", saleman.getSaleManType());
						session.setAttribute("username", saleman.getName());
						session.setAttribute("recomCode", saleman.getRecommend());
						map.put("status", "SUCCESS");
						 return map;
					}else{
						map.put("status", "FALSE");
						 return map;
					}
				}else{
					map.put("status", "FALSE");
					 return map;
				}
			}
			if(userType==4){//客户经理管理员
				Saleman saleman = salemanMapper.salLogin(userName, UtilBase64.toBase64(passWord));
				if(saleman!=null){
					if(saleman.getSaleManType()==userType){
						log.info("客户经理管理员登录成功");
						session.setAttribute("userType", saleman.getSaleManType());
						session.setAttribute("username", saleman.getName());
						session.setAttribute("recomCode", saleman.getRecommend());
						map.put("status", "SUCCESS");
						 return map;
					}else{
						map.put("status", "FALSE");
						 return map;
					}
				}else{
					map.put("status", "FALSE");
					 return map;
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error("登录失败");
		}
	}
	@Override
	public Map<String, Object> toPage(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer userType = (Integer)session.getAttribute("userType");
			if(userType==1){
				List<Wbl_ModelEntity> list = wbl_ModelDAO.selectModelBySys();
				map.put("data", list);
				return map;
			}
			if(userType==2){
				List<Wbl_ModelEntity> list = wbl_ModelDAO.selectModelByCus();	
				map.put("data", list);
				return map;
						}
			if(userType==3){
				List<Wbl_ModelEntity> list = wbl_ModelDAO.selectModelBySal();
				map.put("data", list);
				return map;
			}
			if(userType==4){
				List<Wbl_ModelEntity> list = wbl_ModelDAO.selectModelBySalMan();
				map.put("data", list);
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取用户列表
	 */
	@Override
	public Map<String, Object> beeList(Integer pageNum, HttpSession session) {
		try {
			String sessionid = session.getId();
			//从session中获取登录用户信息
			Wbl_UserEntity user = (Wbl_UserEntity)session.getAttribute(sessionid+"USER");
			//处理页码
			pageNum=pageNum==1?0:(pageNum-1)*10;
			//保存当前页
			session.setAttribute("pageNow", pageNum);
			List<Wbl_BeeEntity> list = null;
			//判断用户类型
			if(user!=null){
				Integer userType = user.getUsertype();
				switch (userType) {
				case 1://系统管理员能看到全部用户
					
					break;
				case 2://客服能看到全部用户
					
					break;
				case 3://业务员只能看到他所推荐的用户
					//list = wbl_BeeDao.selectBeeByCode(user.getRecomCode(),pageNum);
					Integer pages = wbl_BeeDao.selectBeeByCodeNum(user.getRecomCode());
					session.setAttribute("pages", pages);
					break;
				default:
					break;
				}
				return ResultUtil.success(list, "获取用户列表成功");
			}else{
				return ResultUtil.error("获取用户列表失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error("获取用户列表失败");
		}
	}
	/**
	 * 新增用户
	 */
	@Override
	public Integer userAdd(UserNewEntity userNewEntity) {
		try {
			if(userNewEntity==null){
				log.info("新增用户数据为空");
				return 2;
			}
			userNewEntity.setPassWord(UtilBase64.toBase64(userNewEntity.getPassWord()));
			Integer result = userNewDAO.insertUser(userNewEntity);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 用户重置密码
	 */
	@Override
	public Map<String, Object> resetPassWord(String passWordold,
			String passWordnew, HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			//获取当前用户名
			String username = session.getAttribute("username").toString();
			//获取用户类型
			Integer userType = (Integer)session.getAttribute("userType");
			if(userType==1||userType==2){
				UserNewEntity userNewEntity = userNewDAO.userLogin(username, UtilBase64.toBase64(passWordold));
				if(userNewEntity!=null){
					userNewEntity.setPassWord(UtilBase64.toBase64(passWordnew));
					Integer result = userNewDAO.resetPass(userNewEntity);
					if(result==1){
						outMap.put("data", result);
						outMap.put("status", "SUCCESS");
					}else{
						outMap.put("data", result);
						outMap.put("status", "FALSE");
					}
				}else{
					outMap.put("data", 0);
					outMap.put("status", "FALSE");
				}
			}else{
				Saleman saleman = salemanMapper.salLogin(username, UtilBase64.toBase64(passWordold));
				if(saleman!=null){
					saleman.setPassWord(UtilBase64.toBase64(passWordnew));
					Integer result = salemanMapper.resetPass(saleman);
					if(result==1){
						outMap.put("data", result);
						outMap.put("status", "SUCCESS");
					}else{
						outMap.put("data", result);
						outMap.put("status", "FALSE");
					}
				}else{
					outMap.put("data", 0);
					outMap.put("status", "FALSE");
				}
			}
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 跳转到修改页面
	 */
	@Override
	public Map<String, Object> toUpdatePage(Long id, HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			UserNewEntity userNewEntity = userNewDAO.selectUserByID(id);
			outMap.put("data", userNewEntity);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 修改用户
	 */
	@Override
	public Integer updateUser(UserNewEntity userNewEntity, HttpSession session) {
		try {
			if(userNewEntity.getPassWord()!=null){
				userNewEntity.setPassWord(UtilBase64.toBase64(userNewEntity.getPassWord()));
			}
			Integer result = userNewDAO.updateUser(userNewEntity);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
