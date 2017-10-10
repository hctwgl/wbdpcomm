package com.wbdp.bee.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.wbdp.bee.dao.CompreMapper;
import com.wbdp.bee.entity.Compre;
import com.wbdp.bee.service.CompreService;
import com.wbdp.bee.util.RedisDataStore;
import com.wbdp.bee.util.UtilPackaging;

/**
 * 公司综合数据业务实现类
 * @author 汪赛军
 * date:2017年9月17日上午8:49:17
 *
 */
@Service
public class CompreServiceImpl implements CompreService {
	@Resource
	private CompreMapper compreMapper;
	//日志
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CompreServiceImpl.class);
	
	/**
	 * 获取公司综合数据列表
	 */
	@Override
	public Map<String,Object> compreList(Integer pageNum, HttpSession session) {
		Map<String,Object> outMap = new HashMap<String, Object>();
		List<Map<String, Object>> data=null;  //获取数据 
		Integer pages=null;  //获取分页大小
		try {
			session.setAttribute("pageNow", pageNum);
			pageNum=pageNum==1?0:(pageNum-1)*10;  //初始化起始页码
			Integer userType = (Integer)session.getAttribute("userType");
			String username = session.getAttribute("username").toString();
			logger.info("session中的username："+username);
			//当用户为客服时，能查看所有信息
			if(userType==2){
				//查询数据
				data=compreMapper.selectCompreAll(pageNum);
				//获取大小
				pages=compreMapper.countCompreAll();
			}else if(userType==3){//用户为客户经理
				logger.info("客户经理查询综合数据");
				//String recomCode = (String)session.getAttribute("recomCode");
				data = compreMapper.selectCompre(pageNum, username);
				//获取大小
				pages = compreMapper.getCountByManeger(username);
			}else if(userType==4){//客户管理员
				//String recomCode = (String)session.getAttribute("recomCode");
				data = compreMapper.selectCompreByman(pageNum, username);
				//获取大小
				pages =compreMapper.getCount(username);
			}
			session.setAttribute("pages", ((pages+10)-1)/10);
		    //执行封
			outMap.put("status", "SUCCESS");
			outMap.put("data", data);
		   //return UtilPackaging.toResultMap(pages,data);
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("综合数据获取失败:"+e);
			throw e;
		}
	}
		
	/**
	 * 上传综合数据
	 */
	@Override
	public Map<String, Object> uploadClientZongHe(String company,
			HttpSession session) {
		try {
			List<Compre> comList = new ArrayList<Compre>();
			Map<String, Object> outMap = new HashMap<String, Object>();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				//获取客户经理推荐码
				String recomCode = session.getAttribute("recomCode").toString();
				//获取客户经理用户名
				//String username = session.getAttribute("username").toString();
				//获取redis连接
				Jedis jedis = RedisDataStore.getconnetion();
				String creatby = session.getAttribute("username").toString();
				String sessionid = session.getId();
				List<String[]> result = (List<String[]>)session.getAttribute(sessionid+"groupClient");
				Integer listLength = result.size();
				for(int i=0;i<=listLength-1;i++){
					Compre compre = new Compre();
					for(int j=0;j<=result.get(i).length;j++){
						switch (j) {
						case 0://总金额
							//bee.setBeename(result.get(i)[j]);
							compre.setPrice(Integer.parseInt(result.get(i)[j]));
							break;
						case 1://手机数量
							compre.setPhoneNumber(Integer.parseInt(result.get(i)[j]));
							//bee.setBeecard(result.get(i)[j]);
							break;
						case 2://期数
							//bee.setPhone(result.get(i)[j]);
							compre.setPeriods(Integer.parseInt(result.get(i)[j]));
							break;
						case 3://客户经理
							compre.setManager(result.get(i)[j]);
							break;
						case 4://公司名
							compre.setCompany(result.get(i)[j]);
							break;
						default:
							break;
						}
					}
					compre.setCreateBy(creatby);
					compre.setCreateDate(format.format(new Date()));
					comList.add(compre);
				}
				//批量新增公司综合数据
				Integer resultCom = compreMapper.batchInsertCompre(comList);
				if(resultCom>0){
					outMap.put("data", resultCom);
					outMap.put("status", "SUCCESS");
				}else{
					outMap.put("data", resultCom);
					outMap.put("status", "FALSE");
				}
				return outMap;
			} catch (Exception e) {
				e.printStackTrace();
				outMap.put("data", 0);
				outMap.put("status", "EXCEPTION");
				return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 新增综合数据
	 */
	@Override
	public Map<String, Object> insertCompre(Compre compre, HttpSession session) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> outMap = new  HashMap<String, Object>();
		try {
			//获取客户经理名字
			String username = session.getAttribute("username").toString();
			compre.setCreateBy(username);
			compre.setManager(username);
			compre.setCreateDate(format.format(new Date()));
			Integer result = compreMapper.insertCompre(compre);
			if(result==1){
				logger.info("保存综合数据成功");
				outMap.put("status", "SUCCESS");
				outMap.put("data", result);
			}else{
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
}
