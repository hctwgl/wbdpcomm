package JuintTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;








import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;












import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.wbdp.bee.dao.BrushcreditMapper;
import com.wbdp.bee.dao.Wbl_BeeDao;
import com.wbdp.bee.dao.Wbl_ModelDAO;
import com.wbdp.bee.dao.Wbl_ModelUserDAO;
import com.wbdp.bee.entity.Wbl_ModelEntity;
import com.wbdp.bee.util.AliyunVerify;
import com.wbdp.bee.util.RedisDataStore;



@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestMyBatis {
	@Autowired
	private Wbl_ModelUserDAO wbl_ModelUserDAO;
	@Autowired
	private Wbl_ModelDAO wbl_ModelDAO;
	@Autowired
	private BrushcreditMapper brushcreditMapper;
	@Autowired
	private  Wbl_BeeDao wbl_BeeDao;
	@Test
	public void testModel(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Long> list = new ArrayList<Long>();
		map.put("userId", 19l);
		list.add(7l);
		list.add(8l);
		list.add(9l);
		list.add(10l);
		list.add(11l);
		map.put("list", list);
		wbl_ModelUserDAO.updateModelGive(map);
		wbl_ModelUserDAO.updateModelRel(map);
	}
	@Test
	public void test01(){
		List<Map<String, Object>> list = wbl_BeeDao.downLoadBee(5l);
		for(Map<String,Object> map:list){
			System.out.println(map.get("BeeName").toString());
		}
	}
	@Test
	public void testModel02(){
		List<Wbl_ModelEntity> list = wbl_ModelDAO.selectWbl_Model(19l);
		for(Wbl_ModelEntity w:list){
			System.out.println(w.getModelName());
		}
	}
	@Test
	public void testModel03(){
		//对身份证号码做处理
		String BeeCard = "430423199408131416";
		String name = "汪赛";
		String msg = AliyunVerify.query(name, BeeCard);
		com.alibaba.fastjson.JSONObject obj = JSON.parseObject(msg);
		String result = obj.getString("result");
		com.alibaba.fastjson.JSONObject resultobj = JSON.parseObject(result);
		/*StringBuilder sb = new StringBuilder(BeeCard);
		BeeCard = sb.replace(6, 14, "********").toString();*/
		System.out.println(resultobj.getString("verifystatus"));
	}
	//测试集合对象存入redis
		@Test
		public void testRedis(){
			Map<String,Object> mapPollen= new HashMap<String,Object>();
			mapPollen.put("name", "汪赛军");
			mapPollen.put("age", 23);
			mapPollen.put("like", "eat");
			Object obj = JSON.toJSON(mapPollen);
			Map<String,Object> json = (Map<String,Object>) JSONObject.fromObject(obj.toString());  
			//System.out.println(json.get("age") instanceof Integer);
			Jedis jedis = RedisDataStore.getconnetion();
			jedis.get("13714318835"+"PUBLIC");
			System.out.println(jedis.get("13714318835PUBLIC"));
			//jedis.hmset("orderMap", mapPollen);
		}
}
