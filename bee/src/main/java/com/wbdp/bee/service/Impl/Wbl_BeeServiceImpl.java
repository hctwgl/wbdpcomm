package com.wbdp.bee.service.Impl;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.bee.dao.BeeMapperNew;
import com.wbdp.bee.dao.BrushcreditMapper;
import com.wbdp.bee.dao.CompreMapper;
import com.wbdp.bee.dao.Wbl_BeeDao;
import com.wbdp.bee.entity.BrushcreditEntity;
import com.wbdp.bee.entity.Compre;
import com.wbdp.bee.entity.Wbl_BeeEntity;
import com.wbdp.bee.service.Wbl_BeeService;
import com.wbdp.bee.util.DateFormat;
import com.wbdp.bee.util.ExcelWrite;
import com.wbdp.bee.util.FileDownLoad;
import com.wbdp.bee.util.RedisDataStore;
import com.wbdp.bee.util.UtilHttpSend;
import com.wbdp.bee.util.UtilPackaging;
import com.wbdp.bee.util.ZhimaCreditAntifraudScore;



@Service
public class Wbl_BeeServiceImpl implements Wbl_BeeService {

	@Resource
	private Wbl_BeeDao BeeDao; 
	@Resource
	private BrushcreditMapper brushcreditMapper;
	@Resource
	private BeeMapperNew beeMapperNew;
	@Resource
	private CompreMapper compreMapper;
	//日志
	 private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Wbl_BeeServiceImpl.class);
	


	/*
	 * 
	 * 方法名: BeeAllList   
	 * 方法描述:  客户信息列表
	 * 创建人:wisedata004
	 */
	@Override
	public Map<String, Object> BeeAllList(Integer pageNum,HttpSession session) {
		// TODO Auto-generated method stub
	    pageNum=pageNum==1?0:(pageNum-1)*10;  //初始化起始页码
		List<Map<String, Object>> data=null;  //获取数据 
		Integer pages=null;  //获取分页大小
		try {
			Integer userType = (Integer)session.getAttribute("userType");
			String username = session.getAttribute("username").toString();
			//当用户为客服时，能查看所有客户信息
			if(userType==2){
				//查询数据
				data=beeMapperNew.selectAllBee(pageNum);
				//获取大小
				pages=beeMapperNew.selectAllBeeCount();
			}else if(userType==3){//用户为客户经理时，只能看到提交订单中填写了他推荐码的客户或者导入人是该客户经理的客户（公对公客户）
				String recomCode = (String)session.getAttribute("recomCode");
				data = beeMapperNew.selectSaleManBee(recomCode, pageNum);
				//获取大小
				pages = beeMapperNew.selectSaleManBeeCount(recomCode);
			}else if(userType==4){//客户管理员
				//String recomCode = (String)session.getAttribute("recomCode");
				data = beeMapperNew.selectBeeBySaleMan(username, pageNum);
				//获取大小
				pages =beeMapperNew.selectBeeBySaleManCount(username);
			}
		    //执行封装
		   return UtilPackaging.toResultMap(pages,data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("客户信息获取失败:"+e);
			throw e;
		}
	}
	
	/**
	 * 删除客户
	 */
	@Override
	public Map<String, Object> delBee(Long id, HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			logger.info("获取到的ID："+id);
			Integer result = beeMapperNew.delBee(id);
			if(result==1){
				logger.info("删除用户成功");
				outMap.put("status", "SUCCESS");
				outMap.put("data", result);
			}else{
				logger.info("删除用户失败");
				outMap.put("status", "FALSE");
				outMap.put("data", result);
			}
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
			outMap.put("data", 0);
			return outMap;
		}
	}

	/*
	 * 
	 * 方法名: BeeAllListOfID   
	 * 方法描述:  客户信息列表详细(根据id查询)
	 * 创建人:wisedata004
	 */
	@Override
	public Map<String, Object> BeeAllListOfID(Long id) {
		try {
			//查询数据
			List<Wbl_BeeEntity> data=BeeDao.BeeAllListOfID(id);
			return UtilPackaging.toResultMap(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("客户详细信息获取失败:"+e);
			throw e;
		}
	}

	/**
     * 用户上传集团客户数据（公对公）
     * @param session
     * @return
     */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> uploadClient(String company,HttpSession session) {
		List<Wbl_BeeEntity> beeList = new ArrayList<Wbl_BeeEntity>();
		List<BrushcreditEntity> brushList = new ArrayList<BrushcreditEntity>();
		Map<String, Object> outMap = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//获取客户经理推荐码
			String recomCode = session.getAttribute("recomCode").toString();
			//获取redis连接
			Jedis jedis = RedisDataStore.getconnetion();
			String creatby = session.getAttribute("username").toString();
			String sessionid = session.getId();
			List<String[]> result = (List<String[]>)session.getAttribute(sessionid+"groupClient");
			Integer listLength = result.size();
			for(int i=0;i<=listLength-1;i++){
				Wbl_BeeEntity bee = new Wbl_BeeEntity();
				BrushcreditEntity brush = new BrushcreditEntity();
				for(int j=0;j<=result.get(i).length;j++){
					switch (j) {
					case 0:
						bee.setBeename(result.get(i)[j]);
						break;
					case 1:
						
						bee.setBeecard(result.get(i)[j]);
						break;
					case 2:
						bee.setPhone(result.get(i)[j]);
						//用于到redis中匹配用户ID
						brush.setPhone(result.get(i)[j]);
						break;
					case 3://部门
						bee.setDepart(result.get(i)[j]);
						break;
					case 4://套餐价格每月
						brush.setPacMonthlyPrice(Integer.parseInt(result.get(i)[j]));
						break;
					case 5://期数
						brush.setPacPeriods(Integer.parseInt(result.get(i)[j]));
						break;
					case 6://手机型号
						brush.setPhoneModel(result.get(i)[j]);
						break;
					case 7://套餐总价
						brush.setPacPrice(Integer.parseInt(result.get(i)[j]));
						break;
					default:
						break;
					}
				}
				//进行身份证与姓名的验证
		        String resultStr = ZhimaCreditAntifraudScore.ZhimaCreditAntifraudScoreGet(bee.getBeecard(), bee.getBeename());
		        logger.info("身份证与姓名的验证："+resultStr);
		        JSONObject obj = JSON.parseObject(resultStr);
		        if("true".equals(obj.getString("success"))){
		        	String score = obj.getString("score");
		        	logger.info("客户信用欺诈分查询结果："+score);
		            //score不等于100时，代表该用户姓名与身份证信息不匹配
		            if("100".equals(score)){
		            	logger.info("用户姓名与身份证信息匹配成功");
		            	bee.setCompany(company);
						bee.setCreatby(creatby);
						bee.setBeeType(1);
		            	bee.setBeestatus(0);
		            	bee.setCreatdate(new Date());
		            	beeList.add(bee);
		            	//客户经理推荐码，为当前登录客户经理的推荐码
						brush.setRecomCode(recomCode);
						//刷脸状态默认为1
						brush.setFaceStatus(1);
						//刷刷时间
						brush.setBrushTime(format.format(new Date()));
						//开始时间
						 String starttime = DateFormat.dateAddMonth(format.format(new Date()),1);
						 brush.setStartTime(starttime);
						//截止时间=开始时间+期数月份
						 String endtime = DateFormat.dateAddMonth(starttime,brush.getPacPeriods());
						 brush.setEndTime(endtime);
		            	 brushList.add(brush);
		            }else{
		            	logger.info("用户姓名与身份证信息不匹配");
		            }
		        }else{
		        	logger.info("身份证号码不存在");
		        }
			}
			//检查该用户是否已注册
			Wbl_BeeEntity beeCheck = beeList.get(0);
			Wbl_BeeEntity beeResult = BeeDao.selectBeeByPhone(beeCheck.getPhone());
			if(beeResult!=null){
				logger.info("上传类型出错，用户已存在");
				outMap.put("data", 2);
				outMap.put("status", "FALSE");
				return outMap;
			}
			Integer msg = BeeDao.uploadClient(beeList);
			if(msg>0){
				//集团客户保存成功后,查询出所有用户的ID与手机号码，存入redis中
				List<Wbl_BeeEntity> list = BeeDao.getPhoneAndID();
				for(Wbl_BeeEntity bee:list){
					//将客户手机号与ID存入redis中，等待匹配
					jedis.set(bee.getPhone()+"PUBLIC", bee.getId().toString());
				}
				//将套餐记录中的手机号匹配redis中的数据,并放入任性刷记录实体中
				for(BrushcreditEntity brush:brushList){
					logger.info("循环出来的手机号码："+jedis.get(brush.getPhone()));
					brush.setBeeID(Long.parseLong(jedis.get(brush.getPhone()+"PUBLIC")));
				}
				//批量将任性刷数据存入数据库中
				Integer brushResult = brushcreditMapper.batchSaveBrush(brushList);
				if(brushResult>0){
					logger.info("批量保存任性刷数据成功");
					outMap.put("data", 1);
					outMap.put("status", "SUCCESS");
				}else{
					logger.info("批量保存任性刷数据失败");
					outMap.put("data", 0);
					outMap.put("status", "FALSE");
				}
				
			}
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", 0);
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}
	/**
	 * 导入集团客户（公拉私）
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> uploadClientPrivate(String company,
			HttpSession session) {
		List<Wbl_BeeEntity> beeList = new ArrayList<Wbl_BeeEntity>();
		List<BrushcreditEntity> brushList = new ArrayList<BrushcreditEntity>();
		Map<String, Object> outMap = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//获取客户经理推荐码
			String recomCode = session.getAttribute("recomCode").toString();
			//获取redis连接
			Jedis jedis = RedisDataStore.getconnetion();
			String creatby = session.getAttribute("username").toString();
			String sessionid = session.getId();
			List<String[]> result = (List<String[]>)session.getAttribute(sessionid+"groupClient");
			Integer listLength = result.size();
			for(int i=0;i<=listLength-1;i++){
				Wbl_BeeEntity bee = new Wbl_BeeEntity();
				BrushcreditEntity brush = new BrushcreditEntity();
				for(int j=0;j<=result.get(i).length;j++){
					switch (j) {
					case 0:
						//bee.setBeename(result.get(i)[j]);
						break;
					case 1:
						
						//bee.setBeecard(result.get(i)[j]);
						break;
					case 2:
						//bee.setPhone(result.get(i)[j]);
						//用于到redis中匹配用户ID
						brush.setPhone(result.get(i)[j]);
						break;
					case 3://部门
						bee.setDepart(result.get(i)[j]);
						break;
					case 4://套餐价格每月
						brush.setPacMonthlyPrice(Integer.parseInt(result.get(i)[j]));
						break;
					case 5://期数
						brush.setPacPeriods(Integer.parseInt(result.get(i)[j]));
						break;
					case 6://手机型号
						brush.setPhoneModel(result.get(i)[j]);
						break;
					case 7://套餐总价
						brush.setPacPrice(Integer.parseInt(result.get(i)[j]));
						break;
					default:
						break;
					}
				}
				//客户经理推荐码，为当前登录客户经理的推荐码
				brush.setRecomCode(recomCode);
				//刷脸状态默认为3，保存后推送至用户
				brush.setFaceStatus(3);
				//刷刷时间
				brush.setBrushTime(format.format(new Date()));
				//开始时间
				 String starttime = DateFormat.dateAddMonth(format.format(new Date()),1);
				 brush.setStartTime(starttime);
				//截止时间=开始时间+期数月份
				 String endtime = DateFormat.dateAddMonth(starttime,brush.getPacPeriods());
				 brush.setEndTime(endtime);
				//bee.setCompany(company);
				//bee.setCreatby(creatby);
				//bee.setCreatdate(new Date());
				//bee.setBeeType(1);
				//beeList.add(bee);
				brushList.add(brush);
			}
			Integer msg = 1;//BeeDao.uploadClient(beeList);
			if(msg>0){
				//集团客户保存成功后,查询出所有用户的ID与微信号，存入redis中
				List<Wbl_BeeEntity> list = BeeDao.getPhoneAndWX();
				for(Wbl_BeeEntity bee:list){
					//将客户手机号与微信号存入redis中，等待匹配
					if( bee.getBeewx()!=null){
						jedis.set(bee.getPhone()+"PRIVATE",bee.getBeewx());
					}
				}
				//将套餐记录中的手机号匹配redis中的数据,并放入任性刷记录实体中
				for(BrushcreditEntity brush:brushList){
					brush.setClientWx(jedis.get(brush.getPhone()+"PRIVATE"));
				}
				//批量将任性刷数据存入数据库中
				Integer brushResult = brushcreditMapper.batchSaveBrush(brushList);
				if(brushResult>0){
					//将套餐信息推送至客户
					for(BrushcreditEntity brush:brushList){
						//发送推送信息
						JSONObject obj = new JSONObject();
						obj.put("openid", brush.getClientWx());
						obj.put("pacPeriods", brush.getPacPeriods());
						obj.put("pacMonthlyPrice", brush.getPacMonthlyPrice());
						obj.put("pacPrice", brush.getPacPrice());
						obj.put("phoneModel", brush.getPhoneModel());
						obj.put("time", brush.getBrushTime());
						String msgResult = UtilHttpSend.doPost("http://www.wisedp.com/BeeCost/push/pushPacInfo",obj.toString());
						logger.info("推送返回数据："+msgResult);
					}
					logger.info("批量保存任性刷数据成功");
					outMap.put("data", 1);
					outMap.put("status", "SUCCESS");
				}else{
					logger.info("批量保存任性刷数据失败");
					outMap.put("data", 0);
					outMap.put("status", "FALSE");
				}
			}
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", 0);
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}
	
	/**
     * 客户经理下载其名下公拉私客户数据
     * @param session
     */
	@Override
	public String downLoadBee(Long id,HttpSession session,HttpServletResponse response) {
		logger.info("开始下载数据:"+id);
		try {
			List<Map<String, Object>> list = BeeDao.downLoadBee(id);
			String result = ExcelWrite.writeExcel(list);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 客户经理单独为客户新增套餐
	 */
	@Override
	public Map<String, Object> insertBeePackage(HttpSession session,Integer userType,
			BrushcreditEntity brushcreditEntity) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			logger.info("新增套餐获取的用户类型："+userType);
			//获取该客户经理推荐码
			String recomCode = session.getAttribute("recomCode").toString();
			logger.info("session的客户经理推荐码："+recomCode);
			//新增套餐数据
			//客户经理推荐码
			brushcreditEntity.setRecomCode(recomCode);
			//刷入时间
			brushcreditEntity.setBrushTime(format.format(new Date()));
			//刷脸状态
			//判断传来的用户类型，如果为公拉私用户则默认为3并推送，若为公对公客户则默认为1
			if(userType==1){//公对公客户
				brushcreditEntity.setFaceStatus(1);
			}else{//公拉私客户
				brushcreditEntity.setFaceStatus(3);
			}
			//开始时间
			 String starttime = DateFormat.dateAddMonth(format.format(new Date()),1);
			 brushcreditEntity.setStartTime(starttime);
			//截止时间=开始时间+期数月份
			 String endtime = DateFormat.dateAddMonth(starttime,brushcreditEntity.getPacPeriods());
			 brushcreditEntity.setEndTime(endtime);
			 Integer result = brushcreditMapper.insertBrush(brushcreditEntity);
			 logger.info("保存套餐返回值："+result);
			 if(result==1&&userType!=2){
				 outMap.put("status", "SUCCESS");
				 outMap.put("data", result);
				 return outMap;
			 }else if(result==1&&userType==2){
				//发送推送信息
				JSONObject obj = new JSONObject();
				obj.put("openid", brushcreditEntity.getClientWx());
				obj.put("pacPeriods", brushcreditEntity.getPacPeriods());
				obj.put("pacMonthlyPrice", brushcreditEntity.getPacMonthlyPrice());
				obj.put("pacPrice", brushcreditEntity.getPacPrice());
				obj.put("phoneModel", brushcreditEntity.getPhoneModel());
				obj.put("time", brushcreditEntity.getBrushTime());
				String msgResult = UtilHttpSend.doPost("http://www.wisedp.com/BeeCost/push/pushPacInfo",obj.toString());
				logger.info("推送返回数据："+msgResult);
				outMap.put("status", "SUCCESS");
				outMap.put("data", result);
				return outMap;
			 }else{
				outMap.put("status", "FALSE");
				outMap.put("data", result);
				return outMap;
			 }
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
			outMap.put("data", 0);
			return outMap;
		}
	}
	/**
	 * 客户管理页面客户经理新增公对公客户与套餐数据
	 */
	@Override
	public Map<String, Object> insertBeeAndPackage(HttpSession session,
			Wbl_BeeEntity wbl_BeeEntity, BrushcreditEntity brushcreditEntity) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//获取该客户经理推荐码
			String recomCode = session.getAttribute("recomCode").toString();
			logger.info("session的客户经理推荐码："+recomCode);
			//获取客户信用欺诈分
			//进行身份证与姓名的验证
	        String result = ZhimaCreditAntifraudScore.ZhimaCreditAntifraudScoreGet(wbl_BeeEntity.getBeecard(), wbl_BeeEntity.getBeename());
	        logger.info("身份证与姓名的验证："+result);
	        JSONObject obj = JSON.parseObject(result);
	        if("true".equals(obj.getString("success"))){
	        	String score = obj.getString("score");
	        	logger.info("客户信用欺诈分查询结果："+score);
	            //score不等于100时，代表该用户姓名与身份证信息不匹配
	            if("100".equals(score)){
	            	logger.info("用户姓名与身份证信息匹配成功");
	            	wbl_BeeEntity.setBeestatus(0);
	            	wbl_BeeEntity.setCreatdate(new Date());
	            	wbl_BeeEntity.setCreatby(recomCode);
	            }else{
	            	logger.info("用户姓名与身份证信息不匹配");
	            	outMap.put("status", "FALSE");
					outMap.put("data", "");
					return outMap;
	            }
	        }else{
	        	logger.info("身份证号码不存在");
	        }
	        Integer resultBee = beeMapperNew.insertBeeBySaleMan(wbl_BeeEntity);
	        logger.info("新增用户后返回的ID："+wbl_BeeEntity.getId());
	        if(resultBee==1){
	        	brushcreditEntity.setFaceStatus(1);
	        	brushcreditEntity.setBeeID(wbl_BeeEntity.getId());
	        	brushcreditEntity.setBrushTime(format.format(new Date()));
	        	brushcreditEntity.setRecomCode(recomCode);
	        	//开始时间
				 String starttime = DateFormat.dateAddMonth(format.format(new Date()),1);
				 brushcreditEntity.setStartTime(starttime);
				//截止时间=开始时间+期数月份
				 String endtime = DateFormat.dateAddMonth(starttime,brushcreditEntity.getPacPeriods());
				 brushcreditEntity.setEndTime(endtime);
				 Integer resultPac = brushcreditMapper.insertBrush(brushcreditEntity);
				 if(resultPac==1){
					 outMap.put("status", "SUCCESS");
					 outMap.put("data", resultPac);
					 return outMap;
				 }else{
					 outMap.put("status", "FALSE");
					 outMap.put("data", resultPac);
					 return outMap;
				 }
	        }else{
	        	outMap.put("status", "FALSE");
				 outMap.put("data", resultBee);
				 return outMap;
	        }
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
			 outMap.put("data", 0);
			 return outMap;
		}
	}
}
