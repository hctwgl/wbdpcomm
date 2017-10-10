package JuintTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.bee.api.limu.LimuSocialSecurity;


public class LocalTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String corpName=null;                  //获取社保目前所缴纳公司
		int bal=8900;                             //获取社保总额
		int baseDeposit=2050;                     //获取社保基数
		int number=0;                          //获取在当前公司工作时间(月) 
		int total=0;                           //计算花粉总额(元)
		
       int Total_shebao=5050;
       
       Total_shebao=Total_shebao<5000?5000:Total_shebao;
       Total_shebao=Total_shebao>10000?10000:Total_shebao;
		System.out.println(Total_shebao);
	}
	
	
	void temp(){
		/**
		 *   
  <sql id="bee_Column_List" >
    be.ID,be.BeeWX,be.Phone,be.BeeName,be.Sex,be.BeeCard,be.CardImage,be.Marriage,be.Education,be.CreatBy,be.CreatDate,be.UpDateBy,be.UpDateTime
  </sql>
  
    <sql id="beeaddress_Column_List" >
    bs.ID,bs.BeeID,bs.Province,bs.City,bs.Area,bs.DetAddress,bs.CreatDate,bs.type
  </sql>
  
  <sql id="linkman_Column_List" >
    ln.ID,ln.BeeID,ln.LinkManName,ln.Relation,ln.Phone,ln.CreatBy,ln.CreatDate,ln.UpDateBy,ln.UpDateTime
  </sql>
  
   <sql id="bankcard_Column_List" >
    bd.ID,bd.BeeID,bd.Bank,bd.OpenBank,bd.BankCard,bd.City,bd.CreatBy,bd.CreatDate,bd.UpDateBy,bd.UpDateTime
  </sql>
  
   <sql id="socialdata_Column_List" >
    sa.ID,sa.BeeID,sa.SocialAccount,sa.SocialPassWord,sa.Base,sa.Balance,sa.Company,sa.PayTime,sa.SocialState,sa.CreatBy,sa.CreatDate,sa.UpdateBy,sa.UpdateTime
  </sql>
  
  <sql id="pollen_Column_List" >
	 pn.ID,pn.pneID,pn.CreditLimit,pn.CreditDate,pn.CreatDate,pn.UpdateTime
  </sql> 
		      
		      
		      
		 */
	}
}
