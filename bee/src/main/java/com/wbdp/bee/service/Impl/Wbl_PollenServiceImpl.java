package com.wbdp.bee.service.Impl;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.bee.api.aliyun.AliyunBankCard;
import com.wbdp.bee.dao.Wbl_BeeDao;
import com.wbdp.bee.dao.Wbl_PollenDAO;
import com.wbdp.bee.dao.Wbl_SocialdataDAO;
import com.wbdp.bee.entity.Wbl_BeeEntity;
import com.wbdp.bee.entity.Wbl_PollenEntity;
import com.wbdp.bee.service.Wbl_BeeService;
import com.wbdp.bee.service.Wbl_PollenService;
import com.wbdp.bee.util.UtilPackaging;



@Service
public class Wbl_PollenServiceImpl implements Wbl_PollenService {

	@Resource
	private Wbl_SocialdataDAO SocialdataDAO;
	@Resource
	private Wbl_PollenDAO PollenDAO;
	
	//日志
	private Logger logger=Logger.getLogger(Wbl_PollenServiceImpl.class);
    //时间格式化
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 方法名: getPollenTest   
	 * 方法描述: 后台试算花粉额度(不保存到数据库) 
	 * 入参描述: 根据入参beeid获取社保账号，密码，城市计算花粉额度                         socialaccount:社保账号,socialpassword:社保密码
	 * 成功回调: {"data":{"total":9300},"msg":"","stutus":"SUCCESS"}
	 * 失败回调: {"code":"1102","msg":"用户名或密码不匹配，请重新输入","token":"88643d5e4d464836aff2691d859bc47e"}
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月13日
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String getPollenTest(Long beeid) {
		// TODO Auto-generated method stub
		Map<String,Object> info=null;        //客户信息(客户ID,姓名,社保账号密码,车险号码,信用卡号)
		Integer resultShebao=0;           //社保查询的花粉额度等信息
		Integer resultChexian=0;          //车险查询出的花粉额度 
		Integer resultXinyongka=0;        //信用卡查询出的花粉额度
		Integer total=0;       //总花粉金额(社保花粉额度+车险花粉额度+信用卡花粉额度)     
		
		try {
		//获取客户信息,幷分别计算额度
			info=PollenDAO.getPollenInfoOfBee(beeid);
		//如果客户信息里包含社保账号密码则获取花粉额度
			if(info.get("SocialAccount")!=null&&info.get("SocialPassWord")!=null){
				resultShebao=(Integer) this.shebaoPollen(info.get("SocialAccount").toString(),info.get("SocialPassWord").toString()).get("huaFen");
			}
		//如有客户信息包含车险号码则获取花粉额度
			if(info.get("InsuranceNum")!=null){
				resultChexian=this.chexianPollen(info.get("InsuranceNum").toString());
			}
		//如果客户信息包含信用卡则获取花粉额度
			if(info.get("CreditCard")!=null&&info.get("BankName")!=null){
				resultXinyongka=this.xinyongkaPollen(info.get("BeeName").toString(),info.get("BeeCard").toString(),info.get("CreditCard").toString());
			}
		//如果客户是电信老用户则获取花粉额度
			if(info.get("IsNoOlder").equals("0")){
				resultXinyongka=this.isOlder();
			}	
		//计算总花粉额度(总额度不能超过1W)	
			total=resultShebao+resultChexian+resultXinyongka;
			total=total>10000?10000:total;
          //封装数据 
             Map<String,Object> data=new HashMap<String, Object>();
                      //  data.put("corpName", corpName);
                      //  data.put("bal", bal);
                     //  data.put("baseDeposit", baseDeposit);
                     //  data.put("number", numBer);
                         data.put("total",total);         
            return JSON.toJSONString(UtilPackaging.toResultMap(data));   
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("平台试算花粉额度异常:"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		 return null;
	}


	

	/**
	 * 方法名: getPollen  
	 * 方法描述: 微信获取花粉额度(保存到数据库) 
	 * 入参描述: 根据入参beeid获取社保账号，密码，城市计算花粉额度      
	 * 回调描述: {"data":9300,"msg":"","stutus":"SUCCESS"}   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月13日
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String getPollen(Long beeid) {
		// TODO Auto-generated method stub
		Map<String,Object> info=null;        //客户信息(客户ID,姓名,社保账号密码,车险号码,信用卡号)
		Integer resultShebao=0;           //社保查询的花粉额度等信息
		Integer resultChexian=0;          //车险查询出的花粉额度 
		Integer resultXinyongka=0;        //信用卡查询出的花粉额度
		Integer resultIsOlder=0;          //电信老用户额度
		Integer total=0;                  //总花粉金额(社保花粉额度+车险花粉额度+信用卡花粉额度) 
		
	 //判断客户是否是第一次申请和大于一个月后的重新申请花粉额度
	if(PollenDAO.checkBeeid(beeid)==0||PollenDAO.checkBeeidOfDate(beeid)==true){		
   try {
	 //获取客户信息,幷分别计算额度
		info=PollenDAO.getPollenInfoOfBee(beeid);
		System.out.println(JSON.toJSONString(info));
	//如果客户信息里包含社保账号密码则获取花粉额度(并添加或修改到数据库)
	    if(info.get("SocialAccount")!=null&&info.get("SocialPassWord")!=null){
		  //获取到调用立木征信查询出的社保数据
		  Map<String, Object> resultSocial=this.shebaoPollen(info.get("SocialAccount").toString(),info.get("SocialPassWord").toString());
			//----------------------------------------保存和修改数据库----------------------------------
			 //取出社保数据保存到社保表
			  SocialdataDAO.updateSocialdata(beeid,resultSocial.get("corpName").toString(),(Integer)resultSocial.get("bal"),(Integer)resultSocial.get("baseDeposit"),(Integer)resultSocial.get("numBer"),(Integer)resultSocial.get("huaFen"),df.format(new Date()));
	           //----------------------------------------End----------------------------------    
	         //取出社保计算的花粉金额(最新获取的社保花粉额度减去已用花粉额度)    
//	          Wbl_PollenEntity pollen =  PollenDAO.getWbl_PollenEntity(beeid);
			  resultShebao=resultShebao<=0?0:resultShebao;
		}
	    
	//如有客户信息包含车险号码则获取花粉额度
		if(info.get("InsuranceNum")!=null){
			resultChexian=this.chexianPollen(info.get("InsuranceNum").toString());
		}
		
	//如果客户信息包含信用卡则获取花粉额度(客户姓名,身份证号,信用卡号)
		if(info.get("CreditCard")!=null&&info.get("BankName")!=null){
			resultXinyongka=this.xinyongkaPollen(info.get("BeeName").toString(),info.get("BeeCard").toString(),info.get("CreditCard").toString());
		}
		
	//如果客户是电信老用户则获取花粉额度
		/*if(info.get("IsNoOlder").equals(0)){
			resultIsOlder=this.isOlder();
		}*/
		
	//计算总花粉额度(总额度不能超过1W)	
		total=resultShebao+resultChexian+resultXinyongka;//+resultIsOlder;
		//获取用户类型
		Integer beeType = (Integer)info.get("BeeType");
		//若类型为2则代表用户为公拉私客户，需要查询公司最大额度，若计算出的额度大于公司额度，则使用公司额度，否则使用计算出来的额度
		if(beeType==2){
			Integer maxAmount = PollenDAO.getCompanyMaxAmount(info.get("Company").toString());
			total=total>maxAmount?maxAmount:total;
		}else{
			total=total>10000?10000:total;
		}
		
		
	//保存花粉金额到数据库(如果第一次申请则添加数据库,否则为更新数据库(总额度-已使用额度))
        if(PollenDAO.checkBeeid(beeid)==0){
       	    PollenDAO.insertPollen(beeid, total, df.format(new Date()));
           }else{
       	    PollenDAO.updatePollen(beeid,total, df.format(new Date()));
           }
        
	return JSON.toJSONString(UtilPackaging.toResultString(total.toString()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("微信获取花粉额度异常:"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
   //如果该客户一个月内再次申请,则返回数据库中保存的花粉额度(总额度-已使用额度)
   }else{
	   Wbl_PollenEntity pollen =  PollenDAO.getWbl_PollenEntity(beeid);
	    total=pollen.getCreditlimit();
	    total=total<=0?0:total;
	   return JSON.toJSONString(UtilPackaging.toResultString(total.toString()));
    }
		return null;
	}
	



	/**
	 * 方法名: 根据社保计算花粉额度  
	 * 方法描述:  
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月21日
	 */
	protected Map<String, Object> shebaoPollen(String SocialAccount,String SocialPassWord){
		String corpName=null;                  //获取社保目前所缴纳公司
		int bal=0;                             //获取社保总额
		int baseDeposit=0;                     //获取社保基数
		int numBer=0;                          //获取在当前公司工作时间(月) 
		int Total_shebao=0;                    //计算花粉总额(元)
		String city=null;                      //查询出城市
		Map<String,Object> result=new HashMap<String, Object>(); //返回查询的结果集
		//根据beeid获取社保账号,密码,城市代号
//		Map<String,String> map=SocialdataDAO.getSocialdataUserAndPass(beeid);
//		    SocialAccount=map.get("SocialAccount");
//		   SocialPassWord=map.get("SocialPassWord");
//		             city=map.get("City");
		//设置假数据
		  Jedis redis = new Jedis ("45.249.95.141",6379);
		        redis.auth("woshijiang");
	          String resultJson=redis.get("lina");
		     //解析立木社保查询接口返回的json
	       JSONObject json=(JSONObject) JSON.parseObject(resultJson);
   try {
   //如果查询成功则继续计算花粉信用   
  if(json.getString("code").equals("0000")||json.getIntValue("code")==0000){ 
          //获取data里面的值
          JSONObject jsonData=(JSONObject) json.get("data");
           //获取当前工作公司
          corpName=jsonData.getString("corpName");
           //获取社保总额度
             JSONArray insurancesArray=(JSONArray)JSONArray.parse(jsonData.get("insurances").toString());
                 JSONObject insurances0=(JSONObject) insurancesArray.get(0);  
                  bal=Double.valueOf(insurances0.get("bal").toString()).intValue();          
            //获取社保缴纳详细记录
               JSONArray pensionDetails=(JSONArray) JSONArray.parse(jsonData.get("pensionDetails").toString());
                  //获取目前所在公司名称与基数
        	       JSONObject now=(JSONObject) pensionDetails.get(0);
        	        //获取目前公司名称
        	          String temp=now.getString("corpName");
        	         //获取目前公司社保缴纳基数  
        	          baseDeposit=Double.valueOf(now.get("baseDeposit").toString()).intValue();
               //遍历记录计算出在目前公司工作年限(月)
                  for(int i=0;i<pensionDetails.size();i++){
                	  JSONObject all=(JSONObject) pensionDetails.get(i);
                	      //计算出当前公司出现次数
                	   if(all.getString("corpName").equals(temp)){
                		   numBer++;
                	   }
                  }
           //计算花粉额度(百位取整)       
             if(numBer>=1&&numBer<3){
            	Total_shebao=(int) ((baseDeposit+(bal*0.5))*1);
            	Total_shebao=(Total_shebao)/10000%10*10000+(Total_shebao)/1000%10*1000+(Total_shebao)/100%10*100;
             }else if(numBer>=3&&numBer<6){
            	Total_shebao=(int) ((baseDeposit+(bal*0.5))*1.5); 
            	Total_shebao=(Total_shebao)/10000%10*10000+(Total_shebao)/1000%10*1000+(Total_shebao)/100%10*100;
             }else if(numBer>=6){
                Total_shebao=(int) ((baseDeposit+(bal*0.5))*2); 
                Total_shebao=(Total_shebao)/10000%10*10000+(Total_shebao)/1000%10*1000+(Total_shebao)/100%10*100;
             }  
            //取舍花粉额度(最低给5000,最高给10000)
             Total_shebao=Total_shebao<5000?5000:Total_shebao;
             Total_shebao=Total_shebao>10000?10000:Total_shebao;
            //--------------------------------------- 
             logger.warn("-------社保查询结束------");
             logger.warn("所在公司:"+corpName);
             logger.warn("社保总额:"+bal);
             logger.warn("社保基数"+baseDeposit);
             logger.warn("目前公司工作年限:"+numBer);
             logger.warn("花粉总额度:"+Total_shebao); 
            //封装 
             result.put("corpName", corpName);
             result.put("bal", bal);
             result.put("baseDeposit", baseDeposit);
             result.put("numBer", numBer);
             result.put("huaFen",Total_shebao); 
             return result;  
           }
          //如果查询失败,则返回失败0
          else{
        	  result.put("huafen", 0);
        	  return result;        	   
           }        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("社保计算花粉额度异常:"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return null;
	}
	
	/**
	 * 方法名: chexianPollen   
	 * 方法描述:  根据车险获取花粉额度
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月21日
	 */
	protected Integer chexianPollen(String InsuranceNum){
		 if(InsuranceNum!=null||InsuranceNum!=""){
			 return 10000; 
		  }
		logger.warn("根据车险获取的花粉额度:"+0);
		return 0;
	}
	
	/**
	 * 方法名: xinyongkaPollen   
	 * 方法描述:  根据信用卡获取花粉额度
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月21日
	 */
	protected Integer xinyongkaPollen(String BeeName,String BeeCard,String CreditCard){
		//判断是否有入参传入
		if(BeeName!=null && BeeCard!=null && CreditCard!=null){
			//如果有入参则调用银行卡三要素认证(如果验证通过则给10000额度)
			if(AliyunBankCard.query(CreditCard, BeeCard, BeeName)){
				return 10000;
			}
		}
		logger.warn("根据信用卡获取的花粉额度:"+0);
		return 0;
	}
	
	/**
	 * 方法名: xinyongkaPollen   
	 * 方法描述:  
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年8月18日
	 */
	protected Integer isOlder(){
		
		return 10000;
	}
	
}
