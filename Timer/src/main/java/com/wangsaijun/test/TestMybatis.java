package com.wangsaijun.test;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
import com.wbdp.tsp.dao.WbdpDevicebindingMapper;
import com.wbdp.tsp.dao.WbdpEarningMapper;
import com.wbdp.tsp.entity.WbdpEarning;
import com.wbdp.tsp.service.TripScoreService;
import com.wbdp.tsp.service.WbdpScoreService;
import com.wbdp.tsp.util.DateUtil;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestMybatis {

	@Autowired
	private WbdpScoreService wbdpScoreService;
	@Autowired
	private WbdpDevicebindingMapper wbdpDevicebindingMapper;
	@Autowired
	private TripScoreService wbdpTrip;
	@Autowired
	private WbdpEarningMapper wbdpEarningMapper;
	
	@Test
	public void test1(){
		//收益日期
		Date scoreDate = DateUtil.getNextDay(new Date());
		//录入时间
		Date saveDate = new Date();
		//评分表中前一天所有车牌
		List<String> list = wbdpScoreService.searchWbdpVehice();
		//根据车牌查询评分
		//int score = wbdpScoreService.searchDriveScore("");
		//绑定表中所有车牌
		List<String> l = wbdpDevicebindingMapper.searchWbdpVehice();
		//批量存储收益
		List<WbdpEarning> e = new ArrayList<WbdpEarning>();
		for(String s: l){
			WbdpEarning we = new WbdpEarning();
			//如果该车牌存在于上一天的评分表中，则将评分和收益录入收益表，否则将绿色出行录入收益表
			if(list.contains(s)){
				//车牌号码
				we.setPlateNumber(s);
				//车辆ID
				Long vehicleID = wbdpTrip.serchVehicleID(s);
				we.setVehicleId(vehicleID);
				//评分
				int score = wbdpScoreService.searchDriveScore(s);
				we.setDriveScore(score);
				//根据评分判断收益
				if(score>=95){
					we.setScoreEarning(3);
				}else if(score>90&&score<=95){
					we.setScoreEarning(2);
				}else if(score>=80&&score<90){
					we.setScoreEarning(1);
				}else{
					we.setScoreEarning(0);
				}
				//绿色出行
				we.setGreenDrive(0);
				//绑定收益
				we.setBinding(0);
				//续保收益
				we.setRenewal(0);
				//收益时间
				we.setEarningDate(scoreDate);
				//录入时间
				we.setSaveDate(saveDate);
				e.add(we);
			}else{
					//车辆ID
					Long vehicleID = wbdpTrip.serchVehicleID(s);
					//评分
					we.setDriveScore(0);
					//评分收益
					we.setScoreEarning(0);
					//绑定收益
					we.setBinding(0);
					//续保收益
					we.setRenewal(0);
					we.setVehicleId(vehicleID);
					//车牌号码
					we.setPlateNumber(s);
					//绿色出行收益
					we.setGreenDrive(1);
					//收益时间
					we.setEarningDate(scoreDate);
					//录入时间
					we.setSaveDate(saveDate);
					e.add(we);
			}
		}
		wbdpEarningMapper.insertWbdpEarning(e);
	}
	
	@Test
	public void test2(){
		List<String> list = wbdpDevicebindingMapper.searchWbdpVehice();
		for(String s:list){
			System.out.println(s);
		}
	}
}
