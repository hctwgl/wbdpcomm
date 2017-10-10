package com.wangsaijun.test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import com.wbdp.tsp.dao.WbdpScoreMapper;
import com.wbdp.tsp.entity.WbdpDevicebinding;
import com.wbdp.tsp.entity.WbdpEarning;
import com.wbdp.tsp.entity.WbdpScore;
import com.wbdp.tsp.entity.WbdpTrip;
import com.wbdp.tsp.service.TrafficViolationService;
import com.wbdp.tsp.service.TripScoreService;
import com.wbdp.tsp.service.WbdpScoreService;
import com.wbdp.tsp.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestClass {

	@Autowired
	private TripScoreService wbdpTrip;
	@Autowired
	private WbdpScoreMapper wbdpScoreMapper;
	@Autowired
	private WbdpEarningMapper wbdpEarningMapper;
	@Autowired
	private WbdpDevicebindingMapper wbdpDevicebindingMapper;
	@Autowired
	private WbdpScoreService wbdpScoreService;
	@Autowired
	private TrafficViolationService trafficViolationService;
	@Test
	public void test() {
		//分组查询数据
		List<WbdpTrip> list = wbdpTrip.searchWbdpTrip();
		
		//查询夜间行驶时间
		List<WbdpTrip> n = wbdpTrip.searchNightDrive();
		
		// 存储存在夜间行驶行为的设备号
		List<String> nlist = new ArrayList<String>();
		if (n.size() >0) {
			for (WbdpTrip trip : n) {
				if(trip!=null){
					nlist.add(trip.getDeviceNumber());
				}
			}
		}
		//查询疲劳驾驶次数
		List<WbdpTrip> f = wbdpTrip.searchFatigueDriveDrive();
		
		//查询超速次数
		List<WbdpTrip> o
		= wbdpTrip.searchOverSpeed();
		//存储超速中的所有设备号
		List<String> plist = new ArrayList<String>();
		for(WbdpTrip trip:o){
			plist.add(trip.getDeviceNumber());
		}
		//System.out.println("超速次数："+o.size());
		//批量插入
		List<WbdpScore> s = new ArrayList<WbdpScore>();
		//System.out.println("..."+n.size());
		for(WbdpTrip w:list){
			WbdpScore score = new WbdpScore();
			//总分
			int driveScore = 0;
			//车牌
			String plateNumber = "";
			if("123ABC1492483078751".equals(w.getDeviceNumber())){
				plateNumber = "粤B2U5P2";
			}else {
				 plateNumber = wbdpTrip.searchPlateNumber(w.getDeviceNumber());
			}
			//车辆ID
			Long vehicleID = wbdpTrip.serchVehicleID(plateNumber);
			score.setVehicleId(vehicleID);
			score.setPlateNumber(plateNumber);
			//急刹车次数、分数
			int suddenBrake = w.getSuddenBrake();
			score.setSuddenBrake(w.getSuddenBrake());
			if(suddenBrake==0){
				score.setSbScore(12);
				driveScore+=12;
			}else if(suddenBrake==1){
				score.setSbScore(11);
				driveScore+=10.8;
			}else if(suddenBrake==2){
				score.setSbScore(10);
				driveScore+=10;
			}
			else if(suddenBrake==3){
				score.setSbScore(8);
				driveScore+=8;
			}
			else if(suddenBrake==4){
				score.setSbScore(7);
				driveScore+=7;
			}
			else if(suddenBrake==5){
				score.setSbScore(5);
				driveScore+=5;
			}else if(suddenBrake==6){
				score.setSbScore(2);
				driveScore+=2;
			}else {
				score.setSbScore(0);
				driveScore+=0;
			}
			//急加速次数、分数
			int rapidSpeed = w.getRapidSpeed();
			score.setRapidSpeed(rapidSpeed);
			if(rapidSpeed==0){
				score.setRsScore(8);
				driveScore+=8;
			}else if(rapidSpeed==1){
				score.setRsScore(7);
				driveScore+=7;
			}else if(rapidSpeed==2){
				score.setRsScore(6);
				driveScore+=6;
			}
			else if(rapidSpeed==3){
				score.setRsScore(6);
				driveScore+=6;
			}
			else if(rapidSpeed==4){
				score.setRsScore(5);
				driveScore+=5;
			}
			else if(rapidSpeed==5){
				score.setRsScore(3);
				driveScore+=3;
			}else if(rapidSpeed==6){
				score.setRsScore(2);
				driveScore+=2;
			}else {
				score.setRsScore(0);
				driveScore+=0;
			}
			//急转弯次数
			int suddenTurn = w.getSuddenTurn();
			score.setSuddenTurn(suddenTurn);
			//System.out.println("急转弯："+suddenTurn);
			if(suddenTurn==0){
				score.setStScore(10);
				driveScore+=10;
			}else if(suddenTurn==1){
				score.setStScore(9);
				driveScore+=9;
			}else if(suddenTurn==2){
				score.setStScore(8);
				driveScore+=8;
			}
			else if(suddenTurn==3){
				score.setStScore(7);
				driveScore+=7;
			}
			else if(suddenTurn==4){
				score.setStScore(6);
				driveScore+=6;
			}
			else if(suddenTurn==5){
				score.setStScore(4);
				driveScore+=4;
			}else if(suddenTurn==6){
				score.setStScore(2);
				driveScore+=2;
			}else {
				score.setStScore(0);
				driveScore+=0;
			}
			//平均速度km/h、分数
			int mail = w.getMail()/1000;
			double driveTime = ((double)w.getDrivertime())/3600;
			double meanSpeed = Double.parseDouble(new DecimalFormat("#.00").format(mail/driveTime));
			score.setMeanSpeed(meanSpeed);
			if(meanSpeed<=80){
				score.setMsScore(5);
				driveScore+=5;
			}else if(meanSpeed>80&&meanSpeed<=100){
				score.setMsScore(4);
				driveScore+=4;
			}else if(meanSpeed>100&&meanSpeed<=120){
				score.setMsScore(2);
				driveScore+=2;
			}else{
				score.setMsScore(0);
				driveScore+=0;
			}
			//超速
			if(o.size()==0){
				score.setOverSpeed(0);
				score.setOsScore(12);
				driveScore+=12;
			}else{
				//如果当前设备号存在于超速设备表中，则根据超速次数得分，否则满分
				if(plist.contains(w.getDeviceNumber())){
				for(WbdpTrip wo:o){
						if(w.getDeviceNumber().equals(wo.getDeviceNumber())){
							int overSpeed = wo.getOverSpeedTime();
							score.setOverSpeed(overSpeed);
							if(overSpeed==0){
								score.setOsScore(12);
								driveScore+=12;
							}else if(overSpeed>0&&overSpeed<=2){
								score.setOsScore(10);
								driveScore+=10;
							}else if(overSpeed>2&&overSpeed<=4){
								score.setOsScore(7);
								driveScore+=7;
							}else if(overSpeed>=5){
								score.setOsScore(5);
								driveScore+=5;
							}
						}
				}
				}else{
					score.setOverSpeed(0);
					score.setOsScore(12);
					driveScore+=12;
				}
			}
			
			//驾驶时长、分数
			double drivertime = (double)(w.getDrivertime())/60;
			score.setDriveTime((int)drivertime);
			if(drivertime<60){
				score.setDtScore(8);
				driveScore+=8;
			}else if(drivertime>=60&&drivertime<90){
				score.setDtScore(6);
				driveScore+=6;
			}else if(drivertime>=90&&drivertime<120){
				score.setDtScore(5);
				driveScore+=5;
			}else if(drivertime>=120&&drivertime<150){
				score.setDtScore(3);
				driveScore+=3;
			}else if(drivertime>=150&&drivertime<180){
				score.setDtScore(2);
				driveScore+=2;
			}else{
				score.setDtScore(0);
				driveScore+=0;
			}
			// 夜间行驶时长、分数
			if (n.size()>0) {
				// 如果当前设备号存在于有夜间行驶记录的设备中，则根据时间得分，否则满分
				if (nlist.contains(w.getDeviceNumber())) {
					for (WbdpTrip wn : n) {
							if (w.getDeviceNumber().equals(wn.getDeviceNumber())) {
								System.out.println("夜间行驶时长:"+wn.getDrivertime()+"设备UUID："+w.getDeviceNumber());
								double driverNight = Double.parseDouble(new DecimalFormat("#.00").format((Double.parseDouble(wn.getDrivertime().toString())) / 3600));
								//double driverNight = (Double.parseDouble(wn.getDrivertime().toString())) / 3600;
								System.out.println(driverNight);
								score.setNightDrive(driverNight);
								if (driverNight == 0) {
									
									score.setNdScore(8);
									driveScore += 8;
								} else if (driverNight > 0 && driverNight <= 0.5) {
									
									score.setNdScore(6);
									driveScore += 6;
								} else if(driverNight > 0.5 && driverNight <= 1) {
									
									score.setNdScore(5);
									driveScore += 5;
								}else if (driverNight > 1) {
	
									score.setNdScore(3);
									driveScore += 3;
								}
							} 
					}
				} else {
					System.out.println("如果当前设备号存在于有夜间行驶记录的设备中，则根据时间得分，否则满分");
					score.setNightDrive((double)0);
					score.setNdScore(8);
					driveScore += 8;
				}
			}else {
				System.out.println("我是第一个判断");
				score.setNightDrive((double)0);
				score.setNdScore(8);
				driveScore += 8;
			}
			//疲劳驾驶次数、分数
			int fatigue = 0;
			for(WbdpTrip wf:f){
				if(w.getDeviceNumber().equals(wf.getDeviceNumber())){
					if((((int)wf.getDrivertime())/3600)>2&&(((int)wf.getDrivertime())/3600)<2.5){
						fatigue++;
					}else if((((int)wf.getDrivertime())/3600)>2.5&&(((int)wf.getDrivertime())/3600)<3){
						fatigue+=2;
					}else if((((int)wf.getDrivertime())/3600)>3&&(((int)wf.getDrivertime())/3600)<4){
						fatigue+=3;
					}else if((((int)wf.getDrivertime())/3600)>4){
						fatigue+=4;
					}
				}
			}
			score.setFatigueDrive(fatigue);
			if(fatigue==0){
				score.setFdScore(25);
				driveScore+=25;
			}else if(fatigue==1){
				score.setFdScore(10);
				driveScore+=10;
			}else if(fatigue==2){
				score.setFdScore(5);
				driveScore+=5;
			}else{
				score.setFdScore(0);
				driveScore+=0;
			}
			//怠速时长
			double idleTime = ((double)w.getIdleTime())/60;
			
			//System.out.println("怠速："+idleTime);
			
			double per = Double.parseDouble(new DecimalFormat("#.00").format(idleTime/drivertime));
			score.setIdleTime((int)(per*100));
			//System.out.println("行驶时间："+drivertime);
			//System.out.println("怠速时间百分比："+per);
			if(per<=0.2){
				
				score.setItScore(12);
				driveScore+=12;
			}else if(per>0.2&&per<=0.25){
				score.setItScore(10);
				driveScore+=10;
		
			}else if(per>0.25&&per<=0.3){
			
				score.setItScore(7);
				driveScore+=7;
			}else{
				score.setItScore(5);
				driveScore+=5;
			}
			//总分
			score.setDriveScore(driveScore);
			
			//评分日期
			Date scoreDate = DateUtil.getNextDay(new Date());
			score.setScoreDate(scoreDate);
			//录入时间
			Date saveDate = new Date();
			score.setSaveDate(saveDate);
			s.add(score);
			System.out.println("总分："+driveScore);
			//System.out.println(w.getDeviceNumber()+":"+score.getStScore());
	}
		wbdpScoreMapper.insertListWbdpScore(s);
	}

	@Test
	public void e() {

		//System.out.println("任务启动。。。。。。。。。。。"+sdf.format(new Date()));
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
					//如果在评分表中没有数据，则根据绑定状态判断绿色出行收益
					int binding = wbdpDevicebindingMapper.serchBinding(s);
					if(binding==1){
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
			}
			wbdpEarningMapper.insertWbdpEarning(e);
	}
	//测试推送违章信息
	 @Test
	  public void testtrafficViolation(){
		  try {
			  trafficViolationService.TrafficViolationPush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
}